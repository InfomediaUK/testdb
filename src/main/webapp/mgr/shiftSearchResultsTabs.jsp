<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:parameter id="tab" name="tab" value="summary"/>
<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="summary"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
    <a href="javascript:doSearchAndSubmit('ShiftSearchFormMgr', 'shiftSearchProcess.do')">
    <bean:message key="link.tabSummary"/>
    </a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="financials"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
    <a href="javascript:doSearchAndSubmit('ShiftSearchFormMgr', 'shiftSearchProcess2.do')">
    <bean:message key="link.tabFinancials"/>
    </a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="applicants"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>" width="20%">
    <a href="javascript:doSearchAndSubmit('ShiftSearchFormMgr', 'shiftSearchProcess3.do')">
    <bean:message key="link.tabApplicants"/>
    </a>
    </td>
    <td align="center" width="40%">
      &nbsp;
    </td>
  </tr>
</table>
