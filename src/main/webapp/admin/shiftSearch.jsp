<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.shiftSearch"/>
		</td>
  </tr>
</table>
<html:errors/>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<html:form action="/shiftSearchProcess.do" focus="fromDate">
  <tr>
    <td width="80%">
<table class="simple" width="100%">
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
    <td width="20%" valign="center" align="center">
      <html:submit styleClass="titleButton" tabindex="2"><bean:message key="button.search"/></html:submit>
      <br/>
      <br/>
      <html:reset styleClass="titleButton" tabindex="2"><bean:message key="button.reset"/></html:reset>
    </td>
  </tr>
</html:form>
</table>

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

function doYesterday() {
  var yesterdayStr = getDateStr(getDate(-1));
  document.ShiftSearchFormAdmin.fromDate.value = yesterdayStr;
  document.ShiftSearchFormAdmin.toDate.value = yesterdayStr;
}

function doToday() {
  var todayStr = getDateStr(getDate(0));
  document.ShiftSearchFormAdmin.fromDate.value = todayStr;
  document.ShiftSearchFormAdmin.toDate.value = todayStr;
}

function doTomorrow() {
  var tomorrowStr = getDateStr(getDate(+1));
  document.ShiftSearchFormAdmin.fromDate.value = tomorrowStr;
  document.ShiftSearchFormAdmin.toDate.value = tomorrowStr;
}

function doYesterdayTodayTomorrow() {
  var yesterdayStr = getDateStr(getDate(-1));
  document.ShiftSearchFormAdmin.fromDate.value = yesterdayStr;
  var tomorrowStr = getDateStr(getDate(+1));
  document.ShiftSearchFormAdmin.toDate.value = tomorrowStr;
}

function doLastWeek() {
  document.ShiftSearchFormAdmin.fromDate.value = getDateStr(getSOW(getDate(-7)));
  document.ShiftSearchFormAdmin.toDate.value = getDateStr(getEOW(getDate(-7)));
}

function doThisWeek() {
  document.ShiftSearchFormAdmin.fromDate.value = getDateStr(getSOW(getDate(0)));
  document.ShiftSearchFormAdmin.toDate.value = getDateStr(getEOW(getDate(0)));
}

function doNextWeek() {
  document.ShiftSearchFormAdmin.fromDate.value = getDateStr(getSOW(getDate(+7)));
  document.ShiftSearchFormAdmin.toDate.value = getDateStr(getEOW(getDate(+7)));
}

function doLastWeekThisWeekNextWeek() {
  document.ShiftSearchFormAdmin.fromDate.value = getDateStr(getSOW(getDate(-7)));
  document.ShiftSearchFormAdmin.toDate.value = getDateStr(getEOW(getDate(+7)));
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


