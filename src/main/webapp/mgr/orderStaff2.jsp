<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<logic:empty name="OrderStaffFormMgr" property="list">
<br/>
<br/>
<bean:message key="error.noLocations"/>
</logic:empty>
<logic:notEmpty name="OrderStaffFormMgr" property="list">
<html:form action="/orderStaff3.do" focus="location0" onsubmit="return singleSubmit();"> <%-- onsubmit="return validateOrderStaffForm(this);"> --%> 
<html:hidden property="page" value="2"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <logic:empty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.newBooking"/>
      </logic:empty>
      <logic:notEmpty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.editBooking"/>
      </logic:notEmpty>
      -&nbsp;<bean:message key="title.orderStaff2"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="orderStaffButtons.jsp" flush="true" />
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<bean:define id="clientName" value=""/>
<bean:define id="siteName" value=""/>
<table class="radio">
<logic:iterate id="location" name="OrderStaffFormMgr" property="list" indexId="locationIndex">
<logic:notEqual name="location" property="clientName" value="<%= clientName %>">
  <bean:define id="clientName" name="location" property="clientName" type="java.lang.String"/>
  <bean:define id="siteName" value=""/>
</logic:notEqual>
<logic:notEqual name="location" property="siteName" value="<%= siteName %>">
  <tr>
    <td colspan="3">
		  <bean:write name="location" property="siteName"/>
		</td>
  </tr>
  <bean:define id="siteName" name="location" property="siteName" type="java.lang.String"/>
</logic:notEqual>
  <tr>
    <td>
      <html:img src="images/indent.gif" width="15" height="9"/>
    </td>
    <td>
      <html:radio tabindex="1" property="location.locationId" idName="location" value="locationId" styleId='<%= "location" + locationIndex %>'/>
    </td>
    <td>
      <label for="<%= "location" + locationIndex %>">
	  <bean:write name="location" property="name"/>
  	  <logic:notEmpty name="location" property="description">
  	  (<bean:write name="location" property="description"/>)
  	  </logic:notEmpty>
  	  </label>
    </td>
  </tr>  
</logic:iterate>
</table>
<%--
	<!-- Begin Validator Javascript Function-->
	<html:javascript formName="OrderStaffFormMgr" page="1"/>
	<!-- End of Validator Javascript Function-->
--%>
</html:form>
</logic:notEmpty>