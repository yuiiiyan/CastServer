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
  <link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
  <meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<%-- <c:if test="${not empty fingers}"><meta http-equiv="refresh" content="5"></c:if> --%>
</head>
<body>


  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <form action="show/finger.do" method="post" name="Form" id="Form">
              <button class="layui-btn layui-btn-lg layui-btn-primary layui-btn-radius"  onclick="addfinger();">新建用户</button>
              		<table id="simple-table"  class="layui-table" style="margin-top:5px;">
								<thead>
								  <tr>
										<th class="center" >用户编号</th>
										<th class="center" >用户名字</th>
										<th class="center" >指纹是否同步</th>
										<th class="center" >创建日期</th>					
										<th class='center'>最后登录日期</th>						
										<th class='center'>备注信息</th>						
										<th class='center'>管理操作</th>	
									</tr>
								</thead>

								<tbody>
								<c:choose>
									<c:when test="${not empty fingers}">
									<c:forEach items="${fingers}" var="finger" varStatus="vs">
									<tr>
										<c:if test="${finger.fingerid != -1}">
										<td class='center' style="width: auto;">${finger.fingerid}</td>
										<td class='center' style="width: auto;">${finger.username}</td>
										<td class='center' style="width: auto;">
										${finger.isuse?'已同步':'未同步'}
										</td>
										<td class='center' style="width: auto;"><fmt:formatDate value="${finger.creatdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td class='center' style="width: auto;"><fmt:formatDate value="${finger.finalpassdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td class='center' style="width: auto;">${finger.note}</td>
										<td class='center' style="width: auto;">
               								<button class="layui-btn layui-btn-primary layui-btn-sm" onclick="usefinger('${finger.fingerid}');">  <i class="layui-icon">&#xe654;</i></button>
							                <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="editfinger('${finger.fingerid}','${finger.username}');"><i class="layui-icon">&#xe642;</i></button>
							                <button class="layui-btn layui-btn-primary layui-btn-sm" onclick="deletefinger('${finger.fingerid}');"><i class="layui-icon">&#xe640;</i></button>
							            </td>
							            </c:if>
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
  
  function addfinger(){
	  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	  parent.layer.close(index);  // 关闭layer
	  window.parent.addfinger();
	};
	 function editfinger(fingerid,username){
		  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		  parent.layer.close(index);  // 关闭layer
		  window.parent.editfinger(fingerid,username);
		};
	 function deletefinger(fingerid){
		 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		  parent.layer.close(index);  // 关闭layer
		  window.parent.deletefinger(fingerid);
	};
	function usefinger(fingerid){
		 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		  parent.layer.close(index);  // 关闭layer
		  window.parent.usefinger(fingerid);
	};
  </script>
</body>
</html>