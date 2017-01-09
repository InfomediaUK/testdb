<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="1" cellspacing="0" border="0" height="100%">
  <tr valign="top">
    <td align="right" class="headerLinks">
<html:link forward="home"><bean:message key="link.home"/></html:link>
&bull;
<html:link forward="logout"><bean:message key="link.logout"/></html:link>
&bull;
<a href="javascript:toggleShowPageHelp();"><bean:message key="link.toggleHelp"/></a>
    </td>
  </tr>
  <tr valign="bottom">
    <td align="right">
<mmj-mgr:manager var="manager"/><bean:write name="manager" property="user.fullName"/>
    </td>
  </tr>
</table>
