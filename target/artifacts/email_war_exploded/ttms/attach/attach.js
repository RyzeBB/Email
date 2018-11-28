$(document).ready(function(){
   $("#uploadFormId")
   .on("click",".btn-upload-empinfo,.btn-upload-salary,.btn-upload-tc",doUpload);
   $("#sendform").on("click",".send,.sendtc",doSend);

});
function doUpload(){
	//异步提交表单($.ajaxSubmit为异步提交表单)
	//使用此函数时需要在页面引入(jquery.form.js )
	var url;
	if($(this).hasClass("btn-upload-empinfo")){
		url  = "attachment/empinfo.do";
	}
	if($(this).hasClass("btn-upload-salary")){
		url = "attachment/salary.do";
	}
	if($(this).hasClass("btn-upload-tc")){
		url = "attachment/tc.do";
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
function doSend() {
	var url;
	var sendType;
	if($(this).hasClass("send")){
        url = "attachment/send.do";
        sendType = "gz";
	}
	if($(this).hasClass("sendtc")){
		url = "attachment/sendtc.do";
		sendType = "tc";
	}
	var params={
		username:$("#username").val(),
		password:$("#password").val(),
		sendType:sendType
	};
	if(!params.username){
		alert("请输入邮箱");
		return;
	}
	if(!params.password){
		alert("请输入授权码");
		return;
	}

	/*{"state":1,"message":"OK","data":{"empTotal":3,"noSendTotal":1,
	"sendTotal":2,"noSendList":["zhahjsjfuuf0sfdsl@163.com"],"failReason":"zhahjsjfuuf0sfdsl@163.com--地址无效"}}*/
    $.ajaxSettings.async = true;
    $("#pg").removeClass("hidden");
    $.post(url,params,function(result){
        if(result.state==1){//
        	$("#pg").addClass("hidden");
            tiphandle(result.data);
            doFileDelete(sendType);
        }else{
            alert(result.message);
        }
    })
}

function tiphandle(result){
    $("#tip").empty().append($("<a style='text-decoration: none'>共计"+result.empTotal+"人,成功发送"+result.sendTotal+"封,失败发送"+result.noSendTotal+"封。<br></a>"))
		.append($("<a style='text-decoration: none'>"+(result.failReason==null?'':result.failReason)+"</a>"));

}
function doFileDelete(sendType) {
	debugger
    var url = (sendType==="gz"?"attachment/gzFleDelete.do":"attachment/tcFleDelete.do");
	var checked = (sendType==="gz" ? $("#check-gz").is(':checked') : $("#check-tc").is(':checked'));
    var param ={checked:checked};
    if(!param.checked){
    	return;
	}
    $.ajaxSettings.async = true;
    $.post(url,param,function (result) {
    	if(result.state!==1){
    		alert(result.message);
    	}
    })
}