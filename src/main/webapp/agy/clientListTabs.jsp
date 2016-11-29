<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<bean:parameter id="tab" name="tab" value="A"/>
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

<table class="tabs" width="100%">
  <tr>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="A"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterA">
        <bean:write name="indexLetterA" />
      </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="B"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterB">
        <bean:write name="indexLetterB" />
      </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="C"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterC">
        <bean:write name="indexLetterC" />
      </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="D"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterD">
        <bean:write name="indexLetterD" />
      </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="E"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterE">
        <bean:write name="indexLetterE" />
      </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="F"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterF">
        <bean:write name="indexLetterF" />
      </html:link>
    </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="G"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterG">
        <bean:write name="indexLetterG" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="H"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterH">
        <bean:write name="indexLetterH" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="I"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterI">
        <bean:write name="indexLetterI" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="J"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterJ">
        <bean:write name="indexLetterJ" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="K"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterK">
        <bean:write name="indexLetterK" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="L"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterL">
        <bean:write name="indexLetterL" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="M"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterM">
        <bean:write name="indexLetterM" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="N"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterN">
        <bean:write name="indexLetterN" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="O"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterO">
        <bean:write name="indexLetterO" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="P"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterP">
        <bean:write name="indexLetterP" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="Q"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterQ">
        <bean:write name="indexLetterQ" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="R"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterR">
        <bean:write name="indexLetterR" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="S"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterS">
        <bean:write name="indexLetterS" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="T"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterT">
        <bean:write name="indexLetterT" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="U"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterU">
        <bean:write name="indexLetterU" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="V"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterV">
        <bean:write name="indexLetterV" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="W"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterW">
        <bean:write name="indexLetterW" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="X"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterX">
        <bean:write name="indexLetterX" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="Y"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterY">
        <bean:write name="indexLetterY" />
      </html:link>
     </td>
    <bean:define id="tabClass" value="tabNotSelected"/><logic:equal name="tab" value="Z"><bean:define id="tabClass" value="tabSelected"/></logic:equal>
    <td align="center" class="<bean:write name="tabClass"/>">
      <html:link forward="clientList" paramId="indexLetter"  paramName="indexLetterZ">
        <bean:write name="indexLetterZ" />
      </html:link>
     </td>
  </tr>
</table>
