<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<style>
#siteUnavailable {
text-align:center;
width:100%;
background-color:#e0e0e0;

BORDER-TOP: #cc0000 1px solid;
BORDER-LEFT: #cc0000 1px solid;
BORDER-RIGHT: #cc0000 1px solid;
BORDER-BOTTOM: #cc0000 1px solid;

padding-top:0.5em;
padding-bottom:0.5em;

}
#siteUnavailableContainer {
width:100%;
padding-top:0em;
padding-bottom:0.5em;
}
</style>
<%
String xmlFile = "file:///" + com.helmet.application.FileHandler.getInstance().getTempFileLocation() + "/../admin/siteUnavailable.xml";
pageContext.setAttribute("xmlFile", xmlFile);
String xslFile = "file:///" + com.helmet.application.FileHandler.getInstance().getTempFileLocation() + "/../admin/siteUnavailable.xsl";
pageContext.setAttribute("xslFile", xslFile);
%>
<c:import var="xml" url="${xmlFile}"/>
<c:import var="xsl" url="${xslFile}"/>
<x:transform xml="${xml}" xslt="${xsl}"/>
