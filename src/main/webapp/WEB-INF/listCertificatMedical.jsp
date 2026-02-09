<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.CertificatMedical" %>
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste Certificats Médicaux</title>
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
    	<h1>Liste Certificats Medicals</h1>
        <div class="error">${error}</div>
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID Certificat</th>
                    <th>Date Emission</th>
                    <th>Statut</th>
                    <th>Nom Patient</th>
                    <th>Prenom Patient</th>
                    <th>Nom Medecin</th>
                    <th>Prenom Medecin</th>
                </tr>
            </thead>

            <tbody>
                <% 
                	@SuppressWarnings("unchecked")
                    List<CertificatMedical> list = (List<CertificatMedical>) request.getAttribute("listCertificat");
                    for(CertificatMedical c : list){ 
                %>
                    <tr>
                        <td><%= c.getId_certificat() %></td>
                        <td><%= c.getDate_emission() %></td>
                        <td><%= c.getStatut() %></td>
                        <td><%= c.getNom_patient() %></td>
                        <td><%= c.getPrenom_patient() %></td>
                        <td><%= c.getNom_medecin() %></td>
                        <td><%= c.getPrenom_medecin() %></td>
                        <td>
                            <button class="bt edit"
                                onclick="openUpdateModal(this)"
                                data-id="<%= c.getId_certificat() %>"
                                data-statut="<%= c.getStatut() %>"
                                data-pfnm="<%= c.getNom_patient() %>"
                                data-psnm="<%= c.getPrenom_patient() %>"
                                data-mfnm="<%= c.getNom_medecin() %>"
                                data-msnm="<%= c.getPrenom_medecin() %>">
                            </button>
                            <form action="${pageContext.request.contextPath}/DeleteCertificatMedical" method="post">
                                <input type="hidden" name="id" value="<%= c.getId_certificat() %>">
                                <input type="submit" value="" class="bt delete">
                            </form>
                            <form action="${pageContext.request.contextPath}/DownloadCertificatMedicalPDF" method="get">
                                <input type="hidden" name="id" value="<%= c.getId_certificat() %>">
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
            <h1 id="ttl">Modifier Certificat Médical :</h1>
            <div class="error">${error}</div>
            <form id="frm" action="${pageContext.request.contextPath}/UpdateCertificatMedical" method="post">
                <input type="hidden" id="idcertificat" name="idcertificat">
                
                <label for="statut" class="lab">Statut : </label><br>
                <select id="statut" name="statut" class="inp">
                	<option value="APTE">Apte</option>
                	<option value="INAPTE">Inapte</option>
                </select><br>

                <label for="pfnm" class="lab">Nom Patient : </label><br>
                <input type="text" id="pfnm" name="pfnm" class="inp" required><br>
                <label for="psnm" class="lab">Prenom Patient : </label><br>
                <input type="text" id="psnm" name="psnm" class="inp" required><br>

                <label for="mfnm" class="lab">Nom Medecin : </label><br>
                <input type="text" id="mfnm" name="mfnm" class="inp" required><br>
                <label for="msnm" class="lab">Prenom Medecin : </label><br>
                <input type="text" id="msnm" name="msnm" class="inp" required><br>

                <input type="submit" id="btn" class="bttn" value="Modifier Certificat">
                <div class="error">${error}</div>
            </form>
        </div>
    </div>
    
    <script>
        function openUpdateModal(btn) {
            document.getElementById("idcertificat").value = btn.dataset.id;
            document.getElementById("statut").value     = btn.dataset.statut;
            document.getElementById("pfnm").value       = btn.dataset.pfnm;
            document.getElementById("psnm").value       = btn.dataset.psnm;
            document.getElementById("mfnm").value       = btn.dataset.mfnm;
            document.getElementById("msnm").value       = btn.dataset.msnm;
            
            document.getElementById("ttl").textContent = "Modifier Certificat Médical";
            document.getElementById("btn").value = "Modifier Certificat Medical";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/UpdateCertificatMedical";
            
            document.getElementById("back").style.display = "flex";
        }
        
        function showElement(){
            document.getElementById("idcertificat").value = null;
            document.getElementById("statut").value      = null;
            document.getElementById("pfnm").value        = null;
            document.getElementById("psnm").value        = null;
            document.getElementById("mfnm").value        = null;
            document.getElementById("msnm").value        = null;

            document.getElementById("ttl").textContent = "Ajouter Certificat Médical";
            document.getElementById("btn").value = "Ajouter Certificat";
            document.getElementById("frm").action = "${pageContext.request.contextPath}/AddCertificatMedical";
            
            document.getElementById("back").style.display = "flex";
        }

        function hideElement() {
            document.getElementById("back").style.display = "none";
        }
    </script>

</body>
</html>
