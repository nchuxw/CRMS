function showli(str){
	var sub_menu = document.getElementById(str);
	var dis_v = sub_menu.style.display;
	if(dis_v == "none")
		sub_menu.style.display = "block";
	else
		sub_menu.style.display = "none"; 
}

