<%@page import="com.bit.hellopt.service.reviewboard.RBoardServiceImpl"%>
<%@page import="com.bit.hellopt.service.reviewboard.RBoardService"%>
<%@page import="org.apache.ibatis.annotations.Mapper"%>
<%@page import="com.bit.hellopt.vo.reviewboard.RBoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

<%-- <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board/reviewBoard.css"> --%>
<meta charset="UTF-8">
<title>후기게시판</title>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<style>
 	.content {
	box-sizing: border-box;
	width: 800px;
	margin: 0 auto 150px;
	padding-top: 10%;
	text-align: center; 
}
	.btn{color: white;}
	td .star{
		  background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;
		  background-size: auto 100%;
		  width: 20px;
		  height: 20px;
		  display: inline-block;
		  text-indent: -9999px;
		  cursor: pointer;
		}
	div .star.on{background-position:0 0;}
	
	a {
		text-decoration: none;
		color: white;
	}
	button {
		background-color:white;
	}
	.paging { list-style: none; }
	.paging li {
		float: left;
		margin-right: 8px;
	}
	.paging li a {
		text-decoration: none;
		display: block;
		padding: 3px 7px;
		border: 1px solid red;
		font-weight: bold;
		color: white;
	}
	.paging .disable {
		border: 1px solid silver;
		padding: 3px 7px;
		color: silver;
	}
	.paging .now {
		border: 1px solid red;
		padding: 3px 7px;
		background-color: red;
	}
	.paging li a:hover {
		background-color: red;
		color: black;
	}
</style>
</head>
<body>
<div id = "container">
	<sec:authorize access="isAuthenticated()">
	<p><a href="${pageContext.request.contextPath}/review/insertform">후기쓰러가기</a></p>	
	</sec:authorize>
<c:forEach var="rBoard" items="${rBoardList }" varStatus="status"> 
<form>
<table class="tbl_wrap">
	<thead class="tbl_head">
		<tr>
			<c:if test="${rBoard.userFileName == null }">
			<td class="profiletd">
				<div class="profilediv">
				<img class='profile' src="/hellopt/file/708641a0ecc24332a908d974d41d07b5.png">
				</div>
			</td>
			</c:if>
			<c:if test="${rBoard.userFileName != null }">
			<td class="profiletd">
				<div  class="profilediv">
				<img class='profile' src="/hellopt/file/${rBoard.userFileName}">
				</div>
			</td>
			</c:if>
			<td>
				<span>글쓴이 : ${rBoard.userName }</span>
				<!-- 글수정삭제 메뉴 -->
				<sec:authorize access="isAuthenticated()">
				<sec:authentication var="principal" property="principal" />
				<c:if test="${rBoard.userId == principal.username}">
					<button type="button" data-toggle="collapse" data-target="#togle" class="menu">
						<svg height="30" width="30" viewBox="0 0 60 60">
		    			 <circle clip-rule="evenodd" cx="8" cy="24" r="4.5" style="fill:#b4000f;" /> 
		    			 <circle clip-rule="evenodd" cx="24" cy="24" r="4.5" style="fill:#b4000f;" /> 
		    			 <circle  cx="40" cy="24" r="4.5" style="fill:#b4000f;" /> 
						</svg>
					</button>
					<div id="togle" class="collapse">	
						<input type = "button" value="글 수정" onclick = "modify(${rBoard.revIdx})">
						<input type = "button" value="글 삭제" onclick = "del(${rBoard.revIdx })">
					</div>
				</c:if>
				</sec:authorize>
				<!-- 글수정삭제 메뉴 끝 -->
			<div class="starRev"> 
				<%-- 	if문 써서	<c:forEach var="i" begin="1" end="5" step="1" >
			<c:if test="${i  > rBoard.revStar }">
					<span class="star"></span>
				</c:if>	
				<c:if test="${i  <= rBoard.revStar }">
					<span class="star on"></span>
				</c:if>	
			</c:forEach> --%>
			<c:forEach var="i" begin="1" end="${rBoard.revStar }" step="1">
				<span class="star on">${i}</span>
			</c:forEach> 
			<c:forEach var="i" begin="1" end="${5-(rBoard.revStar) }" step="1">
				<span class="star">${i}</span>
			</c:forEach>   
			</div>
			</td>  
		</tr>
		<!--별점기능 -->
	</thead>
	
	<tbody>
		<tr>
			<td colspan="2">
			<!--이미지 사진 업로드한부분 나오는곳  -->
			<c:forEach var="file" items="${rBoard.filevo }">
				<a id="prev" href="#">prev</a>
					<div class="content_img_div">
						<img class="content_img" src="/hellopt/s3/review/${file.revFileSname } ">
					</div>
				<a id="next" href="#">next</a>
			</c:forEach>
			<div id="content">내용 :${rBoard.revContent}</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">날짜 :<fmt:formatDate value="${rBoard.revRegdate }" type="date"/></td>
		</tr>
	</tbody>
	</table>
</form>		
<form class="commentForm" name="commentFrom" method="post">		
	<table class = "rply_wrap">
		<tr>
			<td colspan="2">
				<a href="#demo" data-toggle="collapse" value="댓글보기"  class = "more_rpl" onclick = "listReply2(${rBoard.revIdx })">댓글보기</a>
			</td>
		</tr> 
		<!-- 댓글 작성 폼 -->
		<tr class="rpl_area">
			<td class="rpl_write_area">
				<textarea rows="2" cols="80" class="revCmtComment${rBoard.revIdx }" name="revCmtComment" placeholder="댓글 달기..."></textarea>
			</td>
			<td>
		<sec:authorize access="isAuthenticated()">
			<input type ="button" value="댓글등록" class = "rplbtn" class="btn${rBoard.revIdx }" onclick = "createCmt(${rBoard.revIdx })">
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			<input type ="button" value="댓글등록" class = "rplbtn" class="btn${rBoard.revIdx }" onclick = "createCmt2(${rBoard.revIdx })">
		</sec:authorize>
				<!-- <button type="button" class="btnReply">댓글등록</button> -->
			</td>	
		</tr>
		<!-- 댓글이 있으면 댓글몇개 달렸다고 출력하기 -->
	</table>
		<div class="listReply${rBoard.revIdx }"></div>
	</form>
</c:forEach>
	<br><br><br>
	<table>
	<!--페이징 -->
	<tr>
	<td colspan="4">
		<ol class="paging">
			<c:choose>
				<c:when test="${pvo.beginPage == 1}">
					<li class="disable">이전으로<li>
				</c:when>
				<c:otherwise>
					<li>
						<a class="disable" href="review?cPage=${pvo.beginPage - 1 }">이전으로</a>
					</li>
				</c:otherwise>
			</c:choose>
			
			<!-- 블록내에 표시할 페이지 표시(시작페이지~끝페이지)  -->
			<c:forEach var="k" begin="${pvo.beginPage }" end="${pvo.endPage }">
			<c:choose>
				<c:when test="${k == pvo.nowPage }">
					<li class="now">${k }</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="review?cPage=${k}">${k }</a>
					</li>
				</c:otherwise>
			</c:choose>
			</c:forEach>
			<!-- [다음으로]에 대한 사용여부 처리 -->
			<!-- 사용불가(disable) 
					endPage가 전체페이지 수보다 크거나 같으면
				-->
			<c:choose>
				<c:when test="${pvo.endPage >= pvo.totalPage }">
					<li class="disable">다음으로 </li>
				</c:when>
				<c:otherwise>
					<li >
						<a href="review?cPage=${pvo.endPage + 1 }">다음으로</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ol>
	</td>
	</tr>
</table>
 	<!--페이징끝 -->
</div>	
<%@ include file="/WEB-INF/include/include-body.jsp" %>	
<script>
//게시글 수정 삭제 시작
function del(revIdx) {
	var chk = confirm("정말 삭제 하시겠습니까?");
	if (chk){
		location.href = 'deleterboard?revIdx='+revIdx;	
	}
}	
function modify(revIdx) {
		location.href = '${pageContext.request.contextPath}/review/updateform?revIdx='+revIdx;	
		
}
</script>

<script>
//댓글작성하기
function createCmt(revIdx) {
      console.log(revIdx)
      var revCmtComment= $(".revCmtComment" + revIdx).val();
      console.log(revCmtComment);
      var param="revCmtComment=" + revCmtComment + "&revIdx=" + revIdx;
      console.log(param);
      
      $.ajax({
         type:"post",
         url: "reply/insert",
         data: param,
         success: function(){
            listReply2(revIdx);
            $(".revCmtComment" + revIdx).val("");
            alert("댓글이 등록되었습니다.");
         }
      });
   }
function createCmt2(revIdx) {
	var chk = confirm("로그인 사용자만 댓글을 남길 수 있습니다. 로그인 페이지로 이동합니다.");
	if(chk){
		location.href = '${pageContext.request.contextPath}/login';	
	}

   }

  	//댓글 수정하기
  	//수정폼
   function modReple(revCmtIdx,revCmtRegdate,revCmtComment,revIdx, userName){
	   var 	output = "<table id='revCmtIdx"+revCmtIdx+"'>";
			output +="<tr>";
			output +="<td>" + userName;
			output +="(" + changeDate(revCmtRegdate) +")<br>";
			output +="<textarea name='revCmtComment' id='revCmtComment' rows='2' cols='80'>";
			output += revCmtComment
			output +="</textarea><br>";
			output +="<input type='button' value='수정' onclick= updateReple(" +revCmtIdx +","+revIdx+ ",'" + userName + "')>";
			output +="<input type='button' value='취소' onclick='listReply2("+revIdx+")'>";
			output +="</td></tr>";
			output +="</table>";
			$(".listReply" + revIdx).html(output);
			$('#revCmtIdx'+revCmtIdx+'#revCmtComment').focus();
			/* $('#revCmtIdx'+revCmtIdx).replaceWith(output);
			$('#revCmtIdx'+revCmtIdx+'#editContent').focus(); */
   } 
  	//수정누르면 업뎃되는기능
    function updateReple(revCmtIdx,revIdx,userName){
	   
	   var repleEditContent =$('#revCmtComment').val();
	   console.log(repleEditContent);
	   var paramData = JSON.stringify({"revCmtComment":repleEditContent, "revCmtIdx":revCmtIdx});
	   console.log(paramData);
		$.ajax({
			url:"reply/update?revCmtIdx="+revCmtIdx,
			contentType: 'application/json',
			type:"post",
			data: paramData,
			success:function(result){
				console.log(result);
				listReply2(revIdx);
				alert("댓수정완료");
			}
			,error: function(error){
				console.log("에러:" + error);
			}
		})
   
   } 

   //댓글 삭제하기
   function delReple(revCmtIdx, revIdx){
	   alert("댓삭" + revCmtIdx, revIdx);
			console.log("revIdx:" +revIdx);	   
	   $.ajax({
		   
		   url:"reply/delete?revCmtIdx="+revCmtIdx,
			type:"post",
			data:{"revIdx" : revIdx,"revCmtIdx" : revCmtIdx},
			success:function(result){
				listReply2(revIdx);
				alert("삭제되었습니다."+revCmtIdx);
			}
	   		,error:function(error){
	   			console.log("에러:" + error);
	   		}
	   })
   }

	function listReply2(revIdx){
		$.ajax({
			type: "get",
			//contentType: "application/json", ==>생략가능 (RestController가 )
			url: "review/replyjson?revIdx="+revIdx,
			success:function(result){
				console.log(result);
				if(result.length > 0){
					var output = "<table>";
					for(var i in result){
						output +="<tr>";
						output +="<td>" + result[i].userName;
						output +="(" +changeDate(result[i].revCmtRegdate)+")<br>";
						output += result[i].revCmtComment +"<br>";
						output +="<sec:authorize access='isAuthenticated()'>";
						output +="<sec:authentication var='principal' property='principal' />";
						output +="<c:if test="${result[i].userId == principal.username}">";
						output +="<input type='button' value='수정' class='ajaxbtn' onclick= modReple(" + result[i].revCmtIdx +","+ result[i].revCmtRegdate + ",'"+result[i].revCmtComment+"',"+ result[i].revIdx + ",'" + result[i].userName +"')>";
						output +="<input type='button' value='삭제' class='ajaxbtn' onclick= delReple(" + result[i].revCmtIdx + ","+ result[i].revIdx + ")>";
						output +="</c:if>";
						output +="</sec:authorize>";
						output +="</td></tr>";
					}
					output +="</table>";
				} else {
					var output = "<table id='demo'class='collapse'>";
					output +="<tr>";
					output +="<td><h6>등록된 댓글이 없습니다.</h6></td>";
					output +="</tr>";
					output +="</table>";
				}
				
				$(".listReply" + revIdx).html(output);
			}
		}); 
	}
</script>
<script type="text/javascript">
	function changeDate(String){
		date = new Date(parseInt(String));
		year = date.getFullYear();
		month = date.getMonth();
		day = date.getDate();
		hour = date.getHours();
		minute = date.getMinutes();
		second = date.getSeconds();
		strDate = year+"-"+month+"-"+day+" "+hour+":"+minute+ ":"+second;
		return strDate;
	}
</script>
</body>
</html>