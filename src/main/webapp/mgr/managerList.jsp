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
	<bean:message key="title.managerList"/>
	</td>
	<mmj-mgr:hasAccess forward="managerNew">
    <html:form action="/managerNew.do" onsubmit="return singleSubmit();">
    <td align="right" valign="middle" width="75"><html:submit styleClass="titleButton"><bean:message key="button.new"/></html:submit></td>
    </html:form>
	</mmj-mgr:hasAccess>
  </tr>
</table>

<logic:notPresent name="ListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="ListFormMgr" property="list">
<logic:empty name="ListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="ListFormMgr" property="list">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left"><bean:message key="label.name"/></th>
    <th align="left"><bean:message key="label.emailAddress"/></th>
    <th align="left"><bean:message key="label.login"/></th>
    <th align="center"><bean:message key="label.showPageHelpShort"/></th>
    <th align="center"><bean:message key="label.superUserShort"/></th>
    <th align="center"><bean:message key="label.secretWordShort"/></th>
  </tr>
  </thead>
  <logic:iterate id="manager" name="ListFormMgr" property="list" indexId="managerIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <td align="left">
	<mmj-mgr:hasAccess forward="managerView">
  	  <html:link forward="managerView" paramId="manager.managerId" paramName="manager" paramProperty="managerId">
        <bean:write name="manager" property="user.fullName"/>
	  </html:link>
	</mmj-mgr:hasAccess>
	<mmj-mgr:hasNoAccess forward="managerView">
      <bean:write name="manager" property="user.fullName"/>
	</mmj-mgr:hasNoAccess>
    </td>
    <td align="left">
      <bean:write name="manager" property="user.emailAddress"/>
    </td>
    <td align="left">
      <bean:write name="manager" property="user.login"/>
    </td>
    <td align="center">
  	<logic:equal name="manager" property="user.showPageHelp" value="true">
	  <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
	</logic:equal>
	<logic:notEqual name="manager" property="user.showPageHelp" value="true">
	  <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
	</logic:notEqual>
    </td>
    <td align="center">
  	<logic:equal name="manager" property="user.superUser" value="true">
	  <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
	</logic:equal>
	<logic:notEqual name="manager" property="user.superUser" value="true">
	  <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
	</logic:notEqual>
    </td>
    <td align="center">
	<logic:equal name="manager" property="user.hasChangedSecretWord" value="true">
      <html:img src="images/checkboxdisabledon.bmp" width="13" height="13" />
	</logic:equal>
	<logic:notEqual name="manager" property="user.hasChangedSecretWord" value="true">
	  <html:img src="images/checkboxdisabledoff.bmp" width="13" height="13" />
	</logic:notEqual>
    </td>
  </tr> 
  </logic:iterate>
</table> 
</logic:notEmpty> 
</logic:present>