<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="OrderStaffFormAgy" property="list">
<br/>
<br/>
<bean:message key="error.noExpenses"/>
</logic:empty>
<logic:notEmpty name="OrderStaffFormAgy" property="list">
  <bean:define id="bookingDates" name="OrderStaffFormAgy" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
	<%
	com.helmet.bean.BookingDate minDate = bookingDates[0];
	com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
	pageContext.setAttribute("minDate", minDate);
	pageContext.setAttribute("maxDate", maxDate);
	%>
	<html:form action="/orderStaff9.do" focus="expense0" onsubmit="return singleSubmit();">
		<html:hidden property="page" value="8"/>
		<html:hidden property="selectedExpenses" value="0"/>
		<table>
		  <tr>
		    <td align="left" valign="middle" width="250">
		      <logic:empty name="OrderStaffFormAgy" property="booking.bookingId">
		      <bean:message key="title.newBooking"/>
		      </logic:empty>
		      <logic:notEmpty name="OrderStaffFormAgy" property="booking.bookingId">
		      <bean:message key="title.editBooking"/>
		      </logic:notEmpty>
		      -&nbsp;<bean:message key="title.orderStaff8"/>
		    </td>
		    <td align="left" valign="top">
		      <jsp:include page="orderStaffButtons.jsp" flush="true" />
		    </td>
		  </tr>
		</table>
		<hr/>
		<html:errors />
		<table border="0" width="100%">
		  <tr>
		    <td width="50%" valign="top">
		      <logic:notEmpty name="OrderStaffFormAgy" property="list">
		        <table class="simple">
						<logic:iterate id="expense" name="OrderStaffFormAgy" property="list" indexId="expenseIndex">
						  <tr>
						    <th class="label">
									<bean:write name="expense" property="name"/>
									<logic:notEmpty name="expense" property="description">(<bean:write name="expense" property="description"/>)</logic:notEmpty>
						    </th>
						    <td>
									<html:multibox tabindex="1" property="selectedExpenses" styleId='<%= "expense" + expenseIndex %>'>
									  <bean:write name="expense" property="expenseId"/>
									</html:multibox>
							  </td>
							</tr>
						</logic:iterate>
						</table>
					</logic:notEmpty>
					<br/>
					<table class="simple" width="100%">
					  <tr>
					    <th align="left" class="label" nowrap="true">
					      <bean:message key="label.comment"/>&nbsp;
					    </th>
					    <td align="left" width="100%">
					      <html:textarea tabindex="1" property="expensesText" cols="65" rows="3"/>
					    </td>
					  </tr>
					</table>
		    </td>
		    <td class="simple" width="50%" valign="top">
		      <table class="simple">
		        <tr>
		          <th class="label">
		            <bean:message key="label.client"/>
		          </td>
		          <td>
		            <bean:write name="OrderStaffFormAgy" property="client.name"/>&nbsp;(<bean:write name="OrderStaffFormAgy" property="client.code"/>)
		          </td>
		        </tr>
		        <tr>
		          <th class="label">
		            <bean:message key="label.reason"/>
		          </td>
		          <td>
		            <bean:write name="OrderStaffFormAgy" property="reasonForRequest.name"/>&nbsp;-
		            <bean:write name="OrderStaffFormAgy" property="reasonForRequestText"/>
		          </td>
		        </tr>
		        <tr>
		          <th class="label">
		            <bean:message key="label.location"/>
		          </td>
		          <td>
						    <bean:write name="OrderStaffFormAgy" property="location.name"/>,
						    <bean:write name="OrderStaffFormAgy" property="location.siteName"/>
					      <logic:notEmpty name="OrderStaffFormAgy" property="location.description">
					  	    (<bean:write name="OrderStaffFormAgy" property="location.description"/>)
					      </logic:notEmpty>
		          </td>
		        </tr>
		        <tr>
		          <th class="label" valign="top">
		            <bean:message key="label.jobProfile"/>
		          </td>
		          <td>
		            <bean:write name="OrderStaffFormAgy" property="jobProfile.name"/>
	              (<bean:write name="OrderStaffFormAgy" property="jobProfile.jobFamilyCode"/>.<bean:write name="OrderStaffFormAgy" property="jobProfile.jobSubFamilyCode"/>.<bean:write name="OrderStaffFormAgy" property="jobProfile.code"/>)
		          </td>
		        </tr>
					  <tr>
					    <th align="left" class="label">
					      <bean:message key="label.start"/>
					    </th>
					    <td align="left">
					      <bean:write name="minDate" property="bookingDate" formatKey="format.longDateFormat"/>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label">
					      <bean:message key="label.end"/>
					    </th>
					    <td align="left">
					      <bean:write name="maxDate" property="bookingDate" formatKey="format.longDateFormat"/>
					    </td>
					  </tr>
		        <tr>
		          <th align="left" class="label">
		            <bean:message key="label.days"/>
		          </td>
		          <td>
		            <bean:write name="OrderStaffFormAgy" property="noOfDates"/>
		          </td>
		        </tr>
					  <tr>
					    <th align="left" class="label">
					      <bean:message key="label.totalHours" />
					    </th>
					    <td align="left">
					      <bean:write name="OrderStaffFormAgy" property="totalHours" format="#0.00"/>
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label">
								<bean:message key="label.rrpRate"/>
					    </th>
					    <td align="left">
								<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="hourlyRate" format="#,##0.00" />
					    </td>
					  </tr>
					  <tr>
					    <th align="left" class="label">
								<bean:message key="label.rrpValue"/>
					    </th>
					    <td align="left">
								<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="rrp" format="#,##0.00" />
					    </td>
					  </tr>
<%/* START OF SPECIFICS */%>

<bean:define id="firstSpecific" value="true"/>

<logic:equal name="OrderStaffFormAgy" property="cvRequired" value="true">
<logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.cvRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.cvRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="OrderStaffFormAgy" property="interviewRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.interviewRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.interviewRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="OrderStaffFormAgy" property="canProvideAccommodation" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.canProvideAccommodation" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.canProvideAccommodation" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal><logic:equal name="OrderStaffFormAgy" property="carRequired" value="true"><logic:equal name="firstSpecific" value="true"><tr><th align="left" class="label"><bean:message key="label.specifics" /></th><td><bean:message key="label.carRequired" /></logic:equal><logic:notEqual name="firstSpecific" value="true">,&nbsp;<bean:message key="label.carRequired" /></logic:notEqual><bean:define id="firstSpecific" value="false"/></logic:equal>
<logic:equal name="firstSpecific" value="false"></td></tr></logic:equal>

<%/* END OF SPECIFICS */%>
		      </table>
		    </td>
		  </tr>
		</table>    
	</html:form>
</logic:notEmpty>