<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/partials/head.jsp" %>
<body onload="javascript:body_load();">
	<div id="wrap">
		<!-- skip_nav --> 
		<div id="skip_nav">  
		</div>
		<!-- //skip --> 
		
		<!-- header -->
		<%@ include file="/WEB-INF/partials/header.jsp" %>
		<!-- //header -->
		
		<!-- container -->
		<div id="container">
		<article>
			<!-- left_area -->
			<%@ include file="/WEB-INF/partials/leftarea.jsp" %>
			<!--// left_area -->	
			<!--// content_area -->
			<form id="frm" name="frm" method="post" action="/">
			<div class="content_area">
				<div id="content">
					<!-- main_title -->
					<div class="main_title">
						<h3 class="title">관리자 목록</h3>
					</div>
					<!--// main_title -->

					<!-- tabel_search_area -->
					<div class="table_search_area">
						<div class="button_area">
							<h4 class="title float_left" style="vertical-align: center; margin-top: 10px">총 <%= request.getAttribute("totalCnt") %> 명</h4>
							<div class="float_right">
								<input type="text" placeholder="검색어(이름)" id="usr_nm" name="usrnm" style="margin-right:10px"/>
								<button class="btn save" title="조회" id="btn_search" name="btn_search">
									<span>조회</span>
								</button>
							</div>
						</div>
					</div>

					<!-- title_area -->
					
					<div class="title_area">
						<button type="button" class="btn save float_right" onclick='callSubList()'>
							<span>관리자 등록</span>
						</button>
					</div>
					
					<!--// title_area -->
					
					
					<!-- table 1dan list -->
					<div class="table_area">
						<div class="area_xscroll">
							<table class="list fixed">
								<caption>리스트 화면</caption>
								<colgroup>
									<col style="width: 5%;" />
									<col style="width: 35%;" />
									<col style="width: 35%;" />
									<col style="width: 25%;" />
								</colgroup>
								<thead>
								<tr>
									<th scope="col" rowspan="2">순번</th>
									<th scope="col" rowspan="2">사원번호</th>
									<th scope="col" rowspan="2">이름</th>
									<th scope="col" rowspan="2">등록일시</th>
								</tr>
								</thead>
								
								<tbody id="result">
									<c:forEach var="manager" items="${managerList}">
										<tr>
											<td><fmt:parseNumber var="row" value="${manager.rn}" integerOnly="true" />${row}</td>
											<td>${manager.empe_no}</td>
											<td>${manager.name}</td>
											<td>${manager.crt_dtm}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!--// table 1dan list -->
					<!-- paging_area -->
					<%@ include file="/WEB-INF/partials/paging.jsp" %>
					<!-- //paging_area -->
					<hr />
				</div>
			</div>
			<!--// content_area -->
			</form>	
		</article>	
		</div>
		
		<!-- //container -->
		<!-- footer --> 
		<%@ include file="/WEB-INF/partials/footer.jsp" %>
		<!-- //footer -->
        <!-- user managing modal -->
        <div class="modal fade" id="usrMngModal" tabindex="-1" role="dialog" aria-labelledby="usrMngModalLabel" aria-hidden="true">
    		<div class="modal-dialog" role="document">
    			<div class="modal-content" style="margin-top:15%">
    				<div class="modal-header">
	                    <h5 class="modal-title" id="usrMngModalLabel">사원 관리</h5>
	                </div>
	                <div class="modal-body">
						<table id="subordinate-list">
							<colgroup>
								<col style="width: 10%;" /> <!-- 순번 -->
								<col style="width: 20%;" /><!-- 사원번호 -->
								<col style="width: 20%;" /><!-- 이름 -->
								<col style="width: 15%;" /><!-- 부서명 -->
								<col style="width: 15%;" /><!-- 직급 -->
								<col style="width: 15%;" /><!-- 담당업무 -->
							</colgroup>
							<thead>
								<tr>
									<th>순번</th>
									<th>사원번호</th>
									<th>이름</th>
									<th>부서명</th>
									<th>직급</th>
									<th>담당업무</th>
								</tr>
							</thead>
						</table>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="hideUsrMngModal()">취소</button>
	                    <button type="button" class="btn btn-primary" id="btn-delMng">관리자 삭제</button>
	                </div>
		        </div>
	        </div>
        </div>
        <!-- //user managing modal --> 
	</div>
</body>
</html>
