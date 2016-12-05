<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<display:table name="QueryFormAdmin.resultSetData.dynaBeans.rows" id="dynaBean" export="true" requestURI="queryProcess.do" pagesize="50">
<logic:iterate id="dynaProperty" name="QueryFormAdmin" property="resultSetData.dynaProperties" type="org.apache.commons.beanutils.DynaProperty">
<display:column title="<%= dynaProperty.getName() %>" sortable="true">
<%= ((org.apache.commons.beanutils.DynaBean)dynaBean).get(dynaProperty.getName()) %>
</display:column>
</logic:iterate>
</display:table>

		