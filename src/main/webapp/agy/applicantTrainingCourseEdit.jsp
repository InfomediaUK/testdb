<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.helmet.bean.Applicant" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<bean:define id="applicant" name="ApplicantTrainingCourseFormAgy" property="applicant" type="com.helmet.bean.Applicant"/>
<bean:define id="applicantTrainingCourse" name="ApplicantTrainingCourseFormAgy" property="applicantTrainingCourse" type="com.helmet.bean.ApplicantTrainingCourse"/>
<bean:define id="trainingCompanyCourseList" name="ApplicantTrainingCourseFormAgy" property="trainingCompanyCourseList" type="java.util.List"/>
<bean:define id="applicantId" name="applicant" property="applicantId"/>
<bean:define id="disciplineCategoryId" name="applicant" property="disciplineCategoryId"/>
<bean:define id="photoFileUrl" name="ApplicantTrainingCourseFormAgy" property="applicant.photoFileUrl" type="java.lang.String" />

<html:form action="/applicantTrainingCourseEditProcess.do" enctype="multipart/form-data" focus="trainingCourseId" onreset="clearForm()" onsubmit="return singleSubmit();">
  <html:hidden name="ApplicantTrainingCourseFormAgy" property="applicantTrainingCourse.applicantTrainingCourseId" />
  <html:hidden name="ApplicantTrainingCourseFormAgy" property="applicantTrainingCourse.applicantId" />
  <html:hidden name="ApplicantTrainingCourseFormAgy" property="applicantTrainingCourse.documentationFileName" />
  <html:hidden name="ApplicantTrainingCourseFormAgy" property="applicantTrainingCourse.noOfChanges" />
<table cellpadding="0" cellspacing="0" width="100%" height="30" border="0">
  <tr>
	  <td align="left" valign="middle" class="title">
      <bean:message key="title.applicantTrainingCourseEdit"/>
    </td>
    <td align="right" valign="middle" width="80">
      <html:submit styleClass="titleButton" tabindex="2"><bean:message key="button.save"/></html:submit>
    </td>
    <td align="right" valign="middle" width="80">
      <html:reset styleClass="titleButton" tabindex="2" onclick="javascript:clearForm();return false;"><bean:message key="button.reset"/></html:reset>
    </td>
  </tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td width="75%">
  		<table class="simple" width="100%">
		    <tr>
		      <th align="left" class="label"><bean:message key="label.firstName"/></th>
		      <td align="left"><bean:write name="ApplicantTrainingCourseFormAgy" property="applicant.user.firstName"/></td>
		    </tr>
		    <tr>
		      <th align="left" class="label"><bean:message key="label.lastName"/></th>
		      <td align="left"><bean:write name="ApplicantTrainingCourseFormAgy" property="applicant.user.lastName"/></td>
		   	</tr>
		    <tr>
		      <th align="left" class="label"><bean:message key="label.nhsStaffName"/></th>
		      <td align="left"><bean:write name="ApplicantTrainingCourseFormAgy" property="applicant.user.nhsStaffName"/></td>
		    </tr>
		    <tr>
		      <th align="left" class="label"><bean:message key="label.emailAddress"/></th>
		      <td align="left">
		        <logic:empty name="ApplicantTrainingCourseFormAgy" property="applicant.user.emailAddress">
		        &nbsp;
		        </logic:empty>
		        <logic:notEmpty name="ApplicantTrainingCourseFormAgy" property="applicant.user.emailAddress">    
		  	      <html:link forward="sendEmail" paramId="toEmailAddress" paramName="ApplicantTrainingCourseFormAgy" paramProperty="applicant.user.niceEmailAddress" titleKey="title.sendEmail">
		            <bean:write name="ApplicantTrainingCourseFormAgy" property="applicant.user.emailAddress"/>
		          </html:link>
			    </logic:notEmpty>
		      </td>
		    </tr>
		    <tr>
		      <th align="left" class="label">
		        <bean:message key="label.disciplineCategory"/>
		      </th>
		      <td align="left">
			    <bean:write name="ApplicantTrainingCourseFormAgy" property="applicant.disciplineCategoryName"/>&nbsp;
		<logic:present name="ApplicantTrainingCourseFormAgy" property="applicant.regulatorName">
		           (<bean:message key="label.mustRegisterWith"/>&nbsp;<bean:write name="ApplicantTrainingCourseFormAgy" property="applicant.regulatorName"/>)
		</logic:present>
		      </td>
		    </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.trainingCourse"/>
			    </th>
			    <td>
              <html:select name="ApplicantTrainingCourseFormAgy" property="applicantTrainingCourse.trainingCompanyCourseId">
            <html:option value="0"><bean:message key="label.pleaseSelect"/></html:option>
              <html:options collection="trainingCompanyCourseList" labelProperty="nameWithOnlineAndTrainingCompanyName" property="trainingCompanyCourseId" />
            </html:select>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.startDate"/>
			    </th>
			    <td>
			      <html:text property="startDateStr" styleId="startDateStr" title="dd/MM/yyyy" tabindex="1"/>
						<input type="reset" value=" ... " id="startDateStrButton" tabindex="2">
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.endDate"/>
			    </th>
			    <td>
			      <html:text property="endDateStr" styleId="endDateStr" title="dd/MM/yyyy" tabindex="1"/>
				  <input type="reset" value=" ... " id="endDateStrButton" tabindex="2">
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label">
			      <bean:message key="label.documentation" />
			    </th>
			    <td align="left">
			      <html:file property="documentationFormFile" size="50" />&nbsp;<bean:write name="ApplicantTrainingCourseFormAgy" property="applicantTrainingCourse.documentationFileName"/>
			    </td>
			  </tr>
			  <tr>
			    <th align="left" class="label" valign="top"><bean:message key="label.comment"/></th>
			    <td align="left">
			      <html:textarea name="ApplicantTrainingCourseFormAgy" property="applicantTrainingCourse.comment" cols="60" rows="4"/>
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
    document.ApplicantTrainingCourseFormAgy.trainingCourseId.value = '';
    document.ApplicantTrainingCourseFormAgy.trainingCompanyId.value = '';
    document.ApplicantTrainingCourseFormAgy.startDateStr.value = '';
    document.ApplicantTrainingCourseFormAgy.endDateStr.value = '';
    document.ApplicantTrainingCourseFormAgy.comment.value = '';
    loadTrainingCourses();
    resetDates();
  }
	// end hiding contents from old browsers  -->
</script>

    <td width="25%" valign="top" align="center">
<logic:empty name="ApplicantTrainingCourseFormAgy" property="applicant.photoFilename" >
	  <bean:message key="text.noPhotoAvailable"/>
</logic:empty>
<logic:notEmpty name="ApplicantTrainingCourseFormAgy" property="applicant.photoFilename" >
	  <bean:define id="photoFileUrl" name="ApplicantTrainingCourseFormAgy" property="applicant.photoFileUrl" type="java.lang.String" />
	  <html:img src="<%= request.getContextPath() + photoFileUrl %>" width="140" /> <!-- height="180" -->
</logic:notEmpty>
    </td>
  </tr>
</table>
</html:form>

<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers


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
					inputField     :    "endDateStr",
					button         :    "endDateStrButton",  // What will trigger the popup of the calendar
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

         inputField     :    "endDateStr",   // id of the input field
         button         :    "endDateStrButton",  // What will trigger the popup of the calendar
         ifFormat       :    "%d/%m/%Y",       //  of the input field: 18-03-05
         showsTime      :     false,          //no time
         dateStatusFunc    :    disallowDateAfter, //the function to call
         onUpdate       :    filterDates1

		});

      Zapatec.Calendar.setup({
         inputField     :    "startDateStr",
         button         :    "startDateStrButton",  // What will trigger the popup of the calendar
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


