<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/mmj-admin" prefix="mmj-admin" %>

<bean:message key="title.gradeOrder"/>

<br/>
<br/>
<bean:write name="ClientDisplayOrderFormAdmin" property="client.name"/>
<br/>
<br/>

<logic:present name="ClientDisplayOrderFormAdmin" property="list">
<form action="gradeOrderProcess.do" name="ClientDisplayOrderFormAdmin">
  <html:select name="ClientDisplayOrderFormAdmin" property="commaDelimitedKey" size="20" style="width: 400px">
  <logic:iterate id="grade" name="ClientDisplayOrderFormAdmin" property="list" type="com.helmet.bean.Grade">
    <bean:define id="id" name="grade" property="gradeId" />
    <bean:define id="noOfChanges" name="grade" property="noOfChanges" />
    <bean:define id="name" name="grade" property="name" />
    <option value="<%= id %>,<%= noOfChanges %>"><%= name %></option>
  </logic:iterate>
  </html:select>
  <br/>
  <br/>
  <html:button onclick="javascript:swap(document.ClientDisplayOrderFormAdmin.commaDelimitedKey, -1);" property="up">
    <bean:message key="button.up"/>
  </html:button>
  <html:button onclick="javascript:swap(document.ClientDisplayOrderFormAdmin.commaDelimitedKey, 1);" property="down">
    <bean:message key="button.down"/>
  </html:button>
  <html:submit onclick="javascript:loadvalues(document.ClientDisplayOrderFormAdmin.commaDelimitedKey, document.ClientDisplayOrderFormAdmin.order)">
    <bean:message key="button.save"/>
  </html:submit>
<%--
  <html:cancel>
    <bean:message key="button.cancel"/>
  </html:cancel>
--%>
  <html:submit onclick="javascript:zeroiseDisplayOrder.value='true';loadvalues(document.ClientDisplayOrderFormAdmin.commaDelimitedKey, document.ClientDisplayOrderFormAdmin.order)">
    <bean:message key="button.zeroise"/>
  </html:submit>
  <html:hidden name="ClientDisplayOrderFormAdmin" property="order"/>
  <html:hidden name="ClientDisplayOrderFormAdmin" property="zeroiseDisplayOrder"/>
  <html:hidden name="ClientDisplayOrderFormAdmin" property="client.clientId"/>
</form>
</logic:present>

<script type="text/javascript">
<!--

var dividerText = '***';
var dividerValue = 0;

function swap(listbox, direction)
{
  var i = listbox.selectedIndex;

  if (i > -1)
  {
    // An item has been selected.
    var src = i;
    var tgt = i + direction;

    if (tgt > -1 &&
        tgt < listbox.length)
    {
      // We are NOT at the top moving up OR at the bottom moving down.
      if ((tgt == listbox.length - 1 &&
           listbox.options[src].value == dividerValue) ||
          (tgt == 0 &&
           listbox.options[src].value == dividerValue))
      {
        alert('no can do - cannot move a divider to the top or bottom');
      }
      else
      {
  // AND we are not moving a divider down to the bottom.

  srctext = listbox.options[src].text;
  srcvalue = listbox.options[src].value;
  tgttext = listbox.options[tgt].text;
  tgtvalue = listbox.options[tgt].value;

        if (srcvalue < 0 && tgtvalue < 0)
  {
          alert('no can do - cannot swap a menu type option with another.');
  }
  else
  {
      // AND we are not moving an option with a negative value with another option with a negative value.

    if ((srcvalue < 0 && src == 0) ||
        (tgtvalue < 0 && tgt == 0))
    {
      alert('no can do - cannot move menu type from top.');
    }
    else
    {
      // AND we are not moving an option with a negative value with another option with a negative value.

    //
    // KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD KRD
    //
    // Still not bullet proof !!!
    //

    if ((srcvalue == dividerValue &&
         listbox.options[tgt - (direction * -1)].value < 0) ||
            (tgtvalue == dividerValue &&
         listbox.options[src - (direction * -1)].value < 0))
    {
        alert('no can do - cannot move a divider next to a menu type option.');
      }
      else
      {
      // AND we are not moving a divider type option next to a menu type option.

      listbox.options[src].text = tgttext;
      listbox.options[src].value = tgtvalue;
      listbox.options[tgt].text = srctext;
      listbox.options[tgt].value = srcvalue;

      listbox.selectedIndex = tgt;
    }
    }
  }
      }
    }
    else
    {
      alert('no can do - cannot be at the top moving up OR at the bottom moving down.');
    }
  }
  else
  {
    alert('nothing selected to move !!!');
  }
}

function loadvalues(listbox, valueField)
{
  var values = '';

  for (var i = 0; i < listbox.length; i++)
  {
    if (values != '')
    {
      values = values + '|';
    }
    values = values + listbox.options[i].value;

  }

  valueField.value = values;

//  alert(values);
}

function showIt() {
  alert(document.ClientDisplayOrderFormAdmin.order.value);
}

//-->
</script>

