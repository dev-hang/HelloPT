var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스

// 기본좌표 백암빌딩 설정
var latitude = 37.503252; // 위도
var longitude = 127.024342; // 경도

// geolocationAPI를 사용하여 현재 위치를 가져온다
if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function (position) { // geolocation을 이용해서 접속 위치를 얻어옵니다.
        latitude = position.coords.latitude;
        longitude = position.coords.longitude;

        var locPosition = new kakao.maps.LatLng(latitude, longitude);
        map.setCenter(locPosition);
    });
} else {
    console.log("geolocaion을 사용할 수 없습니다.");
}

var options = { //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(latitude, longitude), //지도의 중심좌표.
    level: 3 //지도의 레벨(확대, 축소 정도)
};

function httpGetAsync(theUrl, callback) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

function requestAPI(response) {
    console.log(response);
    var stock = {
        empty: '재고 없음',
        few: '2~29개',
        some: '30~99개',
        plenty: '100개~',
        break: '판매중지',
        null: '정보없음',
    }

    var stockImg = {
        empty: 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiM4NDg0ODQiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+',
        few: 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiNGQjUyNTIiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+',
        some: 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiNGRkNFMDAiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+',
        plenty: 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiMwMEFGODAiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+',
        break: 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiM4NDg0ODQiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+',
        null: 'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiM4NDg0ODQiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+',
    }

    // 마커를 표시할 위치와 title 객체 배열
    var response = JSON.parse(response); // JSON 객체로 변환
    var positions = [];

    for (var i = 0; i < response['stores'].length; i++) {
        positions.push({
            title: response['stores'][i]['name'],
            latlng: new kakao.maps.LatLng(response['stores'][i]['lat'], response['stores'][i]['lng']),
            content: response['stores'][i]['name'] + 
            	'<br>재고 : ' + stock[response['stores'][i]['remain_stat']] +
                '<br>입고 시간 : ' + response['stores'][i]['stock_at'] +
                '<br>업데이트 시간 : ' + response['stores'][i]['created_at'],
            imginfo: stockImg[response['stores'][i]['remain_stat']],
        });
    }

    for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 주소
        // 재고에 따라 마커 이미지 색상이 변경
        var imageSrc = positions[i].imginfo;

        // 마커 이미지의 이미지 크기
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성 
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시
            image: markerImage // 마커 이미지 
        });

        // 마커에 표시할 인포윈도우를 생성
        var infowindow = new kakao.maps.InfoWindow({
            content: positions[i].content // 인포윈도우에 표시할 내용
        });

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록
        // 이벤트 리스너로는 클로저를 만들어 등록
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록
        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
    }
}

// 인포윈도우를 표시하는 클로저를 만드는 함수
function makeOverListener(map, marker, infowindow) {
    return function () {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수
function makeOutListener(infowindow) {
    return function () {
        infowindow.close();
    };
}

// 엔터가 눌렸을 때 실행되는 함수
function enterkey() {
    if (window.event.keyCode == 13) {
        // 장소를 검색
        searchPlace();
    }
}

// 검색 버튼이 눌렸을 때 실행되는 함수
function buttonPress() {
    // 장소를 검색
    searchPlace();
}

// 장소 검색 객체를 생성
var ps = new kakao.maps.services.Places();

// 검색을 요청하는 함수
function searchPlace() {
    var keyword = document.getElementById('search').value;

    // 검색어를 입력하지 않고 엔터나 검색 버튼을 눌렀을 때 실행
    if (!keyword.trim()) {
        alert('검색어를 입력해주세요!');

        return false;
    }

    // 장소 검색 객체를 통해 장소 검색을 요청
    ps.keywordSearch(keyword, placeSearchCB);
}

// 장소 검색이 완료되었을 때 호출되는 콜백함수
function placeSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료 됐으면 검색 목록을 반환
        PlacesData(data);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert('검색 결과가 존재하지 않습니다.');

        return;
    } else if (status === kakao.maps.services.Status.ERROR) {
        alert('검색 중 오류가 발생했습니다.');

        return;
    }
}

// 검색 결과 목록을 나타내는 함수
function PlacesData(places) {
    var bounds = new kakao.maps.LatLngBounds();
    var placePosition = new kakao.maps.LatLng(places[0].y, places[0].x);

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기 위해 LatLngBounds 객체에 좌표를 추가
    bounds.extend(placePosition);

    for (var i = 0; i < places.length; i++) {
        console.log(places[i]);
    }

    map.setBounds(bounds);
}

var default_lat = latitude;
var default_lng = longitude;
var lat = default_lat;
var lng = default_lng;
var urlString = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByGeo/json?lat=" + lat + "&lng=" + lng + "&m=2000";
httpGetAsync(urlString, requestAPI);

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

// 지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록
kakao.maps.event.addListener(map, 'dragend', function () {
    var latlng = map.getCenter(); // 지도의 중심좌표
    lat = latlng.getLat();
    lng = latlng.getLng();
    urlString = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByGeo/json?lat=" + lat + "&lng=" + lng + "&m=2000";
    httpGetAsync(urlString, requestAPI);

});
