/**
 * 
 */
$(function(){
	$("#index_sel").change(function(){
		let selval = $("#index_sel").val();
		window.location.href = "/weather/viewByCityId/" + selval;
	});
});