<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login AMSP</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/formStyle.css" />
</head>
<body>

	<div class="mycontainer">

        <h1>Login AMSP :</h1>
    <form action="${pageContext.request.contextPath}/LoginAmsp" method="post">
        <label for="fnm" class="lab">Nom : </label><br>
        <input type="text" id="fnm" name="fnm" class="inp" required><br>
        
        <label for="psw" class="lab">Password : </label><br>
        <input type="password" id="psw" name="psw" class="inp" required><br>
        
        <input type="submit" id="btn" value="submit" class="bttn" value="Login">

		<div class="error">${error}</div>
    </form>

    </div>

</body>
</html>