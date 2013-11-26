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
                                                                     杰森 轻博客
                  </a>
                  <div class="nav-collapse collapse navbar-responsive-collapse">
                    <ul class="nav">
                      <li class="active"><a href="http://www.jasonsoso.com">首页</a></li>
                      <li><a href="http://qing.jasonsoso.com">轻博客</a></li>
                      
                    </ul>
                    <form class="navbar-search pull-left" action="">
                      <input type="text" class="search-query span2" placeholder="搜索标签">
                      <button type="submit" class="btn">搜索</button>
                    </form>
                    <ul class="nav pull-right">
                      <li class="divider-vertical"></li>
                      <li><a href="${ctx}/article/create">发轻博客</a></li>
                      
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