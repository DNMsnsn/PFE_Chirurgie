<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.ArretTravail" %>
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste Arrêts de Travail</title>
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
    
    <button class="bt add" onclick="showElement()"></button>

    <div class="maincontainer">
    	<h1>Liste Arret de Travail</h1>
        <div class="error">${error}</div>
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID Arret</th>
                    <th>Type</th>
                    <th>Date Debut</th>
                    <th>Date Fin</th>
                    <th>Nom Patient</th>
                    <th>Prenom Patient</th>
                    <th>Nom Medecin</th>
                    <th>Prenom Medecin</th>
                </tr>
            </thead>

            <tbody>
                <% 
                @SuppressWarnings("unchecked")
                    List<ArretTravail> list = (List<ArretTravail>) request.getAttribute("listArret");
                    for(ArretTravail a : list){ 
                %>
                    <tr>
                        <td><%= a.getId_arret() %></td>
                        <td><%= a.getType() %></td>
                        <td><%= a.getDate_debut() %></td>
                        <td><%= a.getDate_fin() %></td>
                        <td><%= a.getNom_patient() %></td>
                        <td><%= a.getPrenom_patient() %></td>
                        <td><%= a.getNom_medecin() %></td>
                        <td><%= a.getPrenom_medecin() %></td>
                        <td>
                            <button class="bt edit"
                                onclick="openUpdateModal(this)"
                                data-id="<%= a.getId_arret() %>"
                                data-type="<%= a.getType() %>"
                                data-debut="<%= a.getDate_debut() %>"
                                data-fin="<%= a.getDate_fin() %>"
                                data-pfnm="<%= a.getNom_patient() %>"
                                data-psnm="<%= a.getPrenom_patient() %>"
                                data-mfnm="<%= a.getNom_medecin() %>"
                                data-msnm="<%= a.getPrenom_medecin() %>">
                            </button>
                            <form action="${pageContext.request.contextPath}/DeleteArretTravail" method="post">
                                <input type="hidden" name="id" value="<%= a.getId_arret() %>">
                                <input type="submit" value="" class="bt delete">
                            </form>
                            <form action="${pageContext.request.contextPath}/DownloadArretTravailPDF" method="get">
                                <input type="hidden" name="id" value="<%= a.getId_arret() %>">
                                <input type="submit" value="" class="bt download">
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    
    <div class="back" id="back">
        <button class="closeBut" onclick="hideElement()"></button>
        <div class="updatecontainer">
            <h1 id="ttl">Modifier Arrêt de Travail :</h1>
            <div class="error">${error}</div>
            <form id="frm" action="${pageContext.request.contextPath}/UpdateArretTravail" method="post">
                <input type="hidden" id="idarret" name="idarret">
                
                <label for="pfnm" class="lab">Nom Patient : </label><br>
                <input type="text" id="pfnm" name="pfnm" class="inp" required><br>
                <label for="psnm" class="lab">Prenom Patient : </label><br>
                <input type="text" id="psnm" name="psnm" class="inp" required><br>

                <label for="mfnm" class="lab">Nom Medecin : </label><br>
                <input type="text" id="mfnm" name="mfnm" class="inp" required><br>
                <label for="msnm" class="lab">Prenom Medecin : </label><br>
                <input type="text" id="msnm" name="msnm" class="inp" required><br>
                
                <label for="type" class="lab">Type : </label><br>
               	<select id="type" name="type" class="inp">
                	<option value="UN ARRET DE TRAVAIL">UN ARRET DE TRAVAIL</option>
                	<option value="UNE PROLONGATION">UNE PROLONGATION</option>
                	<option value="UNE REPRISE">UNE REPRISE</option>
                </select><br>

                <label for="debut" class="lab">Date Debut : </label><br>
                <input type="date" name="debut" id="debut" class="inp" required><br>

                <label for="fin" class="lab">Date Fin : </label><br>
                <input type="date" name="fin" id="fin" class="inp" required><br>

                <input type="submit" id="btn" class="bttn" value="Modifier Arrêt">
                <div class="error">${error}</div>
            </form>
        </div>
    </div>
    
    <script>
        function openUpdateModal(btn) {
            document.getElementById("idarret").value = btn.dataset.id;
            document.getElementById("type").value   = btn.dataset.type;
            document.getElementById("debut").value  = btn.dataset.debut;
            document.getElementById("fin").value    = btn.dataset.fin;
            document.getElementById("pfnm").value   = btn.dataset.pfnm;
            document.getElementById("psnm").value   = btn.dataset.psnm;
            document.getElementById("mfnm").value   = btn.dataset.mfnm;
            document.getElementById("msnm").value   = btn.dataset.msnm;

            document.getElementById("ttl").textContent = "Modifier Arrêt de Travail";
            document.getElementById("btn").value = "Modifier Arrêt";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/UpdateArretTravail";
            
            document.getElementById("back").style.display = "flex";
            
        }
        
        function showElement(){
            document.getElementById("idarret").value = null;
            document.getElementById("type").value    = null;
            document.getElementById("debut").value   = null;
            document.getElementById("fin").value     = null;
            document.getElementById("pfnm").value    = null;
            document.getElementById("psnm").value    = null;
            document.getElementById("mfnm").value    = null;
            document.getElementById("msnm").value    = null;

            document.getElementById("ttl").textContent = "Ajouter Arrêt de Travail";
            document.getElementById("btn").value = "Ajouter Arrêt";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/AddArretTravail";
            
            document.getElementById("back").style.display = "flex";
        }

        function hideElement() {
            document.getElementById("back").style.display = "none";
        }
    </script>

</body>
</html>
