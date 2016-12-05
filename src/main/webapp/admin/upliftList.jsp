<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.upliftList"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.client"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="ClientListFormAdmin" paramProperty="client.clientId"><bean:write name="ClientListFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="ClientListFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="ClientListFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="ClientListFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="ClientListFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="ClientListFormAdmin" property="client.active"/></td>
  </tr>
</table>

<br/>

<bean:message key="title.upliftList"/>&nbsp;
<mmj-admin:hasAccess forward="upliftNew">
<html:link forward="upliftNew" paramId="uplift.clientId" paramName="ClientListFormAdmin" paramProperty="client.clientId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>

<%--
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.upliftDay" /></th>
    <th align="left"><bean:message key="label.upliftHour" /></th>
    <th align="left"><bean:message key="label.upliftFactor" /></th>
    <th align="left"><bean:message key="label.upliftValue" /></th>
  </tr>
  </thead>
	<logic:iterate id="uplift" name="ClientListFormAdmin" property="list">
	<bean:define id="trClass" value="uplift"/>
	<logic:notEqual name="uplift" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="upliftView">
      <html:link forward="upliftView" paramId="uplift.upliftId" paramName="uplift" paramProperty="upliftId"><bean:message name="uplift" property="upliftDayDescriptionKey"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="upliftView">
	    <bean:message name="uplift" property="upliftDayDescriptionKey"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="uplift" property="upliftHour"/>
    </td>
    <td align="left">
      <bean:write name="uplift" property="upliftFactor"/>
    </td>
    <td align="left">
      <bean:write name="uplift" property="upliftValue"/>
    </td>
  </tr>
  </logic:iterate>
</table>

<br/>
--%>

<%
int[] days = {0,1,2,3,4,5,6,7};
String[] dayNames = {"PH","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
int[] hours = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};

String[] colours = {"aqua", 
					"green", 
					"navy", 
					"silver", 
					"black", 
					"grey", 
					"olive", 
					"teal", 
					"blue", 
					"lime", 
					"purple", 
					"white", 
					"fuchsia", 
					"maroon", 
					"red", 
					"yellow"};

pageContext.setAttribute("days", days);
pageContext.setAttribute("dayNames", dayNames);
pageContext.setAttribute("hours", hours);
String upliftFactorFormat = "#0.0####";
%>

<table class="simple">
  <thead>
  <tr>
    <th align="left">&nbsp;</th>
	<logic:iterate id="dayName" name="dayNames" type="java.lang.String">
    <th align="center" colspan="2"><bean:write name="dayName" /></th>
    </logic:iterate>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
	<logic:iterate id="day" name="days" type="java.lang.Integer">
    <th align="center">F</th>
    <th align="center">V</th>
    </logic:iterate>
  </tr>
  </thead>
<logic:iterate id="hour" name="hours" type="java.lang.Integer">
  <tr>
	  <th align="left">
	    <bean:write name="hour" />
	  </th>
  <logic:iterate id="day" name="days" type="java.lang.Integer">
	<logic:iterate id="uplift" name="ClientListFormAdmin" property="list" type="com.helmet.bean.Uplift">
	  <logic:equal name="uplift" property="upliftDay" value="<%= Integer.toString(day) %>">
		<logic:equal name="uplift" property="upliftHour" value="<%= Integer.toString(hour) %>">

    <bean:define id="bgcolor" value="#ff0000"/>
    </*-- not good way to get colour --*/>
    <logic:equal name="uplift" property="upliftFactor" value="1">
	    <bean:define id="bgcolor" value="#ffffff"/>
    </logic:equal>
    <logic:equal name="uplift" property="upliftFactor" value="1.3">
	    <bean:define id="bgcolor" value="#e0e0e0"/>
    </logic:equal>
    <logic:equal name="uplift" property="upliftFactor" value="1.5">
	    <bean:define id="bgcolor" value="#c0c0c0"/>
    </logic:equal>
    <logic:equal name="uplift" property="upliftFactor" value="1.6">
	    <bean:define id="bgcolor" value="#a0a0a0"/>
    </logic:equal>
    <logic:equal name="uplift" property="upliftFactor" value="2.0">
	    <bean:define id="bgcolor" value="#808080"/>
    </logic:equal>
    <logic:greaterThan name="uplift" property="upliftMinutePeriod" value="0">
	    <bean:define id="bgcolor" value="#ffff00"/>
    </logic:greaterThan>
    
    <td align="right" bgcolor="<%= bgcolor %>">
    	<mmj-admin:hasAccess forward="upliftView">
		<html:link forward="upliftView" paramId="uplift.upliftId" paramName="uplift" paramProperty="upliftId">
    <logic:greaterThan name="uplift" property="upliftMinutePeriod" value="0"><b></logic:greaterThan>
	      <bean:write name="uplift" property="upliftFactor" format="<%= upliftFactorFormat %>"/>
    <logic:greaterThan name="uplift" property="upliftMinutePeriod" value="0"></b></logic:greaterThan>
		</html:link>
    	</mmj-admin:hasAccess>
    	<mmj-admin:hasNoAccess forward="upliftView">
    <logic:greaterThan name="uplift" property="upliftMinutePeriod" value="0"><b></logic:greaterThan>
	      <bean:write name="uplift" property="upliftFactor" format="<%= upliftFactorFormat %>"/>
    <logic:greaterThan name="uplift" property="upliftMinutePeriod" value="0"></b></logic:greaterThan>
		</mmj-admin:hasNoAccess>
	  </td>
    <td align="right">
	   <bean:write name="uplift" property="upliftValue" format="#0.0#"/>
	  </td>
		</logic:equal>
	  </logic:equal>
	</logic:iterate>
    </td>
  </logic:iterate>
  </tr>
</logic:iterate>
</table>

<%-- HORIZONTAL
<table class="simple">
  <thead>
  <tr>
    <th align="left">&nbsp;</th>
	<logic:iterate id="hour" name="hours" type="java.lang.Integer">
    <th align="center" colspan="2"><bean:write name="hour" /></th>
    </logic:iterate>
  </tr>
  <tr>
    <th align="left">&nbsp;</th>
	<logic:iterate id="hour" name="hours" type="java.lang.Integer">
    <th align="center">F</th>
    <th align="center">V</th>
    </logic:iterate>
  </tr>
  </thead>
<logic:iterate id="day" name="days" type="java.lang.Integer">
  <tr>
	<th align="left">
	  <%= dayNames[day] %>
	</th>
  <logic:iterate id="hour" name="hours" type="java.lang.Integer">
	<logic:iterate id="uplift" name="ClientListFormAdmin" property="list" type="com.helmet.bean.Uplift">
	  <logic:equal name="uplift" property="upliftDay" value="<%= Integer.toString(day) %>">
		<logic:equal name="uplift" property="upliftHour" value="<%= Integer.toString(hour) %>">
    <td align="right">
    	<mmj-admin:hasAccess forward="upliftView">
		<html:link forward="upliftView" paramId="uplift.upliftId" paramName="uplift" paramProperty="upliftId">
	      <bean:write name="uplift" property="upliftFactor" format="#0.0#"/>
		</html:link>
    	</mmj-admin:hasAccess>
    	<mmj-admin:hasNoAccess forward="upliftView">
	      <bean:write name="uplift" property="upliftFactor" format="#0.0#"/>
		</mmj-admin:hasNoAccess>
	</td>
    <td align="right">
	      <bean:write name="uplift" property="upliftValue" format="#0.0#"/>
	</td>
		</logic:equal>
	  </logic:equal>
	</logic:iterate>
    </td>
  </logic:iterate>
  </tr>
</logic:iterate>
</table>
--%>
