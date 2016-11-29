<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="subcontractInvoiceItemUser" name="SubcontractInvoiceItemFormAgy"  property="subcontractInvoiceItemUser" type="com.helmet.bean.SubcontractInvoiceItemUser"/>
  <html:form action="/subcontractInvoiceItemEditProcess.do" onsubmit="return singleSubmit();">
    <html:hidden name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.subcontractInvoiceId" />
    <html:hidden name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.subcontractInvoiceItemId" />
    <html:hidden name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.noOfChanges" />
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
			<td align="left" valign="middle" class="title">
			  <bean:message key="title.subcontractInvoiceItemEdit"/>
			</td>
<mmj-agy:hasAccess forward="subcontractInvoiceItemEdit">
      <td align="right" valign="middle" width="80">
        <html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit>
      </td>
</mmj-agy:hasAccess>
	  </tr>
	</table>
	<html:errors/>
	<table class="simple" width="100%">
	  <tr>
	    <th align="left">Bank Request</th>
	    <td align="left"><bean:write  name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.bankReqNum"/></td>
	  </tr>
	  <tr>
	    <th align="left">Staff Name</th>
	    <td align="left"><bean:write  name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.staffName"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.date"/></th>
	    <td align="left"><bean:write  name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.date" format="dd-MMM-yyyy"/></td>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.time"/></th>
	    <td align="left"><bean:write name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.startTime" format="HH:mm" /> - <bean:write name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.endTime" format="HH:mm" /></td>
	  </tr>
	  <tr>
	    <th align="left">Hours</th>
	    <td align="left"><bean:write  name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.noOfHours" format="##0.00"/></td>
	  </tr>
	  <tr>
	    <th align="left">Worked Time</th>
	    <td align="left"><bean:write  name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.workedStartTime" format="HH:mm"/> - <bean:write  name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.workedEndTime" format="HH:mm"/></td>
	  </tr>
	  <tr>
	    <th align="left">Worked Hours</th>
	    <td align="left"><bean:write  name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.workedNoOfHours" format="##0.00"/></td>
	  </tr>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.rate"/></th>
	    <td align="left"><html:text name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.rate" /></td>
	  </tr>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.comment"/></th>
	    <td align="left"><html:textarea name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.comment" cols="50" rows="4"/></td>
	  </tr>
	  <tr>
	    <th align="left" colspan="2">
	      Only change Value if you know what you are doing.<br />
	      If you change Rate or Value will have to Reissue the Invoice...
	    </th>
	  </tr>
	  <tr>
	    <th align="left"><bean:message key="label.value"/></th>
	    <td align="left"><html:text name="SubcontractInvoiceItemFormAgy" property="subcontractInvoiceItemUser.value" /></td>
	  </tr>
  </table>
  </html:form>
  