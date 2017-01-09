<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.agencyInvoiceView"/>
		</td>
  </tr>
</table>

<logic:notEmpty name="AgencyInvoiceFormMgr" property="agencyInvoice">

<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.invoiceNo"/></th>
    <td align="left" width="75%">
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.agencyInvoiceId"/>

<bean:define id="agencyInvoice" name="AgencyInvoiceFormMgr" property="agencyInvoice" type="com.helmet.bean.AgencyInvoice"/>
<%
  String fileName = "ai" + agencyInvoice.getAgencyInvoiceId();
  String tempFilePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getTempFileFolder() + "/" + fileName + ".pdf";
  String bothFileName = "ai" + agencyInvoice.getAgencyInvoiceId() + "ts";
  String bothTempFilePath = request.getContextPath() + com.helmet.application.FileHandler.getInstance().getTempFileFolder() + "/" + bothFileName + ".pdf";
%>
<html:link href="<%= tempFilePath %>" target="pdf">
  <bean:message key="link.view"/>
</html:link>

<logic:notEmpty name="AgencyInvoiceFormMgr" property="agencyInvoice.timesheetFilename">
	<html:link href="<%= bothTempFilePath %>" target="both">
	  <bean:message key="link.viewBoth"/>
	</html:link>
</logic:notEmpty>

    </td>
    
    <td rowspan="2" align="right">
	  <html:link href="http://www.adobe.com/products/acrobat/readstep2.html" target="_blank">
        <img src="<%= request.getContextPath() %>/images/getAdobeReader.gif" width="112" height="33" align="middle"/>
      </html:link>
    </td>
    
  </tr>
  <logic:notEmpty name="AgencyInvoiceFormMgr" property="agencyInvoice.timesheetFilename">
  <tr>
    <th align="left"><bean:message key="label.timesheet"/></th>
    <td align="left" colspan="2">
		  <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.timesheetFilename"/>
      <bean:define id="timesheetUrl" name="AgencyInvoiceFormMgr" property="agencyInvoice.timesheetUrl" type="java.lang.String" />
      <html:link href="<%= request.getContextPath() + timesheetUrl %>" target="timesheet">
        <bean:message key="link.view"/>
      </html:link>
      <logic:notEqual name="AgencyInvoiceFormMgr" property="agencyInvoice.timesheetExists" value="true">
        <bean:message key="label.missing"/>
      </logic:notEqual>
    </td>
  </tr>
  </logic:notEmpty>
  <tr>
    <th align="left"><bean:message key="label.agency"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.agencyName" />
      (<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.agencyCode" />)
    </td>
  </tr>
  
  <tr>
    <th align="left"><bean:message key="label.reference"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.reference"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.created"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.creationTimestamp" formatKey="format.longDateTimeFormat"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoicePayRateValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.payRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceWtdValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.wtdValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceNiValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.niValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceWageRateValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.wageRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceCommissionValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.commissionValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceChargeRateValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.chargeRateValue" format="#,##0.00"/>
    </td>
  </tr>
  <logic:greaterThan name="AgencyInvoiceFormMgr" property="agencyInvoice.expenseValue" value="0">
  <tr>
    <th align="left"><bean:message key="label.invoiceExpenseValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.expenseValue" format="#,##0.00"/>
    </td>
  </tr>
  </logic:greaterThan>
  <logic:greaterThan name="AgencyInvoiceFormMgr" property="agencyInvoice.discountValue" value="0">
  <tr>
    <th align="left"><bean:message key="label.invoiceDiscountValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/>-<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.discountValue" format="#,##0.00"/>
      (<bean:message key="label.currencySymbol"/>-<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.discountVatValue" format="#,##0.00"/>)
			<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.discountText"/>
    </td>
  </tr>
  </logic:greaterThan>
  <tr>
    <th align="left"><bean:message key="label.invoiceVatValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.vatValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.invoiceTotalValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.totalValue" format="#,##0.00"/>
    </td>
  </tr>

  <tr>
    <th align="left"><bean:message key="label.noOfHours"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.noOfHours" format="#,##0.00"/>
    </td>
  </tr>
<%--
  <tr>
    <th align="left"><bean:message key="label.feeValue"/></th>
    <td align="left" colspan="2">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.feeValue" format="#,##0.00"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.paymentTerms"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.paymentTerms" format="#,##0.00"/>
    </td>
  </tr>
--%>
  <tr>
    <th align="left"><bean:message key="label.status"/></th>
    <td align="left" colspan="2">
      <bean:message name="AgencyInvoiceFormMgr" property="agencyInvoice.statusDescriptionKey" />
<%--
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.submittedByConsultantId" />
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.submittedTimestamp" />
--%>
  </tr>
<logic:notEmpty name="AgencyInvoiceFormMgr" property="agencyInvoice.submittedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.submittedBy"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.submittedByFirstName"/>
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.submittedByLastName"/>
	    (<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.submittedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="AgencyInvoiceFormMgr" property="agencyInvoice.authorizedTimestamp">
  <tr>
    <th align="left"><bean:message key="label.authorizedBy"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.authorizedByFirstName"/>
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.authorizedByLastName"/>
	    (<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.authorizedTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
<logic:notEmpty name="AgencyInvoiceFormMgr" property="agencyInvoice.paidTimestamp">
  <tr>
    <th align="left"><bean:message key="label.paidBy"/></th>
    <td align="left" colspan="2">
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.paidByFirstName"/>
      <bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.paidByLastName"/>
	    (<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.paidTimestamp" formatKey="format.longDateTimeFormat"/>)
    </td>
  </tr>
</logic:notEmpty>
</table>
<br/>
<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.shiftNo"/></th>
    <th align="left"><bean:message key="label.date"/></th>
    <th align="left"><bean:message key="label.jobProfile"/></th>
    <th align="left"><bean:message key="label.location"/></th>
    <th align="left"><bean:message key="label.shift"/></th>
    <th align="left"><bean:message key="label.invoicePayRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceWtdValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceNiValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceWageRateValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceCommissionValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceChargeRateValueShort"/></th>
  <logic:greaterThan name="AgencyInvoiceFormMgr" property="agencyInvoice.expenseValue" value="0">
    <th align="left"><bean:message key="label.invoiceExpenseValueShort"/></th>
	</logic:greaterThan>
    <th align="left"><bean:message key="label.invoiceVatValueShort"/></th>
    <th align="left"><bean:message key="label.invoiceTotalValueShort"/></th>
  </tr>
<logic:iterate id="bookingDate" name="AgencyInvoiceFormMgr" property="agencyInvoice.bookingDateUserApplicants">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td>
 			<mmj-mgr:hasAccess forward="bookingDateView">
      <html:link forward="bookingDateView" paramId="bookingDate.bookingDateId" paramName="bookingDate" paramProperty="bookingDateId">    			
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
      </html:link>
      </mmj-mgr:hasAccess>
 			<mmj-mgr:hasNoAccess forward="bookingDateView">
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
      </mmj-mgr:hasNoAccess>
    </td>
    <td>
			<bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </td>
    <td>
 			<bean:write name="bookingDate" property="jobProfileName"/>
 			(<bean:write name="bookingDate" property="agencyCode"/>)
    </td>
    <td>
      <bean:write name="bookingDate" property="locationName"/>,
      <bean:write name="bookingDate" property="siteName"/>
    </td>
    <td>
			<bean:write name="bookingDate" property="shiftName"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedPayRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedWtdValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedNiValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedWageRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedCommissionValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedChargeRateValue" format="#,##0.00"/>
    </td>
  <logic:greaterThan name="AgencyInvoiceFormMgr" property="agencyInvoice.expenseValue" value="0">
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="expenseValue" format="#,##0.00"/>
    </td>
	</logic:greaterThan>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="totalVatValue" format="#,##0.00"/>
    </td>
    <td align="right" title="<bean:write name="bookingDate" property="feePerShift" format="#0.00"/> <bean:write name="bookingDate" property="feePerHour" format="#0.00"/> <bean:write name="bookingDate" property="feePercentage" format="#0.00"/> <bean:write name="bookingDate" property="feeValue" format="#0.00"/>">
      <bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedTotalValue" format="#,##0.00"/>
    </td>
  </tr>
</logic:iterate>

<bean:define id="colspanAll" value="13"/>	
<bean:define id="colspanBlankBit" value="6"/>	
<logic:greaterThan name="AgencyInvoiceFormMgr" property="agencyInvoice.expenseValue" value="0">
  <bean:define id="colspanAll" value="14"/>	
  <bean:define id="colspanBlankBit" value="7"/>	
</logic:greaterThan>

<tr><th align="left" colspan="<%= colspanAll %>" bgcolor="#000000" height="3"></th></tr>
<logic:greaterThan name="AgencyInvoiceFormMgr" property="agencyInvoice.discountValue" value="0">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th colspan="2" align="left">
      <bean:message key="label.discount"/>
    </th>
    <td colspan="2" align="left">
			<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.discountText"/>
		</td>
    <td align="right">
      <bean:message key="label.currencySymbol"/>-<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.discountValue" format="#,##0.00"/>
		</td>
    <th colspan="<%= colspanBlankBit %>" align="left">
			&nbsp;
		</th>
    <td align="right">
			<bean:message key="label.currencySymbol"/>-<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.discountVatValue" format="#,##0.00"/>
		</td>
    <td align="right">
      <bean:message key="label.currencySymbol"/>-<bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.discountTotalValue" format="#,##0.00"/>
		</td>
	</tr>
<tr><th align="left" colspan="<%= colspanAll %>" bgcolor="#000000" height="3"></th></tr>
</logic:greaterThan>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th colspan="5" align="left">
      <bean:message key="label.total"/>
    </th>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.payRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.wtdValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.niValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.wageRateValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.commissionValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.chargeRateValue" format="#,##0.00"/>
    </td>
  <logic:greaterThan name="AgencyInvoiceFormMgr" property="agencyInvoice.expenseValue" value="0">
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.expenseValue" format="#,##0.00"/>
    </td>
	</logic:greaterThan>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.vatValue" format="#,##0.00"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="AgencyInvoiceFormMgr" property="agencyInvoice.totalValue" format="#,##0.00"/>
    </td>
  </tr>    
</table>
<br/>
</logic:notEmpty>





      