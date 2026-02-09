<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.CertificatSejour" %>
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste Certificats de Séjour</title>
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
    	<h1>Liste Certificats de Sejour</h1>
        <div class="error">${error}</div>
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID Sejour</th>
                    <th>Date Début</th>
                    <th>Date Fin</th>
                    <th>Nom Patient</th>
                    <th>Prénom Patient</th>
                </tr>
            </thead>

            <tbody>
                <% 
                @SuppressWarnings("unchecked")
                    List<CertificatSejour> list = (List<CertificatSejour>) request.getAttribute("listSejour");
                    for(CertificatSejour c : list){ 
                %>
                    <tr>
                        <td><%= c.getId_titre() %></td>
                        <td><%= c.getDate_debut() %></td>
                        <td><%= c.getDate_fin() %></td>
                        <td><%= c.getNom_patient() %></td>
                        <td><%= c.getPrenom_patient() %></td>
                        <td>
                            <button class="bt edit"
                                onclick="openUpdateModal(this)"
                                data-id="<%= c.getId_titre() %>"
                                data-debut="<%= c.getDate_debut() %>"
                                data-fin="<%= c.getDate_fin() %>"
                                data-pfnm="<%= c.getNom_patient() %>"
                                data-psnm="<%= c.getPrenom_patient() %>">
                            </button>
                            <form action="${pageContext.request.contextPath}/DeleteCertificatSejour" method="post">
                                <input type="hidden" name="id" value="<%= c.getId_titre() %>">
                                <input type="submit" value="" class="bt delete">
                            </form>
                            <form action="${pageContext.request.contextPath}/DownloadCertificatSejourPDF" method="get">
                                <input type="hidden" name="id" value="<%= c.getId_titre() %>">
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
            <h1 id="ttl">Modifier Certificat de Séjour :</h1>
            <div class="error">${error}</div>
            <form id="frm" action="${pageContext.request.contextPath}/UpdateCertificatSejour" method="post">
                <input type="hidden" id="idtitre" name="idtitre">
                
                <label for="debut" class="lab">Date Début : </label><br>
                <input type="date" name="debut" id="debut" class="inp" required><br>

                <label for="fin" class="lab">Date Fin : </label><br>
                <input type="date" name="fin" id="fin" class="inp" required><br>

                <label for="pfnm" class="lab">Nom Patient : </label><br>
                <input type="text" id="pfnm" name="pfnm" class="inp" required><br>
                <label for="psnm" class="lab">Prénom Patient : </label><br>
                <input type="text" id="psnm" name="psnm" class="inp" required><br>

                <input type="submit" id="btn" class="bttn" value="Modifier Sejour">
                <div class="error">${error}</div>
            </form>
        </div>
    </div>
    
    <script>
        function openUpdateModal(btn) {
            document.getElementById("idtitre").value = btn.dataset.id;
            document.getElementById("debut").value   = btn.dataset.debut;
            document.getElementById("fin").value     = btn.dataset.fin;
            document.getElementById("pfnm").value    = btn.dataset.pfnm;
            document.getElementById("psnm").value    = btn.dataset.psnm;
            
            document.getElementById("ttl").textContent = "Modifier Certificat de Séjour";
            document.getElementById("btn").value = "Modifier Sejour";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/UpdateCertificatSejour";
            
            document.getElementById("back").style.display = "flex";
        }
        
        function showElement(){
            document.getElementById("idtitre").value = null;
            document.getElementById("debut").value   = null;
            document.getElementById("fin").value     = null;
            document.getElementById("pfnm").value    = null;
            document.getElementById("psnm").value    = null;

            document.getElementById("ttl").textContent = "Ajouter Certificat de Séjour";
            document.getElementById("btn").value = "Ajouter Sejour";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/AddCertificatSejour";
            
            document.getElementById("back").style.display = "flex";
        }

        function hideElement() {
            document.getElementById("back").style.display = "none";
        }
    </script>

</body>
</html>
