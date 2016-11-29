<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="OrderStaffFormAgy" property="shifts">
	<br/>
	<br/>
	<bean:message key="error.noShifts"/>
</logic:empty>
<logic:notEmpty name="OrderStaffFormAgy" property="shifts">
	<%-- focusControl stuff --%>
	<bean:define id="focusControl" value="shift0"/>
	<logic:greaterThan name="OrderStaffFormAgy" property="noOfDates" value="1">
	  <bean:define id="focusControl" value="bookingDates[0].shiftId"/>
	</logic:greaterThan>
  <bean:define id="bookingDates" name="OrderStaffFormAgy" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
  <%
  com.helmet.bean.BookingDate minDate = bookingDates[0];
  com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
  pageContext.setAttribute("minDate", minDate);
  pageContext.setAttribute("maxDate", maxDate);
  %>
	<html:form action="/orderStaff6.do" focus="<%= focusControl %>" onsubmit="return singleSubmit();">
	<html:hidden property="page" value="5"/>
	<table>
	  <tr>
	    <td align="left" valign="middle" width="250">
	      <logic:empty name="OrderStaffFormAgy" property="booking.bookingId">
	      <bean:message key="title.newBooking"/>
	      </logic:empty>
	      <logic:notEmpty name="OrderStaffFormAgy" property="booking.bookingId">
	      <bean:message key="title.editBooking"/>
	      </logic:notEmpty>
	      -&nbsp;<bean:message key="title.orderStaff5"/>
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
				<logic:equal name="OrderStaffFormAgy" property="undefinedShift" value="true">
				  <table class="simple">
					  <tr>
					    <th class="label" align="left" colspan="2">
					      Undefined Shift:
					    </td>
					  </tr>
					  <tr>
					    <th class="label" align="left">
					      <bean:message key="label.totalHours" />
					    </td>
					    <td align="right">
					      <html:text property="totalHours" size="7" tabindex="1" />
					    </td>
					  </tr>
					</table>
				</logic:equal>
				<logic:equal name="OrderStaffFormAgy" property="noOfDates" value="1">
				  <logic:equal name="OrderStaffFormAgy" property="definedShifts" value="true">
						<table class="simple" width="75%">
						<logic:iterate id="shift" name="OrderStaffFormAgy" property="shifts" indexId="shiftIndex">
				      <logic:notEqual name="shift" property="undefined" value="true">
						  <tr>
						    <th class="label">
						      <table width="100%">
						        <tr>
	                    <td width="35%">
	                      <bean:write name="shift" property="name"/>
	                    </td>
	                    <td width="35%">
	                      <bean:write name="shift" property="startTime" format="HH:mm"/>-<bean:write name="shift" property="endTime" format="HH:mm"/>
	                    </td>
	                    <td width="15%" align="right">
	                      <bean:write name="shift" property="noOfHours" format="#0.00"/>
	                    </td>
	                    <td width="15%" align="right">
	                      <bean:write name="shift" property="breakNoOfHours" format="#0.00"/>
	                    </td>
						        </tr>
						      </table>
						    </th>
						    <td>
						      <html:radio tabindex="1" property='<%= "bookingDates[" + 0 + "].shiftId" %>' idName="shift" value="shiftId" styleId='<%= "shift" + shiftIndex %>' />
						    </td>
						  </tr>
						  </logic:notEqual>
						</logic:iterate>
						</table>
				  </logic:equal>
				</logic:equal>
				<%-- multiple dates --%>
				<logic:greaterThan name="OrderStaffFormAgy" property="noOfDates" value="1">
				  <logic:equal name="OrderStaffFormAgy" property="definedShifts" value="true">
						<table class="simple">
						<logic:iterate id="bookingDate" name="OrderStaffFormAgy" property="bookingDates" indexId="bookingDateIndex">
						  <tr>
						    <th class="label" align="left">
						<fmt:formatDate value="${bookingDate.bookingDate}" pattern="EEE, dd MMM yyyy" />
						    </th>
						    <td align="left">
						<html:select tabindex="1" property='<%= "bookingDates[" + bookingDateIndex + "].shiftId" %>'>
							<logic:iterate id="shift" name="OrderStaffFormAgy" property="shifts" indexId="shiftIndex" type="com.helmet.bean.Shift">
				        <logic:notEqual name="shift" property="undefined" value="true">
						    <bean:define id="shiftId"  name="shift" property="shiftId" type="java.lang.Integer"/>
							  <html:option value="<%= shiftId.toString() %>">
							  <bean:write name="shift" property="name"/>
						    <bean:write name="shift" property="startTime" format="HH:mm"/>-<bean:write name="shift" property="endTime" format="HH:mm"/>
							  <bean:write name="shift" property="noOfHours" format="#0.00"/>
							  <bean:write name="shift" property="breakNoOfHours" format="#0.00"/>
							  </html:option>
							  </logic:notEqual>
							</logic:iterate>
						<%--
						  <bean:define id="shifts" name="OrderStaffFormAgy" property="shifts"/>
						  <html:options collection="shifts" labelProperty="name" property="shiftId"/>
						--%>
						</html:select>
						    </td>
						  </tr>
						</logic:iterate>
						</table>
				  </logic:equal>
				</logic:greaterThan>
    </td>
    <td width="50%" valign="top">
      <table class="simple">
        <tr>
          <th class="label">
		        <bean:message key="label.client"/>
          </th>
          <td>
            <bean:write name="OrderStaffFormAgy" property="client.name"/>&nbsp;(<bean:write name="OrderStaffFormAgy" property="client.code"/>)
          </td>
        </tr>
        <tr>
          <th class="label">
            <bean:message key="label.reason"/>
          </th>
          <td>
            <bean:write name="OrderStaffFormAgy" property="reasonForRequest.name"/>&nbsp;-
            <bean:write name="OrderStaffFormAgy" property="reasonForRequestText"/>
          </td>
        </tr>
        <tr>
          <th class="label">
            <bean:message key="label.location"/>
          </th>
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
          </th>
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
          </th>
          <td>
            <bean:write name="OrderStaffFormAgy" property="noOfDates"/>
          </td>
        </tr>
      </table>
	    </td>
	  </tr>
	</table>    
	</html:form>
</logic:notEmpty>