var locat = (window.location+'').split('/'); 
$(function(){if('main'== locat[3]){locat =  locat[0]+'//'+locat[2];}else{locat =  locat[0]+'//'+locat[2]+'/'+locat[3];};});

var fmid = "fhindex";	//菜单点中状态
var mid = "fhindex";	//菜单点中状态
var fhsmsCount = 0;		//站内信总数
var userid;			//用户ID
var TFHsmsSound = '1';	//站内信提示音效
var websocket;			//websocket对象
var oladress="";		//在线管理和站内信服务器IP和端口

function siMenu(id,fid,mname,murl){
	if(id != mid){
		$("#"+mid).removeClass();
		mid = id;
	}
	if(fid != fmid){
		$("#"+fmid).removeClass();
		fmid = fid;
	}
	$("#"+fid).attr("class","active open");
	$("#"+id).attr("class","active");
	top.mainFrame.tabAddHandler(id,mname,murl);
	if(murl != "druid/index.html"){
		jzts();
	}
}

//$(function(){
//	getHeadMsg();	//初始页面最顶部信息
//});

////初始页面信息
//function getHeadMsg(){
//	$.ajax({
//		type: "POST",
//		url: locat+'/head/getList.do?tm='+new Date().getTime(),
//    	data: encodeURI(""),
//		dataType:'json',
//		//beforeSend: validateData,
//		cache: false,
//		success: function(data){
//			 $.each(data.list, function(i, list){
//				 $("#user_info").html('<small>Welcome</small> '+list.name+'');//登陆者资料
//				 user = list.username;
//				 userid = list.userid;		//用户ID
//				 if(list.username != 'admin'){
//					 $("#systemset").hide();	//隐藏系统设置
//				 }
//			 });
//			 fhsmsCount = Number(data.fhsmsCount);
//			 $("#fhsmsCount").html(Number(fhsmsCount));	//站内信未读总数
//			 TFHsmsSound = data.FHsmsSound;				//站内信提示音效
////			 wimadress = data.wimadress;				//即时聊天服务器IP和端口
//			 oladress = data.oladress;					//在线管理和站内信服务器IP和端口
////			 online();									//连接在线
//		}
//	});
//}

//获取站内信未读总数(在站内信删除未读新信件时调用此函数更新未读数)
function getFhsmsCount(){
	$.ajax({
		type: "POST",
		url: locat+'/head/getFhsmsCount.do?tm='+new Date().getTime(),
    	data: encodeURI(""),
		dataType:'json',
		cache: false,
		success: function(data){
			 fhsmsCount = Number(data.fhsmsCount);
			 $("#fhsmsCount").html(Number(fhsmsCount));	//站内信未读总数
		}
	});
}

//去通知收信人有站内信接收
function fhsmsmsg(username){
	var arrusername = username.split(';');
	for(var i=0;i<arrusername.length;i++){
		websocket.send('[fhsms]'+arrusername[i]);//发送通知
	}
}

//读取站内信时减少未读总数
function readFhsms(){
	fhsmsCount = Number(fhsmsCount)-1;
	$("#fhsmsCount").html(Number(fhsmsCount) <= 0 ?'0':fhsmsCount);
}

//修改密码
function editUserH(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="修改密码";
	 diag.URL = locat+'/users/goEditPassword.do';
	 diag.Width = 469;
	 diag.Height = 250;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//系统设置
function editSys(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="系统设置";
	 diag.URL = locat+'/head/goSystem.do';
	 diag.Width = 600;
	 diag.Height = 526;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//站内信
function fhsms(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="站内信";
	 diag.URL = locat+'/fhsms/list.do?STATUS=2';
	 diag.Width = 800;
	 diag.Height = 500;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

////切换菜单
//function changeMenus(){
//	window.location.href=locat+'/main/yes';
//}

//清除加载进度
function hangge(){
	$("#jzts").hide();
}

//显示加载进度
function jzts(){
	$("#jzts").show();
}