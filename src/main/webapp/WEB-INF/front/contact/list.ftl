<#include "common-taglibs.ftl" />
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
              <li ><a href="${ctx }/contact">首页</a></li>
              <li class="active"><a href="${ctx }/contact/list">通讯录</a></li>
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
          <h1>通讯录</h1>
        </div>
        
            <p>
                <!-- content start-->
   <div class="bs-example">
   
            <!-- 操作 -->
            <ul class="nav nav-tabs">
              <li title="列表"  class="icon index_collection_link active">
                <a class="pjax" href="${ctx }/contact/list">
                  <i class="icon-th-list"></i>
                  <span>列表</span>
                </a>
              </li>
              <li title="新增"  class="icon new_collection_link ">
                <a class="pjax" href="${ctx }/contact/create">
                  <i class="icon-plus"></i>
                  <span>新增</span>
                </a>
              </li>
            </ul>
            
      <table class="table table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>姓名</th>
            <th>手机号码</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
            
            <#list page.result as contact>
              <tr>
                <td>1</td>
                <td>${contact.name}</td>
                <td>${contact.mobile}</td>
                <td>@mdo</td>
              </tr>
          </#list>
          
        </tbody>
      </table>
    </div>
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
