<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <!--  It is a good idea to bundle all CSS in one file. The same with JS -->

    <!--  JQUERY -->
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>

    <!--  BOOTSTRAP -->
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

    <!--  PAGINATION plugin -->
    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
    <title>演示翻页插件</title>
    <script type="text/javascript">
        $(function() {

            $("#demo_pag1").bs_pagination({
                currentPage:5,//当前页号
                rowsPerPage:10,//每页显示条数
                totalPages: 100,  //总页数，必填
                totalRows:1000,//总条数

                visiblePageLinks:5,//最多可以显示的页号卡片数

                showGoToPage:true, //是否显示"跳转到"
                showRowsPerPage:true, //是否显示"每页显示条数"
                showRowsInfo:true, //是否显示记录信息

                onChangePage: function(e,pageObj) { // returns page_num and rows_per_page after a link has clicked
                    //alert("aaaa");
                    alert(pageObj.currentPage);
                    alert(pageObj.rowsPerPage);
                }
            });

        });
    </script>
</head>
<body>
<!--  Just create a div and give it an ID -->

<div id="demo_pag1"></div>
</body>
</html>
