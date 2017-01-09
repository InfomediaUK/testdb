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

<html:form action="/locationJobProfileAddProcess.do" onsubmit="return singleSubmit();">
<html:hidden property="location.locationId"/>
<html:hidden property="location.noOfChanges"/>
  <tr>
	<td align="left" valign="middle" class="title">
<bean:message key="title.locationJobProfileAdd"/>
	</td>
    <mmj-mgr:hasAccess forward="locationJobProfileAdd">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.add"/></html:submit></td>
	</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
  </tr>
</table>
<html:errors/>
<logic:notEmpty name="LocationJobProfileAddRemoveFormMgr" property="list">
  <bean:define id="jobFamilyName" value=""/>
  <bean:define id="jobSubFamilyName" value=""/>
  <logic:iterate id="jobProfile" name="LocationJobProfileAddRemoveFormMgr" property="list" type="com.helmet.bean.JobProfileUser">
    <logic:notEqual name="jobProfile" property="jobFamilyName" value="<%= jobFamilyName %>">
      <bean:write name="jobProfile" property="jobFamilyName"/> (<bean:write name="jobProfile" property="jobFamilyCode"/>)
	  <br/>
	  <bean:define id="jobFamilyName" name="jobProfile" property="jobFamilyName" type="java.lang.String"/>
	  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="jobProfile" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
      <html:img src="images/indent.gif" width="15" height="9"/>
      <bean:write name="jobProfile" property="jobSubFamilyName"/> (<bean:write name="jobProfile" property="jobSubFamilyCode"/>)
      <br/>
      <bean:define id="jobSubFamilyName" name="jobProfile" property="jobSubFamilyName" type="java.lang.String"/>
      <bean:define id="jobProfileName" value=""/>
    </logic:notEqual>
    <html:img src="images/trans.gif" width="15" height="9"/>
    <html:img src="images/indent.gif" width="15" height="9"/>
    <mmj-mgr:hasAccess forward="locationJobProfileAdd">
      <html:multibox property="selectedItems" >
        <bean:write name="jobProfile" property="jobProfileId" />
      </html:multibox>
    </mmj-mgr:hasAccess>
    <bean:write name="jobProfile" property="name"/> (<bean:write name="jobProfile" property="code"/>)
    &nbsp;<br/>
  </logic:iterate>

</logic:notEmpty>

</html:form>
