<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기작성</title>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/review/reviewWrite.css">
<style>

	.star{
		  background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;
		  background-size: auto 100%;
		  width: 30px;
		  height: 30px;
		  display: inline-block;
		  text-indent: -9999px;
		  cursor: pointer;
		}
	div.star.on{background-position:0 0;}
</style>
<script>
//--다중 파일 선택시 미리보기
	//이미지 정보들을 담을 배열
	var sel_files =[];
	
	$(document).ready(function(){
		$("#input_imgs").on("change", handleImgFileSelect);
	});
		
 	function fileUploadAction(){
		console.log("fileUploadAction");
		$("#input_imgs").trigger('click');
	} 
	
	function handleImgFileSelect(e){
		// 이미지 정보들을 초기화
 		sel_files = [];
/* 		$(".imgs_wrap").empty();  */
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		
		var index = 0; 
		filesArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}
			sel_files.push(f);
			
			var reader = new FileReader();
			reader.onload = function(e){
				var html ="<span class ='img_id_"+index+"'>";
					html +="<img src="+ e.target.result+" >";
					html +="<input type='button' value='삭제' onclick='deleteImageAction("+index+")'>";
					html +="</span>";
				//var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("+index+")\" id = \"img_id" +index+"\">";
					//html +="<img src=\"" + e.target.result + "\" data-file='" + f.name + "' class = \"img_id" +index+"\"  title='Click to remove'>";
					//html +="</a>";
					
				$(".imgs_wrap").append(html);
				index++;
				
			}
			reader.readAsDataURL(f);
		});
	}
	
	//다중 파일 업로드 할때 특정 이미지만 삭제하기
	function deleteImageAction(index){
		alert("미리보기삭제" + index);
		console.log("sel_files : " + sel_files);
		sel_files.splice(index, 1);
		
		var imgid = ".img_id_" + index;
		console.log("imgid:" + imgid);
		
		$(imgid).remove();
		console.log("sel_files : " + sel_files);
	}
	//수정할 때 기존에 올려져 있던 이미지 삭제부분
	function imgDel(revFileIdx){
		alert("이미지삭제" + revFileIdx);
		console.log(revFileIdx);
			   $.ajax({
				   url:"imgDel?revFileIdx="+revFileIdx,
					type:"post",
					data: {"revFileIdx" : revFileIdx},
					success:function(){
						$('#uploadedimg'+revFileIdx).remove();
						alert("삭제되었습니다."+revFileIdx);
					}
			  		,error:function(error){
			   			console.log("에러:" + error);
			   		}
			   })
		   }
</script>
</head>
<body>
	<br><br><br><br>
	<div class ="container">
<form id="frm" method="post" 
		enctype="multipart/form-data">
	<div id="wrap">
		<div id = "product_layout_1">
			<div class = "top">
				<div class="writeform">
				<div class = "product_info">
					<div class ="redbar"></div>
					<span id="title">별점</span>
					<div class="starRev">
						<span class="star on">1</span>
						<span class="star">2</span>
						<span class="star">3</span>
						<span class="star">4</span>
						<span class="star" id="star">5</span>
						<input id="revStar1" type="hidden" name="revStar" value="">
					</div> 
				</div>
				<div class="textarea">
				<div class="titlegroup">
					<div class ="redbar"></div>
					<span id="title2">내용</span>
				</div>
				<textarea name="revContent" rows="20" cols="100" title="내용" placeholder="후기를 작성해주세요..."></textarea>
				</div>
				</div>
				<div class = "imgbox">	
				<a href="javascript:" onclick="fileUploadAction();"  class="btn">파일 업로드</a>
				<input type="file"  multiple="multiple" id="input_imgs"  name="file_0" >
				<div id = "fileDiv">
					<h2><b>이미지 미리보기</b></h2>
					<div class="imgcontent">
							<span class="imgs_wrap">
								<img class = "img">
							</span>
					</div>		
				</div>
			</div><!-- class ="imgbox"끝 -->	
			</div><!--class="top"끝  -->
		</div>	
	</div>
<!--  이미지 미리보기 -->

	<div class = "btnclass">
	<a href="#this" id="list" class="btn">목록으로</a>
	<a href="#this" id="write" class="btn">작성완료</a>
	</div>
</form>
</div>	
<%@ include file="/WEB-INF/include/include-body.jsp" %>	

<script type="text/javascript">
	var g_count = 1; 
 	$(document).ready(function(){
 		$("#list").on("click", function(e){
 			e.preventDefault();
 			fn_openBoardList(); 
 		})
 		$("#write").on("click", function(e){
 			e.preventDefault();
 			fn_writeBoard();
 		})

 	});
 	
 	function fn_openBoardList(){
 		var comSubmit = new ComSubmit();
 		console.log("<c:url value='/reviewBoard'/>");
 		comSubmit.setUrl("<c:url value='/review'/>");
 		comSubmit.submit();
 	}
 	
 	function fn_writeBoard(){
 		var comSubmit = new ComSubmit("frm");
 		comSubmit.setUrl("<c:url value='/insertrboard'/>");
 		comSubmit.submit();
 	}

 	//별점 스크립트부분
	$('.starRev span').click(function(){
		$(this).parent().children('span').removeClass('on');
		$(this).addClass('on').prevAll('span').addClass('on');
 		var star = $(this).text(); 
		$('#revStar1').attr('value',star);   
		  return false;
		})
</script>
</body>
</html>