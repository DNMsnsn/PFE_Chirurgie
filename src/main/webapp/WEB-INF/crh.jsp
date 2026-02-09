<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Crh" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List CRH</title>
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
    	<h1>Liste CRH</h1>
    	<div class="error">${error}</div>
    	
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID CRH</th>
                    <th>Nom Patient</th>
                    <th>Prénom Patient</th>
                    <th>Nom Medecin</th>
                    <th>Prénom Medecin</th>
                    
                    <th>Date Entree</th>
                    <th>Date Sortie</th>
                </tr>
            </thead>

            <tbody>
                <% 
                @SuppressWarnings("unchecked")
                    List<Crh> item = (List<Crh>) request.getAttribute("listCrh");
                    if(item != null){
                        for(Crh p : item){ 
                %>
                    <tr>
                        <td><%= p.getId_crh() %></td>
                        <td><%= p.getNom_patient() %></td>
                        <td><%= p.getPrenom_patient() %></td>
                        <td><%= p.getNom_medecin() %></td>
                        <td><%= p.getPrenom_medecin() %></td>
                        
                        <td><%= p.getDate_entre() %></td>
                        <td><%= p.getDate_sortie() %></td>
                        
                        <td></td>
                        
                            <td>
							    <button class="bt edit"
							        type="button"
							        onclick="openUpdateModal(this)"
							        data-id="<%= p.getId_crh() %>"
							        data-n_patient="<%= p.getNom_patient() %>"
							        data-p_patient="<%= p.getPrenom_patient() %>"
							        data-n_medecin="<%= p.getNom_medecin() %>"
							        data-p_medecin="<%= p.getPrenom_medecin() %>"
							        
							        data-dte="<%= p.getDate_entre() %>"
							        data-dts="<%= p.getDate_sortie() %>"
							       
							        data-content="<%= p.getContent() %>"
							    ></button>
							
							    <form action="${pageContext.request.contextPath}/DeleteCrh" method="post">
							        <input type="hidden" name="id" value="<%= p.getId_crh() %>">
							        <input type="submit" value="" class="bt delete">
							    </form>
							    <form action="${pageContext.request.contextPath}/DownloadCrhQrPDF" method="get">
	                                <input type="hidden" name="id" value="<%= p.getId_crh() %>">
	                                <input type="hidden" name="id_patient" value="<%= p.getId_patient() %>">
	                                <input type="hidden" name="n_patient" value="<%= p.getNom_patient() %>">
	                                <input type="hidden" name="p_patient" value="<%= p.getPrenom_patient() %>">
									<input type="hidden" name="n_medecin" value="<%= p.getNom_medecin() %>">
									<input type="hidden" name="p_medecin" value="<%= p.getPrenom_medecin() %>">
									
									<input type="hidden" name="dte" value="<%= p.getDate_entre() %>">
									<input type="hidden" name="dts" value="<%= p.getDate_sortie() %>">
									<input type="hidden" name="content" value="<%= p.getContent() %>">
					                                
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
	
	<!-- FORMULAIRE -->
	
	<div class="back" id="back">
        <button class="closeBut" onclick="hideElement()"></button>
	
	
	    <div class="mycontainer">
            <h1 id="ttl">Ajouter un CRH :</h1>
            
	    	<form id="frm" action="${pageContext.request.contextPath}/AddCrh" method="post">
	    		<input type="hidden" id="idc" name="idc">
	    		
		    	<label for="n_patient" class="lab">Nom Patient : </label><br>
				<input type="text" id="n_patient" name="n_patient" class="inp" required><br>
				<label for="p_patient" class="lab">Prénom Patient : </label><br>
				<input type="text" id="p_patient" name="p_patient" class="inp" required><br>
				
				<label for="n_medecin" class="lab">Nom Medecin : </label><br>
				<input type="text" id="n_medecin" name="n_medecin" class="inp" required><br>
				<label for="p_medecin" class="lab">Prénom Medecin : </label><br>
				<input type="text" id="p_medecin" name="p_medecin" class="inp" required><br>
				
		    	<label for="dte" class="lab">Date Entree : </label><br>
				<input type="date" id="dte" name="dte" class="inp" required><br>
		    	
		    	<label for="dts" class="lab">Date Sortie: </label><br>
				<input type="date" id="dts" name="dts" class="inp" required><br>
		    	
		    	<label for="content" class="lab">Contenue : </label><br>
		    	<textarea id="content" name="content" class="area" maxlength="4000" required></textarea>
			    <input type="submit" id="btn" class="bttn" value="send">
		    </form>
	    </div>
	</div>
	
	
	<script>
	
	    function openUpdateModal(btn) {

	        document.getElementById("idc").value = btn.dataset.id;

	        document.getElementById("n_patient").value = btn.dataset.n_patient;
	        document.getElementById("p_patient").value = btn.dataset.p_patient;

	        document.getElementById("n_medecin").value = btn.dataset.n_medecin;
	        document.getElementById("p_medecin").value = btn.dataset.p_medecin;

	        document.getElementById("dte").value = btn.dataset.dte;
	        document.getElementById("dts").value = btn.dataset.dts;
	        
	        document.getElementById("content").value = btn.dataset.content;

	        document.getElementById("ttl").textContent = "Modifier un CRH :";
	        document.getElementById("btn").value = "Modifier CRH";
	        document.getElementById("frm").action =
	            "${pageContext.request.contextPath}/UpdateCrh";

	        document.getElementById("back").style.display = "flex";
	    }

	    function hideElement() {
	        document.getElementById("back").style.display = "none";
	    }


        function showElement() {
        	document.getElementById("idc").value = "";

	        document.getElementById("n_patient").value = "";
	        document.getElementById("p_patient").value = "";

	        document.getElementById("n_medecin").value = "";
	        document.getElementById("p_medecin").value = "";

	        document.getElementById("dte").value = "";
	        document.getElementById("dts").value = "";
	        
	        document.getElementById("content").value = "";

	        document.getElementById("ttl").textContent = "Ajouter un CRH :";
	        document.getElementById("btn").value = "Ajouter CRH";
	        document.getElementById("frm").action =
	            "${pageContext.request.contextPath}/AddCrh";

	        document.getElementById("back").style.display = "flex";
        }

	</script>


</body>
</html>