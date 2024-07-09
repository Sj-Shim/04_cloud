<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<div id="header">
		<header>
			<h1 class="home_logo" >
			<img width="180" height="35" src="images/common/logo.png" alt="" />
			</h1>
			<!-- gnb_menu -->
			<div id="nav">
			<nav>
				<ul class="menu">

				    <%
				    
			    	String menu01 = "";
			    	String menu02 = "";
			    	String menu03 = "";
			    	String menu04 = "";
			    	String menu05 = "";
			    	String menu06 = "";
			    	String menu07 = "";
			    	String menu08 = "";
			    	String menu09 = "";
				    
					%>		
					<% //if (menu01 != null && menu02 != null) { %>
						<% //if (menu01.equals("Y") || menu02.equals("Y")) { %>
							<li id="menu_scn">
								<a href="javascript:page_move('menu');" title="자료전송 배치 파일관리로 이동">
									<span>자료전송 배치 파일관리</span>
								</a>
							</li>
						<% //} %>
					<% //} %>
					
					<% if (menu03 != null ) { %>
						<% if (menu03.equals("Y")) { %>
							<li id="menu_tg_cust">
								<a href="javascript:page_move('index');" title="Target 고객 관리 페이지로 이동">
									<span>Target 고객 조회</span>
								</a>
							</li>
						<% } %>
					<% } %>
					
					<% if (menu04 != null && menu05 != null && menu06 != null && menu07 != null && menu08 != null) { %>
						<% if (menu04.equals("Y") || menu05.equals("Y") || menu06.equals("Y") || menu07.equals("Y") || menu08.equals("Y")) { %>
							<li id="menu_kafka">
								<a href="javascript:page_move('pyb_cdhd_attr');" title="Kafka SQL 관리 페이지로 이동">
									<span>프로파일 관리</span>
								</a>
							</li>
						<% } %>
					<% } %>							
						
					<% if (menu09 != null ) { %>
						<% if (menu09.equals("Y")) { %>	
							<li id="menu_user_auth">
								<a href="javascript:page_move('user_auth');" title="권한 관리 페이지로 이동">
									<span>사용자 권한 관리</span>
								</a>
							</li>
						<% } %>
					<% } %>	
				</ul>
			</nav>
			</div>
			<!-- hsection -->
			<div class="hsection_area">
				<a href="javascript:logout();" class="btn logout" title="로그아웃 하기">
					<span>로그아웃</span>
				</a>
			</div>
			<!-- //hsection -->
		</header>
		</div>
		
		
		<script>
		function logout() {
		    window.location.href = "/logout.jsp";
		}
		</script>
		
		