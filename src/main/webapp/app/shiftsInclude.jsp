<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:parameter id="theFormApp" name="theFormApp" value="ListFormApp"/>
<bean:parameter id="theList" name="theList" value="list"/>
<bean:parameter id="theUpliftFactors" name="theUpliftFactors" value="upliftFactors"/>
<bean:parameter id="expenseCount" name="expenseCount" value="0"/>
<bean:parameter id="showTotals" name="showTotals" value="false"/>
<bean:parameter id="showCheckbox" name="showCheckbox" value="false"/>
<bean:parameter id="show" name="show" value="weekly"/>
<bean:parameter id="hideMoney" name="hideMoney" value="false"/>
<bean:parameter id="allowChange" name="allowChange" value="true"/>

<bean:define id="hideMoneyColumnCount" value="1" type="java.lang.String"/>
<logic:equal name="hideMoney" value="true">
	<bean:define id="hideMoneyColumnCount" value="0" type="java.lang.String"/>
</logic:equal>

<logic:empty name="<%= theFormApp %>" property="<%= theList %>" >
  <logic:equal name="show" value="weekly">
	<br/>
	<bean:write name="<%= theFormApp %>" property="startDate" formatKey="format.longDateFormat" />
	-
	<bean:write name="<%= theFormApp %>" property="endDate" formatKey="format.longDateFormat" />
	<br/>
  </logic:equal>
  <br/>
  <bean:message key="text.noDetails"/>
</logic:empty>
<logic:notEmpty name="<%= theFormApp %>" property="<%= theList %>" >

<bean:define id="showAgreed" value="false" type="java.lang.String"/>

<bean:define id="upliftFactors" name="<%= theFormApp %>" property="<%= theUpliftFactors %>" type="java.util.List"/>

<table class="simple" width="100%">
  <tr>
    <td align="center" colspan="3">
	  <logic:equal name="show" value="weekly">
		<bean:write name="<%= theFormApp %>" property="startDate" formatKey="format.longDateFormat" />
		-
		<bean:write name="<%= theFormApp %>" property="endDate" formatKey="format.longDateFormat" />
	  </logic:equal>
	  <logic:notEqual name="show" value="weekly">
  	  &nbsp;
	  </logic:notEqual>
    </td>
<logic:equal name="showAgreed" value="true">
    <th align="center" colspan="5">
	  <bean:message key="label.agreed"/>
    </th>
</logic:equal>
    <bean:define id="colspan" value="<%= Integer.toString(3 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>"/>
    <logic:greaterThan name="expenseCount" value="0">
      <bean:define id="colspan" value="<%= Integer.toString(4 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>"/>
    </logic:greaterThan>
    <th align="center" colspan="<bean:write name="colspan"/>">
	  <bean:message key="label.actual"/>
    </th>
    <td align="left">
  	  &nbsp;
    </td>
    <logic:equal name="showCheckbox" value="true">
    <td align="center">
  	  &nbsp;
    </td>
    </logic:equal>
  </tr>
  <tr>
    <th align="center">
  	  <bean:message key="label.shiftNo"/>
    </th>
    <th align="left">
	  <bean:message key="label.date"/>
    </th>
    <th align="left">
	  <bean:message key="label.shift"/>
    </th>
<logic:equal name="showAgreed" value="true">
    <th align="center">
	  <bean:message key="label.time"/>
    </th>
    <th align="center">
	  <bean:message key="label.break"/>
    </th>
    <th align="center">
	  <bean:message key="label.hrs"/>
    </th>
    <th align="center">
	  <bean:message key="label.value"/>
    </th>
    <th align="center">
			<bean:message key="label.overtimeShort"/>
    </th>
</logic:equal>
    <th align="center">
	  <bean:message key="label.time"/>
    </th>
    <th align="center">
	  <bean:message key="label.break"/>
    </th>
    <th align="center">
	  <bean:message key="label.hrs"/>
    </th>
		<logic:iterate name="<%= theFormApp %>" property="<%= theUpliftFactors %>" id="upliftFactor"  indexId="upliftFactorIndex">
    <th align="center">
		  x<bean:write name="upliftFactor" format="#0.##"/> 
    </th>
		</logic:iterate>
<logic:equal name="hideMoney" value="false">
    <th align="center">
	  <bean:message key="label.value"/>
    </th>
</logic:equal>
    <logic:greaterThan name="expenseCount" value="0">
    <th align="center">
	  <bean:message key="label.expenses"/>
    </th>
    </logic:greaterThan>
    <th align="center">
	  <bean:message key="label.status"/>
    </th>
    <logic:equal name="showCheckbox" value="true">
    <th align="center">
    <input type="checkbox" border="0" title="<bean:message key="text.toggle"/>" onclick="javascript:toggle(this.checked)"/>
    </th>
    </logic:equal>
  </tr>

  <bean:define id="agreedColspan" value="0" type="java.lang.String"/>
  <logic:equal name="showAgreed" value="true">
    <bean:define id="agreedColspan" value="5" type="java.lang.String"/>
  </logic:equal>

  <bean:define id="colspan" value="<%= Integer.toString(Integer.parseInt(agreedColspan) + 7 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>"/>
  <logic:equal name="showCheckbox" value="true">
  <bean:define id="colspan" value="<%= Integer.toString(Integer.parseInt(agreedColspan) + 8 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>"/>
  </logic:equal>
  <logic:greaterThan name="expenseCount" value="0">
  <bean:define id="colspan" value="<%= Integer.toString(Integer.parseInt(agreedColspan) + 8 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>"/>
  <logic:equal name="showCheckbox" value="true">
  <bean:define id="colspan" value="<%= Integer.toString(Integer.parseInt(agreedColspan) + 9 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>"/>
  </logic:equal>
  </logic:greaterThan>

  <tr><td align="left" class="divider" colspan="<bean:write name="colspan"/>" height="3"></td></tr>

  <logic:iterate name="<%= theFormApp %>" property="<%= theList %>" id="bookingGradeApplicantDate"  indexId="bookingDateIndex">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <th align="left">
	    <bean:write name="bookingGradeApplicantDate" property="bookingId" format="#000"/>.<bean:write name="bookingGradeApplicantDate" property="bookingDateId" format="#000"/>
	  </th>
	  <td align="left" nowrap="nowrap">
	    <bean:write name="bookingGradeApplicantDate" property="bookingDate" formatKey="format.longDateFormat" />
	  </td>
	  <td align="left" nowrap="nowrap">
	    <bean:write name="bookingGradeApplicantDate" property="shiftName"/>
	  </td>
<logic:equal name="showAgreed" value="true">
	  <td align="center" nowrap="nowrap">
	    <bean:write name="bookingGradeApplicantDate" property="shiftStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="shiftEndTime" format="HH:mm"/>
	  </td>
	  <td align="center" nowrap="nowrap">
	    <bean:write name="bookingGradeApplicantDate" property="shiftBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="shiftBreakEndTime" format="HH:mm"/>
	    <%/* (<bean:write name="bookingGradeApplicantDate" property="shiftBreakNoOfHours" format="#0.00"/>) */%>
	  </td>
	  <td align="right">
        <logic:equal name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
  	      <s><bean:write name="bookingGradeApplicantDate" property="shiftNoOfHours" format="#0.00"/></s>
        </logic:equal>
        <logic:notEqual name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
  	      <bean:write name="bookingGradeApplicantDate" property="shiftNoOfHours" format="#0.00"/>
        </logic:notEqual>
	  </td>
	  <td align="right">
        <logic:equal name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
  	      <s><bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="wageRateValue" format="#,##0.00"/></s>
        </logic:equal>
        <logic:notEqual name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
          <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="wageRateValue" format="#,##0.00"/>
        </logic:notEqual>
	  </td>
	  <td align="center">
      <logic:greaterThan name="bookingGradeApplicantDate" property="shiftOvertimeNoOfHours" value="0">
      &gt;<bean:write name="bookingGradeApplicantDate" property="shiftOvertimeNoOfHours" format="#0.##"/>&nbsp;x<bean:write name="bookingGradeApplicantDate" property="shiftOvertimeUpliftFactor" format="#0.##"/>
      </logic:greaterThan>
      <logic:lessEqual name="bookingGradeApplicantDate" property="shiftOvertimeNoOfHours" value="0">
        <bean:message key="label.none"/>
      </logic:lessEqual>
	  </td>
</logic:equal>	  
  	<logic:notEqual name="bookingGradeApplicantDate" property="hasBeenEntered" value="true">
	  <td align="center" colspan="<%= Integer.toString(3 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>">
	  	<logic:equal name="bookingGradeApplicantDate" property="canBeEdited" value="true">
        <logic:equal name="allowChange" value="true">
	        <html:link forward="bookingDateWorked" paramId="bookingDate.bookingDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingDateId" titleKey="link.title.enterTimes">
            <bean:message key="link.enterTimes"/>
	        </html:link>
  	    </logic:equal>
  	  </logic:equal>
	  	<logic:notEqual name="bookingGradeApplicantDate" property="canBeEdited" value="true">
  	      &nbsp;
	    </logic:notEqual>
	  </td>
    </logic:notEqual>
  	<logic:equal name="bookingGradeApplicantDate" property="hasBeenEntered" value="true">
	  <td align="center" nowrap="nowrap">
	  	<logic:equal name="bookingGradeApplicantDate" property="canBeEdited" value="true">
        <logic:equal name="allowChange" value="true">
		      <html:link forward="bookingDateWorked" paramId="bookingDate.bookingDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingDateId" titleKey="link.title.editTimes">
	 	        <bean:write name="bookingGradeApplicantDate" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="workedEndTime" format="HH:mm"/>
		      </html:link>
        </logic:equal>
      </logic:equal>
	  	<logic:notEqual name="bookingGradeApplicantDate" property="canBeEdited" value="true">
  	      <bean:write name="bookingGradeApplicantDate" property="workedStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="workedEndTime" format="HH:mm"/>
  	  </logic:notEqual>
	  </td>
	  <td align="center" nowrap="nowrap">
	    <bean:write name="bookingGradeApplicantDate" property="workedBreakStartTime" format="HH:mm"/>&nbsp;-&nbsp;<bean:write name="bookingGradeApplicantDate" property="workedBreakEndTime" format="HH:mm"/>
	    <%/* (<bean:write name="bookingGradeApplicantDate" property="workedBreakNoOfHours" format="#0.00"/>) */%>
	  </td>
	  <td align="right">
<%--
      <logic:equal name="bookingGradeApplicantDate" property="hasUplift" value="true">
      *
      </logic:equal>
--%>
	    <bean:write name="bookingGradeApplicantDate" property="workedNoOfHours" format="#0.00"/>
	  </td>
		<logic:iterate name="<%= theFormApp %>" property="<%= theUpliftFactors %>" id="upliftFactor" indexId="upliftFactorIndex" type="java.math.BigDecimal">
			<%
			java.math.BigDecimal upliftHours = new java.math.BigDecimal(0);
			%>	    
		    <logic:present name="bookingGradeApplicantDate" property="bookingDateHours">
			  <logic:iterate name="bookingGradeApplicantDate" property="bookingDateHours" id="bookingDateHour" indexId="bookingDateHourIndex" type="com.helmet.bean.BookingDateHour">
					<%
					if (bookingDateHour.getUpliftFactor().compareTo(upliftFactor) == 0) {
						java.math.BigDecimal portionOfHour = bookingDateHour.getChargeRateValue().compareTo(new java.math.BigDecimal(0)) < 0 ? bookingDateHour.getPortionOfHour().multiply(new java.math.BigDecimal(-1)) : bookingDateHour.getPortionOfHour();
						upliftHours = upliftHours.add(portionOfHour);
					}
					%>
		      </logic:iterate>
		    </logic:present>
			<%
			pageContext.setAttribute("upliftHours", upliftHours);
			%>
		  <td align="right">
		      <bean:write name="upliftHours" scope="page" format="#0.00"/>
		  </td>
		</logic:iterate>
<logic:equal name="hideMoney" value="false">
	  <td align="right">
        <bean:message key="label.currencySymbol"/><bean:write name="bookingGradeApplicantDate" property="workedWageRateValue" format="#,##0.00"/>
	  </td>
</logic:equal>
    </logic:equal>

    <logic:greaterThan name="expenseCount" value="0">
	  <td align="center">
	  	<logic:equal name="bookingGradeApplicantDate" property="canBeEdited" value="true">
        <logic:equal name="allowChange" value="true">
  	      <html:link forward="bookingDateExpenseNew" paramId="bookingDate.bookingDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingDateId" titleKey="link.title.enterExpenseClaim">
    	      <bean:message key="link.claimExpense"/>
  	      </html:link>
  	    </logic:equal>
  	  </logic:equal>
	  	<logic:notEqual name="bookingGradeApplicantDate" property="canBeEdited" value="true">
          &nbsp;
	    </logic:notEqual>
	  </td>
      </logic:greaterThan>

	  <td align="left">
        <logic:equal name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
    	  <bean:message name="bookingGradeApplicantDate" property="bookingDateStatusDescriptionKey"/>
        </logic:equal>
        <logic:notEqual name="bookingGradeApplicantDate" property="bookingDateIsCancelled" value="true">
		  <logic:notEqual name="bookingGradeApplicantDate" property="hasBeenEntered" value="true">
		  	<logic:equal name="bookingGradeApplicantDate" property="canBeEdited" value="true">
		      <bean:message key="text.activated" />
		    </logic:equal>
		    <logic:notEqual name="bookingGradeApplicantDate" property="canBeEdited" value="true">
		      <bean:message key="text.notActivated" />
		    </logic:notEqual>
		  </logic:notEqual>
		  <logic:equal name="bookingGradeApplicantDate" property="hasBeenEntered" value="true">
     	    <logic:equal name="bookingGradeApplicantDate" property="workedStatusIsRejected" value="true">
      	      <bean:message name="bookingGradeApplicantDate" property="workedStatusDescriptionKey"/>
            </logic:equal> 
     	    <logic:notEqual name="bookingGradeApplicantDate" property="workedStatusIsRejected" value="true">
			  <logic:equal name="bookingGradeApplicantDate" property="canBeSubmitted" value="true">
			    <bean:message key="text.activated" />
			  </logic:equal>
			  <logic:notEqual name="bookingGradeApplicantDate" property="canBeSubmitted" value="true">
			    <logic:equal name="bookingGradeApplicantDate" property="canBeWithdrawn" value="true">
			      <html:link forward="bookingDateWithdraw" paramId="bookingDate.bookingDateId" paramName="bookingGradeApplicantDate" paramProperty="bookingDateId">
		            <bean:message key="link.withdraw"/>
			      </html:link>
                </logic:equal>
			    <logic:notEqual name="bookingGradeApplicantDate" property="canBeWithdrawn" value="true">
	 	   	      <bean:message name="bookingGradeApplicantDate" property="workedStatusDescriptionKey"/>
                </logic:notEqual>
			  </logic:notEqual>
			</logic:notEqual>
	    </logic:equal>
	    </logic:notEqual>
 	  </td>
      <logic:equal name="showCheckbox" value="true">
		<logic:equal name="bookingGradeApplicantDate" property="canBeSubmitted" value="true">
		  <td align="center">
			<html:multibox name="<%= theFormApp %>" property="selectedBookingDates" styleId="<%= \"bd\" + bookingDateIndex %>"  onclick="javascript:enableSubmitButton()" >
			  <bean:write name="bookingGradeApplicantDate" property="bookingDateId"/>
			</html:multibox>
		  </td>
		</logic:equal>
		<logic:notEqual name="bookingGradeApplicantDate" property="canBeSubmitted" value="true">
		  <td>&nbsp;</td>
		</logic:notEqual>
      </logic:equal>
	</tr>

	<%/* COMMENT */%>
	<logic:notEmpty name="bookingGradeApplicantDate" property="comment">
	<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left" colspan="<%= Integer.toString(Integer.parseInt(agreedColspan) + 3) %>">
        &nbsp;
      </td>
	  <td align="left" colspan="<%= Integer.toString(3 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>">
        <bean:write name="bookingGradeApplicantDate" property="comment"/>
      </td>
      <logic:greaterThan name="expenseCount" value="0">
    	<td>&nbsp;</td>
      </logic:greaterThan>
      <td>&nbsp;</td>
      <logic:equal name="showCheckbox" value="true">
    	<td>&nbsp;</td>
      </logic:equal>
    </tr>
    </logic:notEmpty>

	<%-- EXPENSES --%>
    <logic:notEmpty name="bookingGradeApplicantDate" property="bookingDateExpenseUsers" >
	<logic:iterate name="bookingGradeApplicantDate" property="bookingDateExpenseUsers" id="bookingDateExpense" >
	<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left" colspan="<%= Integer.toString(Integer.parseInt(agreedColspan) + 3) %>">
        &nbsp;
      </td>
      <td align="left" colspan="<%= Integer.toString(3 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>">
	  	<logic:equal name="bookingGradeApplicantDate" property="canBeEdited" value="true">
        <logic:equal name="allowChange" value="true">
	        <html:link forward="bookingDateExpenseEdit" paramId="bookingDateExpense.bookingDateExpenseId" paramName="bookingDateExpense" paramProperty="bookingDateExpenseId" titleKey="link.title.editExpenseClaim"><bean:write name="bookingDateExpense" property="expenseName"/></html:link>
	      </logic:equal>
	    </logic:equal>
	  	<logic:notEqual name="bookingGradeApplicantDate" property="canBeEdited" value="true">
          <bean:write name="bookingDateExpense" property="expenseName"/>
        </logic:notEqual>
        <%/* 
		<logic:notEmpty name="bookingDateExpense" property="expenseDescription">
          (<bean:write name="bookingDateExpense" property="expenseDescription"/>)
        </logic:notEmpty>
        */%>
		<logic:equal name="bookingDateExpense" property="isMultiplier" value="true">
          -
          <bean:write name="bookingDateExpense" property="qty" format="#0.00"/>
		  @
		  <bean:message key="label.currencySymbol"/><bean:write name="bookingDateExpense" property="expenseMultiplier" format="#0.00"/>
  		</logic:equal>
		<logic:notEmpty name="bookingDateExpense" property="text">
          -
          <bean:write name="bookingDateExpense" property="text"/>
		</logic:notEmpty>
	  </td>
	  <td align="right">
        <bean:message key="label.currencySymbol" /><bean:write name="bookingDateExpense" property="value" format="#0.00"/>
	  	<logic:equal name="bookingGradeApplicantDate" property="canBeEdited" value="true">
        <logic:equal name="allowChange" value="true">
	        <html:link forward="bookingDateExpenseDelete" paramId="bookingDateExpense.bookingDateExpenseId" paramName="bookingDateExpense" paramProperty="bookingDateExpenseId" titleKey="link.title.deleteExpenseClaim"><bean:message key="link.deleteShort"/></html:link>
        </logic:equal>
      </logic:equal>
      </td>
	  <td align="left">
	    <logic:empty name="bookingDateExpense" property="filename">
          <bean:message key="text.noReceipt"/>
		</logic:empty>
		<logic:notEmpty name="bookingDateExpense" property="filename">
          <bean:define id="documentUrl" name="bookingDateExpense" property="documentUrl" type="java.lang.String" />
          <html:link href="<%= request.getContextPath() + documentUrl %>" target="_blank" titleKey="link.title.viewReceipt"><bean:message key="link.viewReceipt"/></html:link>
		</logic:notEmpty>
      </td>
	  <logic:equal name="showCheckbox" value="true">
	    <td>&nbsp;</td>
	  </logic:equal>
	</tr>
	</logic:iterate>
	</logic:notEmpty>

	<%/* REJECT */%>
	<logic:equal name="bookingGradeApplicantDate" property="workedStatusIsRejected" value="true">
	<tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
	  <td align="left" colspan="<%= Integer.toString(Integer.parseInt(agreedColspan) + 3) %>">
        &nbsp;
      </td>
	  <td align="left" colspan="<%= Integer.toString(3 + Integer.parseInt(hideMoneyColumnCount) + upliftFactors.size()) %>">
        <bean:write name="bookingGradeApplicantDate" property="rejectText"/>
      </td>
      <logic:greaterThan name="expenseCount" value="0">
    	<td>&nbsp;</td>
      </logic:greaterThan>
      <td>&nbsp;</td>
      <logic:equal name="showCheckbox" value="true">
    	<td>&nbsp;</td>
      </logic:equal>
    </tr>
    </logic:equal>

  <tr><td align="left" class="divider" colspan="<bean:write name="colspan"/>" height="3"></td></tr>
  </logic:iterate>

  <%/* TOTALS */%>
  <logic:equal name="showTotals" value="true">
  <tr class="highlightoff" onmouseover="this.className='highlighton';" onmouseout="this.className='highlightoff';">
  <bean:define id="agreedColspan" value="0" type="java.lang.String"/>
  <logic:equal name="showAgreed" value="true">
    <bean:define id="agreedColspan" value="2" type="java.lang.String"/>
  </logic:equal>
  <th align="left" colspan="<%= Integer.toString(Integer.parseInt(agreedColspan) + 3) %>"><bean:message key="label.total"/></th>
<logic:equal name="showAgreed" value="true">
	<td align="right">
	  <bean:write name="<%= theFormApp %>" property="totalAgreedHours" format="#0.00"/>
	</td>
	<td align="right">
	  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormApp %>" property="totalAgreedValue" format="#,##0.00" />
	</td>
  <th align="left" >&nbsp;</th>
</logic:equal>
  <th align="left" colspan="2">&nbsp;</th>
	<td align="right">
	  <bean:write name="<%= theFormApp %>" property="totalActualHours" format="#0.00"/>
	</td>
	<%
	if (upliftFactors.size() > 0) {
	%>
  <th align="left" colspan="<%= Integer.toString(upliftFactors.size()) %>">&nbsp;</th>
  <%
  }
  %>

<logic:equal name="hideMoney" value="false">
	<td align="right">
	  <bean:message key="label.currencySymbol"/><bean:write name="<%= theFormApp %>" property="totalActualValue" format="#,##0.00" />
	</td>
</logic:equal>

    <logic:greaterThan name="expenseCount" value="0">
	  <td align="right"><bean:message key="label.currencySymbol"/><bean:write name="<%= theFormApp %>" property="totalExpenseValue" format="#,##0.00" /></td>
    </logic:greaterThan>
	<th align="right">&nbsp;</th>
    <logic:equal name="showCheckbox" value="true">
      <th>&nbsp;</th>
    </logic:equal>
  </tr>
  </logic:equal>
  
</table>

</logic:notEmpty>

<logic:equal name="showCheckbox" value="true">
<bean:define id="bdList" name="<%= theFormApp %>" property="<%= theList %>" type="java.util.List" />
<script type="text/javascript">//<![CDATA[
<!--  to hide script contents from old browsers
function toggle(checked) {
  for (i = 0; i < <%= bdList.size() %>; i++) {
    if (document.forms["BookingDatesFormApp"].elements["bd" + i]) {
      document.forms["BookingDatesFormApp"].elements["bd" + i].checked = checked;
    }
  }
  enableSubmitButton();
}
function enableSubmitButton() {
  if (document.forms["BookingDatesFormApp"].elements["submitButton"]) {
    document.forms["BookingDatesFormApp"].elements["submitButton"].disabled = true;
	for (i = 0; i < <%= bdList.size() %>; i++) {
	  if (document.forms["BookingDatesFormApp"].elements["bd" + i]) {
	    if (document.forms["BookingDatesFormApp"].elements["bd" + i].checked) {
          document.forms["BookingDatesFormApp"].elements["submitButton"].disabled = false;
	      break;
		}
      }
	}
  }
}

enableSubmitButton();

// end hiding contents from old browsers  -->
</script>
</logic:equal>

