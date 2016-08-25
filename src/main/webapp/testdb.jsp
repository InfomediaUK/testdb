<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test DB</title>
</head>
<body>
<h1>Test DB!!!!</h1>
<%
String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
String userName = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
String password = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");
String databaseName = "testdb";
String url = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName;
String message = "Nothing Yet...";
%>
Host: <%= host %><br />
Port: <%= port %><br />
UserName: <%= userName %><br />
Password: <%= password %><br />
URL: <%= url %><br />
<%
try
{
  Connection connection = DriverManager.getConnection(url, userName, password);
  if (connection == null)
  {
    message = "Null Connection";
  }
  connection.close();
}
catch (SQLException e)  
{
  message = e.getMessage();
}
%>
Message: <%= message %><br />
</body>
</html>