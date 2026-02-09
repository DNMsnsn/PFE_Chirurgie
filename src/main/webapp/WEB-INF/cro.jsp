<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Cro" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List CRO</title>
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
    	<h1>Liste CRO</h1>
    	<div class="error">${error}</div>
    	
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID Protocole</th>
                    <th>Nom Patient</th>
                    <th>Prénom Patient</th>
                    <th>Nom Operateur</th>
                    <th>Prénom Operateur</th>
                    
                    <th>Date Operation</th>
                    <th>Bloc</th>
                    <th>Diagnostique Lesionnel</th>
                    <th>Intervention Pratique</th>
                </tr>
            </thead>

            <tbody>
                <% 
                @SuppressWarnings("unchecked")
                    List<Cro> item = (List<Cro>) request.getAttribute("listCro");
                    if(item != null){
                        for(Cro p : item){ 
                %>
                    <tr>
                        <td><%= p.getId_cro() %></td>
                        <td><%= p.getNom_patient() %></td>
                        <td><%= p.getPrenom_patient() %></td>
                        <td><%= p.getNom_operateur() %></td>
                        <td><%= p.getPrenom_operateur() %></td>
                        
                        <td><%= p.getDate_operation() %></td>
                        <td><%= p.getBloc() %></td>
                        <td><%= p.getDiagnostic_lesionnel() %></td>
                        <td><%= p.getIntervention_paratique() %></td>
                        <td></td>
                        
                            <td>
							    <button class="bt edit"
							        type="button"
							        onclick="openUpdateModal(this)"
							        data-id="<%= p.getId_cro() %>"
							        data-n_patient="<%= p.getNom_patient() %>"
							        data-p_patient="<%= p.getPrenom_patient() %>"
							        data-n_operateur="<%= p.getNom_operateur() %>"
							        data-p_operateur="<%= p.getPrenom_operateur() %>"
							        data-n_aide="<%= p.getNom_aide() %>"
							        data-p_aide="<%= p.getPrenom_aide() %>"
							        data-n_reanimateur="<%= p.getNom_reanimateur() %>"
							        data-p_reanimateur="<%= p.getPrenom_reanimateur() %>"
							        data-n_anesthesiste="<%= p.getNom_anesthesiste() %>"
							        data-p_anesthesiste="<%= p.getPrenom_anesthesiste() %>"
							        data-dt="<%= p.getDate_operation() %>"
							        data-bloc="<%= p.getBloc() %>"
							        data-diag="<%= p.getDiagnostic_lesionnel() %>"
							        data-intervention="<%= p.getIntervention_paratique() %>"
							        data-protocole="<%= p.getProtocole() %>"
							    ></button>
							
							    <form action="${pageContext.request.contextPath}/DeleteCro" method="post">
							        <input type="hidden" name="id" value="<%= p.getId_cro() %>">
							        <input type="submit" value="" class="bt delete">
							    </form>
							    <form action="${pageContext.request.contextPath}/DownloadCroQrPDF" method="get">
	                                <input type="hidden" name="id" value="<%= p.getId_cro() %>">
	                                <input type="hidden" name="id_patient" value="<%= p.getId_patient() %>">
	                                <input type="hidden" name="n_patient" value="<%= p.getNom_patient() %>">
	                                <input type="hidden" name="p_patient" value="<%= p.getPrenom_patient() %>">
									<input type="hidden" name="n_operateur" value="<%= p.getNom_operateur() %>">
									<input type="hidden" name="p_operateur" value="<%= p.getPrenom_operateur() %>">
									<input type="hidden" name="n_aide" value="<%= p.getNom_aide() %>">
									<input type="hidden" name="p_aide" value="<%= p.getPrenom_aide() %>">
									<input type="hidden" name="n_reanimateur" value="<%= p.getNom_reanimateur() %>">
									<input type="hidden" name="p_reanimateur" value="<%= p.getPrenom_reanimateur() %>">
									<input type="hidden" name="n_anesthesiste" value="<%= p.getNom_anesthesiste() %>">
									<input type="hidden" name="p_anesthesiste" value="<%= p.getPrenom_anesthesiste() %>">
									<input type="hidden" name="dt" value="<%= p.getDate_operation() %>">
									<input type="hidden" name="bloc" value="<%= p.getBloc() %>">
									<input type="hidden" name="diag" value="<%= p.getDiagnostic_lesionnel() %>">
									<input type="hidden" name="intervention" value="<%= p.getIntervention_paratique() %>">
									<input type="hidden" name="protocole" value="<%= p.getProtocole() %>">
					                                
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
            <h1 id="ttl">Ajouter un CRO :</h1>
            
	    	<form id="frm" action="${pageContext.request.contextPath}/AddCro" method="post">
	    		<input type="hidden" name="id">
	    		
		    	<label for="n_patient" class="lab">Nom Patient : </label><br>
				<input type="text" id="n_patient" name="n_patient" class="inp" required><br>
				<label for="p_patient" class="lab">Prénom Patient : </label><br>
				<input type="text" id="p_patient" name="p_patient" class="inp" required><br>
				
				<label for="n_operateur" class="lab">Nom Operateur : </label><br>
				<input type="text" id="n_operateur" name="n_operateur" class="inp" required><br>
				<label for="p_operateur" class="lab">Prénom Operateur : </label><br>
				<input type="text" id="p_operateur" name="p_operateur" class="inp" required><br>
				
				<label for="n_aide" class="lab">Nom Aide Operateur : </label><br>
				<input type="text" id="n_aide" name="n_aide" class="inp" required><br>
				<label for="p_aide" class="lab">Prénom Aide Operateur : </label><br>
				<input type="text" id="p_aide" name="p_aide" class="inp" required><br>
				
				<label for="n_reanimateur" class="lab">Nom Reanimateur : </label><br>
				<input type="text" id="n_reanimateur" name="n_reanimateur" class="inp" required><br>
				<label for="p_reanimateur" class="lab">Prénom Reanimateur : </label><br>
				<input type="text" id="p_reanimateur" name="p_reanimateur" class="inp" required><br>
		    	
		    	<label for="n_anesthesiste" class="lab">Nom Anesthesiste : </label><br>
				<input type="text" id="n_anesthesiste" name="n_anesthesiste" class="inp" required><br>
				<label for="p_anesthesiste" class="lab">Prénom Anesthesiste : </label><br>
				<input type="text" id="p_anesthesiste" name="p_anesthesiste" class="inp" required><br>
		    	
		    	<label for="dt" class="lab">Date : </label><br>
				<input type="date" id="dt" name="dt" class="inp" required><br>
		    	
		    	<label for="bloc" class="lab">Bloc : </label><br>
				<input type="number" id="bloc" name="bloc" class="inp" required><br>
		    	
		    	<label for="diag" class="lab">Diagnostique Lesionnel : </label><br>
				<input type="text" id="diag" name="diag" class="inp" required><br>
		    	
		    	<label for="intervention" class="lab">Intervention Pratique : </label><br>
				<input type="text" id="intervention" name="intervention" class="inp" required><br>
		    	
		    	<label for="protocole" class="lab">Protocole : </label><br>
		    	<textarea id="protocole" name="protocole" class="area" required></textarea>
			    <input type="submit" id="btn" class="bttn" value="send">
		    </form>
	    </div>
	</div>
	
	
	<script>
	
	    function deleteField(button) {
	        button.parentElement.remove();
	    }
	    
	    function openUpdateModal(btn) {

	        document.querySelector("input[name='id']").value = btn.dataset.id;

	        document.getElementById("n_patient").value = btn.dataset.n_patient;
	        document.getElementById("p_patient").value = btn.dataset.p_patient;

	        document.getElementById("n_operateur").value = btn.dataset.n_operateur;
	        document.getElementById("p_operateur").value = btn.dataset.p_operateur;

	        document.getElementById("n_aide").value = btn.dataset.n_aide;
	        document.getElementById("p_aide").value = btn.dataset.p_aide;

	        document.getElementById("n_reanimateur").value = btn.dataset.n_reanimateur;
	        document.getElementById("p_reanimateur").value = btn.dataset.p_reanimateur;

	        document.getElementById("n_anesthesiste").value = btn.dataset.n_anesthesiste;
	        document.getElementById("p_anesthesiste").value = btn.dataset.p_anesthesiste;

	        document.getElementById("dt").value = btn.dataset.dt;
	        document.getElementById("bloc").value = btn.dataset.bloc;
	        document.getElementById("diag").value = btn.dataset.diag;
	        document.getElementById("intervention").value = btn.dataset.intervention;
	        document.getElementById("protocole").value = btn.dataset.protocole;

	        document.getElementById("ttl").textContent = "Modifier un CRO :";
	        document.getElementById("btn").value = "Modifier CRO";
	        document.getElementById("frm").action =
	            "${pageContext.request.contextPath}/UpdateCro";

	        document.getElementById("back").style.display = "flex";
	    }

	    function hideElement() {
	        document.getElementById("back").style.display = "none";
	    }


        function showElement() {
        	document.querySelector("input[name='id']").value = "";

	        document.getElementById("n_patient").value = "";
	        document.getElementById("p_patient").value = "";

	        document.getElementById("n_operateur").value = "";
	        document.getElementById("p_operateur").value = "";

	        document.getElementById("n_aide").value = "";
	        document.getElementById("p_aide").value = "";

	        document.getElementById("n_reanimateur").value = "";
	        document.getElementById("p_reanimateur").value = "";

	        document.getElementById("n_anesthesiste").value = "";
	        document.getElementById("p_anesthesiste").value = "";

	        document.getElementById("dt").value = "";
	        document.getElementById("bloc").value = "";
	        document.getElementById("diag").value = "";
	        document.getElementById("intervention").value = "";
	        document.getElementById("protocole").value = "";

	        document.getElementById("ttl").textContent = "Ajouter un CRO :";
	        document.getElementById("btn").value = "Ajouter CRO";
	        document.getElementById("frm").action =
	            "${pageContext.request.contextPath}/AddCro";

	        document.getElementById("back").style.display = "flex";
        }

	</script>


</body>
</html>