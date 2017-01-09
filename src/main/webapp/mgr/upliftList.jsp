<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
	<td align="left" valign="middle" class="title">
	<bean:message key="title.upliftList"/>
	</td>
  </tr>
</table>

<logic:notPresent name="ListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ListFormMgr" property="list">
<logic:empty name="ListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ListFormMgr" property="list">

<%
int[] days = {0,1,2,3,4,5,6,7};
String[] dayNames = {"PH","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
int[] hours = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
pageContext.setAttribute("days", days);
pageContext.setAttribute("dayNames", dayNames);
pageContext.setAttribute("hours", hours);
%>

<table class="simple">
  <thead>
  <tr>
    <th align="left">&nbsp;</th>
	<logic:iterate id="dayName" name="dayNames" type="java.lang.String">
    <th align="center" id="d<bean:write name="dayName" />"><bean:write name="dayName" /></th>
    </logic:iterate>
  </tr>
  </thead>
<logic:iterate id="hour" name="hours" type="java.lang.Integer">
  <tr>
	<th align="right" id="h<bean:write name="hour" />">
	  &nbsp;<bean:write name="hour" />&nbsp;
	</th>
  <logic:iterate id="day" name="days" type="java.lang.Integer">
	<logic:iterate id="uplift" name="ListFormMgr" property="list" type="com.helmet.bean.Uplift">
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
    
    <td align="right" bgcolor="<%= bgcolor %>" 
    onmouseover="document.getElementById('d<%= dayNames[day] %>').style.color='#cc0000'; 
                 document.getElementById('h<%= hour %>').style.color='#cc0000'; 
                 style.color='#cc0000';" 
    onmouseout="document.getElementById('d<%= dayNames[day] %>').style.color='#000000'; 
                document.getElementById('h<%= hour %>').style.color='#000000'; 
                style.color='#000000'"
    >
    
      &nbsp;<bean:write name="uplift" property="upliftFactor" format="#0.00"/>&nbsp;
	</td>
		</logic:equal>
	  </logic:equal>
	</logic:iterate>
    </td>
  </logic:iterate>
  </tr>
</logic:iterate>
</table>

</logic:notEmpty> 
</logic:present>