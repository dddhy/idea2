function OpenTab(Text,URL){
	if( $("#MenusTabs").tabs('exists', Text) ){
		$("#MenusTabs").tabs('select', Text);
	}
	else{
		var Content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src=" + URL + "></iframe>";
		$("#MenusTabs").tabs('add',{
			title:Text,
			closable:true,
			content:Content
		});	
	}
}
	
		updDialog = function(){
		//	alert(11)
			var rows = $("#data").datagrid('getSelections');
			if(1 !=rows.length){
				$.messager.alert('警告','请选择一条且只能选择一条数据');
				return;
			}
			
			var row = rows[0];
			$("#dd").dialog('open');
			
			$("#addForm").form('load',row);
			
			url='addCom.do?c_id'+row.c_id;
		}
		
	

	/*	function reset(){
			//alert(111)
			$("#bname").val('');
			$("#price").val('');
			$("#sel").val("<option value='"+请选择+"'>"+请选择+"</option>");
			$("#bjianjie").val('');
			$("#bbiming").val('');
			$("#imgId").val('');
		}*/


		 function SaveDialog(){
			
					//alert(11)
					/*url:"<%=request.getContextPath()%>/addCom.do",*/
					$("#addForm").form('submit',{
						
						url:'addCom.do',
						onSumbmit:function(){
							//对表单校验  validate
						//	alert(12)
							return $("#addForm").form('validate')
						},
						success:function(data){
						//location.href="<%=request.getContextPath()%>/index.jsp"
							alert("添加成功！！")
							//关闭窗口
							$("#dd").dialog('close');
							//刷新表格数据
							$("#data").datagrid('reload');
							
					}
			 	});
					
			 }
		 
 
		 delCom = function(c_id){
			// alert(c_id);
			 	//$.post("url",参数,函数)
				$.post("del.do",{c_id:c_id},function(result){
					if(result.success){
						$.messager.alert('提示','删除成功')
						$("#data").datagrid('reload');
					}
					else{
						$.messager.alert("系统提示", "<font color=red>删除失败</font>");
					}
				},"json");
		 }
		 	
		 delChkDialog = function(){
			 
			 var rows = $("#data").datagrid('getSelections');
			 
			 if(rows.length==0){
				 $.messager.alert('提示','请至少选一项');
				 return;
			 }
			 
			 var temp = [];
			 
			 for (var i = 0; i < rows.length; i++) {
				
				 temp.push(rows[i].c_id);
			}
			 
			 var ids = temp.join(",");
			// alert(ids)
			 $("#data").datagrid('reload')
			 location.href="./delAll.do?ids="+ids;
			 $.messager.alert('提示','删除成功');
		 }
		 
		 queryType = function(){
			//alert(111)
			 $("#sel").combobox({
			
				 url:"./queryType.do",
					valueField:'id',
					textField:'typename'
			 });
				
		 };
		 
		 queryUser = function(){
			 
			 $.ajax({
				 
				 url:"./queryuser.do",
				 success:function(data){
					 
					 for (var i = 0; i < data.length; i++) {
						 $("#username").html(data[i].t_username);
						 $("#usermoney").html(data[i].t_usermoney);
					}
					
				 },
				 error:function(){
					 alert("错了");
				 }
			 })
		 }
		 
	
		 
		$(function(){
	/*		var TreeMenusDatas=[{
				text:"图书馆图书系统",
				children:[{
					text:"小说图书信息",
					attributes:{
						url:"list.jsp"
					}
				}]
			
			}];
			
	
		$("#TreeMenus").tree({
			data:TreeMenusDatas,
			lines:true,
			onClick:function(node){
				if( node.attributes ){
					OpenTab( node.text, node.attributes.url );
				}
			}
		});*/
	});