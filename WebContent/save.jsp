<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加商品页面</title>
</head>
<body>
    
    <!-- web.jsp 应该吧数据传递给productService,但是service不能接受http请求,因此java出现了servlet -->
	<form action="<%=request.getContextPath()%>/product/save.mvc" method="post">
		商品名称:<input type="text" name="name" /><br />
		商品价格:<input type="text" name="price" /><br />
		商品备注:<textarea rows="5" cols="30" name="remark"></textarea><br />
		<button type="submit">提交商品</button>
	</form>
</body>
</html>