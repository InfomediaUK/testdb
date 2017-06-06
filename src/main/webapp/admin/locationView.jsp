<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.locationView"/>

<br/>
<br/>

<table>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.client"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="clientView">
      <html:link forward="clientView" paramId="client.clientId" paramName="LocationViewFormAdmin" paramProperty="client.clientId"><bean:write name="LocationViewFormAdmin" property="client.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="clientView">
      <bean:write name="LocationViewFormAdmin" property="client.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="client.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="client.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="client.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="client.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.site"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left">
    <mmj-admin:hasAccess forward="siteView">
      <html:link forward="siteView" paramId="site.siteId" paramName="LocationViewFormAdmin" paramProperty="site.siteId"><bean:write name="LocationViewFormAdmin" property="site.name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="siteView">
      <bean:write name="LocationViewFormAdmin" property="site.name"/>
    </mmj-admin:hasNoAccess>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.address"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="site.address.fullAddress"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.country"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="site.countryName"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="site.code"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="site.active"/></td>
  </tr>
  <tr>
    <th align="left" colspan="2"><bean:message key="label.location"/></th>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.nhsWard"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.nhsWard"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.description"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.description"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.code"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.code"/></td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left">
      <bean:message key="label.contact" />
    </th>
  </tr>
  <tr>
    <td align="left">
      <bean:message key="label.contactName" />
    </td>
    <td align="left">
      <bean:write name="LocationViewFormAdmin" property="location.contactName"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      <bean:message key="label.contactJobTitle" />
    </td>
    <td align="left">
      <bean:write name="LocationViewFormAdmin" property="location.contactJobTitle"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      <bean:message key="label.contactEmailAddress" />
    </td>
    <td align="left">
      <bean:write name="LocationViewFormAdmin" property="location.contactEmailAddress"/>
    </td>
  </tr>
  <tr>
    <td align="left">
      <bean:message key="label.contactTelephoneNumber" />
    </td>
    <td align="left">
      <bean:write name="LocationViewFormAdmin" property="location.contactTelephoneNumber"/>
    </td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.singleCandidateShow"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.singleCandidateShow"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.singleCandidateDefault"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.singleCandidateDefault"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.cvRequiredShow"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.cvRequiredShow"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.cvRequiredDefault"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.cvRequiredDefault"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.interviewRequiredShow"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.interviewRequiredShow"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.interviewRequiredDefault"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.interviewRequiredDefault"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.canProvideAccommodationShow"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.canProvideAccommodationShow"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.canProvideAccommodationDefault"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.canProvideAccommodationDefault"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.carRequiredShow"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.carRequiredShow"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.carRequiredDefault"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.carRequiredDefault"/></td>
  </tr>
  <tr>
    <td align="left" valign="top"><bean:message key="label.commentsDefault"/></td>
    <td align="left"><pre><bean:write name="LocationViewFormAdmin" property="location.commentsDefault"/></pre></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.active"/></td>
  </tr>
</table>

<logic:equal name="LocationViewFormAdmin" property="location.active" value="true">
<mmj-admin:hasAccess forward="locationEdit">
  <html:link forward="locationEdit" paramId="location.locationId" paramName="LocationViewFormAdmin" paramProperty="location.locationId"><bean:message key="link.edit"/></html:link>&nbsp;
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="locationDelete">
  <html:link forward="locationDelete" paramId="location.locationId" paramName="LocationViewFormAdmin" paramProperty="location.locationId"><bean:message key="link.delete"/></html:link>
</mmj-admin:hasAccess>
</logic:equal>
<br/>
<br/>
<bean:message key="title.shiftList"/>&nbsp;
<mmj-admin:hasAccess forward="shiftNew">
<html:link forward="shiftNew" paramId="shift.locationId" paramName="LocationViewFormAdmin" paramProperty="location.locationId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="shiftOrder">
<html:link forward="shiftOrder" paramId="location.locationId" paramName="LocationViewFormAdmin" paramProperty="location.locationId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.description" /></th>
    <th align="left"><bean:message key="label.code" /></th>
    <th align="left"><bean:message key="label.startTime" /></th>
    <th align="left"><bean:message key="label.endTime" /></th>
    <th align="left"><bean:message key="label.breakStartTime" /></th>
    <th align="left"><bean:message key="label.breakEndTime" /></th>
    <th align="left"><bean:message key="label.noOfHours" /></th>
    <th align="left"><bean:message key="label.breakNoOfHours" /></th>
    <th align="left"><bean:message key="label.upliftFactor" /></th>
    <th align="left"><bean:message key="label.upliftValue" /></th>
    <th align="left"><bean:message key="label.useShiftUplift" /></th>
    <th align="left"><bean:message key="label.overtimeNoOfHours" /></th>
    <th align="left"><bean:message key="label.overtimeUpliftFactor" /></th>
  </tr>
  </thead>
	<logic:iterate id="shift" name="LocationViewFormAdmin" property="location.shifts">
	<bean:define id="trClass" value="shift"/>
	<logic:notEqual name="shift" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
	  <td align="left">
    <mmj-admin:hasAccess forward="shiftView">
      <html:link forward="shiftView" paramId="shift.shiftId" paramName="shift" paramProperty="shiftId"><bean:write name="shift" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="shiftView">
      <bean:write name="shift" property="name"/>
    </mmj-admin:hasNoAccess>    
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="description"/>
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="code"/>
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="startTime"/>
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="endTime"/>
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="breakStartTime"/>
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="breakEndTime"/>
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="noOfHours"/>
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="breakNoOfHours"/>
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="upliftFactor"/>
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="upliftValue"/>
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="useShiftUplift"/>
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="overtimeNoOfHours"/>
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="overtimeUpliftFactor"/>
	  </td>
	</tr>
	</logic:iterate>  
</table>
<br/>
<bean:message key="title.dressCodeList"/>
<mmj-admin:hasAccess forward="dressCodeNew">
<html:link forward="dressCodeNew" paramId="dressCode.locationId" paramName="LocationViewFormAdmin" paramProperty="location.locationId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="dressCodeOrder">
<html:link forward="dressCodeOrder" paramId="location.locationId" paramName="LocationViewFormAdmin" paramProperty="location.locationId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
  </tr>
  </thead>
	<logic:iterate id="dressCode" name="LocationViewFormAdmin" property="location.dressCodes">
	<bean:define id="trClass" value="dressCode"/>
	<logic:notEqual name="dressCode" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
	  <td align="left">
    <mmj-admin:hasAccess forward="dressCodeView">
      <html:link forward="dressCodeView" paramId="dressCode.dressCodeId" paramName="dressCode" paramProperty="dressCodeId"><bean:write name="dressCode" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="dressCodeView">
      <bean:write name="dressCode" property="name"/>
    </mmj-admin:hasNoAccess>    
	  </td>
	</tr>
	</logic:iterate>  
</table>
<br/>
<bean:message key="title.expenseList"/>
<mmj-admin:hasAccess forward="expenseNew">
<html:link forward="expenseNew" paramId="expense.locationId" paramName="LocationViewFormAdmin" paramProperty="location.locationId"><bean:message key="link.new"/></html:link>
</mmj-admin:hasAccess>
<mmj-admin:hasAccess forward="expenseOrder">
<html:link forward="expenseOrder" paramId="location.locationId" paramName="LocationViewFormAdmin" paramProperty="location.locationId"><bean:message key="link.order"/></html:link>
</mmj-admin:hasAccess>
<br/>
<br/>
<table class="simple">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.description" /></th>
    <th align="left"><bean:message key="label.code" /></th>
    <th align="left"><bean:message key="label.multiplier" /></th>
    <th align="left"><bean:message key="label.vatRate" /></th>
  </tr>
  </thead>
	<logic:iterate id="expense" name="LocationViewFormAdmin" property="location.expenses">
	<bean:define id="trClass" value="expense"/>
	<logic:notEqual name="expense" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-admin:hasAccess forward="expenseView">
      <html:link forward="expenseView" paramId="expense.expenseId" paramName="expense" paramProperty="expenseId"><bean:write name="expense" property="name"/></html:link>
    </mmj-admin:hasAccess>
    <mmj-admin:hasNoAccess forward="expenseView">
      <bean:write name="expense" property="name"/>
    </mmj-admin:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="expense" property="description"/>
    </td>
    <td align="left">
      <bean:write name="expense" property="code"/>
    </td>
    <td align="right">
      <bean:write name="expense" property="multiplier"/>
    </td>
    <td align="right">
      <bean:write name="expense" property="vatRate"/>
    </td>
  </tr>
  </logic:iterate>
</table>
<br/>
<table>
  <tr>
    <td valign="top">
      <bean:message key="title.included"/>
      <logic:notEmpty name="LocationViewFormAdmin" property="location.locationJobProfileUsers">
			<html:form action="locationJobProfileDelete.do">
			<html:hidden name="LocationViewFormAdmin" property="location.locationId" />
		  <bean:define id="jobFamilyName" value=""/>
		  <bean:define id="jobSubFamilyName" value=""/>
		
		  <logic:iterate id="locationJobProfileUser" name="LocationViewFormAdmin" property="location.locationJobProfileUsers" indexId="indexId">
		    <logic:notEqual name="locationJobProfileUser" property="jobFamilyName" value="<%= jobFamilyName %>">
			    <mmj-admin:hasAccess forward="jobFamilyView">
			      <html:link forward="jobFamilyView" paramId="jobFamily.jobFamilyId" paramName="locationJobProfileUser" paramProperty="jobFamilyId"><bean:write name="locationJobProfileUser" property="jobFamilyName"/> (<bean:write name="locationJobProfileUser" property="jobFamilyCode"/>)</html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="jobFamilyView">
			      <bean:write name="locationJobProfileUser" property="jobFamilyName"/> (<bean:write name="locationJobProfileUser" property="jobFamilyCode"/>)
			    </mmj-admin:hasNoAccess>   
		      <br/>
		      <bean:define id="jobFamilyName" name="locationJobProfileUser" property="jobFamilyName" type="java.lang.String"/>
				  <bean:define id="jobSubFamilyName" value=""/>
		    </logic:notEqual>
		    <logic:notEqual name="locationJobProfileUser" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
          <html:img src="images/indent.gif" width="15" height="9"/>
			    <mmj-admin:hasAccess forward="jobSubFamilyView">
			      <html:link forward="jobSubFamilyView" paramId="jobSubFamily.jobSubFamilyId" paramName="locationJobProfileUser" paramProperty="jobSubFamilyId"><bean:write name="locationJobProfileUser" property="jobSubFamilyName"/> (<bean:write name="locationJobProfileUser" property="jobSubFamilyCode"/>)</html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="jobSubFamilyView">
			      <bean:write name="locationJobProfileUser" property="jobSubFamilyName"/> (<bean:write name="locationJobProfileUser" property="jobSubFamilyCode"/>)
			    </mmj-admin:hasNoAccess>   
		      <br/>
		      <bean:define id="jobSubFamilyName" name="locationJobProfileUser" property="jobSubFamilyName" type="java.lang.String"/>
				  <bean:define id="jobProfileName" value=""/>
		    </logic:notEqual>
        <html:img src="images/trans.gif" width="15" height="9"/>
        <html:img src="images/indent.gif" width="15" height="9"/>
		    <logic:equal name="LocationViewFormAdmin" property="location.active" value="true">
		    <mmj-admin:hasAccess forward="locationJobProfileDelete">
		      <html:multibox property="selectedItems" >
		        <bean:write name="locationJobProfileUser" property="locationJobProfileId" />,<bean:write name="locationJobProfileUser" property="noOfChanges" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="jobProfileView">
		      <html:link forward="jobProfileView" paramId="jobProfile.jobProfileId" paramName="locationJobProfileUser" paramProperty="jobProfileId"><bean:write name="locationJobProfileUser" property="jobProfileName"/>  (<bean:write name="locationJobProfileUser" property="jobProfileCode"/>)</html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="jobProfileView">
          <bean:write name="locationJobProfileUser" property="jobProfileName"/>  (<bean:write name="locationJobProfileUser" property="jobProfileCode"/>)
		    </mmj-admin:hasNoAccess> 
        <bean:write name="locationJobProfileUser" property="budget"/>
		    <mmj-admin:hasAccess forward="locationJobProfileView">
		      <html:link forward="locationJobProfileView" paramId="locationJobProfile.locationJobProfileId" paramName="locationJobProfileUser" paramProperty="locationJobProfileId"><bean:write name="locationJobProfileUser" property="rate"/></html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="locationJobProfileView">
          <bean:write name="locationJobProfileUser" property="rate"/>
	      </mmj-admin:hasNoAccess> 
		    &nbsp;<br/>  
		  </logic:iterate>
			<logic:equal name="LocationViewFormAdmin" property="location.active" value="true">
			<mmj-admin:hasAccess forward="locationJobProfileDelete">
			<br/>
			<html:submit><bean:message key="button.exclude"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
    <td valign="top">
      &nbsp;
    </td>
    <td valign="top">
			<bean:message key="title.excluded"/>
      <logic:notEmpty name="LocationViewFormAdmin" property="location.jobProfileUsers">
			<html:form action="locationJobProfileNew.do">
			<html:hidden name="LocationViewFormAdmin" property="location.locationId" />
		  <bean:define id="jobFamilyName" value=""/>
		  <bean:define id="jobSubFamilyName" value=""/>
		  <logic:iterate id="jobProfile" name="LocationViewFormAdmin" property="location.jobProfileUsers">
		    <logic:notEqual name="jobProfile" property="jobFamilyName" value="<%= jobFamilyName %>">
			    <mmj-admin:hasAccess forward="jobFamilyView">
			      <html:link forward="jobFamilyView" paramId="jobFamily.jobFamilyId" paramName="jobProfile" paramProperty="jobFamilyId"><bean:write name="jobProfile" property="jobFamilyName"/> (<bean:write name="jobProfile" property="jobFamilyCode"/>)</html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="jobFamilyView">
			      <bean:write name="jobProfile" property="jobFamilyName"/> (<bean:write name="jobProfile" property="jobFamilyCode"/>)
			    </mmj-admin:hasNoAccess>   
		      <br/>
		      <bean:define id="jobFamilyName" name="jobProfile" property="jobFamilyName" type="java.lang.String"/>
				  <bean:define id="jobSubFamilyName" value=""/>
		    </logic:notEqual>
		    <logic:notEqual name="jobProfile" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
          <html:img src="images/indent.gif" width="15" height="9"/>
			    <mmj-admin:hasAccess forward="jobSubFamilyView">
			      <html:link forward="jobSubFamilyView" paramId="jobSubFamily.jobSubFamilyId" paramName="jobProfile" paramProperty="jobSubFamilyId"><bean:write name="jobProfile" property="jobSubFamilyName"/> (<bean:write name="jobProfile" property="jobSubFamilyCode"/>)</html:link>
			    </mmj-admin:hasAccess>
			    <mmj-admin:hasNoAccess forward="jobSubFamilyView">
			      <bean:write name="jobProfile" property="jobSubFamilyName"/> (<bean:write name="jobProfile" property="jobSubFamilyCode"/>)
			    </mmj-admin:hasNoAccess>   
		      <br/>
		      <bean:define id="jobSubFamilyName" name="jobProfile" property="jobSubFamilyName" type="java.lang.String"/>
				  <bean:define id="jobProfileName" value=""/>
		    </logic:notEqual>
        <html:img src="images/trans.gif" width="15" height="9"/>
        <html:img src="images/indent.gif" width="15" height="9"/>
		    <logic:equal name="LocationViewFormAdmin" property="location.active" value="true">
		    <mmj-admin:hasAccess forward="locationJobProfileNew">
		      <html:multibox property="selectedItems" >
		        <bean:write name="jobProfile" property="jobProfileId" />
		      </html:multibox>
		    </mmj-admin:hasAccess>
		    </logic:equal>
		    <mmj-admin:hasAccess forward="jobProfileView">
		      <html:link forward="jobProfileView" paramId="jobProfile.jobProfileId" paramName="jobProfile" paramProperty="jobProfileId"><bean:write name="jobProfile" property="name"/> (<bean:write name="jobProfile" property="code"/>)</html:link>
		    </mmj-admin:hasAccess>
		    <mmj-admin:hasNoAccess forward="jobProfileView">
		      <bean:write name="jobProfile" property="name"/> (<bean:write name="jobProfile" property="code"/>)
		    </mmj-admin:hasNoAccess>   
		    &nbsp;<br/>
		  </logic:iterate>
			<logic:equal name="LocationViewFormAdmin" property="location.active" value="true">
			<mmj-admin:hasAccess forward="locationJobProfileNew">
			<br/>
			<html:submit><bean:message key="button.include"/></html:submit>
			</mmj-admin:hasAccess>
			</logic:equal>
			</html:form>
			</logic:notEmpty>
    </td>
  </tr>
</table>



