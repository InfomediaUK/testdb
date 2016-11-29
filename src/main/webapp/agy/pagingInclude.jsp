<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:parameter id="actionPath" name="actionPath"        value="actionPath Not Set"/>
<bean:parameter id="formName"   name="formName"          value="formName Not Set"/>

<bean:define id="form"          name="<%= formName %>"              type="org.apache.struts.validator.DynaValidatorForm"/>
<bean:define id="currentPage"   name="form" property="page"         type="java.lang.Integer"/>
<bean:define id="previousPage"  name="form" property="previousPage" type="java.lang.Integer"/>
<bean:define id="nextPage"      name="form" property="nextPage"     type="java.lang.Integer"/>
<bean:define id="pageCount"     name="form" property="pageCount"    type="java.lang.Integer"/>

<%
String pageParameter   = (actionPath.indexOf('?') == -1) ? "?page=" : "&page=";
String pageActionPath  = null;
String pageNumberTitle = null;
%>

  <table class="tabs" width="100%">
    <tr>
      <bean:define id="tabClass" value="tabSelected"/>
      <td align="center" class="<bean:write name="tabClass"/>" >
      Page&nbsp;
			<logic:equal name="form" property="previousPage" value="-1">
			  &lt;
			</logic:equal>
			<logic:notEqual name="form" property="previousPage" value="-1">
			  <%
			    pageActionPath = actionPath;
			    if (previousPage > -1)
			    {
			      pageActionPath += pageParameter + previousPage;
			    }
			  %>
			  <html:link page="<%= pageActionPath %>" title="Previous Page">
			    &lt;
			  </html:link>
			</logic:notEqual>

<%
int pageNumber = 0;
String pageTitle = null;
for(int i = 0; i < pageCount; i++)
{
  pageActionPath = actionPath;
  pageNumber = (i + 1);
  pageNumberTitle = "Page Number " + pageNumber;
  if (pageNumber > -1)
  {
    pageActionPath += pageParameter + pageNumber;
  }
  if (currentPage.intValue() == pageNumber)
  {
%>
<%= pageNumber %>&nbsp;
<%
  }
  else
  {
%>
<html:link page="<%= pageActionPath %>"  title="<%= pageNumberTitle %>">
 <%= pageNumber %>&nbsp;
</html:link>
<%
  }
}
%>      
			<logic:equal name="form" property="nextPage" value="-1">
			  &gt;
			</logic:equal>
			<logic:notEqual name="form" property="nextPage" value="-1">
			  <%
			    pageActionPath = actionPath + pageParameter + nextPage;
			  %>
			  <html:link page="<%= pageActionPath %>" title="Next Page">
			    &gt;
			  </html:link>
			</logic:notEqual>
			</td>
      <td align="center" class="tabInvisibleClass" width="60%">
        &nbsp;
      </td>
    </tr>
  </table>
			