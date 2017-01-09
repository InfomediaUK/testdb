<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<%--

<div align="right" style="color:#e0e0e0">
<logic:equal name="BookingViewFormMgr" property="booking.active" value="true">
<logic:equal name="BookingViewFormMgr" property="booking.canBeEdited" value="true">
<mmj-mgr:hasAccess forward="bookingEdit">
  <html:link forward="bookingEdit" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId"><bean:message key="link.edit"/></html:link>
  &bull;  
</mmj-mgr:hasAccess>
</logic:equal>
<logic:equal name="BookingViewFormMgr" property="booking.canBeCancelled" value="true">
<mmj-mgr:hasAccess forward="bookingCancel">
  <html:link forward="bookingCancel" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId"><bean:message key="link.cancel"/></html:link>
</mmj-mgr:hasAccess> 
</logic:equal>
<logic:equal name="BookingViewFormMgr" property="booking.canBeSubmitted" value="true">
<mmj-mgr:hasAccess forward="bookingSubmit">
  &bull;  
  <html:link forward="bookingSubmit" paramId="booking.bookingId" paramName="BookingViewFormMgr" paramProperty="booking.bookingId"><bean:message key="link.submit"/></html:link>
</mmj-mgr:hasAccess> 
</logic:equal>
</logic:equal>
</div>

--%>