<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.locationNew"/>

<br/>
<br/>

<html:errors/>

<html:form action="locationNewProcess.do" onsubmit="javascript:sortCheckBoxes();" focus="location.name">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="site.name" /> <%-- to preserve value --%>
<html:hidden property="site.countryName" /> <%-- to preserve value --%>

<html:hidden property="location.siteId" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="LocationFormAdmin" property="client.name"/> (<bean:write name="LocationFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.site"/></td>
    <td align="left"><bean:write name="LocationFormAdmin" property="site.name"/> (<bean:write name="LocationFormAdmin" property="site.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><html:text property="location.name" maxlength="50" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.nhsWard"/></th>
    <td align="left" colspan="3"><html:text property="location.nhsWard" maxlength="50" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><html:text property="location.description" maxlength="50" size="50"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><html:text property="location.code"/></td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left">
      <bean:message key="label.contact" />
    </th>
  </tr>
  <tr>
    <td align="left">
      <bean:message key="label.contactName" />
    </td>
    <td align="left">
      <html:text property="location.contactName" maxlength="50"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      <bean:message key="label.contactJobTitle" />
    </td>
    <td align="left">
      <html:text property="location.contactJobTitle" maxlength="50"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      <bean:message key="label.contactEmailAddress" />
    </td>
    <td align="left">
      <html:text property="location.contactEmailAddress" maxlength="320"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      <bean:message key="label.contactTelephoneNumber" />
    </td>
    <td align="left"><html:text property="location.contactTelephoneNumber" maxlength="20"/></td>
  </tr>
  <tr>
    <td align="left">
		  <label for="singleCandidateShowStrId"><bean:message key="label.singleCandidateShow"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="singleCandidateShowStr" id="singleCandidateShowStrId">
    </td>
  </tr>
  <tr>
    <td align="left">
		  <label for="singleCandidateDefaultStrId"><bean:message key="label.singleCandidateDefault"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="singleCandidateDefaultStr" id="singleCandidateDefaultStrId">
    </td>
  </tr>
  <tr>
    <td align="left">
		  <label for="cvRequiredShowStrId"><bean:message key="label.cvRequiredShow"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="cvRequiredShowStr" id="cvRequiredShowStrId">
    </td>
  </tr>
  <tr>
    <td align="left">
		  <label for="cvRequiredDefaultStrId"><bean:message key="label.cvRequiredDefault"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="cvRequiredDefaultStr" id="cvRequiredDefaultStrId">
    </td>
  </tr>
  <tr>
    <td align="left">
		  <label for="interviewRequiredShowStrId"><bean:message key="label.interviewRequiredShow"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="interviewRequiredShowStr" id="interviewRequiredShowStrId">
    </td>
  </tr>
  <tr>
    <td align="left">
		  <label for="interviewRequiredDefaultStrId"><bean:message key="label.interviewRequiredDefault"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="interviewRequiredDefaultStr" id="interviewRequiredDefaultStrId">
    </td>
  </tr>
  <tr>
    <td align="left">
		  <label for="canProvideAccommodationShowStrId"><bean:message key="label.canProvideAccommodationShow"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="canProvideAccommodationShowStr" id="canProvideAccommodationShowStrId">
    </td>
  </tr>
  <tr>
    <td align="left">
		  <label for="canProvideAccommodationDefaultStrId"><bean:message key="label.canProvideAccommodationDefault"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="canProvideAccommodationDefaultStr" id="canProvideAccommodationDefaultStrId">
    </td>
  </tr>
  <tr>
    <td align="left">
		  <label for="carRequiredShowStrId"><bean:message key="label.carRequiredShow"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="carRequiredShowStr" id="carRequiredShowStrId">
    </td>
  </tr>
  <tr>
    <td align="left">
		  <label for="carRequiredDefaultStrId"><bean:message key="label.carRequiredDefault"/></label>
    </td>
    <td align="left">
		  <input type="checkbox" name="carRequiredDefaultStr" id="carRequiredDefaultStrId">
    </td>
  </tr>
  <tr>
    <td align="left" valign="top"><bean:message key="label.commentsDefault"/></td>
    <td align="left"><html:textarea property="location.commentsDefault" cols="65" rows="10"/></td>
  </tr>
  <html:hidden property="location.singleCandidateShow"/>
  <html:hidden property="location.singleCandidateDefault"/>
  <html:hidden property="location.cvRequiredShow"/>
  <html:hidden property="location.cvRequiredDefault"/>
  <html:hidden property="location.interviewRequiredShow"/>
  <html:hidden property="location.interviewRequiredDefault"/>
  <html:hidden property="location.canProvideAccommodationShow"/>
  <html:hidden property="location.canProvideAccommodationDefault"/>
  <html:hidden property="location.carRequiredShow"/>
  <html:hidden property="location.carRequiredDefault"/>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.save"/></html:submit></td>
  </tr>
</table>

</html:form>

<script type="text/javascript">
<!--

theFormAdmin = document.LocationFormAdmin;

function sortCheckBoxes() {
	theFormAdmin.elements["location.singleCandidateShow"].value = theFormAdmin.singleCandidateShowStr.checked;
	theFormAdmin.elements["location.singleCandidateDefault"].value = theFormAdmin.singleCandidateDefaultStr.checked;
	theFormAdmin.elements["location.cvRequiredShow"].value = theFormAdmin.cvRequiredShowStr.checked;
	theFormAdmin.elements["location.cvRequiredDefault"].value = theFormAdmin.cvRequiredDefaultStr.checked;
	theFormAdmin.elements["location.interviewRequiredShow"].value = theFormAdmin.interviewRequiredShowStr.checked;
	theFormAdmin.elements["location.interviewRequiredDefault"].value = theFormAdmin.interviewRequiredDefaultStr.checked;
	theFormAdmin.elements["location.canProvideAccommodationShow"].value = theFormAdmin.canProvideAccommodationShowStr.checked;
	theFormAdmin.elements["location.canProvideAccommodationDefault"].value = theFormAdmin.canProvideAccommodationDefaultStr.checked;
	theFormAdmin.elements["location.carRequiredShow"].value = theFormAdmin.carRequiredShowStr.checked;
	theFormAdmin.elements["location.carRequiredDefault"].value = theFormAdmin.carRequiredDefaultStr.checked;
}

theFormAdmin.singleCandidateShowStr.checked = theFormAdmin.elements["location.singleCandidateShow"].value == "true";
theFormAdmin.singleCandidateDefaultStr.checked = theFormAdmin.elements["location.singleCandidateDefault"].value == "true";
theFormAdmin.cvRequiredShowStr.checked = theFormAdmin.elements["location.cvRequiredShow"].value == "true";
theFormAdmin.cvRequiredDefaultStr.checked = theFormAdmin.elements["location.cvRequiredDefault"].value == "true";
theFormAdmin.interviewRequiredShowStr.checked = theFormAdmin.elements["location.interviewRequiredShow"].value == "true";
theFormAdmin.interviewRequiredDefaultStr.checked = theFormAdmin.elements["location.interviewRequiredDefault"].value == "true";
theFormAdmin.canProvideAccommodationShowStr.checked = theFormAdmin.elements["location.canProvideAccommodationShow"].value == "true";
theFormAdmin.canProvideAccommodationDefaultStr.checked = theFormAdmin.elements["location.canProvideAccommodationDefault"].value == "true";
theFormAdmin.carRequiredShowStr.checked = theFormAdmin.elements["location.carRequiredShow"].value == "true";
theFormAdmin.carRequiredDefaultStr.checked = theFormAdmin.elements["location.carRequiredDefault"].value == "true";

//-->
</script>

