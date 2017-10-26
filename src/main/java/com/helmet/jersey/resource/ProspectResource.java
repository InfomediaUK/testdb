package com.helmet.jersey.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.api.AdminService;
import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;
import com.helmet.bean.Consultant;
import com.helmet.bean.User;
import com.helmet.xml.jaxb.model.Prospect;
import com.sun.jersey.multipart.BodyPartEntity;
import com.sun.jersey.multipart.MultiPart;

/**
 * @author infomedia
 *
 */
// Will map the resource to the URL prospects
@Path("/prospect")
public class ProspectResource
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  @POST
  @Consumes("multipart/mixed")
  public Response post(MultiPart multiPart)
  {
    logProgress("In ProspectResource Post Method......");
    boolean isProcessed = false;
    String message = null;
    Prospect prospect = multiPart.getBodyParts().get(0).getEntityAs(Prospect.class);
    logProgress("Found prospect: " + prospect.toString());
    User user = createUser(prospect);
    logProgress("Created User...");
    Applicant applicant = createApplicant(user, prospect);
    logProgress("Created Applicant...");
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    Agency agency = adminService.getAgency(prospect.getAgencyId());
    if (agency == null)
    {
      logProgress("Agency NOT found...");
      return Response.status(Response.Status.NOT_FOUND).entity("Agency NOT Found for Agency Id: " + prospect.getAgencyId()).build();
    }
    Consultant consultant = adminService.getProspectConsultant(prospect.getAgencyId());
    if (consultant == null)
    {
      logProgress("Prospect Consultant NOT found...");
      return Response.status(Response.Status.NOT_FOUND).entity("Prospect Consultant NOT Found for Agency: " + agency.getName()).build();
    }
    else
    {
      logProgress("Prospect Consultant found...");
      AgyService agyService = ServiceFactory.getInstance().getAgyService();
      int rowCount = 0;
      try
      {
        logProgress("About to Insert Applicant...");
        rowCount = agyService.insertApplicant(applicant, consultant.getConsultantId());
        logProgress("Inserted Applicant...");
        if (multiPart.getBodyParts().size() == 2)
        {
          logProgress("Multipart contains file...");
          BodyPartEntity bpe = (BodyPartEntity)multiPart.getBodyParts().get(1).getEntity();
          try
          {
            InputStream inputStream = bpe.getInputStream();
            isProcessed = saveCvFile(applicant, inputStream);
            if (isProcessed)
            {
              message = "Applicant loaded and attachement processed successfully.";
            }
          }
          catch (Exception e)
          {
            message = e.getMessage();
          }
        }
        else
        {
          logProgress("Does NOT contain file...");
          message = "Applicant loaded successfully.";
          isProcessed = true;
        }
        String notes = applicant.getReference() + "\nProfession: " + prospect.getProfession() + "\nLength of Stay: " + prospect.getLengthOfStay();
        saveApplicantNotes(applicant, notes);
      }
      catch (DuplicateDataException e)
      {
        message = "Applicant Insert Failed (Duplicate)...";
        logProgress(message);
      }
      catch (Exception e)
      {
        message = "Applicant Insert Failed: " + e.getMessage();
        logProgress(message);
      }
      logProgress("Processed: " + isProcessed);
      if (isProcessed)
      {
        return Response.status(Response.Status.ACCEPTED).entity(message).type(MediaType.TEXT_PLAIN).build();
      }
      return Response.status(Response.Status.BAD_REQUEST).entity("Failed. Reason : " + message).type(MediaType.TEXT_PLAIN).build();
    }
  }

  private User createUser(Prospect prospect)
  {
    logProgress("Start createUser()");
    User user = new User();
    user.setFirstName(prospect.getFirstName());
    user.setLastName(prospect.getLastName());
    user.setEmailAddress(prospect.getEmail());
    // From ApplicantNewProcess
    String temp = Long.toString(new java.util.Date().getTime());
    user.setLogin(prospect.getEmail());
    user.setPwd(prospect.getEmail());
    user.setPwdHint("Please call us and we'll tell you.");
    logProgress("End createUser()");
    return user;
  }

  private Applicant createApplicant(User user, Prospect prospect)
  {
    logProgress("Start creatApplicant()");
    Applicant applicant = new Applicant();
    // Put User into Applicant.
    applicant.setUser(user);
    applicant.setGender(prospect.getGender().getCode());
    applicant.setAgencyId(prospect.getAgencyId());
    // Agency Paperwork.
    applicant.setReference("Prospect from PJ Locums.");
    // Can NOT be null. >>>>>
    applicant.setNiNumber("");
    applicant.setRegistrationNumber("");
    applicant.setAhpRegistrationType(0);
    applicant.setRegistrationAlertNotification(0);
    applicant.setVisaType(prospect.getVisaId() == null ? 0 : prospect.getVisaId());
    // <<<<< Can NOT be null.
    applicant.setDisciplineCategoryId(prospect.getDisciplineId());
    applicant.setIdDocumentId(prospect.getIdDocumentId());
    applicant.setMobileNumber(prospect.getMobileTelephone());
    applicant.setTelephoneNumber(prospect.getMobileTelephone());
    applicant.setCvFilename(prospect.getDocumentFileName());
    String availableForWork = prospect.getAvailableForWork();
    if (StringUtils.isNotEmpty(availableForWork))
    {
      // Date supplied.
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      Date availableForWorkDate = convertDate(availableForWork, sdf);
      applicant.setAvailabilityDate(availableForWorkDate);
    }
    logProgress("End creatApplicant()");
    return applicant;
  }
  
  protected Date convertDate(String dateStr, SimpleDateFormat simpleDateFormat)
  {
    logProgress("Start convertDate()");
    Date date = null;
    if (StringUtils.isNotEmpty(dateStr))
    {
      try
      {
        date = new Date(simpleDateFormat.parse(dateStr).getTime());
      }
      catch (ParseException e)
      {
        e.printStackTrace();
        return new Date(0);
      }
    }
    logProgress("End convertDate()");
    return date;
  }

  private boolean saveCvFile(Applicant applicant, InputStream inputStream)
  {
    boolean isProcessed = false;;
    String filePath = FileHandler.getInstance().getApplicantFileLocation() + applicant.getCvFileUrl();
    File folder = new File(filePath).getParentFile();
    if (!folder.exists())
    {
      // Create any required directories.
      folder.mkdirs();
    }
    // Read the InputStream and store it in a 'byteArrayOutputStream'.
    FileOutputStream fileOutputStream = null;
    try
    {
      fileOutputStream = new FileOutputStream(filePath);
      int read = 0;
      byte[] bytes = new byte[1024];
      while ((read = inputStream.read(bytes)) != -1) 
      {
        fileOutputStream.write(bytes, 0, read);
      }
    }
    catch (IOException e)
    {
      // TODO
      logProgress("IOException - uploading " + applicant.getCvFilename());
      e.printStackTrace();
    }
    finally
    {
      if (inputStream != null)
      {
        try
        {
          inputStream.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
      if (fileOutputStream != null)
      {
        try
        {
          fileOutputStream.close();
          isProcessed = true;
          logProgress("CV File: " + applicant.getCvFilename() + " saved ok.");
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    return isProcessed;
  }
  
  private void saveApplicantNotes(Applicant applicant, String notes)
  {
    logProgress("Start saveApplicantNotes()");
    String notesFileName = FileHandler.getInstance().getApplicantFileLocation() +
                           FileHandler.getInstance().getApplicantFileFolder() + 
                           "/" + applicant.getApplicantId() + "/notes.txt";
    File folder = new File(notesFileName).getParentFile();
    if (!folder.exists())
    {
      // Create any required directories.
      folder.mkdirs();
    }
    try
    {
      Utilities.saveFile(notesFileName, notes);
      logProgress("Notes File: " + notesFileName + " saved ok.");
    }
    catch (IOException e)
    {
      // TODO 
      logProgress("IOException - saving " + notesFileName);
      e.printStackTrace();
    }
    logProgress("End saveApplicantNotes()");
  }
  
  // Returns the string "Found"
  // Use http://localhost:8080/jersey/rest/prospect/test
  //
  @GET
  @Path("/test")
  @Produces(MediaType.TEXT_PLAIN)
  public String getTest()
  {
    return String.valueOf("Found");
  }

  private void logProgress(String message)
  {
    logger.info(message);
    System.out.println(message);    
  }
}
