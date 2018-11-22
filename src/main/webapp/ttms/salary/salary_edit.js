
$(document).ready(function(){
	$("#modal-dialog").on("click",".ok",doSaveOrUpdate);
	$("#modal-dialog").on("hidden.bs.modal",function(){
		$(this).off("click",".ok")
		       .removeData("id");
	});

	var id=$("#modal-dialog").data("id");
	if(id) {doFindObjectById(id);}
});
function doFindObjectById(id){
	var url="emp/doFindObjectById.do"
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
		 doInitEditFormData(result.data);	
		}else{
		 alert(result.message);
		}
	});
}
function doInitEditFormData(obj){
	$("#empcode").val(obj.empcode);
	$("#empname").val(obj.empname);
    $("#departname").val(obj.departname);
    $("#email").val(obj.email);

}

function doSaveOrUpdate(){
	debugger
	if(!$("#editFormId").valid())return;
	var params=getEditFormData();
	var id=$("#modal-dialog").data("id");
	if(id)params.id=id;
	var updateUrl="emp/doUpdateObject.do";
	var insertUrl="emp/doSaveObject.do";
    var url=id?updateUrl:insertUrl;
	$.post(url,params,function(result){
        $("#modal-dialog").modal("hide");
		if(result.state==1){
		 doGetObjects();
		}else if(result.state==2){
		 alert(result.message);
		}
	});
}
function getEditFormData(){
  var params={
	  "empcode":$("#empcode").val(),
	  "empname":$("#empname").val(),
	  "departname":$("#departname").val(),
	  "email":$("#email").val()
  }
  return params;
}




