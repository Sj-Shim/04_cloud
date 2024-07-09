<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
		<title>Signin &gt; NH 로우코드 포탈 (내부망)</title>
		<link rel="stylesheet" type="text/css" href="static/css/login.css" />
		
		<script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="static/js/move.js"></script>
		<script type="text/javascript" src="static/js/callService.js"></script>
		
	</head>

	<script>

		function checkEnterKey(event) {
		    if (event.keyCode === 13) {  // 13은 Enter 키의 keyCode입니다.
		    	callService();
		    }
		}
		
		function callService() {
		
			if ($("#loginId").val().trim() == "") {
				alert("ID를 입력하세요.");
				$("#loginId").focus();
				return;
			}
			
			if ($("#loginPw").val().trim() == "") {
				alert("비밀번호를 입력하세요.");
				$("#loginPw").focus();
				return;
			}	         
			
		}
	
		function makeAjaxCallSuccessCallback() {
			
			
			location.href = '/'; //main page
		}
		 
		function makeAjaxCallFailCallback() {
			alert("Login이 처리되지 않았습니다.");
			$("#loginId").val("");
			$("#loginPw").val("");
		}
	
	</script>


	<body>
		<!-- wrap -->
		<div id="wrap">	
		<!-- container -->
			<div id="container">
				<article>
					<div id="content">
						<div id="article">
						<article>
		                	<div class="login_area">
				                <h1 class="logo">
							<img src="static/images/common/logo.png" alt="" />
			         	        </h1>
								<!-- <div class="login_form"> 
			                        <form id="frm" name="frm" action="" method="post">
			                        	<fieldset>
			                            	<legend>Login</legend>
			                                <label for="login_id">ID</label>
			                                <input type="text" name="loginId" id="loginId" class="login_id" placeholder="id" onkeydown="checkEnterKey(event)"/>
			                                <label for="login_pw">Password</label>
			                                <input type="password" name="loginPw" id="loginPw" class="login_pw" placeholder="password" onkeydown="checkEnterKey(event)"/>

												<a href="javascript:callService();" class="btn_login" title="login">
													<span>log in </span>
												</a>

			                            </fieldset>
			                        </form>
		                        </div>-->
		                        
								<div class="login_form"> 
								    <form id="frm" name="frm" action="/login" method="post">
								        <fieldset>
								        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								            <legend>Login</legend>
								            <label for="login_id">ID</label>
								            <input type="text" name="username" id="loginId" class="login_id" placeholder="id"/>
								            <label for="login_pw">Password</label>
								            <input type="password" name="password" id="loginPw" class="login_pw" placeholder="password"/>
								
								            <button type="submit" class="btn_login" title="login">
								                <span>log in</span>
								            </button>
											<input type="checkbox" name="admin" id="check_admin"/>
											<label for="check_admin" class="visible_label">
												<p>관리자로 로그인</p>
											</label>
								        </fieldset>
								    </form>
								</div>
		                        
		                        
							</div>
						</article>
						</div>
					</div>
				</article>
			</div>
			<!-- //container -->
			<!-- footer --> 
			<div id="footer">
			<footer>
				
			</footer>
			</div>
			<!-- //footer -->
		</div>
		<!-- //wrap -->
	</body>
	
</html>

