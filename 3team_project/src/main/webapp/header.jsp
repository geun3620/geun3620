<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>electronic goods</title>
</head>

<style type="text/css">
	table{
		border-collapse: collapse;
	}
	a{
		text-decoration: none;
	}
	button,input[type=submit] {
		border: none;
		cursor: pointer;
	}
	button:hover {
		background-color: orange;
	}
	input[type=submit]:hover{
		background-color: yellow;
	}
	header{
		padding: 1px;
	}
	header > h1 {
		text-align: center;
		color : blue;
		font-style : italic;
	}
	div.menu {
   height: 40px;
   background-color: #9EE6D7;
   text-align:center;
vertical-align:middle;
}

.menu>ul {
   width: 650px;
   height: 30px;
   list-style: none;
}

.menu>ul>li {
   float: left;
   margin-right: 50px;
}
</style>

<body>
<header>
	<h1>electronic goods</h1>
	
</header>
</body>
</html>