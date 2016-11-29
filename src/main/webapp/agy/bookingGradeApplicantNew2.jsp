<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ 
taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ 
taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="clientAgencyJobProfileGrades" name="BookingGradeApplicantFormAgy" property="clientAgencyJobProfileGrades" type="java.util.List" />
<mmj-agy:consultant var="consultant"/>

<%/* focusControl stuff */%>
<bean:define id="focusControl" value="submitButton"/>

<mmj-agy:hasAccess forward="canChangeGrade">
  <%
  if (clientAgencyJobProfileGrades.size() > 1) {
	%>
  <bean:define id="focusControl" value="clientAgencyJobProfileGradeId"/>
  <%
  }
  else {
  %>  
  <%/* grade can't be edited because there's only one so move on */%>
  <mmj-agy:hasAccess forward="canChangeRates">
    <bean:define id="focusControl" value="rate"/>
  </mmj-agy:hasAccess>
  <mmj-agy:hasNoAccess forward="canChangeRates">
    <%/* user can't change rates so move on */%>
    <mmj-agy:hasAccess forward="canChangeVatRates">
      <bean:define id="focusControl" value="chargeRateVatRate"/>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="canChangeVatRates">
      <%/* the default will be used */%>
		</mmj-agy:hasNoAccess>
  </mmj-agy:hasNoAccess>
  <%
  }
  %>
</mmj-agy:hasAccess>
<mmj-agy:hasNoAccess forward="canChangeGrade">
  <%/* user can't change grade so move on */%>
  <mmj-agy:hasAccess forward="canChangeRates">
    <bean:define id="focusControl" value="rate"/>
  </mmj-agy:hasAccess>
  <mmj-agy:hasNoAccess forward="canChangeRates">
    <%/* user can't change rates so move on */%>
    <mmj-agy:hasAccess forward="canChangeVatRates">
      <bean:define id="focusControl" value="chargeRateVatRate"/>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="canChangeVatRates">
      <%/* the default will be used */%>
		</mmj-agy:hasNoAccess>
  </mmj-agy:hasNoAccess>
</mmj-agy:hasNoAccess>

<html:form action="/bookingGradeApplicantNew3.do" focus="<%= focusControl %>" onsubmit="return singleSubmit();">
<html:hidden property="page" value="2"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.bookingGradeApplicantNewStep2"/>&nbsp;-&nbsp;<bean:message key="title.bookingGradeApplicantNew2"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="bookingGradeApplicantNewButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />

<script type="text/javascript">
<!--

clientAgencyJobProfileGrades = new Array(<%= clientAgencyJobProfileGrades.size() %>);

arrayLength          = 21;

elId                 = 0;
elRate               = 1;
elPayRate            = 2;
elWtdPercentage      = 3;
elWtdRate            = 4;
elNiPercentage       = 5;
elNiRate             = 6;
elCommission         = 7;
elPayeRate           = 8;
elWageRate           = 9; // not used
elChargeRateVatRate  = 10;
elPayRateVatRate     = 11;
elWtdVatRate         = 12;
elNiVatRate          = 13;
elCommissionVatRate  = 14;
elChargeRateVatValue = 15;
elPayRateVatValue    = 16;
elWtdVatValue        = 17;
elNiVatValue         = 18;
elCommissionVatValue = 19;
elGradeName          = 20;

function changeGrade(index) {
  
  setItsValue('rateStr', clientAgencyJobProfileGrades[index][elRate]);
  setItsValue('payRateStr', clientAgencyJobProfileGrades[index][elPayRate]);
  setItsValue('wtdPercentageStr', clientAgencyJobProfileGrades[index][elWtdPercentage]);
  setItsInnerHTML('wtdRateStr', clientAgencyJobProfileGrades[index][elWtdRate]);
  setItsValue('niPercentageStr', clientAgencyJobProfileGrades[index][elNiPercentage]);
  setItsInnerHTML('niRateStr', clientAgencyJobProfileGrades[index][elNiRate]);
  setItsInnerHTML('commissionRateStr', clientAgencyJobProfileGrades[index][elCommission]);

  setItsValue('wageRateStr', clientAgencyJobProfileGrades[index][elPayeRate]);
  setItsValue('chargeRateVatRateStr', clientAgencyJobProfileGrades[index][elChargeRateVatRate]);
  setItsValue('payRateVatRateStr', clientAgencyJobProfileGrades[index][elPayRateVatRate]);
  setItsValue('wtdVatRateStr', clientAgencyJobProfileGrades[index][elWtdVatRate]);
  setItsValue('niVatRateStr', clientAgencyJobProfileGrades[index][elNiVatRate]);
  setItsValue('commissionVatRateStr', clientAgencyJobProfileGrades[index][elCommissionVatRate]);

  setItsInnerHTML('chargeRateVatValueStr', clientAgencyJobProfileGrades[index][elChargeRateVatValue]);
  setItsInnerHTML('payRateVatValueStr', clientAgencyJobProfileGrades[index][elPayRateVatValue]);
  setItsInnerHTML('wtdVatValueStr', clientAgencyJobProfileGrades[index][elWtdVatValue]);
  setItsInnerHTML('niVatValueStr', clientAgencyJobProfileGrades[index][elNiVatValue]);
  setItsInnerHTML('commissionVatValueStr', clientAgencyJobProfileGrades[index][elCommissionVatValue]);

  setItsValue('gradeName', clientAgencyJobProfileGrades[index][elGradeName]);
}

//-->
</script>

<logic:iterate id="clientAgencyJobProfileGrade" name="clientAgencyJobProfileGrades" indexId="indexId" type="com.helmet.bean.ClientAgencyJobProfileGradeUser">
<script type="text/javascript">
<!--
clientAgencyJobProfileGrades[<%= indexId %>] = new Array(arrayLength);
clientAgencyJobProfileGrades[<%= indexId %>][elId] = '<bean:write name="clientAgencyJobProfileGrade" property="clientAgencyJobProfileGradeId"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elRate] = '<bean:write name="clientAgencyJobProfileGrade" property="rate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elPayRate] = '<bean:write name="clientAgencyJobProfileGrade" property="payRate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elWtdPercentage] = '<bean:write name="clientAgencyJobProfileGrade" property="wtdPercentage" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elWtdRate] = '<bean:write name="clientAgencyJobProfileGrade" property="wtdRate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elNiPercentage] = '<bean:write name="clientAgencyJobProfileGrade" property="niPercentage" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elNiRate] = '<bean:write name="clientAgencyJobProfileGrade" property="niRate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elCommission] = '<bean:write name="clientAgencyJobProfileGrade" property="commission" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elPayeRate] = '<bean:write name="clientAgencyJobProfileGrade" property="payeRate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elChargeRateVatRate] = '<bean:write name="clientAgencyJobProfileGrade" property="chargeRateVatRate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elPayRateVatRate] = '<bean:write name="clientAgencyJobProfileGrade" property="payRateVatRate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elWtdVatRate] = '<bean:write name="clientAgencyJobProfileGrade" property="wtdVatRate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elNiVatRate] = '<bean:write name="clientAgencyJobProfileGrade" property="niVatRate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elCommissionVatRate] = '<bean:write name="clientAgencyJobProfileGrade" property="commissionVatRate" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elChargeRateVatValue] = '<bean:write name="clientAgencyJobProfileGrade" property="chargeRateVatValue" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elPayRateVatValue] = '<bean:write name="clientAgencyJobProfileGrade" property="payRateVatValue" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elWtdVatValue] = '<bean:write name="clientAgencyJobProfileGrade" property="wtdVatValue" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elNiVatValue] = '<bean:write name="clientAgencyJobProfileGrade" property="niVatValue" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elCommissionVatValue] = '<bean:write name="clientAgencyJobProfileGrade" property="commissionVatValue" format="#,##0.00"/>';
clientAgencyJobProfileGrades[<%= indexId %>][elGradeName] = '<bean:write name="clientAgencyJobProfileGrade" property="gradeName"/>';
//-->
</script>
</logic:iterate>

<table class="simple">
  <logic:notEmpty name="BookingGradeApplicantFormAgy" property="masterVendorName">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
      <bean:message key="label.masterVendorName" />
    </th>
    <th align="right" colspan="2">
    	<bean:write name="BookingGradeApplicantFormAgy" property="masterVendorName"/>
		</th>
	</tr>
  </logic:notEmpty>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
      <bean:message key="label.totalHours" />
    </th>
    <td align="right">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.bookingNoOfHours" format="#0.00" />
    </td>
    <td align="left">
      &nbsp;
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
      <bean:message key="label.grade" />
    </th>
    <td align="right">
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.gradeName" />
      <html:hidden property="gradeName" styleId="gradeName"/>
    </td>
    <td align="left">
	  <%
	  if (clientAgencyJobProfileGrades.size() > 1) {
		%>
      <mmj-agy:hasAccess forward="canChangeGrade">
      <html:select property="clientAgencyJobProfileGradeId" onchange="javascript:changeGrade(this.selectedIndex)">
        <html:options collection="clientAgencyJobProfileGrades" labelProperty="gradeName" property="clientAgencyJobProfileGradeId"/>
      </html:select>
      </mmj-agy:hasAccess>
      <mmj-agy:hasNoAccess forward="canChangeGrade">
        <html:hidden property="clientAgencyJobProfileGradeId" />
      </mmj-agy:hasNoAccess>
	  <%
	  }
	  else {
	  %>  
      <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.gradeName" />
      <html:hidden property="clientAgencyJobProfileGradeId" />
    <%
    }
    %>
	  </td>
	</tr>

  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
	  <bean:message key="label.chargeRate" />
    </th>
    <td align="right">
	  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.rate" format="#,##0.00" />
    </td>
    <td align="left">
    <mmj-agy:hasAccess forward="canChangeRates">
      <html:text property="rateStr" size="10" styleId="rateStr" />
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="canChangeRates">
    <html:hidden property="rateStr" />
    </mmj-agy:hasNoAccess>
	  </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
	  <bean:message key="label.payRate" />
    </th>
    <td align="right">
	  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.payRate" format="#,##0.00" />
    </td>
    <td align="left">
    <mmj-agy:hasAccess forward="canChangeRates">
      <html:text property="payRateStr" size="10" styleId="payRateStr" />
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="canChangeRates">
    <html:hidden property="payRateStr" />
    </mmj-agy:hasNoAccess>
	  </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
      <bean:message key="label.wtdPercentage" />
    </th>
    <td align="right">
    <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.wtdPercentage" format="#0.00" />%
	  (<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.wtdRate" format="#,##0.00" />)
    </td>
    <td align="left">
    <mmj-agy:hasAccess forward="canChangeRates">
      <html:text property="wtdPercentageStr" size="10" styleId="wtdPercentageStr" />
  	  (<bean:message key="label.currencySymbol"/><label id="wtdRateStr"><bean:write name="BookingGradeApplicantFormAgy" property="wtdRateStr" format="#,##0.00" /></label>)
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="canChangeRates">
	  <html:hidden property="wtdPercentageStr" />
    </mmj-agy:hasNoAccess>
  	</td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
      <bean:message key="label.niPercentage" />
    </th>
    <td align="right">
	  <bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.niPercentage" format="#0.00" />%
	  (<bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.niRate" format="#,##0.00" />)
    </td>
    <td align="left">
    <mmj-agy:hasAccess forward="canChangeRates">
      <html:text property="niPercentageStr" size="10" styleId="niPercentageStr" />
  	  (<bean:message key="label.currencySymbol"/><label id="niRateStr"><bean:write name="BookingGradeApplicantFormAgy" property="niRateStr" format="#,##0.00" /></label>)
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="canChangeRates">
	  <html:hidden property="niPercentageStr" />
    </mmj-agy:hasNoAccess>
  	</td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
      <bean:message key="label.commission" />
    </th>
    <td align="right">
	  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.commissionRate" format="#,##0.00" />
    </td>
    <td align="left">
	  <bean:message key="label.currencySymbol"/><label id="commissionRateStr"><bean:write name="BookingGradeApplicantFormAgy" property="commissionRateStr" format="#,##0.00" /></label>
  	</td>
  </tr>

  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
	  <bean:message key="label.wageRate" />
    </th>
    <td align="right">
      <logic:equal name="consultant" property="canViewWages" value="true"><%-- Consultant CAN view wages. Show wage... --%>
	      <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="bookingGrade.wageRate" format="#,##0.00" />
	    </logic:equal>
      <logic:equal name="consultant" property="canViewWages" value="false"><%-- Consultant can NOT view wages. --%>
        &nbsp;
      </logic:equal>
    </td>
    <td align="left">
    <mmj-agy:hasAccess forward="canChangeRates">
      <html:text property="wageRateStr" size="10" styleId="wageRateStr" />
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="canChangeRates">
    <html:hidden property="wageRateStr" />
    </mmj-agy:hasNoAccess>
<%--
	  <bean:message key="label.currencySymbol"/><bean:write name="BookingGradeApplicantFormAgy" property="wageRate" format="#,##0.00" />
--%>
	  </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
      <bean:message key="label.chargeRateVatRate" />
    </th>
    <td align="right">
      <bean:write name="BookingGradeApplicantFormAgy" property="chargeRateVatRate" format="#0.00" />%
    </td>
    <td align="left">
      <mmj-agy:hasAccess forward="canChangeVatRates">
        <html:text property="chargeRateVatRateStr" size="10" styleId="chargeRateVatRateStr" />
	  	  (<bean:message key="label.currencySymbol"/><label id="chargeRateVatValueStr"><bean:write name="BookingGradeApplicantFormAgy" property="chargeRateVatValueStr" format="#,##0.00" /></label>)
      </mmj-agy:hasAccess>
      <mmj-agy:hasNoAccess forward="canChangeVatRates">
      <html:hidden property="chargeRateVatRateStr" />
      </mmj-agy:hasNoAccess>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
      <bean:message key="label.payRateVatRate" />
    </th>
    <td align="right">
      <bean:write name="BookingGradeApplicantFormAgy" property="payRateVatRate" format="#0.00" />%
    </td>
    <td align="left">
      <mmj-agy:hasAccess forward="canChangeVatRates">
        <html:text property="payRateVatRateStr" size="10" styleId="payRateVatRateStr" />
	  	  (<bean:message key="label.currencySymbol"/><label id="payRateVatValueStr"><bean:write name="BookingGradeApplicantFormAgy" property="payRateVatValueStr" format="#,##0.00" /></label>)
      </mmj-agy:hasAccess>
      <mmj-agy:hasNoAccess forward="canChangeVatRates">
        <html:hidden property="payRateVatRateStr" />
      </mmj-agy:hasNoAccess>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
	  <bean:message key="label.wtdVatRate" />
    </th>
    <td align="right">
		  <bean:write name="BookingGradeApplicantFormAgy" property="wtdVatRate" format="#0.00" />%
    </td>
    <td align="left">
      <mmj-agy:hasAccess forward="canChangeVatRates">
  			<html:text property="wtdVatRateStr" size="10" styleId="wtdVatRateStr" />
	  	  (<bean:message key="label.currencySymbol"/><label id="wtdVatValueStr"><bean:write name="BookingGradeApplicantFormAgy" property="wtdVatValueStr" format="#,##0.00" /></label>)
	    </mmj-agy:hasAccess>
	    <mmj-agy:hasNoAccess forward="canChangeVatRates">
	  		<html:hidden property="wtdVatRateStr" />
      </mmj-agy:hasNoAccess>
    </td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
	  <bean:message key="label.niVatRate" />
    </th>
    <td align="right">
		  <bean:write name="BookingGradeApplicantFormAgy" property="niVatRate" format="#0.00" />%
    </td>
    <td align="left">
	    <mmj-agy:hasAccess forward="canChangeVatRates">
	  		<html:text property="niVatRateStr" size="10" styleId="niVatRateStr" />
	  	  (<bean:message key="label.currencySymbol"/><label id="niVatValueStr"><bean:write name="BookingGradeApplicantFormAgy" property="niVatValueStr" format="#,##0.00" /></label>)
	    </mmj-agy:hasAccess>
	    <mmj-agy:hasNoAccess forward="canChangeVatRates">
	  		<html:hidden property="niVatRateStr" />
	    </mmj-agy:hasNoAccess>
		</td>
  </tr>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th align="left">
      <bean:message key="label.commissionVatRate" />
    </th>
    <td align="right">
      <bean:write name="BookingGradeApplicantFormAgy" property="commissionVatRate" format="#0.00" />%
    </td>
    <td align="left">
      <mmj-agy:hasAccess forward="canChangeVatRates">
        <html:text property="commissionVatRateStr" size="10" styleId="commissionVatRateStr" />
    	  (<bean:message key="label.currencySymbol"/><label id="commissionVatValueStr"><bean:write name="BookingGradeApplicantFormAgy" property="commissionVatValueStr" format="#,##0.00" /></label>)
      </mmj-agy:hasAccess>
      <mmj-agy:hasNoAccess forward="canChangeVatRates">
        <html:hidden property="commissionVatRateStr" />
      </mmj-agy:hasNoAccess>
	  </td>
	</tr>
</table>
</html:form>

<%--
<br/>
<logic:iterate id="clientAgencyJobProfileGrade" name="clientAgencyJobProfileGrades" type="com.helmet.bean.ClientAgencyJobProfileGradeUser">

<bean:write name="clientAgencyJobProfileGrade" property="gradeName"/>

<bean:write name="clientAgencyJobProfileGrade" property="clientAgencyJobProfileGradeId"/>
<bean:write name="clientAgencyJobProfileGrade" property="rate"/>
<bean:write name="clientAgencyJobProfileGrade" property="payRate"/>
<bean:write name="clientAgencyJobProfileGrade" property="wtdPercentage"/>
<bean:write name="clientAgencyJobProfileGrade" property="wtdRate"/>
<bean:write name="clientAgencyJobProfileGrade" property="niPercentage"/>
<bean:write name="clientAgencyJobProfileGrade" property="niRate"/>
<bean:write name="clientAgencyJobProfileGrade" property="commission"/>
<bean:write name="clientAgencyJobProfileGrade" property="payeRate"/>
<bean:write name="clientAgencyJobProfileGrade" property="chargeRateVatRate"/>
<bean:write name="clientAgencyJobProfileGrade" property="payRateVatRate"/>
<bean:write name="clientAgencyJobProfileGrade" property="wtdVatRate"/>
<bean:write name="clientAgencyJobProfileGrade" property="niVatRate"/>
<bean:write name="clientAgencyJobProfileGrade" property="commissionVatRate"/>

<bean:write name="clientAgencyJobProfileGrade" property="chargeRateVatValue"/>
<bean:write name="clientAgencyJobProfileGrade" property="payRateVatValue"/>
<bean:write name="clientAgencyJobProfileGrade" property="wtdVatValue"/>
<bean:write name="clientAgencyJobProfileGrade" property="niVatValue"/>
<bean:write name="clientAgencyJobProfileGrade" property="commissionVatValue"/>

<br/>
</logic:iterate>
--%>
    