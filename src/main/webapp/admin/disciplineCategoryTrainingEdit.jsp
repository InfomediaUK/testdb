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
    <td align="left"><bean:message key="label.mandatory"/></td>
    <td align="left">
      <html:radio property="disciplineCategoryTraining.mandatory" value="true" styleId="mandatory" />
      <label for="mandatory">
        <bean:message key="label.yes" />
      </label>
      <html:radio property="disciplineCategoryTraining.mandatory" value="false" styleId="mandatory" />
      <label for="mandatory">
        <bean:message key="label.no" />
      </label>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>
