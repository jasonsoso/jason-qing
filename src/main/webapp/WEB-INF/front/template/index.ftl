<#include "common-taglibs.ftl" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>杰森博客</title>
    <#include "common-header.ftl" />
    <style>
body {
    margin: 0;
    padding: 50px 0;
}

.lead { padding: 40px 0; }

#posts { margin: 30px auto 0; }
.post {
    margin: 0 0 50px;
}

@media (min-width: 940px) {
    .container > #posts .post { width: 268px; }
}
 

@media (min-width: 768px) and (max-width: 979px) {
    .container-fluid > #posts .post { width: 280px; }
    .container-fluid > #posts .post.cs2 { width: 610px; }
    .container > #posts .post { width: 337px; }
    .container > #posts .post.cs2 { width: 100%; }
}
 

@media (max-width: 767px) {
    body { padding: 50px 20px; }
    .post, .post.cs2 { width: 100%; }
}
    </style>
    

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
                    <img src="http://wrongwaycn.github.com/bootstrap/docs/assets/img/bootstrap-mdo-sfmoma-03.jpg" alt="">
                    <div class="carousel-caption">
                      <h4>First Thumbnail label</h4>
                      <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    </div>
                  </div>
                  <div class="item active">
                    <img src="http://wrongwaycn.github.com/bootstrap/docs/assets/img/bootstrap-mdo-sfmoma-01.jpg" alt="">
                    <div class="carousel-caption">
                      <h4>Second Thumbnail label</h4>
                      <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    </div>
                  </div>
                  <div class="item">
                    <img src="http://wrongwaycn.github.com/bootstrap/docs/assets/img/bootstrap-mdo-sfmoma-02.jpg" alt="">
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
        <img src="${ctx}/resources/images/default.png" width="42" height="42" class="avatar" style="float:left">
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
    <script src="http://cdnjs.cloudflare.com/ajax/libs/masonry/3.0.0/masonry.pkgd.js"></script>
    <script>
// Load is used to ensure all images have been loaded, impossible with document
jQuery( window ).load( function() {
    // Takes the gutter width from the bottom margin of .post
    var gutter = parseInt( jQuery( '.post' ).css( 'marginBottom' ) );
    var container = jQuery( '#posts' );
    // Creates an instance of Masonry on #posts

    container.masonry({
        gutter: gutter,
        itemSelector: '.post',
        columnWidth: '.post'
    });
    // This code fires every time a user resizes the screen and only affects .post elements
    // whose parent class is .container-fluid. Triggers resize so nothing looks weird.
    jQuery( window ).bind( 'resize', function(){
        if ( jQuery( '#posts' ).parent().hasClass( 'container-fluid' ) ) {
            // Resets all widths to 'auto' to sterilize calculations
            post_width = jQuery( '.post' ).width() + gutter;
            jQuery( '.container-fluid #posts, body > .container-fluid' ).css( 'width', 'auto');
            // Calculates how many .post elements will actually fit per row. Could this code be cleaner?

            posts_per_row = jQuery( '#posts' ).innerWidth() / post_width;
            floor_posts_width = ( Math.floor( posts_per_row ) * post_width ) - gutter;
            ceil_posts_width = ( Math.ceil( posts_per_row ) * post_width ) - gutter;
            posts_width = ( ceil_posts_width > jQuery( '#posts' ).innerWidth() ) ? floor_posts_width : ceil_posts_width;
            if ( posts_width == jQuery( '.post' ).width() ) posts_width = '100%';
            
            // Ensures that all top-level .container-fluid elements have equal width and stay centered
            
            jQuery( '.container-fluid #posts, body > .container-fluid' ).css( 'width', posts_width );
            jQuery( 'body > .container-fluid' ).css({ 'margin': '0 auto' });
        }
    }).trigger( 'resize' );
});
    </script>
    
</body>
</html>