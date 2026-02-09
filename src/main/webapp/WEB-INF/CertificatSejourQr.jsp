<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Certicat Sejour</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<jsp:include page="menu.jsp"></jsp:include>
	
	<%
		int id = (Integer) request.getAttribute("id");
		String nomPat = (String) request.getAttribute("nomPat");
		String prePat = (String) request.getAttribute("prePat");
		String debut = (String) request.getAttribute("debut");
		String fin = (String) request.getAttribute("fin");
	%>
	
	<div class="board">
	
		<h1>Hopital CHAHIDS MAHMOUDI</h1>
		<h2>Certificat De Sejour</h2>
		
		<label class="pl"><span class="tt">ID: </span><%= id %></label>
		<label class="pl"><span class="tt">Patient: </span><%= nomPat %> <%= prePat %></label>
		<label class="pl"><span class="tt">Date debut: </span><%= debut %></label>
		<label class="pl"><span class="tt">Date Fin: </span><%= fin %></label>
		
		<form action="${pageContext.request.contextPath}/DownloadCertificatSejourPDF" method="get">
			<input type="hidden" name="id" value="<%= id %>">
			<input type="submit" value="" class="bt download">
		</form>
                            
	</div>

</body>
</html>