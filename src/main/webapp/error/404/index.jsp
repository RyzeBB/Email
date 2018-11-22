<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>404</title>
    <!-- Bootstrap core CSS -->
    <link href="${basePath}/error/404/css/bootstrap.css" rel="stylesheet">
    <!-- FONT AWESOME CSS -->
    <link href="${basePath}/error/404/css/font-awesome.min.css" rel="stylesheet" />
    <!--GOOGLE FONT -->
 <link href='http://fonts.googleapis.com/css?family=Nova+Flat' rel='stylesheet' type='text/css'>
    <!-- custom CSS here -->
    <link href="${basePath}/error/404/css/style.css" rel="stylesheet" />
</head>
<body>
    
   
    <div class="container">
      
          <div class="row pad-top text-center">
                 <div class="col-md-6 col-md-offset-3 text-center">
                  <h1>  What have you done? </h1>
                   <h5> Now Go Back Using Below LInk</h5> 
              <span id="error-link"></span>
                     <h2>! ERROR DECETED !</h2>
</div>
        </div>

            <div class="row text-center">
                 <div class="col-md-8 col-md-offset-2">
                     
                     <h3> <i  class="fa fa-lightbulb-o fa-5x"></i> </h3>
               <a href="#" class="btn btn-primary">GO TO HOME PAGE</a> 

</div>
        </div>

    </div>
    <!-- /.container -->
  
  
    <!--Core JavaScript file  -->
    <script src="${basePath}/error/404/js/jquery-1.10.2.js"></script>
    <!--bootstrap JavaScript file  -->
    <script src="${basePath}/error/404/js/bootstrap.js"></script>
     <!--Count Number JavaScript file  -->
    <script src="${basePath}/error/404/js/countUp.js"></script>
       <!--Custom JavaScript file  -->
    <script src="${basePath}/error/404/js/custom.js"></script>
</body>
</html>
