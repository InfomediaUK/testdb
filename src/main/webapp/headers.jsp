<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<B>Request Method: </B>
<%= request.getMethod() %>
<BR>
<B>Request URI: </B>
<%= request.getRequestURI() %>
<BR>
<B>Request Protocol: </B>
<%= request.getProtocol() %>
<BR><BR>
<TABLE BORDER=1 ALIGN=CENTER>
<TR BGCOLOR="#FFAD00">
<TH>Header Name</TH>
<TH>Header Value</TH>
<%
    java.util.Enumeration headerNames = request.getHeaderNames();
    while(headerNames.hasMoreElements()) {
      String headerName = (String)headerNames.nextElement();
%>
<TR><TD>
<%= headerName %>
</TD>
<TD>
<%= request.getHeader(headerName) %>
</TD>
</TR>
<%
    }
%>
</TABLE>

<script type="text/javascript">
<!--

try
{
var ax = new ActiveXObject("WScript.Network");
document.write('User: ' + ax.UserName + '<br />');
document.write('Computer: ' + ax.ComputerName + '<br />');
}
catch (e)
{
document.write('Permission to access computer name is denied' + '<br />');
}

//-->
</script>
