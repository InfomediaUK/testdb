<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%-- 
<logic:present name="ShiftSearchFormAgy" property="list">
  <logic:notEmpty name="ShiftSearchFormAgy" property="list">
		<jsp:include page="shiftsInclude.jsp" flush="true">
		  <jsp:param name="theFormAgy" value="ShiftSearchFormAgy"/>
		</jsp:include>
  </logic:notEmpty>
</logic:present>

<br/>
<br/>
--%>


<logic:iterate id="bookingDate" name="ShiftSearchFormAgy" property="list" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex" length="1">
<br/>
<bean:write name="bookingDate" property="agencyName"/>
<br/>
<bean:write name="bookingDate" property="locationName"/>,&nbsp;<bean:write name="bookingDate" property="siteName"/>
<br/>
<bean:write name="bookingDate" property="jobProfileName"/>
-
<bean:write name="bookingDate" property="gradeName"/>
<br/>
<bean:write name="bookingDate" property="applicantFirstName"/>&nbsp;<bean:write name="bookingDate" property="applicantLastName"/>
<br/>
<br/>
</logic:iterate>

<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.shiftNo"/></th>
    <th align="left"><bean:message key="label.date"/></th>
    <th align="left"><bean:message key="label.times"/></th>
    <th align="left"><bean:message key="label.quantity"/></th>
    <th align="left"><bean:message key="label.details"/></th>
    <th align="left"><bean:message key="label.unitPrice"/></th>
    <th align="left"><bean:message key="label.net"/></th>
    <th align="left"><bean:message key="label.vatRate"/></th>
    <th align="left"><bean:message key="label.vat"/></th>
  </tr>
<logic:iterate id="bookingDate" name="ShiftSearchFormAgy" property="list" type="com.helmet.bean.BookingDateUserApplicant" indexId="bookingDateIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
 			<bean:write name="bookingDate" property="bookingId" format="#000"/>.<bean:write name="bookingDate" property="bookingDateId" format="#000"/>
    </th>
    <th align="left">
      <bean:write name="bookingDate" property="bookingDate" formatKey="format.longDateFormat"/>
    </th>
    <td align="left">
      <bean:write name="bookingDate" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingDate" property="workedEndTime" format="HH:mm"/>
      <logic:greaterThan name="bookingDate" property="workedBreakNoOfHours" value="0">
      (<bean:write name="bookingDate" property="workedBreakNoOfHours" format="#0.00"/>)
      </logic:greaterThan>
      <logic:equal name="bookingDate" property="hasUplift" value="true">
      *
      </logic:equal>
    </td>
    <td align="right">
<bean:write name="bookingDate" property="workedNoOfHours" format="#0.00"/>
    </td>
  <logic:equal name="bookingDate" property="chargeRateVatRate" value="0">
    <td align="left">
<bean:message key="label.commission"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="commissionRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedCommissionValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="commissionVatRate" value="0">
<bean:write name="bookingDate" property="commissionVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedCommissionVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedCommissionVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" colspan="3">
      &nbsp;
    </td>
    <td align="right">
      &nbsp;
    </td>
    <td align="left">
<bean:message key="label.payRate"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="payRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedPayRateValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="payRateVatRate" value="0">
<bean:write name="bookingDate" property="payRateVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedPayRateVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedPayRateVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" colspan="3">
      &nbsp;
    </td>
    <td align="right">
      &nbsp;
    </td>
    <td align="left">
<bean:message key="label.wtd"/>&nbsp;@&nbsp;<bean:write name="bookingDate" property="wtdPercentage" format="#,##0.00" />%
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="wtdRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedWtdValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="wtdVatRate" value="0">
<bean:write name="bookingDate" property="wtdVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedWtdVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedWtdVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" colspan="3">
      &nbsp;
    </td>
    <td align="right">
      &nbsp;
    </td>
    <td align="left">
<bean:message key="label.ni"/>&nbsp;@&nbsp;<bean:write name="bookingDate" property="niPercentage" format="#,##0.00" />%
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="niRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedNiValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="niVatRate" value="0">
<bean:write name="bookingDate" property="niVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedNiVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedNiVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </logic:equal>  
  <logic:notEqual name="bookingDate" property="chargeRateVatRate" value="0">
    <td align="left">
<bean:message key="label.chargeRate"/>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="chargeRate" format="#,##0.00" />
    </td>
    <td align="right">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedChargeRateValue" format="#,##0.00" />
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="chargeRateVatRate" value="0">
<bean:write name="bookingDate" property="chargeRateVatRate" format="#,##0.00" />
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDate" property="workedChargeRateVatValue" value="0">
<bean:message key="label.currencySymbol"/><bean:write name="bookingDate" property="workedChargeRateVatValue" format="#,##0.00" />
      </logic:greaterThan>
    </td>
  </logic:notEqual>
  </tr>
  <logic:iterate id="bookingDateExpense" name="bookingDate" property="bookingDateExpenses" type="com.helmet.bean.BookingDateExpenseUser">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="right" colspan="3">
      &nbsp;
    </td>
    <td align="right">
<logic:equal name="bookingDateExpense" property="isMultiplier" value="true">
  <bean:write name="bookingDateExpense" property="qty" format="#0.00"/>
</logic:equal>
<logic:notEqual name="bookingDateExpense" property="isMultiplier" value="true">
  1.00
</logic:notEqual>
    </td>
    <td align="left">
 <bean:write name="bookingDateExpense" property="expenseName"/>
 <logic:notEmpty name="bookingDateExpense" property="text">
   -
   <bean:write name="bookingDateExpense" property="text"/>
 </logic:notEmpty>
    </td>
    <td align="right">
<logic:equal name="bookingDateExpense" property="isMultiplier" value="true">
  <bean:message key="label.currencySymbol"/><bean:write name="bookingDateExpense" property="expenseMultiplier" format="#0.00"/>
</logic:equal>
<logic:notEqual name="bookingDateExpense" property="isMultiplier" value="true">
  <bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="qty" format="#0.00"/>
</logic:notEqual>
    </td>
    <td align="right">
<bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="value" format="#0.00"/>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDateExpense" property="expenseVatRate" value="0">
<bean:write name="bookingDateExpense" property="expenseVatRate" format="#0.00"/>
      </logic:greaterThan>
    </td>
    <td align="right">
      <logic:greaterThan name="bookingDateExpense" property="vatValue" value="0">
<bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="vatValue" format="#0.00"/>
      </logic:greaterThan>
    </td>
  </tr>
  </logic:iterate>

</logic:iterate>
<tr><td align="left" colspan="9">&nbsp;</td></tr>
<tr>
  <th align="right" colspan="6"><bean:message key="label.subTotal" /></th>
  <td align="right" >
    <bean:message key="label.currencySymbol"/><bean:write name="ShiftSearchFormAgy" property="totalNetValue" format="#,##0.00" />
  </td>
  <th align="right" >&nbsp;</th>
  <td align="right" >
    <bean:message key="label.currencySymbol"/><bean:write name="ShiftSearchFormAgy" property="totalVatValue" format="#,##0.00" />
  </td>
</tr>
<tr><td align="left" colspan="9">&nbsp;</td></tr>
<%--
<tr>
  <td align="left" colspan="5" rowspan="3">&nbsp;</td>
  <th align="left" colspan="3"><bean:message key="label.totalNetAmount" /></th>
  <td align="right" >
    <bean:message key="label.currencySymbol"/><bean:write name="ShiftSearchFormAgy" property="totalNetValue" format="#,##0.00" />
  </td>
</tr>
<tr>
  <th align="left" colspan="3"><bean:message key="label.totalVatAmount" /></th>
  <td align="right" >
    <bean:message key="label.currencySymbol"/><bean:write name="ShiftSearchFormAgy" property="totalVatValue" format="#,##0.00" />
  </td>
</tr>
--%>
<tr>
  <th align="right" colspan="6"><bean:message key="label.totalUppercase" /></th>
  <th align="right" colspan="2">&nbsp;</th>
  <td align="right" >
    <bean:message key="label.currencySymbol"/><bean:write name="ShiftSearchFormAgy" property="totalValue" format="#,##0.00" />
  </td>
</tr>

</table>
<%--
<bean:write name="ShiftSearchFormAgy" property="totalExpenseValue" />
--%>
