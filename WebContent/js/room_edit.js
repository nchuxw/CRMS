var width = 1;
var height = 1;

//alert("room_edit.js");

$('#width').bind('keydown',function(event){
    if(event.keyCode == "13") {
        changeSize();
    }
});

$('#height').bind('keydown',function(event){
    if(event.keyCode == "13") {
        changeSize();
    }
});

function changeSize() {
	var w = parseInt(document.getElementById("width").value);
	var h = parseInt(document.getElementById("height").value);
	
	if(w <= 0 || h <= 0){
		return ;
	}
	
	var tab = document.getElementById("layout");
	if(w > width){
		for(var i = 0; i < height; i++){
			for(var j = width; j < w; j++){
				var x = tab.rows[i].insertCell();
				x.style.width=50+"px";
			}
		}
		width = w;
	}
	if(h > height){
		for(var i = height; i < h; i++){
			tab.insertRow();
			for(var j = 0; j < w; j++){
				var x = tab.rows[i].insertCell();
				x.style.height=50+"px";
			}
		}
		height = h;
	}
	if(h < height){
		for(var i = height - 1; i > h - 1; i--){
			tab.deleteRow();
		}
		height = h;
	}
	if(w < width){
		for(var i = 0; i < height; i++){
			for(var j = width; j > w; j--){
				tab.rows[i].deleteCell();
			}
		}
		width = w;
	}
}

function allowDrop(ev)
{
	ev.preventDefault();
}

var dragid;
var count=0;

$("#layout").on('dragstart', 'img', function (ev) {
	//ev.dataTransfer.setData("Text",ev.target.id);
	dragid=ev.target.id;
})

function drag(ev) {
	//ev.dataTransfer.setData("Text",ev.target.id);
	dragid=ev.target.id;
}

function drop(ev)
{
	ev.preventDefault();
	//var data=ev.dataTransfer.getData("Text");
	var data = dragid;
	if(data!=null && data == "com"){
		var img = document.createElement("img");
		img.id="com" + count;
		count++;
		img.src="images/computer_usable.png";
		img.draggable="true";
		img.width="40";
		img.height="40";
		ev.target.appendChild(img);
	}
	else{
		ev.target.appendChild(document.getElementById(data));
	}
}

function delet_com(ev)
{
	ev.preventDefault();
	//var data=ev.dataTransfer.getData("Text");
	var data = dragid;
	if(data != "com"){
		var delet_block = document.getElementById("delet_block");
		var node = document.getElementById(data);
		ev.target.appendChild(node);
		delet_block.removeChild(node);
	}
}


function submitt(){
	var frm = document.getElementById("frm");
	var tab = document.getElementById("layout");
	var count = 0;
	for (var i = 0; i < tab.rows.length; i++) {
        for (var j = 0; j < tab.rows[i].cells.length; j++) {
        	if(tab.rows[i].cells[j].innerHTML != ""){
        		var comNoIn = document.createElement("input");
        		var layoutXIn = document.createElement("input");
        		var layoutYIn = document.createElement("input");
        		comNoIn.name="com_no";
        		layoutXIn.name="layoutX"
            	layoutYIn.name="layoutY"
        		comNoIn.type="hidden";
        		layoutXIn.type="hidden";
        		layoutYIn.type="hidden";
        		comNoIn.value=count;
        		layoutXIn.value=j;
        		layoutYIn.value=i;
        		frm.appendChild(comNoIn);
        		frm.appendChild(layoutXIn);
        		frm.appendChild(layoutYIn);
        		count++;
        	}
        }
    }
	frm.com_num.value=count;
}
