<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<bean:parameter id="isView" name="isView" value="false"/>
<bean:parameter id="isSubmit" name="isSubmit" value="false"/>
<bean:parameter id="isCancel" name="isCancel" value="false"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
    <%/* VIEW */%>
    <logic:equal name="isView" value="true">
		<td align="left" valign="middle" class="title">
  		<bean:message key="title.view"/>
      <bean:message name="BookingViewFormMgr" property="booking.statusDescriptionKey"/>
  		<bean:message key="title.bookingNo"/>
      <bean:write name="BookingViewFormMgr" property="booking.bookingId" format="#000"/>
      <bean:write name="BookingViewFormMgr" property="booking.jobProfileName"/>
		</td>
    <logic:equal name="BookingViewFormMgr" property="booking.canBeEdited" value="true">
    <mmj-mgr:hasAccess forward="bookingEdit">
    <html:form action="/bookingEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="booking.bookingId" value="<bean:write name="BookingViewFormMgr" property="booking.bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingEdit">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingViewFormMgr" property="booking.canBeEdited" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    <logic:equal name="BookingViewFormMgr" property="booking.canBeCancelled" value="true">
    <mmj-mgr:hasAccess forward="bookingCancel">
    <html:form action="/bookingCancel.do" onsubmit="return singleSubmit();">    
    <input type="hidden" name="booking.bookingId" value="<bean:write name="BookingViewFormMgr" property="booking.bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.cancel"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingCancel">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingViewFormMgr" property="booking.canBeCancelled" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    <logic:equal name="BookingViewFormMgr" property="booking.canBeSubmitted" value="true">
    <mmj-mgr:hasAccess forward="bookingSubmit">
    <html:form action="/bookingSubmit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="booking.bookingId" value="<bean:write name="BookingViewFormMgr" property="booking.bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.submit"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingSubmit">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingViewFormMgr" property="booking.canBeSubmitted" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    <logic:equal name="BookingViewFormMgr" property="booking.canBeExtended" value="true">
    <mmj-mgr:hasAccess forward="bookingExtend">
    <html:form action="/bookingExtend.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="booking.bookingId" value="<bean:write name="BookingViewFormMgr" property="booking.bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.extend"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    </logic:equal>

    <logic:equal name="BookingViewFormMgr" property="booking.canBeExtendedCompleted" value="true">
    <mmj-mgr:hasAccess forward="bookingExtendCompleted">
    <html:form action="/bookingExtendCompleted.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="booking.bookingId" value="<bean:write name="BookingViewFormMgr" property="booking.bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.extendCompleted"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    </logic:equal>

    </logic:equal>
    <%/* CANCEL */%>
    <logic:equal name="isCancel" value="true">
		<td align="left" valign="middle" class="title">
  		<bean:message key="title.bookingCancel"/>
      <bean:message name="BookingViewFormMgr" property="booking.statusDescriptionKey"/>
  		<bean:message key="title.bookingNo"/>
      <bean:write name="BookingViewFormMgr" property="booking.bookingId" format="#000"/>
      <bean:write name="BookingViewFormMgr" property="booking.jobProfileName"/>
		</td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    <logic:equal name="BookingViewFormMgr" property="booking.canBeCancelled" value="true">
    <mmj-mgr:hasAccess forward="bookingCancel">
    <html:form action="/bookingCancelProcess.do"
               onsubmit="javascript:document.BookingViewFormMgr.elements['booking.cancelText'].value = 
                         document.CancelFormMgr.cancelText.value;return singleSubmit();">
    <input type="hidden" name="booking.bookingId" value="<bean:write name="BookingViewFormMgr" property="booking.bookingId"/>"/>
    <input type="hidden" name="booking.noOfChanges" value="<bean:write name="BookingViewFormMgr" property="booking.noOfChanges"/>"/>
    <%-- used to lock the booking --%>
    <input type="hidden" name="bookingId" value="<bean:write name="BookingViewFormMgr" property="booking.bookingId"/>"/>
    <input type="hidden" name="booking.cancelText"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingCancel">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingViewFormMgr" property="booking.canBeCancelled" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    <td align="right" valign="middle" width="75">&nbsp;</td>
		</logic:equal>
    <%/* SUBMIT */%>
    <logic:equal name="isSubmit" value="true">
		<td align="left" valign="middle" class="title">
  		<bean:message key="title.bookingSubmit"/>
      <bean:message name="BookingViewFormMgr" property="booking.statusDescriptionKey"/>
  		<bean:message key="title.bookingNo"/>
      <bean:write name="BookingViewFormMgr" property="booking.bookingId" format="#000"/>
      <bean:write name="BookingViewFormMgr" property="booking.jobProfileName"/>
		</td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    <logic:equal name="BookingViewFormMgr" property="booking.canBeSubmitted" value="true">
    <mmj-mgr:hasAccess forward="bookingSubmit">
    <html:form action="/bookingSubmitProcess.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="booking.bookingId" value="<bean:write name="BookingViewFormMgr" property="booking.bookingId"/>"/>
    <input type="hidden" name="booking.noOfChanges" value="<bean:write name="BookingViewFormMgr" property="booking.noOfChanges"/>"/>
    <%-- used to lock the booking --%>
    <input type="hidden" name="bookingId" value="<bean:write name="BookingViewFormMgr" property="booking.bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingSubmit">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingViewFormMgr" property="booking.canBeSubmitted" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
		</logic:equal>
  </tr>
</table>