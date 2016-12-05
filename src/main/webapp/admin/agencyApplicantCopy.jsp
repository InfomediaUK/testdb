<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:define id="targetAgencyId" name="AgencyApplicantCopyFormAdmin" property="targetAgencyId" />
<bean:message key="title.agencyApplicantCopy"/>
<br/>
<br/>
<html:form action="agencyApplicantCopySelect.do" onsubmit="return singleSubmit();">
<html:hidden name="AgencyApplicantCopyFormAdmin" property="targetAgencyId"/>
<table class="simple">
  <tr>
    <td align="left">
      <bean:message key="label.sourceAgency"/>
    </td>
    <td>
      <div id="select1" style="display:block">
      <html:select property="sourceAgencyId" onchange="javascript:loadConsultants()" style="width:400px" styleId="sourceAgencyId" tabindex="1">
      </html:select>
      </div>
      <div id="text1" style="display:none">
      <input type="text" disabled="disabled" style="width:397px; height:17px; border-width:2px; border-color:white; border-style:none" value="<bean:message key="label.loading"/>"/>
      </div>
    </td>
  </tr>
  <tr>
    <td align="left">
      <bean:message key="label.consultant"/>
    </td>
    <td>
      <div id="select2" style="display:block">
      <html:select property="consultantId" style="width:400px" styleId="consultantId" tabindex="1">
      </html:select>
      </div>
      <div id="text2" style="display:none">
      <input type="text" disabled="disabled" style="width:397px; height:17px; border-width:2px; border-color:white; border-style:none" value="<bean:message key="label.loading"/>"/>
      </div>
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <html:submit><bean:message key="button.select"/></html:submit>&nbsp;
      <html:reset styleClass="titleButton" tabindex="2" onclick="javascript:clearForm();return false;"><bean:message key="button.reset"/></html:reset>
    </td>
  </tr>
</table>

<script type="text/javascript">
	<!--  to hide script contents from old browsers
  function clearForm() {
    document.AgencyApplicantCopyFormAdmin.sourceAgencyId.value = '';
    document.AgencyApplicantCopyFormAdmin.consultantId.value = '';
    loadAgencies();
  }
	// end hiding contents from old browsers  -->
</script>

</html:form>

<script type="text/javascript">
<!--  to hide script contents from old browsers

var theRequest1;
var theRequest2;

function getRequest() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function loadSelectField(id, url, callbackMethodx) {
  
  if (id == "1") {
		theRequest1 = getRequest();
		theRequest1.open("POST", url, true);
		theRequest1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		theRequest1.onreadystatechange = callbackMethodx;
		theRequest1.send(null);
  }
  else {
		theRequest2 = getRequest();
		theRequest2.open("POST", url, true);
		theRequest2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		theRequest2.onreadystatechange = callbackMethodx;
		theRequest2.send(null);
  }

	document.getElementById("select" + id).style.display = "none";
	document.getElementById("text" + id).style.display = "block";

}

function callbackMethod(id, selectField, value) {

  var responseXML;
  if (id == "1") {
		if ((theRequest1.readyState == 4) && (theRequest1.status == 200)) {
			var responseXML = theRequest1.responseXML;
	  }
  }
  else {
		if ((theRequest2.readyState == 4) && (theRequest2.status == 200)) {
			var responseXML = theRequest2.responseXML;
	  }
  } 

  if (responseXML) {

	  locations = responseXML.getElementsByTagName("list")[0];
    if (locations) {
		  // Loop over the items in the cart
		  items = locations.getElementsByTagName("item");
	
		  theSelectField = getSelectField(selectField);

      var oldValue = theSelectField.value;
      
      if (value != "") {
        oldValue = value;
      }
      
	  	theSelectField.length = 0;
	
	  	theSelectField.length = items.length + 1;
	
			theSelectField.options[0].text = '<bean:message key="label.loading"/>';
			theSelectField.options[0].value = '';
	
			for (var I = 0 ; I < items.length ; I++) {
	
		 		var location = items[I];
	
		    // Extract the text nodes from the name and quantity elements
		    var theId = location.getAttribute("id");
		    var theName = location.getAttribute("name");
	
				theSelectField.options[I+1].text = theName;
				theSelectField.options[I+1].value = theId;
	
	      if (theId == oldValue) {
	        theSelectField.selectedIndex = I+1;
	      }
		  }

			theSelectField.options[0].text = '<bean:message key="label.pleaseSelect"/>';

  	}

  	document.getElementById("select" + id).style.display = "block";
		document.getElementById("text" + id).style.display = "none";
  
  }

}

function getSelectField(selectField) {

	return document.getElementById(selectField);

}

function agenciesCallbackMethod() {
	callbackMethod("1", "sourceAgencyId", "");
}

function consultantsCallbackMethod() {
	callbackMethod("2", "consultantId", "");
}

function agenciesCallbackMethodAndSetUsingRequestValue() {
	callbackMethod("1", "sourceAgencyId", "<%= request.getParameter("sourceAgencyId") == null ? "" : request.getParameter("sourceAgencyId") %>");
}

function consultantsCallbackMethodAndSetUsingRequestValue() {
	callbackMethod("2", "consultantId", "<%= request.getParameter("consultantId") == null ? "" : request.getParameter("consultantId") %>");
}

function loadAgencies() {
	loadSelectField("1", "<%= request.getContextPath() %>/admin/agencyApplicantCopyXML.do?targetAgencyId=<bean:write name="targetAgencyId" />&type=agency", agenciesCallbackMethod);
  loadConsultants();
}

function loadConsultants() {
	loadSelectField("2", "<%= request.getContextPath() %>/admin/agencyApplicantCopyXML.do?targetAgencyId=<bean:write name="targetAgencyId" />&type=consultant&sourceAgencyId=" + getSelectField("sourceAgencyId").value, consultantsCallbackMethod);
}

function loadAgenciesAndSetUsingRequestValue() {
	loadSelectField("1", "<%= request.getContextPath() %>/admin/agencyApplicantCopyXML.do?targetAgencyId=<bean:write name="targetAgencyId" />&type=agency", agenciesCallbackMethodAndSetUsingRequestValue);
  loadConsultantsAndSetUsingRequestValue();
}

function loadConsultantsAndSetUsingRequestValue() {
	loadSelectField("2", "<%= request.getContextPath() %>/admin/agencyApplicantCopyXML.do?targetAgencyId=<bean:write name="targetAgencyId" />&type=consultant&sourceAgencyId=" + getSelectField("sourceAgencyId").value, consultantsCallbackMethodAndSetUsingRequestValue);
}

loadAgenciesAndSetUsingRequestValue();

//-->
</script>

