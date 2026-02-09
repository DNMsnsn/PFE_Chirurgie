<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<div class="container">
        <a href="${pageContext.request.contextPath}/ListPatient"><button class="btn">Patients</button></a>
        <a href="${pageContext.request.contextPath}/ListMedecin"><button class="btn">Medecins</button></a>
        <a href="${pageContext.request.contextPath}/ListAmsp"><button class="btn">AMSP</button></a>
        <a href="${pageContext.request.contextPath}/ListRendezVous"><button class="btn">RDV</button></a>
        <a href="${pageContext.request.contextPath}/ListDiagnostic"><button class="btn">Diagnostic</button></a>
        <a href="${pageContext.request.contextPath}/ListArretTravail"><button class="btn">Arret</button></a>
        <a href="${pageContext.request.contextPath}/ListCertificatMedical"><button class="btn">Certificat</button></a>
        <a href="${pageContext.request.contextPath}/ListCertificatSejour"><button class="btn">Sejour</button></a>
        <a href="${pageContext.request.contextPath}/ListCro"><button class="btn">CRO</button></a>
        <a href="${pageContext.request.contextPath}/ListCrh"><button class="btn">CRH</button></a>
        
        <a href="${pageContext.request.contextPath}/ListHistorique"><button class="btn historic"></button></a>
        <a href="${pageContext.request.contextPath}/Dashboard"><button class="btn profile"></button></a>
        <a href="${pageContext.request.contextPath}/LogoutAmsp"><button class="btn logout"></button></a>
    </div>
    
    <div class="searchbar">
        <form action="${pageContext.request.contextPath}/Search" method="post">
            <input type="text" name="search" id="search" class="inp" placeholder="Cherche Nom ou Prenom..." required>
            <select name="choose" id="choose" class="inp">
                <option value="AMSP">AMSP</option>
                <option value="Medecin">Medecin</option>
                <option value="Patient">Patient</option>
                <option value="RDV">RDV</option>
                <option value="Diagnostic">Diagnostic</option>
                <option value="Arret">Arret de Travail</option>
                <option value="Certificat">Certificat Medical</option>
                <option value="Titre">Certificat Sejour</option>
            </select>
            <input type="submit" name="sub" id="sub" class="bb" value="">
        </form>
    </div>

</body>
</html>