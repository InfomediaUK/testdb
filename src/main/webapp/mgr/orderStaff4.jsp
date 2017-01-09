<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<html:form action="/orderStaff5.do" onsubmit="javascript:cal.submitFlatDates(); return singleSubmit();">
<html:hidden property="page" value="4"/>
<html:hidden property="dates" styleId="datesId" />
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <logic:empty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.newBooking"/>
      </logic:empty>
      <logic:notEmpty name="OrderStaffFormMgr" property="booking.bookingId">
      <bean:message key="title.editBooking"/>
      </logic:notEmpty>
      -&nbsp;<bean:message key="title.orderStaff4"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="orderStaffButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="17" />
        <jsp:param name="backButtonTabIndex" value="18" />
      </jsp:include>
    </td>
  </tr>
</html:form>
</table>
<hr/>
<html:errors />
<table width="100%">

<form name="helperFormMgr" action="javascript:doDates(true)" method="POST" onReset="resetDates()"> 
  <tr>
    <td>
<a href="javascript:addOne();" tabIndex="15"><bean:message key="label.more"/></a>
<a href="javascript:takeOne();" tabIndex="16"><bean:message key="label.less"/></a>
  	</td>
  </tr>
  <tr>
    <td class="calPicker">
<div id="flatcalMultiDays" style="float: left"></div>
  	</td>
  </tr>
</table>
<table>
  <tr>
    <td align="left">
      <bean:message key="label.from"/>
    </td>
    <td align="left">
			<input type="text" name="fromDate" id="fromDate" tabindex="1"/>
			<input type="reset" value=" ... " id="fromDateButton" tabindex="2">
	  </td>
    <td align="left" valign="middle" rowspan="2">
      <a href="javascript:doMondayToFriday()" tabindex="5">M-F</a>
	  </td>
    <td align="center">
      <label for="monday">M</label>
	  </td>
    <td align="center">
      <label for="tuesday">T</label>
	  </td>
    <td align="center">
      <label for="wednesday">W</label>
	  </td>
    <td align="center">
      <label for="thursday">T</label>
	  </td>
    <td align="center">
      <label for="friday">F</label>
	  </td>
    <td align="center">
      <label for="saturday">S</label>
	  </td>
    <td align="center">
      <label for="sunday">S</label>
	  </td>
  </tr>
  <tr>
    <td align="left">
			<bean:message key="label.to"/>
    </td>
    <td align="left">
			<input type="text" name="toDate" id="toDate" tabindex="3"/>
			<input type="reset" value=" ... " id="toDateButton" tabindex="4">
		</td>
    <td align="center">
			<input type="checkbox" name="monday" id="monday" value="1" tabindex="6">
		</td>
    <td align="center">
			<input type="checkbox" name="tuesday" id="tuesday" value="2" tabindex="7">
		</td>
    <td align="center">
			<input type="checkbox" name="wednesday" id="wednesday" value="3" tabindex="8">
		</td>
    <td align="center">
			<input type="checkbox" name="thursday" id="thursday" value="4" tabindex="9">
		</td>
    <td align="center">
			<input type="checkbox" name="friday" id="friday" value="5" tabindex="10">
		</td>
    <td align="center">
			<input type="checkbox" name="saturday" id="saturday" value="6" tabindex="11">
		</td>
    <td align="center">
			<input type="checkbox" name="sunday" id="sunday" value="0" tabindex="12">
	  </td>
	</tr>
	<tr>
	  <td align="center" colspan="10">
			<input type="submit" value="<bean:message key="button.updateCalendar"/>" tabindex="13" class="wizardButton">
			<input type="reset" value="<bean:message key="button.resetCalendar"/>" tabindex="14" class="wizardButton">
	  </td>
	</tr>
</table>
</form>

<script type="text/javascript" language="JavaScript">
  <!--
  var focusControl = document.forms["helperFormMgr"].elements["fromDate"];

  if (focusControl.type != "hidden" && !focusControl.disabled) {
     focusControl.focus();
  }
  // -->
</script>

<bean:define id="dates" name="OrderStaffFormMgr" property="dates" type="java.lang.String" />

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

function doMondayToFriday() {
  document.helperFormMgr.monday.checked = true;
  document.helperFormMgr.tuesday.checked = true;
  document.helperFormMgr.wednesday.checked = true;
  document.helperFormMgr.thursday.checked = true;
  document.helperFormMgr.friday.checked = true;
  document.helperFormMgr.saturday.checked = false;
  document.helperFormMgr.sunday.checked = false;
}

// load current selections ...

var MA = [
<%
  // get date s request parameter (comma separated dates in the form yyyy/mm/dd)
  java.util.StringTokenizer datesParser = new java.util.StringTokenizer(dates, ",");
  int i = 0; 
  // for each one ...
  while (datesParser.hasMoreTokens()) {
    // in the format yyyy/mm/dd
    String theDate = datesParser.nextToken();
    // need to separate into yyyy mm dd
    java.util.StringTokenizer dateParser = new java.util.StringTokenizer(theDate, "-");
    Integer theYear = new Integer(dateParser.nextToken());
    Integer theMonth = new Integer(dateParser.nextToken());
    Integer theDay = new Integer(dateParser.nextToken());
    if (i > 0) {
%>
,
<%
    }
%>
new Date(<%= theYear.intValue() %>, <%= theMonth.intValue() - 1 %>, <%= theDay.intValue() %>)    
<%    
    i++;
  }
%>
];

// end hiding contents from old browsers  -->
</script>

<mmj-mgr:manager var="manager"/>
<logic:equal name="manager" property="user.showPageHelp" value="true">
<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers
var canEnterAnyDate = false;
// end hiding contents from old browsers  -->
</script>
</logic:equal>
<logic:notEqual name="manager" property="user.showPageHelp" value="true">
<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers
var canEnterAnyDate = true;
// end hiding contents from old browsers  -->
</script>
</logic:notEqual>

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

// Top optimize performance, Create the variables outside isDisabled.  
// isDisabled is called for EVERY day (e.g. 365 times for a year) 
var dateNextYear=new Date() 
// If you are concerned with leap years then code accordingly 
dateNextYear.setDate(dateNextYear.getDate() + 365) 
var today = new Date()
today.setHours(0);
today.setMinutes(0);
today.setSeconds(0); 

// Disable Function return true if disabled 
function isDisabled(date) {

  if (canEnterAnyDate) {
    return false; // allow all days to be selected - in the past and > 1 year in the future
  }
  else{
    if (date >= dateNextYear) {
      return true;
    }
    else {
      return (date.getTime() < today.getTime()); 
    }
  }  

} 

function flatCallback(cal) {
	// here we'll write the output; this is only for example.  You
	// will normally fill an input field or something with the dates.
	var el = document.getElementById("datesId");

	// reset initial content.
	el.value = "";

	// Reset the "MA", in case one triggers the calendar again.
	// CAREFUL!  You don't want to do "MA = [];".  We need to modify
	// the value of the current array, instead of creating a new one.
	// Zapatec.Calendar.setup is called only once! :-)  So be careful.
	MA.length = 0;

	// walk the calendar's multiple dates selection hash
	for (var i in cal.multiple) {
    	  var currentDate = cal.multiple[i];
 	  // sometimes the date is not actually selected, that's why we need to check.
	  if (currentDate) {
      	    // OK, selected.  Fill an input field.  Or something.  Just for example,
	    // we will display all selected dates in the element having the id "output".
	    if (el.value != "") {
	      el.value += ",";
	    }
	    el.value += currentDate.print("%Y-%m-%d");

	    // and push it in the "MA", in case one triggers the calendar again.
	    MA[MA.length] = currentDate;
	  }
	}
}

var SPECIAL_DAYS = [
<logic:iterate id="publicHoliday" name="OrderStaffFormMgr" property="list" indexId="publicHolidayIndex">
  <logic:notEqual name="publicHolidayIndex" value="0">,</logic:notEqual>
  '<bean:write name="publicHoliday" property="phDate"/>'
</logic:iterate>
];

function dateIsSpecial(year, month, day) {
  for (ii = 0; ii < SPECIAL_DAYS.length; ii++) {
    theMonth = '0' + (month + 1);
    if (theMonth.length > 2) {
      theMonth = theMonth.substr(1);
    }
    theDay = '0' + day;
    if (theDay.length > 2) {
      theDay = theDay.substr(1);
    }
    theDate = year + '-' + theMonth + '-' + theDay;
    if (SPECIAL_DAYS[ii] == theDate) {
	return true;
    }
  }
  return false;
}

function addOne() {
  if (cal.numberMonths < 12) {
    cal.numberMonths = cal.numberMonths + 1;
    cal.refresh();
  }
}
function takeOne() {
  if (cal.numberMonths > 1) {
    cal.numberMonths = cal.numberMonths - 1;
    cal.refresh();
  }
}

function doDates(useFromAndTo) {

  MA.length = 0;
	
  if (useFromAndTo) {
	  
	  fromDate = document.getElementById('fromDate').value;
	  toDate = document.getElementById('toDate').value;
	
	//  %Y-%m-%d
	//  fromYear = fromDate.substr(0,4);
	//  fromMonth = fromDate.substr(5,2);
	//  fromDay = fromDate.substr(8,2);
	//  toYear = toDate.substr(0,4);
	//  toMonth = toDate.substr(5,2);
	//  toDay = toDate.substr(8,2);
	
	//  %d-%m-%y
	  fromDay = fromDate.substr(0,2);
	  fromMonth = fromDate.substr(3,2);
	  fromYear = fromDate.substr(6);
	  toDay = toDate.substr(0,2);
	  toMonth = toDate.substr(3,2);
	  toYear = toDate.substr(6);
	
	  if (fromYear < 100) {
	    fromYear = "20" + fromYear;
	  }  
	  if (toYear < 100) {
	    toYear = "20" + toYear;
	  }  
	
	//  alert(
	//  fromDay + ' ' +
	//  fromMonth + ' ' +
	//  fromYear + ' ' +
	//  toDay + ' ' +
	//  toMonth + ' ' +
	//  toYear);
	  
	  theFromDate = new Date(fromYear, fromMonth - 1, fromDay);
	  theToDate = new Date(toYear, toMonth - 1, toDay);
	
	  while (theFromDate <= theToDate) {
	    dow = theFromDate.getDay();  
	    doit = false;
	    switch (dow){
	      case 0: 
					if (document.helperFormMgr.sunday.checked) doit = true; break; 
	      case 1: 
					if (document.helperFormMgr.monday.checked) doit = true; break;
	      case 2: 
					if (document.helperFormMgr.tuesday.checked) doit = true; break;
	      case 3: 
					if (document.helperFormMgr.wednesday.checked) doit = true; break;
	      case 4: 
					if (document.helperFormMgr.thursday.checked) doit = true; break;
	      case 5: 
					if (document.helperFormMgr.friday.checked) doit = true; break;
	      case 6: 
					if (document.helperFormMgr.saturday.checked) doit = true; break;
	    }
		  if (doit) {
	//  	  alert(dow + ' ' + theFromDate + ' ' + theFromDate.getYear() + ' ' + theFromDate.getMonth() + ' ' + theFromDate.getDate());
	  	  theYear = theFromDate.getYear();
	  	  if (theYear < 200) {
	        // firefox frig 2006 = 106 therefore add 1900 ???
	  	    theYear = theYear + 1900;
	//  	    alert(theYear);
	  	  }
	  	  MA[MA.length] = new Date(theYear, theFromDate.getMonth(), theFromDate.getDate());
	  	}
	    theFromDate.setDate(theFromDate.getDate() + 1);
	  }

  }  
//  alert(MA.length);


  cal.destroy();
  
  // MUST BE THE SAME AS BELOW !!!!
	cal = new Zapatec.Calendar.setup({
	
	  firstDay       : 1,
	  numberMonths   : 2,
	  monthsInRow    : 3,
	  yearStep       : 1,
	
		flat       	   : "flatcalMultiDays", // ID of the parent element
		flatCallback   : flatCallback,
		align      	   : "BR",
		showOthers 	   : true,
		//sortOrder	   : "desc", //default is "asc"ending; or remove comment to sort in "desc"ending order; or "none" to NOT sort the multiple dates.
		multiple   	   : MA, // pass the initial or computed array of multiple dates to be initially selected
	
		dateStatusFunc : function(date, y, m, d) {
				           if (isDisabled(date)) {
				               return "disabled";
				           }
				           else if (dateIsSpecial(y, m, d)) {
	    				       return "zpCalSpecialDay";
				           }
			         },
	
	  disableFunc    : isDisabled
	
		});
  
}

// end hiding contents from old browsers  -->
</script>

<!-- override special days -->
<style type="text/css">
.zpCalSpecialDay { 
	font-weight: bold;
	color: #cc0000;
	background-color: #efebde;
}
.disabled { 
	text-decoration: line-through; 
	color:#000;
}
.edges {
   border : 1px solid;
   border-color: #adaa9c #fff #fff #adaa9c;
   background-color: #fffbee;
}
.edgesSpecialDay {
   font-weight: bold;
   color: #cc0000;
   border : 1px solid;
   border-color: #adaa9c #fff #fff #adaa9c;
   background-color: #fffbee;
}
.between {
   background-color: #dccdb9;
}
.betweenSpecialDay {
   font-weight: bold;
   color: #cc0000;
   background-color: #dccdb9;
}
</style>

<script type="text/javascript">

// MUST BE THE SAME AS ABOVE !!!!
var cal = new Zapatec.Calendar.setup({

  firstDay       : 1,
  numberMonths   : 2,
  monthsInRow    : 3,
  yearStep       : 1,

	flat       	   : "flatcalMultiDays", // ID of the parent element
	flatCallback   : flatCallback,
	align      	   : "BR",
	showOthers 	   : true,
	//sortOrder	   : "desc", //default is "asc"ending; or remove comment to sort in "desc"ending order; or "none" to NOT sort the multiple dates.
	multiple   	   : MA, // pass the initial or computed array of multiple dates to be initially selected

	dateStatusFunc : function(date, y, m, d) {
			           if (isDisabled(date)) {
			               return "disabled";
			           }
			           else if (dateIsSpecial(y, m, d)) {
    				       return "zpCalSpecialDay";
			           }
		         },

  disableFunc    : isDisabled

	});

</script>
<noscript>
  	<br/>
  	This page uses a <a href='http://www.zapatec.com/website/main/products/prod1/'> Javascript Calendar </a>, but
  	your browser does not support Javascript.
  	<br/>
  	Either enable Javascript in your Browser or upgrade to a newer version.
</noscript>

<%-- HELPER FORM STUFF --%>

<script type="text/javascript">
<!--  to hide script contents from old browsers

function dateIsSpecial2(date) {

  return dateIsSpecial(date.getYear(), date.getMonth(), date.getDate());

}


var startDate;
var endDate;
var callbacks = 0;

function resetDates() {
	startDate = endDate = null;
	// also clear calendar
	doDates(false);
}


			/*
			 * Given two dates (in seconds) find out if date1 is bigger, date2 is bigger or
			 * they're the same, taking only the dates, not the time into account.
			 * In other words, different times on the same date returns equal.
			 * returns -1 for date1 bigger, 1 for date2 is bigger 0 for equal
			 */

			function compareDatesOnly(date1, date2) {
				var year1 = date1.getYear();
				var year2 = date2.getYear();
				var month1 = date1.getMonth();
				var month2 = date2.getMonth();
				var day1 = date1.getDate();
				var day2 = date2.getDate();

				if (year1 > year2) {
					return -1;
				}
				if (year2 > year1) {
					return 1;
				}

				//years are equal
				if (month1 > month2) {
					return -1;
				}
				if (month2 > month1) {
					return 1;
				}

				//years and months are equal
				if (day1 > day2) {
					return -1;
				}
				if (day2 > day1) {
					return 1;
				}

				//days are equal
				return 0;


				/* Can't do this because of timezone issues
				var days1 = Math.floor(date1.getTime()/Date.DAY);
				var days2 = Math.floor(date2.getTime()/Date.DAY);
				return (days1 - days2);
				*/
			}

			function filterDates1(cal) {
				startDate = cal.date;
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
						timeFormat     :    "24",
						date           :     startDate,
						electric       :     false,
						showsTime      :     false,          //no time
						disableFunc    :    dateInRange2, //the function to call
						onUpdate       :    filterDates2
					});
				}
			}

			function filterDates2(cal) {
				endDate = cal.date;
			}

			/*
			* Both functions disable and hilight dates.
			*/
			
			/* 
			* Can't choose days after the
			* end date if it is choosen, hilights start and end dates with one style and dates between them with another
			*/
			function dateInRange1(date) {

				if (endDate != null) {

					// Disable dates after end date
					var compareEnd = compareDatesOnly(date, endDate);
					if  (compareEnd < 0) {
						return (true);
					}

					// Hilight end date with "edges" style
					if  (compareEnd == 0) {
					        if (dateIsSpecial2(date)) {
					          return "edgesSpecialDay";
					        }
						return "edges";
					}


					// Hilight inner dates with "between" style
					if (startDate != null){
						var compareStart = compareDatesOnly(date, startDate);
						if  (compareStart < 0) {
						        if (dateIsSpecial2(date)) {
						          return "betweenSpecialDay";
						        }
						        return "between";
						} 
					} 
				}

                                if (!canEnterAnyDate) {
					//disable days prior to today
					var today = new Date();
					var compareToday = compareDatesOnly(date, today);
					if (compareToday > 0) {
						return(true);
					}
				}

			        if (dateIsSpecial2(date)) {
					return "zpCalSpecialDay";
                                }

				//all other days are enabled
				return false;
			}

			/* 
			* Can't choose days before the
			* start date if it is choosen, hilights start and end dates with one style and dates between them with another
			*/

			function dateInRange2(date) {
				if (startDate != null) {
					// Disable dates before start date
					var compareDays = compareDatesOnly(startDate, date);
					if  (compareDays < 0) {
						return (true);
					}

					// Hilight end date with "edges" style
					if  (compareDays == 0) {
					        if (dateIsSpecial2(date)) {
					          return "edgesSpecialDay";
					        }
						return "edges";
					}

					// Hilight inner dates with "between" style
					if ((endDate != null) && (date > startDate) && (date < endDate)) {
						if (dateIsSpecial2(date)) {
						  return "betweenSpecialDay";
						}
						return "between";
					} 
				} 

                                if (!canEnterAnyDate) {
					//disable days prior to today
					var today = new Date();
					var compareToday = compareDatesOnly(date, today);
					if (compareToday > 0) {
						return(true);
					}
				}

			        if (dateIsSpecial2(date)) {
					return "zpCalSpecialDay";
                                }

				//all other days are enabled
				return false;
			}
			// end hiding contents from old browsers  -->
	</script>

		<script type="text/javascript">
		var cal2 = new Zapatec.Calendar.setup({
		
				inputField     :    "fromDate",   // id of the input field
				button         :    "fromDateButton",  // What will trigger the popup of the calendar
				ifFormat       :    "%d/%m/%Y",       // format of the input field
				timeFormat     :    "24",
				showsTime      :     false,          //no time
				electric       :     false,
				dateStatusFunc :    dateInRange1, //the function to call
				onUpdate       :    filterDates1

		});
		
			Zapatec.Calendar.setup({
				inputField     :    "toDate",
				button         :    "toDateButton",  // What will trigger the popup of the calendar
				ifFormat       :    "%d/%m/%Y",
				timeFormat     :    "24",
				showsTime      :     false,          //no time
				electric       :     false,
				dateStatusFunc :    dateInRange2, //the function to call
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








