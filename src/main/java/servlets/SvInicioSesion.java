/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import CRUDI.OperacionesBaseDatos;
import CRUDI.OperacionesPersona;
import CRUDI.OperacionesUsuarios;
import CRUDI.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ramon
 */
@WebServlet(name = "SvInicioSesion", urlPatterns = {"/SvInicioSesion"})
public class SvInicioSesion extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String idUsuarioTexto = request.getParameter("idUsuario");
        String contrasena = request.getParameter("contrasena");
        Connection conexion = null;

        try {
            conexion = OperacionesBaseDatos.conectar();
            OperacionesUsuarios opUser = new OperacionesUsuarios(conexion);

            if (idUsuarioTexto != null && !idUsuarioTexto.isEmpty() && contrasena != null && !contrasena.isEmpty()) {
                int idUsuario = Integer.parseInt(idUsuarioTexto);
                Usuario user = opUser.buscarUsuarioPorId(idUsuario);

                if (user != null && user.getContrasena() != null && BCrypt.checkpw(contrasena, user.getContrasena())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("idUsuario", user.getIdUsuario()); 
                    session.setAttribute("rol", user.getRol());

                    if ("usuario".equals(user.getRol())) {
                        request.getRequestDispatcher("hacerReserva.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("opcionesAdmin.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("mensajeError", "El usuario ingresado no coincide con la contraseña");
                    request.getRequestDispatcher("store.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mensajeError", "Por favor ingresa un usuario y una contraseña válidos");
                request.getRequestDispatcher("store.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            request.setAttribute("mensajeError", "Error en el sistema, por favor intente nuevamente. Si el problema persiste, contacte al soporte técnico.");
            request.getRequestDispatcher("store.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("mensajeError", "Hubo un problema al intentar iniciar sesión. Por favor, intente nuevamente más tarde.");
            request.getRequestDispatcher("store.jsp").forward(request, response);
        }
        finally {
                try {
                    if (conexion != null && !conexion.isClosed()) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
}




    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
