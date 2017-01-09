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

<html:form action="/locationManagerJobProfileRemoveProcess.do" onsubmit="return singleSubmit();">
<html:hidden name="ManagerViewFormMgr" property="manager.managerId"/>
<html:hidden name="ManagerViewFormMgr" property="manager.noOfChanges"/>
  <tr>
	<td align="left" valign="middle" class="title">
<bean:message key="title.locationManagerJobProfileRemove"/>
	</td>
    <mmj-mgr:hasAccess forward="locationManagerJobProfileAdd">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.remove"/></html:submit></td>
	</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<logic:notEmpty name="ManagerViewFormMgr" property="manager.locationManagerJobProfileUsers">
  <bean:define id="siteName" value=""/>
  <bean:define id="locationName" value=""/>
  <bean:define id="jobFamilyName" value=""/>
  <bean:define id="jobSubFamilyName" value=""/>
  <logic:iterate id="locationManagerJobProfile" name="ManagerViewFormMgr" property="manager.locationManagerJobProfileUsers" type="com.helmet.bean.LocationManagerJobProfileUser">
    <logic:notEqual name="locationManagerJobProfile" property="siteName" value="<%= siteName %>">
      <bean:write name="locationManagerJobProfile" property="siteName"/> (<bean:write name="locationManagerJobProfile" property="siteCode"/>)
	  <br/>
	  <bean:define id="siteName" name="locationManagerJobProfile" property="siteName" type="java.lang.String"/>
	  <bean:define id="locationName" value=""/>
	  <bean:define id="jobFamilyName" value=""/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="locationManagerJobProfile" property="locationName" value="<%= locationName %>">
      <html:img src="images/indent.gif" width="15" height="9"/>
      <bean:write name="locationManagerJobProfile" property="locationName"/> (<bean:write name="locationManagerJobProfile" property="locationCode"/>)
	  <br/>
	  <bean:define id="locationName" name="locationManagerJobProfile" property="locationName" type="java.lang.String"/>
	  <bean:define id="jobFamilyName" value=""/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="locationManagerJobProfile" property="jobFamilyName" value="<%= jobFamilyName %>">
      <html:img src="images/trans.gif" width="15" height="9"/>
      <html:img src="images/indent.gif" width="15" height="9"/>
      <bean:write name="locationManagerJobProfile" property="jobFamilyName"/> (<bean:write name="locationManagerJobProfile" property="jobFamilyCode"/>)
	  <br/>
	  <bean:define id="jobFamilyName" name="locationManagerJobProfile" property="jobFamilyName" type="java.lang.String"/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="locationManagerJobProfile" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
      <html:img src="images/trans.gif" width="15" height="9"/>
      <html:img src="images/trans.gif" width="15" height="9"/>
      <html:img src="images/indent.gif" width="15" height="9"/>
      <bean:write name="locationManagerJobProfile" property="jobSubFamilyName"/> (<bean:write name="locationManagerJobProfile" property="jobSubFamilyCode"/>)
      <br/>
      <bean:define id="jobSubFamilyName" name="locationManagerJobProfile" property="jobSubFamilyName" type="java.lang.String"/>
      <bean:define id="jobProfileName" value=""/>
    </logic:notEqual>
    <html:img src="images/trans.gif" width="15" height="9"/>
    <html:img src="images/trans.gif" width="15" height="9"/>
    <html:img src="images/trans.gif" width="15" height="9"/>
    <html:img src="images/indent.gif" width="15" height="9"/>
    <html:multibox property="selectedItems" >
        <bean:write name="locationManagerJobProfile" property="locationManagerJobProfileId" />,<bean:write name="locationManagerJobProfile" property="noOfChanges" />
    </html:multibox>
    <bean:write name="locationManagerJobProfile" property="jobProfileName"/> (<bean:write name="locationManagerJobProfile" property="jobProfileCode"/>)
    <br/>
  </logic:iterate>
</logic:notEmpty>

</html:form>
