
/*
	$(".calInsert").click(function(){
		
	//document.calInsert.submit();	
	
	$("#formId").submit();
	
		setInterval(function(){
	
			opener.parent.location.reload();
			window.close();
	
		},1000);

	});
*/
$(".calInsert").click(function(){
	// Get form
	var form = $('#formId')[0];
	
	 // Create an FormData object 
	var data = new FormData(form);
	
	if($("#uploadFile").val().length==0) {
		alert("영상을 업로드하세요.");
		$("#uploadFile").focus();
		return false;
	};
	
	if($("#mComment").val().length==0) {
		alert("내용을 입력하세요.");
		$("#mComment").focus();
		return false;
	};

	 $.ajax({
         type: "POST",
         enctype: 'multipart/form-data',
         url: "/hellopt/calenderWriteOk",
         data: data,
         processData: false,
         contentType: false,
         cache: false,
         timeout: 0,
         success: function (data) {
        	 opener.parent.location.reload();
 			 window.close();
         },
         error: function (e) {
             alert(" 등록실패 ");
         }
     });
	 
});


