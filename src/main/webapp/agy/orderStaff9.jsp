<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<bean:define id="bookingDates" name="OrderStaffFormAgy" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
<%
com.helmet.bean.BookingDate minDate = bookingDates[0];
com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
pageContext.setAttribute("minDate", minDate);
pageContext.setAttribute("maxDate", maxDate);
%>
<html:form action="/orderStaff10.do" focus="comments" onsubmit="return singleSubmit();">
	<html:hidden property="page" value="9"/>
	<table>
	  <tr>
	    <td align="left" valign="middle" width="250">
	      <logic:empty name="OrderStaffFormAgy" property="booking.bookingId">
	      <bean:message key="title.newBooking"/>
	      </logic:empty>
	      <logic:notEmpty name="OrderStaffFormAgy" property="booking.bookingId">
	      <bean:message key="title.editBooking"/>
	      </logic:notEmpty>
	      -&nbsp;<bean:message key="title.orderStaff9"/>
	    </td>
	    <td align="left" valign="top">
	      <jsp:include page="orderStaffButtons.jsp" flush="true" />
	    </td>
	  </tr>
	</table>
	<hr/>
	<html:errors />
	<table border="0" width="100%">
	  <tr>
	    <td width="50%">
			<table class="simple" width="100%">
			  <tr>
			    <th align="left" valign="top" class="label" nowrap="true">
			      <bean:message key="label.comments" />
			    </th>
			    <td align="left" width="100%">
			      &nbsp;
			    </td>
			  </tr>
			  <tr>
			    <td align="left" width="100%" colspan="2">
			      <html:textarea tabindex="1" property="comments" cols="52" rows="10"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.duration" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="duration" maxlength="100" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.reference" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="bookingReference" maxlength="20" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <td align="left">
			      &nbsp;
			    </td>
			    <th align="left" class="label">
			      <bean:message key="label.contact" />
			    </th>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.contactName" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="contactName" size="30" maxlength="50" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.contactJobTitle" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="contactJobTitle" size="30" maxlength="50" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.contactEmailAddress" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="contactEmailAddress" size="30" maxlength="320" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.contactTelephoneNumber" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="contactTelephoneNumber" size="30" maxlength="20" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <td align="left">
			      &nbsp;
			    </td>
			    <th align="left" class="label">
			      <bean:message key="label.accountContact" />
			    </th>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.accountContactName" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="accountContactName" size="30" maxlength="50" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.accountContactAddress" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="accountContactAddress.address1" size="30" maxlength="200" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      &nbsp;
			    </th>
			    <td align="left" width="100%">
			      <html:text property="accountContactAddress.address2" size="30" maxlength="200" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      &nbsp;
			    </th>
			    <td align="left" width="100%">
			      <html:text property="accountContactAddress.address3" size="30" maxlength="200" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      &nbsp;
			    </th>
			    <td align="left" width="100%">
			      <html:text property="accountContactAddress.address4" size="30" maxlength="200" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.accountContactPostalCode" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="accountContactAddress.postalCode" maxlength="20" tabindex="1"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.accountContactCountry" />
			    </th>
			    <td align="left" width="100%">
			      <mmj:countryList var="countryList" />
			      <html:select property="accountContactAddress.countryId" tabindex="1">
			        <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
			        <html:options collection="countryList" labelProperty="name" property="countryId" />
			      </html:select>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.accountContactEmailAddress" />
			    </th>
			    <td align="left" width="100%">
			      <html:text property="accountContactEmailAddress" size="30" tabindex="1"/>
			    </td>
			  </tr>
			</table>
    </td>
    <td width="50%" valign="top">
      <table class="simple">
        <tr>
          <th class="label">
		        <bean:message key="label.client"/>
          </td>
          <td>
            <bean:write name="OrderStaffFormAgy" property="client.name"/>&nbsp;(<bean:write name="OrderStaffFormAgy" property="client.code"/>)
          </td>
        </tr>
        <tr>
          <th class="label">
            <bean:message key="label.reason"/>
          </td>
          <td>
            <bean:write name="OrderStaffFormAgy" property="reasonForRequest.name"/>&nbsp;-
            <bean:write name="OrderStaffFormAgy" property="reasonForRequestText"/>
          </td>
        </tr>
        <tr>
          <th class="label">
            <bean:message key="label.location"/>
          </td>
          <td>
				    <bean:write name="OrderStaffFormAgy" property="location.name"/>,
				    <bean:write name="OrderStaffFormAgy" property="location.siteName"/>
			      <logic:notEmpty name="OrderStaffFormAgy" property="location.description">
			  	    (<bean:write name="OrderStaffFormAgy" property="location.description"/>)
			      </logic:notEmpty>
          </td>
        </tr>
        <tr>
          <th class="label" valign="top">
            <bean:message key="label.jobProfile"/>
          </td>
          <td>
            <bean:write name="OrderStaffFormAgy" property="jobProfile.name"/>
	          (<bean:write name="OrderStaffFormAgy" property="jobProfile.jobFamilyCode"/>.<bean:write name="OrderStaffFormAgy" property="jobProfile.jobSubFamilyCode"/>.<bean:write name="OrderStaffFormAgy" property="jobProfile.code"/>)
          </td>
        </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.start"/>
			    </th>
			    <td align="left">
			      <bean:write name="minDate" property="bookingDate" formatKey="format.longDateFormat"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.end"/>
			    </th>
			    <td align="left">
			      <bean:write name="maxDate" property="bookingDate" formatKey="format.longDateFormat"/>
			    </td>
			  </tr>
        <tr>
          <th align="left" class="label">
            <bean:message key="label.days"/>
          </td>
          <td>
            <bean:write name="OrderStaffFormAgy" property="noOfDates"/>
          </td>
        </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.totalHours" />
			    </th>
			    <td align="left">
			      <bean:write name="OrderStaffFormAgy" property="totalHours" format="#0.00"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
						<bean:message key="label.rrpRate"/>
			    </th>
			    <td align="left">
						<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="hourlyRate" format="#,##0.00" />
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
						<bean:message key="label.rrpValue"/>
			    </th>
			    <td align="left">
						<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="rrp" format="#,##0.00" />
			    </td>
			  </tr>
<%/* START OF SPECIFICS */%>

<bean:define id="firstSpecific" value="true"/>

<logic:equal name="OrderStaffFormAgy" property="cvRequired" value="true">
<logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.cvRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.cvRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="OrderStaffFormAgy" property="interviewRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.interviewRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.interviewRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="OrderStaffFormAgy" property="canProvideAccommodation" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.canProvideAccommodation" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.canProvideAccommodation" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="OrderStaffFormAgy" property="carRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.carRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.carRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal>
<logic:equal name="firstSpecific" value="false"></td></tr></logic:equal>

<%/* END OF SPECIFICS */%>
<%/* START OF EXPENSES */%>

<logic:notEmpty name="OrderStaffFormAgy" property="expenseArray">
  <tr>
    <th align="left" class="label">
    	<bean:message key="label.expenses" />
    </th>
    <td>
			<logic:iterate id="expense" name="OrderStaffFormAgy" property="expenseArray" indexId="expenseIndex"><logic:greaterThan name="expenseIndex" value="0">,&nbsp;</logic:greaterThan><bean:write name="expense" property="name"/></logic:iterate>
			<logic:notEmpty name="OrderStaffFormAgy" property="expensesText">
			  (<bean:write name="OrderStaffFormAgy" property="expensesText"/>)
			</logic:notEmpty>
    </td>
  </tr>
</logic:notEmpty>

<%/* END OF EXPENSES */%>
      </table>
	    </td>
	  </tr>
	</table>    
</html:form>