<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>杰森博客 管理后台</title>
    <%@include file="/common/taglibs.jsp" %>
	<%@include file="/common/common-header.jsp" %>
 </head>

  <body>
	<%@include file="/WEB-INF/views/admin/header.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">
        <%@include file="/WEB-INF/views/admin/left.jsp" %>
        <div class="span9">
          	<c:if test="${not empty message}">
                 <div class="alert alert-${message.type }">
                    <a class="close" data-dismiss="alert" href="#">×</a>
                     ${message.text }
                </div>
            </c:if>
			<!-- 面包屑 -->
			<ul class="breadcrumb">
			  <li>
			    <a href="${ctx }/admin">首页</a> <span class="divider">/</span>
			  </li>
			  <li>
			    <a href="${ctx }/article/list">文章管理</a> <span class="divider">/</span>
			  </li>
			  <li id="breadcrumb_active" class="active">新增/修改</li>
			</ul>
			<!-- 操作 -->
			<ul class="nav nav-tabs">
	          <li title="列表"  class="icon index_collection_link ">
	            <a class="pjax" href="${ctx }/article/list">
	              <i class="icon-th-list"></i>
	              <span>列表</span>
	            </a>
	          </li>
	          <li id="createId" title="新增"  class="icon new_collection_link active">
	            <a class="pjax" href="${ctx }/article/create">
	              <i class="icon-plus"></i>
	              <span>新增</span>
	            </a>
	          </li>
	          <li id="editId" title="编辑"  class="icon new_collection_link active">
	            <a class="pjax" >
	              <i class="icon-pencil"></i>
	              <span>编辑</span>
	            </a>
	          </li>
			</ul>
			
			<form:form  class="form-horizontal" method="post" modelAttribute="article">
				<input type="hidden" name="_method" value="${_method}"></input>
			  <div class="control-group">
			    <label class="control-label-width" for="title">*标题</label>
			    <div class="controls">
			        <form:input path="title"  placeholder="title" cssStyle="width: 600px"/>
			        <form:errors path="title" cssClass="formError"/>
			    	<p class="help-block">必填.</p>
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label-width" for="value">*内容</label>
			    <div class="controls">
			      <form:textarea path="content" placeholder="value" cssStyle="width: 600px" />
			      <form:errors path="content" cssClass="formError"/>
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
          
        </div>
      </div>
	<%@include file="/WEB-INF/views/admin/footer.jsp" %>
    </div>
<%@include file="/common/common-footer.jsp" %>
<script src="${ctx }/resources/js/ueditor/ueditor.config.js"></script><!-- 配置文件 -->
<script src="${ctx }/resources/js/ueditor/ueditor.all.min.js"></script><!-- 核心包 發佈版本 修改為 ueditor_all_min.js -->
<script type="text/javascript">
	$(function(){
		load();
		//window.UEDITOR_HOME_URL = "${ctx }/resources/js/ueditor/";
		//var editor = new UE.ui.Editor({
		//	imagePath:"http://photo.jasonsoso.com/"
		//});      
		//editor.render("content");
		//1.2.4以后可以使用一下代码实例化编辑器     
		UE.getEditor('content');

		function load(){
			$('#article_list').addClass('active');
			var _method = $('input[name="_method"]').val();
			if(_method == 'put'){
				//当 _method == 'put' 的时候 为edit编辑
				$('#breadcrumb_active').text("编辑");
				$('#createId').hide();
			}else{
				//否则为新增
				$('#breadcrumb_active').text("新增");
				$('#editId').hide();
				
			}
		};
	});
</script>
  </body>
</html>
