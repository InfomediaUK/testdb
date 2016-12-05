<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj" prefix="mmj" %>

<bean:message key="title.dressCodeDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="dressCodeDeleteProcess.do">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="site.name" /> <%-- to preserve value --%>
<html:hidden property="site.countryName" /> <%-- to preserve value --%>
<html:hidden property="location.name" /> <%-- to preserve value --%>

<html:hidden property="dressCode.locationId" />

<html:hidden property="dressCode.dressCodeId" />
<html:hidden property="dressCode.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="client.name"/> (<bean:write name="DressCodeFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.site"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="site.name"/> (<bean:write name="DressCodeFormAdmin" property="site.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.location"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="location.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="dressCode.name"/></td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.active"/></td>
    <td align="left"><bean:write name="DressCodeFormAdmin" property="dressCode.active"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
