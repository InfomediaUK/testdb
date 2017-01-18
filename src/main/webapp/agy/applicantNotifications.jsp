<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-agy" prefix="mmj-agy" %>
<script type="text/javascript">
var checked=false;
var frmname='';
function checkedAll(frmname)
{
  var searchResultsForm = document.getElementById(frmname);
  if (checked == false)
  {
    checked = true;
  }
  else
  {
    checked = false;
  }
  for (var i =0; i < searchResultsForm.elements.length; i++)
  {
    searchResultsForm.elements[i].checked=checked;
  }
}
</script>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.applicantNotifications"/>
		</td>
  </tr>
</table>
<table cellpadding="0" cellspacing="0" width="100%" border="0">
  <tr>
    <td align="left" valign="top" width="100%">
			<div class="tabber">
        <div class="tabbertab">
          <h2><bean:message key="label.new"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantNew" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
            <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
            &nbsp;<bean:message key="label.dependsOnIterviewDate"/>
            <table class="simple" width="100%">
              <thead>
                <tr>
                  <th align="center" width="2%">
                    <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantNew');"/>
                  </th>
                  <th align="left" width="18%">Created</th>
                  <th align="left" width="18%"><bean:message key="label.interviewDate"/></th>
                  <th align="left" width="20%"><bean:message key="label.name"/></th>
                  <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
                  <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
                  <th align="left" width="20%"><bean:message key="label.mobile"/></th>
                </tr>
              </thead>
              <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="newList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
              <% String applicantNewCheckBox = "applicantId"; %>
              <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
                <td align="left" valign="top">
                  <input type="checkbox" name="<%= applicantNewCheckBox %>" value="<%= applicant.getApplicantId() %>">    
                </td>
                <td align="left"><bean:write name="applicant" property="creationTimestamp" formatKey="format.mediumDateFormat" /></td>
						    <td align="left"><bean:write name="applicant" property="interviewDate" formatKey="format.mediumDateFormat" /></td>
                <td align="left">    
                <mmj-agy:hasAccess forward="applicantView">
                  <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                  </html:link>
                </mmj-agy:hasAccess>
                <mmj-agy:hasNoAccess forward="applicantView">
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </mmj-agy:hasNoAccess>
                </td>
                <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
                <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
                <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
              </tr>   
              </logic:iterate>
            </table>
            <input type="hidden" name="emailActionId" value="16">
          </html:form>
        </div>
			  <div class="tabbertab">
				  <h2><bean:message key="label.crb"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantCrbExpiry" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
				    <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
						<table class="simple" width="100%">
						  <thead>
							  <tr>
							    <th align="center" width="2%">
							      <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantCrbExpiry');"/>
			            </th>
							    <th align="left" width="18%"><bean:message key="label.crbExpiryDate"/></th>
							    <th align="left" width="20%"><bean:message key="label.name"/></th>
							    <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
							    <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
							    <th align="left" width="20%"><bean:message key="label.mobile"/></th>
							  </tr>
						  </thead>
						  <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="crbList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
					    <% String applicantCrbExpiryCheckBox = "applicantId"; %>
						  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
						    <td align="left" valign="top">
						      <input type="checkbox" name="<%= applicantCrbExpiryCheckBox %>" value="<%= applicant.getApplicantId() %>">    
						    </td>
						    <td align="left"><bean:write name="applicant" property="crbExpiryDate" formatKey="format.mediumDateFormat" /></td>
						    <td align="left">    
						    <mmj-agy:hasAccess forward="applicantView">
						      <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
						        <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
						      </html:link>
						    </mmj-agy:hasAccess>
						    <mmj-agy:hasNoAccess forward="applicantView">
						        <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
						    </mmj-agy:hasNoAccess>
						    </td>
						    <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
						    <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
						    <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
						  </tr>   
						  </logic:iterate>
						</table>
						<input type="hidden" name="emailActionId" value="1">
					</html:form>
			  </div>
        <div class="tabbertab">
          <h2><bean:message key="label.dbs"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantDbsExpiry" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
            <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
            <table class="simple" width="100%">
              <thead>
                <tr>
                  <th align="center" width="2%">
                    <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantDbsExpiry');"/>
                  </th>
                  <th align="left" width="18%"><bean:message key="label.dbsRenewalDate"/></th>
                  <th align="left" width="20%"><bean:message key="label.name"/></th>
                  <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
                  <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
                  <th align="left" width="20%"><bean:message key="label.mobile"/></th>
                </tr>
              </thead>
              <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="dbsList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
              <% String applicantDbsExpiryCheckBox = "applicantId"; %>
              <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
                <td align="left" valign="top">
                  <input type="checkbox" name="<%= applicantDbsExpiryCheckBox %>" value="<%= applicant.getApplicantId() %>">    
                </td>
                <td align="left"><bean:write name="applicant" property="dbsRenewalDate" formatKey="format.mediumDateFormat" /></td>
                <td align="left">    
                <mmj-agy:hasAccess forward="applicantView">
                  <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                  </html:link>
                </mmj-agy:hasAccess>
                <mmj-agy:hasNoAccess forward="applicantView">
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </mmj-agy:hasNoAccess>
                </td>
                <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
                <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
                <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
              </tr>   
              </logic:iterate>
            </table>
            <input type="hidden" name="emailActionId" value="17">
          </html:form>
        </div>
        <div class="tabbertab">
          <h2><bean:message key="label.fitToWork"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantFitToWorkExpiry" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
            <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
            <table class="simple" width="100%">
              <thead>
                <tr>
                  <th align="center" width="2%">
                    <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantFitToWorkExpiry');"/>
                  </th>
                  <th align="left" width="18%"><bean:message key="label.fitToWorkExpiryDate"/></th>
                  <th align="left" width="20%"><bean:message key="label.name"/></th>
                  <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
                  <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
                  <th align="left" width="20%"><bean:message key="label.mobile"/></th>
                </tr>
              </thead>
              <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="fitToWorkList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
              <% String applicantFitToWorkExpiryCheckBox = "applicantId"; %>
              <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
                <td align="left" valign="top">
                  <input type="checkbox" name="<%= applicantFitToWorkExpiryCheckBox %>" value="<%= applicant.getApplicantId() %>">    
                </td>
                <td align="left"><bean:write name="applicant" property="fitToWorkExpiryDate" formatKey="format.mediumDateFormat" /></td>
                <td align="left">    
                <mmj-agy:hasAccess forward="applicantView">
                  <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                  </html:link>
                </mmj-agy:hasAccess>
                <mmj-agy:hasNoAccess forward="applicantView">
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </mmj-agy:hasNoAccess>
                </td>
                <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
                <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
                <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
              </tr>   
              </logic:iterate>
            </table>
            <input type="hidden" name="emailActionId" value="2">
          </html:form>
        </div>
        <div class="tabbertab">
          <h2><bean:message key="label.professionalRegistration"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantHpcExpiry" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
            <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
            <table class="simple" width="100%">
              <thead>
                <tr>
                  <th align="center" width="2%">
                    <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantHpcExpiry');"/>
                  </th>
                  <th align="left" width="18%"><bean:message key="label.hpcExpiryDate"/></th>
                  <th align="left" width="20%"><bean:message key="label.name"/></th>
                  <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
                  <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
                  <th align="left" width="20%"><bean:message key="label.mobile"/></th>
                </tr>
              </thead>
              <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="hpcList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
              <% String applicantHpcExpiryCheckBox = "applicantId"; %>
              <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
                <td align="left" valign="top">
                  <input type="checkbox" name="<%= applicantHpcExpiryCheckBox %>" value="<%= applicant.getApplicantId() %>">    
                </td>
                <td align="left"><bean:write name="applicant" property="hpcExpiryDate" formatKey="format.mediumDateFormat" /></td>
                <td align="left">    
                <mmj-agy:hasAccess forward="applicantView">
                  <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                  </html:link>
                </mmj-agy:hasAccess>
                <mmj-agy:hasNoAccess forward="applicantView">
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </mmj-agy:hasNoAccess>
                </td>
                <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
                <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
                <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
              </tr>   
              </logic:iterate>
            </table>
            <input type="hidden" name="emailActionId" value="3">
          </html:form>
        </div>
			  <div class="tabbertab">
				  <h2><bean:message key="label.idDocument"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantIdDocumentExpiry" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
				    <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
	          <table class="simple" width="100%">
	            <thead>
	              <tr>
	                <th align="center" width="2%">
	                  <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantIdDocumentExpiry');"/>
	                </th>
	                <th align="left" width="18%"><bean:message key="label.idDocumentExpiryDate"/></th>
	                <th align="left" width="20%"><bean:message key="label.name"/></th>
	                <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
	                <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
	                <th align="left" width="20%"><bean:message key="label.mobile"/></th>
	              </tr>
	            </thead>
	            <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="idDocumentList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
	            <% String applicantIdDocumentExpiryCheckBox = "applicantId"; %>
	            <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	              <td align="left" valign="top">
	                <input type="checkbox" name="<%= applicantIdDocumentExpiryCheckBox %>" value="<%= applicant.getApplicantId() %>">    
	              </td>
	              <td align="left"><bean:write name="applicant" property="idDocumentExpiryDate" formatKey="format.mediumDateFormat" /></td>
	              <td align="left">    
	              <mmj-agy:hasAccess forward="applicantView">
	                <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
	                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
	                </html:link>
	              </mmj-agy:hasAccess>
	              <mmj-agy:hasNoAccess forward="applicantView">
	                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
	              </mmj-agy:hasNoAccess>
	              </td>
	              <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
	              <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
	              <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
	            </tr>   
	            </logic:iterate>
	          </table>
						<input type="hidden" name="emailActionId" value="4">
          </html:form>
				</div>
        <div class="tabbertab">
          <h2><bean:message key="label.training"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantTrainingExpiry" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
            <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
            <table class="simple" width="100%">
              <thead>
                <tr>
                  <th align="center" width="2%">
                    <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantTrainingExpiry');"/>
                  </th>
                  <th align="left" width="18%"><bean:message key="label.trainingExpiryDate"/></th>
                  <th align="left" width="20%"><bean:message key="label.name"/></th>
                  <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
                  <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
                  <th align="left" width="20%"><bean:message key="label.mobile"/></th>
                </tr>
              </thead>
              <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="trainingList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
              <% String applicantTrainingExpiryCheckBox = "applicantId"; %>
              <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
                <td align="left" valign="top">
                  <input type="checkbox" name="<%= applicantTrainingExpiryCheckBox %>" value="<%= applicant.getApplicantId() %>">    
                </td>
                <td align="left"><bean:write name="applicant" property="trainingExpiryDate" formatKey="format.mediumDateFormat" /></td>
                <td align="left">    
                <mmj-agy:hasAccess forward="applicantView">
                  <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                  </html:link>
                </mmj-agy:hasAccess>
                <mmj-agy:hasNoAccess forward="applicantView">
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </mmj-agy:hasNoAccess>
                </td>
                <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
                <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
                <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
              </tr>   
              </logic:iterate>
            </table>
            <input type="hidden" name="emailActionId" value="5">
          </html:form>
        </div>
			  <div class="tabbertab">
				  <h2><bean:message key="label.visa"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantVisaExpiry" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
				    <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
	          <table class="simple" width="100%">
	            <thead>
	              <tr>
	                <th align="center" width="2%">
	                  <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantVisaExpiry');"/>
	                </th>
	                <th align="left" width="18%"><bean:message key="label.visaExpiryDate"/></th>
	                <th align="left" width="20%"><bean:message key="label.name"/></th>
	                <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
	                <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
	                <th align="left" width="20%"><bean:message key="label.mobile"/></th>
	              </tr>
	            </thead>
	            <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="visaList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
	            <% String applicantVisaExpiryCheckBox = "applicantId"; %>
	            <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	              <td align="left" valign="top">
	                <input type="checkbox" name="<%= applicantVisaExpiryCheckBox %>" value="<%= applicant.getApplicantId() %>">    
	              </td>
	              <td align="left"><bean:write name="applicant" property="visaExpiryDate" formatKey="format.mediumDateFormat" /></td>
	              <td align="left">    
	              <mmj-agy:hasAccess forward="applicantView">
	                <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
	                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
	                </html:link>
	              </mmj-agy:hasAccess>
	              <mmj-agy:hasNoAccess forward="applicantView">
	                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
	              </mmj-agy:hasNoAccess>
	              </td>
	              <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
	              <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
	              <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
	            </tr>   
	            </logic:iterate>
	          </table>
						<input type="hidden" name="emailActionId" value="6">
          </html:form>
				</div>
        <div class="tabbertab">
          <h2><bean:message key="label.drivingLicense"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantDrivingLicenseExpiry" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
            <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
            <table class="simple" width="100%">
              <thead>
                <tr>
                  <th align="center" width="2%">
                    <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantDrivingLicenseExpiry');"/>
                  </th>
                  <th align="left" width="18%"><bean:message key="label.drivingLicenseExpiryDate"/></th>
                  <th align="left" width="20%"><bean:message key="label.name"/></th>
                  <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
                  <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
                  <th align="left" width="20%"><bean:message key="label.mobile"/></th>
                </tr>
              </thead>
              <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="drivingLicenseList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
              <% String applicantDrivingLicenseExpiryCheckBox = "applicantId"; %>
              <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
                <td align="left" valign="top">
                  <input type="checkbox" name="<%= applicantDrivingLicenseExpiryCheckBox %>" value="<%= applicant.getApplicantId() %>">    
                </td>
                <td align="left"><bean:write name="applicant" property="drivingLicenseExpiryDate" formatKey="format.mediumDateFormat" /></td>
                <td align="left">    
                <mmj-agy:hasAccess forward="applicantView">
                  <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                  </html:link>
                </mmj-agy:hasAccess>
                <mmj-agy:hasNoAccess forward="applicantView">
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </mmj-agy:hasNoAccess>
                </td>
                <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
                <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
                <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
              </tr>   
              </logic:iterate>
            </table>
            <input type="hidden" name="emailActionId" value="11">
          </html:form>
        </div>
        <div class="tabbertab">
          <h2><bean:message key="label.reference1"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantReference1" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
            <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
            <table class="simple" width="100%">
              <thead>
                <tr>
                  <th align="center" width="2%">
                    <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantReference1');"/>
                  </th>
                  <th align="left" width="18%"><bean:message key="label.reference1Date"/></th>
                  <th align="left" width="20%"><bean:message key="label.name"/></th>
                  <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
                  <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
                  <th align="left" width="20%"><bean:message key="label.mobile"/></th>
                </tr>
              </thead>
              <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="reference1List" indexId="applicantIndex" type="com.helmet.bean.Applicant">
              <% String applicantReference1CheckBox = "applicantId"; %>
              <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
                <td align="left" valign="top">
                  <input type="checkbox" name="<%= applicantReference1CheckBox %>" value="<%= applicant.getApplicantId() %>">    
                </td>
                <td align="left"><bean:write name="applicant" property="reference1Date" formatKey="format.mediumDateFormat" /></td>
                <td align="left">    
                <mmj-agy:hasAccess forward="applicantView">
                  <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                  </html:link>
                </mmj-agy:hasAccess>
                <mmj-agy:hasNoAccess forward="applicantView">
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </mmj-agy:hasNoAccess>
                </td>
                <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
                <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
                <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
              </tr>   
              </logic:iterate>
            </table>
            <input type="hidden" name="emailActionId" value="9">
          </html:form>
        </div>
        <div class="tabbertab">
          <h2><bean:message key="label.reference2"/></h2>
          <html:form action="/applicantEmailProcess.do" styleId="ApplicantReference2" onsubmit="return singleSubmit();">
<mmj-agy:hasAccess forward="applicantEdit">
            <html:submit styleClass="titleButton"><bean:message key="button.send"/></html:submit>
</mmj-agy:hasAccess>
            <table class="simple" width="100%">
              <thead>
                <tr>
                  <th align="center" width="2%">
                    <input type="checkbox" name="checkall" onclick="checkedAll('ApplicantReference2');"/>
                  </th>
                  <th align="left" width="18%"><bean:message key="label.reference2Date"/></th>
                  <th align="left" width="20%"><bean:message key="label.name"/></th>
                  <th align="left" width="20%"><bean:message key="label.emailAddress"/></th>
                  <th align="left" width="20%"><bean:message key="label.telephoneNumber"/></th>
                  <th align="left" width="20%"><bean:message key="label.mobile"/></th>
                </tr>
              </thead>
              <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="reference2List" indexId="applicantIndex" type="com.helmet.bean.Applicant">
              <% String applicantReference2CheckBox = "applicantId"; %>
              <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
                <td align="left" valign="top">
                  <input type="checkbox" name="<%= applicantReference2CheckBox %>" value="<%= applicant.getApplicantId() %>">    
                </td>
                <td align="left"><bean:write name="applicant" property="reference2Date" formatKey="format.mediumDateFormat" /></td>
                <td align="left">    
                <mmj-agy:hasAccess forward="applicantView">
                  <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                  </html:link>
                </mmj-agy:hasAccess>
                <mmj-agy:hasNoAccess forward="applicantView">
                    <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </mmj-agy:hasNoAccess>
                </td>
                <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
                <td align="left"><bean:write name="applicant" property="telephoneNumber"/></td>
                <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
              </tr>   
              </logic:iterate>
            </table>
            <input type="hidden" name="emailActionId" value="10">
          </html:form>
        </div>
			</div>
	  </td>
  </tr>
  <tr>
    <td>
      &nbsp;
    </td>
  </tr>
  <tr>
    <td>
      <div class="tabber">
        <div class="tabbertab">
          <h2><bean:message key="label.recentlyCompliant"/></h2>
          <table class="simple" width="100%">
            <thead>
              <tr>
                <th align="left"><bean:message key="label.name"/></th>
                <th align="left"><bean:message key="label.emailAddress"/></th>
                <th align="left"><bean:message key="label.mobile"/></th>
                <th align="left"><bean:message key="label.availability"/></th>
                <th align="left"><bean:message key="label.disciplineCategory"/></th>
                <th align="left"><bean:message key="label.clientGroup"/></th>
                <th align="left"><bean:message key="label.professionalReference"/></th>
                <th align="center"><bean:message key="label.ftw"/></th>
                <th align="center"><bean:message key="label.crb"/></th>
                <th align="center"><bean:message key="label.mt"/></th>
                <th align="center"><bean:message key="label.compliant"/></th>
              </tr>
            </thead>
            <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="recentlyCompliantList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
            <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
              <td align="left">    
              <mmj-agy:hasAccess forward="applicantView">
                <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </html:link>
              </mmj-agy:hasAccess>
              <mmj-agy:hasNoAccess forward="applicantView">
                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
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
              <td align="left"><bean:write name="applicant" property="hpcNumber"/></td>   
              <td align="center">
                <logic:equal name="applicant" property="hasCurrentFitToWork" value="true">
                  <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
                </logic:equal>
                <logic:notEqual name="applicant" property="hasCurrentFitToWork" value="true">
                  <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
                </logic:notEqual>
              </td>
              <td align="center">
                <logic:equal name="applicant" property="hasCurrentCRB" value="true">
                  <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
                </logic:equal>
                <logic:notEqual name="applicant" property="hasCurrentCRB" value="true">
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
        </div>
        <div class="tabbertab">
          <h2><bean:message key="label.recentProspects"/></h2>
          <table class="simple" width="100%">
            <thead>
              <tr>
                <th align="left"><bean:message key="label.name"/></th>
                <th align="left"><bean:message key="label.emailAddress"/></th>
                <th align="left"><bean:message key="label.mobile"/></th>
                <th align="left"><bean:message key="label.availability"/></th>
                <th align="left"><bean:message key="label.disciplineCategory"/></th>
                <th align="left">Uploaded</th>
              </tr>
            </thead>
<logic:notEmpty name="ApplicantNotificationsFormAgy" property="recentProspectList">
            <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="recentProspectList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
            <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
              <td align="left">    
              <mmj-agy:hasAccess forward="applicantView">
                <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </html:link>
              </mmj-agy:hasAccess>
              <mmj-agy:hasNoAccess forward="applicantView">
                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
              </mmj-agy:hasNoAccess>
              </td>
              <td align="left"><bean:write name="applicant" property="user.emailAddress"/></td>   
              <td align="left"><bean:write name="applicant" property="mobileNumber"/></td>
              <td align="left"><bean:write name="applicant" property="availabilityDate" formatKey="format.mediumDateFormat"/></td>   
              <td align="left"><bean:write name="applicant" property="disciplineCategoryName"/></td>   
              <td align="left"><bean:write name="applicant" property="creationTimestamp" formatKey="format.mediumDateFormat" /></td>   
            </tr>   
            </logic:iterate>
</logic:notEmpty>            
<logic:empty name="ApplicantNotificationsFormAgy" property="recentProspectList">
            <tr>
              <td align="left" colspan="6">
                <bean:message key="text.noDetails"/>
              </td>
            </tr>
</logic:empty>
          </table>
        </div>
        <div class="tabbertab">
          <h2><bean:message key="label.unarchived"/></h2>
          <table class="simple" width="100%">
            <thead>
              <tr>
                <th align="left"><bean:message key="label.name"/></th>
                <th align="left"><bean:message key="label.emailAddress"/></th>
                <th align="left"><bean:message key="label.mobile"/></th>
						    <th align="left"><bean:message key="label.availability"/></th>
						    <th align="left"><bean:message key="label.disciplineCategory"/></th>
						    <th align="left"><bean:message key="label.clientGroup"/></th>
						    <th align="left"><bean:message key="label.professionalReference"/></th>
						    <th align="center"><bean:message key="label.ftw"/></th>
						    <th align="center"><bean:message key="label.crb"/></th>
						    <th align="center"><bean:message key="label.mt"/></th>
						    <th align="center"><bean:message key="label.compliant"/></th>
              </tr>
            </thead>
            <logic:iterate id="applicant" name="ApplicantNotificationsFormAgy" property="unarchivedList" indexId="applicantIndex" type="com.helmet.bean.Applicant">
            <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
              <td align="left">    
              <mmj-agy:hasAccess forward="applicantView">
                <html:link forward="applicantView" paramId="applicant.applicantId" paramName="applicant" paramProperty="applicantId" >
                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
                </html:link>
              </mmj-agy:hasAccess>
              <mmj-agy:hasNoAccess forward="applicantView">
                  <bean:write name="applicant" property="user.firstName"/>&nbsp;<bean:write name="applicant" property="user.lastName"/>
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
					    <td align="left"><bean:write name="applicant" property="hpcNumber"/></td>   
					    <td align="center">
								<logic:equal name="applicant" property="hasCurrentFitToWork" value="true">
							    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
							  </logic:equal>
							  <logic:notEqual name="applicant" property="hasCurrentFitToWork" value="true">
							    <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
							  </logic:notEqual>
					    </td>
					    <td align="center">
								<logic:equal name="applicant" property="hasCurrentCRB" value="true">
							    <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
							  </logic:equal>
								<logic:notEqual name="applicant" property="hasCurrentCRB" value="true">
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
        </div>
      </div>
    </td>
  </tr>
  <tr>
    <td>
      &nbsp;
    </td>
  </tr>
  <tr>
    <td>
<mmj-agy:hasAccess forward="applicantEdit">
      <html:form action="/applicantsCompliancyTest.do" onsubmit="return singleSubmit();">
		    <html:submit styleClass="wideButton">Compliancy Check</html:submit>
		  </html:form>
</mmj-agy:hasAccess>
    </td>
  </tr>
</table>
					