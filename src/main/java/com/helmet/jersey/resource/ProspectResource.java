package com.helmet.jersey.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.StringUtils;

import com.helmet.api.AdminService;
import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
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
    System.out.println("In Post Method...");
    boolean isProcessed = false;
    String message = null;
    Prospect prospect = multiPart.getBodyParts().get(0).getEntityAs(Prospect.class);
    System.out.println("Found prospect: " + prospect.toString());
    User user = createUser(prospect);
    System.out.println("Created User...");
    Applicant applicant = creatApplicant(user, prospect);
    System.out.println("Created Applicant...");
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    Consultant consultant = adminService.getProspectConsultant(prospect.getAgencyId());
    if (consultant == null)
    {
      System.out.println("Prospect Consultant NOT found...");
      return Response.status(Response.Status.BAD_REQUEST).entity("Prospect Consultant NOT Found.").type(MediaType.TEXT_PLAIN).build();
    }
    else
    {
      System.out.println("Prospect Consultant found...");
      AgyService agyService = ServiceFactory.getInstance().getAgyService();
      int rowCount = 0;
      try
      {
        System.out.println("About to Insert Applicant...");
        rowCount = agyService.insertApplicant(applicant, consultant.getConsultantId());
        System.out.println("Inserted Applicant...");
        if (multiPart.getBodyParts().size() == 2)
        {
          System.out.println("Multipart contains file...");
          BodyPartEntity bpe = (BodyPartEntity)multiPart.getBodyParts().get(1).getEntity();
          try
          {
            InputStream inputStream = bpe.getInputStream();
            isProcessed = saveCvFile(applicant, inputStream);
          }
          catch (Exception e)
          {
            message = e.getMessage();
          }
        }
        else
        {
          System.out.println("Does NOT contain file...");
          isProcessed = true;
        }
        String notes = applicant.getReference() + "\nProfession: " + prospect.getProfession() + "\nLength of Stay: " + prospect.getLengthOfStay();
        saveApplicantNotes(applicant, notes);
      }
      catch (DuplicateDataException e)
      {
        System.out.println("Applicant Insert Failed...");
      }
      if (isProcessed)
      {
        return Response.status(Response.Status.ACCEPTED).entity("Attachement processed successfully.").type(MediaType.TEXT_PLAIN).build();
      }
      return Response.status(Response.Status.BAD_REQUEST).entity("Failed to process attachment. Reason : " + message).type(MediaType.TEXT_PLAIN).build();
    }
  }

  private User createUser(Prospect prospect)
  {
    System.out.println("Start createUser()");
    User user = new User();
    user.setFirstName(prospect.getFirstName());
    user.setLastName(prospect.getLastName());
    user.setEmailAddress(prospect.getEmail());
    // From ApplicantNewProcess
    String temp = Long.toString(new java.util.Date().getTime());
    user.setLogin(temp);
    user.setPwd(temp);
    user.setPwdHint(temp);
    System.out.println("End createUser()");
    return user;
  }

  private Applicant creatApplicant(User user, Prospect prospect)
  {
    System.out.println("Start creatApplicant()");
    Applicant applicant = new Applicant();
    // Put User into Applicant.
    applicant.setUser(user);
    applicant.setGender(prospect.getGender().getCode());
    applicant.setAgencyId(prospect.getAgencyId());
    // Agency Paperwork.
    applicant.setReference("Prospect from PJ Locums.");
    // Can NOT be null. >>>>>
    applicant.setNiNumber("");
    applicant.setHpcNumber("");
    applicant.setAhpRegistrationType(0);
    applicant.setHpcAlertNotification(0);
    // <<<<< Can NOT be null.
    applicant.setDisciplineCategoryId(prospect.getDisciplineId());
    applicant.setPassportType(prospect.getPassportId());
    applicant.setVisaType(prospect.getVisaId());
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
    System.out.println("End creatApplicant()");
    return applicant;
  }
  
  protected Date convertDate(String dateStr, SimpleDateFormat simpleDateFormat)
  {
    System.out.println("Start convertDate()");
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
    System.out.println("End convertDate()");
    return date;
  }

  private boolean saveCvFile(Applicant applicant, InputStream inputStream)
  {
    boolean isProcessed = false;;
    String filePath = FileHandler.getInstance().getCvFileLocation() + applicant.getCvFileUrl();
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
      System.out.println("IOException - uploading " + applicant.getCvFilename());
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
          System.out.println("CV File: " + applicant.getCvFilename() + " saved ok.");
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
    System.out.println("Start saveApplicantNotes()");
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
      System.out.println("Notes File: " + notesFileName + " saved ok.");
    }
    catch (IOException e)
    {
      // TODO 
      System.out.println("IOException - saving " + notesFileName);
      e.printStackTrace();
    }
    System.out.println("End saveApplicantNotes()");
  }
  
}
