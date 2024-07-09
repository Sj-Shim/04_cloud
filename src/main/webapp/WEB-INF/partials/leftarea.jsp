<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- left_area -->
<div class="left_area">
    <div class="lnb_area">
        <!-- left_info_area -->
        <div class="left_info_area">
            <ul class="left_info">
                <li>
                    <strong class="name">김농협</strong>님 반갑습니다.
                </li>
            </ul>
        </div>
        <!--// left_info_area -->
        <!-- lnb -->
        <div class="lnb_area">
            <ul class="lnb" style="padding-left: 0">
                <li id="head_left_mnu_file_mng" class="on">
                    <a href="#">
                        <c:if test="${urlPath eq 'files' }">
                            <span>파일 관리</span>
                        </c:if>
                        <c:if test="${urlPath eq 'hist' }">
                            <span>전송 이력 조회</span>
                        </c:if>
                        <c:if test="${urlPath eq 'mng' }">
                            <span>사용자 관리</span>
                        </c:if>
                    </a>
                    <ul class="lnb_submenu">
                        <c:if test="${urlPath eq 'files' }">
                            <li class="" id="left_files">
                                <a href="javascript:page_move('files')">
                                    <span>파일 관리</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${urlPath eq 'hist' }">
                            <li class="" id="left_trans_hist">
                                <a href="javascript:page_move('trans_hist')">
                                    <span>전송 이력 조회</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${urlPath eq 'mng' }">
                            <li class="" id="left_user_mng">
                                <a href="javascript:page_move('user_mng')">
                                    <span>관리자 목록</span>
                                </a>
                            </li>
                            <li class="" id="left2_addmanager">
                                <a href="javascript:page_move('user_mng/addmanager')">
                                    <span>관리자 추가</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </li>
            </ul>
        </div>
        <!--// lnb -->
    </div>
</div>
<!--// left_area -->