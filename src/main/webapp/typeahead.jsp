<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>
    <title>演示bs_typeahead1插件</title>
    <script type="text/javascript">
        $(function () {
            //当容器加载完成，对容器调用工具函数
            $("#testId").typeahead({
                source:['动力节点教育科技有限公司','京东商城','阿里巴巴','字节跳动']
            });
        });
    </script>
</head>
<body>
<input id="testId" type="text">
</body>
</html>
