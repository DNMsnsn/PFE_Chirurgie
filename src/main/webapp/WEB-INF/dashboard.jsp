<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="menu.jsp"></jsp:include>
    
    <%
  	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	if(session.getAttribute("fnm") == null) {
		response.sendRedirect("LoginAmsp");
		return;
	}
 	%>
 	
 	<%
 		int id = (int) session.getAttribute("id");
 		String fnm = (String) session.getAttribute("fnm");
 		String snm = (String) session.getAttribute("snm");
 		String tel = (String) session.getAttribute("tel");
 		String mail = (String) session.getAttribute("mail");
 		String func = (String) session.getAttribute("func");
 	%>
    
    <div class="board">
    	<h1>Profile : <%= id %> <%= fnm %> <%= snm %></h1>
        <label class="pl"><span class="tt">ID: </span> <%= id %></label>
        <label class="pl"><span class="tt">Nom: </span> <%= fnm %></label>
        <label class="pl"><span class="tt">Prenom: </span> <%= snm %></label>
        <label class="pl"><span class="tt">Tel: </span><%= tel %></label>
        <label class="pl"><span class="tt">Email: </span><%= mail %></label>
        <label class="pl"><span class="tt">Fonction: </span><%= func %></label>
    </div>

</body>
</html>