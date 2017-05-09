<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<bean:define id="list" name="ApplicantsCompliancyTestFormAgy" property="applicantsCompliancyTestResultList" type="java.util.List"/>
<logic:notPresent name="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="list">
	<logic:empty name="list">
	  <bean:message key="text.noDetails"/>
	</logic:empty>
	<logic:notEmpty name="list">
		<table class="simple" width="100%">
		  <thead>
		  <tr>
		    <th align="left"><bean:message key="label.name"/></th>
		    <th align="left"><bean:message key="label.emailAddress"/></th>
		    <th align="left"><bean:message key="label.mobile"/></th>
		    <th align="left"><bean:message key="label.availability"/></th>
        <th align="left"><bean:message key="label.disciplineCategory"/></th>
        <th align="left"><bean:message key="label.clientGroup"/></th>
		    <th align="left"><bean:message key="label.registrationNumber"/></th>
		    <th align="center"><bean:message key="label.ftw"/></th>
		    <th align="center"><bean:message key="label.crb"/></th>
		    <th align="center"><bean:message key="label.mt"/></th>
		    <th align="center"><bean:message key="label.compliant"/></th>
		  </tr>
		  </thead>
		  <logic:iterate id="applicant" name="list" indexId="applicantIndex" type="com.helmet.bean.Applicant">
		  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
		    <td align="left">    
		    <mmj-agy:hasAccess forward="applicantView">
		      <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
		        <bean:write name="applicant" property="user.firstName"/>
		        <bean:write name="applicant" property="user.lastName"/>
		      </html:link>
		    </mmj-agy:hasAccess>
		    <mmj-agy:hasNoAccess forward="applicantView">
		        <bean:write name="applicant" property="user.firstName"/>
		        <bean:write name="applicant" property="user.lastName"/>
		    </mmj-agy:hasNoAccess>
		    </td>
		    <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
		    <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>   
		    <td align="left"><bean:write name="applicant" property="availabilityDate" formatKey="format.mediumDateFormat"/></td>   
		    <td align="left"><bean:write name="applicant" property="disciplineCategoryName"/></td>   
		    <td align="left">
	  <logic:equal name="applicant" property="clientGroup" value="1">
          <bean:message key="label.adults"/>
    </logic:equal>
		<logic:equal name="applicant" property="clientGroup" value="2">
          <bean:message key="label.paeds"/>
    </logic:equal>
        </td>   
		    <td align="left"><bean:write name="applicant" property="registrationNumber"/></td>   
		    <td align="center">
					<logic:equal name="applicant" property="hasCurrentFitToWork" value="true">
				    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
				  </logic:equal>
				  <logic:notEqual name="applicant" property="hasCurrentFitToWork" value="true">
				    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
				  </logic:notEqual>
		    </td>
		    <td align="center">
					<logic:equal name="applicant" property="hasCurrentDisclosure" value="true">
				    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
				  </logic:equal>
					<logic:notEqual name="applicant" property="hasCurrentDisclosure" value="true">
				    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
				  </logic:notEqual>
		    </td>
		    <td align="center">
					<logic:equal name="applicant" property="hasCurrentTraining" value="true">
				    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
				  </logic:equal>
					<logic:notEqual name="applicant" property="hasCurrentTraining" value="true">
				    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
				  </logic:notEqual>
		    </td>
		    <td align="center">
					<logic:equal name="applicant" property="compliant" value="true">
				    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
				  </logic:equal>
					<logic:notEqual name="applicant" property="compliant" value="true">
				    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
				  </logic:notEqual>
		    </td>
		  </tr>
		  </logic:iterate>
		</table>
	</logic:notEmpty>
</logic:present>