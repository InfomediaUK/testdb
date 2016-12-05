<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:errors />
<html:form action="/clientUploadProcess.do" enctype="multipart/form-data" >
<bean:message key="label.uploadFile" />
<html:file property="uploadFile" size="30" />
<html:submit/>
</html:form>
<br/>

<%
com.helmet.application.FileHandler fh = com.helmet.application.FileHandler.getInstance();
String cleardownSQLFilePath = request.getContextPath() + fh.getTempFileFolder() + java.io.File.separator + "cleardown.txt";
String cleardownSequencesFilePath = request.getContextPath() + fh.getTempFileFolder() + java.io.File.separator + "cleardownsequences.txt";
%>
<a href="<%= cleardownSQLFilePath %>" target="sqlWindow">Cleardown SQL</a>
<a href="<%= cleardownSequencesFilePath %>" target="sqlWindow">Cleardown Sequences SQL</a>
