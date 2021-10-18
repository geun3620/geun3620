<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 추가</title>
<script type="text/javascript">
	function boardList(){
		//JS: BOM ---> 위치와 관련있는 객체 --> Location
		location.href="BoardController.do?command=boardlist";
	}
</script>

<style type="text/css">
table {
	border-collapse : collapse;
	table-layout : fixed;
}

tr {
	border-bottom : 1px solid black;
}

th {
	border : 1px solid black;
	padding : 15px 5px 15px 5px;
	text-align : center;
}

td {
	border : 1px solid black;
}

input {

}
</style>

</head>
<body>

<h1>게시글 작성하기</h1>
<form action="BoardController.do" method="post">
	<input type="hidden" name="command" value="insertboard"/>
	<table>
	<col width="15%">
	<col width="85%">
		<tr>
			<th>작성자</th>
			<td><input type="text" name="id" /></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="등록" />
				<input type="button" value="목록" onclick="boardList()" />
			</td>
		</tr>
	</table>
</form>

</body>
</html>






