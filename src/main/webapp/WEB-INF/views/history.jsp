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
						<h3 class="title">전송 이력 조회</h3>
					</div>
					<!--// main_title -->

					<!-- tabel_search_area -->
					<div class="table_search_area">
						<div class="float_left">
							기간:<input type="date" id="txt_inq_st_dt" name="s" value="${st_dt != null or st_dt != '' ? st_dt : '' }">
							~
							<input type="date" id="txt_inq_ed_dt" name="e" value="${ed_dt != null or ed_dt != '' ? ed_dt : '' }">
							등록자:<input type="text" id="txt_reg_usr_id" name="id" value="${not empty usr_id ? usr_id : '' }">
							처리결과:
							<select id="txt_cntn" name="h">
								<option value='' <c:if test="${empty hst_dsc}">selected</c:if> > 선택</option>
								<option value="0" <c:if test="${hst_dsc == '0'}">selected</c:if> >성공</option>
								<option value="1" <c:if test="${hst_dsc == '1'}">selected</c:if> >오류</option>
							</select>	
							파일명:<input type="text" id="f_file_nm" name="n" value="${file_name != null or file_name != '' ? file_name : '' }">
						</div>
						<div class="button_area">
						<div class="float_right">
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
									<col style="width: 5%;" />
									<col style="width: 50%;" />
									<col style="width: 10%;" />
									<col style="width: 15%;" />
									<col style="width: 5%;" />
									<col style="width: 15%;" />
								</colgroup>
								<thead>
								<tr>
									<th scope="col" rowspan="2">순번</th>
									<th scope="col" rowspan="2">파일명</th>
									<th scope="col" rowspan="2">등록자</th>
									<th scope="col" rowspan="2">등록위치</th>
									<th scope="col" rowspan="2">처리결과</th>
									<th scope="col" rowspan="2">처리일시</th>
								</tr>
								</thead>
								
								<tbody id="result">
									<c:forEach var="history" items="${histories}">
										<tr>
											<td><fmt:parseNumber var="row" value="${history.rn}" integerOnly="true" />${row}</td>
											<td class="file" data-key="${history.fl_key}">${not empty history.otxt_flnm ? history.otxt_flnm : history.flnm}</td>
											<td>${history.empe_name}</td>
											<td>${history.pgmnm}</td>
											<td>${history.hst_dsc == '0' ? '성공' : '오류'}</td>
											<td>${history.rg_dtm}</td>
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
		<!-- file info modal -->
		<%@ include file="/WEB-INF/views/fileInfoModal.jsp" %>
    	<!-- //file info modal -->
	</div>
</body>
</html>
