<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.shiftSearch"/>
		</td>
  </tr>
</table>
<html:errors/>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<html:form action="/shiftSearchProcess.do" focus="bookingId" onreset="resetDates()" onsubmit="return singleSubmit();">
  <tr>
    <td width="80%">
<table class="simple" width="100%">
  <tr>
    <th>
      <bean:message key="label.bookingNo"/>
    </th>
    <td>
      <html:text property="bookingId" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.shiftNo"/>
    </th>
    <td>
      <html:text property="shiftNo" tabindex="1"/>
    </td>
  </tr>
<%--
  <tr>
    <th>
      <bean:message key="label.loginId"/>
    </th>
    <td>
      <html:text property="loginId" tabindex="1"/>
    </td>
  </tr>
--%>
  <tr>
    <th>
      <bean:message key="label.invoiceNo"/>
    </th>
    <td>
      <html:text property="invoiceId" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.shiftStatus"/>
    </th>
    <td>
      <html:select property="bookingDateStatus" tabindex="1">
<html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
<% 
for (int i = 0; i < com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_DESCRIPTION_KEYS.length; i++) {
%>
<html:option value="<%= String.valueOf(i) %>"><bean:message key="<%= com.helmet.bean.BookingDate.BOOKINGDATE_STATUS_DESCRIPTION_KEYS[i] %>"/></html:option>
<%
}
%>
      </html:select>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.timesheetStatus"/>
    </th>
    <td>
      <html:select property="workedStatus" tabindex="1">
<html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
<% 
// starting from 1 as NOT showing DRAFT
for (int i = 1; i < com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_DESCRIPTION_KEYS.length; i++) {
%>
<html:option value="<%= String.valueOf(i) %>"><bean:message key="<%= com.helmet.bean.BookingDate.BOOKINGDATE_WORKEDSTATUS_DESCRIPTION_KEYS[i] %>"/></html:option>
<%
}
%>
      </html:select>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.singleCandidate"/>
    </th>
    <td>
      <html:select property="singleCandidate" tabindex="1">
<html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
<html:option value="true"><bean:message key="label.yes"/></html:option>
<html:option value="false"><bean:message key="label.no"/></html:option>
      </html:select>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.activated"/>
    </th>
    <td>
      <html:select property="activated" tabindex="1">
<html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
<html:option value="true"><bean:message key="label.yes"/></html:option>
<html:option value="false"><bean:message key="label.no"/></html:option>
      </html:select>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.site"/>
    </th>
    <td>
      <div id="select1" style="display:block">
      <html:select property="siteId" onchange="javascript:loadLocations()" style="width:400px" styleId="siteId" tabindex="1">
      </html:select>
      </div>
      <div id="text1" style="display:none">
      <input type="text" disabled="disabled" style="width:397px; height:17px; border-width:2px; border-color:white; border-style:none" value="<bean:message key="label.loading"/>"/>
      </div>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.location"/>
    </th>
    <td>
      <div id="select2" style="display:block">
      <html:select property="locationId" onchange="javascript:loadJobProfiles()" style="width:400px" styleId="locationId" tabindex="1">
      </html:select>
      </div>
      <div id="text2" style="display:none">
      <input type="text" disabled="disabled" style="width:397px; height:17px; border-width:2px; border-color:white; border-style:none" value="<bean:message key="label.loading"/>"/>
      </div>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.jobProfile"/>
    </th>
    <td>
      <div id="select3" style="display:block">
      <html:select property="jobProfileId" style="width:400px" styleId="jobProfileId" tabindex="1">
      </html:select>
      </div>
      <div id="text3" style="display:none">
      <input type="text" disabled="disabled" style="width:397px; height:17px; border-width:2px; border-color:white; border-style:none" value="<bean:message key="label.loading"/>"/>
      </div>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.fromDate"/>
    </th>
    <td>
      <html:text property="fromDate" styleId="fromDate" title="dd/MM/yyyy" tabindex="1"/>
			<input type="reset" value=" ... " id="fromDateButton" tabindex="2">
      <a href="javascript:doYesterday()" tabindex="2">yesterday</a>
      <a href="javascript:doToday()" tabindex="2">today</a>
      <a href="javascript:doTomorrow()" tabindex="2">tomorrow</a>
      <a href="javascript:doYesterdayTodayTomorrow()" tabindex="2">all 3</a>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.toDate"/>
    </th>
    <td>
      <html:text property="toDate" styleId="toDate" title="dd/MM/yyyy" tabindex="1"/>
			<input type="reset" value=" ... " id="toDateButton" tabindex="2">
      <a href="javascript:doLastWeek()" tabindex="2">last week</a>
      <a href="javascript:doThisWeek()" tabindex="2">this week</a>
      <a href="javascript:doNextWeek()" tabindex="2">next week</a>
      <a href="javascript:doLastWeekThisWeekNextWeek()" tabindex="2">all 3</a>
    </td>
  </tr>
</table>
    </td>

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

function setFormAction(formName, actionName) {
  actionName = '<%= request.getContextPath() %>' + '/mgr/' + actionName;
  document.forms[formName].action = actionName;
}

function doSearch(formName, actionName) {
  setFormAction(formName, actionName);
  return true;
}

function doSearchAndSubmit(formName, actionName) {
  setFormAction(formName, actionName);
  document.forms[formName].submit();
}

// end hiding contents from old browsers  -->
</script>

<script type="text/javascript">
	<!--  to hide script contents from old browsers
  function clearForm() {
    document.ShiftSearchFormMgr.bookingId.value = '';
    document.ShiftSearchFormMgr.shiftNo.value = '';
    document.ShiftSearchFormMgr.invoiceId.value = '';
    document.ShiftSearchFormMgr.bookingDateStatus.value = '';
    document.ShiftSearchFormMgr.singleCandidate.value = '';
    document.ShiftSearchFormMgr.activated.value = '';
    document.ShiftSearchFormMgr.siteId.value = '';
    document.ShiftSearchFormMgr.locationId.value = '';
    document.ShiftSearchFormMgr.jobProfileId.value = '';
    document.ShiftSearchFormMgr.fromDate.value = '';
    document.ShiftSearchFormMgr.toDate.value = '';
    document.ShiftSearchFormMgr.workedStatus.value = '';
    loadSites();
    resetDates();
  }
	// end hiding contents from old browsers  -->
</script>

    <td width="20%" valign="center" align="center">
      <html:submit styleClass="titleButton" tabindex="2"><bean:message key="button.search"/></html:submit>
      <br/>
      <br/>
      <html:reset styleClass="titleButton" tabindex="2" onclick="javascript:clearForm();return false;"><bean:message key="button.reset"/></html:reset>
<%--
      <html:submit styleClass="titleButton" onclick="javascript:doSearch('ShiftSearchFormMgr., 'shiftSearchProcess.do')"><bean:message key="button.search"/></html:submit>
      <br/>
      <html:submit styleClass="titleButton" onclick="javascript:doSearch('ShiftSearchFormMgr., 'shiftSearchProcess2.do')"><bean:message key="button.search"/></html:submit>
--%>
    </td>
  </tr>
</html:form>
</table>



<script type="text/javascript">
<!--  to hide script contents from old browsers

var theRequest1;
var theRequest2;
var theRequest3;

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
  else if (id == "2") {
		theRequest2 = getRequest();
		theRequest2.open("POST", url, true);
		theRequest2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		theRequest2.onreadystatechange = callbackMethodx;
		theRequest2.send(null);
  }
  else {
		theRequest3 = getRequest();
		theRequest3.open("POST", url, true);
		theRequest3.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		theRequest3.onreadystatechange = callbackMethodx;
		theRequest3.send(null);
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
  else if (id == "2") {
		if ((theRequest2.readyState == 4) && (theRequest2.status == 200)) {
			var responseXML = theRequest2.responseXML;
	  }
  } 
  else {
		if ((theRequest3.readyState == 4) && (theRequest3.status == 200)) {
			var responseXML = theRequest3.responseXML;
	  }
  } 

  if (responseXML) {
	  locations = responseXML.getElementsByTagName("list")[0];

    if (locations) {
		  // Loop over the items in the cart
		  items = locations.getElementsByTagName("item");
	
		  theSelectField = getSelectField(selectField);


//     	alert(theSelectField.value); // + " " + theSelectField.options[theSelectField.selectedIndex].value + " " + theSelectField.selectedIndex);

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

function sitesCallbackMethod() {
	callbackMethod("1", "siteId", "");
}

function locationsCallbackMethod() {
	callbackMethod("2", "locationId", "");
}

function jobProfilesCallbackMethod() {
	callbackMethod("3", "jobProfileId", "");
}

function sitesCallbackMethodAndSetUsingRequestValue() {
	callbackMethod("1", "siteId", "<%= request.getParameter("siteId") == null ? "" : request.getParameter("siteId") %>");
}

function locationsCallbackMethodAndSetUsingRequestValue() {
	callbackMethod("2", "locationId", "<%= request.getParameter("locationId") == null ? "" : request.getParameter("locationId") %>");
}

function jobProfilesCallbackMethodAndSetUsingRequestValue() {
	callbackMethod("3", "jobProfileId", "<%= request.getParameter("jobProfileId") == null ? "" : request.getParameter("jobProfileId") %>");
}

function loadSites() {
	loadSelectField("1", "<%= request.getContextPath() %>/mgr/shiftSearchXML.do?type=site", sitesCallbackMethod);
  loadLocations();
}

function loadLocations() {
	loadSelectField("2", "<%= request.getContextPath() %>/mgr/shiftSearchXML.do?type=location&siteId=" + getSelectField("siteId").value, locationsCallbackMethod);
  loadJobProfiles();
}

function loadJobProfiles() {
	loadSelectField("3", "<%= request.getContextPath() %>/mgr/shiftSearchXML.do?type=jobProfile&siteId=" + getSelectField("siteId").value + "&locationId=" + getSelectField("locationId").value, jobProfilesCallbackMethod);
}

function loadSitesAndSetUsingRequestValue() {
	loadSelectField("1", "<%= request.getContextPath() %>/mgr/shiftSearchXML.do?type=site", sitesCallbackMethodAndSetUsingRequestValue);
  loadLocationsAndSetUsingRequestValue();
}

function loadLocationsAndSetUsingRequestValue() {
	loadSelectField("2", "<%= request.getContextPath() %>/mgr/shiftSearchXML.do?type=location&siteId=<%= request.getParameter("siteId") == null ? "" : request.getParameter("siteId") %>", locationsCallbackMethodAndSetUsingRequestValue);
  loadJobProfilesAndSetUsingRequestValue();
}

function loadJobProfilesAndSetUsingRequestValue() {
	loadSelectField("3", "<%= request.getContextPath() %>/mgr/shiftSearchXML.do?type=jobProfile&siteId=<%= request.getParameter("siteId") == null ? "" : request.getParameter("siteId") %>&locationId=<%= request.getParameter("locationId") == null ? "" : request.getParameter("locationId") %>", jobProfilesCallbackMethodAndSetUsingRequestValue);
}

loadSitesAndSetUsingRequestValue();

//-->
</script>

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

function doYesterday() {
  var yesterdayStr = getDateStr(getDate(-1));
  document.ShiftSearchFormMgr.fromDate.value = yesterdayStr;
  document.ShiftSearchFormMgr.toDate.value = yesterdayStr;
}

function doToday() {
  var todayStr = getDateStr(getDate(0));
  document.ShiftSearchFormMgr.fromDate.value = todayStr;
  document.ShiftSearchFormMgr.toDate.value = todayStr;
}

function doTomorrow() {
  var tomorrowStr = getDateStr(getDate(+1));
  document.ShiftSearchFormMgr.fromDate.value = tomorrowStr;
  document.ShiftSearchFormMgr.toDate.value = tomorrowStr;
}

function doYesterdayTodayTomorrow() {
  var yesterdayStr = getDateStr(getDate(-1));
  document.ShiftSearchFormMgr.fromDate.value = yesterdayStr;
  var tomorrowStr = getDateStr(getDate(+1));
  document.ShiftSearchFormMgr.toDate.value = tomorrowStr;
}

function doLastWeek() {
  document.ShiftSearchFormMgr.fromDate.value = getDateStr(getSOW(getDate(-7)));
  document.ShiftSearchFormMgr.toDate.value = getDateStr(getEOW(getDate(-7)));
}

function doThisWeek() {
  document.ShiftSearchFormMgr.fromDate.value = getDateStr(getSOW(getDate(0)));
  document.ShiftSearchFormMgr.toDate.value = getDateStr(getEOW(getDate(0)));
}

function doNextWeek() {
  document.ShiftSearchFormMgr.fromDate.value = getDateStr(getSOW(getDate(+7)));
  document.ShiftSearchFormMgr.toDate.value = getDateStr(getEOW(getDate(+7)));
}

function doLastWeekThisWeekNextWeek() {
  document.ShiftSearchFormMgr.fromDate.value = getDateStr(getSOW(getDate(-7)));
  document.ShiftSearchFormMgr.toDate.value = getDateStr(getEOW(getDate(+7)));
}

function getSOW(date) {
  var theDate = date;
  while (theDate.getDay() != 1) {
    theDate = getDateFromDate(theDate, -1);
  }
  return theDate;
}

function getEOW(date) {
  var theDate = date;
  while (theDate.getDay() != 0) {
    theDate = getDateFromDate(theDate, +1);
  }
  return theDate;
}

function getDateStr(date) {
	theMonth = '0' + (date.getMonth() + 1);
  if (theMonth.length > 2) {
    theMonth = theMonth.substr(1);
  }
  theDay = '0' + date.getDate();
  if (theDay.length > 2) {
    theDay = theDay.substr(1);
  }
	return theDay + "/" + theMonth + "/" + date.getFullYear();
}

function getDate(multiplier) {
  return getDateFromDate(new Date(), multiplier);
}

function getDateFromDate(date, multiplier) {
	second = 1000;
	minute = second * 60;
	hour = minute * 60;
	day = hour * 24;
	todayNumber = date.getTime();

	theDateNumber = todayNumber + (multiplier * day);
	theDate = new Date();
	theDate.setTime(theDateNumber);
  return theDate;
}

// end hiding contents from old browsers  -->
</script>


	<script type="text/javascript">
		<!--  to hide script contents from old browsers
		var startDate;
		var endDate;
		var ONEDAY = 3600 * 24;

		function resetDates() {
			startDate = endDate = null;
		}

		function filterDates1(cal) {
			startDate = cal.date.getTime();
			/* If they haven't chosen an
			end date before we'll set it to the same date as the start date This
			way if the user scrolls in the start date 5 months forward, they don't
			need to do it again for the end date.
			*/

			if (endDate == null) {
				Zapatec.Calendar.setup({
					inputField     :    "toDate",
					button         :    "toDateButton",  // What will trigger the popup of the calendar
					ifFormat       :    "%d/%m/%Y",
					date           :     cal.date,
					showsTime      :     false,          //no time
					dateStatusFunc		:    disallowDateBefore, //the function to call
					onUpdate       :    filterDates1
				});
			}
		}

		function filterDates2(cal) {
			var date = cal.date;
			endDate = date.getTime()
		}

		/*
		* This functions return true to disallow a date
		* and false to allow it.
		*/


		/*
		* Can't choose days before today or before the
		* end date
		*/
		function disallowDateBefore(date) {
//alert("disallowDateBefore " + date + " " + date.getTime() + " " + startDate + " " + (date > (startDate)));
			date = date.getTime();
			if ((startDate != null) && (date > (startDate))) {
				//start date can't be prior to end date
				return true;
			}

//			var now = new Date().getTime();
//			if (date < (now - ONEDAY)) {
//				//start date can't be prior to today
//				return true;
//			}

			return false;
		}

		/*
		* Can't choose days before today or before the
		* start date
		*/
		function disallowDateAfter(date) {
//alert("disallowDateAfter " + date + " " + date.getTime() + " " + endDate + " " + (date < (endDate)));
			date = date.getTime();
			if ((endDate != null) && (date < (endDate))) {
				//end date can't be before start date
				return true;
			}

//			var now = new Date().getTime();
//			if (date < (now - ONEDAY)) {
//				//end date can't be prior to today
//				return true;
//			}

			return false;
		}

		// end hiding contents from old browsers  -->
	</script>

	<script type="text/javascript">
		var cal = new Zapatec.Calendar.setup({

         inputField     :    "toDate",   // id of the input field
         button         :    "toDateButton",  // What will trigger the popup of the calendar
         ifFormat       :    "%d/%m/%Y",       //  of the input field: 18-03-05
         showsTime      :     false,          //no time
         dateStatusFunc    :    disallowDateAfter, //the function to call
         onUpdate       :    filterDates1

		});

      Zapatec.Calendar.setup({
         inputField     :    "fromDate",
         button         :    "fromDateButton",  // What will trigger the popup of the calendar
         ifFormat       :    "%d/%m/%Y",       //  of the input field: 18-03-05
         showsTime      :     false,          //no time
         dateStatusFunc    :    disallowDateBefore, //the function to call
         onUpdate       :    filterDates2
      });

	</script>
	<noscript>
   	<br/>
   	This page uses a <a href='http://www.zapatec.com/website/main/products/prod1/'> Javascript Calendar </a>, but
   	your browser does not support Javascript.
   	<br/>
   	Either enable Javascript in your Browser or upgrade to a newer version.
	</noscript>


