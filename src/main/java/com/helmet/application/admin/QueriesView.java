package com.helmet.application.admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.admin.abztract.AdminAction;

public class QueriesView extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    String filePath = FileHandler.getInstance().getXmlRealPath("/queries.xml");
    File file = new File(filePath);
    String queriesXml = null;
    if (file.exists())
    {
      try
      {
        queriesXml = Utilities.openFile(filePath);
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        queriesXml = e.getMessage();
      }
    }
    else
    {
      StringBuffer sb = new StringBuffer();
      sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      sb.append("<queries>\n");
      sb.append("<query>\n");
      sb.append("    <sql>\n");
      sb.append("      select\n");
      sb.append("          ai.agencyinvoiceid,\n");
      sb.append("          ai.creationtimestamp,\n");
      sb.append("          ai.chargeratevalue,\n");
      sb.append("          ai.payratevalue + ai.wtdvalue + ai.nivalue as payeratevalue,\n");
      sb.append("          ai.wageratevalue,\n");
      sb.append("          ai.commissionvalue,\n");
      sb.append("          ai.expensevalue,\n");
      sb.append("          ai.vatvalue,\n");
      sb.append("          ai.chargeratevalue + ai.expensevalue + ai.vatvalue as totalvalue,\n");
      sb.append("          c.name as client,\n");
      sb.append("          a.name as agency,\n");
      sb.append("          ap.firstname||' '||ap.lastname as applicant,\n");
      sb.append("          ap.limitedcompanyname,\n");
      sb.append("          sum(bd.workedchargeratevalue) as workedchargeratevalue,\n");
      sb.append("          sum(bd.workedpayratevalue + bd.workedwtdvalue + bd.workednivalue) as workedpayeratevalue,\n");
      sb.append("          sum(bd.workedwageratevalue) as workedwageratevalue,\n");
      sb.append("          sum(bd.workedcommissionvalue) as workedcommissionvalue,\n");
      sb.append("          sum(bd.expensevalue) as workedexpensevalue,\n");
      sb.append("          sum(bd.workedvatvalue + bd.expensevatvalue) as workedvatvalue,\n");
      sb.append("          sum(bd.workedchargeratevalue + bd.expensevalue + bd.workedvatvalue + bd.expensevatvalue) as workedtotalvalue,\n");
      sb.append("          sum(bd.workednoofhours) as workednoofhours,\n");
      sb.append("          count(bd.bookingdateid) as workednoofshifts,\n");
      sb.append("          min(bd.bookingdate) as minbookingdate,\n");
      sb.append("          max(bd.bookingdate) as maxbookingdate\n");
      sb.append("      from\n");
      sb.append("          agencyinvoice ai,\n");
      sb.append("          client c,\n");
      sb.append("          agency a,\n");
      sb.append("          bookingdate bd,\n");
      sb.append("          bookinggradeapplicantdate bgad,\n");
      sb.append("          bookinggradeapplicant bga,\n");
      sb.append("          applicant ap\n");
      sb.append("      where ai.active = true\n");
      sb.append("      and c.clientid = ai.clientid\n");
      sb.append("      and c.active = true\n");
      sb.append("      and a.agencyid = ai.agencyid\n");
      sb.append("      and a.active = true\n");
      sb.append("      and bd.agencyinvoiceid = ai.agencyinvoiceid\n");
      sb.append("      and bd.active = true\n");
      sb.append("      and bgad.bookingdateid = bd.bookingdateid\n");
      sb.append("      and bgad.active = true\n");
      sb.append("      and bgad.status = 3 -- filled\n");
      sb.append("      and bga.bookinggradeapplicantid = bgad.bookinggradeapplicantid\n");
      sb.append("      and bga.active = true\n");
      sb.append("      and ap.applicantid = bga.applicantid\n");
      sb.append("      and ap.active = true\n");
      sb.append("      and ai.agencyinvoiceid > INVOICEID\n");
      sb.append("      group by 1,2,3,4,5,6,7,8,9,10,11,12,13\n");
      sb.append("      order by 1\n");
      sb.append("    </sql>\n");
      sb.append("    <params>\n");
      sb.append("      <param name=\"INVOICEID\" title=\"Greater than invoiceid\" type=\"string\"/>\n");
      sb.append("    </params>\n");
      sb.append("  </query>\n");
      sb.append("</queries>\n");
      queriesXml = sb.toString();
    }
    dynaForm.set("queriesXml", queriesXml);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
