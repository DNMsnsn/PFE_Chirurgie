<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Historique" %>
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Amsp</title>
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
    
    
    <form action="${pageContext.request.contextPath}/DeleteAllHistorique" method="post">
    	<input type="submit" value="" class="closeBut">
    </form>

    <div class="maincontainer">
    	<h1>Historique</h1>
    	<div class="error">${error}</div>
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID Historique</th>
                    <th>ID Patient</th>
                    <th>ID Amsp</th>
                    <th>Type Action</th>
                    <th>Details</th>
                    <th>Date</th>
                    
                </tr>
            </thead>

            <tbody>
            	
                <% 
                @SuppressWarnings("unchecked")
                    List<Historique> item = (List<Historique>) request.getAttribute("listHist");
                    if(item != null){
                        for(Historique p : item){ 
                %>
                    <tr>
                        <td><%= p.getID_histo() %></td>
                        <td><%= p.getID_patient() %></td>
                        <td><%= p.getID_amsp() %></td>
                        <td><%= p.getType_histo() %></td>
                        <td><%= p.getDetails() %></td>
                        <td><%= p.getDt_action() %></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/DeleteHistorique" method="post">
                                <input type="hidden" name="id" value="<%= p.getID_histo() %>">
                                <input type="submit" value="" class="bt delete">
                            </form>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
        
    </div>

</body>
</html>
