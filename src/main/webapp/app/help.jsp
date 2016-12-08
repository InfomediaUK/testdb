<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" 
%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" 
%><br/>

<script type="text/javascript">
<!--

var answers = document.getElementsByTagName('dd');

function toggleAllOn(){
	for (var i = 0; i < answers.length; i++) {
		showIt(answers[i].id);
	}
}

function toggleAllOff(){
	for (var i = 0; i < answers.length; i++) {
		hideIt(answers[i].id);
	}
}

function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      if (oldonload) {
        oldonload();
      }
      func();
    }
  }
}

addLoadEvent(toggleAllOff);

-->
</script>

<style>

dl {
	line-height:150%;
}

dt {
}

dd {
	padding-top: 10px;
	padding-bottom: 10px;
}
	
</style>

<a href="javascript:toggleAllOn();">Show all</a>
-
<a href="javascript:toggleAllOff();">Hide all</a>

<dl>
<dt>
<a href="javascript:toggleIt('answer1')">Tabbed interface explained (Outstanding, Weekly, All)</a>
</dt>
<dd id="answer1">
Match My Job (MMJ) displays shift information in three different tabs
<p>
<img src="<%= request.getContextPath() %>/images/tabsOutstanding.PNG"/>
<br/>
The &quot;Outstanding&quot; tab is the default tab and should initially be empty.
<br/>
It displays any shifts that require an action from you. These will be shifts either awaiting entry or awaiting submission.
</p>
<p>
<img src="<%= request.getContextPath() %>/images/tabsWeekly.PNG"/>
<br/>
The &quot;Weekly&quot; tab displays shifts for the current week.
<br/>		
The links either side of the word &quot;Weekly&quot; allow you to navigate forwards and backwards through the weeks.
<br/>
Use the &quot;&lt;&lt;&lt;&lt;&quot; to navigate back four weeks.<br /> 
Use the &quot;&lt;&lt;&quot; to navigate back two weeks.<br /> 
Use the &quot;&lt;&quot; to navigate to the previous week.<br /> 
Use the &quot;&gt;&quot; to navigate to the next week.<br />
Use the &quot;&gt;&gt;&quot; to navigate foreward two weeks.<br />
Use the &quot;&gt;&gt;&gt;&gt;&quot; to navigate foreward four weeks.
</p>
<p>
<img src="<%= request.getContextPath() %>/images/pdf2PrintButton.PNG" align="left" style="padding-right: 5px"/>
You can ONLY print your weekly timesheet from this tab by clicking on the &quot;PDF2Print&quot; button.
<br/>
This will display a PDF (Portable Document Format) version of your weekly activity enabling to print it. The PDF has your name already printed on it and an area for you to sign and for your line manager to date and sign.
</p>
<p>
<img src="<%= request.getContextPath() %>/images/tabsAll.PNG"/>
<br/>
The &quot;All&quot; tab shows all shifts for your booking in their various states.
</p>
</dd>
<html:link href="#theTop">back to top</html:link> 

<dt>
<a href="javascript:toggleIt('answer2')">Entering your times</a>
</dt>
<dd id="answer2">
<img src="<%= request.getContextPath() %>/images/outstandingTab.PNG"/>
<p>
Use the &quot;Outstanding&quot; tab to view those shifts that are outstanding. 
</p>
<p>
<img src="<%= request.getContextPath() %>/images/enterTimes.PNG"/>
<br/>
Click on the &quot;Enter Times&quot; link against each shift. This displays the default start time, end time and break times for the shift.  
The default times can be amended by MMJ if they are not correct, for example if you always work the same hours and take the same break every day then these can be set as the default. Inform your agency or the MMJ help desk if this is the case.
</p>
<p>
<img src="<%= request.getContextPath() %>/images/dropDowns.PNG" align="left" style="padding-right: 5px"/>
Amend the times as required by using the drop down boxes and add a comment if necessary. 
<br/>
If for you don't take a break on a particular day enter a hyphen in each of the break time fields which implies no break. 
<img src="<%= request.getContextPath() %>/images/hyphenDropDown.PNG" align="right" style="padding-left: 5px"/>
If you don't work at all on a particular day you <b>MUST</b> still enter details for the shift. 
Enter a hyphen in each field which removes all the times and implies no hours worked. 
It is a system requirement that you also enter a reason for not working e.g. Annual Leave or Sick. 
<b>N.B.</b> These &quot;no hour&quot; shifts <b>MUST</b> be submitted for payment along with any worked shifts. 
</p>
<p>
<img src="<%= request.getContextPath() %>/images/saveButton.PNG" align="left" style="padding-right: 5px"/>
When you are happy that all the details are correct press the &quot;Save&quot; button to return you to the &quot;Outstanding&quot; tab.
</p>
<p>
To amend your times click on the times that need amending and make the necessary changes. 
</p>
</dd>
<html:link href="#theTop">back to top</html:link> 

<dt>
<a href="javascript:toggleIt('answer3')">Claiming expenses</a>
</dt>
<dd id="answer3">
<img src="<%= request.getContextPath() %>/images/claim.PNG"/>
<p>
Click on the &quot;Claim&quot; link against each shift to claim your expense. More than one expense can be entered per day. 
<br/>
Select the expense you are claiming, this has to be done one at a time, press the &quot;Next&quot; button. 
<br/>
Quantity
<ul>
<li>Mileage - enter the number of miles</li>
<li>Car Contribution - enter 1 (this can only be claimed once per week - it doesn't matter which day you enter the claim).</li>
<li>Enter a comment in the comment field if relevant e.g. details of a journey, press the &quot;Next&quot; button.</li>
</ul>
<br/>
If you have a receipt to upload then use the &quot;Browse&quot; button to locate the file, press the &quot;Next&quot; button to upload the receipt.
<br/>
If you don't have a receipt to upload, press the &quot;Next&quot; button. 
<br/>
Check the summary details, press the &quot;Finish&quot; button. 
</p>
<p>
To amend an expense claim click on the expense name and make any necessary changes. 
</p>
<p>
To delete an expense claim click on the &quot;D&quot; link next to the expense name and confirm you wish to proceed with the deletion. 
</p>
</dd>
<html:link href="#theTop">back to top</html:link> 

<dt>
<a href="javascript:toggleIt('answer4')">Printing your weekly timesheet</a>
</dt>
<dd id="answer4">
<img src="<%= request.getContextPath() %>/images/weeklyTab.PNG"/>
<p>
To print your weekly timesheet you must be on the &quot;Weekly&quot; tab. 
</p>
<p>
Use the &quot;&lt;&quot; and &quot;&gt;&quot; links either side of the word &quot;Weekly&quot; to navigate to the correct week. 
</p>
<p>
<img src="<%= request.getContextPath() %>/images/pdf2PrintButton.PNG" align="left" style="padding-right: 5px"/>
Click on the &quot;PDF2Print&quot; button.
<br/>
This will display a PDF (Portable Document Format) version of your weekly activity enabling to print it. The PDF has your name already printed on it and an area for you to sign and for your line manager to date and sign.
<br/>
You need to get your line manager to sign your timesheet and then you need to fax/email it to your agency. 
</p>
</dd>
<html:link href="#theTop">back to top</html:link> 

<dt>
<a href="javascript:toggleIt('answer5')">Submitting weekly timesheets/expenses</a>
</dt>
<dd id="answer5">
When the timesheet has been authorised or signed by the your manager you need to submit your timesheet.
<p>
For those timesheets signed in the same week use the &quot;Weekly&quot; tab as this displays shifts for the current week. 
<br/>
<br/>
For those timesheets signed the following week or at a later date use the &quot;Weekly&quot; tab and navigate using the &quot;&lt;&quot; and &quot;&gt;&quot; links to locate the correct week. 
</p>
<p>
<img src="<%= request.getContextPath() %>/images/checkboxes.PNG" align="left" style="padding-right: 5px"/>
<br/>
Check the checkbox to the right of each shift or select the checkbox next to Status to select all shifts.
<br/>
<br/>
<br/>
</p>
<p>
<img src="<%= request.getContextPath() %>/images/submitButton.PNG" align="left" style="padding-right: 5px"/>
Click the &quot;Submit&quot; button.
</p>
<p>
<img src="<%= request.getContextPath() %>/images/confirmButton.PNG" align="left" style="padding-right: 5px"/>
Click the &quot;CONFIRM&quot; button to complete the submission.
</p>
<p>
Submitted shifts will disappear from the &quot;Outstanding&quot; tab but will still be visible in the &quot;Weekly&quot; and &quot;All&quot; tabs.
</p>
</dd>
<html:link href="#theTop">back to top</html:link> 
</dl>





 