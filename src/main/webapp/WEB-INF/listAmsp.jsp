<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Amsp" %>
   
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
    
    <button type="button" class="bt add" onclick="showElement()"></button>

    <div class="maincontainer">
    	<h1>Liste AMSP</h1>
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th>Fonction</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
            	
                <% 
                @SuppressWarnings("unchecked")
                    List<Amsp> item = (List<Amsp>) request.getAttribute("listAmsp");
                    if(item != null){
                        for(Amsp p : item){ 
                %>
                    <tr>
                        <td><%= p.getID_amsp() %></td>
                        <td><%= p.getNom_amsp() %></td>
                        <td><%= p.getPrenom_amsp() %></td>
                        <td><%= p.getTel_amsp() %></td>
                        <td><%= p.getEmail_amsp() %></td>
                        <td><%= p.getFonction_amsp() %></td>
                        <td>
                            <button class="bt edit"
                                type="button"
                                onclick="openUpdateModal(this)"
                                data-id="<%= p.getID_amsp() %>"
                                data-fnm="<%= p.getNom_amsp() %>"
                                data-snm="<%= p.getPrenom_amsp() %>"
                                data-tel="<%= p.getTel_amsp() %>"
                                data-mail="<%= p.getEmail_amsp() %>"
                                data-func="<%= p.getFonction_amsp() %>"
                                data-psw="<%= p.getPassword() %>"
                            ></button>
                            <form action="${pageContext.request.contextPath}/DeleteAmsp" method="post">
                                <input type="hidden" name="id" value="<%= p.getID_amsp() %>">
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
        
        <!-- MODAL -->
        <div class="back" id="back">
            <button class="closeBut" onclick="hideElement()"></button>
            
            <div class="mycontainer">
                <h1 id="ttl">Ajouter un Assistant Médical :</h1>
                
                <form id="frm" action="${pageContext.request.contextPath}/AddAmsp" method="post">
                    <input type="hidden" id="id" name="id">
                
                    <label for="fnm" class="lab">Nom : </label><br>
                    <input type="text" id="fnm" name="fnm" class="inp" required><br>
                    
                    <label for="snm" class="lab">Prénom : </label><br>
                    <input type="text" id="snm" name="snm" class="inp" required><br>
                    
                    <label for="tel" class="lab">Téléphone : </label><br>
                    <input type="text" name="tel" id="tel" maxlength="15" class="inp" required><br>
                    
                    <label for="mail" class="lab">Email : </label><br>
                    <input type="email" name="mail" id="mail" class="inp"><br>
                    
                    <label for="psw" class="lab">Password : </label><br>
                    <input type="password" name="psw" id="psw" class="inp" required><br>
                    
                    <label for="func" class="lab">Fonction : </label><br>
                    <input type="text" name="func" id="func" class="inp" required><br>
                    
                    <input type="submit" id="btn" class="bttn" value="Créer Assistant Médical">
                    
                    <div class="error">${error}</div>
                </form>
            </div>
        </div>
    </div>
    
    <script>
        function openUpdateModal(btn) {
            document.getElementById("id").value = btn.dataset.id;
            document.getElementById("fnm").value = btn.dataset.fnm;
            document.getElementById("snm").value = btn.dataset.snm;
            document.getElementById("tel").value = btn.dataset.tel;
            document.getElementById("mail").value = btn.dataset.mail;
            document.getElementById("psw").value = btn.dataset.psw;
            document.getElementById("func").value = btn.dataset.func;
            
            document.getElementById("ttl").textContent = "Modifier un Assistant Médical :";
            document.getElementById("btn").value = "Modifier";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/UpdateAmsp";
            
            document.getElementById("back").style.display = "flex";
        }
        
        function showElement() {
            document.getElementById("id").value = "";
            document.getElementById("fnm").value = "";
            document.getElementById("snm").value = "";
            document.getElementById("tel").value = "";
            document.getElementById("mail").value = "";
            document.getElementById("psw").value = "";
            document.getElementById("func").value = "";
            
            document.getElementById("ttl").textContent = "Ajouter un Assistant Médical :";
            document.getElementById("btn").value = "Créer Assistant Médical";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/AddAmsp";
            
            document.getElementById("back").style.display = "flex";
        }

        function hideElement() {
            document.getElementById("back").style.display = "none";
        }
    </script>

</body>
</html>
