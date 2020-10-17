<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function () {
			//给"创建"按钮添加单击事件
			$("#createDicValueBtn").click(function () {
				//发送同步请求
				window.location.href="settings/dictionary/value/toSave.do";
			});

			//给"全选"按钮添加单击事件
				$("#chk_all").click(function () {
					/*
					if (this.checked){//如果"全选"按钮被选中
						//列表中的所有checkbox也都要被选中
						$("#tBody input[type='checkbox']").prop("checked",true);
					}else{//如果"全选"按钮没被选中
						//列表中的所有checkbox也都要要取消
						$("#tBody input[type='checkbox']").prop("checked",false);
					}*/

					$("#tBody input[type='checkbox']").prop("checked",this.checked);
				});

			//给列表中所有的checkbox添加单击事件
			$("#tBody input[type='checkbox']").click(function () {
				//判断列表中所有的checkbox是不是都选中了？
				//如果都选中了，则"全选"按钮也要选中
				//如果不是都都选中了，则"全选"按钮应该取消
				if ($("#tBody input[type='checkbox']").size()==$("#tBody input[type='checkbox']:checked").size()){
					$("#chk_all").prop("checked",true);
				}else{
					$("#chk_all").prop("checked",false);
				}
			});

			//给"删除"按钮添加单击事件
			$("#deleteDicValueBtn").click(function () {
				//收集参数
				//获取列表中所有被选中的checkbox
				var chkedIds=$("#tBody input[type='checkbox']:checked");
				//表单验证
				if (chkedIds.size()==0){
					alert("请选择要删除的记录");
					return;
				}

				//遍历chkedIds，获取所有被选中checkbox的value属性值
				var ids="";
				$.each(chkedIds,function () {//id=xxx&id=xxx&....
					ids+="id="+this.value+"&";
				});
				//id=xxx&id=xxx&....&id=xxx&
				ids=ids.substr(0,ids.length-1);//id=xxx&id=xxx&....&id=xxx

				if (window.confirm("确定删除吗？")){
					//发送请求
					$.ajax({
						url:'settings/dictionary/value/deleteDicValueByIds.do',
						data:ids,
						type:'post',
						dataType:'json',
						success:function (data) {
							if (data.code=="1"){
								//跳转到数据字典值的主页面
								window.location.href="settings/dictionary/value/index.do";
							}else{
								//提示信息
								alert(data.message);
							}
						}
					});
				}
			});

			//给"编辑"按钮添加单击事件
			$("#editDicValueBtn").click(function () {
				//收集参数
				//获取列表中所有被选中的checkbox
				var chkedIds=$("#tBody input[type='checkbox']:checked");
				//表单验证
				if (chkedIds.size()==0){
					alert("请选择要编辑的记录");
					return;
				}
				if (chkedIds.size()>1){
					alert("每次只能编辑一条记录");
					return;
				}

				var id=chkedIds[0].value;

				//发送请求
				window.location.href="settings/dictionary/value/toEdit.do?id="+id;
			});
		});
	</script>
</head>
<body>

	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典值列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button id="createDicValueBtn" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button id="editDicValueBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button id="deleteDicValueBtn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" id="chk_all"/></td>
					<td>序号</td>
					<td>字典值</td>
					<td>文本</td>
					<td>排序号</td>
					<td>字典类型编码</td>
				</tr>
			</thead>
			<tbody id="tBody">
				<c:forEach items="${dicValueList}" var="dv" varStatus="vs">
					<c:if test="${vs.count%2==0}">
						<tr class="active">
					</c:if>
					<c:if test="${vs.count%2!=0}">
						<tr>
					</c:if>
						<td><input type="checkbox" value="${dv.id}"/></td>
						<td>${vs.count}</td>
						<td>${dv.value}</td>
						<td>${dv.text}</td>
						<td>${dv.orderNo}</td>
						<td>${dv.typeCode}</td>
					</tr>
				</c:forEach>
				<%--<tr class="active">
					<td><input type="checkbox" /></td>
					<td>1</td>
					<td>m</td>
					<td>男</td>
					<td>1</td>
					<td>sex</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td>2</td>
					<td>f</td>
					<td>女</td>
					<td>2</td>
					<td>sex</td>
				</tr>
				<tr class="active">
					<td><input type="checkbox" /></td>
					<td>3</td>
					<td>1</td>
					<td>一级部门</td>
					<td>1</td>
					<td>orgType</td>
				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td>4</td>
					<td>2</td>
					<td>二级部门</td>
					<td>2</td>
					<td>orgType</td>
				</tr>
				<tr class="active">
					<td><input type="checkbox" /></td>
					<td>5</td>
					<td>3</td>
					<td>三级部门</td>
					<td>3</td>
					<td>orgType</td>
				</tr>--%>
			</tbody>
		</table>
	</div>
	
</body>
</html>