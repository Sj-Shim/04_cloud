function page_move(move_id) {
    switch(move_id) {
		case "index":
            window.location.href = "/";
            break;
        case "kaf_db_con_list":
            window.location.href = "/kaf_db_con_list";
            break;
        case "kaf_db_con_insert":
            window.location.href = "/kaf_db_con_insert";
            break;
        case "user_auth":
            window.location.href = "/user_auth";
            break;    
            
            
        default:
        	window.location.href = "/" + move_id;
        	break;
	}
}


function body_load() {
	fn_top_toggle();
}


function fn_top_toggle() {
	/* Top Toggle */
	$("[id^='left_']").removeClass("on").addClass("off");
	$("[id^='menu_']").removeClass("on").addClass("off");
	
	
	var pathNM = window.location.pathname;
	pathNM = pathNM.replace(/\//g, "");
	
	//debugger;
	
	switch(pathNM) {
		
		
		case "":
			//$("#menu_scn").removeClass("on").addClass("off");
			//$("#menu_tg_cust").removeClass("on").addClass("off");
			$("#menu_tg_cust").removeClass("off").addClass("on");
			break;
		case "menu":
			$("#left_menu").removeClass("off").addClass("on");
			$("#menu_scn").removeClass("off").addClass("on");
		case "":
			
			
			break;


		case "kaf_db_con_insert":
			//$("#menu_scn").removeClass("on").addClass("off");
			//$("#menu_tg_cust").removeClass("on").addClass("off");
			$("#menu_kafka").removeClass("off").addClass("on");
			break;
		case "user_auth":
			//$("#menu_scn").removeClass("on").addClass("off");
			//$("#menu_tg_cust").removeClass("on").addClass("off");
			$("#menu_user_auth").removeClass("off").addClass("on");
			break;	
		default:
			$("#left_"+pathNM).removeClass("off").addClass("on");
			
			if (pathNM.includes("mnu")) {
				$("#menu_scn").removeClass("off").addClass("on");
			} else {
				$("#menu_kafka").removeClass("off").addClass("on");
			}
			
			break;
		
	}
}
