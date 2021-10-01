<%@page import="com.hk.dtos.HkDto"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<%
	//요청된 해당 글의 정보를 DB로 부터 가져와서 밑에 body에 출력해야한다.
	//--->DB에 연겨래서 data입력,조회등등 의 작업을 하는 객체??? DAO이다...
	//그래서 Dao객체를 생성하고
	HkDao dao = new HkDao();
	//요청정보에서 전달된 파라미터를 받는 작업
	//이때 전달된 파라미터를 받는 메서드: getParameter("name")
	int seq = Integer.parseInt(request.getParameter("seq"));
	HkDto dto = dao.getBoard(seq);
	//dao.getBoard(seq);
%>
<body>
	<h1>게시글 상세보기</h1>
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
			<td><%= dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60"><%= dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<button onclick="updateBoard()">수정</button>
				<button onclick="delboard()">삭제</button>
				<button onclick="boardList()">목록</button>
			</td>
		</tr>
	</table>
<script type="text/javascript">
	//수정폼 이동
	function updateBoard() {
		location.href="updateform.jsp?seq=<%=dto.getSeq()%>";
	}
	
	//글 삭제하기
	function delboard() {
		location.href="delboard.jsp?seq=<%=dto.getSeq()%>";
	}
	
	//글목록 조회
	function boardList() {
		location.href="boardlist.jsp";
	}
</script>
</body>
</html>