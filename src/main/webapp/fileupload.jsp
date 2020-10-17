<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>演示文件上传</title>
</head>
<body>
<!--
    文件上传的form表单：
    1、文件上传的输入组件只能用<input type="file">。除此之外，表单组件还有：<input type="text|password|checkbox|radio|hidden|...">、<select>、<textarea>、...
    2、文件上传的表单，请求方式只能是post，不能是get: 向后台提交参数的方式不一样。
      get:放在url后边，通过请求头提交；对参数长度有限制；只能提交文本数据，不能二进制数据；安全差；效率高、可以使用浏览器缓存
      post：通过请求体提交；理论上对参数长度没有限制；既能提交文本数据，又能提交二进制；安全相对好；效率相对较低、不可以使用缓存
    3、文件上传的表单，表单编码格式只能是multipart/form-data:
      根据HTTP协议的规定，浏览器每次向后台几条参数，都会对参数进行编码，默认的编码格式是urlencoded；
      urlencoded编码格式只能对文本数据进行编码，所以，浏览器每次向后台提交参数，都会先把参数统一转化为字符串数据，然后进行urlencoded编码；
      有文件上传的表单编码格式只能用multipart/form-data，以数据的原格式提交。
-->
<form action="workbench/activity/fileUpload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="myFile"><br>
    <input type="text" name="userName"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
