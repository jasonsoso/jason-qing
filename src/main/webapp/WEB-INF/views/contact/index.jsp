<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!--<link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">-->

    <title>Sticky Footer Navbar Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/resources/bootstrap-3.0.3/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${ctx }/resources/css/contact.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="${ctx }/resources/bootstrap-3.0.3/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${ctx }/resources/bootstrap-3.0.3/js/html5shiv.js"></script>
      <script src="${ctx }/resources/bootstrap-3.0.3/js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Wrap all page content here -->
    <div id="wrap">

      <!-- Fixed navbar -->
      <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${ctx }/contact">小担小学六(2)班</a>
          </div>
          <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
              <li class="active"><a href="${ctx }/contact">首页</a></li>
              <li><a href="${ctx }/contact/list">通讯录</a></li>
              <!--<li><a href="#contact">历史聚会</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="dropdown-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>-->
            </ul>
            <!--
            <ul class="nav navbar-nav navbar-right">
                <@shiro.authenticated>
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><@shiro.principal></@shiro.principal><b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="${ctx}/logout?service=<@jason_properties key='sso.currurlpre' default=''/>/shiro-cas">退出</a></li>
                        </ul>
                    </li>
                </@shiro.authenticated>
                <@shiro.notAuthenticated>
                <li>
                    <a rel="nofollow" href="<@jason_properties key='sso.loginurlpre' default=''/>/login?service=<@jason_properties key='sso.currurlpre' default=''/>/shiro-cas">
                                                                请登陆!</a>
                </li>
                </@shiro.notAuthenticated>
            </ul>-->
          
          </div><!--/.nav-collapse -->
        </div>
      </div>

      <!-- Begin page content -->
      <div class="container">
        <div class="page-header">
          <h1>${article.title}</h1>
        </div>
        
        <p>
                <!-- content start-->
                ${article.content}
                <!-- content end-->
              </p>
              
              
        </div>
    </div>

    <div id="footer">
      <div class="container">
        <p class="text-muted">小担小学六（2）班聚会组委会</p>
      </div>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${ctx }/resources/bootstrap-3.0.3/js/jquery-1.10.2.min.js"></script>
    <script src="${ctx }/resources/bootstrap-3.0.3/js/bootstrap.min.js"></script>
  </body>
</html>
