<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>�|������</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/member/member.do" class="" name="">
		<h2>�H�c�|�����ҡA�кɳt�ܱz���H�c�I�����ҳs���C</h2>	
		<input type="hidden" name="action" value="sendAuthMail">
		<input type="hidden" name="acc" value="${param.acc}"> 
		<input type="submit" value="���H���ҫH">
	</form>
</body>
</html>