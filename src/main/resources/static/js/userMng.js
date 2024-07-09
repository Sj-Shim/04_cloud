function callSubList(){
	window.location.href = "/user_mng/addmanager";
}
function callUsrMngModal(){
	$("#usrMngModal").modal("show");
}
function hideUsrMngModal(){
	$("#usrMngModal").modal("hide");
}
$(document).ready(function() {
	$("#btn-delMng").on('click', function(e){
		e.preventDefault();
		const targetUser = 'test12345';
		const param = {
				"empe_no" : targetUser,
		}
		
		$.ajax({
			type: "POST",
			url: "/api/user_mng/add",
			contentType: "application/json",
			data: JSON.stringify(param),
			success: function(response){
				console.log("success add manager");
			},
			error: function(err){
				console.log(err);
			}
		})
	}) 
});