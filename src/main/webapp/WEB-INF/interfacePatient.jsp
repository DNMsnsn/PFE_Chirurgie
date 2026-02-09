<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.Patient, 
				Model.ArretTravail, Model.CertificatMedical, Model.CertificatSejour,
				Model.RendezVous, Model.Cro, Model.Crh" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil Patient</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	
	<div class="maincontainer">
    	<h1>Patient</h1>
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
                </tr>
            </thead>
            <tbody>
                <% 
                	Patient p = (Patient) request.getAttribute("patient");
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
            </tbody>
        </table>
    </div>
    
    <div class="mycontainer">
    	<h1>Liste Rendez-Vous</h1>
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
                	List<RendezVous> rdv = (List<RendezVous>) request.getAttribute("listrdv");
                	for(RendezVous r : rdv){
	            %>
                	<tr>
	                    <td><%= r.getID_rdv() %></td>
	                    <td><%= r.getFnmPatient() %></td>
	                    <td><%= r.getSnmPatient() %></td>
	                    <td><%= r.getFnmMedecin() %></td>
	                    <td><%= r.getSnmMedecin() %></td>
	                    <td><%= r.getDt_rdv() %></td>
	                    <td><%= r.getMotif_rdv() %></td>
	                    <td><%= r.getStatu_rdv() %></td>
				<%
                	}
				%>
            </tbody>
        </table>
    </div>
    
    <div class="mycontainer">
    	<h1>Liste Arret de Travail</h1>
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

	<div class="mycontainer">
    	<h1>Liste Certificats Medicals</h1>
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
                    List<CertificatMedical> lc = (List<CertificatMedical>) request.getAttribute("listCertificat");
                    for(CertificatMedical c : lc){ 
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
	
	<div class="mycontainer">
    	<h1>Liste Certificats de Sejour</h1>
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
                    List<CertificatSejour> ls = (List<CertificatSejour>) request.getAttribute("listSejour");
                    for(CertificatSejour c : ls){ 
                %>
                    <tr>
                        <td><%= c.getId_titre() %></td>
                        <td><%= c.getDate_debut() %></td>
                        <td><%= c.getDate_fin() %></td>
                        <td><%= c.getNom_patient() %></td>
                        <td><%= c.getPrenom_patient() %></td>
                        <td>
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
	
		<div class="mycontainer">
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
                        for(Cro cr : item){ 
                %>
                    <tr>
                        <td><%= cr.getId_cro() %></td>
                        <td><%= cr.getNom_patient() %></td>
                        <td><%= cr.getPrenom_patient() %></td>
                        <td><%= cr.getNom_operateur() %></td>
                        <td><%= cr.getPrenom_operateur() %></td>
                        
                        <td><%= cr.getDate_operation() %></td>
                        <td><%= cr.getBloc() %></td>
                        <td><%= cr.getDiagnostic_lesionnel() %></td>
                        <td><%= cr.getIntervention_paratique() %></td>
                        <td></td>
                        
                            <td>
							    <form action="${pageContext.request.contextPath}/DownloadCroQrPDF" method="get">
	                                <input type="hidden" name="id" value="<%= cr.getId_cro() %>">
	                                <input type="hidden" name="n_patient" value="<%= cr.getNom_patient() %>">
	                                <input type="hidden" name="p_patient" value="<%= cr.getPrenom_patient() %>">
									<input type="hidden" name="n_operateur" value="<%= cr.getNom_operateur() %>">
									<input type="hidden" name="p_operateur" value="<%= cr.getPrenom_operateur() %>">
									<input type="hidden" name="n_aide" value="<%= cr.getNom_aide() %>">
									<input type="hidden" name="p_aide" value="<%= cr.getPrenom_aide() %>">
									<input type="hidden" name="n_reanimateur" value="<%= cr.getNom_reanimateur() %>">
									<input type="hidden" name="p_reanimateur" value="<%= cr.getPrenom_reanimateur() %>">
									<input type="hidden" name="n_anesthesiste" value="<%= cr.getNom_anesthesiste() %>">
									<input type="hidden" name="p_anesthesiste" value="<%= cr.getPrenom_anesthesiste() %>">
									<input type="hidden" name="dt" value="<%= cr.getDate_operation() %>">
									<input type="hidden" name="bloc" value="<%= cr.getBloc() %>">
									<input type="hidden" name="diag" value="<%= cr.getDiagnostic_lesionnel() %>">
									<input type="hidden" name="intervention" value="<%= cr.getIntervention_paratique() %>">
									<input type="hidden" name="protocole" value="<%= cr.getProtocole() %>">
					                                
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
    
    <div class="mycontainer">
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
                    List<Crh> i = (List<Crh>) request.getAttribute("listCrh");
                    if(item != null){
                        for(Crh j : i){ 
                %>
                    <tr>
                        <td><%= j.getId_crh() %></td>
                        <td><%= j.getNom_patient() %></td>
                        <td><%= j.getPrenom_patient() %></td>
                        <td><%= j.getNom_medecin() %></td>
                        <td><%= j.getPrenom_medecin() %></td>
                        
                        <td><%= j.getDate_entre() %></td>
                        <td><%= j.getDate_sortie() %></td>
                        
                        <td></td>
                        
                            <td>
							    <form action="${pageContext.request.contextPath}/DownloadCrhQrPDF" method="get">
	                                <input type="hidden" name="id" value="<%= j.getId_crh() %>">
	                                <input type="hidden" name="id_patient" value="<%= j.getId_patient() %>">
	                                <input type="hidden" name="n_patient" value="<%= j.getNom_patient() %>">
	                                <input type="hidden" name="p_patient" value="<%= j.getPrenom_patient() %>">
									<input type="hidden" name="n_medecin" value="<%= j.getNom_medecin() %>">
									<input type="hidden" name="p_medecin" value="<%= j.getPrenom_medecin() %>">
									
									<input type="hidden" name="dte" value="<%= j.getDate_entre() %>">
									<input type="hidden" name="dts" value="<%= j.getDate_sortie() %>">
									<input type="hidden" name="content" value="<%= j.getContent() %>">
					                                
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
</body>
</html>