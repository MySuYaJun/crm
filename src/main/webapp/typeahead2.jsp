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
    <title>演示bs_typeahead2插件</title>
    <script type="text/javascript">
        $(function () {
            //当容器加载完成，对容器调用工具函数
            $("#testId").typeahead({
                source:function (query,process) {//在容器中输入关键字时，每次键盘弹起都hi指定该函数
                                                // query就是容器每次输入的关键字
                    $.ajax({
                        url:'workbench/transaction/typeaheadTest.do',
                        data:{
                            customerName:query
                        },
                        type:'post',
                        dataType:'json',
                        success:function (data) {//['动力节点教育科技有限公司','京东商城','阿里巴巴','字节跳动']
                            //process：是一个函数，把指定的数据源赋值给source
                            process(data);
                        }
                    });
                }
            });
        });
    </script>
</head>
<body>
<input id="testId" type="text">
</body>
</html>
