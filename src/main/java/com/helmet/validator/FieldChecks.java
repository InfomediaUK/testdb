package com.helmet.validator;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

public class FieldChecks extends org.apache.struts.validator.FieldChecks implements Serializable
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public static boolean validateEqual(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request)
  {

    boolean equal = areEqual(bean, field, validator);

    if (!equal)
    {
      errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
      return false;
    }

    return true;
  }

  public static boolean validateNotEqual(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request)
  {

    boolean equal = areEqual(bean, field, validator);

    if (equal)
    {
      errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
      return false;
    }

    return true;

  }

  private static boolean areEqual(Object bean, Field field, org.apache.commons.validator.Validator validator)
  {

    String value = null;
    if (isString(bean))
    {
      value = (String)bean;
    }
    else
    {
      value = ValidatorUtils.getValueAsString(bean, field.getProperty());
    }

    boolean ignoreCase = true;

    if (!GenericValidator.isBlankOrNull(field.getVarValue("ignoreCase")))
    {
      ignoreCase = "true".equals(field.getVarValue("ignoreCase"));
    }

    String dependField = field.getVarValue("field");

    String dependFieldValue = ValidatorUtils.getValueAsString(bean, dependField);

    boolean equal = false;

    if (ignoreCase)
    {
      equal = value.equalsIgnoreCase(dependFieldValue);
    }
    else
    {
      equal = value.equals(dependFieldValue);
    }

    return equal;
  }

}