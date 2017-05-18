<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>
<bean:define id="consultantList" name="AgencyApplicantTrainingFormAdmin" property="agency.consultants" />
<bean:message key="title.agencyApplicantTraining"/>
<html:form action="/agencyApplicantTrainingSelect.do" onsubmit="return singleSubmit();">
<html:hidden property="agency.agencyId"/>
<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.agency"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="AgencyApplicantTrainingFormAdmin" property="agency.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="AgencyApplicantTrainingFormAdmin" property="agency.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="AgencyApplicantTrainingFormAdmin" property="agency.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="AgencyApplicantTrainingFormAdmin" property="agency.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.consultant"/></td>
    <td align="left">
      <html:select name="AgencyApplicantTrainingFormAdmin" property="consultantId">
        <html:options collection="consultantList" labelProperty="user.fullName" property="consultantId" />
      </html:select>
    </td>
  <tr>
    <td colspan="2" align="center">
      <html:submit><bean:message key="button.select"/></html:submit>
    </td>
  </tr>
</table>
</html:form>

