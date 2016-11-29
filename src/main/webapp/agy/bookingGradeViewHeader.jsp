<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:parameter id="isView" name="isView" value="false"/>
<bean:parameter id="isCancel" name="isCancel" value="false"/>

<bean:parameter id="tab" name="tab" value="summary"/>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
    <%/* VIEW */%>
    <logic:equal name="isView" value="true">
		
		<td align="left" valign="middle" class="title">
  		<bean:message key="title.view"/>&nbsp;
<%--
      <bean:message name="BookingGradeViewFormAgy" property="bookingGrade.bookingStatusDescriptionKey"/>
--%>
      <bean:message name="BookingGradeViewFormAgy" property="bookingGrade.statusDescriptionKey"/>&nbsp;<bean:message key="title.bookingNo"/>&nbsp;<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingId" format="#000"/>&nbsp;<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.jobProfileName"/>

		</td>

    <logic:equal name="tab" value="applicants">
		<logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.canApply" value="true">
		<mmj-agy:hasAccess forward="bookingGradeApplicantNew">
    <html:form action="/bookingGradeApplicantNew.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGrade.bookingGradeId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
		</mmj-agy:hasAccess>
		</logic:equal>
		</logic:equal>
		
    <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.canBeCancelled" value="true">
    <mmj-agy:hasAccess forward="bookingCancel">
    <html:form action="/bookingCancel.do" onsubmit="return singleSubmit();">    
    <input type="hidden" name="bookingGrade.bookingGradeId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.cancel"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="bookingCancel">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-agy:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeViewFormAgy" property="bookingGrade.canBeCancelled" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    
    <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.canBeCopied" value="true">
    <mmj-agy:hasAccess forward="orderStaffCopy">
    <html:form action="/orderStaffCopy.do" onsubmit="return singleSubmit();">    
    <input type="hidden" name="bookingGrade.bookingGradeId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.copy"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="orderStaffCopy">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-agy:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeViewFormAgy" property="bookingGrade.canBeCopied" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>
    
    <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.canBeExtended" value="true">
    <mmj-agy:hasAccess forward="bookingExtend">
    <html:form action="/bookingExtend.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGrade.bookingGradeId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeId"/>"/>
    <input type="hidden" name="bookingGrade.bookingId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.extend"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    </logic:equal>

    <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.canBeExtendedCompleted" value="true">
    <mmj-agy:hasAccess forward="bookingExtendCompleted">
    <html:form action="/bookingExtendCompleted.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="bookingGrade.bookingGradeId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeId"/>"/>
    <input type="hidden" name="bookingGrade.bookingId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.extendCompleted"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    </logic:equal>

		</logic:equal>
		
    <%/* CANCEL */%>
    <logic:equal name="isCancel" value="true">
		<td align="left" valign="middle" class="title">
  		<bean:message key="title.bookingCancel"/>

      <bean:message name="BookingGradeViewFormAgy" property="bookingGrade.statusDescriptionKey"/>&nbsp;<bean:message key="title.bookingNo"/>&nbsp;<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingId" format="#000"/>&nbsp;<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.jobProfileName"/>

		</td>
    <td align="right" valign="middle" width="75">&nbsp;</td>
    <logic:equal name="BookingGradeViewFormAgy" property="bookingGrade.canBeCancelled" value="true">
    <mmj-agy:hasAccess forward="bookingCancel">
    <html:form action="/bookingCancelProcess.do"
               onsubmit="javascript:document.BookingGradeViewFormAgy.elements['cancelText'].value = 
                         document.CancelFormAgy.cancelText.value;return singleSubmit();">
    <input type="hidden" name="bookingGrade.bookingId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingId"/>"/>
    <input type="hidden" name="bookingGrade.bookingGradeId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingGradeId"/>"/>
    <%-- used to lock the booking --%>
    <input type="hidden" name="bookingId" value="<bean:write name="BookingGradeViewFormAgy" property="bookingGrade.bookingId"/>"/>
    <input type="hidden" name="cancelText"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
    </html:form>
    </mmj-agy:hasAccess>
    <mmj-agy:hasNoAccess forward="bookingCancel">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </mmj-agy:hasNoAccess>
    </logic:equal>
    <logic:notEqual name="BookingGradeViewFormAgy" property="bookingGrade.canBeCancelled" value="true">
    <td align="right" valign="middle" width="75">&nbsp;</td>
    </logic:notEqual>

		</logic:equal>
    
    
    
  </tr>
</table>
