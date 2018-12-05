<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>MemberListView</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<h3>
		<p style="color: green">리스트 뷰는 Console에~</p>
	</h3>
	<button id="Btn">jsp에 출력</button>
	<div id="target"></div>

	<script>
		$(function() {
			let str = "";
			$.ajax({
				url : "members/memberList",
				success : function(data) {
					console.log(data);
				}
			});
			$("#Btn").click(function() {
				$.ajax({
					url : "members/memberList",
					dataType : 'json',
					cache : false,
					success : function(data) {
						let tag;
						$.each(data, function(idx, key) {
							str = key;
							if(idx !== 0){
							tag += "<tr>";							
							tag += "<td>" + str + "</td>";
							tag += "<td> :: </td>";
							tag += "<td>" + idx + "</td>";
							tag += "<td> :: </td>";
							tag += "<td>" + key.num + "</td>";
							tag += "<td>" + key.id + "</td>";
							tag += "<td>" + key.name + "</td>";
							tag += "<td>" + key.address + "</td>";
							tag += "</tr>";
							}
						});

						$('#target').html(tag);

					}
				});

			});

		});
	</script>
</body>
</html>