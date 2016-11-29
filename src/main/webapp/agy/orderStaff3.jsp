<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="OrderStaffFormAgy" property="list">
<br/>
<br/>
<bean:message key="error.noJobProfiles"/>
</logic:empty>
<logic:notEmpty name="OrderStaffFormAgy" property="list">
<html:form action="/orderStaff4.do" focus="locationJobProfileUser0" onsubmit="return singleSubmit();">
<html:hidden property="page" value="3"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <logic:empty name="OrderStaffFormAgy" property="booking.bookingId">
      <bean:message key="title.newBooking"/>
      </logic:empty>
      <logic:notEmpty name="OrderStaffFormAgy" property="booking.bookingId">
      <bean:message key="title.editBooking"/>
      </logic:notEmpty>
      -&nbsp;<bean:message key="title.orderStaff3"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="orderStaffButtons.jsp" flush="true" />
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<table border="0" width="100%">
  <tr>
    <td width="50%">
			<bean:define id="jobFamilyName" value=""/>
			<bean:define id="jobSubFamilyName" value=""/>
			<table class="simple" border="1">
			<logic:iterate id="locationJobProfileUser" name="OrderStaffFormAgy" property="list" indexId="locationJobProfileUserIndex">
			  <%/* job family */%>
			  <logic:notEqual name="locationJobProfileUser" property="jobFamilyName" value="<%= jobFamilyName %>">
			  <tr>
			    <th class="label" colspan="6">
			    <bean:write name="locationJobProfileUser" property="jobFamilyName"/>
			    (<bean:write name="locationJobProfileUser" property="jobFamilyCode"/>)
			    </th>
			    <bean:define id="jobFamilyName" name="locationJobProfileUser" property="jobFamilyName" type="java.lang.String"/>
			    <bean:define id="jobSubFamilyName" value=""/>
			  </tr>
			  </logic:notEqual>
			  <%/* job sub family */%>
			  <logic:notEqual name="locationJobProfileUser" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
			  <tr>
			    <th class="label">
			    <html:img src="images/indent.gif" width="15" height="9"/>
			    </th>
			    <th class="label" colspan="3">
			    <bean:write name="locationJobProfileUser" property="jobSubFamilyName"/>
			    (<bean:write name="locationJobProfileUser" property="jobSubFamilyCode"/>)
			    </th>
			    <th class="label" align="right">
					  <%-- &nbsp; FORCE the cell wider - so that the hover stuff works below --%>
					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			      <bean:message key="label.budget"/>
			    </th>
			    <th class="label" align="left">
			      <bean:message key="label.jobDescription"/>
			    </th>  
			    <bean:define id="jobSubFamilyName" name="locationJobProfileUser" property="jobSubFamilyName" type="java.lang.String"/>
			  </tr>
			  </logic:notEqual>
			  <%/* job profile */%>
			  <tr>
				  <th class="label">
				  <html:img src="images/trans.gif" width="15" height="9"/>
			    </th>
			    <th class="label">
				  <html:img src="images/indent.gif" width="15" height="9"/>
				  </th>
				  <th class="label" onmouseover="<%= "b" + locationJobProfileUserIndex %>.className='budgetbold';" onmouseout="<%= "b" + locationJobProfileUserIndex %>.className='budget';">
				    <bean:write name="locationJobProfileUser" property="jobProfileName"/>
			      (<bean:write name="locationJobProfileUser" property="jobProfileCode"/>)
				  </th>
				  <td>
				    <html:radio tabindex="1" property="jobProfile.jobProfileId" idName="locationJobProfileUser" value="jobProfileId" styleId='<%= "locationJobProfileUser" + locationJobProfileUserIndex %>' />
				  </td>
				  <td align="right" class="budget" id="<%= "b" + locationJobProfileUserIndex %>">
				    <bean:message key="label.currencySymbol"/><bean:write name="locationJobProfileUser" property="budget" format="#,##0.00"/>
				  </td>
				  <td>
				  <logic:present name="locationJobProfileUser" property="documentURL">
				  <bean:define id="documentURL" name="locationJobProfileUser" property="documentURL" type="java.lang.String"/>
				  <html:link href="<%= documentURL %>" target="documentURLTarget">
				    <bean:message key="link.view"/>
				  </html:link>
				  </logic:present>
				  <logic:notPresent name="locationJobProfileUser" property="documentURL">
			    &nbsp;
			    </logic:notPresent>
			    </td>  
			  </tr>
			</logic:iterate>
			</table>
			<br/>
			<%--
			<bean:define id="jobFamilyName" value=""/>
			<bean:define id="jobSubFamilyName" value=""/>
			<logic:iterate id="locationJobProfileUser" name="OrderStaffFormAgy" property="list" indexId="locationJobProfileUserIndex">
			  <logic:notEqual name="locationJobProfileUser" property="jobFamilyName" value="<%= jobFamilyName %>">
			    <bean:write name="locationJobProfileUser" property="jobFamilyName"/><br/>
			    <bean:define id="jobFamilyName" name="locationJobProfileUser" property="jobFamilyName" type="java.lang.String"/>
			  <bean:define id="jobSubFamilyName" value=""/>
			  </logic:notEqual>
			  <logic:notEqual name="locationJobProfileUser" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
			    <html:img src="images/indent.gif" width="15" height="9"/>
			    <bean:write name="locationJobProfileUser" property="jobSubFamilyName"/><br/>
			    <bean:define id="jobSubFamilyName" name="locationJobProfileUser" property="jobSubFamilyName" type="java.lang.String"/>
			  </logic:notEqual>
			  <html:img src="images/trans.gif" width="15" height="9"/><html:img src="images/indent.gif" width="15" height="9"/>
			  <html:radio tabindex="1" property="jobProfile.jobProfileId" idName="locationJobProfileUser" value="jobProfileId" styleId='<%= "locationJobProfileUser" + locationJobProfileUserIndex %>' />
			  <label for="<%= "locationJobProfileUser" + locationJobProfileUserIndex %>">
			  <bean:write name="locationJobProfileUser" property="jobProfileName"/>
			  </label>
			  <bean:write name="locationJobProfileUser" property="budget" format="#,##0.00"/>
			  <logic:present name="locationJobProfileUser" property="documentURL">
			  <bean:define id="documentURL" name="locationJobProfileUser" property="documentURL" type="java.lang.String"/>
			  <html:link href="<%= documentURL %>" target="documentURLTarget">
			    <bean:message key="link.jobDescription"/>
			  </html:link>
			  </logic:present>
			  <br/>
			</logic:iterate>
			--%>
		</td>
		<td width="50%" valign="top">
		  <table class="simple">
		    <tr>
		      <th class="label">
		        <bean:message key="label.client"/>
		      </th>
		      <td>
       		  <bean:write name="OrderStaffFormAgy" property="client.name"/>&nbsp;(<bean:write name="OrderStaffFormAgy" property="client.code"/>)
		      </td>
		    </tr>
		    <tr>
		      <th class="label">
		        <bean:message key="label.reason"/>
		      </th>
		      <td>
       		  <bean:write name="OrderStaffFormAgy" property="reasonForRequest.name"/>&nbsp;-
       		  <bean:write name="OrderStaffFormAgy" property="reasonForRequestText"/>
		      </td>
		    </tr>
		    <tr>
          <th class="label">
            <bean:message key="label.location"/>
          </td>
          <td>
				    <bean:write name="OrderStaffFormAgy" property="location.name"/>,
				    <bean:write name="OrderStaffFormAgy" property="location.siteName"/>
			      <logic:notEmpty name="OrderStaffFormAgy" property="location.description">
			  	    (<bean:write name="OrderStaffFormAgy" property="location.description"/>)
			      </logic:notEmpty>
          </td>
		    </tr>
		  </table>
		</td>
  </tr>
</table>		
</html:form>
</logic:notEmpty>