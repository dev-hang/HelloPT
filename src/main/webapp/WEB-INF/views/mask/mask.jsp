<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공공마스크 실시간 판매 현황</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mask/mask1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mask/mask2.css">
    <script src="${pageContext.request.contextPath}/resources/js/mask/main.js"></script>
</head>
<body onload="appendYear();">
<div class="all_view">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1cf06b214747f08132bda9cf27da5cb9&libraries=services,clusterer,drawing"></script>
    <div class="check-bg" id="check-bg"></div>
    <div class="check" id="check">
        <div class="check-header">
            <div class="header-msg">마스크 구매 가능 여부를 확인하세요!</div>
            <button class="cancel-btn" onclick="cancel()">
                <img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDQ5MiA0OTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDQ5MiA0OTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiIgY2xhc3M9ImhvdmVyZWQtcGF0aHMiPjxnPjxnPgoJPGc+CgkJPHBhdGggZD0iTTMwMC4xODgsMjQ2TDQ4NC4xNCw2Mi4wNGM1LjA2LTUuMDY0LDcuODUyLTExLjgyLDcuODYtMTkuMDI0YzAtNy4yMDgtMi43OTItMTMuOTcyLTcuODYtMTkuMDI4TDQ2OC4wMiw3Ljg3MiAgICBjLTUuMDY4LTUuMDc2LTExLjgyNC03Ljg1Ni0xOS4wMzYtNy44NTZjLTcuMiwwLTEzLjk1NiwyLjc4LTE5LjAyNCw3Ljg1NkwyNDYuMDA4LDE5MS44Mkw2Mi4wNDgsNy44NzIgICAgYy01LjA2LTUuMDc2LTExLjgyLTcuODU2LTE5LjAyOC03Ljg1NmMtNy4yLDAtMTMuOTYsMi43OC0xOS4wMiw3Ljg1Nkw3Ljg3MiwyMy45ODhjLTEwLjQ5NiwxMC40OTYtMTAuNDk2LDI3LjU2OCwwLDM4LjA1MiAgICBMMTkxLjgyOCwyNDZMNy44NzIsNDI5Ljk1MmMtNS4wNjQsNS4wNzItNy44NTIsMTEuODI4LTcuODUyLDE5LjAzMmMwLDcuMjA0LDIuNzg4LDEzLjk2LDcuODUyLDE5LjAyOGwxNi4xMjQsMTYuMTE2ICAgIGM1LjA2LDUuMDcyLDExLjgyNCw3Ljg1NiwxOS4wMiw3Ljg1NmM3LjIwOCwwLDEzLjk2OC0yLjc4NCwxOS4wMjgtNy44NTZsMTgzLjk2LTE4My45NTJsMTgzLjk1MiwxODMuOTUyICAgIGM1LjA2OCw1LjA3MiwxMS44MjQsNy44NTYsMTkuMDI0LDcuODU2aDAuMDA4YzcuMjA0LDAsMTMuOTYtMi43ODQsMTkuMDI4LTcuODU2bDE2LjEyLTE2LjExNiAgICBjNS4wNi01LjA2NCw3Ljg1Mi0xMS44MjQsNy44NTItMTkuMDI4YzAtNy4yMDQtMi43OTItMTMuOTYtNy44NTItMTkuMDI4TDMwMC4xODgsMjQ2eiIgZGF0YS1vcmlnaW5hbD0iIzAwMDAwMCIgY2xhc3M9ImhvdmVyZWQtcGF0aCBhY3RpdmUtcGF0aCIgc3R5bGU9ImZpbGw6I0ZGRkZGRiIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+'>
            </button>
        </div>
        <div class="check-body">
            <select id="check-year">
                <option value="xxxx">출생연도 선택</option>
            </select>
        </div>
        <div class="check-result-area" id="msg">
            출생연도를 선택하고 버튼을 눌러주세요!
        </div>
        <div class="check-btn-area" onclick="check_btn()">
            <button class="check-btn">확인하기</button>
        </div>
    </div>
    <div class="map_wrap">
        <ul class="info">
            <li>
                <img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiMwMEFGODAiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+'>
                100개 이상
            </li>
            <li>
                <img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiNGRkNFMDAiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+'>
                30~99개
            </li>
            <li>
                <img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiNGQjUyNTIiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+'>
                2~29개
            </li>
            <li>
                <img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJMYXllcl8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNMjU2LDBDMTUzLjc1NSwwLDcwLjU3Myw4My4xODIsNzAuNTczLDE4NS40MjZjMCwxMjYuODg4LDE2NS45MzksMzEzLjE2NywxNzMuMDA0LDMyMS4wMzUgICAgYzYuNjM2LDcuMzkxLDE4LjIyMiw3LjM3OCwyNC44NDYsMGM3LjA2NS03Ljg2OCwxNzMuMDA0LTE5NC4xNDcsMTczLjAwNC0zMjEuMDM1QzQ0MS40MjUsODMuMTgyLDM1OC4yNDQsMCwyNTYsMHogTTI1NiwyNzguNzE5ICAgIGMtNTEuNDQyLDAtOTMuMjkyLTQxLjg1MS05My4yOTItOTMuMjkzUzIwNC41NTksOTIuMTM0LDI1Niw5Mi4xMzRzOTMuMjkxLDQxLjg1MSw5My4yOTEsOTMuMjkzUzMwNy40NDEsMjc4LjcxOSwyNTYsMjc4LjcxOXoiIGRhdGEtb3JpZ2luYWw9IiMwMDAwMDAiIGNsYXNzPSJhY3RpdmUtcGF0aCIgZGF0YS1vbGRfY29sb3I9IiMwMDAwMDAiIHN0eWxlPSJmaWxsOiM4NDg0ODQiPjwvcGF0aD4KCTwvZz4KPC9nPjwvZz4gPC9zdmc+'>
                재고없음
            </li>
            <li class="mask-chk" onclick="view()">
                <img src='data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMjEuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDUxMiA1MTIiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMiA1MTI7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEycHgiIGhlaWdodD0iNTEycHgiPgo8Zz4KCTxwYXRoIGQ9Ik0zNDYuOTM5LDE5NS41OWMtMzEuODY3LTEuOTk5LTYwLjEyNC0xMS4zLTgxLjcxNy0yNi44OTZjLTUuMTc2LTMuNzM4LTEyLjE1LTMuNzkxLTE3LjM4Mi0wLjEzICAgYy0yMi40MjUsMTUuNjkxLTUxLjA1LDI1LjAzNy04Mi43NzksMjcuMDI2Yy04LjI2OCwwLjUxOS0xNC41NSw3LjY0Mi0xNC4wMzIsMTUuOTFjMC40OTksNy45NDgsNy4xMDEsMTQuMDYyLDE0Ljk1NywxNC4wNjIgICBjMC4zMTUsMCwwLjYzNC0wLjAxLDAuOTUzLTAuMDNjMzMuMzI0LTIuMDksNjMuOTk3LTExLjI4MSw4OS4zODgtMjYuNzA0YzI0Ljc4MiwxNS40MTIsNTUuMjI0LDI0LjYwMiw4OC43MzQsMjYuNzA0ICAgYzguMjcsMC41MDksMTUuMzkxLTUuNzY0LDE1LjkxLTE0LjAzMUMzNjEuNDg5LDIwMy4yMzIsMzU1LjIwNywxOTYuMTA5LDM0Ni45MzksMTk1LjU5eiIgZmlsbD0iIzAwMDAwMCIvPgoJPHBhdGggZD0iTTMxNiwyNTZIMTk2Yy04LjI4NCwwLTE1LDYuNzE2LTE1LDE1czYuNzE2LDE1LDE1LDE1aDEyMGM4LjI4NCwwLDE1LTYuNzE2LDE1LTE1UzMyNC4yODQsMjU2LDMxNiwyNTZ6IiBmaWxsPSIjMDAwMDAwIi8+Cgk8cGF0aCBkPSJNNTEyLDI0MWMwLTU3Ljg5Ny00Ny4xMDMtMTA1LTEwNS0xMDVoLTQ2LjgzNWMtMjQuNzkzLDAtNDYuMDk5LTUuNzM5LTYxLjYxNi0xNi41OTZDMjg2LjAxNiwxMTAuNjM1LDI3MS4zMDIsMTA2LDI1NiwxMDYgICBjLTE1LjMwMiwwLTMwLjAxNSw0LjYzNS00Mi41NDksMTMuNDA0QzE5Ny45MzQsMTMwLjI2MSwxNzYuNjI4LDEzNiwxNTEuODM1LDEzNkgxMDVDNDcuMTAzLDEzNiwwLDE4My4xMDMsMCwyNDEgICBjMCw1Ni4wMTYsNDQuMDkzLDEwMS45MjYsOTkuMzkzLDEwNC44NUMxMTYuMjQ3LDM1OS40MjcsMTc5LjYyOSw0MDYsMjU2LDQwNmM3Ni4zNywwLDEzOS43NTMtNDYuNTczLDE1Ni42MDYtNjAuMTUgICBDNDY3LjkwNywzNDIuOTI2LDUxMiwyOTcuMDE2LDUxMiwyNDF6IE0zMCwyNDFjMC0zNi4yMTksMjUuODA4LTY2LjUyMiw2MC03My40OTF2MTQ2Ljk4MUM1NS44MDgsMzA3LjUyMiwzMCwyNzcuMjE5LDMwLDI0MXogICAgTTEyMCwzMjMuODYyVjE2NmgzMS44MzVjMzEuNDIsMCw1Ny45MzctNy40MDcsNzguODE0LTIyLjAxNUMyMzguMTE2LDEzOC43NjEsMjQ2Ljg4MiwxMzYsMjU2LDEzNiAgIGM5LjExOCwwLDE3Ljg4NCwyLjc2MSwyNS4zNTEsNy45ODVDMzAyLjIyOSwxNTguNTkzLDMyOC43NDUsMTY2LDM2MC4xNjUsMTY2SDM5MnYxNTcuODYyQzM3NS4yOTcsMzM2Ljg1NiwzMTkuNjg0LDM3NiwyNTYsMzc2ICAgUzEzNi43MDMsMzM2Ljg1NiwxMjAsMzIzLjg2MnogTTQyMiwzMTQuNDkxVjE2Ny41MDljMzQuMTkyLDYuOTY4LDYwLDM3LjI3MSw2MCw3My40OTFTNDU2LjE5MiwzMDcuNTIyLDQyMiwzMTQuNDkxeiIgZmlsbD0iIzAwMDAwMCIvPgoJPHBhdGggZD0iTTI4NiwzMTZoLTYwYy04LjI4NCwwLTE1LDYuNzE2LTE1LDE1czYuNzE2LDE1LDE1LDE1aDYwYzguMjg0LDAsMTUtNi43MTYsMTUtMTVTMjk0LjI4NCwzMTYsMjg2LDMxNnoiIGZpbGw9IiMwMDAwMDAiLz4KPC9nPgoKCgoKCgoKCgoKCgoKCgo8L3N2Zz4K'>
                <div class="hid">구매 가능 여부</div>
                <div class="mask-check-msg">확인하기</div>
            </li>
        </ul>
        <div class="search-warp">
            <div class="search">
                <input type="text" id="search" class="place" onkeyup="enterkey();" placeholder="장소를 검색하세요" autocomplete="off">
                <button class="search-btn" onclick="buttonPress();">
                    <img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIGlkPSJDYXBhXzEiIHg9IjBweCIgeT0iMHB4IiB2aWV3Qm94PSIwIDAgNTExLjk5OSA1MTEuOTk5IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1MTEuOTk5IDUxMS45OTk7IiB4bWw6c3BhY2U9InByZXNlcnZlIiB3aWR0aD0iNTEyIiBoZWlnaHQ9IjUxMiI+PGc+PGc+Cgk8Zz4KCQk8cGF0aCBkPSJNNTA4Ljg3NCw0NzguNzA4TDM2MC4xNDIsMzI5Ljk3NmMyOC4yMS0zNC44MjcsNDUuMTkxLTc5LjEwMyw0NS4xOTEtMTI3LjMwOWMwLTExMS43NS05MC45MTctMjAyLjY2Ny0yMDIuNjY3LTIwMi42NjcgICAgUzAsOTAuOTE3LDAsMjAyLjY2N3M5MC45MTcsMjAyLjY2NywyMDIuNjY3LDIwMi42NjdjNDguMjA2LDAsOTIuNDgyLTE2Ljk4MiwxMjcuMzA5LTQ1LjE5MWwxNDguNzMyLDE0OC43MzIgICAgYzQuMTY3LDQuMTY1LDEwLjkxOSw0LjE2NSwxNS4wODYsMGwxNS4wODEtMTUuMDgyQzUxMy4wNCw0ODkuNjI3LDUxMy4wNCw0ODIuODczLDUwOC44NzQsNDc4LjcwOHogTTIwMi42NjcsMzYyLjY2NyAgICBjLTg4LjIyOSwwLTE2MC03MS43NzEtMTYwLTE2MHM3MS43NzEtMTYwLDE2MC0xNjBzMTYwLDcxLjc3MSwxNjAsMTYwUzI5MC44OTYsMzYyLjY2NywyMDIuNjY3LDM2Mi42Njd6IiBkYXRhLW9yaWdpbmFsPSIjMDAwMDAwIiBjbGFzcz0iYWN0aXZlLXBhdGgiIHN0eWxlPSJmaWxsOiM5MDkwOTAiIGRhdGEtb2xkX2NvbG9yPSIjMDAwMDAwIj48L3BhdGg+Cgk8L2c+CjwvZz48L2c+IDwvc3ZnPg=='>
                </button>
            </div>
        </div>
        <div id="map" class="map"></div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/js/mask/map.js"></script>
</div>
</body>
</html>