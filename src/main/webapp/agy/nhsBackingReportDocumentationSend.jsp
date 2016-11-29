<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="nhsBackingReportUser" name="NhsBackingReportDocumentationSendFormAgy"  property="nhsBackingReportUser" type="com.helmet.bean.NhsBackingReportUser"/>
<bean:define id="nhsInvoiceFile" name="NhsBackingReportDocumentationSendFormAgy" property="nhsInvoiceFile" type="java.lang.String"/>
<bean:define id="nhsBackingReportDocumentationFile" name="NhsBackingReportDocumentationSendFormAgy" property="nhsBackingReportDocumentationFile" type="java.lang.String"/>
<bean:define id="nhsBackingReportRejectedDocumentationFile" name="NhsBackingReportDocumentationSendFormAgy" property="nhsBackingReportRejectedDocumentationFile" type="java.lang.String"/>
<% 
String nhsInvoiceFileUrl = request.getContextPath() + nhsInvoiceFile;
String nhsBackingReportDocumentationFileUrl = request.getContextPath() + nhsBackingReportDocumentationFile;
String nhsBackingReportRejectedDocumentationFileUrl = request.getContextPath() + nhsBackingReportRejectedDocumentationFile;
%>

<html:form action="/nhsBackingReportDocumentationSendProcess.do" focus="toEmailAddress" onsubmit="return singleSubmit();">
  <html:hidden name="NhsBackingReportDocumentationSendFormAgy" property="nhsBackingReportUser.nhsBackingReportId" />
	<table cellpadding="0" cellspacing="0" width="100%" height="30">
	  <tr>
			<td align="left" valign="middle" class="title">
			  <bean:message key="title.nhsBackingReportDocumentationSend"/>
			</td>
	    <td align="right" valign="middle" width="80">
	      <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
	    </td>
	  </tr>
	</table>
	<html:errors/>
	<table class="simple" width="100%">
	  <tr>
	    <th align="left">To Email Address</th>
	    <td align="left">
	      <html:text  name="NhsBackingReportDocumentationSendFormAgy" property="toEmailAddress" size="100"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">From Email Address</th>
	    <td align="left">
	      <html:text  name="NhsBackingReportDocumentationSendFormAgy" property="fromEmailAddress" size="100"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">CC Email Address</th>
	    <td align="left">
	      <html:text  name="NhsBackingReportDocumentationSendFormAgy" property="ccEmailAddress" size="100"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left">BCC Email Address</th>
	    <td align="left">
	      <html:text  name="NhsBackingReportDocumentationSendFormAgy" property="bccEmailAddress" size="100"/>
	    </td>
	  </tr>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.subject"/></th>
	    <td align="left">
	      <html:text name="NhsBackingReportDocumentationSendFormAgy" property="subject" size="100" />
	    </td>
	  </tr>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.message"/></th>
	    <td align="left">
	      <html:textarea property="message" style="width:100%" styleId="message" rows="12" />
	    </td>
	  </tr>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.invoice"/></th>
	    <td align="left">
	      <bean:define id="nhsInvoiceLinkTitle" name="NhsBackingReportDocumentationSendFormAgy" property="nhsInvoiceLinkTitle" type="java.lang.String"/>
		  <html:link href="<%= nhsInvoiceFileUrl %>" target="pdf" title="<%= nhsInvoiceLinkTitle %>">
		    <bean:write name="NhsBackingReportDocumentationSendFormAgy" property="nhsInvoiceFileName"/>
		  </html:link>
	    </td>
	  </tr>
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.documentation"/></th>
	    <td align="left">
          <bean:define id="nhsBackingReportDocumentationLinkTitle" name="NhsBackingReportDocumentationSendFormAgy" property="nhsBackingReportDocumentationLinkTitle" type="java.lang.String"/>
		  <html:link href="<%= nhsBackingReportDocumentationFileUrl %>" target="pdf" title="<%= nhsBackingReportDocumentationLinkTitle %>">
		    <bean:write name="nhsBackingReportUser" property="documentationFileName"/>
		  </html:link>
	    </td>
	  </tr>
<logic:notEmpty  name="NhsBackingReportDocumentationSendFormAgy" property="nhsBackingReportRejectedDocumentationFile">
	  <tr>
	    <th align="left" valign="top"><bean:message key="label.rejectedDocumentation"/></th>
	    <td align="left">
		  <bean:define id="nhsBackingReportRejectedDocumentationLinkTitle" name="NhsBackingReportDocumentationSendFormAgy" property="nhsBackingReportRejectedDocumentationLinkTitle" type="java.lang.String"/>
		  <html:link href="<%= nhsBackingReportRejectedDocumentationFileUrl %>" target="pdf" title="<%= nhsBackingReportRejectedDocumentationLinkTitle %>">
		    <bean:write name="nhsBackingReportUser" property="rejectedDocumentationFileName"/>
		  </html:link>
	    </td>
	  </tr>
</logic:notEmpty>      
	</table>

</html:form>
