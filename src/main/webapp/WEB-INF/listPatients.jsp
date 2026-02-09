<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Patient" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Patients</title>
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

    <!-- TABLEAU DES PATIENTS -->
    <div class="maincontainer">
    	<h1>Liste Patients</h1>
    	<div class="error">${error}</div>
    	
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th>Adresse</th>
                    <th>Date de naissance</th>
                    <th>Groupe Sanguin</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
                <% 
                @SuppressWarnings("unchecked")
                    List<Patient> item = (List<Patient>) request.getAttribute("listPatient");
                    if(item != null){
                        for(Patient p : item){ 
                %>
                    <tr>
                        <td><%= p.getID_patient() %></td>
                        <td><%= p.getNom_patient() %></td>
                        <td><%= p.getPrenom_patient() %></td>
                        <td><%= p.getTel_patient() %></td>
                        <td><%= p.getEmail_patient() %></td>
                        <td><%= p.getAdresse_patient() %></td>
                        <td><%= p.getDt_naiss_patient() %></td>
                        <td><%= p.getGrp_sang_patient() %></td>
                        <td>
                            <button class="bt edit"
                                type="button"
                                onclick="openUpdateModal(this)"
                                data-id="<%= p.getID_patient() %>"
                                data-fnm="<%= p.getNom_patient() %>"
                                data-snm="<%= p.getPrenom_patient() %>"
                                data-tel="<%= p.getTel_patient() %>"
                                data-mail="<%= p.getEmail_patient() %>"
                                data-adrs="<%= p.getAdresse_patient() %>"
                                data-dt="<%= p.getDt_naiss_patient() %>"
                                data-blood="<%= p.getGrp_sang_patient() %>"
                            ></button>

                            <form action="${pageContext.request.contextPath}/DeletePatient" method="post">
                                <input type="hidden" name="id" value="<%= p.getID_patient() %>">
                                <input type="submit" value="" class="bt delete">
                            </form>
                            <form action="${pageContext.request.contextPath}/DownloadPatientQrPDF" method="get">
                                <input type="hidden" name="id" value="<%= p.getID_patient() %>">
                                <input type="hidden" name="nom" value="<%= p.getNom_patient() %>">
                                <input type="hidden" name="prenom" value="<%= p.getPrenom_patient() %>">
                                <input type="hidden" name="tel" value="<%= p.getTel_patient() %>">
                                <input type="hidden" name="mail" value="<%= p.getEmail_patient() %>">
                                <input type="hidden" name="adr" value="<%= p.getAdresse_patient() %>">
                                <input type="hidden" name="dt" value="<%= p.getDt_naiss_patient() %>">
                                <input type="hidden" name="grp" value="<%= p.getGrp_sang_patient() %>">
                                
                                <input type="submit" value="" class="bt download">
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
            <h1 id="ttl">Ajouter un patient :</h1>

            <form id="frm" action="${pageContext.request.contextPath}/AddPatient" method="post">
                <input type="hidden" id="id" name="id">

                <label for="fnm" class="lab">Nom : </label><br>
                <input type="text" id="fnm" name="fnm" class="inp" required><br>

                <label for="snm" class="lab">Prénom : </label><br>
                <input type="text" id="snm" name="snm" class="inp" required><br>

                <label for="dt" class="lab">Date de naissance : </label><br>
                <input type="date" id="dt" name="dt" class="inp" required><br>

                <label for="sexe" class="lab">Sexe : </label><br>
                <select name="sexe" id="sexe" class="inp">
                    <option value="Homme">Homme</option>
                    <option value="Femme">Femme</option>
                </select><br>

                <label for="tel" class="lab">Téléphone : </label><br>
                <input type="text" name="tel" id="tel" maxlength="15" class="inp" required><br>

                <label for="mail" class="lab">Email : </label><br>
                <input type="email" name="mail" id="mail" class="inp"><br>

                <label for="adrs" class="lab">Adresse : </label><br>
                <input type="text" name="adrs" id="adrs" class="inp" required><br>

                <label for="blood" class="lab">Groupe Sanguin : </label><br>
                <select name="blood" id="blood" class="inp">
                    <option value="A+">A+</option>
                    <option value="A-">A-</option>
                    <option value="B+">B+</option>
                    <option value="B-">B-</option>
                    <option value="AB+">AB+</option>
                    <option value="AB-">AB-</option>
                    <option value="O+">O+</option>
                    <option value="O-">O-</option>
                </select><br>

                <input type="submit" id="btn" class="bttn" value="Créer Patient">
                
            </form>
        </div>
    </div>

    <!-- SCRIPT -->
    <script>
        function openUpdateModal(btn) {
            document.getElementById("id").value = btn.dataset.id;
            document.getElementById("fnm").value = btn.dataset.fnm;
            document.getElementById("snm").value = btn.dataset.snm;
            document.getElementById("dt").value = btn.dataset.dt;
            document.getElementById("tel").value = btn.dataset.tel;
            document.getElementById("mail").value = btn.dataset.mail;
            document.getElementById("adrs").value = btn.dataset.adrs;
            document.getElementById("blood").value = btn.dataset.blood;

            document.getElementById("ttl").textContent = "Modifier un patient :";
            document.getElementById("btn").value = "Modifier Patient";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/UpdatePatient";

            document.getElementById("back").style.display = "flex";
        }

        function showElement() {
            document.getElementById("id").value = "";
            document.getElementById("fnm").value = "";
            document.getElementById("snm").value = "";
            document.getElementById("dt").value = "";
            document.getElementById("tel").value = "";
            document.getElementById("mail").value = "";
            document.getElementById("adrs").value = "";
            document.getElementById("blood").value = "A+";

            document.getElementById("ttl").textContent = "Ajouter un patient :";
            document.getElementById("btn").value = "Créer Patient";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/AddPatient";

            document.getElementById("back").style.display = "flex";
        }

        function hideElement() {
            document.getElementById("back").style.display = "none";
        }
    </script>

</body>
</html>
