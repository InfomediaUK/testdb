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
<tiles:useAttribute name="titleKey"/>
<title><bean:message name="titleKey" property="value" scope="page"/></title>
<meta name="description" content="<tiles:getAsString name="description"/>">
<meta name="keywords" content="<tiles:getAsString name="keywords"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="<%= pageContext.getSession().getMaxInactiveInterval() %>; url=<%= request.getContextPath() %>/appLoggedOut.do">
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<link rel="icon" href="<%= request.getContextPath() %>/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%= request.getContextPath() %>/favicon.ico" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="site.css" media="screen">
<script language="javascript" type="text/javascript" src="site.js"></script>
</head>
<body>

<html:link linkName="theTop"></html:link>
<bean:define id="pageWidth" value="100%"/>
<bean:define id="helpWidth" value="100%"/>
<bean:define id="spacerWidth" value="11"/>
<bean:define id="contentWidth" value="100%"/>
<bean:define id="contentHeight" value="550"/>

<div align="left">
<%/* TEMPORARY */%>

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
          </td>
        </tr>
      </table>
    </td>
    <td valign="top" align="left" width="<bean:write name="helpWidth"/>">
    <div id="pageHelp">
      <table height="100%">
        <tr>
          <td valign="top">
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
      &nbsp;
    </td>
  </tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>">
  <tr><td height="5" width="<bean:write name="pageWidth"/>"></td></tr>
  <tr><td height="1" width="<bean:write name="pageWidth"/>" bgcolor="#e0e0e0"></td></tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>" height="<bean:write name="contentHeight"/>">
  <tr>
    <td align="left" valign="top" width="<bean:write name="contentWidth"/>" class="contentColumn">
      <tiles:insert attribute="body"/>
    </td>
  </tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<bean:write name="pageWidth"/>">
  <tr><td height="1" width="<bean:write name="pageWidth"/>" bgcolor="#e0e0e0"></td></tr>
  <tr><td height="5" width="<bean:write name="pageWidth"/>"></td></tr>
</table>
</div>
</body>
</html>
