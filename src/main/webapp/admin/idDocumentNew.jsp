<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.idDocumentNew"/>

<br/>
<br/>
<html:errors/>

<html:form action="idDocumentNewProcess.do" focus="idDocument.name">

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text name="IdDocumentFormAdmin" property="idDocument.name" size="60" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text name="IdDocumentFormAdmin" property="idDocument.code" size="60" /></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.idDocumentType"/></td>
    <td align="left">
      <html:select name="IdDocumentFormAdmin" property="idDocument.idDocumentType">
        <html:option value="0"><bean:message key="label.passport"/></html:option>
        <html:option value="1"><bean:message key="label.idCard"/></html:option>
      </html:select>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.requiresVisa"/></td>
    <td align="left">
      <html:radio name="IdDocumentFormAdmin" property="idDocument.requiresVisa" value="true" styleId="requiresVisa" />
      <label for="requiresVisa">
        <bean:message key="label.yes" />
      </label>
      <html:radio name="IdDocumentFormAdmin" property="idDocument.requiresVisa" value="false" styleId="requiresVisa" />
      <label for="requiresVisa">
        <bean:message key="label.no" />
      </label>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
     <td align="left"><html:text name="IdDocumentFormAdmin" property="idDocument.displayOrder" size="10" /></td>
   </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>

