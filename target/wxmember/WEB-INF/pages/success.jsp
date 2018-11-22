<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>签到成功:您于${success.createTime}日完成${success.name}课程签到,剩余听课次数${success.learncount}</h1>
</body>
</html>
<%--
<script>
    function handDate(){
        var date = ${success.createTime};
        return  date.slice(0,date.indexOf("."));
    }
</script>--%>
