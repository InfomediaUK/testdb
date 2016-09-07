<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert definition="company-template">
  <tiles:put name="body" value="/user-form-body.jsp"/>
</tiles:insert>