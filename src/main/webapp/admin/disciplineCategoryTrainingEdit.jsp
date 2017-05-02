<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.disciplineCategoryTrainingEdit"/>

<br/>
<br/>

<html:errors/>

<html:form action="disciplineCategoryTrainingEditProcess.do" focus="disciplineCategoryTraining.feePerShift" >

<html:hidden property="disciplineCategoryTraining.disciplineCategoryTrainingId"/>
<html:hidden property="disciplineCategoryTraining.noOfChanges"/>

<table>
  <tr>
    <td align="left"></td>
    <td align="left">
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
