<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">

         <%-- <li class="active treeview">
            <a href="#">
              <i class="fa fa-dashboard"></i> <span>产品管理</span>
              <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
            </a>
            <ul class="treeview-menu">
              <li><a id="load-project-id"><i class="fa fa-circle-o"></i>项目管理</a></li>
              <li><a id="load-team-id"><i class="fa fa-circle-o"></i>团目管理</a></li>
              <li><a id="load-type-id"><i class="fa fa-circle-o"></i>产品分类</a></li>
              <li><a href="#"><i class="fa fa-circle-o"></i>产品管理</a></li>
              <li><a id="load-attachment-id"><i class="fa fa-circle-o"></i>附件管理</a></li>
            </ul>
          </li>
--%>

          <li class="active treeview">
            <a href="#">
              <i class="fa fa-dashboard"></i> <span>工资发放</span>
              <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
            </a>
            <ul class="treeview-menu">
                <li><a id="load-empinfo-id"><i class="fa fa-circle-o"></i>员工管理</a></li>
                <li><a id="load-attachment-id"><i class="fa fa-circle-o"></i>邮件群发</a></li>
            </ul>
          </li>

        <li class="treeview">
            <shiro:hasAnyRoles name="41,42">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>系统管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
            </shiro:hasAnyRoles>
          <ul class="treeview-menu">
            <shiro:hasAnyRoles name="42">
            <li><a href="#"><i class="fa fa-circle-o"></i>组织管理</a></li>
              <li><a id="load-menu-id"><i class="fa fa-circle-o"></i>菜单管理</a></li>
                  <li><a id="load-role-id"><i class="fa fa-circle-o"></i>角色管理</a></li>
            </shiro:hasAnyRoles>
            <li><a id="load-user-id"><i class="fa fa-circle-o"></i>用户管理</a></li>

          </ul>
        </li>

      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
<script type="text/javascript">
$('#load-role-id').click(function(){
	var url="role/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-menu-id').click(function(){
	var url="menu/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-user-id').click(function(){
	var url="user/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
//-----------------------------------------
$('#load-empinfo-id').click(function(){
    var url="emp/listUI.do?t="+Math.random(1000);
    $(".content").load(url);
})
$('#load-attachment-id').click(function(){
    var url="attachment/listUI.do?t="+Math.random(1000);
    $(".content").load(url);
})
</script>