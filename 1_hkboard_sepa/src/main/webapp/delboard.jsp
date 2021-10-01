<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
</head>
<body>
<%
	String seq = request.getParameter("seq");
	int sseq = Integer.parseInt(seq);
	
	HkDao dao = new HkDao();
	boolean isS = dao.delBoard(sseq);
	if(isS){
		%>
		<script type="text/javascript">
			alert("글을 삭제합니다.");
			location.href="boardlist.jsp";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("글을 삭제 실패.");
			location.href="detailborad.jsp?seq=<%=seq%>";
		</script>
		<%
	}
%>
</body>
</html>