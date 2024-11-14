<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesión</title>
<link rel="stylesheet" type="text/css" href="style.css">
<style type="text/css">
/* Estilo para centrar el formulario en la página */
body {
    background-color: #f0f2f5;
    font-family: Arial, sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

/* Estilo para el contenedor del formulario */
form {
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    width: 300px;
    text-align: center;
}

/* Estilo para los campos de entrada */
input[type="text"], input[type="password"] {
    width: 80%;
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #ccc;
    border-radius: 5px;
}

/* Estilo para el botón */
button {
    width: 100%;
    padding: 10px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

/* Estilo para el botón al pasar el ratón */
button:hover {
    background-color: #45a049;
}

/* Estilo para el mensaje de error */
p {
    color: red;
    font-size: 14px;
    margin-top: 10px;
}


</style>

</head>
<body>

   <form action="/login/login" method="post">

        <label for="nombre_usuario">Nombre de Usuario:</label>
        <input type="text" name="nombre_usuario" required />
        <br />
        <label for="contrasena">Contraseña:</label>
        <input type="password" name="contrasena" required />
        <br />
        <button type="submit">Iniciar Sesión</button>
    </form>
    <!-- Mostrar mensaje de error si los parámetros "error" están presentes -->
    <c:if test="${param.error != null}">
        <p style="color:red;">
            <!-- Mensaje de error según el valor del parámetro "error" -->
            <c:choose>
                <c:when test="${param.error == 1}">Credenciales incorrectas. Por favor, inténtalo de nuevo.</c:when>
                <c:when test="${param.error == 2}">Ocurrió un error en el sistema. Inténtalo más tarde.</c:when>
            </c:choose>
        </p>
    </c:if>
</body>
</html>
