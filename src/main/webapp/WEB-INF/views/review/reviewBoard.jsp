<%@page import="com.bit.hellopt.service.reviewboard.RBoardServiceImpl"%>
<%@page import="com.bit.hellopt.service.reviewboard.RBoardService"%>
<%@page import="org.apache.ibatis.annotations.Mapper"%>
<%@page import="com.bit.hellopt.vo.reviewboard.RBoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">	
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/review/reviewBoard.css">
<title>후기게시판</title>
<%@ include file="/WEB-INF/include/include-header.jsp"%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
</head>
<body>
<div class="container">
	<sec:authorize access="isAuthenticated()">
	<p><a href="${pageContext.request.contextPath}/review/insertform">후기쓰러가기</a></p>	
	</sec:authorize>
<c:forEach var="rBoard" items="${rBoardList }" varStatus="status"> 
	<div id="wrap">
		<div id="product_layout_1">
			<div  class="top">
				<div class="swiper-container">
					<div class = "swiper-wrapper">
					<c:forEach var="file" items="${rBoard.filevo }">
						<div class = "swiper-slide">
							<img class="content_img" src="${pageContext.request.contextPath}/s3/review/${file.revFileSname } ">
						</div>
					</c:forEach>
					</div>
					<!-- 네비게이션 버튼 -->
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
					<!-- 페이징 -->
					<div class="swiper-pagination"></div>
				</div><!--  class="swiper-container swiper1" 끝 -->
			<div class="product_info">
			<c:if test="${rBoard.userFileName == null }">
			<div class="profile">
				<div class="profilediv">
				<img class='profileimg' src="${pageContext.request.contextPath}/s3/review/708641a0ecc24332a908d974d41d07b5.png">
				</div>
				<div class ="h1">${rBoard.userName }</div>
			</div>
			</c:if>
			<c:if test="${rBoard.userFileName != null }">
			<div class="profile">
				<div  class="profilediv">
				<img class='profileimg' src="${pageContext.request.contextPath}/s3/profile/${rBoard.userFileName}">
				</div>
			<div class ="h1">${rBoard.userName }</div>
			</div>
			</c:if>
			<!-- 글수정삭제 메뉴 -->
			<sec:authorize access="isAuthenticated()">
			<sec:authentication var="principal" property="principal" />
			<c:if test="${rBoard.userId == principal.username}">
				<button type="button" data-toggle="collapse" data-target="#togle_${rBoard.revIdx}" class="menu">
					<svg height="30" width="30" viewBox="0 0 60 60">
	    			 <circle  cx="40" cy="8" r="4.5" style="fill:#b4000f;" /> 
	    			 <circle  cx="40" cy="24" r="4.5" style="fill:#b4000f;" /> 
	    			 <circle  cx="40" cy="40" r="4.5" style="fill:#b4000f;" /> 
					</svg>
				</button>
				<div id ="togle">
				<div  class="collapse" id="togle_${rBoard.revIdx}">	
					<input type = "button" value="글 수정" onclick = "modify(${rBoard.revIdx})">
					<input type = "button" value="글 삭제" onclick = "del(${rBoard.revIdx })">
				</div>
				</div>
			</c:if>
			</sec:authorize>
			<!-- 글수정삭제 메뉴 끝 -->
				<div class="rating">
					<div class="starRev"> 
					<c:forEach var="i" begin="1" end="${rBoard.revStar }" step="1">
						<span class="star on">${i}</span>
					</c:forEach> 
					<c:forEach var="i" begin="1" end="${5-(rBoard.revStar) }" step="1">
						<span class="star">${i}</span>
					</c:forEach>   
					</div>
				</div>
				<div class="product_description">
					<p>${rBoard.revContent}</p>
					<span id="quantity"><fmt:formatDate value="${rBoard.revRegdate }" type="date"/></span>
					<div  id ="listReply" class="listReply${rBoard.revIdx }"></div>
				</div>
				
				<div class="related_info">
				<div>
					<div>
						<input type="button" value="댓글보기"  class = "more_rpl" onclick = "listReply2(${rBoard.revIdx })">
					</div>
				</div> 
				</div>
	<form class="commentForm" name="commentFrom" method="post">		
	<div class = "product_layout_1">
		<!-- 댓글 작성 폼 -->
		<div class="rpl_area">
			<div class="rpl_write_area">
			<textarea id="reple" class="revCmtComment${rBoard.revIdx }" name="revCmtComment" placeholder="댓글 달기..."></textarea>
			<span class = "rplbtn">
			<sec:authorize access="isAuthenticated()">
				<input type ="button" value="댓글등록" class = "rplbtn" class="btn${rBoard.revIdx }" onclick = "createCmt(${rBoard.revIdx })">
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<input type ="button" value="댓글등록" class = "rplbtn" class="btn${rBoard.revIdx }" onclick = "createCmt2(${rBoard.revIdx })">
			</sec:authorize>
					<!-- <button type="button" class="btnReply">댓글등록</button> -->
			</span>	
			</div>
		</div>
		<!-- 댓글이 있으면 댓글몇개 달렸다고 출력하기 -->
	</div>
	</form>
			</div><!-- 글내용칸 -->
			</div>
		</div>
	</div>
</c:forEach>				
</div>	
<div class="pagingtable">
	<!--페이징 -->
	<table>
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
</div>
<script>
var mySwiper = new Swiper('.swiper-container', {
	// 슬라이드를 버튼으로 움직일 수 있습니다.
		loop:true,
	  navigation: {
	    nextEl: '.swiper-button-next',
	    prevEl: '.swiper-button-prev',
	  },
	    
	// 현재 페이지를 나타내는 점이 생깁니다. 클릭하면 이동합니다.
	  pagination: {
	    el: '.swiper-pagination',
	    type: 'bullets',
	  },
	    
	});
</script>

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
  		
  		var output = "<table id='revCmtIdx"+revCmtIdx+"'>";
			output +="<tr>";
			output +="<td>" + userName;
			output +="(" + changeDate(revCmtRegdate) +")<br>";
			output +="<textarea name='revCmtComment' id='revCmtComment' rows='2' cols='80'>";
			output += revCmtComment
			output +="</textarea><br>";
			output +="<input type='button' value='수정' class='ajaxbtn' onclick= \"updateReple(" +revCmtIdx +","+revIdx+ ",'" + userName + "')\">";
			output +="<input type='button' value='취소' class='ajaxbtn' onclick=\"listReply2("+revIdx+")\">";
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
				alert("수정완료");
			}
			,error: function(error){
				console.log("에러:" + error);
			}
		})
   
   } 

   //댓글 삭제하기
   function delReple(revCmtIdx, revIdx){
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
						output +="<td class='rplbox'>" + result[i].userName;
						output +="(" +changeDate(result[i].revCmtRegdate)+")<br>";
						output += result[i].revCmtComment +"<br>";
						output +="<input type='button' value='수정' class='ajaxbtn' onclick= \"modReple(" + result[i].revCmtIdx +", "+ result[i].revCmtRegdate + ",'"+result[i].revCmtComment+"',"+ result[i].revIdx + ",'" + result[i].userName +"')\">";
						output +="<input type='button' value='삭제' class='ajaxbtn' onclick= \"delReple(" + result[i].revCmtIdx + ","+ result[i].revIdx + ")\">";
						output +="</td></tr>";
					}
					output +="</table>";
				} else {
					var output ="<table id='demo'>";
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