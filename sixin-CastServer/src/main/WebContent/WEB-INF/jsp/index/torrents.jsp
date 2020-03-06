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
		<meta http-equiv="refresh" content="60">
</head>
<body>


  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <form action="show/logs.do" method="post" name="Form" id="Form">
              		<table id="simple-table"  class="layui-table" style="margin-top:5px;">
								<thead>
								  <tr>
										<th class="center" >IMEI</th>
										<th class="center" >IP地址</th>
										<th class="center" >记录时间</th>
										<th class="center" >电池1电压</th>
										<th class="center" >电池组电压</th>
										<th class="center" >18V1输出电压</th>					
										<th class='center'>18V2输出电压</th>						
										<th class='center'>24V输出电压</th>						
										<th class='center'>28V输出电压</th>	
										<th class='center'>外部电源电压</th>	
										<th class='center'>太阳能电压</th>	
										<th class='center'>充电状态</th>	
										<th class='center'>工作状态</th>	
									</tr>
								</thead>

								<tbody>
								<c:choose>
									<c:when test="${not empty torrents}">
									<c:forEach items="${torrents}" var="tor" varStatus="vs">
									<tr>
										<td class='center' style="width: auto;">${tor.IMEI}</td>
										<td class='center' style="width: auto;">${tor.IP}</td>
										<td class='center' style="width: auto;"><fmt:formatDate value="${tor.time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td class='center' style="width: auto;">${tor.pow1}</td>
										<td class='center' style="width: auto;">${tor.grouppow}</td>
										<td class='center' style="width: auto;">${tor.outv1}</td>
										<td class='center' style="width: auto;">${tor.outv2}</td>
										<td class='center' style="width: auto;">${tor.v24}</td>
										<td class='center' style="width: auto;">${tor.v28}</td>
										<td class='center' style="width: auto;">${tor.extendpow}</td>
										<td class='center' style="width: auto;">${tor.solarpow}</td>
										<td class='center' style="width: auto;">
										<c:if test="${tor.charge == '00' }">涓流充电状态</c:if>
										<c:if test="${tor.charge == '01' }">过充电状态</c:if>
										<c:if test="${tor.charge == '10' }">大电流充电状态</c:if>
										<c:if test="${tor.charge == '11' }">浮充状态</c:if>
										<c:if test="${tor.charge == null || tor.charge == ''}">未知</c:if>
										</td>
										<td class='center' style="width: auto;">
										<c:if test="${tor.work == '0' }">开关电源充电</c:if>
										<c:if test="${tor.work == '1' }">太阳能充电</c:if>
										<c:if test="${tor.work == '2' }">充满</c:if>
										<c:if test="${tor.work == null || tor.work == ''}">未知</c:if>
										</td>
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