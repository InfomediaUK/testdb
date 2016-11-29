<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:parameter id="theFormAgy" name="theFormAgy" value="ListFormAgy"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="showTotals" name="showTotals" value="true"/>
<mmj-agy:consultant var="consultant"/>
<logic:notPresent name="<%= theFormAgy %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="<%= theFormAgy %>" property="<%= theList %>">
<logic:empty name="<%= theFormAgy %>" property="<%= theList %>">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="<%= theFormAgy %>" property="<%= theList %>">
<table class="simple" width="100%">
<thead>
  <tr>
    <th align="left">
    <bean:message key="label.noDot"/>
    </th>
    <th align="left">
			Tax Date
    </th>
    <th align="left">
			<bean:message key="label.client"/>
    </th>
    <th align="left">
			<bean:message key="label.ward"/>
    </th>
    <th align="left">
			Assignment
    </th>
    <th align="left">
			Period
    </th>
    <th align="left">
			Sent
    </th>
    <th align="left">
			Paid
    </th>
    <th align="right">
			Value
    </th>
    <th align="right">
			Related
    </th>
  </tr>
</thead>
<logic:iterate id="subcontractInvoice" name="<%= theFormAgy %>" property="<%= theList %>" type="com.helmet.bean.SubcontractInvoiceUser" indexId="subcontractInvoiceIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
		<mmj-agy:hasAccess forward="subcontractInvoiceView">
		  <html:link forward="subcontractInvoiceView" paramId="subcontractInvoiceUser.subcontractInvoiceId" paramName="subcontractInvoice" paramProperty="subcontractInvoiceId" titleKey="title.subcontractInvoiceView">
  	    <bean:write name="subcontractInvoice" property="subcontractInvoiceNumber" />
		  </html:link>  	  
	  </mmj-agy:hasAccess>	  
		<mmj-agy:hasNoAccess forward="subcontractInvoiceView">
  	    <bean:write name="subcontractInvoice" property="subcontractInvoiceNumber" />
   	</mmj-agy:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="subcontractInvoice" property="date" formatKey="format.longDateFormat"/>
    </td>
    <td align="left">
 			<bean:write name="subcontractInvoice" property="clientName"/>
    </td>
    <td align="left">
 			<bean:write name="subcontractInvoice" property="siteNhsLocation"/>,&nbsp;<bean:write name="subcontractInvoice" property="locationNhsWard"/>
    </td>
    <td align="left">
 			<bean:write name="subcontractInvoice" property="jobProfileNhsAssignment"/>
    </td>
    <td align="left">
	      <bean:write name="subcontractInvoice" property="startDate" formatKey="format.mediumDateFormat"/>&nbsp;-&nbsp;
	      <bean:write name="subcontractInvoice" property="endDate" formatKey="format.mediumDateFormat"/>
    </td>
    <td align="left">
 			<bean:write name="subcontractInvoice" property="sentDate" formatKey="format.mediumDateFormat"/>
    </td>
    <td align="left">
 			<bean:write name="subcontractInvoice" property="paidDate" formatKey="format.mediumDateFormat"/>
    </td>
    <td align="right">
 			<bean:message key="label.currencySymbol"/><bean:write name="subcontractInvoice" property="value" format="#,##0.00"/>
    </td>
    <td align="left">
<logic:equal name="subcontractInvoice" property="relatedSubcontractInvoiceId" value="0">
      &nbsp;
</logic:equal>
<logic:notEqual name="subcontractInvoice" property="relatedSubcontractInvoiceId" value="0">
			<mmj-agy:hasAccess forward="subcontractInvoiceView">
			  <html:link forward="subcontractInvoiceView" paramId="subcontractInvoiceUser.subcontractInvoiceId" paramName="subcontractInvoice" paramProperty="relatedSubcontractInvoiceId" titleKey="title.subcontractInvoiceView">
	  	    <bean:write name="subcontractInvoice" property="relatedSubcontractInvoiceNumber" />
			  </html:link>  	  
		  </mmj-agy:hasAccess>	  
			<mmj-agy:hasNoAccess forward="subcontractInvoiceView">
	  	    <bean:write name="subcontractInvoice" property="relatedSubcontractInvoiceNumber" />
	   	</mmj-agy:hasNoAccess>
</logic:notEqual>
    </td>
  </tr>
</logic:iterate>
  <tr>
    <td align="right" colspan="8">
      Total
    </td>
    <td align="right">
  	  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormAgy %>" property="totalValue" format="#,##0.00" />
    </td>
    <td>
      &nbsp;
    </td>
  </tr>
</table>
</logic:notEmpty>
</logic:present>
