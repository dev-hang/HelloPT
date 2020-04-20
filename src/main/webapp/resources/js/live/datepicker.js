$(function() {
	$(".datepicker").datepicker({
		format : "yyyy/mm/dd",
		startDate : "2020/03/25",
		endDate : "2020/06/25",
		autoclose : true,
		language : "en"
	}).datepicker("update", new Date());

	// disable
	// $('.datepicker > .form-control').prop('disabled', true);

});