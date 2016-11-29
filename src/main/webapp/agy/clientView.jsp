<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.clientView"/>
		</td>
<mmj-agy:hasAccess forward="clientEdit">
    <html:form action="/clientEdit.do" onsubmit="return singleSubmit();">
    <html:hidden name="ClientViewFormAgy" property="client.clientId"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="clientDelete">
    <html:form action="/clientDelete.do" onsubmit="return singleSubmit();">
    <html:hidden name="ClientViewFormAgy" property="client.clientId"/>
    <td>&nbsp;</td>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
    </html:form>
</mmj-agy:hasAccess>
<mmj-agy:hasAccess forward="clientList">
    <td>&nbsp;</td>
    <html:form action="/clientList.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
</mmj-agy:hasAccess>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="25%"><bean:message key="label.name"/></th>
    <td align="left" width="75%"><bean:write name="ClientViewFormAgy" property="client.name"/></td>
  </tr>
  <tr>
    <th align="left" width="25%"><bean:message key="label.nhsName"/></th>
    <td align="left" width="75%"><bean:write name="ClientViewFormAgy" property="client.nhsName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.address"/></th>
    <td align="left">
	  <bean:write name="ClientViewFormAgy" property="client.address.address1"/>
	  <logic:notEmpty name="ClientViewFormAgy" property="client.address.address2">
		<br/><bean:write name="ClientViewFormAgy" property="client.address.address2"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="ClientViewFormAgy" property="client.address.address3">
		<br/><bean:write name="ClientViewFormAgy" property="client.address.address3"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="ClientViewFormAgy" property="client.address.address4">
		<br/><bean:write name="ClientViewFormAgy" property="client.address.address4"/>
	  </logic:notEmpty>		
	</td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.postalCode"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.address.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.country"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.countryName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.code"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.secretWordAtLogin"/></th>
    <td align="left">
      <logic:equal name="ClientViewFormAgy" property="client.secretWordAtLogin" value="true">
		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="ClientViewFormAgy" property="client.secretWordAtLogin" value="true">
		<bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.autoActivateDefault"/></th>
    <td align="left">
      <logic:equal name="ClientViewFormAgy" property="client.autoActivateDefault" value="true">
		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="ClientViewFormAgy" property="client.autoActivateDefault" value="true">
		<bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.upliftCommission"/></th>
    <td align="left">
      <logic:equal name="ClientViewFormAgy" property="client.upliftCommission" value="true">
		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="ClientViewFormAgy" property="client.upliftCommission" value="true">
		<bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.telephoneNumber"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.telephoneNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.faxNumber"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.faxNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.vatNumber"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.vatNumber"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.reference"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.reference"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.clientFreeText"/></th>
    <td align="left">
    <bean:write name="ClientViewFormAgy" property="client.freeText"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.agencyWorkerChecklistOther"/></th>
    <td align="left">
    <bean:write name="ClientViewFormAgy" property="client.agencyWorkerChecklistOther"/>
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
    <th align="left"><bean:message key="label.accountContactName"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy"  property="client.accountContactName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactAddress"/></th>
    <td align="left">
    
	  <bean:write name="ClientViewFormAgy" property="client.accountContactAddress.address1"/>
	  <logic:notEmpty name="ClientViewFormAgy" property="client.accountContactAddress.address2">
		<br/><bean:write name="ClientViewFormAgy" property="client.accountContactAddress.address2"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="ClientViewFormAgy" property="client.accountContactAddress.address3">
		<br/><bean:write name="ClientViewFormAgy" property="client.accountContactAddress.address3"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="ClientViewFormAgy" property="client.accountContactAddress.address4">
		<br/><bean:write name="ClientViewFormAgy" property="client.accountContactAddress.address4"/>
	  </logic:notEmpty>		
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactPostalCode"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.accountContactAddress.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactCountry"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy"  property="client.accountContactCountryName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.accountContactEmailAddress"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy"  property="client.accountContactEmailAddress"/></td>
  </tr>
  <logic:notEmpty name="ClientViewFormAgy" property="client.logoFilename">
  <bean:define id="clientLogo" name="ClientViewFormAgy" property="client.logoUrl" type="java.lang.String"/>
  <bean:define id="clientLogoWidth" name="ClientViewFormAgy" property="client.logoWidth" type="java.lang.Integer"/>
  <bean:define id="clientLogoHeight" name="ClientViewFormAgy" property="client.logoHeight" type="java.lang.Integer"/>
  <tr>
    <th align="left"><bean:message key="label.logo"/></th>
    <td align="left">
      <html:img src="<%= request.getContextPath() + clientLogo %>" width="<%= clientLogoWidth.toString() %>" height="<%= clientLogoHeight.toString() %>" />
    </td>
  </tr>
<!-- 
  <tr>
    <th align="left"><bean:message key="label.logoFilename"/></th>
    <td align="left">
      <bean:write name="ClientViewFormAgy" property="client.logoFilename"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logoWidth"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.logoWidth"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logoHeight"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.logoHeight"/></td>
  </tr>
-->
   </logic:notEmpty>
  <logic:notEmpty name="ClientViewFormAgy" property="client.logo2Filename">
  <bean:define id="clientLogo2" name="ClientViewFormAgy" property="client.logo2Url" type="java.lang.String"/>
  <bean:define id="clientLogo2Width" name="ClientViewFormAgy" property="client.logo2Width" type="java.lang.Integer"/>
  <bean:define id="clientLogo2Height" name="ClientViewFormAgy" property="client.logo2Height" type="java.lang.Integer"/>
  <tr>
    <th align="left"><bean:message key="label.logo2"/></th>
    <td align="left">
      <html:img src="<%= request.getContextPath() + clientLogo2 %>" width="<%= clientLogo2Width.toString() %>" height="<%= clientLogo2Height.toString() %>" />
    </td>
  </tr>
<!-- 
  <tr>
    <th align="left"><bean:message key="label.logo2Filename"/></th>
    <td align="left">
      <bean:write name="ClientViewFormAgy" property="client.logo2Filename"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logo2Width"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.logo2Width"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.logo2Height"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.logo2Height"/></td>
  </tr>
-->
  </logic:notEmpty>
  <tr>
    <th align="left"><bean:message key="label.tradeshift"/></th>
    <td align="right">
<mmj-agy:hasAccess forward="clientTradeshiftValidate">
      <html:form action="/clientTradeshiftValidate.do" onsubmit="return singleSubmit();">
        <html:hidden name="ClientViewFormAgy" property="client.clientId"/>
        <html:submit styleClass="titleButton"><bean:message key="button.validate"/></html:submit>
      </html:form>
</mmj-agy:hasAccess>            
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftSbsPayablesCode"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.tradeshiftSbsPayablesCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftCompanyAccountId"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.tradeshiftCompanyAccountId"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.tradeshiftState"/></th>
    <td align="left"><bean:write name="ClientViewFormAgy" property="client.tradeshiftState"/></td>
  </tr>
</table>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
		<bean:message key="title.clientSiteList"/>
		</td>
		<mmj-agy:hasAccess forward="siteNew">
    <html:form action="/siteNew.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="site.clientId" value="<bean:write name="ClientViewFormAgy" property="client.clientId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
		</mmj-agy:hasAccess>
		<mmj-agy:hasAccess forward="siteOrder">
    <html:form action="/siteOrder.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="client.clientId" value="<bean:write name="ClientViewFormAgy" property="client.clientId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.order"/></html:submit></td>
    </html:form>
		</mmj-agy:hasAccess>
  </tr>
</table>

<logic:notPresent name="ClientViewFormAgy" property="sites">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ClientViewFormAgy" property="sites">
<logic:empty name="ClientViewFormAgy" property="sites">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ClientViewFormAgy" property="sites">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.site"/></th>
    <th align="left"><bean:message key="label.nhsLocation"/></th>
    <th align="left"><bean:message key="label.address"/></th>
    <th align="left"><bean:message key="label.country"/></th>
    <th align="left"><bean:message key="label.code"/></th>
  </tr>
  </thead>
  <logic:iterate id="site" name="ClientViewFormAgy" property="sites" indexId="siteIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
			<mmj-agy:hasAccess forward="siteView">
  	  <html:link forward="siteView" paramId="site.siteId" paramName="site" paramProperty="siteId">
	      <bean:write name="site" property="name"/>
	 	  </html:link>
			</mmj-agy:hasAccess>
			<mmj-agy:hasNoAccess forward="siteView">
      <bean:write name="site" property="name"/>
			</mmj-agy:hasNoAccess>
    </td>
	<logic:equal name="site" property="nhsMatched" value="true">
    <td align="left" class="nhsMatched">
  </logic:equal>
	<logic:notEqual name="site" property="nhsMatched" value="true">
    <td align="left">
  </logic:notEqual>
      <bean:write name="site" property="nhsLocation"/>
    </td>
    <td align="left">
      <bean:write name="site" property="address.fullAddress"/>
    </td>
    <td align="left">
      <bean:write name="site" property="countryName"/>
    </td>
    <td align="left">
      <bean:write name="site" property="code"/>
    </td>
  </tr> 
  </logic:iterate>
</table> 
</logic:notEmpty> 
</logic:present>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
		<bean:message key="title.clientReasonForRequestList"/>
		</td>
		<mmj-agy:hasAccess forward="reasonForRequestNew">
    <html:form action="/reasonForRequestNew.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="reasonForRequest.clientId" value="<bean:write name="ClientViewFormAgy" property="client.clientId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
		</mmj-agy:hasAccess>
		<mmj-agy:hasAccess forward="reasonForRequestOrder">
    <html:form action="/reasonForRequestOrder.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="client.clientId" value="<bean:write name="ClientViewFormAgy" property="client.clientId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.order"/></html:submit></td>
    </html:form>
		</mmj-agy:hasAccess>
  </tr>
</table>

<logic:notPresent name="ClientViewFormAgy" property="reasonForRequests">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ClientViewFormAgy" property="reasonForRequests">
<logic:empty name="ClientViewFormAgy" property="reasonForRequests">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ClientViewFormAgy" property="reasonForRequests">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.reasonForRequest"/></th>
    <th align="left"><bean:message key="label.code"/></th>
  </tr>
  </thead>
  <logic:iterate id="reasonForRequest" name="ClientViewFormAgy" property="reasonForRequests" indexId="reasonForRequestIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
			<mmj-agy:hasAccess forward="reasonForRequestView">
  	  <html:link forward="reasonForRequestView" paramId="reasonForRequest.reasonForRequestId" paramName="reasonForRequest" paramProperty="reasonForRequestId">
	      <bean:write name="reasonForRequest" property="name"/>
	 	  </html:link>
			</mmj-agy:hasAccess>
			<mmj-agy:hasNoAccess forward="reasonForRequestView">
      <bean:write name="reasonForRequest" property="name"/>
			</mmj-agy:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="reasonForRequest" property="code"/>
    </td>
  </tr> 
  </logic:iterate>
</table> 
</logic:notEmpty> 
</logic:present>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
		<bean:message key="title.clientPublicHolidayList"/>
		</td>
		<mmj-agy:hasAccess forward="publicHolidayNew">
    <html:form action="/publicHolidayNew.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="publicHoliday.clientId" value="<bean:write name="ClientViewFormAgy" property="client.clientId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
		</mmj-agy:hasAccess>
  </tr>
</table>

<logic:notPresent name="ClientViewFormAgy" property="publicHolidays">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ClientViewFormAgy" property="publicHolidays">
<logic:empty name="ClientViewFormAgy" property="publicHolidays">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ClientViewFormAgy" property="publicHolidays">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name"/></th>
    <th align="left"><bean:message key="label.date"/></th>
  </tr>
  </thead>
  <logic:iterate id="publicHoliday" name="ClientViewFormAgy" property="publicHolidays" indexId="publicHolidayIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
			<mmj-agy:hasAccess forward="publicHolidayView">
  	  <html:link forward="publicHolidayView" paramId="publicHoliday.publicHolidayId" paramName="publicHoliday" paramProperty="publicHolidayId">
	      <bean:write name="publicHoliday" property="name"/>
	 	  </html:link>
			</mmj-agy:hasAccess>
			<mmj-agy:hasNoAccess forward="publicHolidayView">
      <bean:write name="publicHoliday" property="name"/>
			</mmj-agy:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="publicHoliday" property="phDate" formatKey="format.longDateFormat"/>
    </td>
  </tr> 
  </logic:iterate>
</table> 
</logic:notEmpty> 
</logic:present>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
	<td align="left" valign="middle" class="title">
	<bean:message key="title.clientUpliftList"/>
	</td>
  </tr>
</table>

<logic:notPresent name="ClientViewFormAgy" property="uplifts">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ClientViewFormAgy" property="uplifts">
<logic:empty name="ClientViewFormAgy" property="uplifts">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ClientViewFormAgy" property="uplifts">

<%
int[] days = {0,1,2,3,4,5,6,7};
String[] dayNames = {"PH","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
int[] hours = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
pageContext.setAttribute("days", days);
pageContext.setAttribute("dayNames", dayNames);
pageContext.setAttribute("hours", hours);
%>

<table class="simple">
  <thead>
  <tr>
    <th align="left">&nbsp;</th>
	<logic:iterate id="dayName" name="dayNames" type="java.lang.String">
    <th align="center" id="d<bean:write name="dayName" />"><bean:write name="dayName" /></th>
    </logic:iterate>
  </tr>
  </thead>
<logic:iterate id="hour" name="hours" type="java.lang.Integer">
  <tr>
	<th align="right" id="h<bean:write name="hour" />">
	  &nbsp;<bean:write name="hour" />&nbsp;
	</th>
  <logic:iterate id="day" name="days" type="java.lang.Integer">
	<logic:iterate id="uplift" name="ClientViewFormAgy" property="uplifts" type="com.helmet.bean.Uplift">
	  <logic:equal name="uplift" property="upliftDay" value="<%= Integer.toString(day) %>">
		<logic:equal name="uplift" property="upliftHour" value="<%= Integer.toString(hour) %>">

    <bean:define id="bgcolor" value="#ff0000"/>
    </*-- not good way to get colour --*/>
    <logic:equal name="uplift" property="upliftFactor" value="1">
	    <bean:define id="bgcolor" value="#ffffff"/>
    </logic:equal>
    <logic:equal name="uplift" property="upliftFactor" value="1.3">
	    <bean:define id="bgcolor" value="#e0e0e0"/>
    </logic:equal>
    <logic:equal name="uplift" property="upliftFactor" value="1.5">
	    <bean:define id="bgcolor" value="#c0c0c0"/>
    </logic:equal>
    <logic:equal name="uplift" property="upliftFactor" value="1.6">
	    <bean:define id="bgcolor" value="#a0a0a0"/>
    </logic:equal>
    <logic:equal name="uplift" property="upliftFactor" value="2.0">
	    <bean:define id="bgcolor" value="#808080"/>
    </logic:equal>
    
    <td align="right" bgcolor="<%= bgcolor %>" 
    onmouseover="document.getElementById('d<%= dayNames[day] %>').style.color='#cc0000'; 
                 document.getElementById('h<%= hour %>').style.color='#cc0000'; 
                 style.color='#cc0000';" 
    onmouseout="document.getElementById('d<%= dayNames[day] %>').style.color='#000000'; 
                document.getElementById('h<%= hour %>').style.color='#000000'; 
                style.color='#000000'"
    >
    
      &nbsp;<bean:write name="uplift" property="upliftFactor" format="#0.00"/>&nbsp;
	</td>
		</logic:equal>
	  </logic:equal>
	</logic:iterate>
    </td>
  </logic:iterate>
  </tr>
</logic:iterate>
</table>

</logic:notEmpty> 
</logic:present>

	