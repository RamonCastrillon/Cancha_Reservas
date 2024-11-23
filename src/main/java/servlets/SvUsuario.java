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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ramon
 */
@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
        

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
    }

 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conexion = null;
   
        try {
            
            int dni = Integer.parseInt(request.getParameter("dni"));
            String contrasena = request.getParameter("contrasena");
            String nombre = request.getParameter("nombre").toLowerCase();
            String primerApellido = request.getParameter("primerApellido").toLowerCase();
            String segundoApellido = request.getParameter("segundoApellido").toLowerCase();
            long numeroCelular = Long.parseLong(request.getParameter("celular"));
            String correo = request.getParameter("correoElectronico").toLowerCase();
            
            if (contrasena.length() > 15 || contrasena.length() < 8) {
                request.setAttribute("mensajeError", "La contraseña debe estar entre 8 y 15 caracteres.");
                request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);
                return;
            }

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaUtil = inputFormat.parse(request.getParameter("fechaNacimiento"));
            java.sql.Date fechaNacimiento = new java.sql.Date(fechaUtil.getTime());

            conexion = OperacionesBaseDatos.conectar();
            OperacionesUsuarios opUser = new OperacionesUsuarios(conexion);
            OperacionesPersona opPerson = new OperacionesPersona(conexion);

            Usuario user = new Usuario(dni, contrasena);
            Persona person = new Persona(dni, nombre, primerApellido, segundoApellido, fechaNacimiento, numeroCelular, correo);

            boolean insercionUsuario = opUser.insertarUsuario(user);
            boolean insercionPersona = opPerson.insertarPersona(person);
            
            
            
            if (insercionUsuario && insercionPersona) {
                request.setAttribute("mensajeExito", "Creación de cuenta exitosa.");
                request.getRequestDispatcher("store.jsp").forward(request, response);
            } else {
                request.setAttribute("mensajeError", "El usuario o el número de celular ingresado ya existe.");
                request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);
            }

        } catch (ParseException e) {
            request.setAttribute("mensajeError", "Formato de fecha incorrecto.");
            request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("mensajeError", "Formato de número inválido.");
            request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);

        }catch (ClassNotFoundException ex) {
                request.setAttribute("mensajeError", "Error al procesar la solicitud: " + ex.getMessage());
                request.getRequestDispatcher("crearCuenta.jsp").forward(request, response);
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
        
        

    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
