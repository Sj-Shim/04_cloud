//left_menu 'on' class controller
{
	let leftMenu = document.querySelectorAll("ul.lnb_submenu li");
	const PATHNAME = window.location.pathname;
	const PATHARR = PATHNAME.split('/');
	const ROUTECNT = PATHNAME.split('/').filter(Boolean).length;
	const SEPERATOR = ROUTECNT == 1 ? "left_" : "left".concat(ROUTECNT).concat("_");
	let menuId = "";
	leftMenu.forEach(menu => {
		menuId = menu.id.split(SEPERATOR)[1];
		if(PATHARR[ROUTECNT] == menuId){
			menu.classList.add("on");
		}
		else{
			menu.classList.remove("on");
		}
	});
}
