<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%
String clientIndexLetter = (String)session.getAttribute("clientIndexLetter");
clientIndexLetter = clientIndexLetter == null ? "A" : clientIndexLetter;
String indexLetter = request.getParameter("indexLetter") == null ? clientIndexLetter : request.getParameter("indexLetter");
session.setAttribute("clientIndexLetter", indexLetter);
%>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
    <bean:define id="list" name="ListFormAgy" property="list" type="java.util.List"/>
		<bean:message key="title.clientList"/>&nbsp;(<%=list.size() %>)&nbsp;<%= indexLetter %>
		</td>
		<mmj-agy:hasAccess forward="clientNew">
    <html:form action="/clientNew.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
		</mmj-agy:hasAccess>
  </tr>
</table>

<%-- tabs --%>
<bean:parameter id="tab" name="tab" value="<%= indexLetter %>"/>

<jsp:include page="clientListTabs.jsp" flush="true">
  <jsp:param name="tab" value="<%= tab %>"/>
</jsp:include>

<logic:notPresent name="ListFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ListFormAgy" property="list">
<logic:empty name="ListFormAgy" property="list">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ListFormAgy" property="list">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.client"/></th>
    <th align="left"><bean:message key="label.address"/></th>
    <th align="left"><bean:message key="label.code"/></th>
  </tr>
  </thead>
  <logic:iterate id="clientAgency" name="ListFormAgy" property="list" indexId="clientAgencyIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
			<mmj-agy:hasAccess forward="clientView">
  	  <html:link forward="clientView" paramId="client.clientId" paramName="clientAgency" paramProperty="clientId">
	      <bean:write name="clientAgency" property="clientName"/>
	 	  </html:link>
			</mmj-agy:hasAccess>
			<mmj-agy:hasNoAccess forward="clientView">
      <bean:write name="clientAgency" property="clientName"/>
			</mmj-agy:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="clientAgency" property="clientAddress.fullAddress"/>
    </td>
    <td align="left">
      <bean:write name="clientAgency" property="clientCode"/>
    </td>
  </tr> 
  </logic:iterate>
</table> 
</logic:notEmpty> 
</logic:present>