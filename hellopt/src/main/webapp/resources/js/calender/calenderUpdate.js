
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

	 $.ajax({
         type: "POST",
         enctype: 'multipart/form-data',
         url: "/hellopt/calendarUpdateOk",
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