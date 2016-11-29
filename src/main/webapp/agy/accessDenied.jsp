<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.helmet.application.Constants"%>

<% 
  String requestedUrl =  (String)session.getAttribute(Constants.REQUESTED_URL);
%>

<%= requestedUrl %>
<br/>

Either access denied

<br/>
<br/>

or

<br/>
<br/>

404 page not found but because below /admin - access denied is assumed

<br/>
<br/>

so need to tell the user to check the url/address blah and give them a link

<br/>
<br/>

back to admin home/index