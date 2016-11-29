<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<bean:define id="bookingDates" name="OrderStaffCopyFormAgy" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
<%
com.helmet.bean.BookingDate minDate = bookingDates[0];
com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
pageContext.setAttribute("minDate", minDate);
pageContext.setAttribute("maxDate", maxDate);
%>
<html:form action="/orderStaffCopy10.do" focus="comments" onsubmit="return singleSubmit();">
	<html:hidden property="page" value="9"/>
		<table>
		  <tr>
		    <td align="left" valign="middle" width="250">
	      <bean:message key="title.orderStaffCopyStep9"/>
	      -&nbsp;<bean:message key="title.orderStaffCopy9"/>
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
	    <td width="50%">
				<table class="simple" width="100%">
				  <tr>
				    <th align="left" valign="top" class="label" nowrap="true">
				      <bean:message key="label.comments" />
				    </th>
				    <td align="left" width="100%">
				      &nbsp;
				    </td>
				  </tr>
				  <tr>
				    <td align="left" width="100%" colspan="2">
				      <html:textarea tabindex="1" property="comments" cols="52" rows="10"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.duration" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="duration" maxlength="100" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.reference" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="bookingReference" maxlength="20" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <td align="left">
				      &nbsp;
				    </td>
				    <th align="left" class="label">
				      <bean:message key="label.contact" />
				    </th>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.contactName" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="contactName" size="30" maxlength="50" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.contactJobTitle" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="contactJobTitle" size="30" maxlength="50" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.contactEmailAddress" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="contactEmailAddress" size="30" maxlength="320" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.contactTelephoneNumber" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="contactTelephoneNumber" size="30" maxlength="20" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <td align="left">
				      &nbsp;
				    </td>
				    <th align="left" class="label">
				      <bean:message key="label.accountContact" />
				    </th>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.accountContactName" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="accountContactName" size="30" maxlength="50" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.accountContactAddress" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="accountContactAddress.address1" size="30" maxlength="200" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      &nbsp;
				    </th>
				    <td align="left" width="100%">
				      <html:text property="accountContactAddress.address2" size="30" maxlength="200" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      &nbsp;
				    </th>
				    <td align="left" width="100%">
				      <html:text property="accountContactAddress.address3" size="30" maxlength="200" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      &nbsp;
				    </th>
				    <td align="left" width="100%">
				      <html:text property="accountContactAddress.address4" size="30" maxlength="200" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.accountContactPostalCode" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="accountContactAddress.postalCode" maxlength="20" tabindex="1"/>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.accountContactCountry" />
				    </th>
				    <td align="left" width="100%">
				      <mmj:countryList var="countryList" />
				      <html:select property="accountContactAddress.countryId" tabindex="1">
				        <html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
				        <html:options collection="countryList" labelProperty="name" property="countryId" />
				      </html:select>
				    </td>
				  </tr>
				  <tr>
				    <th align="left" class="label">
				      <bean:message key="label.accountContactEmailAddress" />
				    </th>
				    <td align="left" width="100%">
				      <html:text property="accountContactEmailAddress" size="30" tabindex="1"/>
				    </td>
				  </tr>
				</table>
      </td>
      <td width="50%" valign="top">
	      <jsp:include page="orderStaffCopyFeedback.jsp" flush="true" >
	        <jsp:param name="formName" value="OrderStaffCopyFormAgy" />
	      </jsp:include>
	    </td>
	  </tr>
  </table>    
</html:form>