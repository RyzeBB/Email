$(document).ready(function(){
   $("#uploadFormId")
   .on("click",".btn-upload-empinfo,.btn-upload-salary",doUpload);

});
function doUpload(){
	debugger
	//异步提交表单($.ajaxSubmit为异步提交表单)
	//使用此函数时需要在页面引入(jquery.form.js )
	var url;
	if($(this).hasClass("btn-upload-empinfo")){
		url  = "attachment/empinfo.do"
	}
	if($(this).hasClass("btn-upload-salary")){
		url = "attachment/salary.do"
	}
	$("#uploadFormId").ajaxSubmit({
		type:"post",
		url:url,
		dataType:"json",
		success:function(result){
		alert(result.message);
		}
	});
	return false;//防止表单重复提交的一种方式
}
