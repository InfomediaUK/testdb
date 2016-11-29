<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<table align="center">
  <tr>
    <td align="left">
<bean:message key="text.error404.header" />
<br/>
<br/>
<bean:message key="text.error404.1" />
<br/>
<bean:message key="text.error404.2" />
<br/>
<bean:message key="text.error404.3" />
<br/>
<br/>
<bean:message key="text.error404.options" />
    </td>
  </tr>
</table>
<br/>
<br/>
<html:link accesskey="m" forward="mgr"><bean:message key="link.mgr" /></html:link>
<br/>
<br/>
<html:link accesskey="c" forward="agy"><bean:message key="link.agy" /></html:link>
<br/>
<br/>
<html:link accesskey="p" forward="app"><bean:message key="link.app" /></html:link>
