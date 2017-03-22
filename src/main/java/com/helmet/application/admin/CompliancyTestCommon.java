package com.helmet.application.admin;

import java.lang.reflect.Method;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.lang3.StringUtils;

import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.CompliancyTest;


public abstract class CompliancyTestCommon extends AdminAction
{

  public Boolean propertyIsValid(CompliancyTest compliancyTest, ActionMessages errors)
  {
    Applicant applicant  = new Applicant();
    Class applicantClass = applicant.getClass();
    String methodName    = null;
    String property = compliancyTest.getProperty();
    methodName = "get" + StringUtils.capitalize(property.substring(property.lastIndexOf(".") + 1));
    try
    {
      Method method = applicantClass.getMethod(methodName, new Class[] {});
    }
    catch (Exception e)
    {
      errors.add("compliancyTest", new ActionMessage("errors.compliancyTest.property", e.getMessage()));
      return false;
    }
    return true;
  }

}
