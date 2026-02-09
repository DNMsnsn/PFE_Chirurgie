<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Medecin" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Medecin</title>
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

    <!-- Bouton Ajouter -->
    <button type="button" class="bt add" onclick="showElement()"></button>
    <!-- TABLEAU DES MEDECINS -->
    <div class="maincontainer">
    	<h1>Liste Medecins</h1>
    	<div class="error">${error}</div>
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th>Spécialité</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
                <% 
                @SuppressWarnings("unchecked")
                    List<Medecin> item = (List<Medecin>) request.getAttribute("listMedecin");
                    if(item != null){
                        for(Medecin p : item){ 
                %>
                    <tr>
                        <td><%= p.getID_medecin() %></td>
                        <td><%= p.getNom_medecin() %></td>
                        <td><%= p.getPrenom_medecin() %></td>
                        <td><%= p.getTel_medecin() %></td>
                        <td><%= p.getEmail_medecin() %></td>
                        <td><%= p.getSpecialite_medecin() %></td>
                        <td><%= p.getRole_medecin() %></td>
                        <td>
                            <button class="bt edit"
                                type="button"
                                onclick="openUpdateModal(this)"
                                data-id="<%= p.getID_medecin() %>"
                                data-fnm="<%= p.getNom_medecin() %>"
                                data-snm="<%= p.getPrenom_medecin() %>"
                                data-tel="<%= p.getTel_medecin() %>"
                                data-mail="<%= p.getEmail_medecin() %>"
                                data-spc="<%= p.getSpecialite_medecin() %>"
                                data-role="<%= p.getRole_medecin() %>"
                            ></button>

                            <form action="${pageContext.request.contextPath}/DeleteMedecin" method="post">
                                <input type="hidden" name="id" value="<%= p.getID_medecin() %>">
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

    <!-- MODAL ADD/UPDATE -->
    <div class="back" id="back">
        <button class="closeBut" onclick="hideElement()"></button>

        <div class="mycontainer">
            <h1 id="ttl">Ajouter un Medecin :</h1>

            <form id="frm" action="${pageContext.request.contextPath}/AddMedecin" method="post">
                <input type="hidden" id="id" name="id">

                <label for="fnm" class="lab">Nom : </label><br>
                <input type="text" id="fnm" name="fnm" class="inp" required><br>

                <label for="snm" class="lab">Prénom : </label><br>
                <input type="text" id="snm" name="snm" class="inp" required><br>

                <label for="tel" class="lab">Téléphone : </label><br>
                <input type="text" name="tel" id="tel" maxlength="15" class="inp" required><br>

                <label for="mail" class="lab">Email : </label><br>
                <input type="email" name="mail" id="mail" class="inp"><br>

                <label for="spc" class="lab">Spécialité : </label><br>
                <input type="text" name="spc" id="spc" class="inp" required><br>

                <label for="role" class="lab">Role : </label><br>
                <input type="text" name="role" id="role" class="inp" required><br>

                <input type="submit" id="btn" class="bttn" value="Créer Medecin">
                
            </form>
        </div>
    </div>

    <!-- SCRIPT -->
    <script>
        function openUpdateModal(btn) {
            document.getElementById("id").value = btn.dataset.id;
            document.getElementById("fnm").value = btn.dataset.fnm;
            document.getElementById("snm").value = btn.dataset.snm;
            document.getElementById("tel").value = btn.dataset.tel;
            document.getElementById("mail").value = btn.dataset.mail;
            document.getElementById("spc").value = btn.dataset.spc;
            document.getElementById("role").value = btn.dataset.role;

            document.getElementById("ttl").textContent = "Modifier un Medecin :";
            document.getElementById("btn").value = "Modifier Medecin";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/UpdateMedecin";

            document.getElementById("back").style.display = "flex";
        }

        function showElement() {
            document.getElementById("id").value = "";
            document.getElementById("fnm").value = "";
            document.getElementById("snm").value = "";
            document.getElementById("tel").value = "";
            document.getElementById("mail").value = "";
            document.getElementById("spc").value = "";
            document.getElementById("role").value = "";

            document.getElementById("ttl").textContent = "Ajouter un Medecin :";
            document.getElementById("btn").value = "Créer Medecin";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/AddMedecin";

            document.getElementById("back").style.display = "flex";
        }

        function hideElement() {
            document.getElementById("back").style.display = "none";
        }
    </script>

</body>
</html>
