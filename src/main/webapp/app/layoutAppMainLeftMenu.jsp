<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" errorPage="/errorPages/error500.jsp" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-app" prefix="mmj-app" %>
<%  org.apache.struts.util.ModuleUtils.getInstance().selectModule(request, pageContext.getServletContext()); %>
<!DOCTYPE html>
<html>
<head>
<tiles:useAttribute name="includeZapatec"/>
<tiles:useAttribute name="titleKey"/>
<tiles:useAttribute name="showHeaderLinks"/>
<title>APP - <bean:message name="titleKey" property="value" scope="page"/></title>
<meta name="description" content="<tiles:getAsString name="description"/>">
<meta name="keywords" content="<tiles:getAsString name="keywords"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="<%= pageContext.getSession().getMaxInactiveInterval() %>; url=<%= request.getContextPath() %>/appLoggedOut.do">
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<link rel="icon" href="<%= request.getContextPath() %>/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%= request.getContextPath() %>/favicon.ico" type="image/x-icon">
<logic:equal name="includeZapatec" property="value" value="true">
<link rel="stylesheet" type="text/css" media="all" href="<%= request.getContextPath() %>/zapatec/themes/winxp.css" title="Calendar Theme - winxp.css">
<link href="<%= request.getContextPath() %>/zapatec/doc/css/zpcal.css" rel="stylesheet" type="text/css">
</logic:equal>
<link type="text/css" rel="stylesheet" href="site.css" media="screen">
<logic:equal name="includeZapatec" property="value" value="true">
<!-- import the calendar script -->
<script type="text/javascript" src="<%= request.getContextPath() %>/zapatec/src/utils.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/zapatec/src/calendar.js"></script>
<!-- import the language module -->
<script type="text/javascript" src="<%= request.getContextPath() %>/zapatec/lang/calendar-en.js"></script>
<!-- import the calendar setup script -->
<script type="text/javascript" src="<%= request.getContextPath() %>/zapatec/src/calendar-setup.js"></script>
</logic:equal>
<script language="javascript" type="text/javascript" src="site.js"></script>
</head>
<body>

<html:link linkName="theTop"></html:link>

<jsp:include flush="true" page="/siteUnavailable.jsp"/>

<bean:parameter id="bendyParam" name="bendy" value="" />
<logic:equal name="bendyParam" value="false">
<% session.setAttribute("bendy", "false"); %>
</logic:equal>
<logic:equal name="bendyParam" value="true">
<% session.removeAttribute("bendy"); %>
</logic:equal>

<bean:define id="pageWidth" value="815"/> <!-- 815 100% -->
<bean:define id="helpWidth" value="315"/> <!-- 315 100% -->
<bean:define id="menuWidth" value="95"/> <!-- 95 50 -->
<bean:define id="spacerWidth" value="11"/>
<bean:define id="contentWidth" value="709"/> <!-- 709 100% -->
<bean:define id="headerHeight" value="25"/>
<bean:define id="contentHeight" value="550"/>
<bean:define id="footerHeight" value="25"/>
<logic:notPresent name="bendy" scope="session">
	<bean:define id="pageWidth" value="100%"/>
	<bean:define id="helpWidth" value="100%"/>
	<bean:define id="menuWidth" value="95"/>
	<bean:define id="contentWidth" value="100%"/>
</logic:notPresent>

<div align="left">
<%/* TEMPORARY */%>

<mmj-app:client var="client"/>
<bean:define id="clientName" name="client" property="name" type="java.lang.String"/>
<bean:define id="clientLogo" name="client" property="logo2Url" type="java.lang.String"/>
<bean:define id="clientLogoWidth" name="client" property="logo2Width" type="java.lang.Integer"/>
<bean:define id="clientLogoHeight" name="client" property="logo2Height" type="java.lang.Integer"/>

<mmj-app:agency var="agency"/>
<bean:define id="agencyName" name="agency" property="name" type="java.lang.String"/>
<bean:define id="agencyLogo" name="agency" property="logoUrl" type="java.lang.String"/>
<bean:define id="agencyLogoWidth" name="agency" property="logoWidth" type="java.lang.Integer"/>
<bean:define id="agencyLogoHeight" name="agency" property="logoHeight" type="java.lang.Integer"/>

<table width="<bean:write name="pageWidth"/>" height="76" cellpadding="0" cellspacing="0" border="0">
  <tr height="76">
    <td valign="top" align="left" width="250">
      <table width="250" cellpadding="0" cellspacing="0">
        <tr height="50">
          <td valign="top" align="left">
<logic:equal name="agencyLogoWidth" value="0">
            <bean:write name="agency" property="name"/>
</logic:equal>
<logic:notEqual name="agencyLogoWidth" value="0">
            <html:img src="<%= request.getContextPath() + agencyLogo %>" width="<%= agencyLogoWidth.toString() %>" height="<%= agencyLogoHeight.toString() %>" title="<%= agencyName %>" alt="<%= agencyName %>"/>
</logic:notEqual>
          </td>
        </tr>
        <tr height="26">
          <td valign="middle" align="left">
<table cellpadding="1" cellspacing="0" border="0" height="100%">
  <tr valign="bottom">
    <td align="right" valign="middle">
<mmj-app:applicant var="applicant"/><bean:write name="applicant" property="user.fullName"/>
    </td>
  </tr>
</table>
          </td>
        </tr>
      </table>
    </td>
    <td valign="top" align="left" width="<bean:write name="helpWidth"/>">
    <div id="pageHelp">
      <table height="100%">
        <tr>
          <td valign="top">
      		<tiles:useAttribute name="pageHelpKey"/>
			<bean:message name="pageHelpKey" property="value" scope="page"/>
          </td>
        </tr>
        <tr>
          <td valign="bottom">
			<bean:message key="pageHelp.helpDesk"/>
		  </td>
		</tr>
      </table>
    </div>
    </td>
    <td valign="top" align="left" width="250">
      <table width="250" cellpadding="0" cellspacing="0">
        <tr height="50">
          <td valign="top" align="right">
            &nbsp;
          </td>
        </tr>
        <tr height="26">
          <td valign="bottom" align="right">
          
<logic:equal name="showHeaderLinks" value="true">
          
            <tiles:insert attribute="header"/>

<script type="text/javascript">
<!--

var theRequest;

function getRequest() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function toggleShowPageHelp() {
	theRequest = getRequest();
//	alert(theRequest);
	theRequest.open("POST", "<%= request.getContextPath() %>/app/toggleShowPageHelp.do", true);
	theRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	theRequest.onreadystatechange = callbackMethod;
	theRequest.send("x=123");
}

function callbackMethod() {
	if ((theRequest.readyState == 4) && (theRequest.status == 200)) {
		var response = theRequest.responseText;
//    window.status = window.status + response;
    if (response == "OK") {
      toggle('pageHelp')
    }
	}
}

//-->
</script>

</logic:equal>
<logic:notEqual name="showHeaderLinks" value="true">
			&nbsp;
</logic:notEqual>

          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<script language="javascript" type="text/javascript">
<!--
showIt('pageHelp');
// -->
</script>

<%-- 

NOTE - above ALWAYS shows page help as the Help link pops up a help doco

<mmj-app:applicant var="applicant"/>
<logic:equal name="applicant" property="user.showPageHelp" value="true">
  <script language="javascript" type="text/javascript">
	<!--
	showIt('pageHelp');
	// -->
	</script>
</logic:equal>
<logic:notEqual name="applicant" property="user.showPageHelp" value="true">
  <script language="javascript" type="text/javascript">
	<!--
	hideIt('pageHelp');
	// -->
	</script>
</logic:notEqual>
--%>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>">
  <tr><td height="5" width="<bean:write name="pageWidth"/>"></td></tr>
  <tr><td height="1" width="<bean:write name="pageWidth"/>" bgcolor="#e0e0e0"></td></tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>" height="<bean:write name="contentHeight"/>">
  <tr>
    <td align="left" valign="top" width="<bean:write name="menuWidth"/>" class="leftColumn">
	    <table cellpadding="0" cellspacing="0" align="left" height="100%" width="100%">
	      <tr>
	        <td align="left" valign="top" nowrap="nowrap">
	          <tiles:insert attribute="menu"/>
	        </td>
	      </tr>
	    </table>
    </td>
    <td align="left" valign="top" width="<bean:write name="spacerWidth"/>" height="100%">
	    <table cellpadding="0" cellspacing="0" align="left" width="<bean:write name="spacerWidth"/>" height="100%">
	      <tr>
	        <td align="left" valign="top" width="5" height="100%"><html:img src="images/trans.gif" width="5" height="100%" /></td>
	        <td align="left" valign="top" width="1" height="100%" bgcolor="#e0e0e0"><html:img src="images/trans.gif" width="1" height="100%" /></td>
	        <td align="left" valign="top" width="5" height="100%"><html:img src="images/trans.gif" width="5" height="100%" /></td>
	      </tr>
	    </table>
    </td>
    <td align="left" valign="top" width="<bean:write name="contentWidth"/>" class="contentColumn">
      <tiles:insert attribute="body"/>
    </td>
  </tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>">
  <tr><td height="1" width="<bean:write name="pageWidth"/>" bgcolor="#e0e0e0"></td></tr>
  <tr><td height="5" width="<bean:write name="pageWidth"/>"></td></tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>" height="<bean:write name="footerHeight"/>">
  <tr>
    <td align="center" valign="top" class="footer">
      <tiles:insert attribute="footer"/>
      <%-- Was until 06/09/2016 Lyndon
      <tiles:insert attribute="footer">
        <tiles:put name="showHeaderLinks" type="string" value="<%= showHeaderLinks %>" />
      </tiles:insert>
      --%>
    </td>
  </tr>
</table>
</div>
<script language="javascript">
<!--
// MUST BE AT THE BOTTOM OF THE PAGE OTHERWISE FIREHELMET SCREWS UP!
var formSubmitCount = 0;
// -->
</script>
</body>
</html>
