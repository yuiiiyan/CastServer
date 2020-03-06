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
  <title>终端通信记录</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
  <meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta http-equiv="refresh" content="60">
</head>
<body>


  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <form action="show/sessions.do" method="post" name="Form" id="Form">
              		<table id="simple-table"  class="layui-table" style="margin-top:5px;">
								<thead>
								  <tr>
										<th class="center" >会话编号</th>
										<th class="center" >用户名</th>
										<th class="center" >会话内容</th>				
										<th class='center'>会话时间</th>		
									</tr>
								</thead>

								<tbody>
								<c:choose>
									<c:when test="${not empty Session}">
									<c:forEach items="${Session}" var="session" varStatus="vs">
									<tr>
										<td class='center' style="width: auto;">${session.sessionid}</td>
										<td class='center' style="width: auto;">
										<c:if test="${session.sessionuser == 1}">终端</c:if>
										<c:if test="${session.sessionuser == 0}">服务器</c:if>
										</td>
										<td class='center' style="width: auto;">${session.sessiondata}</td>
										<td class='center' style="width: auto;"><fmt:formatDate value="${session.sessiondate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
									</c:forEach>
									</c:when>
										<c:otherwise>
											<tr>
											<td colspan="100" class='center'>没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
							<div class="page-header position-relative">
								<table style="width:100%;">
									<tr>
										<td style="vertical-align:top;"><div class="pagination"   style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
									</tr>
							</table>
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
  </script>
</body>
</html>