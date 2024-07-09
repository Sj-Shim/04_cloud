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
						<h3 class="title">관리자 추가</h3>
					</div>
					<!--// main_title -->

					<!-- tabel_search_area -->
					<div class="table_search_area">
						<h4 class="title float_left" style="vertical-align: center; margin-top: 10px">총 <%= request.getAttribute("totalCnt") %> 명</h4>
						<div class="button_area">
							<div class="float_right">
								<input type="text" placeholder="검색어(이름)" id="usr_nm" name="usrnm" style="margin-right:10px"/>
								<button class="btn save" title="조회" id="btn_search" name="btn_search">
									<span>조회</span>
								</button>
							</div>
						</div>
					</div>

					<!-- title_area -->
					
					<div class="title_area" hidden>
					</div>
					
					<!--// title_area -->
					
					
					<!-- table 1dan list -->
					<div class="table_area">
						<div class="area_xscroll">
							<table class="list fixed">
								<caption>리스트 화면</caption>
								<colgroup>
									<col style="width: 7%;" /> <!-- 순번 -->
									<col style="width: 21%;" /><!-- 사원번호 -->
									<col style="width: 21%;" /><!-- 이름 -->
									<col style="width: 17%;" /><!-- 부서명 -->
									<col style="width: 17%;" /><!-- 담당업무 -->
									<col style="width: 17%;" /><!-- 직급 -->
								</colgroup>
								<thead>
								<tr>
									<th>순번</th>
									<th>사원번호</th>
									<th>이름</th>
									<th>부서명</th>
									<th>담당업무</th>
									<th>직급</th>
								</tr>
								</thead>
								
								<tbody id="result">
									<c:forEach var="staff" items="${staffList}" varStatus="status">
										<tr style="cursor:pointer;" data-id="${staff.empno}" onclick="getStaffInfo(this)">
											<td>${status.end - status.index + 2}</td>
											<td>${staff.empno}</td>
											<td>${staff.name}</td>
											<td>${staff.dept_name}</td>
											<td>${staff.bsnm}</td>
											<td>${staff.pzcnm}</td>
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
    			<div class="modal-content table_area" style="margin-top:15%">
    				<div class="modal-header">
	                    <h5 class="modal-title" id="usrMngModalLabel">관리자 추가</h5>
	                </div>
	                <div class="modal-body">
						<table id="subordinate-list" class="list">
							<colgroup>
								<col style="width: 23%;" /><!-- 사원번호 -->
								<col style="width: 23%;" /><!-- 이름 -->
								<col style="width: 18%;" /><!-- 부서명 -->
								<col style="width: 20%;" /><!-- 직급 -->
								<col style="width: 16%;" /><!-- 담당업무 -->
							</colgroup>
							<thead>
								<tr>
									<th>사원번호</th>
									<th>이름</th>
									<th>부서명</th>
									<th>담당업무</th>
									<th>직급</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="hideUsrMngModal()">취소</button>
	                    <button type="button" class="btn btn-primary" id="btn-delMng">관리자 추가</button>
	                </div>
		        </div>
	        </div>
        </div>
        <!-- //user managing modal --> 
	</div>
</body>
<script>
	function getStaffInfo(e){
		let dataId = e.dataset.id;
		console.log(dataId);
		let staffInfo = document.querySelectorAll('tr[data-id=' + dataId + '] td');
		console.log("staffInfo : " , staffInfo);
		let empno = staffInfo[1].innerText;
		let name = staffInfo[2].innerText;
		let dept_name = staffInfo[3].innerText;
		let bsnm = staffInfo[4].innerText;
		let pzcnm = staffInfo[5].innerText;
		
		let modalInfo = document.querySelectorAll('table#subordinate-list tbody tr td');
		modalInfo[0].innerText = empno;
		modalInfo[1].innerText = name;
		modalInfo[2].innerText = dept_name;
		modalInfo[3].innerText = bsnm;
		modalInfo[4].innerText = pzcnm;
		
		$("#usrMngModal").modal("show");
		
	}
	$(document).ready(function() {
		$("#btn-addMng").on('click', function(e){
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
		//let token = $("meta[name='_csrf']").attr("content");
		//let header = $("meta[name='_csrf_header']").attr("content");
		
		//$(document).ajaxSend(function(e, xhr, options) {
	    //    xhr.setRequestHeader(header, token);
	    //});
		
		$('#btn_search').click(function() {
			event.preventDefault();
			console.log("click");
			fn_search_submit();
		});
	
	});
						
	function fn_search_submit() {
		if(!searchValidationCheck(frm)){
			window.location.reload();
			return false;
		}
		frm.action = window.location.pathname; 
		frm.method = 'get';
		frm.submit();
	}
</script>
</html>

	
	
