<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<br/>
<h2><bean:message key="title.agySecretWord" /></h2>
<html:errors />
<table>
<html:form action="agySecretWordProcess.do" focus="secretWordValue1">
  <html:hidden property="secretWordKey1"/>
  <html:hidden property="secretWordKey2"/>
  <html:hidden property="secretWordKey3"/>
  <tr>
    <td align="left" class="label"><bean:write name="AgySecretWordForm" property="secretWordKey1"/></td>
    <td align="left"><html:select property="secretWordValue1" >
        <html:option value=""><bean:message key="label.pleaseSelectSingle"/></html:option>
        <html:option value="a">a</html:option><html:option value="b">b</html:option><html:option value="c">c</html:option>
        <html:option value="d">d</html:option><html:option value="e">e</html:option><html:option value="f">f</html:option>
        <html:option value="g">g</html:option><html:option value="h">h</html:option><html:option value="i">i</html:option>
        <html:option value="j">j</html:option><html:option value="k">k</html:option><html:option value="l">l</html:option>
        <html:option value="m">m</html:option><html:option value="n">n</html:option><html:option value="o">o</html:option>
        <html:option value="p">p</html:option><html:option value="q">q</html:option><html:option value="r">r</html:option>
        <html:option value="s">s</html:option><html:option value="t">t</html:option><html:option value="u">u</html:option>
        <html:option value="v">v</html:option><html:option value="w">w</html:option><html:option value="x">x</html:option>
        <html:option value="y">y</html:option><html:option value="z">z</html:option><html:option value="0">0</html:option>
        <html:option value="1">1</html:option><html:option value="2">2</html:option><html:option value="3">3</html:option>
        <html:option value="4">4</html:option><html:option value="5">5</html:option><html:option value="6">6</html:option>
        <html:option value="7">7</html:option><html:option value="8">8</html:option><html:option value="9">9</html:option>
                     </html:select></td>
    <td align="left" class="label"><bean:write name="AgySecretWordForm" property="secretWordKey2"/></td>
    <td align="left"><html:select property="secretWordValue2" >
        <html:option value=""><bean:message key="label.pleaseSelectSingle"/></html:option>
        <html:option value="a">a</html:option><html:option value="b">b</html:option><html:option value="c">c</html:option>
        <html:option value="d">d</html:option><html:option value="e">e</html:option><html:option value="f">f</html:option>
        <html:option value="g">g</html:option><html:option value="h">h</html:option><html:option value="i">i</html:option>
        <html:option value="j">j</html:option><html:option value="k">k</html:option><html:option value="l">l</html:option>
        <html:option value="m">m</html:option><html:option value="n">n</html:option><html:option value="o">o</html:option>
        <html:option value="p">p</html:option><html:option value="q">q</html:option><html:option value="r">r</html:option>
        <html:option value="s">s</html:option><html:option value="t">t</html:option><html:option value="u">u</html:option>
        <html:option value="v">v</html:option><html:option value="w">w</html:option><html:option value="x">x</html:option>
        <html:option value="y">y</html:option><html:option value="z">z</html:option><html:option value="0">0</html:option>
        <html:option value="1">1</html:option><html:option value="2">2</html:option><html:option value="3">3</html:option>
        <html:option value="4">4</html:option><html:option value="5">5</html:option><html:option value="6">6</html:option>
        <html:option value="7">7</html:option><html:option value="8">8</html:option><html:option value="9">9</html:option>
                     </html:select></td>
    <td align="left" class="label"><bean:write name="AgySecretWordForm" property="secretWordKey3"/></td>
    <td align="left"><html:select property="secretWordValue3" >
        <html:option value=""><bean:message key="label.pleaseSelectSingle"/></html:option>
        <html:option value="a">a</html:option><html:option value="b">b</html:option><html:option value="c">c</html:option>
        <html:option value="d">d</html:option><html:option value="e">e</html:option><html:option value="f">f</html:option>
        <html:option value="g">g</html:option><html:option value="h">h</html:option><html:option value="i">i</html:option>
        <html:option value="j">j</html:option><html:option value="k">k</html:option><html:option value="l">l</html:option>
        <html:option value="m">m</html:option><html:option value="n">n</html:option><html:option value="o">o</html:option>
        <html:option value="p">p</html:option><html:option value="q">q</html:option><html:option value="r">r</html:option>
        <html:option value="s">s</html:option><html:option value="t">t</html:option><html:option value="u">u</html:option>
        <html:option value="v">v</html:option><html:option value="w">w</html:option><html:option value="x">x</html:option>
        <html:option value="y">y</html:option><html:option value="z">z</html:option><html:option value="0">0</html:option>
        <html:option value="1">1</html:option><html:option value="2">2</html:option><html:option value="3">3</html:option>
        <html:option value="4">4</html:option><html:option value="5">5</html:option><html:option value="6">6</html:option>
        <html:option value="7">7</html:option><html:option value="8">8</html:option><html:option value="9">9</html:option>
                     </html:select></td>
  </tr>
  <tr>
    <td colspan="6" align="center">
      <br/>
      <html:submit styleClass="button"><bean:message key="button.continue"/></html:submit>&nbsp;
      <html:reset styleClass="button"><bean:message key="button.reset"/></html:reset>&nbsp;
      <html:cancel styleClass="button"><bean:message key="button.cancel"/></html:cancel>
    </td>
  </tr>
</html:form>
</table>
