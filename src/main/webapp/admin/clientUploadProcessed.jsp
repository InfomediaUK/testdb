<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
com.helmet.application.FileHandler fh = com.helmet.application.FileHandler.getInstance();
String outputFilePath = request.getContextPath() + fh.getTempFileFolder() + java.io.File.separator + "helmet.txt";
%>
<a href="<%= outputFilePath %>" target="sqlWindow">Create Client SQL File</a>
