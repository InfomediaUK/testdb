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
<a href="javascript:toggleIt('answer1')">Tabbed interface explained</a>
</dt>
<dd id="answer1">
Match My Job (MMJ) displays Profile & Shift information in tabs
<p>
<img src="<%= request.getContextPath() %>/images/tabsOutstanding.PNG"/>
<br/>
The &quot;Registration&quot; tab is the default tab and should initially be empty.
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

</dl>





 