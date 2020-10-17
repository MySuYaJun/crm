<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,com.bjpowernode.crm.settings.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>演示JSTL标签</title>
</head>
<body>
<%
    int a=20;

    request.setAttribute("a",a);
%>

<%
    if(a<50){
%>
<h2>Hello world</h2>
<%
    }
%>


<c:if test="${a<50}">
    <h2>Hello world</h2>
</c:if>
<hr>
<%
    for(int i=0;i<10;i++){
%>
<h2>Hello world<%=i%></h2>
<%
    }
%>
<hr>
<c:forEach begin="0" end="10" varStatus="vs">
    <h2>Hello world${vs.count}</h2>
</c:forEach>
<hr>
<%
    List<User> userList=new ArrayList<>();
    User user=new User();
    user.setId("1001");
    user.setName("zhangsan");
    userList.add(user);
    user=new User();
    user.setId("1002");
    user.setName("lisi");
    userList.add(user);
    user=new User();
    user.setId("1003");
    user.setName("wangwu");
    userList.add(user);

    request.setAttribute("userList",userList);
%>

<table border="1" width="30%" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>NAME</th>
    </tr>
    <%
        for(User u:userList){
    %>
    <tr>
        <td><%=u.getId()%></td>
        <td><%=u.getName()%></td>
    </tr>
    <%
        }
    %>
</table>
<hr>
<table border="1" width="30%" cellspacing="0">
    <tr>
        <th>序号</th>
        <th>ID</th>
        <th>NAME</th>
    </tr>
    <c:forEach items="${userList}" var="u" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${u.id}</td>
            <td>${u.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
