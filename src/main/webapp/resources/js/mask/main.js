// 연도 동적으로 추가하는 함수
//onload는 window.onload로 받아야 함
window.onload = function appendYear() {
    var date = new Date();
    var year = date.getFullYear();
    console.log("!!!!!!!!"+year);
    for (var i = year; i >= 1900; i--) {
    	console.log("for문안쪽");
        $('#check-year').append('<option value="' + i + '">' + i + '년생</option>');
    }
}

// 요일을 구하는 함수
function WhatDay() {
    var d = new Date();
    var date = d.getDay();

    return date;
}

// 마스크 구매 가능 여부를 확인하는 함수
function check_mask(year, day) {
    if (year === 'x') {
        return null;
    }

    if (day === 6 || day === 0) {
        return true;
    }

    var mask = new Array(10);

    mask[0] = '5';
    mask[1] = '1';
    mask[2] = '2';
    mask[3] = '3';
    mask[4] = '4';
    mask[5] = '5';
    mask[6] = '1';
    mask[7] = '2';
    mask[8] = '3';
    mask[9] = '4';

    if (mask[year] == day) {
        return true;
    } else {
        return false;
    }
}

// 마스크 구매 가능 여부 확인창에서 버튼을 눌렀을 때 호출되는 함수
function check_btn() {
    var ch = $('#check-year option:selected').val();
    var year = ch.substr(3, 1);
    var day = WhatDay();
    var msg = document.getElementById('msg');
    var message = '';

    var result = check_mask(year, day);

    if (result === null) {
        message = "출생연도를 선택해주세요!";
    } else if (result) {
        message = "마스크 구매 가능한 날입니다!";
    } else {
        message = "마스크를 구매할 수 없는 날입니다!";
    }

    msg.innerHTML = message;
}

// 마스크 구매 가능 여부 확인창에서 'x' 버튼을 눌렀을 때 호출되는 함수
function cancel() {
    var bg = document.getElementById('check-bg');
    var check = document.getElementById('check');

    bg.style.display = 'none';
    check.style.display = 'none';
}

// 마커 색 안내 탭에서 확인하기 메뉴를 눌렀을 때 호출되는 함수
function view() {
    var bg = document.getElementById('check-bg');
    var check = document.getElementById('check');

    bg.style.display = 'block';
    check.style.display = 'block';
}
