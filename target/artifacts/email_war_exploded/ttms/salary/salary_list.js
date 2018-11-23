$(document).ready(function(){
	$("#queryFormId").on("click", ".btn-search",doQueryObjects);
	$('#queryFormId')
	.on("click",".btn-add,.btn-update,.btn-delete",doShowEditDialog);
	doGetObjects();
	
});
function doShowEditDialog(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加员工信息";
        var url="emp/editUI.do";
        $("#modal-dialog .modal-body")
            .load(url,function(){
                $(".modal-title").html(title);
                $("#modal-dialog").modal("show");
            });
	}
	if($(this).hasClass("btn-update")){
		$("#modal-dialog").data("id",$(this).parent().parent().data("id"));
		title="修改员工信息"+$("#modal-dialog").data("id");
        var url="emp/editUI.do";
        $("#modal-dialog .modal-body")
            .load(url,function(){
                $(".modal-title").html(title);
                $("#modal-dialog").modal("show");
            });
	}
    if($(this).hasClass("btn-delete")){
        $("#md").data("id",$(this).parent().parent().data("id"));
        title="删除员工信息"+$("#md").data("id");
        var url = "emp/deleteUI.do";
        $("#md .modal-body")
            .load(url,function(){
                $("#md .modal-title").html(title);
                $("#md").modal("show");
            });
    }

}

function doQueryObjects(){
	$("#pageId").data("pageCurrent",1);
	doGetObjects();
}
function getQueryFormData(){
	var params={
		email:$("#searchNameId").val()
	};
	//console.log(JSON.stringify(params));
    return params;
}
function doGetObjects(){
	var url="emp/doFindObjects.do";
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent){
		pageCurrent=1;
	}
	var params=getQueryFormData();
	params.pageCurrent=pageCurrent;
    $.ajaxSettings.async = true;
    $.post(url,params,function(result){//result为一个json对象(JsonResult)
    	if(result.state==1){//成功
    	setTableBodyRows(result.data.list);
    	setPagination(result.data.pageObject);
    	}else{
    	alert(result.message);
    	}
    });	

}
function setTableBodyRows(result){
	var tBody=$("#tbodyId");
	tBody.empty();

	for(var i in result){
	var tr=$("<tr></tr>");
	tr.data("id",result[i].id);
	/*var firstTd='<td ><input type="checkbox" name="checkedItem" value="[id]"></td>';
	firstTd=firstTd.replace("[id]",result[i].id);
	tr.append(firstTd);*/
        tr.append("<td>"+result[i].empcode+"</td>");
	tr.append("<td>"+result[i].empname+"</td>");
        tr.append("<td>"+result[i].departname+"</td>");
        tr.append("<td>"+result[i].email+"</td>");
	tr.append("<td><button type='button' class='btn btn-default btn-update'>修改</button>" +
		"<button type='button' class='btn btn-default btn-delete'>删除</button></td>");
	tBody.append(tr);
	}
	
}

