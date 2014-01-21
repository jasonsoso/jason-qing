<#include "common-taglibs.ftl" />
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!--<link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">-->

    <title>小担小学六（2）班</title>

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
              <li title="列表"  class="icon index_collection_link ">
                <a class="pjax" href="${ctx }/contact/list">
                  <i class="icon-th-list"></i>
                  <span>列表</span>
                </a>
              </li>
              <li title="新增"  class="icon new_collection_link active">
                <a class="pjax" href="${ctx }/contact/create">
                  <i class="icon-plus"></i>
                  <span>新增</span>
                </a>
              </li>
            </ul>
            

      <form class="form-signin" role="form">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" placeholder="Email address" required autofocus>
        <input type="password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
      
      <!--
      <form:form  class="form-horizontal" method="post" modelAttribute="contact">
                <input type="hidden" name="_method" value="${_method}"></input>
              <div class="control-group">
                <label class="control-label-width" for="title">*姓名</label>
                <div class="controls">
                    <form:input path="name"  placeholder="name" cssStyle="width: 600px"/>
                    <form:errors path="name" cssClass="formError"/>
                    <p class="help-block">必填.</p>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label-width" for="title">*手机号码</label>
                <div class="controls">
                    <form:input path="mobile"  placeholder="mobile" cssStyle="width: 600px"/>
                    <form:errors path="mobile" cssClass="formError"/>
                    <p class="help-block">必填.</p>
                </div>
              </div>
              
              
              <div class="control-group">
                <div class="controls">
                  <button type="submit" class="btn">提交</button>
                  <button type="button" class="btn" onclick="javascript:history.go(-1);">返回</button>
                </div>
              </div>
            </form:form>
            -->

    
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
