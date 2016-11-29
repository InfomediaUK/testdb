<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="OrderStaffCopyFormAgy" property="list">
<br/>
<br/>
<bean:message key="error.noExpenses"/>
</logic:empty>
<logic:notEmpty name="OrderStaffCopyFormAgy" property="list">
	<html:form action="/orderStaffCopy9.do" focus="expense0" onsubmit="return singleSubmit();">
		<html:hidden property="page" value="8"/>
		<html:hidden property="selectedExpenses" value="0"/>
		<table>
		  <tr>
		    <td align="left" valign="middle" width="250">
	      <bean:message key="title.orderStaffCopyStep8"/>
	      -&nbsp;<bean:message key="title.orderStaffCopy8"/>
		    </td>
		    <td align="left" valign="top">
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
		      <logic:notEmpty name="OrderStaffCopyFormAgy" property="list">
		        <table class="simple">
						<logic:iterate id="expense" name="OrderStaffCopyFormAgy" property="list" indexId="expenseIndex">
						  <tr>
						    <th class="label">
									<bean:write name="expense" property="name"/>
									<logic:notEmpty name="expense" property="description">(<bean:write name="expense" property="description"/>)</logic:notEmpty>
						    </th>
						    <td>
									<html:multibox tabindex="1" property="selectedExpenses" styleId='<%= "expense" + expenseIndex %>'>
									  <bean:write name="expense" property="expenseId"/>
									</html:multibox>
							  </td>
							</tr>
						</logic:iterate>
						</table>
					</logic:notEmpty>
					<br/>
					<table class="simple" width="100%">
					  <tr>
					    <th align="left" class="label" nowrap="true">
					      <bean:message key="label.comment"/>&nbsp;
					    </th>
					    <td align="left" width="100%">
					      <html:textarea tabindex="1" property="expensesText" cols="65" rows="3"/>
					    </td>
					  </tr>
					</table>
		    </td>
		    <td class="simple" width="50%" valign="top">
		      <jsp:include page="orderStaffCopyFeedback.jsp" flush="true" >
		        <jsp:param name="formName" value="OrderStaffCopyFormAgy" />
		      </jsp:include>
		    </td>
		  </tr>
		</table>    
	</html:form>
</logic:notEmpty>