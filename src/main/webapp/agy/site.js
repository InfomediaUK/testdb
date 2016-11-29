function toggle(id) {
	if (document.layers)
	{
		current = (document.layers[id].display == 'none') ? 'block' : 'none';
		document.layers[id].display = current;
	}
	else if (document.all)
	{
		current = (document.all[id].style.display == 'none') ? 'block' : 'none';
		document.all[id].style.display = current;
	}
	else if (document.getElementById)
	{
		vista = (document.getElementById(id).style.display == 'none') ? 'block' : 'none';
		document.getElementById(id).style.display = vista;
	}
}
function showIt(id) {
	if (document.layers)
	{
		document.layers[id].display = 'block';
	}
	else if (document.all)
	{
 		document.all[id].style.display = 'block';
	}
	else if (document.getElementById)
	{
	  document.getElementById(id).style.display = 'block';
	}
}
function hideIt(id) {
	if (document.layers)
	{
 		document.layers[id].display = 'none';
	}
	else if (document.all)
	{
 		document.all[id].style.display = 'none';
	}
	else if (document.getElementById)
	{
	  document.getElementById(id).style.display = 'none';
	}
}

function getItsValue(id) {
	if (document.layers)
	{
		return document.layers[id].value;
	}
	else if (document.all)
	{
 		return document.all[id].value;
	}
	else if (document.getElementById)
	{
	  return document.getElementById(id).value;
	}
}

function setItsValue(id, value) {
	if (document.layers)
	{
		document.layers[id].value = value;
	}
	else if (document.all)
	{
 		document.all[id].value = value;
	}
	else if (document.getElementById)
	{
	  document.getElementById(id).value = value;
	}
}

function setItsInnerHTML(id, value) {
	if (document.layers)
	{
		document.layers[id].innerHTML = value;
	}
	else if (document.all)
	{
 		document.all[id].innerHTML = value;
	}
	else if (document.getElementById)
	{
	  document.getElementById(id).innerHTML = value;
	}
}

function singleSubmit()
{
  formSubmitCount++;
  return (formSubmitCount == 1);
}

function textCounter(field, remLenLabel, maxlimit) {
  fieldValue = getItsValue(field);
  if (fieldValue.length > maxlimit) { // if too long...trim it!
    setItsValue(field, fieldValue.substring(0, maxlimit));
  }
  else { // otherwise, update 'characters left' counter
    setItsInnerHTML(remLenLabel, maxlimit - fieldValue.length);
  }
}
