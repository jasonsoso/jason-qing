<#include "common-taglibs.ftl" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>杰森博客</title>
    <#include "common-header.ftl" />
    <script src="${ctx }/resources/js/masonry.pkgd.js"></script>
    <script>
jQuery( window ).load( function() {
    var gutter = parseInt( jQuery( '.post' ).css( 'marginBottom' ) );
    var container = jQuery( '#posts' );

    container.masonry({
        gutter: gutter,
        itemSelector: '.post',
        columnWidth: '.post'
    });
    jQuery( window ).bind( 'resize', function(){
        if ( jQuery( '#posts' ).parent().hasClass( 'container-fluid' ) ) {
            post_width = jQuery( '.post' ).width() + gutter;
            jQuery( '.container-fluid #posts, body > .container-fluid' ).css( 'width', 'auto');

            posts_per_row = jQuery( '#posts' ).innerWidth() / post_width;
            floor_posts_width = ( Math.floor( posts_per_row ) * post_width ) - gutter;
            ceil_posts_width = ( Math.ceil( posts_per_row ) * post_width ) - gutter;
            posts_width = ( ceil_posts_width > jQuery( '#posts' ).innerWidth() ) ? floor_posts_width : ceil_posts_width;
            if ( posts_width == jQuery( '.post' ).width() ) posts_width = '100%';
            
            jQuery( '.container-fluid #posts, body > .container-fluid' ).css( 'width', posts_width );
            jQuery( 'body > .container-fluid' ).css({ 'margin': '0 auto' });
        }
    }).trigger( 'resize' );
});
    </script>
    
</head>
<body>
    <#include "header.ftl" /><!--head-->
    <!-- Posts -->
    
    <div class="container">
        <div class="row-fluid">
        <!--span12 start-->
        
        <div class="span12">
        
            <div class="bs-docs-example">
              <div id="myCarousel" class="carousel slide">
                <div class="carousel-inner">
                  <div class="item">
                    <img src="${ctx}/resources/images/bootstrap-mdo-sfmoma-03.jpg" alt="">
                    <div class="carousel-caption">
                      <h4>First Thumbnail label</h4>
                      <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    </div>
                  </div>
                  <div class="item active">
                    <img src="${ctx}/resources/images/bootstrap-mdo-sfmoma-01.jpg" alt="">
                    <div class="carousel-caption">
                      <h4>Second Thumbnail label</h4>
                      <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    </div>
                  </div>
                  <div class="item">
                    <img src="${ctx}/resources/images/bootstrap-mdo-sfmoma-02.jpg" alt="">
                    <div class="carousel-caption">
                      <h4>Third Thumbnail label</h4>
                      <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    </div>
                  </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
              </div>
            </div>
              
        </div>
        
      </div>
      <!--span12 end-->
    </div>
    </div>
    <div class="container">
    
        <div id="posts">
            
            <#list page.result as article>
<div class="post list-widget" style="padding: 5px;">
    <a target="_blank" href="${ctx}/${article.userInfo.username}" title="${article.userInfo.username}">
        <img src="${ctx}/resources/images/default.png" class="avatar" style="float:left">
    </a>
    <div class="list-head">
        <h3><a target="_blank" href="${ctx}/article/${article.id}">${article.title}</a></h3>
        <div class="list-meta">by <a target="_blank" href="${ctx}/${article.userInfo.username}">${article.userInfo.username}</a></div>
    </div>
    <div>
        ${article.summary}
    </div>
</div>
            </#list>
            
        </div>
    </div>
    
    <div class="pagination pagination-centered">
         <#assign pagingUrl="${ctx}/article">
         <#include  "page.ftl">
    </div>
    
    <#include "footer.ftl" /><!--foot-->
    
    <#include "common-footer.ftl" />
    
    
</body>
</html>