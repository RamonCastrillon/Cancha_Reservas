/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import CRUDI.OperacionesBaseDatos;
import CRUDI.OperacionesUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ramon
 */
@WebServlet(name = "SvEliminarUsuario", urlPatterns = {"/SvEliminarUsuario"})
public class SvEliminarUsuario extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvEliminarUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvEliminarUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conexion;
        int id = Integer.parseInt(request.getParameter("id_usuario"));
        
        try {
            conexion = OperacionesBaseDatos.conectar();
            OperacionesUsuarios operaciones = new OperacionesUsuarios(conexion);
            operaciones.eliminarDatos(id);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvEliminarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
