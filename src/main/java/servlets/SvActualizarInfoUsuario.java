/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import CRUDI.OperacionesBaseDatos;
import CRUDI.OperacionesPersona;
import CRUDI.OperacionesUsuarios;
import CRUDI.Persona;
import CRUDI.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "SvActualizarInfoUsuario", urlPatterns = {"/SvActualizarInfoUsuario"})
public class SvActualizarInfoUsuario extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conexion = null;
        Connection conexion2 = null;
   
        try {
            conexion = OperacionesBaseDatos.conectar();
            conexion2 = OperacionesBaseDatos.conectar();
            OperacionesUsuarios opUser = new OperacionesUsuarios(conexion);
            OperacionesPersona opPerson = new OperacionesPersona(conexion2);
            String contrasenaActual = request.getParameter("contrasena");
            String contrasenaNueva = request.getParameter("nuevaContrasena");
            long numeroCelular;
            if(!request.getParameter("celular").equals("")){
                numeroCelular = Long.parseLong(request.getParameter("celular"));
            }
            else{
                numeroCelular = 0;
            }
            String correo = request.getParameter("correo").toLowerCase();
            
            HttpSession session = request.getSession(false);
            Integer idUsuario = (Integer) session.getAttribute("idUsuario");
            Usuario user = opUser.buscarUsuarioPorId(idUsuario);
            
            if (BCrypt.checkpw(contrasenaActual, user.getContrasena()));
                if ((contrasenaNueva.length() > 15 || contrasenaNueva.length() < 8)&& !contrasenaNueva.equals("")) {
                request.setAttribute("mensajeError", "La contraseña debe estar entre 8 y 15 caracteres.");
                request.getRequestDispatcher("configuracionesUsuario.jsp").forward(request, response);
                return;
            }
                
            boolean actualizacionContrasena = opUser.actualizarDatos(null, contrasenaNueva, idUsuario);
            boolean actualizacionOtrosDatos = opPerson.actualizarDatos(numeroCelular, correo, idUsuario);

            if (actualizacionContrasena && actualizacionOtrosDatos) {
                request.setAttribute("mensajeExito", "Datos actualizados con éxito.");
                request.getRequestDispatcher("hacerReserva.jsp").forward(request, response);
            } else {
                request.setAttribute("mensajeError", "Error al actualizar los datos.");
                request.getRequestDispatcher("configuracionesUsuario.jsp").forward(request, response);
            }
            
            
            } catch (ClassNotFoundException ex) {
            request.setAttribute("mensajeError", "Error al ejecutar el proceso");
            request.getRequestDispatcher("configuracionesUsuario.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("mensajeError", "Error al tratar de actualizar los datos");
            request.getRequestDispatcher("configuracionesUsuario.jsp").forward(request, response);
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
