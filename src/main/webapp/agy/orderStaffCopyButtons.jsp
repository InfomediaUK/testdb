<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:parameter id="nextButtonKey" name="nextButtonKey" value="button.next" />
<bean:parameter id="nextButtonTabIndex" name="nextButtonTabIndex" value="2" />
<bean:parameter id="backButtonTabIndex" name="backButtonTabIndex" value="3" />
<bean:parameter id="completeButtonTabIndex" name="completeButtonTabIndex" value="4" />
<html:hidden property="complete" value="false"/>
<table cellspacing="2" border="0" cellpadding="0">
  <tr>
    <td align="left">
      <p id="wizardbuttons"><%-- CSS reverses order of buttons to keep first declared default button on RIGHT --%>
        <html:submit styleId="submitButton" tabindex="<%= nextButtonTabIndex %>" styleClass="wizardButton"><bean:message name="nextButtonKey"/></html:submit>
        <html:cancel styleId="cancelButton" tabindex="<%= backButtonTabIndex %>" styleClass="wizardButton"><bean:message key="button.back"/></html:cancel>
      </p>
    </td>
<logic:equal name="nextButtonKey" value="button.next">
    <td align="left">
      <html:submit onclick="return OnComplete();" styleId="submitButton" tabindex="<%= completeButtonTabIndex %>" styleClass="wizardButton"><bean:message key="button.complete"/></html:submit>
    </td>
</logic:equal>
<logic:equal name="nextButtonKey" value="button.finish">
    <td align="left">
			<select name="bookingsToCreate" title="Bookings to Create">
			  <option selected="selected">1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>  
    </td>
</logic:equal>
  </tr>
</table>    
<script type="text/javascript">
function OnComplete()
{
  document.OrderStaffCopyFormAgy.elements['complete'].value = true;
}
</script>
