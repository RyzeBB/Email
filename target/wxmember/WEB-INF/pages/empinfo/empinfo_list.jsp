<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/ttms/salary/salary_list.js"></script>
<script type="text/javascript" src="${basePath}/ttms/common/page.js"></script>
<style>
	#tableid>thead>tr>th{
		border: 1px solid #ddd;
		text-align: center;
	}
	#tableid>tbody>tr>td{
		border: 1px solid #ddd;
		text-align: center;
		vertical-align: middle!important;
	}
</style>
 <!-- 表单 -->
	<div class="container">
	   <!-- 页面导航 -->
	   <div class="page-header">
			<div class="page-title" style="padding-bottom: 5px">
				<ol class="breadcrumb">
				  <li class="active">员工管理</li>
				</ol>
			</div>
			<div class="page-stats"></div>
		</div>
		<form method="post" id="queryFormId">
		    <!-- 查询表单 -->
			<div class="row page-search">
			 <div class="col-md-12">
				<ul class="list-unstyled list-inline">
					<li><input type="text" id="searchNameId" class="form-control" placeholder="邮箱号"></li>
					<li class='O1'><button type="button" class="btn btn-primary btn-search" >查询</button></li>
					<li class='O2'><button type="button" class="btn btn-primary btn-add">添加</button></li>

				</ul>
			  </div>
			</div>
			<!-- 列表显示内容 -->
			<div class="row col-md-12" style="margin-top: 20px;">
				<table class="table table-bordered" style='table-layout:fixed;' id="tableid">
					<thead>
						<tr >

							<th>员工代码</th>
							<th>姓名</th>
							<th>所在部门</th>
							<th>邮箱</th>
							<th>操作</th>
						</tr>
					</thead>
					<!-- ajax异步获得,并将数据填充到tbody中 -->
					<tbody id="tbodyId" >
					</tbody>
				</table>
          <%@include file="../common/page.jsp" %>
			</div>
		</form>
	</div>
<!-- Modal(模态框) -->
<div class="modal fade" id="md" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
			</div>
			<!-- 此位置放置具体页面的位置 -->
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" id="cc" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" id="oo" >确定</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- modal -->
