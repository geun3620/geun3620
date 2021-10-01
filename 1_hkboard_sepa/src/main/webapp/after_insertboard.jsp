<%@page import="com.hk.dtos.HkDto"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//요청정보에서 전달된 파라미터를 받는 작업j
		//이때 전달된 파라미터를 받는 메서드: getParameter("name")
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HkDao dao = new HkDao();
		
		boolean isS = dao.insertBoard(new HkDto(id, title, content));
		if(isS){
			//sendRedirect(url)는 서버가 클라이언트에 다시 해당 URL을 요청하라고 전달하는것
			response.sendRedirect("boardlist.jsp");
		}else{
			%>
			<script type="text/javascript">
				alert("글추가실패~");
				location.href="insertboard.jsp";
			</script>
			<%
		}
	
	%>
</body>
</html>