<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.reEnterPwdEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="reEnterPwdEditProcess.do" focus="reEnterPwd.name">

<html:hidden property="reEnterPwd.reEnterPwdId"/>
<html:hidden property="reEnterPwd.noOfChanges"/>

<table>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="reEnterPwd.name"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
