<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<logic:empty name="OrderStaffCopyFormAgy" property="shifts">
  <br/>
  <br/>
  <bean:message key="error.noShifts"/>
</logic:empty>

<logic:notEmpty name="OrderStaffCopyFormAgy" property="shifts">
	<%-- focusControl stuff --%>
	<bean:define id="focusControl" value="shift0"/>
	<logic:greaterThan name="OrderStaffCopyFormAgy" property="noOfDates" value="1">
	  <bean:define id="focusControl" value="bookingDates[0].shiftId"/>
	</logic:greaterThan>
	<bean:define id="bookingDates" name="OrderStaffCopyFormAgy" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
	<%
	com.helmet.bean.BookingDate minDate = bookingDates[0];
	com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
	pageContext.setAttribute("minDate", minDate);
	pageContext.setAttribute("maxDate", maxDate);
	%>
	<table>
	  <tr>
	    <td align="left" valign="middle" width="250">
	      <bean:message key="title.orderStaffCopyStep5"/>
	      -&nbsp;<bean:message key="title.orderStaffCopy5"/>
	    </td>
	    <td align="left" valign="top">
	<html:form action="/orderStaffCopy6.do" onsubmit="return singleSubmit();">
	<html:hidden property="page" value="5"/>
	<jsp:include page="orderStaffCopyButtons.jsp" flush="true" >
	  <jsp:param name="backButtonTabIndex" value="1" />
	  <jsp:param name="nextButtonTabIndex" value="2" />
	  <jsp:param name="completeButtonTabIndex" value="3" />
	</jsp:include>
	    </td>
	  </tr>
	</table>
	<hr/>
	<html:errors />
	<table border="0" width="100%">
	  <tr>
	    <td width="50%" valign="top">
	        <logic:equal name="OrderStaffCopyFormAgy" property="undefinedShift" value="true">
	          <table class="simple">
	            <tr>
	              <th class="label" align="left" colspan="2">
	                Undefined Shift:
	              </th>
	            </tr>
	            <tr>
	              <th class="label" align="left">
	                <bean:message key="label.totalHours" />
	              </th>
	              <td align="right">
	                <html:text property="totalHours" size="7" tabindex="1" />
	              </td>
	            </tr>
	          </table>
	        </logic:equal>
	        <logic:equal name="OrderStaffCopyFormAgy" property="noOfDates" value="1">
	          <logic:equal name="OrderStaffCopyFormAgy" property="definedShifts" value="true">
	            <table class="simple" width="75%">
	            <logic:iterate id="shift" name="OrderStaffCopyFormAgy" property="shifts" indexId="shiftIndex">
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
							      <html:radio tabindex="1" name="OrderStaffCopyFormAgy" property='<%= "bookingDates[" + 0 + "].shiftId" %>' idName="shift" value="shiftId" styleId='<%= "shift" + shiftIndex %>' />
	                </td>
	              </tr>
	              </logic:notEqual>
	            </logic:iterate>
	            </table>
	          </logic:equal>
	        </logic:equal>
	        <%-- multiple dates --%>
	        <logic:greaterThan name="OrderStaffCopyFormAgy" property="noOfDates" value="1">
	          <logic:equal name="OrderStaffCopyFormAgy" property="definedShifts" value="true">
	            <table class="simple">
	            <logic:iterate id="bookingDate" name="OrderStaffCopyFormAgy" property="bookingDates" indexId="bookingDateIndex">
	              <tr>
	                <th class="label" align="left">
	            <fmt:formatDate value="${bookingDate.bookingDate}" pattern="EEE, dd MMM yyyy" />
	                </th>
	                <td align="left">
							<html:select tabindex="1" name="OrderStaffCopyFormAgy" property='<%= "bookingDates[" + bookingDateIndex + "].shiftId" %>'>
	              <logic:iterate id="shift" name="OrderStaffCopyFormAgy" property="shifts" indexId="shiftIndex" type="com.helmet.bean.Shift">
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
							</html:select>
	                </td>
	              </tr>
	            </logic:iterate>
	            </table>
	          </logic:equal>
	        </logic:greaterThan>
	    </td>
	    <td width="50%" valign="top">
	      <jsp:include page="orderStaffCopyFeedback.jsp" flush="true" >
	        <jsp:param name="formName" value="OrderStaffCopyFormAgy" />
	      </jsp:include>
	    </td>
	  </tr>
	</table>    
	</html:form>
</logic:notEmpty>    