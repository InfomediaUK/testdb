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

<html:form action="/locationManagerJobProfileAddProcess.do" onsubmit="return singleSubmit();">
<html:hidden name="ManagerViewFormMgr" property="manager.managerId"/>
<html:hidden name="ManagerViewFormMgr" property="manager.noOfChanges"/>
  <tr>
	<td align="left" valign="middle" class="title">
<bean:message key="title.locationManagerJobProfileAdd"/>
	</td>
    <mmj-mgr:hasAccess forward="locationManagerJobProfileAdd">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.add"/></html:submit></td>
	</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<logic:notEmpty name="ManagerViewFormMgr" property="manager.locationJobProfileUsers">
  <bean:define id="siteName" value=""/>
  <bean:define id="locationName" value=""/>
  <bean:define id="jobFamilyName" value=""/>
  <bean:define id="jobSubFamilyName" value=""/>
  <logic:iterate id="locationJobProfile" name="ManagerViewFormMgr" property="manager.locationJobProfileUsers" type="com.helmet.bean.LocationJobProfileUser">
    <logic:notEqual name="locationJobProfile" property="siteName" value="<%= siteName %>">
      <bean:write name="locationJobProfile" property="siteName"/> (<bean:write name="locationJobProfile" property="siteCode"/>)
	  <br/>
	  <bean:define id="siteName" name="locationJobProfile" property="siteName" type="java.lang.String"/>
	  <bean:define id="locationName" value=""/>
	  <bean:define id="jobFamilyName" value=""/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="locationJobProfile" property="locationName" value="<%= locationName %>">
      <html:img src="images/indent.gif" width="15" height="9"/>
      <bean:write name="locationJobProfile" property="locationName"/> (<bean:write name="locationJobProfile" property="locationCode"/>)
	  <br/>
	  <bean:define id="locationName" name="locationJobProfile" property="locationName" type="java.lang.String"/>
	  <bean:define id="jobFamilyName" value=""/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="locationJobProfile" property="jobFamilyName" value="<%= jobFamilyName %>">
      <html:img src="images/trans.gif" width="15" height="9"/>
      <html:img src="images/indent.gif" width="15" height="9"/>
      <bean:write name="locationJobProfile" property="jobFamilyName"/> (<bean:write name="locationJobProfile" property="jobFamilyCode"/>)
	  <br/>
	  <bean:define id="jobFamilyName" name="locationJobProfile" property="jobFamilyName" type="java.lang.String"/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="locationJobProfile" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
      <html:img src="images/trans.gif" width="15" height="9"/>
      <html:img src="images/trans.gif" width="15" height="9"/>
      <html:img src="images/indent.gif" width="15" height="9"/>
      <bean:write name="locationJobProfile" property="jobSubFamilyName"/> (<bean:write name="locationJobProfile" property="jobSubFamilyCode"/>)
      <br/>
      <bean:define id="jobSubFamilyName" name="locationJobProfile" property="jobSubFamilyName" type="java.lang.String"/>
      <bean:define id="jobProfileName" value=""/>
    </logic:notEqual>
    <html:img src="images/trans.gif" width="15" height="9"/>
    <html:img src="images/trans.gif" width="15" height="9"/>
    <html:img src="images/trans.gif" width="15" height="9"/>
    <html:img src="images/indent.gif" width="15" height="9"/>
    <html:multibox property="selectedItems" >
        <bean:write name="locationJobProfile" property="locationId" />,<bean:write name="locationJobProfile" property="jobProfileId" />
    </html:multibox>
    <bean:write name="locationJobProfile" property="jobProfileName"/> (<bean:write name="locationJobProfile" property="jobProfileCode"/>)
    <br/>
  </logic:iterate>
</logic:notEmpty>

</html:form>
