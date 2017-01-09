<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:parameter id="isView" name="isView" value="false"/>
<bean:parameter id="isOffer" name="isOffer" value="false"/>
<bean:parameter id="isReject" name="isReject" value="false"/>
<bean:parameter id="isRenegotiate" name="isRenegotiate" value="false"/>
<%--
<bean:parameter id="isActivate" name="isActivate" value="false"/>
--%>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
    <%/* VIEW */%>
    <logic:equal name="isView" value="true">
		<td align="left" valign="middle" class="title">
      <bean:message key="title.bookingGradeApplicantView"/>
		</td>

    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeMarkedRenegotiate" value="true">
    <mmj-mgr:hasAccess forward="bookingGradeApplicantRenegotiate">
    <html:form action="/bookingGradeApplicantRenegotiate.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.renegotiate"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingGradeApplicantRenegotiate">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeMarkedRenegotiate" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>

    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeOffered" value="true">
    <%/* if it can be offered it can be rejected */%>
    <mmj-mgr:hasAccess forward="bookingGradeApplicantOffer">
    <html:form action="/bookingGradeApplicantOffer.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.offer"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingGradeApplicantOffer">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeOffered" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>

    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeRejected" value="true">
    <mmj-mgr:hasAccess forward="bookingGradeApplicantReject">
    <html:form action="/bookingGradeApplicantReject.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.reject"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingGradeApplicantReject">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeRejected" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>

    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeActivated" value="true">
<%--
    <mmj-mgr:hasAccess forward="bookingGradeApplicantActivate">
    <html:form action="/bookingGradeApplicantActivate.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.activate"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingGradeApplicantActivate">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
--%>    
    </logic:equal>
    </logic:equal>
    
    <%/* RENEGOTIATE */%>
    <logic:equal name="isRenegotiate" value="true">
		<td align="left" valign="middle" class="title">
  		<bean:message key="title.bookingGradeApplicantRenegotiate"/>
		</td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeMarkedRenegotiate" value="true">
    <mmj-mgr:hasAccess forward="bookingGradeApplicantRenegotiate">
    <html:form action="/bookingGradeApplicantRenegotiateProcess.do" 
               onsubmit="javascript:document.BookingGradeApplicantViewFormMgr.elements['bookingGradeApplicant.renegotiateText'].value = 
                         document.RenegotiateFormMgr.renegotiateText.value; return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <input type="hidden" name="bookingGradeApplicant.noOfChanges" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.noOfChanges"/>"/>
		<%-- used to lock the booking --%>
    <input type="hidden" name="bookingId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingId"/>"/>
    <input type="hidden" name="bookingGradeApplicant.renegotiateText"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingGradeApplicantRenegotiate">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeMarkedRenegotiate" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:equal>

    <%/* OFFER */%>
    <logic:equal name="isOffer" value="true">
		<td align="left" valign="middle" class="title">
  		<bean:message key="title.bookingGradeApplicantOffer"/>
		</td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeOffered" value="true">
    <mmj-mgr:hasAccess forward="bookingGradeApplicantOffer">
    <html:form action="/bookingGradeApplicantOfferProcess.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <input type="hidden" name="bookingGradeApplicant.noOfChanges" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.noOfChanges"/>"/>
		<%-- used to lock the booking --%>
    <input type="hidden" name="bookingId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingGradeApplicantOffer">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeOffered" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:equal>

    <%/* REJECT */%>
    <logic:equal name="isReject" value="true">
		<td align="left" valign="middle" class="title">
  		<bean:message key="title.bookingGradeApplicantReject"/>
		</td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeRejected" value="true">
    <mmj-mgr:hasAccess forward="bookingGradeApplicantReject">
    <html:form action="/bookingGradeApplicantRejectProcess.do" 
               onsubmit="javascript:document.BookingGradeApplicantViewFormMgr.elements['bookingGradeApplicant.rejectText'].value = 
                         document.RejectFormMgr.rejectText.value; return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <input type="hidden" name="bookingGradeApplicant.noOfChanges" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.noOfChanges"/>"/>
		<%-- used to lock the booking --%>
    <input type="hidden" name="bookingId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingId"/>"/>
    <input type="hidden" name="bookingGradeApplicant.rejectText"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingGradeApplicantReject">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeRejected" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    </logic:equal>

    <%-- ACTIVATE - now activated by bookingDate !!! so not required
    <logic:equal name="isActivate" value="true">
		<td align="left" valign="middle" class="title">
  		<bean:message key="title.bookingGradeApplicantActivate"/>
		</td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    <logic:equal name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeActivated" value="true">
    <mmj-mgr:hasAccess forward="bookingGradeApplicantActivate">
    <html:form action="/bookingGradeApplicantActivateProcess.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGradeApplicant.bookingGradeApplicantId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.bookingGradeApplicantId"/>"/>
    <input type="hidden" name="bookingGradeApplicant.noOfChanges" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.noOfChanges"/>"/>
		<%/* used to lock the booking */%>
    <input type="hidden" name="bookingId" value="<bean:write name="BookingGradeApplicantViewFormMgr" property="bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.activate"/></html:submit></td>
    </html:form>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="bookingGradeApplicantActivate">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-mgr:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeApplicantViewFormMgr" property="bookingGradeApplicant.canBeActivated" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    </logic:equal>
    --%>

  </tr>
</table> 
