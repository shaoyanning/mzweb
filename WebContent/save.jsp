<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>添加商品页面</title>
	<!-- 引入jquery -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$.post('<%=request.getContextPath()%>/category/ajax.mvc',{name:'admin',pass:'admin'},function(result){
				console.info(result + "," + result.title + ",");
				// 1: 下拉列表添加数据
				$(result.proList).each(function(i){
					$('#sel').append("<option value=" + this.id + ">" + this.name + "</option>");
				});
			})
		})
	</script>
</head>
<body>
    <!-- web.jsp 应该吧数据传递给productService,但是service不能接受http请求,因此java出现了servlet -->
	<form action="<%=request.getContextPath()%>/product/save.mvc" method="post" enctype="multipart/form-data">
		商品名称:<input type="text" name="name" /><br />
		商品价格:<input type="text" name="price" /><br />
		商品备注:<textarea rows="5" cols="30" name="remark"></textarea><br />
		图片: <input type="file" name="img" />
		<br />
		<select name="sel" id="sel">
			<option value='0'>---请选择----</option>
		</select>
		<button type="submit">提交商品</button>
	</form>
	
	
</body>
</html>