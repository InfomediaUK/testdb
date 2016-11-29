<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>

<bean:define id="backingReport" name="NhsBackingReportFileUploadFormAgy" property="backingReport" type="com.helmet.bean.nhs.BackingReport"/>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/nhsBackingReportFileAccept.do" onsubmit="return singleSubmit();">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.nhsBackingReportFileUploadResult"/>:&nbsp;<bean:write name="NhsBackingReportFileUploadFormAgy" property="nhsBackingReportFilename" />
		</td>
<logic:equal name="backingReport" property="canAccept" value="true">
  <mmj-agy:hasAccess forward="nhsBackingReportFileUpload">
    <td align="right" valign="middle" width="75">
      <html:submit styleClass="titleButton"><bean:message key="button.accept"/></html:submit>
    </td>
	  <html:hidden name="NhsBackingReportFileUploadFormAgy" property="accept"/>
	  <html:hidden name="NhsBackingReportFileUploadFormAgy" property="client.clientId"/>
  </mmj-agy:hasAccess>
</logic:equal>
  </tr>
</html:form>
</table>
<html:errors/>
<table class="simple" width="100%">
	<thead>
    <tr>
	    <th align="left">
        Backing Report
      </th>
	    <th align="left">
        Date Range
      </th>
	    <th align="left">
	      NHS Client/Trust
      </th>
	    <th align="right">
	      Commission
      </th>
	    <th align="right">
	      Total Cost
      </th>
	    <th align="right">
	      Grand Total Cost
      </th>
    </tr>
  </thead>
  <tr>
    <td align="left">
      <bean:write name="backingReport" property="name" />
    </td>
    <td align="left">
      <bean:write name="backingReport" property="startDate" formatKey="format.nhsDateFormat"/>&nbsp;-&nbsp;<bean:write name="backingReport" property="endDate" formatKey="format.nhsDateFormat"/>
    </td>
    <td align="left">
      <bean:write name="backingReport" property="trust" />
    </td>
    <td align="right">
      <b><bean:message key="label.currencySymbol"/><bean:write name="backingReport" property="totalCommission" format="#####0.00" /></b>
     </td>
    <td align="right">
      <b><bean:message key="label.currencySymbol"/><bean:write name="backingReport" property="totalCost" format="#####0.00" /></b>
     </td>
    <td align="right">
      <b><bean:message key="label.currencySymbol"/><bean:write name="backingReport" property="grandTotalCost" format="#####0.00" /></b>
    </td>
  </tr>
<logic:iterate id="backingReportError" name="backingReport" property="errors" type="com.helmet.bean.nhs.BackingReportError" indexId="errorIndex">
  <tr>
    <td colspan="6" class="nhsUploadError">
      ***&nbsp;<bean:write name="backingReportError"  property="message" />&nbsp;***&nbsp;
  <logic:present name="backingReportError"  property="nhsBackingReportId" >
    <logic:equal name="backingReportError"  property="view" value="DETAIL" >
      <html:link forward="nhsBackingReportView" paramId="nhsBackingReportUser.nhsBackingReportId" paramName="backingReportError" paramProperty="nhsBackingReportId" titleKey="title.nhsBackingReportView">View</html:link>
    </logic:equal>
    <logic:equal name="backingReportError"  property="view" value="LIST" >
      <html:link forward="nhsBackingReportShiftList" paramId="nhsBackingReportUser.nhsBackingReportId" paramName="backingReportError" paramProperty="nhsBackingReportId" titleKey="title.nhsBackingReportView">View List</html:link>
    </logic:equal>
  </logic:present>
    </td>
  </tr>  
</logic:iterate>
</table>
<table class="simple" width="100%">
	<thead>
	  <tr>
	    <th align="left">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.date" />
	    </th>
	    <th align="left">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.refNum" />
	    </th>
	    <th align="left">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.agencyWorkerName" />
	    </th>
	    <th align="left">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.ward" />
	    </th>
	    <th align="left">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.assignment" />
	    </th>
	    <th align="left">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.contractStart" />
	    </th>
	    <th align="left">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.contractEnd" />
	    </th>
	    <th align="left">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.contractBreakInMinutes" />
	    </th>
	    <th align="left">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.contractTotal" />
	    </th>
	    <th align="right">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.commission" />
	    </th>
	    <th align="right">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.totalCost" />
	    </th>
	    <th align="right">
	      <bean:write name="backingReport" property="backingReportColumnHeadings.rate" />
	    </th>
	  <tr>
	</thead>  
<logic:iterate id="backingReportLine" name="backingReport" property="backingReportLines" type="com.helmet.bean.nhs.BackingReportLine" indexId="backingReportIndex">
  <tr>
    <td>
      <bean:write name="backingReportLine" property="date" formatKey="format.nhsDateFormat"/>
    </td>
    <td>
      <bean:write name="backingReportLine" property="bankReqNum" />
    </td>
    <td>
      <bean:write name="backingReportLine" property="agencyWorkerName" />
    </td>
    <td>
      <bean:write name="backingReportLine" property="hospital" />,
      <bean:write name="backingReportLine" property="ward" />
    </td>
    <td>
      <bean:write name="backingReportLine" property="assignment" />
     </td>
    <td>
      <bean:write name="backingReportLine" property="contractShift.startTime" formatKey="format.nhsTimeFormat" />
     </td>
    <td>
      <bean:write name="backingReportLine" property="contractShift.endTime" formatKey="format.nhsTimeFormat" />
     </td>
    <td align="right">
      <bean:write name="backingReportLine" property="contractShift.breakMinutes" />
     </td>
    <td align="right">
      <bean:write name="backingReportLine" property="contractShift.workedTime" />
    </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="backingReportLine" property="commission" format="#####0.00" />
     </td>
    <td align="right">
      <bean:message key="label.currencySymbol"/><bean:write name="backingReportLine" property="totalCost" format="#####0.00" />
  <logic:equal name="backingReportLine" property="totalCostChanged" value="true">
      *
  </logic:equal>
     </td>
    <td align="right">
      <bean:write name="backingReportLine" property="rate" />
    </td>
  </tr>
  <tr>
    <td colspan="4">
  <logic:present name="backingReportLine" property="bookingId">
    <logic:present name="backingReportLine" property="bookingGradeId">
      <html:link forward="bookingGradeViewSummary" paramId="bookingGrade.bookingGradeId" paramName="backingReportLine" paramProperty="bookingGradeId" titleKey="title.bookingGradeViewSummary">
        <bean:write name="backingReportLine" property="bookingId" format="#000" />
      </html:link>.<bean:write name="backingReportLine" property="bookingDateId" format="#000" />
    </logic:present>
  </logic:present>
  <logic:notPresent name="backingReportLine" property="bookingId">
      Booking NOT Found
  </logic:notPresent>
    </td>
    <th>
      Actual
    </th>
    <td>
      <bean:write name="backingReportLine" property="actualShift.startTime" formatKey="format.nhsTimeFormat" />
  <logic:equal name="backingReportLine" property="actualShift.startTimeChanged" value="true">
      *
  </logic:equal>
     </td>
    <td>
      <bean:write name="backingReportLine" property="actualShift.endTime" formatKey="format.nhsTimeFormat" />
  <logic:equal name="backingReportLine" property="actualShift.endTimeChanged" value="true">
      *
  </logic:equal>
     </td>
    <td align="right">
      <bean:write name="backingReportLine" property="actualShift.breakMinutes" />
     </td>
    <td align="right">
      <bean:write name="backingReportLine" property="actualShift.workedTime" />
    </td>
    <td colspan="3">
      &nbsp;
    </td>
  </tr>
  <logic:equal name="backingReportLine" property="valid" value="false">
    <logic:iterate id="backingReportLineError" name="backingReportLine" property="errors" type="com.helmet.bean.nhs.BackingReportLineError" indexId="errorIndex">
  <tr>
    <td colspan="12" class="nhsUploadError">
      ***&nbsp;<bean:write name="backingReportLineError"  property="message" />&nbsp;***&nbsp;
      <logic:present name="backingReportLineError"  property="bookingGradeApplicantId" >
		  <html:link forward="bookingGradeApplicantView" paramId="bookingGradeApplicant.bookingGradeApplicantId" paramName="backingReportLineError" paramProperty="bookingGradeApplicantId" titleKey="title.bookingGradeApplicantView">View</html:link>
		  </logic:present>
    </td>
  </tr>  
    </logic:iterate>
  </logic:equal> 
  <tr>
    <td colspan="12">
      &nbsp;
    </td>
  </tr>  
</logic:iterate>
  <tr>
    <td colspan="9">
      &nbsp;
    </td>
    <td align="right">
      <b><bean:message key="label.currencySymbol"/><bean:write name="backingReport" property="totalCommission" format="#####0.00"/></b>
     </td>
    <td align="right">
      <b><bean:message key="label.currencySymbol"/><bean:write name="backingReport" property="totalCost" format="#####0.00"/></b>
     </td>
    <td align="right">
      <b><bean:message key="label.currencySymbol"/><bean:write name="backingReport" property="grandTotalCost" format="#####0.00"/></b>
    </td>
  </tr>  
