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
  <title>主控制台</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
  <meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
</head>
<body>


  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">管理页面</div>
              <div class="layui-card-body">
                
                <div class="layui-carousel layadmin-carousel layadmin-shortcut">
                  <div carousel-item>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs3">
                        <a href="javascript:readTorrent();">
                          <i class="layui-icon layui-icon-console"></i>
                          <cite>采集数据记录</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                        <a href="javascript:readFingers();">
                          <i class="layui-icon layui-icon-user"></i>
                          <cite>终端管理</cite>
                        </a>
                      </li>
                      <li class="layui-col-xs3">
                         <a href="javascript:readSessions();">
                          <i class="layui-icon layui-icon-chat"></i>
                          <cite>终端通信记录</cite>
                        </a>
                      </li>
                      <c:if test="${!isconnect}">
                       <li class="layui-col-xs3">
                        <a href="javascript:Open();">
                          <i class="layui-icon layui-icon-find-fill"></i>
                          <cite>连接终端</cite>
                        </a>
                      </li>
                      </c:if>
                      <c:if test="${isconnect}">
                       <li class="layui-col-xs3">
                        <a href="javascript:Close();">
                          <i class="layui-icon layui-icon-find-fill"></i>
                          <cite>断开连接</cite>
                        </a>
                      </li>
                       </c:if>
                    </ul>
                  </div>
                </div>
                
              </div>
            </div>
          </div>
          <div class="layui-col-md6">
            <div class="layui-card">
              <div class="layui-card-header">访问记录</div>
              <div class="layui-card-body">

                <div class="layui-carousel layadmin-carousel layadmin-backlog">
                  <div carousel-item>
                    <ul class="layui-row layui-col-space10">
                      <li class="layui-col-xs6">
                        <a lay-href="app/content/comment.html" class="layadmin-backlog-body">
                          <h3>终端登录数</h3>
                          <p><cite>${pass}</cite></p>
                        </a>
                      </li>
                      <li class="layui-col-xs6">
                        <a lay-href="app/forum/list.html" class="layadmin-backlog-body">
                          <h3>未知信息数</h3>
                          <p><cite>${nopass}</cite></p>
                        </a>
                      </li>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="layui-col-md12">
            <div class="layui-card">
              <div class="layui-card-header">最近访问记录概览</div>
              <div class="layui-card-body">
              <form action="show/index.do" method="post" name="Form" id="Form">
              		<table id="simple-table"  class="layui-table" style="margin-top:5px;">
								<thead>
								  <tr>
										<th class="center" >记录编号</th>
										<th class="center" >访问终端ID</th>
										<th class="center" >访问用户名</th>
										<th class="center" >访问类型</th>	
										<th class="center" >访问状态</th>					
										<th class='center'>访问时间</th>						
										<th class='center'>备注信息</th>	
									</tr>
								</thead>

								<tbody>
								<c:choose>
									<c:when test="${not empty fingerLogs}">
									<c:forEach items="${fingerLogs}" var="log" varStatus="vs">
									<tr>
										<td class='center' style="width: auto;">${log.logid}</td>
										<td class='center' style="width: auto;">
										<c:if test="${log.clientid==-1|| log.clientid ==''||log.clientid ==null}">未知ID</c:if>
										<c:if test="${log.clientid >= 0 }">${log.clientid}</c:if>
										</td>
										<td class='center' style="width: auto;">${log.clientname}</td>
										<td class='center' style="width: auto;">${log.logtype}</td>
										<td class='center' style="width: auto;">${log.function}</td>
										<td class='center' style="width: auto;"><fmt:formatDate value="${log.logtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td class='center' style="width: auto;">${log.remark}</td>
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
          </div>
        </div>
      </div>
      
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
  function readTorrent(){
        var index = layer.open({
          title: '数据采集记录',
          type: 2,
          content: 'show/listtorrent.do?',
          area: ['1200px', '600px'],
          maxmin: true
        });
        layer.full(index);
	};
	 function readSessions(){
	        var index = layer.open({
	          title: '终端会话记录',
	          type: 2,
	          content: 'show/sessions.do?',
	          area: ['1200px', '600px'],
	          maxmin: true
	        });
	        layer.full(index);
		};
	function readFingers(){
        var index = layer.open({
          title: '指纹管理',
          type: 2,
          content: 'show/listfinger.do?',
          area: ['1200px', '600px'],
          maxmin: true
        });
        layer.full(index);
	};

	  function addfinger(){
	      var index = layer.open({
	        title: '新建用户',
	        type: 2,
	        content: 'show/addfingers.do?',
	        area: ['1200px', '600px'],
	        maxmin: true
	      });
	     // layer.full(index);
		};
		  function editfinger(fingerid,username){
		      var index = layer.open({
		        title: '修改用户',
		        type: 2,
		        content: 'show/editfingers.do?fingerid='+fingerid+'&username='+username,
		        area: ['1200px', '600px'],
		        maxmin: true
		      });
		     // layer.full(index);
			};
	  function Open(){
		  window.location.href='<%=basePath%>show/index.do?is=true';
		};
	 function Close(){
		  window.location.href='<%=basePath%>show/index.do?is=false';
		};

		function deletefinger(fingerid){
			window.location.href='<%=basePath%>show/deletefinger.do?fingerid='+fingerid;
		};
		function usefinger(fingerid){
			window.location.href='<%=basePath%>show/usefinger.do?fingerid='+fingerid;
		};
  </script>
</body>
</html>