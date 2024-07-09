<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
	.agr_form {
		margin: 5px auto;
		width : 95%;
	}
	.agr_form > div{
		margin-top : 5px;
		border: 1px solid #000;
	}
	.agr_form h1 {
		text-align: center;
	}
	.agr_form >div::after {
		content: "";
		display: table;
		clear: both;
	}
	.check_box {
		margin-right: 50px;
		float: right;
	}
	button {
		margin-top:15px !important;
		width:100%;
	}
</style>
<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
<script type="text/javascript" src="../static/js/jquery-3.6.1.js"></script>
<script type="text/javascript" src="../static/js/jsrender.js"></script>
<body>
<div id="wrap">
	<form action="/" method="post" class="agr_form" id="frm">
		<div id="agr_col_personal_info">
			<h1>개인정보 수집·이용 동의서</h1>
			<div class="check_box">
				<p>(
					<label for="disagree_col">
						<span>부동의</span>
					</label>
					<input type="checkbox" id="disagree_col" name="agree_col" value="0"/>
					<label for="agree_col">
						<span>동의</span>
					</label>
					<input type="checkbox" id="agree_col" name="agree_col" value="1"/>
				)</p>
			</div>
		</div>
		<c:if test="${blg_dsc != '1'}">
			<div id="agr_pass_personal_info">
				<h1>개인정보 제 3자 제공 동의서</h1>
				<div class="check_box">
					<p>(
						<label for="disagree_pass">
							<span>부동의</span>
						</label>
						<input type="checkbox" id="disagree_pass" name="agree_pass" value="0"/>
						<label for="agree_pass">
							<span>동의</span>
						</label>
						<input type="checkbox" id="agree_pass" name="agree_pass" value="1"/>
					)</p>
				</div>	
			</div>
		</c:if>
		<button type="submit" style="display:block;" class="btn btn-primary" id="btn_submit">제출</button>
	</form>
</div>
<script type="text/javascript">
	const username = "${username}";
	function control_checkSync(box1, box2){
		if(box1.checked){
			box2.checked = false;
		}
	}
	document.addEventListener("DOMContentLoaded" , (event) =>{
		const col_chk1 = document.getElementById("disagree_col");
		const col_chk2 = document.getElementById("agree_col");
		
		col_chk1.addEventListener("change", ()=> control_checkSync(col_chk1, col_chk2));
		col_chk2.addEventListener("change", ()=> control_checkSync(col_chk2, col_chk1));
		
		const pass_chk1 = document.getElementById("disagree_pass");
		const pass_chk2 = document.getElementById("agree_pass");
		
		pass_chk1.addEventListener("change", ()=> control_checkSync(pass_chk1, pass_chk2));
		pass_chk2.addEventListener("change", ()=> control_checkSync(pass_chk2, pass_chk1)); 
	});
	$(document).ready(function(){
		
		$('#btn_submit').click(function() {
			event.preventDefault();
			const col_chk1 = document.getElementById("disagree_col");
			const col_chk2 = document.getElementById("agree_col");
			const pass_chk1 = document.getElementById("disagree_pass");
			const pass_chk2 = document.getElementById("agree_pass");
			
			if(!col_chk1.checked && !col_chk2.checked){
				alert("개인정보수집이용동의서 동의여부에 체크하지 않으셨습니다.");
				return;
			}
			if(!pass_chk1.checked && !pass_chk2.checked){
				alert("개인정보 제3자제공 동의서 동의여부에 체크하지 않으셨습니다.");
				return;
			}
			const frm = document.querySelector("#frm");
			
			let userInput = document.createElement("input");
			userInput.type = "text";
			userInput.name = "username";
			userInput.value = username;
			frm.appendChild(userInput);
			
			frm.action = window.location.pathname;
			frm.method = "post";
			frm.submit();
		});
	
	});
	
</script>
</body>
</html>