/* 모임 작성 버튼 */
(function() {

	$(document).ready(function() {
		return $(".send-btn").click(function() {

			location.href = "/hellopt/meetingWrite";

		});
	});

}).call(this);

//searchKeyword
var getSearch = document.getElementById('getSearch');

 getSearch.onkeyup = function(){
	
	var searchKeyword = document.getElementById('getSearch').value;
	
	console.log("searchKeyword : " + searchKeyword);
	
	if (searchKeyword.length >= 1) {
		$.ajax({
			url : "searchKeyword",
			data : {
				searchKeyword : searchKeyword,
			},
			type : "post",
			dataType : 'json',
			success : function(data) {
				
				
				var containerBody ="";
				var m = moment();
				var today = m.format('YYYYMMDD');
				
				data.forEach(function(getSearch){
					var mDate = getSearch.mDate;
					var mtoday = mDate.replace(/\-/g,'');
					
					console.log(today +":"+mtoday);
					
					if ( getSearch.progressSt == '진행중'){
						containerBody += '<div class="service-details">' ;
						containerBody += '<img src="/hellopt/s3/meeting/'+getSearch.meetingFileVO[0].mSysImg+'">';
						containerBody += '<div class="service-hover-text">';
						containerBody += '<h3 class="msub textLine2">'+getSearch.mSubject+'</h3>';
						//날짜 수정하기
						containerBody += '<p class=mday>'+(mtoday-today)+'일 남음 </p>';
						//여기 까지
						containerBody += '</div>';
						containerBody += '<div class="service-white service-text">';
						containerBody += '<div class="minner">';
						containerBody += '<a class="mcategory">';
						containerBody += '<img class="categoryimg" src="/hellopt/resources/images/meeting/localtag.png" >';
						containerBody += getSearch.mCategory;
						containerBody += '</a>';
						containerBody += '<a class="lcategory">';
						containerBody += '<img class="localimg" src="/hellopt/resources/images/meeting/location.png">';
						containerBody += getSearch.local;
						containerBody += '</a>';
						containerBody += '<a class="mpro">'+getSearch.progressSt+'</a>';
						containerBody += '<p class="msub"><a href="/hellopt/meetingOne?meetingIdx='+getSearch.meetingIdx+'">'+getSearch.mSubject+'</a></p>';
						//포맷 데이터 수정하기
						containerBody += '<a class="mprice">회비 '+numeral( getSearch.mPrice ).format( '0,0' )+'원</a>';
						//여기까지
						containerBody += '</div>';
						containerBody += '</div>';
						containerBody += '</div>';
						
					}
					
				});
				
				$(".container").html(containerBody);
		
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("미팅 통신에러");
			}
		
		}); //json끝 
	}
	
	if (searchKeyword == "") {
		
		$.ajax({
			url : "meetingListAjax",
			data : {
				searchKeyword : "",
			},
			type : "post",
			dataType : 'json',
			success : function(data) {
				
				
				var containerBody ="";
				var m = moment();
				var today = m.format('YYYYMMDD');
				
				data.forEach(function(getSearch){
					var mDate = getSearch.mDate;
					var mtoday = mDate.replace(/\-/g,'');
					
					console.log(today +":"+mtoday);
					
					if ( getSearch.progressSt == '진행중'){
						containerBody += '<div class="service-details">' ;
						containerBody += '<img src="/hellopt/s3/meeting/'+getSearch.meetingFileVO[0].mSysImg+'">';
						containerBody += '<div class="service-hover-text">';
						containerBody += '<h3 class="msub textLine2">'+getSearch.mSubject+'</h3>';
						//날짜 수정하기
						containerBody += '<p class=mday>'+(mtoday-today)+'일 남음 </p>';
						//여기 까지
						containerBody += '</div>';
						containerBody += '<div class="service-white service-text">';
						containerBody += '<div class="minner">';
						containerBody += '<a class="mcategory">';
						containerBody += '<img class="categoryimg" src="/hellopt/resources/images/meeting/localtag.png" >';
						containerBody += getSearch.mCategory;
						containerBody += '</a>';
						containerBody += '<a class="lcategory">';
						containerBody += '<img class="localimg" src="/hellopt/resources/images/meeting/location.png">';
						containerBody += getSearch.local;
						containerBody += '</a>';
						containerBody += '<a class="mpro">'+getSearch.progressSt+'</a>';
						containerBody += '<p class="msub"><a href="/hellopt/meetingOne?meetingIdx='+getSearch.meetingIdx+'">'+getSearch.mSubject+'</a></p>';
						//포맷 데이터 수정하기
						containerBody += '<a class="mprice">회비 '+numeral( getSearch.mPrice ).format( '0,0' )+'원</a>';
						//여기까지
						containerBody += '</div>';
						containerBody += '</div>';
						containerBody += '</div>';
						
					}
					
				});
				
				$(".container").html(containerBody);
		
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("미팅 통신에러");
			}
		
		}); //json끝 
		
	}

};