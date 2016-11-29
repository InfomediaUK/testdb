<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:parameter id="theFormAgy" name="theFormAgy" value="ListFormAgy"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="showTotals" name="showTotals" value="true"/>
<bean:parameter id="showButtons" name="showButtons" value="false"/>

<bean:define id="totalColumns" value="13"/>
<bean:define id="totalColumnsLess2" value="<%= new Integer(Integer.parseInt(totalColumns) - 2).toString() %>"/>
<bean:define id="totalColumnsLess3" value="<%= new Integer(Integer.parseInt(totalColumns) - 3).toString() %>"/>
<mmj-agy:consultant var="consultant"/>

<%-- shifts --%>
<logic:notPresent name="<%= theFormAgy %>" property="<%= theList %>">
  <br/>
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="<%= theFormAgy %>" property="<%= theList %>">
	<logic:empty name="<%= theFormAgy %>" property="<%= theList %>">
	  <br/>
	  <bean:message key="text.noDetails"/>
	</logic:empty>
	<logic:notEmpty name="<%= theFormAgy %>" property="<%= theList %>">
		<table class="simple" width="100%">

    <logic:iterate id="bookingBookingDate" name="<%= theFormAgy %>" property="<%= theList %>" type="com.helmet.application.agy.BookingBookingDateUserApplicant" indexId="bookingBookingDateIndex">
			<bean:define id="firstBookingDate" name="bookingBookingDate" property="firstBookingDate" type="com.helmet.bean.BookingDateUserApplicant" />
      <thead>
      <tr><th align="left" colspan="<bean:write name="totalColumns"/>" bgcolor="#000000" height="3"></th></tr>
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <th colspan="<%= totalColumnsLess2 %>" align="left" >
          <bean:write name="firstBookingDate" property="clientName"/>,&nbsp;<bean:write name="firstBookingDate" property="siteName"/>,&nbsp;<bean:write name="firstBookingDate" property="locationName"/>
<logic:notEmpty name="firstBookingDate" property="bookingReference" >
          &nbsp;&nbsp;(<bean:message key="label.reference" />&nbsp;<bean:write name="firstBookingDate" property="bookingReference"/>)          
</logic:notEmpty>
          <br/>
          <mmj-agy:hasAccess forward="bookingGradeViewSummary">
          <html:link forward="bookingGradeViewSummary" paramId="bookingGrade.bookingGradeId" paramName="firstBookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewSummary">
            <bean:write name="firstBookingDate" property="bookingId" format="#000"/>
          </html:link>
          </mmj-agy:hasAccess>    
          <mmj-agy:hasNoAccess forward="bookingGradeViewSummary">
            <bean:write name="firstBookingDate" property="bookingId" format="#000"/>
          </mmj-agy:hasNoAccess>  
          - 
          <mmj-agy:hasAccess forward="bookingGradeViewApplicants">
          <html:link forward="bookingGradeViewApplicants" paramId="bookingGrade.bookingGradeId" paramName="firstBookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewApplicants">         
          <bean:write name="firstBookingDate" property="jobProfileName"/>
          </html:link>
          </mmj-agy:hasAccess>
          <mmj-agy:hasNoAccess forward="bookingGradeViewApplicants">
          <bean:write name="firstBookingDate" property="jobProfileName"/>
          </mmj-agy:hasNoAccess>
          - 
          <mmj-agy:hasAccess forward="bookingGradeViewShifts">
          <html:link forward="bookingGradeViewShifts" paramId="bookingGrade.bookingGradeId" paramName="firstBookingDate" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewShifts">         
          <bean:write name="firstBookingDate" property="gradeName"/>
          </html:link>
          </mmj-agy:hasAccess>
          <mmj-agy:hasNoAccess forward="bookingGradeViewShifts">
          <bean:write name="firstBookingDate" property="gradeName"/>
          </mmj-agy:hasNoAccess>
          -
          <mmj-agy:hasAccess forward="bookingGradeApplicantView">
          <html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="firstBookingDate" paramProperty="bookingGradeApplicantId" titleKey="title.bookingGradeViewApplicant">
            <bean:message key="label.currencySymbol"/><bean:write name="firstBookingDate" property="chargeRate" format="#0.00"/>
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
              -&nbsp;<bean:message key="label.currencySymbol"/><bean:write name="firstBookingDate" property="wageRate" format="#0.00"/>
  </logic:equal>
  <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Check if Applicant subcontracted... --%>
    <logic:equal name="firstBookingDate" property="applicantOriginalAgencyId" value="0"><%-- Applicant NOT subcontracted. Show wage... --%>
              -&nbsp;<bean:message key="label.currencySymbol"/><bean:write name="firstBookingDate" property="wageRate" format="#0.00"/>
    </logic:equal>
  </logic:equal>
          </html:link>
    
          <logic:notEmpty name="<%= theFormAgy %>" property="applicant.user.emailAddress">
            <mmj-agy:hasAccess forward="sendConfirmationEmail">
              &nbsp;
              <html:link forward="sendConfirmationEmail" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="firstBookingDate" paramProperty="bookingGradeApplicantId" titleKey="title.sendConfirmationEmail">
                <bean:message key="link.sendConfirmationEmail"/>
              </html:link>
            </mmj-agy:hasAccess>    
          </logic:notEmpty>
    
          <logic:notEmpty name="<%= theFormAgy %>" property="applicant.mobileNumber">
            &nbsp;
            <mmj-agy:hasAccess forward="sendConfirmationSms">
              <html:link forward="sendConfirmationSms" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="firstBookingDate" paramProperty="bookingGradeApplicantId" titleKey="title.sendConfirmationSms">
                <bean:message key="link.sendConfirmationSms"/>
              </html:link>
            </mmj-agy:hasAccess>    
          </logic:notEmpty>    
    
          </mmj-agy:hasAccess>
          <mmj-agy:hasNoAccess forward="bookingGradeApplicantView">
            <bean:message key="label.currencySymbol"/><bean:write name="firstBookingDate" property="chargeRate" format="#0.00"/>
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
            -&nbsp;<bean:message key="label.currencySymbol"/><bean:write name="firstBookingDate" property="wageRate" format="#0.00"/>
  </logic:equal>
  <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Check if Applicant subcontracted... --%>
    <logic:equal name="firstBookingDate" property="applicantOriginalAgencyId" value="0"><%-- Applicant NOT subcontracted. Show wage... --%>
              -&nbsp;<bean:message key="label.currencySymbol"/><bean:write name="firstBookingDate" property="wageRate" format="#0.00"/>
    </logic:equal>
  </logic:equal>
          </mmj-agy:hasNoAccess>    
        </th>
        <th colspan="2" align="right">
		<logic:equal name="showButtons" value="true">
		  <%
		  String authorizeAction = request.getContextPath() + "/agy/applicantBookingDatesAuthorize.do";
		  String invoiceAction   = request.getContextPath() + "/agy/applicantBookingDatesInvoice.do";
		  %>
      <logic:equal name="bookingBookingDate" property="canMultiAuthorize" value="true">
          <form name="<%= bookingBookingDateIndex %>" action="<%= authorizeAction %>" onsubmit="return singleSubmit();">
            <html:hidden name="bookingBookingDate" property="applicantId" />
            <html:hidden name="bookingBookingDate" property="bookingId" />
            <html:hidden name="bookingBookingDate" property="selectedBookingDates" />
            <html:hidden name="bookingBookingDate" property="weekToShow" />
            <html:submit styleClass="titleButton"><bean:message key="button.authorize"/></html:submit>
          </form>
      </logic:equal>
      <logic:equal name="bookingBookingDate" property="canMultiInvoice" value="true">
          <form name="<%= bookingBookingDateIndex %>" action="<%= invoiceAction %>" onsubmit="return singleSubmit();">
            <html:hidden name="bookingBookingDate" property="applicantId" />
            <html:hidden name="bookingBookingDate" property="bookingId" />
            <html:hidden name="bookingBookingDate" property="selectedBookingDates" />
            <html:hidden name="bookingBookingDate" property="weekToShow" />
            <html:submit styleClass="titleButton"><bean:message key="button.invoice"/></html:submit>
          </form>
      </logic:equal>
    </logic:equal>
        </th>
      </tr>  
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <td colspan="3">
          &nbsp;
        </td>
        <th colspan="3" align="center">
          <bean:message key="label.agreed"/>
        </th>
        <th colspan="4" align="center">
          <bean:message key="label.actual"/>
        </th>
        <td colspan="3">
          &nbsp;
        </td>
      </tr> 
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <th align="left">
          <bean:message key="label.noDot"/>
        </th>
        <th align="left">
          <bean:message key="label.date"/>
        </th>
        <th align="left">
          <bean:message key="label.shift"/>
        </th>
        <th align="center">
          <bean:message key="label.times"/>
          (<bean:message key="label.hrs"/>)
        </th>
        <th align="center">
          <bean:message key="label.charge"/>
        </th>
        <th align="center">
          <bean:message key="label.wage"/>
        </th>
        <th align="center">
          <bean:message key="label.times"/>
          (<bean:message key="label.hrs"/>)
        </th>
        <th align="center">
          <bean:message key="label.charge"/>
        </th>
        <th align="center">
          <bean:message key="label.wage"/>
        </th>
        <th align="left">
          <bean:message key="label.expenses"/>
        </th>
        <th align="left">
          <bean:message key="label.vat"/>
        </th>
        <th align="left">
          <bean:message key="label.total"/>
        </th>
        <th align="left">
          <bean:message key="label.status"/>
        </th>
      </tr>
      </thead>
      <logic:iterate id="bookingDate" name="bookingBookingDate" property="list" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <td align="left">
          <bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
        </td>
        <td align="left" nowrap="nowrap">
          <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormatyy"/>&nbsp;
        </td>
        <td align="left">
          <bean:write name="bookingDate" property="shiftName"/>
        </td>
        <td align="right">
         <bean:write name="bookingDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="shiftEndTime" format="HH:mm"/>
    <%--
         (<bean:write name="bookingDate" property="shiftBreakNoOfHours" format="#0.00"/>)
    --%>
         (<bean:write name="bookingDate" property="shiftNoOfHours" format="#0.00"/>)
        </td>
        <td align="right">
          <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="chargeRateValue" format="#0.00"/>
        </td>
        <td align="right">
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
          <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="wageRateValue" format="#0.00"/>
  </logic:equal>
  <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Check if Applicant subcontracted... --%>
    <logic:equal name="bookingDate" property="applicantOriginalAgencyId" value="0"><%-- Applicant NOT subcontracted. Show wage... --%>
          <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="wageRateValue" format="#0.00"/>
    </logic:equal>        
    <logic:greaterThan name="bookingDate" property="applicantOriginalAgencyId" value="0"><%-- Applicant IS subcontracted. Don't show wage. --%>
          &nbsp;
    </logic:greaterThan>        
  </logic:equal>
        </td>
        <logic:equal name="bookingDate" property="hasBeenEntered" value="true">
        <%/* timesheet has been submitted */%>
        <td align="right">
          <logic:equal name="bookingDate" property="hasUplift" value="true">
          *
          </logic:equal>
         <bean:write name="bookingDate" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="workedEndTime" format="HH:mm"/>
    <%--
         (<bean:write name="bookingDate" property="workedBreakNoOfHours" format="#0.00"/>)
    --%>
          (<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>)
        </td>
        <td align="right">
          <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedChargeRateValue" format="#0.00"/>
        </td>
        <td align="right">
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
          <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedWageRateValue" format="#0.00"/>
  </logic:equal>
  <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Check if Applicant subcontracted... --%>
    <logic:equal name="bookingDate" property="applicantOriginalAgencyId" value="0"><%-- Applicant NOT subcontracted. Show wage... --%>
          <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedWageRateValue" format="#0.00"/>
    </logic:equal>
    <logic:greaterThan name="bookingDate" property="applicantOriginalAgencyId" value="0"><%-- Applicant IS subcontracted. Don't show wage. --%>
          &nbsp;
    </logic:greaterThan>        
  </logic:equal>
        </td>
        </logic:equal> 
        <logic:notEqual name="bookingDate" property="hasBeenEntered" value="true">
        <td align="left">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td align="left">&nbsp;</td>
        </logic:notEqual>
        <td align="right">
          <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="expenseValue" format="#0.00"/>
        </td>
        <td align="right">
          <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="totalVatValue" format="#0.00"/>
        </td>
        <td align="right">
          <bean:message key="label.currencySymbol" /><bean:write name="bookingDate" property="workedTotalValue" format="#0.00"/>
        </td>
        <td align="left">
          <logic:equal name="bookingDate" property="isCancelled" value="true">
          <bean:message name="bookingDate" property="statusDescriptionKey"/>
        </logic:equal>
          <logic:notEqual name="bookingDate" property="isCancelled" value="true">
          <logic:equal name="bookingDate" property="canBeActivated" value="true">
              <bean:message key="text.notActivated"/>
        </logic:equal>
          <logic:notEqual name="bookingDate" property="canBeActivated" value="true">
            <logic:equal name="bookingDate" property="hasBeenEntered" value="true">
            <bean:message name="bookingDate" property="workedStatusDescriptionKey"/>
            <logic:equal name="bookingDate" property="workedStatusIsInvoiced" value="true">
                <logic:greaterThan name="bookingDate" property="agencyInvoiceId" value="0">
            <mmj-agy:hasAccess forward="agencyInvoiceView">
              <html:link forward="agencyInvoiceView" paramId="agencyInvoice.agencyInvoiceId" paramName="bookingDate" paramProperty="agencyInvoiceId"><bean:write name="bookingDate" property="agencyInvoiceId"/></html:link>
            </mmj-agy:hasAccess>
            <mmj-agy:hasNoAccess forward="agencyInvoiceView">
                <bean:write name="bookingDate" property="agencyInvoiceId"/>
            </mmj-agy:hasNoAccess>
              </logic:greaterThan>
              </logic:equal>
          </logic:equal>
            <logic:notEqual name="bookingDate" property="hasBeenEntered" value="true">
                <bean:message key="text.activated"/>
          </logic:notEqual>
          </logic:notEqual>
          </logic:notEqual>
        </td>
      </tr>
    
    <logic:notEmpty name="bookingDate" property="comment">
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <th align="left" class="label" colspan="3">
          <bean:message key="label.comment"/>
        </th>
        <td align="left" colspan="<bean:write name="totalColumnsLess3"/>">
          <bean:write name="bookingDate" property="comment"/>
        </td>
      </tr>
    </logic:notEmpty> 
    <logic:equal name="bookingDate" property="workedStatusIsRejected" value="true">
      <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
        <th align="left" class="label" colspan="3">
          <bean:message key="label.rejectText"/>
        </th>
        <td align="left" colspan="<bean:write name="totalColumnsLess3"/>">
          <bean:write name="bookingDate" property="rejectText"/>
        </td>
      </tr>
    </logic:equal>
    
      <tr><th align="left" colspan="<bean:write name="totalColumns"/>" bgcolor="#000000" height="3"></th></tr>
      </logic:iterate>
    </logic:iterate>

		
		<logic:equal name="showTotals" value="true">
		  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
		    <th align="left" colspan="3"><bean:message key="label.total"/></th>
				<td align="right"><bean:write name="<%= theFormAgy %>" property="totalHours" format="#0.00"/></td>
				<td align="right">
				  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalAgreedValue" format="#,##0.00"/>
				</td>
				<td align="right">
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
				  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalAgreedWageRateValue" format="#,##0.00"/>
	</logic:equal>
  <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Check if Applicant subcontracted... --%>
    <logic:equal name="<%= theFormAgy %>" property="applicant.originalAgencyId" value="0"><%-- Applicant NOT subcontracted. Show wage... --%>
          <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalAgreedWageRateValue" format="#,##0.00"/>
    </logic:equal>
    <logic:greaterThan name="<%= theFormAgy %>" property="applicant.originalAgencyId" value="0"><%-- Applicant IS subcontracted. Don't show wage. --%>
          &nbsp;
    </logic:greaterThan>        
  </logic:equal>
				</td>
				<td align="right"><bean:write name="<%= theFormAgy %>" property="totalActualHours" format="#0.00"/></td>
				<td align="right">
				  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalActualValue" format="#,##0.00"/>
				</td>
				<td align="right">
  <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
			    <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalActualWageRateValue" format="#,##0.00"/>
	</logic:equal>
  <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. Check if Applicant subcontracted... --%>
    <logic:equal name="<%= theFormAgy %>" property="applicant.originalAgencyId" value="0"><%-- Applicant NOT subcontracted. Show wage... --%>
          <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalActualWageRateValue" format="#,##0.00"/>
    </logic:equal>
    <logic:greaterThan name="<%= theFormAgy %>" property="applicant.originalAgencyId" value="0"><%-- Applicant IS subcontracted. Don't show wage. --%>
          &nbsp;
    </logic:greaterThan>        
  </logic:equal>
				</td>
				<td align="right">
				  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalExpenseValue" format="#,##0.00"/>
				</td>
			  <td align="right">
			    <bean:message key="label.currencySymbol" /><bean:write name="<%= theFormAgy %>" property="totalTotalVatValue" format="#,##0.00"/>
			  </td>
			  <td align="right">
			    <bean:message key="label.currencySymbol" /><bean:write name="<%= theFormAgy %>" property="totalActualTotalValue" format="#,##0.00"/>
			  </td>
				<th align="right">&nbsp;</th>
		  </tr>
		</logic:equal>
		
		</table>
	
	</logic:notEmpty>
</logic:present>
