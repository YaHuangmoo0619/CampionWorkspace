<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*" %>
<%@ page import="com.member_mail.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="com.employee.model.*" %>
<%@ page import="com.campsite_owner.model.*" %>

<% MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); %>

<jsp:useBean id="member_mailSvc" class="com.member_mail.model.Member_mailService" />
<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService" />
<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService" />
<jsp:useBean id="memberSvc"	class="com.member.model.MemberService" />
<%
	Member_mailService member_mailSvc2 = new Member_mailService();
	List<Member_mailVO> list = member_mailSvc2.getAll();
	Set<String> set = new HashSet<String>();
	for(Member_mailVO mailVO : list){
		if(mailVO.getRcpt_no().equals(memberVO.getMbr_no())){
			MemberVO member = memberSvc.getOneMember(mailVO.getSend_no());
			EmployeeVO employee = employeeSvc.getOneEmployee(mailVO.getSend_no());
			Campsite_ownerVO campsite_owner =  campsite_ownerSvc.getOneCampsite_owner(mailVO.getSend_no());
			if(member != null){
				set.add(member.getMbr_no().toString());
			}else if(employee != null && employee.getEmp_no() > 90001){
				set.add(employee.getEmp_no().toString());
			}else if(campsite_owner != null){
				set.add(campsite_owner.getCso_no().toString());
			}
		}
		if(mailVO.getSend_no().equals(memberVO.getMbr_no())){
			MemberVO member = memberSvc.getOneMember(mailVO.getRcpt_no());
			EmployeeVO employee = employeeSvc.getOneEmployee(mailVO.getRcpt_no());
			Campsite_ownerVO campsite_owner =  campsite_ownerSvc.getOneCampsite_owner(mailVO.getRcpt_no());
			if(member != null){
				set.add(member.getMbr_no().toString());
			}else if(employee != null && employee.getEmp_no() == 90001){
				set.add(employee.getEmp_no().toString());
			}else if(campsite_owner != null){
				set.add(campsite_owner.getCso_no().toString());
				}
			}
		}
			
	request.setAttribute("set",set);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<title>所有會員站內信列表</title>
<%@ include file="/part-of/partOfCampion_frontTop_css.txt"%>
<%@ include file="/part-of/partOfCampion_backLeft_css.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_css.txt"%>
<style>
body{
	background-color: #4e5452;
	color: #4e5452;
}
div.left{
	margin-top: 20px;
}
div.right{
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}
a.content{
	color: #80c344;
	font-size: 0.6em;
}
a.content:hover {
	color: #4B7F52;
}
input.confirm {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.confirm:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
#confirmTop:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
div.forSearchs{
	margin: 0 auto;
	width: 70%;
	hieght: 50px;
	position: relative;
}
div.forSearchsMore{
	top: 110%;
	left: 15%;
	width: 70%;
	position: absolute;
	background-color: #fff;
	box-shadow: 0 1px 5px 0 #4e5452;
	display: none
}
#mail_cont{
	border-radius:5px;
	background-color:#eee;
	border:none;
	padding:5px 15px;
	width:50%;
}
span{
	 font-size:0.8em;
	 font-weight:444;
	 padding: 7px;
	 background-color: #eee;
	 border-radius:5px;
}
span:hover{
	cursor: pointer;
	background-color: #4e5452;
	color: #eee;
}
label, select, input {
	font-size: 0.8em;
}
table{
	width: 700px;
	margin: 30px auto;
/* 	border: 1px solid #4e5452; */
}
th, td{
	text-align: center;
/* 	border: 1px solid #4e5452; */
	padding: 10px 15px;
}
td.function{
	text-align: justify;	
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
input.change{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.change:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
#focus{
	margin-right: -5px;
}
tr {
/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}
tr:hover {
	box-shadow: 0 1px 5px 0 #4e5452 inset;
	cursor: pointer;
}
</style>

</head>
<body onload="connection()">
<%@ include file="/part-of/partOfCampion_frontTop_body.txt"%>
<%@ include file="/part-of/partOfCampion_arrowToTop_body.txt"%>
<div class="container">
	<div class="row">
<!-- 		<div class= "left col-3"> -->
<%-- 		<%@ include file="/part-of/partOfCampion_backLeft_body.txt"%></div> --%>
		<div class="right col">
			<h5 style="color: #80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<h3>會員站內信列表&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/front-end/member_mail/addMember_mail.jsp">寄信</a>&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/front-end/member_mail/listAllMember_mail_send.jsp">寄件備份列表</a>
			</h3>
			<hr>
			<div class="forSearchs" id="forSearchs">
					<ul>
						<li>
							<form method="post"	action="<%=request.getContextPath()%>/member_mail/member_mail.do">
								<label	for="mail_cont"></label>
								<input type="text" name="mail_cont" id="mail_cont" placeholder="依信件內容查詢">
								<input type="hidden" name="action"	value="compositeSearchTop">
								<input type="hidden" name="where"	value="rcpt">
								<input type="submit" value="送出查詢" class="confirm" id="confirmTop">&nbsp;<span>進階查詢</span>
							</form>
					</li>
					</ul>
					<div class="forSearchs forSearchsMore" id="forSearchsMore">
					<ul>
						<li>
							<form method="post"	action="<%=request.getContextPath()%>/member_mail/member_mail.do">
<%-- 								<jsp:useBean id="member_mailSvc" class="com.member_mail.model.Member_mailService" /> --%>
<%-- 								<jsp:useBean id="campsite_ownerSvc" class="com.campsite_owner.model.Campsite_ownerService" /> --%>
<%-- 								<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService" /> --%>
<%-- 								<jsp:useBean id="memberSvc"	class="com.member.model.MemberService" /> --%>
	
								<label for="mail_no" style="display:none;">信件編號:</label>
								<select size="1" name="mail_no" id="mail_no" style="display:none;">
									<option value="no">--請選擇--</option>
									<c:forEach var="member_mailVO" items="${member_mailSvc.all}">
										<option value="${member_mailVO.mail_no}">${member_mailVO.mail_no}</option>
										
									</c:forEach>
								</select>
								<br>
								<label for="send_no">寄件人:</label>
								<select size="1" name="send_no" id="send_no">
									<option value="no">--請選擇--</option>
									<c:forEach var="rcptNo" items="${set}">
										<c:if test="${memberVO.mbr_no != rcptNo}">
											<option value="${rcptNo}" ${employeeSvc.getOneEmployee(Integer.valueOf(rcptNo)).emp_no >= 90001? 'style="display:none;"':''}>
													${memberSvc.getOneMember(Integer.valueOf(rcptNo)).name}
													${employeeSvc.getOneEmployee(Integer.valueOf(rcptNo)).emp_no >= 90001? '客服專員':''}
													${campsite_ownerSvc.getOneCampsite_owner(Integer.valueOf(rcptNo)).name}
											</option>
										</c:if>
									</c:forEach>
								</select>
								<br>
								<label for="rcpt_no">收件人:</label> 
								<select size="1" name="rcpt_no" id="rcpt_no">
									<option value="no">--請選擇--</option>
									<c:forEach var="rcptNo" items="${set}">
										<c:if test="${memberVO.mbr_no != rcptNo}">
											<option value="${rcptNo}" ${employeeSvc.getOneEmployee(Integer.valueOf(rcptNo)).emp_no >= 90001? 'style="display:none;"':''}>
													${memberSvc.getOneMember(Integer.valueOf(rcptNo)).name}
													${campsite_ownerSvc.getOneCampsite_owner(Integer.valueOf(rcptNo)).name}
											</option>
										</c:if>
									</c:forEach>
								</select>
								<br>
								<label for="mail_time">發信日期:</label>
								<input	type="text" name="mail_time" id="mail_time"></input>
								<br>
								<label	for="mail_cont">信件內容:</label>
								<input type="text"	name="mail_cont" id="mail_cont">
								<br>
								<label	for="mail_stat">信件狀態:</label>
								<select size="1" name="mail_stat" id="mail_stat">
									<option value="no">--請選擇--</option>
									<option value="0">收件</option>
									<option value="1">寄件</option>
								</select>
								<br>
								<label for="mail_read_stat">信件閱讀狀態:</label>
								<select	size="1" name="mail_read_stat" id="mail_read_stat">
									<option value="no">--請選擇--</option>
									<option value="0">未讀</option>
									<option value="1">已讀</option>
								</select>
								<br>
								<input type="hidden" name="action" value="compositeSearch">
								<input type="hidden" name="where"	value="rcpt">
								<input type="submit" value="送出查詢" class="confirm">
							</form>
						</li>
					</ul>
					</div>
				</div>
				<div style="text-align:center;font-weight:555;">
					<div style="width: 150px;display:inline-block;">寄件人</div>
					<div style="width: 150px;display:inline-block;">收件人</div>
					<div style="width: 250px;display:inline-block;">信件內容</div>
					<div style="width: 150px;display:inline-block;">信件日期</div>
				</div>
<%-- 			--${member_mailVO != null? member_mailVO.rcpt_no:'123' }-- --%>
			<c:if test="${member_mailVO != null}">
			<!-- insert回傳的VO沒有信件編號 -->
					<div onclick="sendNotify()" id="sendNotify" style="display:none;">${member_mailVO.rcpt_no}/mail</div>
			</c:if>
			<table>
			<tbody id="mailTable">
<!-- 				<tr> -->
<!-- 					<th style="width:50px">編號</th> -->
<!-- 					<th style="width:50px">寄件人</th> -->
<!-- 					<th style="width:50px">收件人</th> -->
<!-- 					<th style="width:200px">內容</th> -->
<!-- 					<th style="width:50px">信件狀態</th> -->
<!-- 					<th style="width:100px">信件閱讀狀態</th> -->
<!-- 					<th style="width:100px">發信時間</th> -->
<!-- 					<th style="width:100px"><a class="content" href="#focus" style="text-decoration: none;">看更新</a><a -->
<!-- 						id="first" style="text-decoration: none;"></a></th> -->
<!-- 				</tr> -->
<%-- 				<jsp:useBean id="member_mailSvc" class="com.member_mail.model.Member_mailService"/> --%>
				<c:forEach var="member_mailVO" items="${member_mailSvc.all}">
<%-- 					<tr ${member_mailVO.mail_no == param.mail_no ? 'bgcolor=#eee':''}> --%>
					<c:if test="${memberVO.mbr_no == member_mailVO.rcpt_no && member_mailVO.mail_stat == 0}">
					<tr>
<%-- 						<c:if test="${member_mailVO.mail_no==param.mail_no}"> --%>
<%-- 							<td>${member_mailVO.mail_no}<a id="focus"></a></td> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${member_mailVO.mail_no!=param.mail_no}"> --%>
						<td style="display:none;">${member_mailVO.mail_no}</td>
<%-- 						</c:if> --%>
						<td>${employeeSvc.getOneEmployee(member_mailVO.send_no).emp_no > 90001?'客服專員':''}${memberSvc.getOneMember(member_mailVO.send_no).name}${campsite_ownerSvc.getOneCampsite_owner(member_mailVO.send_no).name}</td>
						<td>${memberSvc.getOneMember(member_mailVO.rcpt_no).name}</td>
						<c:set var="mail_cont" value="${member_mailVO.mail_cont}" />
							<c:if test="${mail_cont.length() > 10}">
								<td>${fn:substring(mail_cont, 0, 10)}...</td>
							</c:if>
							<c:if test="${mail_cont.length() <= 10}">
								<td>${mail_cont}</td>
							</c:if>
						<td style="display:none;">${member_mailVO.mail_stat}</td>
						<td class="mail_read_stat" style="display:none;">${member_mailVO.mail_read_stat}</td>
						<c:set var="mail_time" value="${member_mailVO.mail_time}" />
							<td>${fn:substring(mail_time, 0, 10)}</td>
<!-- 						<td> -->
<%-- 							<form method="post" action="<%=request.getContextPath()%>/member_mail/member_mail.do"> --%>
<!-- 								<input class="change" type="submit" value="修改"> -->
<%-- 								<input type="hidden" name="mail_no" value="${member_mailVO.mail_no}"> --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 							</form> -->
<!-- 						</td> -->
					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="/part-of/partOfCampion_arrowToTop_js.txt"%>
<%@ include file="/part-of/partOfCampion_frontTop_js.txt"%>

<script>
	$("body").on("click","tr",function(e){
// 		console.log('click');
		let mail_no = e.currentTarget.children[0].innerText;
// 		console.log(mail_no);
		window.location.href="<%=request.getContextPath()%>/member_mail/member_mail.do?mail_no="+ mail_no + "&action=read";
	});
	for (let i = 0; i < $(".mail_read_stat").length; i++) {
		if ($(".mail_read_stat")[i].innerText === '1') {
			$($(".mail_read_stat")[i]).parent()[0].style.backgroundColor = '#eee';
		}
		if ($(".mail_read_stat")[i].innerText === '0') {
			$($(".mail_read_stat")[i]).parent()[0].style.fontWeight = '555';
		}
	}
	
	let countSearch = 0;
	$("span").click(function(e){
		countSearch++;
		if (countSearch % 2 == 1) {
			$("#forSearchsMore")[0].style.display="inline";
			$("#confirmTop")[0].setAttribute("disabled","");
			$("#confirmTop")[0].style.backgroundColor="#4B7F52";
			$("#confirmTop")[0].style.color="#80c344";
			$("#confirmTop")[0].style.cursor="context-menu";
		} else {
			$("#forSearchsMore")[0].style.display="none";
			$("#confirmTop")[0].removeAttribute("disabled");
			$("#confirmTop")[0].style.backgroundColor="#80c344";
			$("#confirmTop")[0].style.color="#4e5452";
		}
	});
	
</script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
jQuery.datetimepicker.setLocale('zh-TW');
$(function(){
	$('#mail_time').datetimepicker({
		format:'Y-m-d',
		maxDate:'+1970/01/01',
		timepicker:false
	});
});
</script>

</body>

</html>