<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
    <title>演示日历插件</title>
    <script type="text/javascript">
        $(function () {
            //当整个页面都加载完成，对容器调用工具函数
            $("#myDate").datetimepicker({
                language:'zh-CN',//语言
                format:'yyyy-mm-dd',//选中日期之后显示到容器中的格式
                minView:'month',//最小能够选择日期的视图
                initialDate:new Date(),//日历显示的初始化日期
                autoclose:true,//选中日期之后，自动关闭日历
                todayBtn:true,//是否显示"今天"按钮
                clearBtn:true//是否显示"清空"按钮
            });
        });
    </script>
</head>
<body>
<input type="text" id="myDate" readonly>
</body>
</html>
