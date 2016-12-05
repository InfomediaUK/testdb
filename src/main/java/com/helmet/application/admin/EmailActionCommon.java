package com.helmet.application.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.lang3.StringUtils;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.helmet.application.Utilities;
import com.helmet.application.admin.abztract.AdminAction;

public abstract class EmailActionCommon extends AdminAction
{
  protected void validateTemplate(ActionMessages errors, String template, String type, String fileExtension)
  {
    if (!template.endsWith(fileExtension))
    {
      errors.add("emailAction", new ActionMessage("errors.emailTemplate.mustEndWith", type, fileExtension));
    }
    if (!template.startsWith("/"))
    {
      errors.add("emailAction", new ActionMessage("errors.emailTemplate.mustStartWith", "/"));
    }
  }
  
  protected void validateTemplateFolder(ActionMessages errors, String templateFileName)
  {
    if (StringUtils.countMatches(templateFileName, "/") > 1)
    {
      // A folder has been specified...
      String templateFolderName = templateFileName.substring(0, templateFileName.lastIndexOf("/"));
      File templateFolder = new File(templateFolderName);
      if (!templateFolder.exists())
      {
        errors.add("emailAction", new ActionMessage("errors.emailTemplate.folderDoesNotExist", templateFolderName));
      }
    }    
  }
  
  protected boolean templateFileExists(String templateFileName)
  {
    File templateFile = new File(templateFileName);
    return templateFile.exists();
  }

  protected boolean writeTemplate(String templateFileName, String content)
  {
    if (templateFileExists(templateFileName))
    {
      // File already exists. Only overwrite if changed. Also could make backup here... 
      StringBuffer oldContent = new StringBuffer();
      Utilities.suckInFile(templateFileName, oldContent);
      if (!content.trim().equals(oldContent.toString().trim()))
      {
        // Content has changed.
        return writeTemplateFile(templateFileName, content);
      }
      return true;
    }
    else
    {
      // File does NOT exist, write it.
      return writeTemplateFile(templateFileName, content);
    }
  }
  
  private boolean writeTemplateFile(String templateFileName, String content)
  {
    BufferedWriter writer = null;
    try 
    {
      File templateFile = new File(templateFileName);
      // This will output the full path where the file will be written to...
      System.out.println(templateFile.getCanonicalPath());
      writer = new BufferedWriter(new FileWriter(templateFile));
      writer.write(content);
    } 
      catch (Exception e) 
    {
      e.printStackTrace();
      return false;
    } 
    finally 
    {
      try 
      {
        // Close the writer regardless of what happens...
        writer.close();
      } 
      catch (Exception e) 
      {
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }
  
}
