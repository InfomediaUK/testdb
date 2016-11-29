<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:parameter id="formName" name="formName" value="Form Name Not Set" />
<bean:define id="form" name="<%= formName %>" type="org.apache.struts.validator.DynaValidatorForm"/>
<table class="simple" width="100%">
  <tr>
    <th align="left">
      <bean:message key="label.agency" />
    </tH>
    <th align="right">
      <bean:message key="label.percentage" />
    </th>
    <th align="left">
      <bean:message key="label.grade" />
    </th>
    <th align="right">
      <bean:message key="label.value" />
    </th>
    <th align="right">
      <bean:message key="label.rate" />
    </th>
  </tr>
<logic:iterate id="clientAgencyJobProfileGrade" name="form" property="clientAgencyJobProfileGradeUserArray" >
  <tr>
    <td align="left">
      <bean:write name="clientAgencyJobProfileGrade" property="agencyName"/>
    </td>
    <td align="right">
      <bean:write name="clientAgencyJobProfileGrade" property="percentage" format="#0" />%
    </td>
    <td align="left">
      <bean:write name="clientAgencyJobProfileGrade" property="gradeName"/>
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="clientAgencyJobProfileGrade" property="value" format="#,##0.00" />
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="clientAgencyJobProfileGrade" property="rate" format="#,##0.00" />
    </td>
  </tr>
</logic:iterate>
</table>
