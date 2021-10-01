<%@page import="com.hk.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록 보기</title>
</head>
<%
	HkDao dao = new HkDao();
	List<HkDto> list = dao.getBoardList();
%>
<body>
	<h1>게시판 글목록</h1>
	<table border="1">
		<col width="50px">
		<col width="100px">
		<col width="300px">
		<col width="200px">
		<tr>
			<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th>
		</tr>
		<%
			for(int i=0; i<list.size();i++){
				HkDto dto = list.get(i);//list[dto,dto,dto..]--->순차적으로 하나씩 꺼낸다.
				%>
				<tr>
					<td><%=dto.getSeq()%></td>
					<td><%=dto.getId()%></td>
					<td><a href="detailboard.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
					<td><%=dto.getRegdate()%></td>
				</tr>
				<%
			}
		%>
		<tr>
			<td colspan="4">
				<a href="insertboard.jsp">글쓰기</a>
				<a href="index.jsp">메인</a>
			</td>
		</tr>
	</table>
</body>
</html>