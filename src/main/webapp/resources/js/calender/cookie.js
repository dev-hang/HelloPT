


//---------------------------------------

function notice_setCookie(name, value, expiredays) {
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);
	document.cookie = name + "=" + escape(value) + "; path=/; expires="
			+ todayDate.toGMTString() + ";"
}

function popup_closeToday() {
	if (document.forms[0].todayPopup.checked)
		// 저장된 쿠키에 아래와 같은 이름이 있으면 1=하룻동안 공지창 열지 않음
		notice_setCookie("eventPopup", "done", 1); 
		self.close();
}

function popup_close() {
	// window.close();
	self.close();
}

function na_call(str) {
	eval(str);
}

// 이벤트 페이지 이동
function pageChange() {
 //opener.parent.location.reload();
 opener.document.location.href="/hellopt/calender"
 window.close();
};