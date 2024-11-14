<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
    <title>Bienvenido</title>
    <style type="text/css">body {
	    background-color: #f0f2f5;
	    font-family: Arial, sans-serif;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    height: 100vh;
	    margin: 0;
}</style>
</head>
<body>
    <!-- Mensaje de bienvenida para el usuario -->
   <h1>Te damos la bienvenida, ${sessionScope.nombreUsuario}, has iniciado sesión con éxito.</h1>
</body>
</html>
