<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.clientView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.client"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="ClientViewFormAdmin" property="client.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.nhsName"/></td>
    <td align="left"><bean:write name="ClientViewFormAdmin" property="client.nhsName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ClientViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ClientViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ClientViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.upliftCommission"/></td>
    <td align="left"><bean:write name="ClientViewFormAdmin" property="client.upliftCommission"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="ClientViewFormAdmin" property="client.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientViewFormAdmin" property="client.active"/></td>
  </tr>
</table>

<logic:equal name="ClientViewFormAdmin" property="client.active" value="true">
<mmj-admin:hasAccess forward="clientEdit">
  <html:link forward="clientEdit" paramId="client.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="clientDelete">
  <html:link forward="clientDelete" paramId="client.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>

<br/>
<br/>

<bean:message key="title.siteList"/>&nbsp;
<mmj-admin:hasAccess forward="siteNew">
<html:link forward="siteNew" paramId="site.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="siteOrder">
<html:link forward="siteOrder" paramId="client.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
<%--
    <th align="left"><bean:message key="label.action" /></th>
--%>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.address" /></th>
    <th align="left"><bean:message key="label.country" /></th>
    <th align="left"><bean:message key="label.code" /></th>
  </tr>
  </thead>
  <logic:iterate id="site" name="ClientViewFormAdmin" property="client.sites">
	<bean:define id="trClass" value="site"/>
	<logic:notEqual name="site" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
<%--
    <td align="left">
    <logic:equal name="site" property="active" value="true">
	    <html:link forward="siteEdit" paramId="site.siteId" paramName="site" paramProperty="siteId"><bean:message key="link.edit"/></html:link>
	    |
	    <html:link forward="siteDelete" paramId="site.siteId" paramName="site" paramProperty="siteId"><bean:message key="link.delete"/></html:link>
    </logic:equal>
    <logic:notEqual name="site" property="active" value="true">
      &nbsp;
    </logic:notEqual>
    </td>
--%>
    <td align="left">
    <mmj-admin:hasAccess forward="siteView">
      <html:link forward="siteView" paramId="site.siteId" paramName="site" paramProperty="siteId"><bean:write name="site" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="siteView">
      <bean:write name="site" property="name"/>
    </mmj-admin:hasNoAccess>
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

<%-- no sortables as .do required clientId - it would be nice though !!! --%>
<%--
<display:table name="ClientViewFormAdmin.client.sites" id="site" class="list">
  <display:column titleKey="label.action">
    <logic:equal name="site" property="active" value="true">
	    <html:link forward="siteEdit" paramId="site.siteId" paramName="site" paramProperty="siteId"><bean:message key="link.edit"/></html:link>
	    |
	    <html:link forward="siteDelete" paramId="site.siteId" paramName="site" paramProperty="siteId"><bean:message key="link.delete"/></html:link>
    </logic:equal>
    <logic:notEqual name="site" property="active" value="true">
      &nbsp;
    </logic:notEqual>
  </display:column>
  <display:column titleKey="label.name" >
      <html:link forward="siteView" paramId="site.siteId" paramName="site" paramProperty="siteId"><bean:write name="site" property="name"/></html:link>
  </display:column>
  <display:column titleKey="label.address" >
    <bean:write name="site" property="address.address1"/>
    <bean:write name="site" property="address.address2"/>
    <bean:write name="site" property="address.address3"/>
    <bean:write name="site" property="address.address4"/>
  </display:column>
  <display:column property="address.postalCode" titleKey="label.postalCode" />
  <display:column property="countryName" titleKey="label.country" />
  <display:column property="creationTimestamp" titleKey="label.created" />
  <display:column property="active" titleKey="label.active" />
</display:table>
--%>

<br/>

<bean:message key="title.managerList"/>&nbsp;
<mmj-admin:hasAccess forward="managerNew">
<html:link forward="managerNew" paramId="manager.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.emailAddress" /></th>
    <th align="left"><bean:message key="label.login" /></th>
  </tr>
  </thead>
	<logic:iterate id="manager" name="ClientViewFormAdmin" property="client.managers">
	<bean:define id="trClass" value="manager"/>
	<logic:notEqual name="manager" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
	  <td align="left">
    <mmj-admin:hasAccess forward="managerView">
      <html:link forward="managerView" paramId="manager.managerId" paramName="manager" paramProperty="managerId"><bean:write name="manager" property="user.fullName"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="managerView">
      <bean:write name="manager" property="user.fullName"/>
    </mmj-admin:hasNoAccess>    
	  </td>
	  <td align="left">
	    <bean:write name="manager" property="user.emailAddress"/>
	  </td>
	  <td align="left">
	    <bean:write name="manager" property="user.login"/>
	  </td>
	</tr>
	</logic:iterate>  
</table>

<br/>

<bean:message key="title.jobFamilyList"/>&nbsp;
<mmj-admin:hasAccess forward="jobFamilyNew">
<html:link forward="jobFamilyNew" paramId="jobFamily.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="jobFamilyOrder">
<html:link forward="jobFamilyOrder" paramId="client.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.code" /></th>
  </tr>
  </thead>
	<logic:iterate id="jobFamily" name="ClientViewFormAdmin" property="client.jobFamilies">
	<bean:define id="trClass" value="jobFamily"/>
	<logic:notEqual name="jobFamily" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="jobFamilyView">
      <html:link forward="jobFamilyView" paramId="jobFamily.jobFamilyId" paramName="jobFamily" paramProperty="jobFamilyId"><bean:write name="jobFamily" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="jobFamilyView">
      <bean:write name="jobFamily" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="jobFamily" property="code"/>
    </td>
  </tr>
  </logic:iterate>
</table>

<br/>

<bean:define id="listGrades" name="ClientViewFormAdmin" property="client.grades" type="java.util.List" />
<bean:message key="title.gradeList"/> (<%= listGrades.size() %>)&nbsp;
<mmj-admin:hasAccess forward="gradeNew">
<html:link forward="gradeNew" paramId="grade.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="gradeOrder">
<html:link forward="gradeOrder" paramId="client.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<% 
  int columns = 8;
  int rows = listGrades.size() / columns;
  int remainder = listGrades.size() % columns;
%>
<table class="simple">
<logic:iterate id="grade" name="ClientViewFormAdmin" indexId="indexGrade" property="client.grades">
<bean:define id="g" name="indexGrade" type="java.lang.Integer" />
<bean:define id="tdClass" value="grade"/>
<logic:notEqual name="grade" property="active" value="true">
  <bean:define id="tdClass" value="inactive"/>
</logic:notEqual>
<%
  if (g % columns == 0)
  {
%>
  <tr>  
<%
  }
%>
    <td class="<bean:write name="tdClass"/>">
	    <mmj-admin:hasAccess forward="gradeView">
	      <html:link forward="gradeView" paramId="grade.gradeId" paramName="grade" paramProperty="gradeId"><bean:write name="grade" property="name"/></html:link>
	    </mmj-admin:hasAccess>
	    <mmj-admin:hasNoAccess forward="gradeView">
	      <bean:write name="grade" property="name"/>
	    </mmj-admin:hasNoAccess> 
    </td>
<%
  if (g % columns == (columns - 1))
  {
%>
  </tr>  
<%
  }
%>
</logic:iterate>
<% 
  if (remainder > 0)
  {
    // Fill to end with TDs
    while (remainder++ < columns)
    {
%> 
     <td>&nbsp;</td>
<%      
    }
%> 
  </tr>
<%    
  }
%>
</table>

<br/>

<bean:message key="title.agencyList"/>
<br/>
<br/>
<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
			<logic:notEmpty name="ClientViewFormAdmin" property="client.clientAgencies">
			<html:form action="clientAgencyDelete.do">
			<html:hidden name="ClientViewFormAdmin" property="client.clientId" />
		  <logic:iterate id="clientAgency" name="ClientViewFormAdmin" property="client.clientAgencies">
		    <logic:equal name="ClientViewFormAdmin" property="client.active" value="true">
		    <mmj-admin:hasAccess forward="clientAgencyDelete">
		      <html:multibox property="selectedItems" >
		        <bean:write name="clientAgency" property="clientAgencyId" />,<bean:write name="clientAgency" property="noOfChanges" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="clientAgencyView">
		      <html:link forward="clientAgencyView" paramId="clientAgency.clientAgencyId" paramName="clientAgency" paramProperty="clientAgencyId"><bean:write name="clientAgency" property="agencyName"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="clientAgencyView">
 			    <bean:write name="clientAgency" property="agencyName"/>
		    </mmj-admin:hasNoAccess>   
        &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="ClientViewFormAdmin" property="client.active" value="true">
			<mmj-admin:hasAccess forward="clientAgencyDelete">
			<br/>
			<html:submit><bean:message key="button.exclude"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
    <td valign="top">
      &nbsp;
    </td>
    <td valign="top">
			<bean:message key="title.excluded"/>
			<logic:notEmpty name="ClientViewFormAdmin" property="client.agencies">
			<html:form action="clientAgencyNew.do">
			<html:hidden name="ClientViewFormAdmin" property="client.clientId" />
		  <logic:iterate id="agency" name="ClientViewFormAdmin" property="client.agencies">
		    <logic:equal name="ClientViewFormAdmin" property="client.active" value="true">
		    <mmj-admin:hasAccess forward="clientAgencyNew">
		      <html:multibox property="selectedItems" >
		        <bean:write name="agency" property="agencyId" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="agencyView">
		      <html:link forward="agencyView" paramId="agency.agencyId" paramName="agency" paramProperty="agencyId"><bean:write name="agency" property="name"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="agencyView">
 			    <bean:write name="agency" property="name"/>
		    </mmj-admin:hasNoAccess>   
		    &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="ClientViewFormAdmin" property="client.active" value="true">
			<mmj-admin:hasAccess forward="clientAgencyNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>

<bean:message key="title.reasonForRequestList"/>&nbsp;
<mmj-admin:hasAccess forward="reasonForRequestNew">
<html:link forward="reasonForRequestNew" paramId="reasonForRequest.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="reasonForRequestOrder">
<html:link forward="reasonForRequestOrder" paramId="client.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.code" /></th>
  </tr>
  </thead>
	<logic:iterate id="reasonForRequest" name="ClientViewFormAdmin" property="client.reasonForRequests">
	<bean:define id="trClass" value="reasonForRequest"/>
	<logic:notEqual name="reasonForRequest" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="reasonForRequestView">
      <html:link forward="reasonForRequestView" paramId="reasonForRequest.reasonForRequestId" paramName="reasonForRequest" paramProperty="reasonForRequestId"><bean:write name="reasonForRequest" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="reasonForRequestView">
      <bean:write name="reasonForRequest" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="reasonForRequest" property="code"/>
    </td>
  </tr>
  </logic:iterate>
</table>

<br/>

<bean:message key="title.publicHolidayList"/>&nbsp;
<mmj-admin:hasAccess forward="publicHolidayNew">
<html:link forward="publicHolidayNew" paramId="publicHoliday.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.date" /></th>
  </tr>
  </thead>
	<logic:iterate id="publicHoliday" name="ClientViewFormAdmin" property="client.publicHolidays">
	<bean:define id="trClass" value="publicHoliday"/>
	<logic:notEqual name="publicHoliday" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="publicHolidayView">
      <html:link forward="publicHolidayView" paramId="publicHoliday.publicHolidayId" paramName="publicHoliday" paramProperty="publicHolidayId"><bean:write name="publicHoliday" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="publicHolidayView">
      <bean:write name="publicHoliday" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="publicHoliday" property="phDate"/>
    </td>
  </tr>
  </logic:iterate>
</table>

<br/>

<bean:message key="title.mgrAccessGroupList"/>&nbsp;
<mmj-admin:hasAccess forward="mgrAccessGroupNew">
<html:link forward="mgrAccessGroupNew" paramId="mgrAccessGroup.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
  </tr>
  </thead>
	<logic:iterate id="mgrAccessGroup" name="ClientViewFormAdmin" property="client.mgrAccessGroups">
	<bean:define id="trClass" value="publicHoliday"/>
	<logic:notEqual name="mgrAccessGroup" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="mgrAccessGroupView">
      <html:link forward="mgrAccessGroupView" paramId="mgrAccessGroup.mgrAccessGroupId" paramName="mgrAccessGroup" paramProperty="mgrAccessGroupId"><bean:write name="mgrAccessGroup" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="mgrAccessGroupView">
      <bean:write name="mgrAccessGroup" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  </logic:iterate>
</table>

<br/>

<bean:message key="title.reEnterPwdList"/>
<br/>
<br/>
<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
			<logic:notEmpty name="ClientViewFormAdmin" property="client.clientReEnterPwds">
			<html:form action="clientReEnterPwdDelete.do">
			<html:hidden name="ClientViewFormAdmin" property="client.clientId" />
		  <logic:iterate id="clientReEnterPwd" name="ClientViewFormAdmin" property="client.clientReEnterPwds">
		    <logic:equal name="ClientViewFormAdmin" property="client.active" value="true">
		    <mmj-admin:hasAccess forward="clientReEnterPwdDelete">
		      <html:multibox property="selectedItems" >
		        <bean:write name="clientReEnterPwd" property="clientReEnterPwdId" />,<bean:write name="clientReEnterPwd" property="noOfChanges" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <bean:write name="clientReEnterPwd" property="reEnterPwdName"/>
        &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="ClientViewFormAdmin" property="client.active" value="true">
			<mmj-admin:hasAccess forward="clientReEnterPwdDelete">
			<br/>
			<html:submit><bean:message key="button.exclude"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
    <td valign="top">
      &nbsp;
    </td>
    <td valign="top">
			<bean:message key="title.excluded"/>
			<logic:notEmpty name="ClientViewFormAdmin" property="client.reEnterPwds">
			<html:form action="clientReEnterPwdNew.do">
			<html:hidden name="ClientViewFormAdmin" property="client.clientId" />
		  <logic:iterate id="reEnterPwd" name="ClientViewFormAdmin" property="client.reEnterPwds">
		    <logic:equal name="ClientViewFormAdmin" property="client.active" value="true">
		    <mmj-admin:hasAccess forward="clientReEnterPwdNew">
		      <html:multibox property="selectedItems" >
		        <bean:write name="reEnterPwd" property="reEnterPwdId" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <bean:write name="reEnterPwd" property="name"/>
		    &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="ClientViewFormAdmin" property="client.active" value="true">
			<mmj-admin:hasAccess forward="clientReEnterPwdNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>

<br/>

<mmj-admin:hasAccess forward="upliftList">
<html:link forward="upliftList" paramId="client.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.upliftList"/></html:link>
</mmj-admin:hasAccess>

<%--
<bean:message key="title.upliftList"/>
<mmj-admin:hasAccess forward="upliftNew">
<html:link forward="upliftNew" paramId="uplift.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.upliftDay" /></th>
    <th align="left"><bean:message key="label.upliftHour" /></th>
    <th align="left"><bean:message key="label.upliftFactor" /></th>
    <th align="left"><bean:message key="label.upliftValue" /></th>
  </tr>
  </thead>
	<logic:iterate id="uplift" name="ClientViewFormAdmin" property="client.uplifts">
	<bean:define id="trClass" value="uplift"/>
	<logic:notEqual name="uplift" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="upliftView">
      <html:link forward="upliftView" paramId="uplift.upliftId" paramName="uplift" paramProperty="upliftId"><bean:write name="uplift" property="upliftDay"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="upliftView">
      <bean:write name="uplift" property="upliftDay"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="uplift" property="upliftHour"/>
    </td>
    <td align="left">
      <bean:write name="uplift" property="upliftFactor"/>
    </td>
    <td align="left">
      <bean:write name="uplift" property="upliftValue"/>
    </td>
  </tr>
  </logic:iterate>
</table>
--%>

<br/>
<br/>

<mmj-admin:hasAccess forward="budgetTransactionList">
<html:link forward="budgetTransactionList" paramId="client.clientId" paramName="ClientViewFormAdmin" paramProperty="client.clientId"><bean:message key="link.budgetTransactionList"/></html:link>
</mmj-admin:hasAccess>

