<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.siteUnavailableView"/>

<br/>
<br/>
<table class="simple">
  <tr>
    <td>
      <html:textarea name="SiteUnavailableFormAdmin" property="text" style="width:100%" styleId="message" cols="100" rows="12" disabled="true" />      
    </td>
  </tr>
</table>
<mmj-admin:hasAccess forward="siteUnavailableEdit" >
  <html:link forward="siteUnavailableEdit"><bean:message key="link.edit"/></html:link>
</mmj-admin:hasAccess>

