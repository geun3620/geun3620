<%@page import="com.hk.dtos.HkDto"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
</head>
<%
	//상세보기페이지에서 출력된 글정보에 해당하는 글번호를 전달해야 동일한 글정보를 아래 body에 출력할 수 있다.
	String seq = request.getParameter("seq");
	int sseq = Integer.parseInt(seq);//정수형으로 변환
	
	HkDao dao = new HkDao();
	HkDto dto = dao.getBoard(sseq);
%>
<body>
	<h1>게시글 수정하기</h1>
	<form action="after_updateboard.jsp" method="post">
	<input type="hidden" name="seq" value="<%= dto.getSeq()%>"/>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td><%= dto.getSeq() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= dto.getId() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="<%= dto.getTitle() %>"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="content"><%= dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정"/>
				<button type="button" onclick="boardList()">목록</button>
			</td>
		</tr>
	</table>
	</form>
<script type="text/javascript">
	function boardList() {
		location.href="boardlist.jsp";
	}
</script>
</body>
</html>