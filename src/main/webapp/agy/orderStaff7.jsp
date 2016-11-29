<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<%-- focusControl stuff --%>
<bean:define id="focusControl" value="singleCandidateStr"/>
<logic:notEqual name="OrderStaffFormAgy" property="location.singleCandidateShow" value="true">
  <bean:define id="focusControl" value="cvRequiredStr"/>
  <logic:notEqual name="OrderStaffFormAgy" property="location.cvRequiredShow" value="true">
	  <bean:define id="focusControl" value="interviewRequiredStr"/>
	  <logic:notEqual name="OrderStaffFormAgy" property="location.interviewRequiredShow" value="true">
		  <bean:define id="focusControl" value="canProvideAccommodationStr"/>
		  <logic:notEqual name="OrderStaffFormAgy" property="location.canProvideAccommodationShow" value="true">
			  <bean:define id="focusControl" value="carRequiredStr"/>
			  <logic:notEqual name="OrderStaffFormAgy" property="location.carRequiredShow" value="true">
  			  <bean:define id="focusControl" value="dressCode0"/>
			  </logic:notEqual>
		  </logic:notEqual>
	  </logic:notEqual>
  </logic:notEqual>
</logic:notEqual>
 <bean:define id="bookingDates" name="OrderStaffFormAgy" property="bookingDates" type="com.helmet.bean.BookingDate[]"/>
 <%
 com.helmet.bean.BookingDate minDate = bookingDates[0];
 com.helmet.bean.BookingDate maxDate = bookingDates[bookingDates.length-1];
 pageContext.setAttribute("minDate", minDate);
 pageContext.setAttribute("maxDate", maxDate);
 %>
<html:form action="/orderStaff8.do" onsubmit="javascript:sortCheckBoxes(); return singleSubmit();" focus="<%= focusControl %>">
	<html:hidden property="page" value="7"/>
	<table>
	  <tr>
	    <td align="left" valign="middle" width="250">
	      <logic:empty name="OrderStaffFormAgy" property="booking.bookingId">
	      <bean:message key="title.newBooking"/>
	      </logic:empty>
	      <logic:notEmpty name="OrderStaffFormAgy" property="booking.bookingId">
	      <bean:message key="title.editBooking"/>
	      </logic:notEmpty>
	      -&nbsp;<bean:message key="title.orderStaff7"/>
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
	    <td width="50%" valign="top">
	      <table class="simple">
				<logic:equal name="OrderStaffFormAgy" property="location.singleCandidateShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.singleCandidate"/>
					  </th>
					  <td>
					    <input tabindex="1" type="checkbox" name="singleCandidateStr" id="singleCandidateStrId">
					  </td>
					</tr>
				</logic:equal>
				<logic:equal name="OrderStaffFormAgy" property="location.cvRequiredShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.cvRequired"/>
					  </th>
					  <td>
					    <input tabindex="1" type="checkbox" name="cvRequiredStr" id="cvRequiredStrId">
					  </td>
					</tr>
				</logic:equal>
				<logic:equal name="OrderStaffFormAgy" property="location.interviewRequiredShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.interviewRequired"/>
					  </th>
					  <td>
					    <input tabindex="1" type="checkbox" name="interviewRequiredStr" id="interviewRequiredStrId">
					  </td>
					</tr>
				</logic:equal>
				<logic:equal name="OrderStaffFormAgy" property="location.canProvideAccommodationShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.canProvideAccommodation"/>
					  </th>
					  <td>
				    	<input tabindex="1" type="checkbox" name="canProvideAccommodationStr" id="canProvideAccommodationStrId">
					  </td>
					</tr>
				</logic:equal>
				<logic:equal name="OrderStaffFormAgy" property="location.carRequiredShow" value="true">
				  <tr>
				    <th class="label">
					    <bean:message key="label.carRequired"/>
					  </th>
					  <td>
			    		<input tabindex="1" type="checkbox" name="carRequiredStr" id="carRequiredStrId">
					  </td>
					</tr>
				</logic:equal>
				  <tr>
				    <th class="label">
				      <bean:message key="label.autoFill"/>
					  </th>
					  <td>
				      <input tabindex="1" type="checkbox" name="autoFillStr" id="autoFillStrId">
				      <html:hidden property="autoFill"/>
					  </td>
					</tr>
				  <tr>
				    <th class="label">
				      <bean:message key="label.autoActivate"/>
					  </th>
					  <td>
				      <input tabindex="1" type="checkbox" name="autoActivateStr" id="autoActivateStrId">
				      <html:hidden property="autoActivate"/>
					  </td>
					</tr>
				</table>
				<logic:equal name="OrderStaffFormAgy" property="location.singleCandidateShow" value="true">
					<html:hidden property="singleCandidate"/>
				</logic:equal>
				<logic:equal name="OrderStaffFormAgy" property="location.cvRequiredShow" value="true">
					<html:hidden property="cvRequired"/>
				</logic:equal>
				<logic:equal name="OrderStaffFormAgy" property="location.interviewRequiredShow" value="true">
					<html:hidden property="interviewRequired"/>
				</logic:equal>
				<logic:equal name="OrderStaffFormAgy" property="location.canProvideAccommodationShow" value="true">
					<html:hidden property="canProvideAccommodation"/>
				</logic:equal>
				<logic:equal name="OrderStaffFormAgy" property="location.carRequiredShow" value="true">
				  <html:hidden property="carRequired"/>
				</logic:equal>
				<logic:notEmpty name="OrderStaffFormAgy" property="list">
				  <br/>
				  <logic:equal name="OrderStaffFormAgy" property="noOfDressCodes" value="1">
				  <table class="simple">
				    <tr>
				      <th class="label">
				        <bean:message key="label.dressCode"/>&nbsp;&nbsp;
				      </th>
				      <td>
				        <bean:write name="OrderStaffFormAgy" property="dressCode.name"/>&nbsp;&nbsp;
				      </td>
				    </tr>
				  </table>
				  </logic:equal>
				  <logic:greaterThan name="OrderStaffFormAgy" property="noOfDressCodes" value="1">
				    <bean:message key="label.dressCode"/>
				    <table class="radio">
					  <logic:iterate id="dressCode" name="OrderStaffFormAgy" property="list" indexId="dressCodeIndex">
					  <tr>
					    <td>
				   	    <html:radio tabindex="1" property="dressCode.dressCodeId" idName="dressCode" value="dressCodeId" styleId='<%= "dressCode" + dressCodeIndex %>' />
				   	  </td>
				   	  <td>
							  <label for="<%= "dressCode" + dressCodeIndex %>">
							  <bean:write name="dressCode" property="name"/>
							  </label>
							</td>
					  </tr>
					  </logic:iterate>
					  </table>
				 </logic:greaterThan>
				</logic:notEmpty>
    </td>
    <td width="50%" valign="top">
      <table class="simple">
        <tr>
          <th class="label">
		        <bean:message key="label.client"/>
          </td>
          <td>
            <bean:write name="OrderStaffFormAgy" property="client.name"/>&nbsp;(<bean:write name="OrderStaffFormAgy" property="client.code"/>)
          </td>
        </tr>
        <tr>
          <th class="label">
            <bean:message key="label.reason"/>
          </td>
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
        <tr>
          <th class="label" valign="top">
            <bean:message key="label.jobProfile"/>
          </td>
          <td>
            <bean:write name="OrderStaffFormAgy" property="jobProfile.name"/>
	          (<bean:write name="OrderStaffFormAgy" property="jobProfile.jobFamilyCode"/>.<bean:write name="OrderStaffFormAgy" property="jobProfile.jobSubFamilyCode"/>.<bean:write name="OrderStaffFormAgy" property="jobProfile.code"/>)
          </td>
        </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.start"/>
			    </th>
			    <td align="left">
			      <bean:write name="minDate" property="bookingDate" formatKey="format.longDateFormat"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.end"/>
			    </th>
			    <td align="left">
			      <bean:write name="maxDate" property="bookingDate" formatKey="format.longDateFormat"/>
			    </td>
			  </tr>
        <tr>
          <th align="left" class="label">
            <bean:message key="label.days"/>
          </td>
          <td>
            <bean:write name="OrderStaffFormAgy" property="noOfDates"/>
          </td>
        </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.totalHours" />
			    </th>
			    <td align="left">
			      <bean:write name="OrderStaffFormAgy" property="totalHours" format="#0.00"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
						<bean:message key="label.rrpRate"/>
			    </th>
			    <td align="left">
						<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="hourlyRate" format="#,##0.00" />
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
						<bean:message key="label.rrpValue"/>
			    </th>
			    <td align="left">
						<bean:message key="label.currencySymbol"/><bean:write name="OrderStaffFormAgy" property="rrp" format="#,##0.00" />
			    </td>
			  </tr>
      </table>
	    </td>
	  </tr>
	</table>    
</html:form>

<script type="text/javascript">
<!--

theFormAgy = document.OrderStaffFormAgy;

function sortCheckBoxes() {
<logic:equal name="OrderStaffFormAgy" property="location.singleCandidateShow" value="true">
	theFormAgy.singleCandidate.value = theFormAgy.singleCandidateStr.checked;
</logic:equal>
<logic:equal name="OrderStaffFormAgy" property="location.cvRequiredShow" value="true">
	theFormAgy.cvRequired.value = theFormAgy.cvRequiredStr.checked;
</logic:equal>
<logic:equal name="OrderStaffFormAgy" property="location.interviewRequiredShow" value="true">
	theFormAgy.interviewRequired.value = theFormAgy.interviewRequiredStr.checked;
</logic:equal>
<logic:equal name="OrderStaffFormAgy" property="location.canProvideAccommodationShow" value="true">
	theFormAgy.canProvideAccommodation.value = theFormAgy.canProvideAccommodationStr.checked;
</logic:equal>
<logic:equal name="OrderStaffFormAgy" property="location.carRequiredShow" value="true">
	theFormAgy.carRequired.value = theFormAgy.carRequiredStr.checked;
</logic:equal>

theFormAgy.autoFill.value = theFormAgy.autoFillStr.checked;
theFormAgy.autoActivate.value = theFormAgy.autoActivateStr.checked;

}

<logic:equal name="OrderStaffFormAgy" property="location.singleCandidateShow" value="true">
theFormAgy.singleCandidateStr.checked = theFormAgy.singleCandidate.value == "true";
</logic:equal>
<logic:equal name="OrderStaffFormAgy" property="location.cvRequiredShow" value="true">
theFormAgy.cvRequiredStr.checked = theFormAgy.cvRequired.value == "true";
</logic:equal>
<logic:equal name="OrderStaffFormAgy" property="location.interviewRequiredShow" value="true">
theFormAgy.interviewRequiredStr.checked = theFormAgy.interviewRequired.value == "true";
</logic:equal>
<logic:equal name="OrderStaffFormAgy" property="location.canProvideAccommodationShow" value="true">
theFormAgy.canProvideAccommodationStr.checked = theFormAgy.canProvideAccommodation.value == "true";
</logic:equal>
<logic:equal name="OrderStaffFormAgy" property="location.carRequiredShow" value="true">
theFormAgy.carRequiredStr.checked = theFormAgy.carRequired.value == "true";
</logic:equal>

theFormAgy.autoFillStr.checked = theFormAgy.autoFill.value == "true";
theFormAgy.autoActivateStr.checked = theFormAgy.autoActivate.value == "true";

//-->
</script>

