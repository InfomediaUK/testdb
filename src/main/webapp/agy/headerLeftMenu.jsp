<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="1" cellspacing="0" border="0" height="100%">
  <tr valign="middle">
    <td align="right" class="headerLinks">
<html:link forward="home"><bean:message key="link.home"/></html:link>
&bull;
<html:link forward="logout"><bean:message key="link.logout"/></html:link>
<mmj-agy:consultant var="consultant"/>&nbsp;(<bean:write name="consultant" property="user.fullName"/>)
<a href="javascript:toggleShowPageHelp();">?</a>
    </td>
  </tr>
</table>
