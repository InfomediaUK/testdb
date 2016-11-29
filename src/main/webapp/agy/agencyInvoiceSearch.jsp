<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ 
taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ 
taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="http://displaytag.sf.net" prefix="display" %><%@ 
taglib uri="/mmj-agy" prefix="mmj-agy" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.agencyInvoiceSearch"/>
		</td>
  </tr>
</table>
<html:errors/>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<html:form action="/agencyInvoiceSearchProcess.do" focus="agencyInvoiceId" onreset="resetDates()" onsubmit="return singleSubmit();">
  <tr>
    <td width="80%">
<table class="simple" width="100%">
  <tr>
    <th>
      <bean:message key="label.invoiceNo"/>
    </th>
    <td>
      <html:text property="agencyInvoiceId" tabindex="1"/>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.client"/>
    </th>
    <td>
      <div id="select1" style="display:block">
      <html:select property="clientId" style="width:400px" styleId="clientId" tabindex="1">
      </html:select>
      </div>
      <div id="text1" style="display:none">
      <input type="text" disabled="disabled" style="width:397px; height:17px; border-width:2px; border-color:white; border-style:none" value="<bean:message key="label.loading"/>"/>
      </div>
    </td>
  </tr>
  <tr>
    <th>
      <bean:message key="label.status"/>
    </th>
    <td>
      <html:select property="status" tabindex="1">
<html:option value=""><bean:message key="label.pleaseSelect"/></html:option>
<% 
for (int i = 0; i < com.helmet.bean.AgencyInvoice.AGENCYINVOICE_STATUS_DESCRIPTION_KEYS.length; i++) {
%>
<html:option value="<%= String.valueOf(i) %>"><bean:message key="<%= com.helmet.bean.AgencyInvoice.AGENCYINVOICE_STATUS_DESCRIPTION_KEYS[i] %>"/></html:option>
<%
}
%>
      </html:select>
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
  actionName = '<%= request.getContextPath() %>' + '/agy/' + actionName;
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
    document.AgencyInvoiceSearchFormAgy.agencyInvoiceId.value = '';
    document.AgencyInvoiceSearchFormAgy.status.value = '';
    document.AgencyInvoiceSearchFormAgy.clientId.value = '';
    document.AgencyInvoiceSearchFormAgy.fromDate.value = '';
    document.AgencyInvoiceSearchFormAgy.toDate.value = '';
    loadClients();
    resetDates();
  }
	// end hiding contents from old browsers  -->
</script>

    <td width="20%" valign="center" align="center">
      <html:submit styleClass="titleButton" tabindex="2"><bean:message key="button.search"/></html:submit>
      <br/>
      <br/>
      <html:reset styleClass="titleButton" tabindex="2" onclick="javascript:clearForm();return false;"><bean:message key="button.reset"/></html:reset>
    </td>
  </tr>
</html:form>
</table>

<mmj-agy:consultant var="consultant"/>

<script type="text/javascript">
<!--  to hide script contents from old browsers

var theRequest1;

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

function clientsCallbackMethod() {
	callbackMethod("1", "clientId", "");
}

function clientsCallbackMethodAndSetUsingRequestValue() {
	callbackMethod("1", "clientId", "<%= request.getParameter("clientId") == null ? "" : request.getParameter("clientId") %>");
}

function loadClients() {
	loadSelectField("1", "<%= request.getContextPath() %>/agy/shiftSearchXML.do?agencyId=<bean:write name="consultant" property="agencyId"/>&type=client", clientsCallbackMethod);
}

function loadClientsAndSetUsingRequestValue() {
	loadSelectField("1", "<%= request.getContextPath() %>/agy/shiftSearchXML.do?agencyId=<bean:write name="consultant" property="agencyId"/>&type=client", clientsCallbackMethodAndSetUsingRequestValue);
}

loadClientsAndSetUsingRequestValue();

//-->
</script>

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

function doYesterday() {
  var yesterdayStr = getDateStr(getDate(-1));
  document.AgencyInvoiceSearchFormAgy.fromDate.value = yesterdayStr;
  document.AgencyInvoiceSearchFormAgy.toDate.value = yesterdayStr;
}

function doToday() {
  var todayStr = getDateStr(getDate(0));
  document.AgencyInvoiceSearchFormAgy.fromDate.value = todayStr;
  document.AgencyInvoiceSearchFormAgy.toDate.value = todayStr;
}

function doTomorrow() {
  var tomorrowStr = getDateStr(getDate(+1));
  document.AgencyInvoiceSearchFormAgy.fromDate.value = tomorrowStr;
  document.AgencyInvoiceSearchFormAgy.toDate.value = tomorrowStr;
}

function doYesterdayTodayTomorrow() {
  var yesterdayStr = getDateStr(getDate(-1));
  document.AgencyInvoiceSearchFormAgy.fromDate.value = yesterdayStr;
  var tomorrowStr = getDateStr(getDate(+1));
  document.AgencyInvoiceSearchFormAgy.toDate.value = tomorrowStr;
}

function doLastWeek() {
  document.AgencyInvoiceSearchFormAgy.fromDate.value = getDateStr(getSOW(getDate(-7)));
  document.AgencyInvoiceSearchFormAgy.toDate.value = getDateStr(getEOW(getDate(-7)));
}

function doThisWeek() {
  document.AgencyInvoiceSearchFormAgy.fromDate.value = getDateStr(getSOW(getDate(0)));
  document.AgencyInvoiceSearchFormAgy.toDate.value = getDateStr(getEOW(getDate(0)));
}

function doNextWeek() {
  document.AgencyInvoiceSearchFormAgy.fromDate.value = getDateStr(getSOW(getDate(+7)));
  document.AgencyInvoiceSearchFormAgy.toDate.value = getDateStr(getEOW(getDate(+7)));
}

function doLastWeekThisWeekNextWeek() {
  document.AgencyInvoiceSearchFormAgy.fromDate.value = getDateStr(getSOW(getDate(-7)));
  document.AgencyInvoiceSearchFormAgy.toDate.value = getDateStr(getEOW(getDate(+7)));
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


