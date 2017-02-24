<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.queriesEdit"/>

<br/>
<br/>
<html:form action="queriesEditProcess.do" focus="queriesXml">
<table>
  <tr>
    <td>
      <html:textarea name="QueriesFormAdmin" property="queriesXml" style="width:100%" styleId="message" cols="100" rows="50" disabled="false" />      
    </td>
  </tr>
  <tr>
    <td align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>
</html:form>

