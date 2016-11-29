<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
<html:form action="/locationEditProcess.do" focus="location.name" onsubmit="sortCheckBoxes(); return singleSubmit();">
<html:hidden property="location.siteId"/>
<html:hidden property="location.locationId"/>
<html:hidden property="location.noOfChanges"/>
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.locationEdit"/>
		</td>
    <mmj-agy:hasAccess forward="locationEdit">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.save"/></html:submit></td>
		</mmj-agy:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.name"/></th>
    <td align="left" colspan="3"><html:text property="location.name" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.nhsWard"/></th>
    <td align="left" colspan="3"><html:text property="location.nhsWard" maxlength="50" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.description"/></th>
    <td align="left" colspan="3"><html:text property="location.description" maxlength="200" size="50"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left" colspan="3"><html:text property="location.code"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.nhsDayStartTime"/></th>
    <td align="left" colspan="3">
      <html:select property="nhsDayStartHour">
        <html:option value="--"><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="1">01</html:option>
        <html:option value="2">02</html:option>
        <html:option value="3">03</html:option>
        <html:option value="4">04</html:option>
        <html:option value="5">05</html:option>
        <html:option value="6">06</html:option>
        <html:option value="7">07</html:option>
        <html:option value="8">08</html:option>
        <html:option value="9">09</html:option>
        <html:option value="10">10</html:option>
        <html:option value="11">11</html:option>
        <html:option value="12">12</html:option>
        <html:option value="13">13</html:option>
        <html:option value="14">14</html:option>
        <html:option value="15">15</html:option>
        <html:option value="16">16</html:option>
        <html:option value="17">17</html:option>
        <html:option value="18">18</html:option>
        <html:option value="19">19</html:option>
        <html:option value="20">20</html:option>
        <html:option value="21">21</html:option>
        <html:option value="22">22</html:option>
        <html:option value="23">23</html:option>
      </html:select>:<html:select property="nhsDayStartMinute">
        <html:option value="--"><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="5">05</html:option>
        <html:option value="10">10</html:option>
        <html:option value="15">15</html:option>
        <html:option value="20">20</html:option>
        <html:option value="25">25</html:option>
        <html:option value="30">30</html:option>
        <html:option value="35">35</html:option>
        <html:option value="40">40</html:option>
        <html:option value="45">45</html:option>
        <html:option value="50">50</html:option>
        <html:option value="55">55</html:option>
      </html:select>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.nhsDayEndTime"/></th>
    <td align="left" colspan="3">
      <html:select property="nhsDayEndHour">
        <html:option value="--"><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="1">01</html:option>
        <html:option value="2">02</html:option>
        <html:option value="3">03</html:option>
        <html:option value="4">04</html:option>
        <html:option value="5">05</html:option>
        <html:option value="6">06</html:option>
        <html:option value="7">07</html:option>
        <html:option value="8">08</html:option>
        <html:option value="9">09</html:option>
        <html:option value="10">10</html:option>
        <html:option value="11">11</html:option>
        <html:option value="12">12</html:option>
        <html:option value="13">13</html:option>
        <html:option value="14">14</html:option>
        <html:option value="15">15</html:option>
        <html:option value="16">16</html:option>
        <html:option value="17">17</html:option>
        <html:option value="18">18</html:option>
        <html:option value="19">19</html:option>
        <html:option value="20">20</html:option>
        <html:option value="21">21</html:option>
        <html:option value="22">22</html:option>
        <html:option value="23">23</html:option>
      </html:select>:<html:select property="nhsDayEndMinute">
        <html:option value="--"><bean:message key="label.pleaseSelectShort"/></html:option>
        <html:option value="0">00</html:option>
        <html:option value="5">05</html:option>
        <html:option value="10">10</html:option>
        <html:option value="15">15</html:option>
        <html:option value="20">20</html:option>
        <html:option value="25">25</html:option>
        <html:option value="30">30</html:option>
        <html:option value="35">35</html:option>
        <html:option value="40">40</html:option>
        <html:option value="45">45</html:option>
        <html:option value="50">50</html:option>
        <html:option value="55">55</html:option>
      </html:select>
    </td>
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
      <html:text property="location.contactName" maxlength="50" size="50"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactJobTitle" />
    </th>
    <td align="left" colspan="3">
      <html:text property="location.contactJobTitle" maxlength="50" size="50"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactEmailAddress" />
    </th>
    <td align="left" colspan="3">
      <html:text property="location.contactEmailAddress" maxlength="320" size="50"/>
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

theFormAgy = document.LocationFormAgy;

function sortCheckBoxes() {
	theFormAgy.elements["location.singleCandidateShow"].value = theFormAgy.singleCandidateShowStr.checked;
	theFormAgy.elements["location.singleCandidateDefault"].value = theFormAgy.singleCandidateDefaultStr.checked;
	theFormAgy.elements["location.cvRequiredShow"].value = theFormAgy.cvRequiredShowStr.checked;
	theFormAgy.elements["location.cvRequiredDefault"].value = theFormAgy.cvRequiredDefaultStr.checked;
	theFormAgy.elements["location.interviewRequiredShow"].value = theFormAgy.interviewRequiredShowStr.checked;
	theFormAgy.elements["location.interviewRequiredDefault"].value = theFormAgy.interviewRequiredDefaultStr.checked;
	theFormAgy.elements["location.canProvideAccommodationShow"].value = theFormAgy.canProvideAccommodationShowStr.checked;
	theFormAgy.elements["location.canProvideAccommodationDefault"].value = theFormAgy.canProvideAccommodationDefaultStr.checked;
	theFormAgy.elements["location.carRequiredShow"].value = theFormAgy.carRequiredShowStr.checked;
	theFormAgy.elements["location.carRequiredDefault"].value = theFormAgy.carRequiredDefaultStr.checked;
}

theFormAgy.singleCandidateShowStr.checked = theFormAgy.elements["location.singleCandidateShow"].value == "true";
theFormAgy.singleCandidateDefaultStr.checked = theFormAgy.elements["location.singleCandidateDefault"].value == "true";
theFormAgy.cvRequiredShowStr.checked = theFormAgy.elements["location.cvRequiredShow"].value == "true";
theFormAgy.cvRequiredDefaultStr.checked = theFormAgy.elements["location.cvRequiredDefault"].value == "true";
theFormAgy.interviewRequiredShowStr.checked = theFormAgy.elements["location.interviewRequiredShow"].value == "true";
theFormAgy.interviewRequiredDefaultStr.checked = theFormAgy.elements["location.interviewRequiredDefault"].value == "true";
theFormAgy.canProvideAccommodationShowStr.checked = theFormAgy.elements["location.canProvideAccommodationShow"].value == "true";
theFormAgy.canProvideAccommodationDefaultStr.checked = theFormAgy.elements["location.canProvideAccommodationDefault"].value == "true";
theFormAgy.carRequiredShowStr.checked = theFormAgy.elements["location.carRequiredShow"].value == "true";
theFormAgy.carRequiredDefaultStr.checked = theFormAgy.elements["location.carRequiredDefault"].value == "true";

//-->
</script>

  