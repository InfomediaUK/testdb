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

function singleSubmit()
{
  formSubmitCount++;
  return (formSubmitCount == 1);
}