<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.locationView"/>
		</td>
<mmj-mgr:hasAccess forward="locationEdit">
    <html:form action="/locationEdit.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="location.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.edit"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="locationDelete">
    <html:form action="/locationDelete.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="location.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.delete"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
    <html:form action="/locationView.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="location.siteId" value="<bean:write name="LocationViewFormMgr" property="location.siteId"/>"/>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
	</tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.name"/></th>
    <td align="left" colspan="3">
      <bean:write name="LocationViewFormMgr" property="location.name"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.description"/></th>
    <td align="left" colspan="3">
	  	<bean:write name="LocationViewFormMgr" property="location.description"/>
  	</td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left" colspan="3"><bean:write name="LocationViewFormMgr" property="location.code"/></td>
  </tr>
  <tr>
    <td align="left">
      &nbsp;
    </td>
    <th align="left" class="label" colspan="3">
      <bean:message key="label.contact" />
    </th>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactName" />
    </th>
    <td align="left" colspan="3">
      <bean:write name="LocationViewFormMgr" property="location.contactName"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactJobTitle" />
    </th>
    <td align="left" colspan="3">
      <bean:write name="LocationViewFormMgr" property="location.contactJobTitle"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactEmailAddress" />
    </th>
    <td align="left" colspan="3">
      <bean:write name="LocationViewFormMgr" property="location.contactEmailAddress"/>
    </td>
  </tr>
  <tr>
    <th align="left" class="label">
      <bean:message key="label.contactTelephoneNumber" />
    </th>
    <td align="left" colspan="3">
      <bean:write name="LocationViewFormMgr" property="location.contactTelephoneNumber"/>
    </td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.commentsDefault"/></th>
    <td align="left" colspan="3"><pre><bean:write name="LocationViewFormMgr" property="location.commentsDefault"/></pre></td>
  </tr>
  <tr>
    <th align="left" width="45%">
		  <bean:message key="label.singleCandidateShow"/>
    </th>
    <td align="center" width="5%">
      <logic:equal name="LocationViewFormMgr" property="location.singleCandidateShow" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.singleCandidateShow" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
    <th align="left" width="45%">
		  <bean:message key="label.singleCandidateDefault"/>
    </th>
    <td align="center" width="5%">
      <logic:equal name="LocationViewFormMgr" property="location.singleCandidateDefault" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.singleCandidateDefault" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left">
		  <bean:message key="label.cvRequiredShow"/>
    </th>
    <td align="center">
      <logic:equal name="LocationViewFormMgr" property="location.cvRequiredShow" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.cvRequiredShow" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
    <th align="left">
		  <bean:message key="label.cvRequiredDefault"/>
    </th>
    <td align="center">
      <logic:equal name="LocationViewFormMgr" property="location.cvRequiredDefault" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.cvRequiredDefault" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left">
		  <bean:message key="label.interviewRequiredShow"/>
    </th>
    <td align="center">
      <logic:equal name="LocationViewFormMgr" property="location.interviewRequiredShow" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.interviewRequiredShow" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
    <th align="left">
		  <bean:message key="label.interviewRequiredDefault"/>
    </th>
    <td align="center">
      <logic:equal name="LocationViewFormMgr" property="location.interviewRequiredDefault" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.interviewRequiredDefault" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left">
		  <bean:message key="label.canProvideAccommodationShow"/>
    </th>
    <td align="center">
      <logic:equal name="LocationViewFormMgr" property="location.canProvideAccommodationShow" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.canProvideAccommodationShow" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
    <th align="left">
		  <bean:message key="label.canProvideAccommodationDefault"/>
    </th>
    <td align="center">
      <logic:equal name="LocationViewFormMgr" property="location.canProvideAccommodationDefault" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.canProvideAccommodationDefault" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
  <tr>
    <th align="left">
		  <bean:message key="label.carRequiredShow"/>
    </th>
    <td align="center">
      <logic:equal name="LocationViewFormMgr" property="location.carRequiredShow" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.carRequiredShow" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
    <th align="left">
		  <bean:message key="label.carRequiredDefault"/>
    </th>
    <td align="center">
      <logic:equal name="LocationViewFormMgr" property="location.carRequiredDefault" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="LocationViewFormMgr" property="location.carRequiredDefault" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
    </td>
  </tr>
</table>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.locationShiftList"/>
		</td>
<mmj-mgr:hasAccess forward="shiftNew">
    <html:form action="/shiftNew.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="shift.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="shiftOrder">
    <html:form action="/shiftOrder.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="location.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.order"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
	</tr>
</table>

<table class="simple" width="100%">
  <thead>
  <tr>
    <td align="left" colspan="5">&nbsp;</td>
    <th align="center" colspan="3"><bean:message key="label.uplift" /></th>
    <th align="center" colspan="2"><bean:message key="label.overtime" /></th>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.description" /></th>
    <th align="left"><bean:message key="label.code" /></th>
    <th align="left"><bean:message key="label.times" /></th>
    <th align="left"><bean:message key="label.break" /></th>
    <th align="left"><bean:message key="label.factor" /></th>
    <th align="left"><bean:message key="label.value" /></th>
    <th align="left"><bean:message key="label.use" /></th>
    <th align="left"><bean:message key="label.noOfHours" /></th>
    <th align="left"><bean:message key="label.factor" /></th>
  </tr>
  </thead>
	<logic:iterate id="shift" name="LocationViewFormMgr" property="location.shifts">
	<bean:define id="trClass" value="shift"/>
	<logic:notEqual name="shift" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
	  <td align="left">
    <mmj-mgr:hasAccess forward="shiftView">
      <html:link forward="shiftView" paramId="shift.shiftId" paramName="shift" paramProperty="shiftId"><bean:write name="shift" property="name"/></html:link>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="shiftView">
      <bean:write name="shift" property="name"/>
    </mmj-mgr:hasNoAccess>    
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="description"/>
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="code"/>
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="startTime" format="HH:mm"/>
			-
	    <bean:write name="shift" property="endTime" format="HH:mm"/>
	    (<bean:write name="shift" property="noOfHours" format="#0.00"/>)
	  </td>
	  <td align="left">
	    <bean:write name="shift" property="breakStartTime" format="HH:mm"/>
			-
	    <bean:write name="shift" property="breakEndTime" format="HH:mm"/>
	    (<bean:write name="shift" property="breakNoOfHours" format="#0.00"/>)
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="upliftFactor" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="upliftValue" format="#0.00"/>
	  </td>
	  <td align="left">
      <logic:equal name="shift" property="useShiftUplift" value="true">
    		<bean:message key="label.yes"/>
      </logic:equal>
      <logic:notEqual name="shift" property="useShiftUplift" value="true">
		    <bean:message key="label.no"/>
      </logic:notEqual>
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="overtimeNoOfHours" format="#0.00"/>
	  </td>
	  <td align="right">
	    <bean:write name="shift" property="overtimeUpliftFactor" format="#0.00"/>
	  </td>
	</tr>
	</logic:iterate>  
</table>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.locationDressCodeList"/>
		</td>
<mmj-mgr:hasAccess forward="dressCodeNew">
    <html:form action="/dressCodeNew.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="dressCode.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="dressCodeOrder">
    <html:form action="/dressCodeOrder.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="location.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.order"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
	</tr>
</table>

<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
  </tr>
  </thead>
	<logic:iterate id="dressCode" name="LocationViewFormMgr" property="location.dressCodes">
	<bean:define id="trClass" value="dressCode"/>
	<logic:notEqual name="dressCode" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
	  <td align="left">
    <mmj-mgr:hasAccess forward="dressCodeView">
      <html:link forward="dressCodeView" paramId="dressCode.dressCodeId" paramName="dressCode" paramProperty="dressCodeId"><bean:write name="dressCode" property="name"/></html:link>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="dressCodeView">
      <bean:write name="dressCode" property="name"/>
    </mmj-mgr:hasNoAccess>    
	  </td>
	</tr>
	</logic:iterate>  
</table>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
		<td align="left" valign="middle" class="title">
<bean:message key="title.locationExpenseList"/>
		</td>
<mmj-mgr:hasAccess forward="expenseNew">
    <html:form action="/expenseNew.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="expense.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="expenseOrder">
    <html:form action="/expenseOrder.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="location.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.order"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
	</tr>
</table>

<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name" /></th>
    <th align="left"><bean:message key="label.description" /></th>
    <th align="left"><bean:message key="label.code" /></th>
    <th align="left"><bean:message key="label.multiplier" /></th>
    <th align="left"><bean:message key="label.vatRate" /></th>
  </tr>
  </thead>
	<logic:iterate id="expense" name="LocationViewFormMgr" property="location.expenses">
	<bean:define id="trClass" value="expense"/>
	<logic:notEqual name="expense" property="active" value="true">
	  <bean:define id="trClass" value="inactive"/>
	</logic:notEqual>
  <tr class="<bean:write name="trClass"/>">
    <td align="left">
    <mmj-mgr:hasAccess forward="expenseView">
      <html:link forward="expenseView" paramId="expense.expenseId" paramName="expense" paramProperty="expenseId"><bean:write name="expense" property="name"/></html:link>
    </mmj-mgr:hasAccess>
    <mmj-mgr:hasNoAccess forward="expenseView">
      <bean:write name="expense" property="name"/>
    </mmj-mgr:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="expense" property="description"/>
    </td>
    <td align="left">
      <bean:write name="expense" property="code"/>
    </td>
    <td align="right">
      <bean:write name="expense" property="multiplier" format="#0.00"/>
    </td>
    <td align="right">
      <bean:write name="expense" property="vatRate" format="#0.00"/>
    </td>
  </tr>
  </logic:iterate>
</table>

<table cellpadding="0" cellspacing="0" width="100%" height="30">
  <tr>
    <td align="left" valign="middle" class="title">
      <bean:message key="title.locationJobProfileList"/>
	</td>
<mmj-mgr:hasAccess forward="locationJobProfileAdd">
    <html:form action="/locationJobProfileAdd.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="location.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.add"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
<mmj-mgr:hasAccess forward="locationJobProfileRemove">
    <html:form action="/locationJobProfileRemove.do" onsubmit="return singleSubmit();">
    <input type="hidden" name="location.locationId" value="<bean:write name="LocationViewFormMgr" property="location.locationId"/>"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.remove"/></html:submit></td>
    </html:form>
</mmj-mgr:hasAccess>
  </tr>
</table>

<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.jobFamily" /></th>
    <th align="left"><bean:message key="label.jobSubFamily" /></th>
    <th align="left"><bean:message key="label.jobProfile" /></th>
    <th align="left"><bean:message key="label.budget" /></th>
    <th align="left"><bean:message key="label.rrp" /></th>
  </tr>
  </thead>
  <bean:define id="jobFamilyName" value=""/>
  <bean:define id="jobSubFamilyName" value=""/>
		
  <logic:iterate id="locationJobProfileUser" name="LocationViewFormMgr" property="location.locationJobProfileUsers" indexId="indexId">
  <tr>
    <td align="left">
    <logic:equal name="locationJobProfileUser" property="jobFamilyName" value="<%= jobFamilyName %>">
  		&nbsp;
		</logic:equal>
    <logic:notEqual name="locationJobProfileUser" property="jobFamilyName" value="<%= jobFamilyName %>">
      <bean:write name="locationJobProfileUser" property="jobFamilyName"/>
      <logic:notEmpty name="locationJobProfileUser" property="jobFamilyCode">
        (<bean:write name="locationJobProfileUser" property="jobFamilyCode"/>)
 			</logic:notEmpty>
      <bean:define id="jobFamilyName" name="locationJobProfileUser" property="jobFamilyName" type="java.lang.String"/>
		  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
		</td>
		<td align="left">
    <logic:equal name="locationJobProfileUser" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
  		&nbsp;
		</logic:equal>
    <logic:notEqual name="locationJobProfileUser" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
	    <bean:write name="locationJobProfileUser" property="jobSubFamilyName"/>
      <logic:notEmpty name="locationJobProfileUser" property="jobSubFamilyCode">
	      (<bean:write name="locationJobProfileUser" property="jobSubFamilyCode"/>)
	    </logic:notEmpty>
      <bean:define id="jobSubFamilyName" name="locationJobProfileUser" property="jobSubFamilyName" type="java.lang.String"/>
		  <bean:define id="jobProfileName" value=""/>
    </logic:notEqual>
		</td>
    <td align="left">
      <bean:write name="locationJobProfileUser" property="jobProfileName"/>
      <logic:notEmpty name="locationJobProfileUser" property="jobProfileCode">
        (<bean:write name="locationJobProfileUser" property="jobProfileCode"/>)
      </logic:notEmpty>
    </td>
    <td align="right">
      <bean:write name="locationJobProfileUser" property="budget" format="#0.00"/>
    </td>
    <td align="right">
    <mmj-mgr:hasAccess forward="locationJobProfileView">
	  <html:link forward="locationJobProfileView" paramId="locationJobProfile.locationJobProfileId" paramName="locationJobProfileUser" paramProperty="locationJobProfileId"><bean:write name="locationJobProfileUser" property="rate" format="#0.00"/></html:link>
	</mmj-mgr:hasAccess>
	<mmj-mgr:hasNoAccess forward="locationJobProfileView">
      <bean:write name="locationJobProfileUser" property="rate" format="#0.00"/>
	</mmj-mgr:hasNoAccess> 
    </td>
  </tr>
  </logic:iterate>
</table>
