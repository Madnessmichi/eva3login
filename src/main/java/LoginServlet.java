

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Mapeo del servlet a la URL "/login"
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario de inicio de sesión
        String nombreUsuario = request.getParameter("nombre_usuario");
        String contrasena = request.getParameter("contrasena");

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myconstruction", "root", "Andreita1!");
            // Preparar la consulta SQL para verificar las credenciales del usuario
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario=? AND contrasena=?");
            stmt.setString(1, nombreUsuario); // Asignar el nombre de usuario al primer parámetro
            stmt.setString(2, contrasena); // Asignar la contraseña al segundo parámetro

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Validar si el usuario existe en la base de datos
            if (rs.next()) {
                // Si el usuario existe, guarda el nombre en la sesión
                request.getSession().setAttribute("nombreUsuario", nombreUsuario);
                response.sendRedirect("Home.jsp"); // Redirigir a la página de bienvenida
            } else {
                // Si no existe, redirigir al formulario de login con un mensaje de error
                response.sendRedirect("Login.jsp?error=1");
            }
        } catch (Exception e) {
            // En caso de error, imprimir la traza y redirigir con un mensaje de error
            e.printStackTrace();
            response.sendRedirect("Login.jsp?error=2");
        }
    }
}
