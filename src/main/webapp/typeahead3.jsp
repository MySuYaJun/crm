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
    <title>演示bs_typeahead3插件</title>
    <script type="text/javascript">
        $(function () {
            var name2Id={};//定义一个全局的空对象
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
                        success:function (data) {//[{id:xxx,name:xxx,...},{id:xxx,name:xxx,....},.......]
                            //process：是一个函数，把指定的数据源赋值给source
                            //process(data);

                            //遍历[{id:xxx,name:xxx,...},{id:xxx,name:xxx,....},.......]，获取每一个元素的name属性值，组成一个简单数组，赋值给source
                            var array=[];
                            $.each(data,function (index,obj) {
                                array.push(obj.name);

                                name2Id[obj.name]=obj.id;//访问对象name2Id，给此对象的obj.name属性赋值obj.id
                                /*
                                {
                                    '动力节点教育科技有限公司':1001,
                                    '京东商城':1002,
                                    '阿里巴巴':1003,
                                    '字节跳动':1004
                                }*/
                            });
                            process(array);
                        }
                    });
                },
                items:3,//控制下拉列表中最多能够显示多少条记录
                afterSelect:function (item) {//当选中一个选项之后，会自动触发该函数;item表示选中的那一项的值
                    alert(name2Id[item]);
                },
                delay:2000//延迟浏览器的反应时间 单位是毫秒
            });
        });
    </script>
</head>
<body>
<input id="testId" type="text">
</body>
</html>
