
/*
 * 너무 깔끔한 슬라이드 보류 var swiper = new Swiper('.swiper-container', { zoom: true,
 * pagination: { el: '.swiper-pagination', }, navigation: { nextEl:
 * '.swiper-button-next', prevEl: '.swiper-button-prev', }, });
 */

//3d 슬라이드 다시 적용하기
var swiper = new Swiper('.swiper-container', {
      effect: 'coverflow',
      grabCursor: true,
      centeredSlides: true,
      slidesPerView: 'auto',
      coverflowEffect: {
      rotate: 50,
      stretch: 0,
      depth: 100,
      modifier: 1,
      slideShadows : true,
    },
      pagination: {
      el: '.swiper-pagination',
    },
});

//이동 
 var idx = document.getElementById('meeting-idx').value; 

$('.send-btn2').click(function () {
	location.href="/hellopt/meeting";
});

$('.send-u').click(function () {
	location.href="/hellopt/meetingUpdate?meetingIdx="+idx;
});

$('.send-d').click(function () {
	location.href="/hellopt/meetingDelete?meetingIdx="+idx;
});

/* 지도 api 설정하기 */
var yx = document.getElementById('mLocation').value;
var array = yx.split(',');

for (let i = 0; i < array.length; i++) {
    console.log(array[i])
}

var mapContainer = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var mapOptions = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(array[1], array[2]), //지도의 중심좌표.
	
	level: 3 //지도의 레벨(확대, 축소 정도)
};



var map = new kakao.maps.Map(mapContainer, mapOptions); //지도 생성 및 객체 리턴



/*현재위치 마커 표시하기 */

var markerPosition = new kakao.maps.LatLng(array[1], array[2]);

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);



var add_search = document.getElementById('search_name').value;

var iwContent = '<a href="https://map.kakao.com/link/map/'+array[0]+','+array[1]+','+array[2]+'" style="padding:5px; color: black; margin:8px;">'+array[0]+'</a> <a href="https://map.kakao.com/link/to/'+array[0]+','+array[1]+','+array[2]+'" style="color:black; margin:8px;" ></a>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
iwPosition = new kakao.maps.LatLng(array[1], array[2]); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
position: iwPosition,
content: iwContent
});

// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
infowindow.open(map, marker);


//데이트 피커 적용하기
$('#datePicker').datepicker({
	format : "yyyy-mm-dd", //날짜 형식
	//todayHighlight: true
	//languge : "kr"  
});
