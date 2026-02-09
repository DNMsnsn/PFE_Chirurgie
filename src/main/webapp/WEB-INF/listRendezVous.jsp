<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.RendezVous" %>
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Rendez-Vous</title>
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
    	<h1>Liste Rendez-Vous</h1>
    	<div class="error">${error}</div>
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID RDV</th>
                    <th>Nom Patient</th>
                    <th>Prenom Patient</th>
                    <th>Nom Medecin</th>
                    <th>Prenom Medecin</th>
                    <th>Date RDV</th>
                    <th>Motif</th>
                    <th>Statut</th>
                </tr>
            </thead>

            <tbody>
                <% 
                @SuppressWarnings("unchecked")
	                List<RendezVous> item = (List<RendezVous>) request.getAttribute("listRdv");
	                for(RendezVous p : item){ 
	            %>
                	<tr>
	                    <td><%= p.getID_rdv() %></td>
	                    <td><%= p.getFnmPatient() %></td>
	                    <td><%= p.getSnmPatient() %></td>
	                    <td><%= p.getFnmMedecin() %></td>
	                    <td><%= p.getSnmMedecin() %></td>
	                    <td><%= p.getDt_rdv() %></td>
	                    <td><%= p.getMotif_rdv() %></td>
	                    <td><%= p.getStatu_rdv() %></td>
	                    <td>
	                        <button class="bt edit"
							    onclick="openUpdateModal(this)"
							    data-id="<%= p.getID_rdv() %>"
							    data-pfnm="<%= p.getFnmPatient() %>"
							    data-psnm="<%= p.getSnmPatient() %>"
							    data-mfnm="<%= p.getFnmMedecin() %>"
							    data-msnm="<%= p.getSnmMedecin() %>"
							    data-date="<%= p.getDt_rdv() %>"
							    data-motif="<%= p.getMotif_rdv() %>"
							    data-stt="<%= p.getStatu_rdv() %>">
							</button>
	                        <form action="${pageContext.request.contextPath}/DeleteRendezVous" method="post">
	                        	<input type="hidden" name="id" value="<%= p.getID_rdv() %>">
	                        	<input type="submit" value="" class="bt delete">
	                        </form>
	                    </td>
	                </tr>
                
                <%
                	}
                %>
            </tbody>
        </table>
    </div>
    
    <div class="back" id="back">
    
			    	<button class="closeBut" onclick="hideElement()"></button>
			    
			    	<div class="updatecontainer">
			
				        <h1 id="ttl">Modifier Rendez-Vous :</h1>
				        
				        <div class="error">${error}</div>
				        
					    <form id="frm" action="${pageContext.request.contextPath}/UpdateRendezVous" method="post">
					    	<input type="hidden" id="idrdv" name="idrdv">
					    	
					        <label for="pfnm" class="lab">Nom Patient : </label><br>
					        <input type="text" id="pfnm" name="pfnm" class="inp" required><br>
					        <label for="psnm" class="lab">Prenom Patient : </label><br>
					        <input type="text" id="psnm" name="psnm" class="inp" required><br>
					        
					        <label for="mfnm" class="lab">Nom Medecin : </label><br>
					        <input type="text" id="mfnm" name="mfnm" class="inp" required><br>
					        <label for="msnm" class="lab">Prenom Medecin : </label><br>
					        <input type="text" id="msnm" name="msnm" class="inp" required><br>
					        
					        <label for="dt" class="lab">Date Rendez-Vous : </label><br>
					        <input type="date" name="dt" id="dt" class="inp" required><br>
					        
					        <label for="motif" class="lab">Motif : </label><br>
					        <textarea name="motif" id="motif" class="motif"></textarea><br>
					        
					        <label for="stt" class="lab">Statut : </label><br>
					        <select name="stt" id="stt" class="inp">
				            	<option value="Prevu">Prevu</option>
				            	<option value="Annule">Annule</option>
				            	<option value="Realise">Realise</option>
				            	<option value="Rate">Rate</option>
				        	</select><br>
					        
					        <input type="submit" id="btn" class="bttn" value="Modifier Rendez-Vous">
							
							<div class="error">${error}</div>
							
					    </form>
				
				    </div>
			    </div>
    
    <script>
	    function openUpdateModal(btn) {
	
	        document.getElementById("idrdv").value = btn.dataset.id;
	        document.getElementById("pfnm").value  = btn.dataset.pfnm;
	        document.getElementById("psnm").value  = btn.dataset.psnm;
	        document.getElementById("mfnm").value  = btn.dataset.mfnm;
	        document.getElementById("msnm").value  = btn.dataset.msnm;
	        document.getElementById("dt").value    = btn.dataset.date;
	        document.getElementById("motif").value = btn.dataset.motif;
	        document.getElementById("stt").value   = btn.dataset.stt;
	
	        document.getElementById("back").style.display = "flex";
	    }
	    
	    function showElement(){
	    	
	    	document.getElementById("idrdv").value = null;
	        document.getElementById("pfnm").value   = null;
	        document.getElementById("psnm").value   = null;
	        document.getElementById("mfnm").value   = null;
	        document.getElementById("msnm").value   = null;
	        document.getElementById("dt").value     = null;
	        document.getElementById("motif").value   = null;
	        document.getElementById("stt").value   = null;
	        
	        document.getElementById("ttl").textContent = "Ajouter Rendez-Vous";
	    	document.getElementById("btn").value = "Add Rendez-Vous";
	    	document.getElementById("frm").action = "${pageContext.request.contextPath}/AddRendezVous";
	    	
	    	document.getElementById("back").style.display = "flex";
	    }
	
	    function hideElement() {
	        document.getElementById("back").style.display = "none";
	    }
    </script>

</body>
</html>