<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.idDocumentDelete"/>
<br/>
<br/>
<html:errors/>
<html:form action="idDocumentDeleteProcess.do" focus="idDocument.name">
<html:hidden property="idDocument.idDocumentId"/>
<html:hidden property="idDocument.noOfChanges"/>
<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="IdDocumentFormAdmin" property="idDocument.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="IdDocumentFormAdmin" property="idDocument.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.idDocumentType"/></td>
    <td align="left">
<logic:equal name="IdDocumentFormAdmin" property="idDocument.idDocumentType" value="0">
       <bean:message key="label.passport"/>
</logic:equal>
<logic:equal name="IdDocumentFormAdmin" property="idDocument.idDocumentType" value="1">
       <bean:message key="label.idCard"/>
</logic:equal>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.displayOrder"/></td>
    <td align="left"><bean:write name="IdDocumentFormAdmin" property="idDocument.displayOrder"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
