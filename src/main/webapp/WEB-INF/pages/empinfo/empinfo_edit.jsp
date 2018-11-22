<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form  class="form-horizontal" role="form" id="editFormId">
	<div class="form-group">
		<label for="empcode" class="col-sm-2 control-label" >员工代码:</label>
	    <div class="col-sm-10">
			<input type="text" class="form-control" name="empcode" id="empcode"  placeholder="请输入员工代码">
	    </div>
	</div>
	<div class="form-group">
		<label for="empname" class="col-sm-2 control-label" >姓名</label>
		<div class="col-sm-10">
			<input type="text" class="form-control required" name="empname" id="empname" >
		</div>
	</div>

	<div class="form-group">
		<label for="departname" class="col-sm-2 control-label" >所在部门</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="departname" id="departname" >
		</div>
	</div>
	<div class="form-group">
		<label  class="col-sm-2 control-label">邮箱:</label>
		<div class="col-sm-10">
			<input type="email" class="form-control required" autocomplete="off"  name="email" id="email">
		</div>
	</div>
</form>
<script type="text/javascript" src="${basePath}/ttms/salary/salary_edit.js"></script>