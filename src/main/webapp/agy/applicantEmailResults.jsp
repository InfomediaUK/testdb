<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>


<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
		  <bean:write name="ApplicantEmailFormAgy" property="emailActionName"/>
		</td>
  </tr>
</table>

<logic:notPresent name="ApplicantEmailFormAgy" property="emailActionResultList">
  List Not Present
</logic:notPresent>
<logic:present name="ApplicantEmailFormAgy" property="emailActionResultList">
	<logic:empty name="ApplicantEmailFormAgy" property="emailActionResultList">
	  List Empty
	</logic:empty>
	<logic:notEmpty name="ApplicantEmailFormAgy" property="emailActionResultList">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name"/></th>
    <th align="left"><bean:message key="label.emailAddress"/></th>
    <th align="left">Status</th>
    <th align="left">Message</th>
  </tr>
  </thead>
  <logic:iterate id="emailActionResult" name="ApplicantEmailFormAgy" property="emailActionResultList" indexId="applicantIndex" type="com.helmet.bean.EmailActionResult">
  <tr>
    <td>
	  <mmj-agy:hasAccess forward="applicantView">
	    <html:link forward="applicantView" paramId="applicant.applicantId" paramName="emailActionResult" paramProperty="applicantId" >
	      <bean:write name="emailActionResult" property="fullName"/>
	    </html:link>
	  </mmj-agy:hasAccess>
	  <mmj-agy:hasNoAccess forward="applicantView">
	      <bean:write name="emailActionResult" property="fullName"/>
	  </mmj-agy:hasNoAccess>
    </td>
    <td><bean:write name="emailActionResult" property="emailAddress"/></td>
    <td><bean:write name="emailActionResult" property="emailStatus"/></td>
    <td><bean:write name="emailActionResult" property="message"/></td>
  </tr>
  </logic:iterate>
</table>
	</logic:notEmpty>
</logic:present>