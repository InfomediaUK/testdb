<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:parameter id="tab" name="tab" value="A"/>
<bean:parameter id="bookingGradeId" name="bookingGradeId" value="0"/>
<bean:define id="indexLetterA" value="A"/>
<bean:define id="indexLetterB" value="B"/>
<bean:define id="indexLetterC" value="C"/>
<bean:define id="indexLetterD" value="D"/>
<bean:define id="indexLetterE" value="E"/>
<bean:define id="indexLetterF" value="F"/>
<bean:define id="indexLetterG" value="G"/>
<bean:define id="indexLetterH" value="H"/>
<bean:define id="indexLetterI" value="I"/>
<bean:define id="indexLetterJ" value="J"/>
<bean:define id="indexLetterK" value="K"/>
<bean:define id="indexLetterL" value="L"/>
<bean:define id="indexLetterM" value="M"/>
<bean:define id="indexLetterN" value="N"/>
<bean:define id="indexLetterO" value="O"/>
<bean:define id="indexLetterP" value="P"/>
<bean:define id="indexLetterQ" value="Q"/>
<bean:define id="indexLetterR" value="R"/>
<bean:define id="indexLetterS" value="S"/>
<bean:define id="indexLetterT" value="T"/>
<bean:define id="indexLetterU" value="U"/>
<bean:define id="indexLetterV" value="V"/>
<bean:define id="indexLetterW" value="W"/>
<bean:define id="indexLetterX" value="X"/>
<bean:define id="indexLetterY" value="Y"/>
<bean:define id="indexLetterZ" value="Z"/>

<% 
String actionUrl = request.getContextPath() + "/agy/bookingGradeApplicantNew.do?bookingGrade.bookingGradeId=" + bookingGradeId + "&indexLetter=";
%>

<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="A"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterA}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterA %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="B"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterB}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterB %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="C"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterC}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterC %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="D"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterD}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterD %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="E"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterE}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterE %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="F"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterF}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterF %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="G"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterG}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterG %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="H"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterH}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterH %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="I"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterI}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterI %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="J"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterJ}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterJ %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="K"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterK}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterK %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="L"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterL}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterL %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="M"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterM}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterM %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="N"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterN}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterN %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="O"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterO}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterO %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="P"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterP}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterP %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="Q"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterQ}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterQ %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="R"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterR}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterR %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="S"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterS}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterS %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="T"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterT}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterT %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="U"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterU}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterU %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="V"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterV}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterV %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="W"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterW}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterW %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="X"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterX}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterX %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="Y"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterY}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterY %></a>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="Z"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <c:url value="/agy/bookingGradeApplicantNew.do" var="bookingGradeApplicantNewUrl">
        <c:param name="indexLetter" value="${indexLetterZ}"/>
        <c:param name="bookingGrade.bookingGradeId" value="${bookingGradeId}"/>
      </c:url>
	    <a href="<c:out value="${bookingGradeApplicantNewUrl}" />"><%= indexLetterZ %></a>
    </td>
  </tr>
</table>


