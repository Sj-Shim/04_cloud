<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav aria-label="Page navigation">
    <ul class="pagination paging_area justify-content-center">
        <li class="page-item" <c:if test="${currentPage == 1}">hidden</c:if>>
            <a class="page-link stimg" href="javascript:void(0)" aria-label="First" title="맨처음 페이지로 이동" onclick="changeUrlSearchPage(1)">
                <img src="${pageContext.request.contextPath}/static/images/common/btn_paging_first_off.png" alt="맨처음 페이지로 이동" />
            </a>
        </li>
        <li class="page-item" <c:if test="${currentPage == 1}">hidden</c:if>>
            <a class="page-link stimg" href="javascript:void(0)" aria-label="Previous" title="전 페이지로 이동" onclick="changeUrlSearchPage(${currentPage - 1})">
                <img src="${pageContext.request.contextPath}/static/images/common/btn_paging_prev_off.png" alt="전 페이지로 이동" />
            </a>
        </li>
        <c:forEach var="i" begin="${startPage}" end="${endPage}">
            <c:if test="${i <= totalPages}">
                <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                    <a class="page-link" href="javascript:void(0)" onclick="changeUrlSearchPage(${i})">${i}</a>
                </li>
            </c:if>
        </c:forEach>
        <li class="page-item" <c:if test='${currentPage == totalPages}'>hidden</c:if>>
            <a class="page-link stimg" href="javascript:void(0)" aria-label="Next" title="다음 페이지로 이동" onclick="changeUrlSearchPage(${currentPage + 1})">
                <img src="${pageContext.request.contextPath}/static/images/common/btn_paging_next_on.png" alt="다음 페이지로 이동" />
            </a>
        </li>
        <li class="page-item" <c:if test='${currentPage == totalPages}'>hidden</c:if>>
            <a class="page-link stimg" href="javascript:void(0)" aria-label="Last" title="맨마지막 페이지로 이동" onclick="changeUrlSearchPage(${totalPages})">
                <img src="${pageContext.request.contextPath}/static/images/common/btn_paging_last_on.png" alt="맨마지막 페이지로 이동" />
            </a>
        </li>
    </ul>
</nav>
<!--   <nav aria-label="Page navigation">
	<ul class="pagination paging_area justify-content-center">
		<li class="page-item" <c:if test='${currentPage == 1}'>hidden</c:if>>
                  <a class="page-link stimg" href="javascript:void(0)" aria-label="First" title="맨처음 페이지로 이동" onclick="changeUrlSearchPage(1)">
                      <img src="static/images/common/btn_paging_first_off.png" alt="맨처음 페이지로 이동" />
                  </a>
              </li>
              <li class="page-item" <c:if test='${currentPage == 1}'>hidden</c:if>>
                  <a class="page-link stimg" href="javascript:void(0)" aria-label="Previous" title="전 페이지로 이동" onclick="changeUrlSearchPage(${currentPage - 1})">
                      <img src="static/images/common/btn_paging_prev_off.png" alt="전 페이지로 이동" />
                  </a>
              </li>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
                  <c:if test="${i <= totalPages}">
                      <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                          <a class="page-link" href="javascript:void(0)" onclick="changeUrlSearchPage(${i})">${i}</a>
                      </li>
                  </c:if>
              </c:forEach>
		<li class="page-item" <c:if test='${currentPage == totalPages}'>hidden</c:if>>
                  <a class="page-link stimg" href="javascript:void(0)" aria-label="Next" title="다음 페이지로 이동" onclick="changeUrlSearchPage(${currentPage + 1})">
                      <img src="static/images/common/btn_paging_next_on.png" alt="다음 페이지로 이동" />
                  </a>
              </li>
              <li class="page-item" <c:if test='${currentPage == totalPages}'>hidden</c:if>>
                  <a class="page-link stimg" href="javascript:void(0)" aria-label="Last" title="맨마지막 페이지로 이동" onclick="changeUrlSearchPage(${totalPages})">
                      <img src="static/images/common/btn_paging_last_on.png" alt="맨마지막 페이지로 이동" />
                  </a>
              </li>
	</ul>
</nav> -->