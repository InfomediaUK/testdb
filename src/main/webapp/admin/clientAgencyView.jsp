<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.clientAgencyView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.client"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="ClientAgencyViewFormAdmin" paramProperty="client.clientId"><bean:write name="ClientAgencyViewFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="ClientAgencyViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.agency"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
	    <mmj-admin:hasAccess forward="agencyView">
	      <html:link forward="agencyView" paramId="agency.agencyId" paramName="ClientAgencyViewFormAdmin" paramProperty="clientAgency.agencyId"><bean:write name="ClientAgencyViewFormAdmin" property="clientAgency.agencyName"/></html:link>
	    </mmj-admin:hasAccess>
	    <mmj-admin:hasNoAccess forward="agencyView">
			    <bean:write name="ClientAgencyViewFormAdmin" property="clientAgency.agencyName"/>
	    </mmj-admin:hasNoAccess>   
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.feePerShift"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="clientAgency.feePerShift" format="#,##0.00"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.feePerHour"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="clientAgency.feePerHour" format="#,##0.00"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.feePercentage"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="clientAgency.feePercentage" format="#,##0.00"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.paymentTerms"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="clientAgency.paymentTerms" format="#,##0"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.created"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="clientAgency.creationTimestamp"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientAgencyViewFormAdmin" property="clientAgency.active"/></td>
  </tr>
</table>

<logic:equal name="ClientAgencyViewFormAdmin" property="clientAgency.active" value="true">
<mmj-admin:hasAccess forward="clientAgencyEdit" >
  <html:link forward="clientAgencyEdit" paramId="clientAgency.clientAgencyId" paramName="ClientAgencyViewFormAdmin" paramProperty="clientAgency.clientAgencyId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
</logic:equal>

<br/>
<br/>

<bean:message key="title.gradeList"/>
<br/>
<br/>
<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
			<logic:notEmpty name="ClientAgencyViewFormAdmin" property="clientAgency.clientAgencyGradeUsers">
			<html:form action="clientAgencyGradeDelete.do">
			<html:hidden name="ClientAgencyViewFormAdmin" property="clientAgency.clientAgencyId" />
		  <logic:iterate id="clientAgencyGradeUser" name="ClientAgencyViewFormAdmin" property="clientAgency.clientAgencyGradeUsers">
		    <logic:equal name="ClientAgencyViewFormAdmin" property="clientAgency.active" value="true">
		    <mmj-admin:hasAccess forward="clientAgencyGradeDelete">
		      <html:multibox property="selectedItems" >
		        <bean:write name="clientAgencyGradeUser" property="clientAgencyGradeId" />,<bean:write name="clientAgencyGradeUser" property="noOfChanges" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="gradeView">
		      <html:link forward="gradeView" paramId="grade.gradeId" paramName="clientAgencyGradeUser" paramProperty="gradeId"><bean:write name="clientAgencyGradeUser" property="gradeName"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="clientAgencyView">
 			    <bean:write name="clientAgencyGradeUser" property="gradeName"/>
		    </mmj-admin:hasNoAccess>   
		    <mmj-admin:hasAccess forward="clientAgencyGradeView">
		      <html:link forward="clientAgencyGradeView" paramId="clientAgencyGrade.clientAgencyGradeId" paramName="clientAgencyGradeUser" paramProperty="clientAgencyGradeId"><bean:write name="clientAgencyGradeUser" property="rate"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="clientAgencyGradeView">
 			    <bean:write name="clientAgencyGradeUser" property="rate"/>
		    </mmj-admin:hasNoAccess>
        &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="ClientAgencyViewFormAdmin" property="clientAgency.active" value="true">
			<mmj-admin:hasAccess forward="clientAgencyGradeDelete">
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
			<logic:notEmpty name="ClientAgencyViewFormAdmin" property="clientAgency.grades">
			<html:form action="clientAgencyGradeNew.do">
			<html:hidden name="ClientAgencyViewFormAdmin" property="clientAgency.clientAgencyId" />
		  <logic:iterate id="grade" name="ClientAgencyViewFormAdmin" property="clientAgency.grades">
		    <logic:equal name="ClientAgencyViewFormAdmin" property="clientAgency.active" value="true">
		    <mmj-admin:hasAccess forward="clientAgencyGradeNew">
		      <html:multibox property="selectedItems" >
		        <bean:write name="grade" property="gradeId" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="gradeView">
		      <html:link forward="gradeView" paramId="grade.gradeId" paramName="grade" paramProperty="gradeId"><bean:write name="grade" property="name"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="gradeView">
 			    <bean:write name="grade" property="name"/>
		    </mmj-admin:hasNoAccess>   
		    &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="ClientAgencyViewFormAdmin" property="clientAgency.active" value="true">
			<mmj-admin:hasAccess forward="clientAgencyGradeNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>