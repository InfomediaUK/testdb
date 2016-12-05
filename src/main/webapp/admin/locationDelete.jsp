<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:message key="title.locationDelete"/>

<br/>
<br/>

<html:errors/>

<html:form action="locationDeleteProcess.do">

<html:hidden property="client.name" /> <%-- to preserve value --%>
<html:hidden property="client.countryName" /> <%-- to preserve value --%>
<html:hidden property="site.name" /> <%-- to preserve value --%>
<html:hidden property="site.countryName" /> <%-- to preserve value --%>

<html:hidden property="location.siteId" />

<html:hidden property="location.locationId" />
<html:hidden property="location.noOfChanges" />

<table>
  <tr>
    <td align="left"><bean:message key="label.client"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="client.name"/> (<bean:write name="LocationViewFormAdmin" property="client.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.site"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="site.name"/> (<bean:write name="LocationViewFormAdmin" property="site.countryName"/>)</td>
  </tr>
  <tr>
    <td align="left"><bean:message key="label.name"/></td>
    <td align="left"><bean:write name="LocationViewFormAdmin" property="location.name"/></td>
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
  <tr>
    <td colspan="2" align="center"><html:submit><bean:message key="button.delete"/></html:submit></td>
  </tr>
</table>

</html:form>
