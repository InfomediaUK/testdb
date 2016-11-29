<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ 
taglib uri="/mmj-agy" prefix="mmj-agy" %>
<logic:empty name="BookingGradeApplicantFormAgy" property="list">
<br/>
<br/>
<bean:message key="error.noApplicant"/>
</logic:empty>
<logic:notEmpty name="BookingGradeApplicantFormAgy" property="list" >
<html:form action="/bookingGradeApplicantNew5.do" enctype="multipart/form-data" onsubmit="return singleSubmit();">
<html:hidden property="page" value="4"/>
<table>
  <tr>
    <td align="left" valign="middle" width="250">
      <bean:message key="title.bookingGradeApplicantNewStep4"/>&nbsp;-&nbsp;<bean:message key="title.bookingGradeApplicantNew4"/>
    </td>
    <td align="left" valign="top">
      <jsp:include page="bookingGradeApplicantNewButtons.jsp" flush="true" >
        <jsp:param name="nextButtonTabIndex" value="3" />
        <jsp:param name="backButtonTabIndex" value="4" />
      </jsp:include>
    </td>
  </tr>
</table>
<hr/>
<html:errors />
<table class="simple" width="100%">
  <tr>
    <th align="left">
      <bean:message key="label.cvFilename" />
    </th>
    <td align="left">
      <html:file property="document" size="30" />
    </td>
  </tr>

<logic:notEmpty name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.filename">
  <tr>
    <th align="left" class="label"><bean:message key="label.cv"/></th>
    <td align="left">
      <bean:define id="documentUrl" name="BookingGradeApplicantFormAgy" property="bookingGradeApplicant.documentUrl" type="java.lang.String" />
      <html:link href="<%= request.getContextPath() + documentUrl %>" target="_blank"><bean:message key="link.view"/></html:link>
    </td>
  </tr>
</logic:notEmpty>

</table>
</html:form>
</logic:notEmpty>