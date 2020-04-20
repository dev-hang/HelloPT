
/*
	$(".calInsert").click(function(){
		
	//document.calInsert.submit();	
	
	$("#formId").submit();
	
		setTimeout (function(){
	
			opener.parent.location.reload();
			window.close();
	
		},1000);

	});
*/

	$(".delCal").click(function(){
	
	 // Create an FormData object 
	var idx = document.getElementById("delIdx").value;
	
	 $.ajax({
	     type: "POST",
	     enctype: 'multipart/form-data',
	     url: "/hellopt/calendarDelete?calendarIdx="+idx,
	     data: idx,
	     processData: false,
	     contentType: false,
	     cache: false,
	     timeout: 0,
	     success: function (idx) {
	    	 opener.parent.location.reload();
				 window.close();
	     },
	     error: function (e) {
	         alert(" 등록실패 ");
	     }
	 });
	 
	});