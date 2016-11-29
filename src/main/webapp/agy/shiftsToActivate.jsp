<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="showCheckbox" value="false"/>
<bean:define id="formAction" value="/home.do"/>
<mmj-agy:hasAccess forward="bookingDatesActivate">
<bean:define id="showCheckbox" value="true"/>
<bean:define id="formAction" value="/bookingDatesActivate.do"/>
</mmj-agy:hasAccess>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="<%= formAction %>" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:define id="list" name="ShiftListFormAgy" property="list" type="java.util.List"/>
<bean:message key="title.shiftsToActivate"/>&nbsp;(<%=list.size() %>)
    </td>
<logic:present name="ShiftListFormAgy" property="list">
  <logic:notEmpty name="ShiftListFormAgy" property="list">
		  <mmj-agy:hasAccess forward="bookingDatesActivate">
		  <td align="right" valign="middle" width="75">
		    <html:submit styleClass="titleButton"><bean:message key="button.activate"/></html:submit>
		  </td>
		  </mmj-agy:hasAccess>
  </logic:notEmpty>
</logic:present>
  </tr>
</table>

<jsp:include page="shiftsInclude.jsp" flush="true">
  <jsp:param name="theFormAgy" value="ShiftListFormAgy"/>
  <jsp:param name="showCheckbox" value="<%= showCheckbox %>"/>
</jsp:include>

</html:form>
 