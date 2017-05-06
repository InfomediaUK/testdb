package com.helmet.application.agy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.helmet.bean.ApplicantEntity;
import com.helmet.bean.CompliancyTest;

public class ApplicantCompliancyTest
{
  private String startOfReasonMarker = "***** Start of Compliancy Test Failed *****";
  private String endOfReasonMarker   = "****** End of Compliancy Test Failed ******";

  // Private empty constructor
  private ApplicantCompliancyTest()
  {
  }

  private static final class SingletonHolder
  {
    static final ApplicantCompliancyTest applicantCompliancyTest = new ApplicantCompliancyTest();
  }
  
  public static ApplicantCompliancyTest getInstance()
  {
    return SingletonHolder.applicantCompliancyTest;
  }
  
  public void setEndOfReasonMarker(String endOfReasonMarker)
  {
    this.endOfReasonMarker = endOfReasonMarker;
  }

  public void setStartOfReasonMarker(String startOfReasonMarker)
  {
    this.startOfReasonMarker = startOfReasonMarker;
  }

  public Boolean isApplicantCompliant(List<CompliancyTest> listCompliancyTest, ApplicantEntity applicant, StringBuffer reason)
  {
    applicant.setCompliant(true);
    Class applicantClass = applicant.getClass();
    Class userClass      = applicant.getUser().getClass();
    Class addressClass   = applicant.getAddress().getClass();
    Object sourceObject  = null;
    Method method        = null;
    String methodName    = null;
    String propertyName  = null;
    String propertyValue = null;
    for (CompliancyTest compliancyTest : listCompliancyTest)
    {
      propertyName = compliancyTest.getProperty();
      propertyValue = compliancyTest.getValue();
      try
      {
        // The matcher.group() will be something like %fullName% and the method will be: getFullName.
        methodName = "get" + StringUtils.capitalize(propertyName.substring(propertyName.lastIndexOf(".") + 1));
        int dotCount = StringUtils.countMatches(propertyName, ".");
        if (dotCount == 1)
        {
          // Eg. applicant.crbExpiryDate
          method = applicantClass.getMethod(methodName, new Class[] {});
          sourceObject = applicant;
        }
        else
        {
          // Has 2 dots. Eg. applicant.user.fullName or applicant.address.postalCode 
          if (propertyName.substring(propertyName.indexOf(".") + 1, propertyName.lastIndexOf(".")).equalsIgnoreCase("user"))
          {
            // User method.
            method = userClass.getMethod(methodName, new Class[] {});
            sourceObject = applicant.getUser();
          }
          else
          {
            // Address method.
            method = addressClass.getMethod(methodName, new Class[] {});
            sourceObject = applicant.getAddress();
          }
        }
        if (method.getReturnType() == String.class)
        {
          // String type.
          String value = (String)method.invoke(sourceObject, new Object[0]);
          if (!compliantStringProperty(reason, propertyName, propertyValue, value))
          {
            applicant.setCompliant(false);
          }
        }
        else if (method.getReturnType() == Integer.class)
        {
          // Integer type.
          Integer value = (Integer)method.invoke(sourceObject, new Object[0]);
          if (!compliantIntegerProperty(reason, propertyName, propertyValue, value))
          {
            applicant.setCompliant(false);
          }
        }
        else if (method.getReturnType() == Boolean.class)
        {
          // Boolean type.
          Boolean value = (Boolean)method.invoke(sourceObject, new Object[0]);
          if (value != null)
          {
            // Value is applicable...
            if (!compliantBooleanProperty(reason, propertyName, propertyValue, value))
            {
              applicant.setCompliant(false);
            } 
          }
        }
        else if (method.getReturnType() == Date.class)
        {
          // Date type.
          Date value = (Date)method.invoke(sourceObject, new Object[0]);
          if (propertyValue.equalsIgnoreCase("in future"))
          {
            if (!dateInFuture(value))
            {
              applicant.setCompliant(false);
            }
          }
        }
      }
      catch (SecurityException e)
      {
        // TODO Auto-generated catch block
        reflectionProblem(methodName, sourceObject, e);
      }
      catch (NoSuchMethodException e)
      {
        // TODO Auto-generated catch block
        reflectionProblem(methodName, sourceObject, e);
      }
      catch (IllegalArgumentException e)
      {
        // TODO Auto-generated catch block
        reflectionProblem(methodName, sourceObject, e);
      }
      catch (IllegalAccessException e)
      {
        // TODO Auto-generated catch block
        reflectionProblem(methodName, sourceObject, e);
      }
      catch (InvocationTargetException e)
      {
        // TODO Auto-generated catch block
        reflectionProblem(methodName, sourceObject, e);
      }
    }
    // Check that Applicant has done mandatory training.
    applicant.hasRequiredTraining(reason);
    return applicant.getCompliant();
  }
  
  private void reflectionProblem(String methodName, Object sourceObject, Exception e)
  {
    System.out.println("Source Object: " + sourceObject.getClass().getName());
    System.out.println("Method Name: " + methodName);
    e.printStackTrace();
  }

  public void addCompliancyTestFailureReasonToNotes(StringBuffer reason, StringBuffer notes)
  {
    Integer startPos = notes.indexOf(startOfReasonMarker);
    Integer endPos   = notes.indexOf(endOfReasonMarker);
    if (startPos == -1)
    {
      // No Start of Reason Marker found.
      notes.append(startOfReasonMarker);
      notes.append("\n");
      notes.append(reason);
      notes.append(endOfReasonMarker);
    }
    else
    {
      // Start of Reason Marker found.
      if (endPos == -1)
      {
        // No End of Reason Marker found. Just replace the Start of Reason Marker.
        endPos = startPos + startOfReasonMarker.length();
      }
      else
      {
        endPos += endOfReasonMarker.length();
      }
      if (startPos <= endPos)
      {
        notes.replace(startPos, endPos, startOfReasonMarker + "\n" + reason + endOfReasonMarker);
      }
    }
  }
  
  public void removeCompliancyTestFailureReasonFromNotes(StringBuffer notes)
  {
    Integer startPos = notes.indexOf(startOfReasonMarker);
    Integer endPos   = notes.indexOf(endOfReasonMarker);
    if (startPos > -1)
    {
      // Start of Reason Marker found.
      if (endPos == -1)
      {
        // No End of Reason Marker found. Just replace the Start of Reason Marker.
        endPos = startPos + startOfReasonMarker.length();
      }
      else
      {
        endPos += endOfReasonMarker.length();
      }
      if (startPos <= endPos)
      {
        notes.replace(startPos, endPos, "");
      }
    }
  }
  
  private Boolean compliantStringProperty(StringBuffer reason, String propertyName, String propertyValue, String value)
  {
    if (propertyValue.equalsIgnoreCase("not empty") && StringUtils.isNotEmpty(value))
    {
      return true;
    }
    if (propertyValue.equalsIgnoreCase("empty") && StringUtils.isEmpty(value))
    {
      return true;
    }
    logReason(reason, propertyName, propertyValue);
    return false;
  }
  
  private Boolean compliantIntegerProperty(StringBuffer reason, String propertyName, String propertyValue, Integer value)
  {
    if (propertyValue.equalsIgnoreCase("not null") && value != null)
    {
      return true;
    }
    else if (propertyValue.equalsIgnoreCase("null") && value == null)
    {
      return true;
    }
    else if (propertyValue.toLowerCase().contains("equal"))
    {
      Integer testValue = Integer.valueOf(propertyValue.substring(propertyValue.lastIndexOf(" ") + 1));
      if (value.equals(testValue))
      {
        return true;
      }
    }
    else if (propertyValue.toLowerCase().contains("not equal"))
    {
      Integer testValue = Integer.valueOf(propertyValue.substring(propertyValue.lastIndexOf(" ") + 1));
      if (!value.equals(testValue))
      {
        return true;
      }
    }
    logReason(reason, propertyName, propertyValue);
    return false;
  }

  private Boolean compliantBooleanProperty(StringBuffer reason, String propertyName, String propertyValue, Boolean value)
  {
    if (value.equals(new Boolean(propertyValue)))
    {
      return true;
    }
    logReason(reason, propertyName, propertyValue);
    return false;
  }
  
  private void logReason(StringBuffer reason, String propertyName, String propertyValue)
  {
    if (reason != null)
    {
      reason.append(propertyName);
      reason.append(": ");
      reason.append(propertyValue);
      reason.append("\n");
    }        
  }
  
  /**
   * Checks that supplied date is NOT before today.
   * 
   * @param dateToCheck
   * @return
   */
  private boolean dateInFuture(Date dateToCheck)
  {
    java.util.Date today = new java.util.Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(today);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    today = calendar.getTime();
    return today.compareTo(dateToCheck) <= 0;
  }
}
