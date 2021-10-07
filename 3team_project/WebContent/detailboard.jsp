<%@page import="com.hk.daos.BoardDao"%>
<%@page import="com.hk.dtos.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<%BoardDto dto=(BoardDto)request.getAttribute("dto"); %>
<body>

<h1>게시글 상세보기</h1>
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
		<td><%=dto.getTitle()%></td>
 	</tr> 
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent()%></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<button onclick="updateBoard()" title="수정폼으로 이동" >수정</button>
			<button onclick="delBoard()">삭제</button>
			<button onclick="boardList()">목록</button>
		</td>
	</tr>
</table>

<script type="text/javascript">
	//수정폼 이동
	function updateBoard(){
		location.href="BoardController.do?command=updateform&seq=<%=dto.getSeq()%>";
	}
	
	//글 삭제하기
	function delBoard(){
		location.href="BoardController.do?command=muldel&chk=<%=dto.getSeq()%>";
	}
	
</script>

</body>
</html>






