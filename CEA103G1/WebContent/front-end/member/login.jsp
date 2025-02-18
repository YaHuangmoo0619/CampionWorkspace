<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="BIG5">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style type="text/css">
        @import url("https://fonts.googleeapis.com/css?family=Noto+Sans+TC:100,300,400,500,700,900&display=swap");
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        html, body {
            height: 100%;
        }
        body {
            background: url("<%=request.getContextPath()%>/front-images/register-blur3.png") no-repeat center center / cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login {
            width: 350px;
            height: 450px;
            background-color: rgba(215, 223, 215, .9);
            border-radius: 10px;
            border: 3px solid #fff;
            box-shadow: 0 0 60px #000;
            backdrop-filter: blur(5px);
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .form {
            width: 250px;
            font-family: '微軟正黑體', sans-serif;
            color: #666666;
        }
        .form h2 {
            margin-bottom: 20px;
            padding-bottom: 20px;
            border-bottom: 1px solid #000000;
            display: flex;
            justify-content: center;
        }
        .form .group {
            margin-bottom: 20px;
        }
        .form label {
            line-height: 2;
            font-family: '微軟正黑體', sans-serif;
        }
        .form input {
            width: 250px;
            border: 1px solid #aaa;
            line-height: 3;
            border-radius: 5px;

        }
        .form .btn-group {
            font-size: 0;
            margin-top: 30px;
        }
        .form .btn {
            font-size: 17px;
            border-radius: 5px;
            border: none;
            background-color: #239ad1;
            width: 250px;
            padding: 10px 0;
            color: #fff;
            font-family: '微軟正黑體', sans-serif;
        }
        .form .btn+.btn {
            margin-left: 20px;
        }
        button:hover {
            background: linear-gradient(#6ab4fe, #6ab4fe);
        }
        a {
   		    text-decoration:none;
		}
		input[type='checkbox']{
            width:12px;
            height:12px;
        }
    </style>
</head>

<body>
    <div class="login">
        <form class="form" method="post" action="<%=request.getContextPath()%>/member/member.do">
            <h2>會員登入</h2>
            <div class="group">
                <label for="id">帳號</label>
                <input type="text" name="acc" id="acc">
                <input type="hidden" name="action" value="login_Member">
            </div>
            <div class="group">
                <label for="pwd">密碼</label>
                <input type="password" name="pwd" id="pwd">
                <input type="hidden" name="action" value="login_Member">
            </div>
            <div class="btn-group">
                <button class="btn">登入</button>  
                <input type="hidden" name="action" value="login_Member">
            </div>
            <br>
        	<div>
                <a href="<%=request.getContextPath()%>/front-end/member/signup.jsp">還不是會員? 立即註冊</a>  
            </div>
            <br>
            <div>
                <a href="<%=request.getContextPath()%>/front-end/member/forgetPassword.jsp">忘記密碼</a>  
            </div>
            <div>
             <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
            <br>
        </form>
    </div>
</body>

</html>