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
<bean:message key="title.siteDelete"/>
		</td>
		<html:form action="siteDeleteProcess.do" onsubmit="return singleSubmit();">
		<mmj-mgr:hasAccess forward="siteDelete">
		<html:hidden property="site.siteId"/>
		<html:hidden property="site.noOfChanges"/>
    <td align="right" valign="middle" width="75"><html:submit styleClass="confirmButton"><bean:message key="button.confirm"/></html:submit></td>
		</mmj-mgr:hasAccess>
    <td align="right" valign="middle" width="75"><html:cancel styleClass="titleButton"><bean:message key="button.cancel"/></html:cancel></td>
		</html:form>
  </tr>
</table>
<html:errors/>
<table class="simple" width="100%">
  <tr>
    <th align="left" width="35%"><bean:message key="label.name"/></th>
    <td align="left" width="65%"><bean:write name="SiteViewFormMgr" property="site.name"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.address"/></th>
    <td align="left">
	  <bean:write name="SiteViewFormMgr" property="site.address.address1"/>
	  <logic:notEmpty name="SiteViewFormMgr" property="site.address.address2">
		<br/><bean:write name="SiteViewFormMgr" property="site.address.address2"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="SiteViewFormMgr" property="site.address.address3">
		<br/><bean:write name="SiteViewFormMgr" property="site.address.address3"/>
	  </logic:notEmpty>		
	  <logic:notEmpty name="SiteViewFormMgr" property="site.address.address4">
		<br/><bean:write name="SiteViewFormMgr" property="site.address.address4"/>
	  </logic:notEmpty>		
	</td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.postalCode"/></th>
    <td align="left"><bean:write name="SiteViewFormMgr" property="site.address.postalCode"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.country"/></th>
    <td align="left"><bean:write name="SiteViewFormMgr" property="site.countryName"/></td>
  </tr>
  <tr>
    <th align="left"><bean:message key="label.code"/></th>
    <td align="left"><bean:write name="SiteViewFormMgr" property="site.code"/></td>
  </tr>
</table>
