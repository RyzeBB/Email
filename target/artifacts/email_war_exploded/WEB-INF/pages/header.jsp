<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>宁夏</b>航信</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>宁 夏 航 信</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <!-- Tasks: style can be found in dropdown.less -->
          <li class="dropdown tasks-menu">
            <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger"></span>
            </a>--%>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <shiro:user>
                <span class="hidden-xs">欢迎您,<span id="userId">${sessionScope.currentUser.unittax}<span></span>
              </shiro:user>
              <shiro:guest>
                <span class="hidden-xs">欢迎您<span id="guestId"></span></span>
              </shiro:guest>
              <%--<shiro:user>
                <span class="hidden-xs">欢迎您,<shiro:principal></shiro:principal></span>
              </shiro:user>
              <shiro:guest>
                <span class="hidden-xs">欢迎您</span>
              </shiro:guest>--%>


            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <shiro:user>
              <li class="user-header">
                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                <p>
                  会员信息中心
                  <small id="current-time">Member since Nov. 2016</small>
                </p>
              </li>

              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">

                  <%--<div class="col-xs-4 text-center">
                    <a href="#">个人信息</a>
                  </div>--%>
                  <div class="col-xs-4 text-center col-xs-offset-2">
                    <a id="pwdid">修改密码</a>
                  </div>
                  <div class="col-xs-4 col-xs-offset-1 text-center">
                    <a href="logout.do">退出</a>
                  </div>
                </div>
              </li>
              </shiro:user>
              <!-- Menu Footer-->
             <%-- <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">修改密码</a>
                </div>
                <div class="pull-right">
                  <a href="logout.do" class="btn btn-default btn-flat">退出</a>

                </div>
              </li>--%>

            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
<script>
    setInterval(function() {
        var now = (new Date()).toLocaleString();
        $('#current-time').text(now);
    }, 1000);

  $(function () {
      $("#pwdid").on("click",changepwd);
      function changepwd() {
          debugger
          var title = "修改密码";
          $(".cl").text("取消");
          $(".ok").text("确认");
          var url = "${basePath}/information/changepwd.do";
          $("#modal-dialog .modal-body")
              .load(url,function(){//异步加载完成回调此函数
                  //设置标题内容
                  $("#myModalLabel").html(title);
                  //显示模态框(index.jsp中)
                  $("#modal-dialog").modal("show");
              });

      }
  })

</script>