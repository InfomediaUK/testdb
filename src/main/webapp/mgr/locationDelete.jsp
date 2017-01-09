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
<bean:message key="title.locationDelete"/>
		</td>
		<html:form action="locationDeleteProcess.do" onsubmit="return singleSubmit();">
		<mmj-mgr:hasAccess forward="locationDelete">
    <html:hidden property="location.siteId"/>
		<html:hidden property="location.locationId"/>
		<html:hidden property="location.noOfChanges"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
		</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left"><bean:message key="label.name"/></th>
    <td align="left" colspan="3"><bean:write name="LocationViewFormMgr" property="location.name"/></td>
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