<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
					<%
				    	String userId = (String) session.getAttribute("userId");
				    	String userName = (String) session.getAttribute("userId");
				
						if (userName == null) { 
							out.println("<script>window.location.href = '/login.jsp';</script>");
						} else {
							if (userName.equals("")) { 
								out.println("<script>window.location.href = '/login.jsp';</script>");
							}
						}
					%>
					
					<!-- left_info_area -->
					<div class="left_info_area">
						<ul class="left_info">
							<li>
								<strong class="name"><%=userName%></strong>님 반갑습니다.
							</li>
						</ul>
					</div>