<%--
  Created by IntelliJ IDEA.
  User: dengjie
  Date: 2015/10/30
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String flag = (String)request.getSession().getAttribute("flag");
%>
<html>
<head>
<base href="<%=basePath%>"/>
<script src="<%=request.getContextPath()%>/static_resource/js/jquery-1.10.2.js"></script>
<title>欢迎</title>
</head>
<body>
	<%=basePath%>
	<script type="text/javascript">
		/*$.ajax({
			cache : false,
			data : {
                'username':'zdj',
                'password':'123456'
			},
			url : "rs/login",// 提交的URL
			type : "POST",
			async : true,
			success : function(data) {
				alert(234);
			},
			error : function(data) {
				alert("Connection error");
			}
		});*/
	</script>
    <form action="rs/login.json" method="post" >
        用户名:<input name="username" /><br>
        密码：<input name="password" />
        <input type="submit" value="登录">
    </form>

</body>
</html>
