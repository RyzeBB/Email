<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/ttms/attach/attach.js"></script>
<div class="container">
    <!-- 页面导航 -->
    <div class="page-header" style="margin-bottom: 0px;padding-bottom: 0px">
        <div class="page-title">
            <ol class="breadcrumb">
                <li class="active">工资发放</li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-6">
                <form method="post" id="uploadFormId"
                      enctype="multipart/form-data"><!-- bixude -->
                    <!-- 查询表单 -->
                    <div class="col-md-12">
                        <ul class="list-unstyled list-inline">
                            <li><h4>邮箱信息：</h4></li>
                            <li><input type="file" name="mFile" class="form-control"></li>
                            <li class='O1'>
                                <button type="button" class="btn btn-primary btn-upload-empinfo">上传</button>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-12">
                        <ul class="list-unstyled list-inline">
                            <li><h4>工资信息：</h4></li>
                            <li><input type="file" name="mFile" class="form-control"></li>
                            <li class='O1'>
                                <button type="button" class="btn btn-primary btn-upload-salary">上传</button>
                            </li>
                        </ul>
                    </div>
                </form>
            </div>
            <div class="col-md-6" >
                <div class="col-md-12" style="height: 80px">
                    <div class="col-md-8 center-block" style="height:40px;margin:20px auto" >
                        <p>
                            <button class="btn btn-block btn-primary">发送</button>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>






