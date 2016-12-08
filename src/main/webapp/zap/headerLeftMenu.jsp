<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-app" prefix="mmj-app" %>
<table cellpadding="1" cellspacing="0" border="0" height="100%">
  <tr valign="middle">
    <td align="right" class="headerLinks">
      <html:link href="javascript:doPopupHelp()" titleKey="link.title.help"><bean:message key="link.help"/></html:link>
    </td>
  </tr>
</table>

<script type="text/javascript">

function doInformPopupHelp() {
  alert("Welcome to Match My Job (MMJ).\n\nTo view the help, please click on the Help link.");
}

function doPopupHelp() {
  options = 'width=750,height=575,menubar=0,status=1,toolbar=0,resizable=1,scrollbars=1';
  var test = window.open("<%= request.getContextPath() %>/zap/help.do","help",options);
  if (test == null) {
      alert("The help popup window has been blocked by your browser settings.\n\nTo view the help, please click on the Help link.");
  }
}

</script>

<logic:equal name="popupHelp" value="true" scope="session">
<%
String helpPopupEvent = com.helmet.application.SystemHandler.getInstance().getHelpPopupEvent();
if (helpPopupEvent != null && !"".equals(helpPopupEvent)) {
%>
<script type="text/javascript">
function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      if (oldonload) {
        oldonload();
      }
      func();
    }
  }
}
addLoadEvent(<%= helpPopupEvent %>);
</script>
<%/* need to remove attribute from session */%>
<%
session.removeAttribute("popupHelp");
}
%>
</logic:equal>

