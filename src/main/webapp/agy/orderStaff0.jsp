<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ 
taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ 
taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="OrderStaffFormAgy" property="list">
<br/>
<br/>
<bean:message key="error.noClients"/>
</logic:empty>
<logic:notEmpty name="OrderStaffFormAgy" property="list">
<html:form action="/orderStaff1.do" focus="c0" onsubmit="return singleSubmit();">
<html:hidden property="page" value="0"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <logic:empty name="OrderStaffFormAgy" property="booking.bookingId">
      <bean:message key="title.newBooking"/>
      </logic:empty>
      <logic:notEmpty name="OrderStaffFormAgy" property="booking.bookingId">
      <bean:message key="title.editBooking"/>
      </logic:notEmpty>
      -&nbsp;<bean:message key="title.orderStaff0"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="orderStaffButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<table border="0" width="100%">
  <tr>
    <td width="50%">
      <table class="simple" border="1">
        <tr>
          <th class="label">
            <bean:message key="label.client"/>
          </th>
          <td>
            <bean:define id="clients" name="OrderStaffFormAgy" property="list"/>
            <html:select name="OrderStaffFormAgy" property="client.clientId">
              <html:options collection="clients" labelProperty="listName" property="clientId" />
            </html:select>
          </td>
        </tr>
      </table>
		</td>
		<td width="50%">
		  &nbsp;
		</td>
  </tr>
</table>		
</html:form>
</logic:notEmpty>