<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/locationEditProcess.do" focus="location.name" onsubmit="sortCheckBoxes(); return singleSubmit();">
<html:hidden property="location.siteId"/>
<html:hidden property="location.locationId"/>
<html:hidden property="location.noOfChanges"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.locationEdit"/>
		</td>
    <mmj-mgr:hasAccess forward="locationEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.name"/></th>
    <td align="left" colspan="3"><html:text property="location.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.description"/></th>
    <td align="left" colspan="3"><html:text property="location.description"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left" colspan="3"><html:text property="location.code"/></td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left" class="label" colspan="3">
      <bean:message key="label.contact" />
    </th>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactName" />
    </th>
    <td align="left" colspan="3">
      <html:text property="location.contactName" maxlength="50"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactJobTitle" />
    </th>
    <td align="left" colspan="3">
      <html:text property="location.contactJobTitle" maxlength="50"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactEmailAddress" />
    </th>
    <td align="left" colspan="3">
      <html:text property="location.contactEmailAddress" maxlength="320"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactTelephoneNumber" />
    </th>
    <td align="left" colspan="3">
      <html:text property="location.contactTelephoneNumber" maxlength="20"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.commentsDefault"/></th>
    <td align="left" colspan="3"><html:textarea property="location.commentsDefault" cols="65" rows="5"/></td>
  </tr>
  <tr>
    <th align="left" width="45%">
		  <label for="singleCandidateShowStrId"><bean:message key="label.singleCandidateShow"/></label>
    </th>
    <td align="center" width="5%">
		  <input type="checkbox" name="singleCandidateShowStr" id="singleCandidateShowStrId">
    </td>
    <th align="left" width="45%">
		  <label for="singleCandidateDefaultStrId"><bean:message key="label.singleCandidateDefault"/></label>
    </th>
    <td align="center" width="5%">
		  <input type="checkbox" name="singleCandidateDefaultStr" id="singleCandidateDefaultStrId">
    </td>
  </tr>
  <tr>
    <th align="left">
		  <label for="cvRequiredShowStrId"><bean:message key="label.cvRequiredShow"/></label>
    </th>
    <td align="center">
		  <input type="checkbox" name="cvRequiredShowStr" id="cvRequiredShowStrId">
    </td>
    <th align="left">
		  <label for="cvRequiredDefaultStrId"><bean:message key="label.cvRequiredDefault"/></label>
    </th>
    <td align="center">
		  <input type="checkbox" name="cvRequiredDefaultStr" id="cvRequiredDefaultStrId">
    </td>
  </tr>
  <tr>
    <th align="left">
		  <label for="interviewRequiredShowStrId"><bean:message key="label.interviewRequiredShow"/></label>
    </th>
    <td align="center">
		  <input type="checkbox" name="interviewRequiredShowStr" id="interviewRequiredShowStrId">
    </td>
    <th align="left">
		  <label for="interviewRequiredDefaultStrId"><bean:message key="label.interviewRequiredDefault"/></label>
    </th>
    <td align="center">
		  <input type="checkbox" name="interviewRequiredDefaultStr" id="interviewRequiredDefaultStrId">
    </td>
  </tr>
  <tr>
    <th align="left">
		  <label for="canProvideAccommodationShowStrId"><bean:message key="label.canProvideAccommodationShow"/></label>
    </th>
    <td align="center">
		  <input type="checkbox" name="canProvideAccommodationShowStr" id="canProvideAccommodationShowStrId">
    </td>
    <th align="left">
		  <label for="canProvideAccommodationDefaultStrId"><bean:message key="label.canProvideAccommodationDefault"/></label>
    </th>
    <td align="center">
		  <input type="checkbox" name="canProvideAccommodationDefaultStr" id="canProvideAccommodationDefaultStrId">
    </td>
  </tr>
  <tr>
    <th align="left">
		  <label for="carRequiredShowStrId"><bean:message key="label.carRequiredShow"/></label>
    </th>
    <td align="center">
		  <input type="checkbox" name="carRequiredShowStr" id="carRequiredShowStrId">
    </td>
    <th align="left">
		  <label for="carRequiredDefaultStrId"><bean:message key="label.carRequiredDefault"/></label>
    </th>
    <td align="center">
		  <input type="checkbox" name="carRequiredDefaultStr" id="carRequiredDefaultStrId">
    </td>
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
</html:form>
</table>


<script type="text/javascript">
<!--

theFormMgr = document.LocationFormMgr;

function sortCheckBoxes() {
	theFormMgr.elements["location.singleCandidateShow"].value = theFormMgr.singleCandidateShowStr.checked;
	theFormMgr.elements["location.singleCandidateDefault"].value = theFormMgr.singleCandidateDefaultStr.checked;
	theFormMgr.elements["location.cvRequiredShow"].value = theFormMgr.cvRequiredShowStr.checked;
	theFormMgr.elements["location.cvRequiredDefault"].value = theFormMgr.cvRequiredDefaultStr.checked;
	theFormMgr.elements["location.interviewRequiredShow"].value = theFormMgr.interviewRequiredShowStr.checked;
	theFormMgr.elements["location.interviewRequiredDefault"].value = theFormMgr.interviewRequiredDefaultStr.checked;
	theFormMgr.elements["location.canProvideAccommodationShow"].value = theFormMgr.canProvideAccommodationShowStr.checked;
	theFormMgr.elements["location.canProvideAccommodationDefault"].value = theFormMgr.canProvideAccommodationDefaultStr.checked;
	theFormMgr.elements["location.carRequiredShow"].value = theFormMgr.carRequiredShowStr.checked;
	theFormMgr.elements["location.carRequiredDefault"].value = theFormMgr.carRequiredDefaultStr.checked;
}

theFormMgr.singleCandidateShowStr.checked = theFormMgr.elements["location.singleCandidateShow"].value == "true";
theFormMgr.singleCandidateDefaultStr.checked = theFormMgr.elements["location.singleCandidateDefault"].value == "true";
theFormMgr.cvRequiredShowStr.checked = theFormMgr.elements["location.cvRequiredShow"].value == "true";
theFormMgr.cvRequiredDefaultStr.checked = theFormMgr.elements["location.cvRequiredDefault"].value == "true";
theFormMgr.interviewRequiredShowStr.checked = theFormMgr.elements["location.interviewRequiredShow"].value == "true";
theFormMgr.interviewRequiredDefaultStr.checked = theFormMgr.elements["location.interviewRequiredDefault"].value == "true";
theFormMgr.canProvideAccommodationShowStr.checked = theFormMgr.elements["location.canProvideAccommodationShow"].value == "true";
theFormMgr.canProvideAccommodationDefaultStr.checked = theFormMgr.elements["location.canProvideAccommodationDefault"].value == "true";
theFormMgr.carRequiredShowStr.checked = theFormMgr.elements["location.carRequiredShow"].value == "true";
theFormMgr.carRequiredDefaultStr.checked = theFormMgr.elements["location.carRequiredDefault"].value == "true";

//-->
</script>

  