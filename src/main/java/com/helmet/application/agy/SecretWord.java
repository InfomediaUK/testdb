package com.helmet.application.agy;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.agy.abztract.AgyAction;

public class SecretWord extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    Integer secretWordKey1 = (Integer)dynaForm.get("secretWordKey1");
    Integer secretWordKey2 = (Integer)dynaForm.get("secretWordKey2");
    Integer secretWordKey3 = (Integer)dynaForm.get("secretWordKey3");

    if (secretWordKey1 == 0)
    {
      // first time through

      int secretWordLength = getConsultantLoggedIn().getUser().getSecretWord().length();

      int key1 = 0;
      int key2 = 0;
      int key3 = 0;

      Random generator = new Random(new Date().getTime());

      while (key1 == 0 || key2 == 0 || key3 == 0)
      {
        if (key1 == 0)
        {
          key1 = generator.nextInt(secretWordLength) + 1;
          if (key1 == key2 || key1 == key3)
          {
            key1 = 0;
          }
        }
        if (key2 == 0)
        {
          key2 = generator.nextInt(secretWordLength - 1) + 1;
          if (key2 == key1 || key2 == key3)
          {
            key2 = 0;
          }
        }
        if (key3 == 0)
        {
          key3 = generator.nextInt(secretWordLength - 1) + 1;
          if (key3 == key1 || key3 == key2)
          {
            key3 = 0;
          }
        }
      }

      secretWordKey1 = Math.min(Math.min(key1, key2), key3);
      secretWordKey3 = Math.max(Math.max(key1, key2), key3);
      secretWordKey2 = secretWordKey1 != key1 && secretWordKey3 != key1 ? key1 : secretWordKey1 != key2 && secretWordKey3 != key2 ? key2 : key3;

      dynaForm.set("secretWordKey1", secretWordKey1);
      dynaForm.set("secretWordKey2", secretWordKey2);
      dynaForm.set("secretWordKey3", secretWordKey3);

    }

    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
