<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui1.5/jquery.easyui.min.js"> </script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui1.5/locale/easyui-lang-zh_CN.js"> </script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/static/js/easyui1.5/themes/default/easyui.css"></link>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/js/easyui1.5/themes/icon.css"></link>
<!-- ztree树 -->
<script src="<%=request.getContextPath() %>/static/js/ztree/jquery.ztree.all-3.5.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/js/ztree/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/EasyuiCRUD.js"></script>
<title>Insert title here</title>
</head>
<body>
		<div>
			用户名：<input id="mname" name="mname"><br>
			类型：<select id="typeid" name="typeid">
					<option value="0">请选择</option>
					<option value="11">流行</option>
					<option value="22">摇滚</option>
				</select><br>
			状态：<select id="status" name="status">
				<option value="0">请选择</option>
				<option value="1">上架</option>
				<option value="2">下架</option>
			</select><br>
		 上架时间:<input id="startdate" type="date" name="startdate">-<input id="enddate" type="date" name="enddate"><br>
			
		<a id="btn" class="easyui-linkbutton" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
		<div>
			<a href="javaScript:openDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a> 
			<a href="javaScript:updDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a> 
		</div>
		<table id="data" class="easyui-datagrid"></table>
		
		
		<div id="mydialog" closed="true" button="#AddOk" class="easyui-dialog" style="width:400px;height:200px;">
			<form id="addForm">
				<input type="hidden" name="id">
				<table>
				<tr>
					<td>客户名称</td>
					<td>
						<input type="text" name="name" class="easyui-validatebox" required="true"> 
					</td>
				</tr>
				 <tr>
					<td>所属地址</td>
					<td>
						<input type="text" name="location_id" class="easyui-validatebox" required="true"> 
					</td>
				</tr>
				<tr>
					<td>录入时间</td>
					<td>
						<input type="date" name="create_time" class="easyui-validatebox" required="true">
					</td>
				</tr>
				<tr>
					<td>电话</td>
					<td>
						<input name="phone" class="easyui-validatebox" required="true">
					</td>
				</tr>
				<tr>
					<td>部门</td>
					<td>
						<input  name="department_id"> 
					</td>
				</tr>
				
				<tr>
					<td>图片</td>
					<td>
						<input type="date" name="createtime"> 
					</td>
				</tr> 
				
				</table>
			</form>
			
			<div id="AddOk">
				<a href="javaScript:saveDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
				<a href="javaScript:cancelDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消 </a>
			
			</div>
		</div>
		
</body>
	
	<script type="text/javascript">
		$(function(){
			
			queryMusic();
		})
		
			openDialog = function(){
			
			$("#mydialog").dialog('open');
		}
		
		
		 function saveDialog(){
				
	<%-- 			$("#addForm").form('submit',{
					
					url:'cusAction!addCus.action',
					onSumbmit:function(){
						
						return $("#addForm").form('validate')
					},
					success:function(data){
					//location.href="<%=request.getContextPath()%>/index.jsp"
						alert("添加成功！！")
						//关闭窗口
						$("#mydialog").dialog('close');
						//刷新表格数据
						$("#data").datagrid('reload');
						
				}
		 	}); --%>
		 	
		 		$.ajax({
		           url:"<%=request.getContextPath()%>/cusAction!addCus.action",
		           data:$("#addForm").serialize(),
		           type:"post",
		    	   success:function(){
		    		   
		    		   alert("添加成功！！")
		    		   
		    			$("#mydialog").dialog('close');
						//刷新表格数据
						$("#data").datagrid('reload');
		    		   
		    	   }
		    		
		    	})
				
		 }
		
		
		
		$("#btn").click(function(){
		
		$("#data").datagrid('load',{
			mname:$("#mname").val(),
			typeid:$("#typeid").val(),
			status:$("#status").val(),
			startdate:$("#startdate").val(),
			enddate:$("#enddate").val(),
			})
		})
		
		queryMusic = function(){
			
			$("#data").datagrid({
				
				url:'<%=request.getContextPath()%>/cusAction!queryCustomer.action',
				columns:[[
							{field:'ck',checkbox:true},
							{field:'id',title:'id',width:100},
							{field:'cname',title:'公司名称',width:100},
							{field:'lname',title:'所属省市',width:100},
							{field:'create_time',title:'录入时间',width:100},
							{field:'gj_time',title:'跟进时间',width:100},
							{field:'phone',title:'电话',width:100},
							{field:'dname',title:'部门',width:100},
							{field:'cz',title:'操作',width:100,
								formatter:function(value,row,index){
									if(row.status=='2'){
										
										var a = "<a href='javascript:delDialog("+row.id+")' class='easyui-linkbutton' iconCls='icon-ok'>删除</a>";
										a+=" | <a href='javascript:up("+row.mid+")' class='easyui-linkbutton' iconCls='icon-ok'>下架</a>";
										return a;
									}else{
										var a = "<a href='javascript:delDialog("+row.id+")' class='easyui-linkbutton' iconCls='icon-ok'>删除</a>";
										a+=" | <a href='javascript:down("+row.mid+")' class='easyui-linkbutton' iconCls='icon-ok'>上架</a>";
										return a;
										
									}
									
								},
							},
						
					
						]],
						
							pagination:true,
							
							pageList:[2,5,10],
							
							rownumbers:true,
							
							fitColumns:true
			})
		};
		
		
		 delDialog = function(id){
			 alert(id);
			 	//$.post("url",参数,函数)
				$.post("cusAction!deleteCus.action",{id:id},function(result){
					
						$.messager.alert('提示','删除成功')
						$("#data").datagrid('reload');
				
				},"json");
		 }
		 
		 
		 
		 up = function(mid){
			 
			 $.ajax({
				
				 url:"music/up.do?mid="+mid,
				 success:function(){

					 
					$("#data").datagrid('reload');
				 }
			 })
		 };
		 
		 down = function(mid){
			 
			 $.ajax({
				
				 url:"music/down.do?mid="+mid,
				 success:function(){
					 <%-- location.href="<%=request.getContextPath()%>/list.jsp" --%>
					 
					$("#data").datagrid('reload');
				 }
			 });
		 }
		 
	</script>

</html>