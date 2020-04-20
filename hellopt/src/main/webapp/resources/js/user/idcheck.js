function idCheck() {
    let httpRequest = new XMLHttpRequest();
    let userId = document.getElementById('userId').value;
    let pattern = /[0-9a-z-_]{5,20}/g;
    
    if(!userId.match(pattern)) {
    	alert("올바르지 않은 아이디 형식입니다.");
    	return;
    } else {
    	httpRequest.open('POST', "/user/idcheck")
        httpRequest.responseType = 'json';
        httpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        httpRequest.send(JSON.stringify({"userId" : userId}));
        httpRequest.onload = function() {
            let result = httpRequest.response;
            let resultLabel = document.getElementById('idCheckResult');
            if(result == 1) {
                resultLabel.innerHTML = "중복된 아이디 입니다."
            } else {
            	resultLabel.innerHTML = "사용 가능한 아이디 입니다."
            }
        }
    }
    
}

function formCheck() {
	idCheck();
	
	let result = document.getElementById('idCheckResult').innerHTML;
	if(result == "사용 가능한 아이디 입니다.") {
		let form = document.getElementById('user');
		form.submit();
	} else {
		alert("아이디를 확인해주십시오");
	}
}

function loadPreviewImg(img, previewName) {
	let isIE = (navigator.appName == "Microsoft Internet Explorer");
	let path = img.value;
	let ext = path.substring(path.lastIndexOf('.') + 1).toLowerCase();
	
	if(ext == "gif" || ext =="jpeg" || ext == "jpg" || ext == "png") {
		let preview = document.getElementById(previewName);
		if(isIE) {
			preview.src = path;
		} else {
			if(img.files[0]) {
				let reader = new FileReader();
				reader.onload = function (e) {
					preview.src = e.target.result;
				}
				reader.readAsDataURL(img.files[0]);
			}
		}
	} else {
		alert("올바르지 않는 이미지 파일 형식입니다.")
	}
}

let userId = document.getElementById("userId");
let idInfo = document.getElementById("id-info");
userId.addEventListener("focus", (event) => {
	idInfo.innerText = "영어 소문자, 숫자, -, _로 이루어진 5-20자 문자를 아이디로 사용할 수 있습니다.";
});

let userPw = document.getElementById("userPw");
userPw.addEventListener("focus", (event) => {
	document.getElementById("pw-info").innerText 
	= "영어 대소문자, 숫자, 특수문자 !, @, #, $, %, ^, &, *, (, ), -, _로 이루어진 5-20자 문자를 비밀번호로 사용할 수 있습니다.";
});

let userPwConfirm = document.getElementById("userPwConfirm");
userPwConfirm.addEventListener("change", (event) => {
	let info = document.getElementById("pw-cfm-info");
	let pattern = /[\w!@#$%^&*()-_]{5,20}/g;
	if(userPw.value.match(pattern)) {
		if(userPw.value != userPwConfirm.value) {
			info.innerText = "비밀번호가 일치하지 않습니다.";
		} else {
			info.innerText = "";
		}
	} else {
		info.innerText = "올바르지 않은 비밀번호 형식입니다.";
	}
});

userPw.addEventListener("change", (event) => {
	let info = document.getElementById("pw-cfm-info");
	let pattern = /[\w!@#$%^&*()-_]{5,20}/g;
	if(userPw.value.match(pattern)) {
	if(userPw.value != userPwConfirm.value) {
		info.innerText = "비밀번호가 일치하지 않습니다.";
	} else {
		info.innerText = "";
	}
	} else {
		info.innerText = "올바르지 않은 비밀번호 형식입니다.";
	}
});
