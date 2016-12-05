<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.visaTypeEdit"/>
<br/>
<br/>
<html:errors/>
<html:form action="visaTypeEditProcess.do" focus="visaType.name">
	<html:hidden name="VisaTypeFormAdmin" property="visaType.visaTypeId"/>
	<html:hidden name="VisaTypeFormAdmin" property="visaType.noOfChanges"/>
	<table>
	  <tr>
	    <td align="left"><bean:message key="label.name"/></td>
	    <td align="left"><html:text name="VisaTypeFormAdmin" property="visaType.name" size="60" /></td>
	  </tr>
	  <tr>
	    <td align="left"><bean:message key="label.code"/></td>
	    <td align="left"><html:text name="VisaTypeFormAdmin" property="visaType.code" size="60" /></td>
	  </tr>
	  <tr>
	    <td align="left"><bean:message key="label.displayOrder"/></td>
	     <td align="left"><html:text name="VisaTypeFormAdmin" property="visaType.displayOrder" size="10" /></td>
	   </tr>
	  <tr>
	    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
	  </tr>
	</table>
</html:form>

