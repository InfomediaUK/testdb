<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ 
taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<bean:parameter id="nextButtonKey" name="nextButtonKey" value="button.next" />
<bean:parameter id="nextButtonTabIndex" name="nextButtonTabIndex" value="2" />
<bean:parameter id="backButtonTabIndex" name="backButtonTabIndex" value="3" />
<table cellspacing="2" border="0" cellpadding="0">
  <tr>
    <td align="left">
<html:submit styleId="submitButton" tabindex="<%= nextButtonTabIndex %>" styleClass="wizardButton"><bean:message name="nextButtonKey"/></html:submit>
    </td>
  </tr>
  <tr>
    <td align="left">
<html:cancel styleId="cancelButton" tabindex="<%= backButtonTabIndex %>" styleClass="wizardButton"><bean:message key="button.back"/></html:cancel>
    </td>
  </tr>
</table>