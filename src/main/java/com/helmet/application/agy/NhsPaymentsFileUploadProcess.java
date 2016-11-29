package com.helmet.application.agy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.bean.Agency;
import com.helmet.bean.Client;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.nhs.ClientPayment;
import com.helmet.bean.nhs.PaymentLine;
import com.helmet.bean.nhs.PaymentLineError;
import com.helmet.bean.nhs.Payments;

public class NhsPaymentsFileUploadProcess extends NhsFileUploadCommon
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());
  private static final String DATE_COLUMN = "Date";
  private static final String DOCUMENT_COLUMN = "Document";
  private static final String PAYMENT_COLUMN = "Payment";
  private static final String STATUS_COLUMN = "Status";
  private String nhsPaymentsAcceptStatus;

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    ActionForward actionForward = null;
    NhsBackingReport nhsBackingReport = null;
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
//    Client client = (Client)dynaForm.get("client");
    FormFile nhsPaymentsFile = (FormFile)dynaForm.get("nhsPaymentsFormFile");
    String nhsPaymentsFilename = nhsPaymentsFile.getFileName();
    Payments payments = new Payments(nhsPaymentsFilename);
//    client = agyService.getClientUser(client.getClientId());
    StringBuffer nhsBackingReportIds = new StringBuffer();
    if (StringUtils.isEmpty(nhsPaymentsFilename))
    {
      errors.add("nhsPaymentUpload", new ActionMessage("error.paymentsFile.notSupplied"));
    }
    else
    {
      // Now upload the NHS Payment file.
      String fileUrl = FileHandler.getInstance().getNhsPaymentsFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsPaymentsFilename;
      String filePath = FileHandler.getInstance().getNhsPaymentsFileLocation() + fileUrl;
      uploadFile(nhsPaymentsFile, filePath);
      HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
      nhsPaymentsAcceptStatus = FileHandler.getInstance().getNhsPaymentsAcceptStatus();
      String columnNumbersFilePath = FileHandler.getInstance().getNhsPaymentsFileLocation() + FileHandler.getInstance().getNhsPaymentsFileFolder();
      columnNumbers(columnMap, columnNumbersFilePath, errors, "nhsPaymentUpload", "error.paymentColumnNumbersFile");
      if (errors.isEmpty())
      {
        try
        {
          readPaymentsFile(payments, filePath, columnMap);
          validatePayments(agyService, messageResources, payments);
          if (!payments.getValid())
          {
            errors.add("nhsPaymentUpload", new ActionMessage("error.paymentsFile.errorCount", payments.getErrorCount()));
          }
        }
        catch (Exception e)
        {
          errors.add("nhsPaymentUpload", new ActionMessage("error.paymentsFile.exception", e.getMessage()));
        }
      }
    }
    logger.exit("Out going !!!");
    dynaForm.set("accept", payments.getFileName());
    dynaForm.set("nhsPaymentsFilename", nhsPaymentsFilename);
    dynaForm.set("payments", payments);
    if (errors.isEmpty())
    {
      if (payments.getCanAccept())
      {
        request.getSession().setAttribute("payments", payments);
      }
      return mapping.findForward("success");
    }
    else
    {
      saveErrors(request, errors);
      if (payments.getErrorCount() > 0)
      {
        if (payments.getCanAccept())
        {
          request.getSession().setAttribute("payments", payments);
        }
        else
        {
          request.getSession().removeAttribute("payments");
        }
        actionForward = mapping.findForward("attentionRequired");
      }
      else
      {
        actionForward = mapping.findForward("error");
      }
    }
    return actionForward;
  }
 
  private void readPaymentsFile(Payments payments, String filePath, HashMap<String, Integer> columnMap)
    throws Exception
  {
    DateFormat dateFormat = new SimpleDateFormat(getDateFormat());
    dateFormat.setLenient(false);
    List<PaymentLine> paymentLines = new ArrayList<PaymentLine>();
    BufferedReader br = null;
    String line = "";
    try
    {
      br = new BufferedReader(new FileReader(filePath));
      String[] columnData = null;
      PaymentLine paymentLine = null;
      int lineCount = 0;
      while ((line = br.readLine()) != null)
      {
        columnData = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        if (columnData.length == columnMap.size())
        {
          paymentLine = fillPaymentLineFromLine(payments, columnData, columnMap);
          paymentLines.add(paymentLine);
          lineCount++;
        }
        else
        {
          throw new Exception("Expected " + columnMap.size() + " columns but found " + columnData.length + " columns in line: " + line);
        }
      }
      payments.setPaymentLines(paymentLines);
    }
    finally
    {
      if (br != null)
      {
        br.close();
      }
    }
  }
  
  private PaymentLine fillPaymentLineFromLine(Payments payments, String[] columnData, HashMap<String, Integer> columnMap) 
    throws ParseException
  {
    PaymentLine paymentLine = new PaymentLine(payments);
    DateFormat dateFormat = new SimpleDateFormat(getDateFormat());
    dateFormat.setLenient(false);
    java.util.Date date = dateFormat.parse(columnData[columnMap.get(DATE_COLUMN)].replace("\"", ""));
    paymentLine.setDate(new Date(date.getTime()));
    paymentLine.setDocumentName(columnData[columnMap.get(DOCUMENT_COLUMN)].replace("\"", ""));
    DecimalFormat decimalFormat = getDecimalFormat();
    String payment = columnData[columnMap.get(PAYMENT_COLUMN)].replace("\"", "");
    paymentLine.setPayment((BigDecimal)decimalFormat.parse(payment));
    paymentLine.setStatus(columnData[columnMap.get(STATUS_COLUMN)].replace("\"", ""));
    return paymentLine;
  }

  private Boolean validatePayments(AgyService agyService, MessageResources messageResources, Payments payments)
  {
    Boolean fatalError = false;
    DecimalFormat decimalFormat = getDecimalFormat();
    String errorMessage = null;
    HashMap<Integer, Client> clientMap = new HashMap<Integer, Client>();
    List<ClientPayment> clientPaymentList = new ArrayList<ClientPayment>();
    NhsBackingReport nhsBackingReport = null;
    Integer letterIndex = null;
    String nhsBackingReportName = null;
    for (PaymentLine paymentLine : payments.getPaymentLines())
    {
      letterIndex = paymentLine.getDocumentName().indexOf("A");
      // Remove any suffix letter from the Backing Report Name.
      nhsBackingReportName = letterIndex == -1 ? paymentLine.getDocumentName() : paymentLine.getDocumentName().substring(0, letterIndex);
      nhsBackingReport = agyService.getNhsBackingReportForName(nhsBackingReportName, false);
      if (nhsBackingReport == null)
      {
        // NHS Backing Report NOT found. FATAL ERROR!!!
        errorMessage = messageResources.getMessage("error.nhsBackingReport.notFound", nhsBackingReportName);
        paymentLine.setClientName("");
        paymentLine.setDocumentNameInvalid(new PaymentLineError(errorMessage));
        fatalError = true;
      }
      else
      {
        String paidValue = decimalFormat.format(nhsBackingReport.getPaidValue());
        paymentLine.setNhsBackingReport(nhsBackingReport);
        if (nhsBackingReport.getActive() == false)
        {
          // NHS Backing Report is NOT Active. FATAL ERROR!!!
          errorMessage = messageResources.getMessage("error.nhsBackingReport.notActive", nhsBackingReport.getName());
          paymentLine.setDocumentNameInvalid(new PaymentLineError(nhsBackingReport.getName(), errorMessage));
          fatalError = true;
        }
        if (!nhsBackingReport.getAgencyId().equals(getConsultantLoggedIn().getAgencyId()))
        {
          // This Payment is for a different Agency! FATAL ERROR!!!
          Agency nhsBackingReportAgency = agyService.getAgencyUser(nhsBackingReport.getAgencyId());
          errorMessage = messageResources.getMessage("error.nhsBackingReport.notForThisAgency", nhsBackingReportAgency.getName());
          paymentLine.setAgencyInvalid(new PaymentLineError(nhsBackingReport.getName(), errorMessage));
          fatalError = true;
        }
        Client nhsBackingReportClient = agyService.getClientUser(nhsBackingReport.getClientId());
        paymentLine.setClientName(nhsBackingReportClient.getName());
        if (clientMap.containsKey(nhsBackingReportClient.getClientId()))
        {
          // Client in map, add payment to its ClientPayment object.
          for (ClientPayment clientPayment : clientPaymentList)
          {
            if (clientPayment.getClient().getClientId().equals(nhsBackingReportClient.getClientId()))
            {
              clientPayment.setPayment(clientPayment.getPayment().add(paymentLine.getPayment()));
              break;
            }
          }
        }
        else
        {
          // Client NOT in map, add it.
          ClientPayment clientPayment = new ClientPayment(nhsBackingReportClient, paymentLine.getPayment());
          clientMap.put(nhsBackingReportClient.getClientId(), nhsBackingReportClient);
          clientPaymentList.add(clientPayment);
        }
        if (nhsBackingReport.getAgreedValue() == null)
        {
          // The Agreed Value on the NHS Backing Report has NOT been entered.
          errorMessage = messageResources.getMessage("error.nhsBackingReport.agreedValueNotSpecified");
          paymentLine.setPaymentIncorrect(new PaymentLineError(nhsBackingReport.getName(), errorMessage));
        }
        else
        {
          // The Agreed Value on the NHS Backing Report has been entered.
          if (nhsBackingReport.getAgreedValue().compareTo(paymentLine.getPayment()) != 0)
          {
            // The Agreed Value on the NHS Backing Report is different from the Payment on the PaymentLine.
            String agreedValue = decimalFormat.format(nhsBackingReport.getAgreedValue());
            String payment     = decimalFormat.format(paymentLine.getPayment());
            errorMessage = messageResources.getMessage("error.paymentLine.paymentDiffersFromAgreedValue", payment, agreedValue);
            paymentLine.setPaymentIncorrect(new PaymentLineError(nhsBackingReport.getName(), errorMessage));
          }
        }
        if (nhsBackingReport.getTradeshiftDocumentId() == null)
        {
          // The NHS Backing Report has NOT been uploaded to Tradeshift.
          errorMessage = messageResources.getMessage("error.nhsBackingReport.notUploadedToTradeshift");
          paymentLine.setPaymentIncorrect(new PaymentLineError(nhsBackingReport.getName(), errorMessage));
        }
        if (nhsBackingReport.getPaidValue().compareTo(new BigDecimal(0)) != 0)
        {
          // The Paid Value has alredy been entered on the NHS Backing Report.
          errorMessage = messageResources.getMessage("error.nhsBackingReport.paidValueAlreadyEntered", paidValue);
          paymentLine.setPaymentIncorrect(new PaymentLineError(nhsBackingReport.getName(), errorMessage));
        }
        if (!paymentLine.getStatus().equalsIgnoreCase(nhsPaymentsAcceptStatus))
        {
          errorMessage = messageResources.getMessage("error.paymentLine.unexpectedPaymentStatus", paidValue, nhsPaymentsAcceptStatus, paymentLine.getStatus());
          paymentLine.setPaymentIncorrect(new PaymentLineError(nhsBackingReport.getName(), errorMessage));
        }
      }
    }
    payments.setClientPaymentList(clientPaymentList);
    payments.sortPaymentLines();
    return fatalError;
  }
 
}
