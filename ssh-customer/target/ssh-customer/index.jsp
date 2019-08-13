<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui1.5/jquery.easyui.min.js"> </script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui1.5/locale/easyui-lang-zh_CN.js"> </script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/js/easyui1.5/themes/default/easyui.css"></link>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/easyui1.5/themes/icon.css"></link>

<!-- ztree树 -->
<script src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.all-3.5.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/ztree/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/EasyuiCRUD.js"></script>
<title>Insert title here</title>
</head>
	
	<body class="easyui-layout">   
    	
    <div data-options="region:'north',title:'North Title',split:true" style="height:130px;">
    	
    <%-- 	<center><h3>欢迎${User.t_username}登录</h3></center>
    	<center><h3>余额：${User.t_usermoney}</h3></center> --%>
    	<center><h3>欢迎<span id="">${user.username}</span>登录</h3></center>
    </div>   
    <div data-options="region:'west',title:'West',split:true" style="width:200px;">
 
          <ul id="TreeMenus"></ul>
		
    </div>   
    <div data-options="region:'center',title:'center title'"  style="padding:5px;background:#eee;">
    	   <div id="MenusTabs" class="easyui-tabs" title="Tab1" data-options="fit:true" style="width:500px;height:200px;">   
   		  
   		   </div>  
    </div> 
</body>
		<script type="text/javascript">
			$(function(){
				
			<%-- 	$.ajax({
					url:"<%=request.getContextPath()%>/queryuser.do"
				}) --%>
			gettongtree();
			//	queryUser();

			})
			
	
	
			
			//同步树
		function gettongtree(){
			   
			   $('#TreeMenus').tree({    
				    url:'<%=request.getContextPath()%>/user/getTreeAll.do'   
				});  
		
		}
			
			
			$("#TreeMenus").tree({
				
				url:"<%=request.getContextPath()%>/queryTree.do",
				lines:true,
			//	onDblClick onClick
				onClick:function(node){
					//alert(node.url)
					if((node.children=="" || node.children==null)&& node.pid !=0){
						
						if(!$("#MenusTabs").tabs('exists',node.text)){
							
							$("#MenusTabs").tabs('add',{
								
								title:node.text,
								content:creat(node.url),
								closable:true
							})
						}else{
							$("#MenusTabs").tabs('select',node.text)
						}
					}
				}
			})
		
			
			
			creat = function(url){
				
				return '<iframe scrolling="auto" frameborder="0"  src="'+'<%=request.getContextPath()%>'+ url + '" style="width:100%;height:100%;"></iframe>'; 
			}
		</script>
</html>