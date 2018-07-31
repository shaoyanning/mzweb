<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查询商品页面</title>
</head>
<body>
    <!--  request,session,application都称为内置对象 -->
    <!-- web.jsp 应该吧数据传递给productService,但是service不能接受http请求,因此java出现了servlet -->
	<form action="<%=request.getContextPath()%>/ProductServlet" method="get">
		商品名称:<input type="text" name="keyword" /><br />
		<button type="submit">提交商品</button>
		<input type="hidden" name="type" value="query"/>
	</form>
	<table width="600" border="1">
		<tr>
			<th>编码</th>
			<th>名称</th>
			<th>价格</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<!-- 有多少结果集对象,则循环多少次,前端Java有JSTL标签可以获取request内置对象中的数据 -->
		<c:forEach items="${requestScope.proList}" var="product" varStatus="num">
			<tr>
				<td>${num.count}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.remark}</td>
				<td><a href="<%=request.getContextPath()%>/ProductServlet?id=${product.id}&type=delete">删除</a>|更新</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>