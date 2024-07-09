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
						<h3 class="title">파일 등록 목록</h3>
					</div>
					<!--// main_title -->

					<!-- tabel_search_area -->
					<div class="table_search_area">
						<div class="float_left">
							기간:<input type="date" id="txt_inq_st_dt" name="s" value="${st_dt != null or st_dt != '' ? st_dt : '' }">
							~
							<input type="date" id="txt_inq_ed_dt" name="e" value="${ed_dt != null or ed_dt != '' ? ed_dt : '' }">
							등록자:<input type="text" id="txt_reg_usr_id" name="id" value="${not empty usr_id ? usr_id : '' }">
							확장자:
							<select id="file_ext" name="ext">
								<option value='' <c:if test="${empty ext}">selected</c:if> >선택</option>
								<c:forEach var="xcr" items="${xcrList}" >
									<option <c:if test="${ext == xcr}">selected</c:if> >${xcr}</option>
								</c:forEach>
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
					
					<div class="title_area">
						<h4 class="title float_left" style="vertical-align: center; margin-top: 10px">총 <%= request.getAttribute("totalCnt") %> 건</h4>
						<button type="button" class="btn save float_right" onclick='callUploadModal()'>
						  <span>파일 업로드</span>
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
									<col style="width: 50%;" />
									<col style="width: 10%;" />
									<col style="width: 10%;" />
									<col style="width: 20%;" />
								</colgroup>
								<thead>
								<tr>
									<th scope="col" rowspan="2">순번</th>
									<th scope="col" rowspan="2">파일명</th>
									<th scope="col" rowspan="2">등록자</th>
									<th scope="col" rowspan="2">반입/반출</th>
									<th scope="col" rowspan="2">등록일시</th>
								</tr>
								</thead>
								
								<tbody id="result">
									<c:forEach var="file" items="${files}">
										<tr>
											<td><fmt:parseNumber var="row" value="${file.rn}" integerOnly="true" />${row}</td>
											<td class="file" data-key="${file.fl_key}">${not empty file.otxt_flnm ? file.otxt_flnm : file.flnm}</td>
											<td>${file.empe_name }</td>
											<td><c:if test="${file.snr_dsc eq '1' }">반입</c:if><c:if test="${file.snr_dsc eq '2' }">반출</c:if></td>
											<td>${file.upload_dtm }</td>
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
    	<!-- file upload modal -->
    	<div class="modal fade" id="fileUploadModal" tabindex="-1" role="dialog" aria-labelledby="fileUploadModalLabel" aria-hidden="true">
    		<div class="modal-dialog" role="document">
    			<div class="modal-content" style="margin-top:15%">
    				<div class="modal-header">
	                    <h5 class="modal-title" id="fileUploadModalLabel">파일 업로드</h5>
	                </div>
	                <div class="modal-body">
	                	<div id="fileUpload" class="dragAndDropDiv">Drag & Drop Files Here or Browse Files</div>
			        	<input type="file" name="fileUpload" id="fileUpload" style="display:none;" multiple/>    
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="hideUploadModal()">닫기</button>
	                    <button type="button" class="btn btn-primary" id="btn-upload">업로드</button>
	                </div>
		        </div>
	        </div>
        </div>
        <!-- //file upload modal -->
	</div>
</body>
</html>
