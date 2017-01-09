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
<bean:message key="title.locationList"/>
		</td>
  </tr>
</table>

<logic:notPresent name="LocationListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:notPresent>
<logic:present name="LocationListFormMgr" property="list">
<logic:empty name="LocationListFormMgr" property="list">
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="LocationListFormMgr" property="list">
<table class="simple" width="100%">
  <thead>
  <tr>
    <th align="left" colspan="5">
      <bean:message key="label.location"/>&nbsp;-&nbsp;<bean:message key="label.jobProfile"/>
    </th>
    <th align="left"><bean:message key="label.budget"/></th>
    <th align="left"><bean:message key="label.vat"/></th>
    <th align="left"><bean:message key="label.expense"/></th>
    <th align="left"><bean:message key="label.rrpRate"/></th>
    <th align="left"><bean:message key="label.jobDescription"/></th>
  </tr>
  </thead>
  <bean:define id="siteName" value=""/>
  <bean:define id="locationName" value=""/>
  <bean:define id="jobFamilyName" value=""/>
  <bean:define id="jobSubFamilyName" value=""/>
  <logic:iterate id="location" name="LocationListFormMgr" property="list" indexId="locationIndex">
    <logic:notEqual name="location" property="siteName" value="<%= siteName %>">
		  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
		    <td align="left" colspan="5">
	        <bean:write name="location" property="siteName"/>
	      </td>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
	    </tr>
      <bean:define id="siteName" name="location" property="siteName" type="java.lang.String"/>
		  <bean:define id="locationName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="location" property="locationName" value="<%= locationName %>">
		  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
		    <td width="15">
		      <html:img src="images/indent.gif" width="15" height="9"/>
		    </td>
		    <td align="left" colspan="4">
		      <bean:write name="location" property="locationName"/>
        </td>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
      </tr>
      <bean:define id="locationName" name="location" property="locationName" type="java.lang.String"/>
		  <bean:define id="jobFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="location" property="jobFamilyName" value="<%= jobFamilyName %>">
		  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
		    <th width="15">
		      <html:img src="images/trans.gif" width="15" height="9"/>
		    </th>
		    <td width="15">
		      <html:img src="images/indent.gif" width="15" height="9"/>
		    </td>
		    <td align="left" colspan="3">
          <bean:write name="location" property="jobFamilyName"/>
        </td>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
      </tr>
      <bean:define id="jobFamilyName" name="location" property="jobFamilyName" type="java.lang.String"/>
		  <bean:define id="jobSubFamilyName" value=""/>
    </logic:notEqual>
    <logic:notEqual name="location" property="jobSubFamilyName" value="<%= jobSubFamilyName %>">
		  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
		    <th width="15">
		      <html:img src="images/trans.gif" width="15" height="9"/>
		    </th>
		    <th width="15">
		      <html:img src="images/trans.gif" width="15" height="9"/>
		    </th>
		    <td width="15">
		      <html:img src="images/indent.gif" width="15" height="9"/>
		    </td>
		    <td align="left" colspan="2">
          <bean:write name="location" property="jobSubFamilyName"/>
        </td>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
		    <th align="left">&nbsp;</th>
      </tr>
      <bean:define id="jobSubFamilyName" name="location" property="jobSubFamilyName" type="java.lang.String"/>
    </logic:notEqual>
		  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
		    <th width="15">
		      <html:img src="images/trans.gif" width="15" height="9"/>
		    </th>
		    <th width="15">
		      <html:img src="images/trans.gif" width="15" height="9"/>
		    </th>
		    <th width="15">
		      <html:img src="images/trans.gif" width="15" height="9"/>
		    </th>
		    <td width="15">
		      <html:img src="images/indent.gif" width="15" height="9"/>
		    </td>
		    <td align="left">
				<mmj-mgr:hasAccess forward="budgetTransactionNew">
		  	  <html:link forward="budgetTransactionNew" paramId="locationJobProfile.locationJobProfileId" paramName="location" paramProperty="locationJobProfileId"><bean:write name="location" property="jobProfileName"/></html:link>
				</mmj-mgr:hasAccess>
				<mmj-mgr:hasNoAccess forward="budgetTransactionNew">
          <bean:write name="location" property="jobProfileName"/>
				</mmj-mgr:hasNoAccess>
<%--
          (<bean:write name="location" property="clientCode"/><bean:write name="location" property="siteCode"/><bean:write name="location" property="locationCode"/><bean:write name="location" property="jobFamilyCode"/><bean:write name="location" property="jobSubFamilyCode"/><bean:write name="location" property="jobProfileCode"/>)
--%>          
        </td>
		    <td align="right">
				<mmj-mgr:hasAccess forward="budgetTransactionList">
				  <html:link forward="budgetTransactionList" paramId="locationJobProfile.locationJobProfileId" paramName="location" paramProperty="locationJobProfileId"><bean:message key="label.currencySymbol"/><bean:write name="location" property="budget" format="#,##0.00"/></html:link>
				</mmj-mgr:hasAccess>
				<mmj-mgr:hasNoAccess forward="budgetTransactionList">
		  	  <bean:message key="label.currencySymbol"/><bean:write name="location" property="budget" format="#,##0.00"/>
				</mmj-mgr:hasNoAccess>
		  	</td>
		    <td align="right">
				<mmj-mgr:hasAccess forward="budgetTransactionList">
				  <html:link forward="budgetTransactionListVat" paramId="locationJobProfile.locationJobProfileId" paramName="location" paramProperty="locationJobProfileId"><bean:message key="label.currencySymbol"/><bean:write name="location" property="vatBudget" format="#,##0.00"/></html:link>
				</mmj-mgr:hasAccess>
				<mmj-mgr:hasNoAccess forward="budgetTransactionList">
		  	  <bean:message key="label.currencySymbol"/><bean:write name="location" property="vatBudget" format="#,##0.00"/>
				</mmj-mgr:hasNoAccess>
		  	</td>
		    <td align="right">
				<mmj-mgr:hasAccess forward="budgetTransactionList">
				  <html:link forward="budgetTransactionListExpense" paramId="locationJobProfile.locationJobProfileId" paramName="location" paramProperty="locationJobProfileId"><bean:message key="label.currencySymbol"/><bean:write name="location" property="expenseBudget" format="#,##0.00"/></html:link>
				</mmj-mgr:hasAccess>
				<mmj-mgr:hasNoAccess forward="budgetTransactionList">
		  	  <bean:message key="label.currencySymbol"/><bean:write name="location" property="expenseBudget" format="#,##0.00"/>
				</mmj-mgr:hasNoAccess>
		  	</td>
		    <td align="right">
      	  <bean:message key="label.currencySymbol"/><bean:write name="location" property="rate" format="#0.00"/>
		  	</td>
		    <td align="left">
				  <logic:present name="location" property="documentURL">
				  <bean:define id="documentURL" name="location" property="documentURL" type="java.lang.String"/>
				  <html:link href="<%= documentURL %>" target="documentURLTarget">
				    <bean:message key="link.view"/>
				  </html:link>
				  </logic:present>
				  <logic:notPresent name="location" property="documentURL">
				    &nbsp;
          </logic:notPresent>
        </td>
      </tr> 
  </logic:iterate>
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
    <th colspan="5">
        <bean:message key="label.total"/>
    </th>
    <td align="right">
  	  <bean:message key="label.currencySymbol"/><bean:write name="LocationListFormMgr" property="totalBudget" format="#,##0.00"/>
  	</td>
    <td align="right">
  	  <bean:message key="label.currencySymbol"/><bean:write name="LocationListFormMgr" property="totalVatBudget" format="#,##0.00"/>
  	</td>
    <td align="right">
  	  <bean:message key="label.currencySymbol"/><bean:write name="LocationListFormMgr" property="totalExpenseBudget" format="#,##0.00"/>
  	</td>
    <th colspan="2">
        &nbsp;
    </th>
  </tr> 
</table> 
</logic:notEmpty> 
</logic:present>