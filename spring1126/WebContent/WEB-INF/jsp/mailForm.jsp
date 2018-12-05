<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="article">
	<div id="header2">
		<h1>학습목표 : E-mail 보내기 연습</h1>
		<p id="time">2018-11-26</p>
	</div>
	<div id="content">
		<p>여기는 메인 컨텐츠</p>
		<div id="cont">
			<form action="sendEmail" method="post" enctype="multipart/form-data">
				<table width="80%">
					<tr>
						<td>Email To :</td>
						<td><input type="email" name="mailTo" required="required"/></td>
					</tr>
					<tr>
						<td>Subject :</td>
						<td><input type="text" name="subject" /></td>
					</tr>
					<tr>
						<td>Message :</td>
						<td><textarea cols="50" rows="10" name="text" ></textarea></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Send E-mail" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>