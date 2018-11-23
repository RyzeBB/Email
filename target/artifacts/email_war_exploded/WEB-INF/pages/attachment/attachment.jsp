<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/ttms/attach/attach.js"></script>
<div class="container" style="padding-top: 40px">
    <div class="row">
        <div class="col-md-12" style="padding-bottom: 20px">
            <h3><span class="label label-primary">文件上传</span></h3>
        </div>
        <div class="col-md-12">
            <form method="post" id="uploadFormId"
                  enctype="multipart/form-data"><!-- bixude -->
                <!-- 查询表单 -->
                <div class="col-md-6">
                    <ul class="list-unstyled list-inline">
                        <li><h4>邮箱信息：</h4></li>
                        <li><input type="file" name="mFile" class="form-control"></li>
                        <li class='O1'>
                            <button type="button" class="btn btn-primary btn-upload-empinfo">上传</button>
                        </li>
                    </ul>
                </div>
                <div class="col-md-6" style="float:right">
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
    </div>
    <div class="row">
        <div class="col-md-12" style="padding-bottom: 20px">
            <h3><span class="label label-primary">邮件发送</span></h3>
        </div>
        <div class="col-md-12">
            <form class="form-inline" id="sendform" action="/attachment/send.do">
                <div class="form-group">
                    <label for="username">邮箱</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="email">
                </div>
                <div class="form-group">
                    <label for="password">授权码</label>
                    <input type="text" class="form-control" id="password" name="password" placeholder="password">
                </div>
                <button type="submit" class="btn btn-default">校验</button>
                <button type="submit" class="btn btn-default">发送</button>
            </form>
        </div>
    </div>

</div>






