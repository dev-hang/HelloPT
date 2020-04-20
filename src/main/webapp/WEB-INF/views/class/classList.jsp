<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>스트리밍 강의 리스트</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/classlist.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/class/content.css">
<script src="${pageContext.request.contextPath }/resources/js/live/icon.js"></script>
<style>

</style>
</head>
<body>
	<!-- 콘텐츠 시작 { -->
	<div id="wrapper">
		<div class="con-wr">
			<div class="con-inner sub-sub">
				<div class="tit-wr tit-wr-ani">
					<h2>스트리밍 강의 리스트</h2>
				</div>
				<!-- //tit-wr -->

				<div class="sub-content">
					<!--콘탠츠 내용 시작. 내용 불러오기-->
					<div class="content-area clearfix">
						<div class="sub-tit-wr">
							<h3 id="subject_">RECOMMENDED</h3>
						</div>
						<!-- //sub-tit-wr -->
					<!-- 게시판 목록 시작 , 썸네일 이미지 -->
					<div id="bo_gall" style="width: 100%">
						<div class="gall_box rec_class">
							<a href="classdetail?classIdx=${liveClass.classIdx }">
								<div class="thum_hover">
									<div style="color: #ef0000;font-size: 2em;">${liveClass.classLength } 주간<br>${liveClass.classDay }<br>${liveClass.liveStatus }</div>
								</div>
								<!-- //thum_hover -->
							</a>
							<div class="gall_con">
								<div class="gall_img">
									<img id="rec_class_img" src="${pageContext.request.contextPath }/resources/images/class/thumbnail.jpg">
								</div>
								<!-- //gall_img -->
								<div id="rec_class_info">
									<p id="rec_class_info1">
										<span>${liveClass.classType }</span>
									</p>
									<p id="rec_class_info2">${liveClass.className }</p>
									<p id="rec_class_info3">${liveClass.classTime }</p>
								</div>
								<!-- //rec_class_info -->
							</div>
							<!-- //gall_con -->
						</div>
						<!-- //gall_box -->
					
						<div class="thum-tit-wr">
							<h3 class="thum-tit">All CLASSES
							<select id="classType" style="float: right;">
								<option value="전체">전체</option>
								<option value="일대다">일대다</option>
								<option value="다대다">다대다</option>
							</select>
							</h3>
						</div>
						<!-- //thum-tit-wr -->
	
						<ul id="gall_ul" class="gall_row">
							<c:forEach var="liveclass" items="${liveClassList }" begin="0" end="2">
								<li class="gall_li col-gn-3">
									<div class="gall_box">
										<a href="classdetail?classIdx=${liveclass.classIdx }">
											<div class="thum_hover">
												<div style="color: #ef0000;">${liveclass.classLength } 주간<br>${liveclass.classDay }<br>${liveclass.liveStatus }</div>
											</div>
											<!-- //class_info -->
										</a>
										<div class="gall_con">
											<div class="gall_img">
												<img src="${pageContext.request.contextPath }/resources/images/class/thumbnail.jpg">
											</div>
											<!-- //gall_img -->
											<div class="class_info">
												<p class="class_info1">
													<span>${liveclass.classType }</span>
												</p>
												<p class="class_info2">${liveclass.className }</p>
												<p class="class_info3">${liveclass.classTime }</p>
											</div>
											<!-- //class_info -->
										</div>
										<!-- //gall_con -->
										<c:choose>
										<c:when test="${liveclass.classType eq '일대다' }">
											<sec:authorize access="isAuthenticated()">
												<sec:authentication property="principal" var="user" />
												<c:if test="${liveclass.fkUserId eq user.username }">
													<button class="broadcast" onclick="location.href='broadcaster?classIdx=${liveclass.classIdx }'" style="float:left;">START</button>
												</c:if>	
											</sec:authorize>
											<c:forEach var="member" items="${classMember }">
												<c:if test="${member.fkClassIdx eq liveclass.classIdx }">
													<button class="broadcast" onclick="location.href='viewer?classIdx=${liveclass.classIdx }'" style="float:right;">VIEW</button>
												</c:if>
											</c:forEach>
										</c:when>	
										<c:otherwise>
											<sec:authorize access="isAuthenticated()">
											<sec:authentication property="principal" var="user" />
											<c:if test="${liveclass.fkUserId eq user.username }">
												<button class="broadcast" onclick="location.href='multi?classIdx=${liveclass.classIdx }'" style="float:left;">MULTI</button>
											</c:if>
											<c:forEach var="member" items="${classMember }">
												<c:if test="${member.fkClassIdx eq liveclass.classIdx}">
													<button class="broadcast" onclick="location.href='multi?classIdx=${liveclass.classIdx }'" style="float:right;">MULTI</button>
												</c:if>
											</c:forEach>
											</sec:authorize>	
										</c:otherwise>
										</c:choose>
									</div>
									<!-- //gall_box -->
								</li>
							</c:forEach>
						</ul>
						
						<!-- 더보기 클릭시 end값 계산 위해 사용 -->
						<input type="hidden" id="p" name="curPage" value="1">
						
						<!--더보기 버튼-->
 						<button type="button" class="view-more">
							view more <i class="fas fa-plus"></i>
						</button> 
						
						<!-- 로그인한 userId 얻어오기 -->
						<sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal" var="user" />
							<input type="hidden" value="${user.username }" id="userId">
						</sec:authorize>
					</div>
					<!-- //bo_gall -->
					</div>
					<!-- //content-area-->
				</div>
				<!-- //sub-content -->
			</div>
			<!--con-inner-->
		</div>
		<!--con-wr-->
	</div>
	<!-- } 하단 끝 -->
<script>
	$(document).ready(function() {
		$("#classType").change(function() {
			location.href="classlist?classType="+$("#classType").val();
		});
		$("select option[value='${classType}']").attr("selected", true);
		console.log("${classType}");
	});
	
	//더보기 ajax
	var curPage = $("input[name=curPage]").val();
	console.log("curPage : " + curPage);
		$(".view-more").click(function() {
			curPage++;
			 
			var list = new Array();
			<c:forEach var="member" items="${classMember }">;
				var json = new Object();
				json.fkClassIdx = "${member.fkClassIdx}";
				list.push(json);
			</c:forEach>
			 
			 $.ajax({
				type : "GET", 
				url : "moreclass?end=" + curPage * 3,
				dataType : "json",
				success : function(data) {
					/* alert("성공"); */					
					var userId = $("#userId").val();
					
					let ul = document.getElementById("gall_ul");
					
					for(let idx in data) {
						
						let gall_li = document.createElement('li');
						gall_li.className = "gall_li col-gn-3";

						let gall_box = document.createElement('div');
						gall_box.className = "gall_box";
						gall_li.appendChild(gall_box);
						
						let a = document.createElement('a');
						a.href = "classdetail?classIdx=" + data[idx].classIdx;
						gall_box.appendChild(a);
						
						let thum_hover = document.createElement('div');
						thum_hover.className = "thum_hover";
						a.appendChild(thum_hover);
						
						let div = document.createElement('div');
						div.style.color = "#ef0000";
						div.innerText = data[idx].classDay + '\n' + data[idx].classLength + '주간\n' + data[idx].liveStatus;
						thum_hover.appendChild(div);
						
						let gall_con = document.createElement('div');
						gall_con.className = "gall_con";
						gall_box.append(gall_con);
						
						let gall_img = document.createElement('div');
						gall_img.className = "gall_img";
						gall_con.appendChild(gall_img);
						
						let class_info = document.createElement('div');
						class_info.className = "class_info";
						gall_con.append(class_info);
						
						let class_info1 = document.createElement('p');
						class_info1.className = "class_info1";
						let span = document.createElement('span');
						span.innerText = data[idx].classType;
						class_info1.appendChild(span);
						class_info.appendChild(class_info1);
						
						let class_info2 = document.createElement('p');
						class_info2.className = "class_info2";
						class_info2.innerText = data[idx].className;
						class_info.append(class_info2);

						let class_info3 = document.createElement('p');
						class_info3.className = "class_info3";
						class_info3.innerText = data[idx].classTime;
						class_info.append(class_info3);
						
						if (data[idx].classType == '일대다') {
							if (data[idx].fkUserId == userId) {
								let startBtn = document.createElement('button');
								startBtn.className = "broadcast";
								startBtn.addEventListener('click', function() {
									location.href="broadcaster?classIdx=" + data[idx].classIdx;
								});
								startBtn.style.cssFloat = "left";
								startBtn.innerText = "START";
								gall_box.append(startBtn);
							}
							
							let classIdx = data[idx].classIdx;
							for (let idx in list) {
								if (list[idx].fkClassIdx == classIdx) {
									let viewBtn = document.createElement('button');
									viewBtn.className = "broadcast";
									viewBtn.addEventListener('click', function() {
										location.href="viewer?classIdx=" + classIdx;
									});
									viewBtn.style.cssFloat = "right";
									viewBtn.innerText = "VIEW";
									gall_box.append(viewBtn);
								}
							}
							
						} else {
							if (data[idx].fkUserId == userId) {
								let multiBtn = document.createElement('button');
								multiBtn.className = "broadcast";
								multiBtn.addEventListener('click', function() {
									location.href="multi?classIdx=" + data[idx].classIdx;
								});
								multiBtn.style.cssFloat = "left";
								multiBtn.innerText = "MULTI";
								gall_box.append(multiBtn);
							}
							
							
							let classIdx = data[idx].classIdx;
							for (let idx in list) {
								if (list[idx].fkClassIdx == classIdx) {
									let multiBtn = document.createElement('button');
									multiBtn.className = "broadcast";
									multiBtn.addEventListener('click', function() {
										location.href="multi?classIdx=" + classIdx;
									});
									multiBtn.style.cssFloat = "right";
									multiBtn.innerText = "MULTI";
									gall_box.append(multiBtn);
								}
							}
						}
						
						let img = document.createElement('img');
						img.src = "${pageContext.request.contextPath }/resources/images/class/thumbnail.jpg";
						gall_img.appendChild(img);
						
						ul.appendChild(gall_li);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("실패 : \n"
							+ "jqXHR.readyState : " + jqXHR.readyState + "\n" 
							+ "textStatus : " + textStatus + "\n"
							+ "errorThrown : " + errorThrown);
				}
			}); 
		});
</script>
</body>
</html>