<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- header -->
<div id="header">
		<header>
			<h1 class="home_logo" >
			<img width="180" height="35" src="${pageContext.request.contextPath}/static/images/common/logo.png" alt="" />
			</h1>
			<!-- gnb_menu -->
			<div id="nav">
			<nav>
				<ul class="menu">
					<li id="menu_file">
						<a href="javascript:page_move('files');" title="파일 관리">
							<span>파일 관리</span>
						</a>
					</li>
					<li id="menu_trans_hist">
						<a href="javascript:page_move('trans_hist');" title="전송 이력 조회">
							<span>전송 이력 조회</span>
						</a>
					</li>
					<c:if test="true">
					<li id="menu_user_mng">
						<a href="javascript:page_move('user_mng');" title="사용자 관리">
							<span>사용자 관리</span>
						</a>
					</li>
					</c:if>
				</ul>
			</nav>
			</div>
			<!-- hsection -->
			<div class="hsection_area">
				<a href="<c:url value='/logout' />" class="btn logout" title="로그아웃 하기" onclick="event.preventDefault();logout();">
					<span>로그아웃</span>
				</a>
			</div>
			<!-- //hsection -->
		</header>
		</div>
<!-- //header -->
