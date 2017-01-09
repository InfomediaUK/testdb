<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>
<%@ taglib uri="/mmj-mgr" prefix="mmj-mgr" %>
<table cellpadding="0" cellspacing="0" width="100%" height="30">

<html:form action="/locationJobProfileRemoveProcess.do" onsubmit="return singleSubmit();">
<html:hidden property="location.locationId"/>
<html:hidden property="location.noOfChanges"/>
  <tr>
	<td align="left" valign="middle" class="title">
<bean:message key="title.locationJobProfileRemove"/>
	</td>
    <mmj-mgr:hasAccess forward="locationJobProfileRemove">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.remove"/></html:submit></td>
	</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<logic:notEmpty name="LocationJobProfileAddRemoveFormMgr" property="list">

  <bean:define id="jobFamilyName" value=""/>
  <bean:define id="jobSubFamilyName" value=""/>
  <logic:iterate id="locationJobProfile" name="LocationJobProfileAddRemoveFormMgr" property="list" type="com.helmet.bean.LocationJobProfileUser">
    <logic:notEqual name="locationJobProfile" property="jobFamilyName" value="<%= jobFamilyName %>">
      <bean:write name="locationJobProfile" property="jobFamilyName"/> (<bean:write name="locationJobProfile" property="jobFamilyCode"/>)
	  <br/>
	  <bean:define id="jobFamilyName" name="locationJobProfile" property="jobFamilyName" type="java.lang.String"/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="locationJobProfile" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
      <html:img src="images/indent.gif" width="15" height="9"/>
      <bean:write name="locationJobProfile" property="jobSubFamilyName"/> (<bean:write name="locationJobProfile" property="jobSubFamilyCode"/>)
      <br/>
      <bean:define id="jobSubFamilyName" name="locationJobProfile" property="jobSubFamilyName" type="java.lang.String"/>
      <bean:define id="jobProfileName" value=""/>
    </logic:notEqual>
    <html:img src="images/trans.gif" width="15" height="9"/>
    <html:img src="images/indent.gif" width="15" height="9"/>
    <mmj-mgr:hasAccess forward="locationJobProfileAdd">
      <html:multibox property="selectedItems" >
        <bean:write name="locationJobProfile" property="locationJobProfileId" />,<bean:write name="locationJobProfile" property="noOfChanges" />
      </html:multibox>
    </mmj-mgr:hasAccess>
    <bean:write name="locationJobProfile" property="jobProfileName"/> (<bean:write name="locationJobProfile" property="jobProfileCode"/>)
    &nbsp;<br/>
  </logic:iterate>

</logic:notEmpty>

</html:form>
