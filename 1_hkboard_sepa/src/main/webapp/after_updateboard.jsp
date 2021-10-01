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
	//updateform.jsp에서 3개의 값이 전달댐: seq, title, content
	String seq = request.getParameter("seq");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	int sseq = Integer.parseInt(seq);
	
	HkDao dao = new HkDao();
	boolean isS = dao.updateBoard(new HkDto(sseq,title,content));
	if(isS){
		%>
		<script type="text/javascript">
			alert("글을 수정합니다.");
			location.href="detailboard.jsp?seq=<%=seq%>";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("글을 수정 실패~.");
			location.href="updateform.jsp?seq=<%=seq%>";
		</script>
		<%
	}
%>
</body>
</html>