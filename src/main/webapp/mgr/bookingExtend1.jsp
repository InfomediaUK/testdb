<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>

<bean:parameter id="formAction" name="formAction" value="/bookingExtend2.do"/>

<html:form action="<%= formAction %>" onsubmit="javascript:cal.submitFlatDates(); return singleSubmit();">
<html:hidden property="page" value="1"/>

<html:hidden property="booking.bookingId"/>
<html:hidden property="dates" styleId="datesId" />

<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.extendBooking"/>
      -&nbsp;<bean:message key="title.bookingExtend1"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="orderStaffButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</html:form>
</table>
<hr/>
<html:errors />
<table width="100%">
  <tr>
    <td class="calPicker">
<div id="flatcalMultiDays" style="float: left"></div>
  	</td>
  </tr>
</table>

<bean:define id="dates" name="BookingExtendFormMgr" property="dates" type="java.lang.String" />

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

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

var DISABLED_DAYS = [
<logic:iterate id="bookingDate" name="BookingExtendFormMgr" property="currentBookingDates" indexId="bookingDateIndex">
  <logic:notEqual name="boolkingDateIndex" value="0">,</logic:notEqual>
  '<bean:write name="bookingDate" property="bookingDate"/>'
</logic:iterate>
];

function isDisabled(year, month, day) {
  for (ii = 0; ii < DISABLED_DAYS.length; ii++) {
    theMonth = '0' + (month + 1);
    if (theMonth.length > 2) {
      theMonth = theMonth.substr(1);
    }
    theDay = '0' + day;
    if (theDay.length > 2) {
      theDay = theDay.substr(1);
    }
    theDate = year + '-' + theMonth + '-' + theDay;
    if (DISABLED_DAYS[ii] == theDate) {
	    return true;
    }
  }
  return false;
}

var SPECIAL_DAYS = [
<logic:iterate id="publicHoliday" name="BookingExtendFormMgr" property="list" indexId="publicHolidayIndex">
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

// -->
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
.between {
   background-color: #dccdb9;
}
</style>


<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers

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
			               if (isDisabled(y, m, d)) {
			                 return "disabled";
			               }
			               else if (dateIsSpecial(y, m, d)) {
   		    		           return "zpCalSpecialDay";
		                 }  
	                 },

  disableFunc    : isDisabled

	});

// -->
</script>
<noscript>
  	<br/>
  	This page uses a <a href='http://www.zapatec.com/website/main/products/prod1/'> Javascript Calendar </a>, but
  	your browser does not support Javascript.
  	<br/>
  	Either enable Javascript in your Browser or upgrade to a newer version.
</noscript>
