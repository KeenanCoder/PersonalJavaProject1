const display = document.getElementsByName("display");

function appendToDisplay(value){
	display.value += value;
}

function clearDisplay(){
	display.value += value;
}

function calculated(){
	try{
		display.value = eval(display.value);
	} catch(error){
		display.value = "Error";
	}
}