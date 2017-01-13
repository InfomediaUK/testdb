<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<br/>
<br/>
<%--= session.getId() --%>
<%-- should be a tag --%>
<%-- should be a tag --%>
<%-- HAS TO BE KEPT CHECKED OUT FROM CVS --%>
<%
String major = "1";
String minor = "7";
String revision = "01";
String build = "501"; // DO NOT EDIT BUILD. IT'S CHANGED BY ANT.
String time = "17 11 2016 19:19:22"; // DO NOT EDIT TIME. IT'S CHANGED BY ANT.
String user = "infomedia"; // DO NOT EDIT USER. IT'S CHANGED BY ANT.
%>    
<%= major %>.<%= minor %>.<%= revision %>.<%= build %>

<br/> 
<br/>

<script type="text/javascript">
<!--

/*
try
{
var ax = new ActiveXObject("WScript.Network");
document.write(ax.UserName);
document.write('&nbsp;');
document.write(ax.ComputerName);
}
catch (e)
{
// Permission to access computer name is denied
}
*/

//-->
</script>

<%= request.getRemoteAddr() %>

<br/>
<br/>

<%--
<% 
java.util.Enumeration enames = session.getAttributeNames();
while (enames.hasMoreElements()) {
   String name = (String) enames.nextElement();
   Object value = session.getAttribute(name);
   String valueAsString = "" + value;
%>
<%= name + " " + value.getClass().getName() %>   
<br/>
<%
}
%>
<br/>
<br/>
--%>

<%--
<%= Runtime.getRuntime().maxMemory() %>
<%= Runtime.getRuntime().totalMemory() %>
<%= Runtime.getRuntime().freeMemory() %>
<br/>
<br/>
--%>

<%--
  String geoTrustImage = request.getContextPath() + "/images/quickssl_static.gif";
<img src="<%= geoTrustImage %>" width="115" height="55" />
--%>


