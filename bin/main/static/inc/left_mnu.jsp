<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  String uri = request.getRequestURI();
  String filename = uri.substring(uri.lastIndexOf("/") + 1);

  String menu01 = "";
  String menu02 = "";
  String menu03 = "";
  String menu04 = "";
  String menu05 = "";
  String menu06 = "";
  String menu07 = "";
  String menu08 = "";
  String menu09 = "";

  switch (filename) {
  	
  	case "menu.jsp":
  	case "mnu_menu.jsp":
  	case "index.jsp":
%>



			<% //if (menu01 != null && menu02 != null) { %>
				<% //if (menu01.equals("Y") || menu02.equals("Y")) { %>
					<div class="lnb_area">
						<ul class="lnb">
								<li id="head_left_mnu_menu_mng" class="on">
								<a href="#">
									<span>자료 전송 관리</span>
								</a>
								<ul class="lnb_submenu">
									<% //if (menu01.equals("Y")) { %>
										<li id="left_menu" class="on">
											<a href="javascript:page_move('menu')">
												<span>자료전송 배치파일 목록</span>
											</a>
										</li>
									<% //} %>
								

								
									<% if (menu02.equals("Y")) { %>
										<li id="left_mnu_tag">
											<a href="javascript:page_move('mnu_tag')">
												<span>프로그램/Tag 관리</span>
											</a>
										</li>
									<% } %>
								</ul>
							</li>
						</ul>
					</div>
				<% //} %>
			<% //} %>
<%
		break;
  	case "kaf_db_con_list.jsp":
  	case "kaf_db_con_insert.jsp":
  	case "pyb_cdhd_attr.jsp":
  	case "dds_bt_chbs.jsp":
  	case "dds_prd_crbs.jsp":
  	case "dds_cns_mrbs.jsp":
%>
			<% if (menu04 != null && menu05 != null && menu06 != null && menu07 != null && menu08 != null) { %>
				<% if (menu04.equals("Y") || menu05.equals("Y") || menu06.equals("Y") || menu07.equals("Y") || menu08.equals("Y")) { %>

					<div class="lnb_area">
						<ul class="lnb">
						
							<% if (menu04.equals("Y")) { %>
								<li id="head_left_pyb_cdhd_attr" class="on">
									<a href="#">
										<span>프로파일 관리</span>
									</a>
										<ul class="lnb_submenu">
											<li id="left_pyb_cdhd_attr" class="on">
												<a href="javascript:page_move('pyb_cdhd_attr')">
													<span>프로파일 관리</span>
												</a>
											</li>
										</ul>
								</li>
							<% } %>
						
							<% if (menu05.equals("Y")) { %>
								<li id="head_left_dds_bt_chbs" class="on">
									<a href="#">
										<span>배치 프로그램 관리</span>
									</a>
									<ul class="lnb_submenu">
										<li id="left_dds_bt_chbs" class="on">
											<a href="javascript:page_move('dds_bt_chbs')">
												<span>배치 프로그램 관리</span>
											</a>
										</li>
									</ul>
								</li>
							<% } %>
						
							<% if (menu06.equals("Y") || menu07.equals("Y") || menu08.equals("Y")) { %>
								<li id="head_left_mnu_kafka_mng" class="on">
									<a href="#">
										<span>Kafka 관리</span>
									</a>
									<ul class="lnb_submenu">
										<% if (menu06.equals("Y")) { %>
											<li id="left_kaf_db_con_list">
												<a href="javascript:page_move('kaf_db_con_list')">
													<span>Kafka DB 관리</span>
												</a>
											</li>
										<% } %>
										
										<% if (menu07.equals("Y")) { %>
											<li id="left_dds_prd_crbs">
												<a href="javascript:page_move('dds_prd_crbs')">
													<span>Kafka Producer 관리</span>
												</a>
											</li>
										<% } %>
										
										<% if (menu08.equals("Y")) { %>
											<li id="left_dds_cns_mrbs">
												<a href="javascript:page_move('dds_cns_mrbs')">
													<span>Kafka Consumer 관리</span>
												</a>
											</li>
										<% } %>
											
									</ul>
								</li>
							<% } %>
						</ul>
					</div>
				<% } %>
			<% } %>	

<%
		break;
  }
%>