<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Diagnostic" %>
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Diagnostic</title>
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
    	<h1>Liste Diagnostiques</h1>
    	<div class="error">${error}</div>
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom Patient</th>
                    <th>Prenom Patient</th>
                    <th>Nom Medecin</th>
                    <th>Prenom Medecin</th>
                    <th>Date Diag</th>
                    <th>Description</th>
                </tr>
            </thead>

            <tbody>
                <% 
                @SuppressWarnings("unchecked")
	                List<Diagnostic> item = (List<Diagnostic>) request.getAttribute("listDiag");
	                for(Diagnostic p : item){ 
	            %>
                	<tr>
	                    <td><%= p.getID_diagnostic() %></td>
	                    <td><%= p.getFnmPatient() %></td>
	                    <td><%= p.getSnmPatient() %></td>
	                    <td><%= p.getFnmMedecin() %></td>
	                    <td><%= p.getSnmMedecin() %></td>
	                    <td><%= p.getDt_diag() %></td>
	                    <td><%= p.getDescription_diag() %></td>
	                    <td>
	                        <button class="bt edit"
							    onclick="openUpdateModal(this)"
							    data-id="<%= p.getID_diagnostic() %>"
							    data-pfnm="<%= p.getFnmPatient() %>"
							    data-psnm="<%= p.getSnmPatient() %>"
							    data-mfnm="<%= p.getFnmMedecin() %>"
							    data-msnm="<%= p.getSnmMedecin() %>"
							    data-date="<%= p.getDt_diag() %>"
							    data-desc="<%= p.getDescription_diag() %>">
							</button>
	                        <form action="${pageContext.request.contextPath}/DeleteDiagnostic" method="post">
	                        	<input type="hidden" name="id" value="<%= p.getID_diagnostic() %>">
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
				        
					    <form id="frm" action="${pageContext.request.contextPath}/UpdateDiagnostic" method="post">
					    	<input type="hidden" id="iddiag" name="iddiag" required>
					    	
					    	<label for="pfnm" class="lab">Nom Patient : </label><br>
					        <input type="text" id="pfnm" name="pfnm" class="inp" required><br>
					        <label for="psnm" class="lab">Prenom Patient : </label><br>
					        <input type="text" id="psnm" name="psnm" class="inp" required><br>
					        
					        <label for="mfnm" class="lab">Nom Medecin : </label><br>
					        <input type="text" id="mfnm" name="mfnm" class="inp" required><br>
					        <label for="msnm" class="lab">Prenom Medecin : </label><br>
					        <input type="text" id="msnm" name="msnm" class="inp" required><br>
					        
					        <label for="dt" class="lab">Date du Diagnostique : </label><br>
					        <input type="date" name="dt" id="dt" class="inp" required><br>
					        
					        <label for="desc" class="lab">Description : </label><br>
					        <textarea name="desc" id="desc" class="motif"></textarea><br>
					        
					        <input type="submit" id="btn" class="bttn" value="Update Diagnostic">
							
							<div class="error">${error}</div>
							
					    </form>
				
				    </div>
			    </div>
    
    <script>
	    function openUpdateModal(btn) {
	
	        document.getElementById("iddiag").value = btn.dataset.id;
	        document.getElementById("pfnm").value   = btn.dataset.pfnm;
	        document.getElementById("psnm").value   = btn.dataset.psnm;
	        document.getElementById("mfnm").value   = btn.dataset.mfnm;
	        document.getElementById("msnm").value   = btn.dataset.msnm;
	        document.getElementById("dt").value     = btn.dataset.date;
	        document.getElementById("desc").value   = btn.dataset.desc;
	
	        document.getElementById("back").style.display = "flex";
	    }
	    
	    function showElement(){
	    	
	    	document.getElementById("iddiag").value = null;
	        document.getElementById("pfnm").value   = null;
	        document.getElementById("psnm").value   = null;
	        document.getElementById("mfnm").value   = null;
	        document.getElementById("msnm").value   = null;
	        document.getElementById("dt").value     = null;
	        document.getElementById("desc").value   = null;
	    	
	        document.getElementById("ttl").textContent = "Ajouter Diagnostic";
	    	document.getElementById("btn").value = "Add Diagnostic";
	    	document.getElementById("frm").action = "${pageContext.request.contextPath}/AddDiagnostic";
	        
	    	document.getElementById("back").style.display = "flex";
	    }
	
	    function hideElement() {
	        document.getElementById("back").style.display = "none";
	    }
    </script>

</body>
</html>