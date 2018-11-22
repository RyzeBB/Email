<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form  class="form-horizontal" role="form">
	<div class="modal-body">
		<p>您确认要删除该条信息吗？</p>
	</div>
</form>
<script>
	$(document).ready(function () {
        $("#md").on("click","#oo",doDeleteObject);
    });

    function doDeleteObject(){
        debugger
        var id=$("#md").data("id");
        var params;
        if(id){
            params={id:id}
		}
        var url="emp/dodeleteObject.do";
        $.post(url,params,function(result){
            $("#md").modal("hide");
            if(result.state==1){
                doGetObjects();
            }else if(result.state==2){
                alert(result.message);
            }
        });
    }
</script>
