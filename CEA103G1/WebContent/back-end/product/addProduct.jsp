<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~��Ʒs�W - addProduct.jsp</title>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�ӫ~�s�W - addProduct.jsp</h3></td><td>
		 <h4><a href="${pageContext.request.contextPath}/back-end/product/select_page.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" width="100" height="100" border="0"></a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product/product.do" name="form1">
<input type="reset" value="���m�M��">
<table>
	<tr>
		<td>�ӫ~�����s��:</td>	
		<td><input type="TEXT" name="prod_cat_no" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_cat_no()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~���A:</td>
		<td>
			<input type="radio" name="prod_stat" value="0" checked>�U�[<br>
			<input type="radio" name="prod_stat" value="1">�W�[<br> 
		</td>
	</tr>
	<tr>
		<td>�ӫ~�W��:</td>
		<td><input type="TEXT" name="prod_name" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_name()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~����:</td>
		<td><input type="TEXT" name="prod_pc" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_pc()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~�w�s:</td>
		<td><input type="TEXT" name="prod_stg" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_stg()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~��T:</td>
		<td><input type="TEXT" name="prod_info" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_info()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~�~�P:</td>
		<td><input type="TEXT" name="prod_bnd" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_bnd()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~�C��:</td>
		<td><input type="TEXT" name="prod_clr" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_clr()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~�j�p:</td>
		<td><input type="TEXT" name="prod_size" size="45" 
			 value="<%= (productVO==null)? "" : productVO.getProd_size()%>" /></td>
	</tr>
	<tr>
		<td>�B�e�覡:</td>
		<td>
			<select name="ship_meth">
			    <option selected disabled value="">�п�ܹB�e�覡</option>
			    <option value="0">�����B�e�覡</option>
			    <option value="1">���v�t</option>
			    <option value="2">���W�Ө��f</option>
			</select>
		</td>
	</tr>
</table>


<script>

</script>

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W">
</FORM>
</body>

</html>