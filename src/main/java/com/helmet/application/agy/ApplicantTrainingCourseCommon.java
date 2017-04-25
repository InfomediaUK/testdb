package com.helmet.application.agy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.helmet.application.FileHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.ApplicantTrainingCourse;
import com.helmet.bean.TrainingCourse;

public abstract class ApplicantTrainingCourseCommon extends AgyAction
{
  protected String getDocumentationFileName(ApplicantTrainingCourse applicantTrainingCourse, TrainingCourse trainingCourse)
  {
    return trainingCourse.getName().replaceAll(" ", "") + applicantTrainingCourse.getApplicantTrainingCourseId() + ".pdf";
  }
  
  protected String uploadApplicantTrainingCourseFile(ApplicantTrainingCourse applicantTrainingCourse, FormFile applicantTrainingCourseFormFile) 
    throws FileNotFoundException, IOException
  {
    // File name should be in format "Training" + filename + .pdf.
    String fileUrl = FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantTrainingCourse.getApplicantId() + "/training/" + applicantTrainingCourse.getDocumentationFileName();
    String filePath = FileHandler.getInstance().getApplicantFileLocation() + fileUrl;

    // Read the InputStream and store it in a 'byteArrayOutputStream'.
    byte[] fileData = applicantTrainingCourseFormFile.getFileData();

    File folder = new File(filePath).getParentFile();
    if (!folder.exists())
    {
      // Create any required directories.
      folder.mkdirs();
    }
    FileOutputStream fileOutputStream = null;
    try
    {
      fileOutputStream = new FileOutputStream(filePath);
      fileOutputStream.write(fileData);
    }
    finally
    {
      fileOutputStream.close();
    }
    return filePath;
  }
  
  protected boolean validateDates(ActionMessages errors, ApplicantTrainingCourse applicantTrainingCourse)
  {
    Boolean result = true;
    if (applicantTrainingCourse.getStartDate() == null)
    {
      errors.add("applicantTrainingCourse", new ActionMessage("errors.required", "Start Date"));
      result = false;
    }
    if (applicantTrainingCourse.getEndDate() == null)
    {
      errors.add("applicantTrainingCourse", new ActionMessage("errors.required", "End Date"));
      result = false;
    }
    if (applicantTrainingCourse.getStartDate() != null && applicantTrainingCourse.getEndDate() != null)
    {
      if (applicantTrainingCourse.getStartDate().after(applicantTrainingCourse.getEndDate()))
      {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        errors.add("applicantTrainingCourse", new ActionMessage("errors.dateRangeInvalid", df.format(applicantTrainingCourse.getStartDate()), df.format(applicantTrainingCourse.getEndDate())));
        result = false;
      }
    }
    return result;
  }

}
