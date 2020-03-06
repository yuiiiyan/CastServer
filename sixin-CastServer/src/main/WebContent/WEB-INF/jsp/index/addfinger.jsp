<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>
	<meta charset="utf-8" />
  <title>访问与操作记录</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
  <!-- <link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all"> -->
  <meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<%-- <c:if test="${not empty fingers}"><meta http-equiv="refresh" content="5"></c:if> --%>
</head>
<body>

<div class="layui-card">
          <div class="layui-card-body">
            <form class="layui-form" action="show/${MSG}.do" name="taskForm" id="taskForm" lay-filter="component-form-element">
              <div class="layui-row layui-col-space10 layui-form-item">
                <div class="layui-col-lg6">
                  <label class="layui-form-label"  style="width: 100px;">用户ID：</label>
                  <div class="layui-input-block">
                    <input type="num" name="fingerid" id="fingerid" lay-verify="required" placeholder="请输入用户ID（勿与其他用户重复）" value = "${pd.fingerid}" <c:if test="${MSG == 'edit'}"> readonly="readonly" </c:if>autocomplete="off" class="layui-input">
                  </div>
                </div>
                <div class="layui-col-lg6">
                  <label class="layui-form-label"  style="width: 100px;">用户姓名：</label>
                  <div class="layui-input-block">
                    <input type="text" name="username" id="username" lay-verify="required" placeholder="请输入用户姓名" value = "${pd.username}" autocomplete="off" class="layui-input">
                  </div>
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label"  style="width: 100px;">备注：</label>
                <div class="layui-input-block">
                  <textarea name="other" placeholder="" name="note" id="note" class="layui-textarea"></textarea>
                </div>
              </div>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="component-form-element"  onclick="save();">立即提交</button>
                  <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
              </div>
            </form>
          </div>
        </div>
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="foot.jsp"%>
  <script src="static/layuiadmin/layui/layui.js?t=1"></script>  
  <script>
  layui.config({
    base: 'static/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console']);
  layui.use(['form', 'layedit', 'laydate'], function(){
      var form = layui.form
              ,layer = layui.layer
              ,layedit = layui.layedit
              ,laydate = layui.laydate;
  });
  
  function addfinger(){
	  function addfinger(){
		  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		  parent.layer.close(index);  // 关闭layer
		  window.parent.addfinger();
		};
	};
	//保存
	function save(){
		if($("#fingerid").val()==""){
			$("#fingerid").tips({
				side:3,
	            msg:'请输入用户ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#fingerid").focus();
			return false;
		}else if($("#username").val()==""){
			$("#username").tips({
				side:3,
	            msg:'请输入用户姓名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#username").focus();
			return false;
		}
		document.getElementById("taskForm").submit();
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);  // 关闭layer
		window.parent.reload();
	}
  </script>
</body>
</html>