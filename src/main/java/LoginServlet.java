import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario de inicio de sesión
        String nombreUsuario = request.getParameter("nombre_usuario");
        String contrasena = request.getParameter("contrasena");

        // Ruta del archivo de log en el directorio del proyecto
        File logFile = new File("C:/Users/andre/eclipse-workspace/eva3login/login_attempts.log");

        // Crear el directorio si no existe
        if (!logFile.getParentFile().exists()) {
            logFile.getParentFile().mkdirs();
        }

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myconstruction", "root", "");

            // Preparar la consulta SQL para verificar las credenciales del usuario
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM usuarios WHERE nombre_usuario=? AND contrasena=?"
            );
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Registro de intento exitoso en el archivo
                FileUtils.writeStringToFile(logFile, new Date() + " - Login exitoso: " + nombreUsuario + "\n", "UTF-8", true);

                // Usuario autenticado con éxito
                request.getSession().setAttribute("nombreUsuario", nombreUsuario);
                response.sendRedirect("Home.jsp");
            } else {
                // Registro de intento fallido en el archivo
                FileUtils.writeStringToFile(logFile, new Date() + " - Login fallido: " + nombreUsuario + "\n", "UTF-8", true);

                // Credenciales incorrectas
                response.sendRedirect("Login.jsp?error=1");
            }
        } catch (Exception e) {
            // Registro del error en el archivo
            FileUtils.writeStringToFile(logFile, new Date() + " - Error del sistema durante login: " + e.getMessage() + "\n", "UTF-8", true);

            e.printStackTrace();
            response.sendRedirect("Login.jsp?error=2");
        }
    }
}
