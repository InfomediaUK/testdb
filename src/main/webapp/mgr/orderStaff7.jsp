<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<%-- focusControl stuff --%>
<bean:define id="focusControl" value="singleCandidateStr"/>
<logic:notEqual name="OrderStaffFormMgr" property="location.singleCandidateShow" value="true">
  <bean:define id="focusControl" value="cvRequiredStr"/>
  <logic:notEqual name="OrderStaffFormMgr" property="location.cvRequiredShow" value="true">
	  <bean:define id="focusControl" value="interviewRequiredStr"/>
	  <logic:notEqual name="OrderStaffFormMgr" property="location.interviewRequiredShow" value="true">
		  <bean:define id="focusControl" value="canProvideAccommodationStr"/>
		  <logic:notEqual name="OrderStaffFormMgr" property="location.canProvideAccommodationShow" value="true">
			  <bean:define id="focusControl" value="carRequiredStr"/>
			  <logic:notEqual name="OrderStaffFormMgr" property="location.carRequiredShow" value="true">
  			  <bean:define id="focusControl" value="dressCode0"/>
			  </logic:notEqual>
		  </logic:notEqual>
	  </logic:notEqual>
  </logic:notEqual>
</logic:notEqual>
<html:form action="/orderStaff8.do" onsubmit="javascript:sortCheckBoxes(); return singleSubmit();" focus="<%= focusControl %>">
<html:hidden property="page" value="7"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <logic:empty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.newBooking"/>
      </logic:empty>
      <logic:notEmpty name="OrderStaffFormMgr" property="booking.bookingId">
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
<logic:equal name="OrderStaffFormMgr" property="location.singleCandidateShow" value="true">
<input tabindex="1" type="checkbox" name="singleCandidateStr" id="singleCandidateStrId">
<label for="singleCandidateStrId">
<bean:message key="label.singleCandidate"/>
</label>
<br/>
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.cvRequiredShow" value="true">
<input tabindex="1" type="checkbox" name="cvRequiredStr" id="cvRequiredStrId">
<label for="cvRequiredStrId">
<bean:message key="label.cvRequired"/>
</label>
<br/>
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.interviewRequiredShow" value="true">
<input tabindex="1" type="checkbox" name="interviewRequiredStr" id="interviewRequiredStrId">
<label for="interviewRequiredStrId">
<bean:message key="label.interviewRequired"/>
</label>
<br/>
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.canProvideAccommodationShow" value="true">
<input tabindex="1" type="checkbox" name="canProvideAccommodationStr" id="canProvideAccommodationStrId">
<label for="canProvideAccommodationStrId">
<bean:message key="label.canProvideAccommodation"/>
</label>
<br/>
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.carRequiredShow" value="true">
<input tabindex="1" type="checkbox" name="carRequiredStr" id="carRequiredStrId">
<label for="carRequiredStrId">
<bean:message key="label.carRequired"/>
</label>
<br/>
</logic:equal>
<input tabindex="1" type="checkbox" name="autoFillStr" id="autoFillStrId">
<label for="autoFillStrId">
<bean:message key="label.autoFill"/>
</label>
<html:hidden property="autoFill"/>
<br/>
<input tabindex="1" type="checkbox" name="autoActivateStr" id="autoActivateStrId">
<label for="autoActivateStrId">
<bean:message key="label.autoActivate"/>
</label>
<html:hidden property="autoActivate"/>
<br/>
<logic:equal name="OrderStaffFormMgr" property="location.singleCandidateShow" value="true">
<html:hidden property="singleCandidate"/>
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.cvRequiredShow" value="true">
<html:hidden property="cvRequired"/>
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.interviewRequiredShow" value="true">
<html:hidden property="interviewRequired"/>
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.canProvideAccommodationShow" value="true">
<html:hidden property="canProvideAccommodation"/>
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.carRequiredShow" value="true">
<html:hidden property="carRequired"/>
</logic:equal>
<logic:notEmpty name="OrderStaffFormMgr" property="list">
  <br/>
  <logic:equal name="OrderStaffFormMgr" property="noOfDressCodes" value="1">
  <table class="simple">
    <tr>
      <th class="label">
        <bean:message key="label.dressCode"/>&nbsp;&nbsp;
      </th>
      <td>
        <bean:write name="OrderStaffFormMgr" property="dressCode.name"/>&nbsp;&nbsp;
      </td>
    </tr>
  </table>
  </logic:equal>
  <logic:greaterThan name="OrderStaffFormMgr" property="noOfDressCodes" value="1">
    <bean:message key="label.dressCode"/>
    <table class="radio">
	  <logic:iterate id="dressCode" name="OrderStaffFormMgr" property="list" indexId="dressCodeIndex">
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
</html:form>

<script type="text/javascript">
<!--

theFormMgr = document.OrderStaffFormMgr;

function sortCheckBoxes() {
<logic:equal name="OrderStaffFormMgr" property="location.singleCandidateShow" value="true">
	theFormMgr.singleCandidate.value = theFormMgr.singleCandidateStr.checked;
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.cvRequiredShow" value="true">
	theFormMgr.cvRequired.value = theFormMgr.cvRequiredStr.checked;
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.interviewRequiredShow" value="true">
	theFormMgr.interviewRequired.value = theFormMgr.interviewRequiredStr.checked;
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.canProvideAccommodationShow" value="true">
	theFormMgr.canProvideAccommodation.value = theFormMgr.canProvideAccommodationStr.checked;
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.carRequiredShow" value="true">
	theFormMgr.carRequired.value = theFormMgr.carRequiredStr.checked;
</logic:equal>

theFormMgr.autoFill.value = theFormMgr.autoFillStr.checked;
theFormMgr.autoActivate.value = theFormMgr.autoActivateStr.checked;

}

<logic:equal name="OrderStaffFormMgr" property="location.singleCandidateShow" value="true">
theFormMgr.singleCandidateStr.checked = theFormMgr.singleCandidate.value == "true";
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.cvRequiredShow" value="true">
theFormMgr.cvRequiredStr.checked = theFormMgr.cvRequired.value == "true";
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.interviewRequiredShow" value="true">
theFormMgr.interviewRequiredStr.checked = theFormMgr.interviewRequired.value == "true";
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.canProvideAccommodationShow" value="true">
theFormMgr.canProvideAccommodationStr.checked = theFormMgr.canProvideAccommodation.value == "true";
</logic:equal>
<logic:equal name="OrderStaffFormMgr" property="location.carRequiredShow" value="true">
theFormMgr.carRequiredStr.checked = theFormMgr.carRequired.value == "true";
</logic:equal>

theFormMgr.autoFillStr.checked = theFormMgr.autoFill.value == "true";
theFormMgr.autoActivateStr.checked = theFormMgr.autoActivate.value == "true";

//-->
</script>

