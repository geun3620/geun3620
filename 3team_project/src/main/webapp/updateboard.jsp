<%@page import="com.hk.daos.BoardDao"%>
<%@page import="com.hk.dtos.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
</head>

<%
	BoardDto dto=(BoardDto)request.getAttribute("dto");
%>

<script type="text/javascript">
	//목록으로 돌아가기
	function boardList(){
		location.href="BoardController.do?command=boardlist";
	}
</script>

<body>

<h1>게시글 수정하기</h1>
<form action="BoardController.do" method="post">
	<input type="hidden" name="command" value="updateboard"/>
	<input type="hidden" name="seq" value="<%=dto.getSeq()%>"/>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td><%=dto.getSeq()%></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=dto.getId()%></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="<%=dto.getTitle()%>"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="content"><%=dto.getContent()%></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정"/>
				<button type="button" onclick="boardList()">목록</button>
			</td>
		</tr>
	</table>
</form>

</body>
</html>






