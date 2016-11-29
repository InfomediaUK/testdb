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
import com.helmet.bean.SubcontractInvoice;
import com.helmet.bean.nhs.ClientPayment;
import com.helmet.bean.nhs.PaymentLine;
import com.helmet.bean.nhs.PaymentLineError;
import com.helmet.bean.nhs.Payments;

public class SubcontractInvoicePaymentsFileUploadProcess extends NhsFileUploadCommon
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());
  private static final String DATE_COLUMN = "Date";
  //////// WARNING ///////////////////////////////////////////////////////////////
  // The Document column actually holds the Subcontract Invoice Number.
  //
  private static final String DOCUMENT_COLUMN = "Document";
  private static final String PAYMENT_COLUMN = "Payment";
  private static final String STATUS_COLUMN = "Status";
  private String subcontractInvoicePaymentsAcceptStatus;

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    ActionForward actionForward = null;
    SubcontractInvoice subcontractInvoice = null;
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    FormFile subcontractInvoicePaymentsFile = (FormFile)dynaForm.get("subcontractInvoicePaymentsFormFile");
    String subcontractInvoicePaymentsFilename = subcontractInvoicePaymentsFile.getFileName();
    Payments payments = new Payments(subcontractInvoicePaymentsFilename);
    StringBuffer subcontractInvoiceIds = new StringBuffer();
    if (StringUtils.isEmpty(subcontractInvoicePaymentsFilename))
    {
      errors.add("subcontractInvoicePaymentUpload", new ActionMessage("error.paymentsFile.notSupplied"));
    }
    else
    {
      // Now upload the SubcontractInvoice Payment file.
      String fileUrl = FileHandler.getInstance().getNhsPaymentsFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + subcontractInvoicePaymentsFilename;
      String filePath = FileHandler.getInstance().getNhsPaymentsFileLocation() + fileUrl;
      uploadFile(subcontractInvoicePaymentsFile, filePath);
      HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
      subcontractInvoicePaymentsAcceptStatus = FileHandler.getInstance().getNhsPaymentsAcceptStatus();
      String columnNumbersFilePath = FileHandler.getInstance().getNhsPaymentsFileLocation() + FileHandler.getInstance().getNhsPaymentsFileFolder();
      columnNumbers(columnMap, columnNumbersFilePath, errors, "subcontractInvoicePaymentUpload", "error.paymentColumnNumbersFile");
      if (errors.isEmpty())
      {
        try
        {
          readPaymentsFile(payments, filePath, columnMap);
          validatePayments(agyService, messageResources, payments);
          if (!payments.getValid())
          {
            errors.add("subcontractInvoicePaymentUpload", new ActionMessage("error.paymentsFile.errorCount", payments.getErrorCount()));
          }
        }
        catch (Exception e)
        {
          errors.add("subcontractInvoicePaymentUpload", new ActionMessage("error.paymentsFile.exception", e.getMessage()));
        }
      }
    }
    logger.exit("Out going !!!");
    dynaForm.set("accept", payments.getFileName());
    dynaForm.set("subcontractInvoicePaymentsFilename", subcontractInvoicePaymentsFilename);
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
    SubcontractInvoice subcontractInvoice = null;
    Integer subcontractInvoiceId = null;
    for (PaymentLine paymentLine : payments.getPaymentLines())
    {
      subcontractInvoiceId = Integer.parseInt(paymentLine.getDocumentName().substring(1));
      subcontractInvoice = agyService.getSubcontractInvoice(subcontractInvoiceId);
      if (subcontractInvoice == null)
      {
        // Subcontract Invoice NOT found. FATAL ERROR!!!
        errorMessage = messageResources.getMessage("error.subcontractInvoice.notFound", paymentLine.getDocumentName());
        paymentLine.setClientName("");
        paymentLine.setDocumentNameInvalid(new PaymentLineError(errorMessage));
        fatalError = true;
      }
      else
      {
        String paidValue = decimalFormat.format(subcontractInvoice.getValue());
        paymentLine.setSubcontractInvoice(subcontractInvoice);
        if (subcontractInvoice.getActive() == false)
        {
          // Subcontract Invoice is NOT Active. FATAL ERROR!!!
          errorMessage = messageResources.getMessage("error.subcontractInvoice.notActive", subcontractInvoice.getSubcontractInvoiceNumber());
          paymentLine.setDocumentNameInvalid(new PaymentLineError(subcontractInvoice.getSubcontractInvoiceNumber(), errorMessage));
          fatalError = true;
        }
        if (!subcontractInvoice.getToAgencyId().equals(getConsultantLoggedIn().getAgencyId()))
        {
          // This Payment is for a different Agency! FATAL ERROR!!!
          Agency subcontractInvoiceAgency = agyService.getAgencyUser(subcontractInvoice.getToAgencyId());
          errorMessage = messageResources.getMessage("error.subcontractInvoice.notForThisAgency", subcontractInvoiceAgency.getName());
          paymentLine.setAgencyInvalid(new PaymentLineError(subcontractInvoice.getSubcontractInvoiceNumber(), errorMessage));
          fatalError = true;
        }
        Client subcontractInvoiceClient = agyService.getClientUser(subcontractInvoice.getClientId());
        paymentLine.setClientName(subcontractInvoiceClient.getName());
        if (clientMap.containsKey(subcontractInvoiceClient.getClientId()))
        {
          // Client in map, add payment to its ClientPayment object.
          for (ClientPayment clientPayment : clientPaymentList)
          {
            if (clientPayment.getClient().getClientId().equals(subcontractInvoiceClient.getClientId()))
            {
              clientPayment.setPayment(clientPayment.getPayment().add(paymentLine.getPayment()));
              break;
            }
          }
        }
        else
        {
          // Client NOT in map, add it.
          ClientPayment clientPayment = new ClientPayment(subcontractInvoiceClient, paymentLine.getPayment());
          clientMap.put(subcontractInvoiceClient.getClientId(), subcontractInvoiceClient);
          clientPaymentList.add(clientPayment);
        }
        if (subcontractInvoice.getValue() == null)
        {
          // The Value on the Subcontract Invoice has NOT been entered.
          errorMessage = messageResources.getMessage("error.subcontractInvoice.valueNotSpecified");
          paymentLine.setDocumentNameInvalid(new PaymentLineError(subcontractInvoice.getSubcontractInvoiceNumber(), errorMessage));
        }
        else
        {
          // The Value on the Subcontract Invoice has been entered.
          if (subcontractInvoice.getValue().compareTo(paymentLine.getPayment()) != 0)
          {
            // The Value on the Subcontract Invoice is different from the Payment on the PaymentLine.
            String agreedValue = decimalFormat.format(subcontractInvoice.getValue());
            String payment     = decimalFormat.format(paymentLine.getPayment());
            errorMessage = messageResources.getMessage("error.paymentLine.paymentDiffersFromAgreedValue", payment, agreedValue);
            paymentLine.setDocumentNameInvalid(new PaymentLineError(subcontractInvoice.getSubcontractInvoiceNumber(), errorMessage));
          }
        }
        if (subcontractInvoice.getPaidDate() != null)
        {
          // The Paid Value has already been entered on the Subcontract Invoice.
          SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
          formatDate.setLenient(false);
          errorMessage = messageResources.getMessage("error.subcontractInvoice.valueAlreadyPaid", formatDate.format(subcontractInvoice.getPaidDate()));
          paymentLine.setDocumentNameInvalid(new PaymentLineError(subcontractInvoice.getSubcontractInvoiceNumber(), errorMessage));
        }
        if (!paymentLine.getStatus().equalsIgnoreCase(subcontractInvoicePaymentsAcceptStatus))
        {
          errorMessage = messageResources.getMessage("error.paymentLine.unexpectedPaymentStatus", paidValue, subcontractInvoicePaymentsAcceptStatus, paymentLine.getStatus());
          paymentLine.setDocumentNameInvalid(new PaymentLineError(subcontractInvoice.getSubcontractInvoiceNumber(), errorMessage));
        }
      }
    }
    payments.setClientPaymentList(clientPaymentList);
    payments.sortPaymentLines();
    return fatalError;
  }
 
}
