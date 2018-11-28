<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
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
                <div class="col-md-4">
                    <ul class="list-unstyled list-inline">
                        <li><h4>邮箱信息：</h4></li>
                        <li><input type="file" name="mFile" style="width: 150px" class="form-control"></li>
                        <li class='O1'>
                            <button type="button" class="btn btn-primary btn-upload-empinfo">上传</button>
                        </li>
                    </ul>
                </div>
                <div class="col-md-4" >
                    <ul class="list-unstyled list-inline">
                        <li><h4>工资信息：</h4></li>
                        <li><input type="file" name="mFile" style="width: 150px" class="form-control"></li>
                        <li class='O1'>
                            <button type="button" class="btn btn-primary btn-upload-salary">上传</button>
                        </li>
                    </ul>
                </div>

                <div class="col-md-4" >
                    <ul class="list-unstyled list-inline">
                        <li><h4>提成信息：</h4></li>
                        <li><input type="file" name="mFile" style="width: 150px" class="form-control"></li>
                        <li class='O1'>
                            <button type="button" class="btn btn-primary btn-upload-tc">上传</button>
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
            <div class="form-inline" id="sendform" >
                <div class="form-group">
                    <label for="username">邮箱</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="email">
                </div>
                <div class="form-group">
                    <label for="password">授权码</label>
                    <input type="text" class="form-control" id="password" name="password" placeholder="password">
                </div>
                <button type="button" class="btn btn-default send">发送工资</button>
                &nbsp;&nbsp;
                <button type="button" class="btn btn-default sendtc">发送提成</button>&nbsp
                <label><input type="checkbox" id="check-gz" style="font-size:medium">发送完成后清除工资数据</label>&nbsp;
                <label><input type="checkbox" id="check-tc" style="font-size:medium">发送完成后清除提成数据</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="container hidden" id="pg" style="padding-top: 5px">
            <p>发送中...</p>
            <div class="progress">
                <div class="progress-bar progress-bar-success" id="progress" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
                    <span class="sr-only">100% 完成</span>
                </div>
            </div>
        </div>
    </div>

    <div class="row"  style="padding-top: 20px">
        <div class="col-md-12" >
            <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <div>
                    <span id="tip" >
                        <Strong>说明:<br></Strong>
                        1.员工管理可维护员工的email信息,请检查员工代码，邮箱是否唯一。<br>
                        2.上传的excel文件需包含员工代码字段，请确保和员工管理中维护的一样,否则会导致邮件数据错误。<br>
                        3.发送方请使用163邮箱。<br>2.申请163邮箱后，请设置pop3/smtp/imap,并设置授权码作为邮箱密码登录,具体可自行百度或联系管理员。<br>
                        4.邮件发送失败原因众多:地址无效,邮件反垃圾设置等等.建议接收方将发送方邮箱添加进白名单,接收方也可尝试从垃圾箱中找到<br>
                        5.发送完毕后请检查已发送邮件,针对发送失败或被服务器拒收的邮件,请手动完成重发。<br>
                        6.工资条文件规则：工资条excel文件,将工资数据存放在第一个工作表中；航信工资条重命名为01,外包工资条重命名为02,ca工资条重命名为03。 后缀为xls或xlsx。
                        7.邮箱文件规则同工资条文件名称规则。航信01,外包02,ca03。
                    </span>
                </div>

            </div>
        </div>

    </div>
<%--ddddddddddddd--%>

    <script>
        var num = 0;
        var timeID = setInterval(function() {
            $("#progress").css("width", num + "%")
            if(num >= 100) {
                clearInterval(timeID);
            } else {
                num += 1;
            }
        }, 100);
    </script>

</div>






