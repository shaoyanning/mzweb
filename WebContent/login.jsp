<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加商品页面</title>
</head>
<body>
    
    <!-- web.jsp 应该吧数据传递给productService,但是service不能接受http请求,因此java出现了servlet -->
	<form action="<%=request.getContextPath()%>/AccountServlet" method="post">
		账号:<input type="text" name="username" /><br />
		密码:<input type="text" name="password" /><br />
		${requestScope.error}
		<button type="submit">登录</button>
	</form>
</body>
</html>