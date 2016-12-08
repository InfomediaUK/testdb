<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<table cellpadding="0" cellspacing="0" width="100%" border="0" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
      <bean:message key="title.applicantUnavailableEdit"/>
		</td>
<logic:equal name="applicant" property="active" value="true">
		<td align="right" valign="middle" width="75">
      <html:form action="/applicantUnavailableProcess.do" onsubmit="javascript:cal.submitFlatDates(); return singleSubmit();">
        <html:hidden property="unavailableDates" styleId="datesId" />
        <html:submit styleId="submitButton" tabindex="1" styleClass="titleButton"><bean:message key="button.save"/></html:submit>
	    </html:form>
    </td>
</logic:equal>
  </tr>
</table>
<hr/>
<table border="0" width="100%">
  <tr>
    <td>
			<div id="flatcalMultiDays" style="float: left"></div>
    </td>
  </tr>
</table>
<bean:define id="unavailableDates" name="ApplicantUnavailableFormZap" property="unavailableDates" type="java.lang.String" />

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

// load current selections ...

var MA = [
<%
  // get date s request parameter (comma separated dates in the form yyyy/mm/dd)
  java.util.StringTokenizer datesParser = new java.util.StringTokenizer(unavailableDates, ",");
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

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers
var canEnterAnyDate = false; // No days in past or > 1 year in the future.
// end hiding contents from old browsers  -->
</script>

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
//  alert(MA.length);
}

var SPECIAL_DAYS = [];

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
/*	text-decoration: line-through; */ 
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
  numberMonths   : 6,
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

<noscript>
<br/>
This page uses a <a href='http://www.zapatec.com/website/main/products/prod1/'> Javascript Calendar </a>, but
your browser does not support Javascript.
<br/>
Either enable Javascript in your Browser or upgrade to a newer version.
</noscript>









