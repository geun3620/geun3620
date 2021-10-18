<%@page import="com.hk.dtos.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 게시판</title>

<script type="text/javascript">
	//전체 체크박스 기능
	function allSel(bool){//bool은 체크여부를 받는다(true/false)
		var chks=document.getElementsByName("chk");//chks[chk,chk,chk,chk..]
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;//각각의 체크박스에 체크여부(true/false)를 적용
	}
</script>

<style type="text/css">
table {
	border-collapse : collapse;
	table-layout : fixed;
	float : letf;
}

tr {
	border-bottom : 1px solid black;
}

th {
	border-bottom : 3px solid black;
	padding : 15px 5px 15px 5px;
	text-align : center;
}

td {
	border-bottom : 1px solid black;
	padding : 15px 5px 15px 5px;
}



</style>

</head>

<%  
	List<BoardDto> list=(List<BoardDto>)request.getAttribute("list");

	if(list==null){
		request.setAttribute("msg", "요청글에 대한 정보가 없습니다");
		pageContext.forward("error.jsp");
	}
	
%>

<body>



<h1>게시판 글목록</h1>
<form action="BoardController.do" method="post">
<input type="hidden" name="command" value="muldel"/>
<table >
	<col width="3%">
	<col width="5%">
	<col width="16%">
	<col width="50%">
	<col width="26%">
	<tr>
		<th><input type="checkbox" name="all" onclick="allSel(this.checked)" > </th>
		<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th>
	</tr>
	<%
		for(int i=0;i<list.size();i++){
			BoardDto dto=list.get(i);//list[dto,dto,dto...]--> 순차적으로 하나씩 꺼낸다(dto)
			%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>" /> </td>
				<td><%=dto.getSeq()%></td>
				<td><%=dto.getId()%></td>
				<td><a href="BoardController.do?command=detailboard&seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
				<td><%=dto.getRegdate()%></td>
			</tr>
			<%
		}
	%>
	
	<tr>
		<td colspan="5">
			<a href="BoardController.do?command=insertform"><button type="button">글쓰기</button></a>
			<input type="submit" value="글삭제" >
			<a href="BoardController.do?command=main"><button type="button">메인</button></a>
		</td>
	</tr>
</table>
</form>

</body>
</html>






