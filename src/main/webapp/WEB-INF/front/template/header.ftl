<!-- head start-->
    <div class="navbar navbar-fixed-top">
              <div class="navbar-inner">
                <div class="container">
                  <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </a>
                  <a class="brand" href="/" style="padding: 5px 20px 5px;">
                    <img class="img-rounded" width="30" height="30" alt="" src="${ctx}/resources/bootstrap/ico/login.png">
                                                                     杰森博客
                  </a>
                  <div class="nav-collapse collapse navbar-responsive-collapse">
                    <ul class="nav">
                      <li class="active"><a href="#">首页</a></li>
                      <li><a href="#">博客</a></li>
                      <!--<li><a href="#">团购导航</a></li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="#">Action</a></li>
                          <li><a href="#">Another action</a></li>
                          <li><a href="#">Something else here</a></li>
                          <li class="divider"></li>
                          <li class="nav-header">Nav header</li>
                          <li><a href="#">Separated link</a></li>
                          <li><a href="#">One more separated link</a></li>
                        </ul>
                      </li>-->
                    </ul>
                    <form class="navbar-search pull-left" action="">
                      <input type="text" class="search-query span2" placeholder="搜索标签">
                      <button type="submit" class="btn">搜索</button>
                    </form>
                    <ul class="nav pull-right">
                      <!--<li><a href="#">Link</a></li>-->
                      <li class="divider-vertical"></li>
                      
                      <@shiro.authenticated>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><@shiro.principal></@shiro.principal><b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="#">我的博客</a></li>
                          <li><a href="#">我的资料</a></li>
                          <li><a href="#">设置</a></li>
                          <li class="divider"></li>
                          <li><a href="${ctx}/logout?service=<@jason_properties key='sso.currurlpre' default=''/>/shiro-cas">退出</a></li>
                        </ul>
                      </li>
                      </@shiro.authenticated>
                      <@shiro.notAuthenticated>
                        <li>
                            <a rel="nofollow" href="<@jason_properties key='sso.loginurlpre' default=''/>/login?service=<@jason_properties key='sso.currurlpre' default=''/>/shiro-cas">
                              <span class="label label-important">请登陆!</span></a>
                        </li>
                        
                      </@shiro.notAuthenticated>
                      
                    </ul>
                  </div><!-- /.nav-collapse -->
                </div>
              </div><!-- /navbar-inner -->
            </div>
    <!-- head end-->