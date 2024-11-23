/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import CRUDI.OperacionesBaseDatos;
import CRUDI.OperacionesPersona;
import CRUDI.OperacionesReservas;
import CRUDI.Persona;
import CRUDI.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "SvConsultarReservas", urlPatterns = {"/SvConsultarReservas"})
public class SvConsultarReservas extends HttpServlet {

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
        PrintWriter out = null;
        
        try {
            // Establecer la conexión
            conexion = OperacionesBaseDatos.conectar();
            
            // Verificar si hay una sesión activa
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("idUsuario") == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No se ha iniciado sesión");
                return;
            }
            
            // Inicializar operaciones de base de datos
            OperacionesReservas opReservas = new OperacionesReservas(conexion);
            OperacionesPersona opPerson = new OperacionesPersona(conexion);
            
            // Obtener parámetros de la solicitud
            String fechaSeleccionada = request.getParameter("fechaReserva");
            String tipoConsulta = request.getParameter("tipoConsulta");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String primerApellido = request.getParameter("primerApellido");
            String idUsuario = request.getParameter("idUsuario");
            String idReserva = request.getParameter("idReserva");
            
            System.out.println(fechaSeleccionada);
            System.out.println(tipoConsulta);
            System.out.println(idUsuario);
            
            // Lista para almacenar resultados
            ArrayList<Reserva> reservas = new ArrayList<>();
            
            // Realizar búsquedas según los parámetros recibidos
            if (fechaSeleccionada != null && !fechaSeleccionada.isEmpty() && tipoConsulta != null) {
                // Convertir fecha a timestamp
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = sdf.parse(fechaSeleccionada);
                Timestamp fecha = new Timestamp(parsedDate.getTime());
                
                switch (tipoConsulta) {
                    case "dia":
                        reservas = opReservas.buscarReservaPorFechaReserva(fecha);
                        break;
                    case "semana":
                        reservas = opReservas.buscarReservaPorSemana(fecha);
                        break;
                    case "mes":
                        reservas = opReservas.buscarReservaPorMes(fecha);
                        break;
                }
            }
            
            // Búsqueda por nombre
            if (nombreUsuario != null && !nombreUsuario.isEmpty()) {
                reservas = opReservas.buscarReservaPorNombre(nombreUsuario);
            }
            
            // Búsqueda por apellido
            if (primerApellido != null && !primerApellido.isEmpty()) {
                reservas = opReservas.buscarReservaPorApellido(primerApellido);
            }
            
            // Búsqueda por ID de usuario
            if (idUsuario != null && !idUsuario.isEmpty()) {
                try {
                    int id = Integer.parseInt(idUsuario);
                    reservas = opReservas.buscarReservaPorIdUsuario(id);
                } catch (NumberFormatException e) {
                    // Manejar error de conversión
                    System.err.println("Error al convertir ID de usuario: " + e.getMessage());
                }
            }
            
            // Búsqueda por ID de reserva
            if (idReserva != null && !idReserva.isEmpty()) {
                try {
                    int id = Integer.parseInt(idReserva);
                    Reserva reserva = opReservas.buscarReservaPorIdReserva(id);
                    if (reserva != null) {
                        reservas.add(reserva);
                    }
                } catch (NumberFormatException e) {
                    // Manejar error de conversión
                    System.err.println("Error al convertir ID de reserva: " + e.getMessage());
                }
            }
            
            // Preparar respuesta HTML
            response.setContentType("text/html; charset=UTF-8");
            out = response.getWriter();
            out.println("<table class='table table-striped'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID Reserva</th>");
            out.println("<th>ID Usuario</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Primer Apellido</th>");
            out.println("<th>Segundo Apellido</th>");
            out.println("<th>Fecha de Reserva</th>");
            out.println("<th>Fecha de Generación</th>");
            out.println("<th>Valor de la Reserva</th>");
            out.println("<th>Estado Reserva</th>");
            out.println("<th>Numero de Reservaciones</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            for (Reserva reserva : reservas) {
                Persona person = opPerson.buscarPersonaPorId(reserva.getIdUsuario());
                int numeroReservas = person != null ? person.getNumeroReservas() : 0;
                
                out.println("<tr>");
                out.println("<td>" + reserva.getIdReserva() + "</td>");
                out.println("<td>" + reserva.getIdUsuario() + "</td>");
                out.println("<td>" + (reserva.getNombreUsuario() != null ? reserva.getNombreUsuario() : "") + "</td>");
                out.println("<td>" + (reserva.getPrimerApellido() != null ? reserva.getPrimerApellido() : "") + "</td>");
                out.println("<td>" + (reserva.getSegundoApellido() != null ? reserva.getSegundoApellido() : "") + "</td>");
                out.println("<td>" + (reserva.getFechaReserva() != null ? reserva.getFechaReserva() : "") + "</td>");
                out.println("<td>" + (reserva.getFechaGeneracion() != null ? reserva.getFechaGeneracion() : "") + "</td>");
                out.println("<td>" + reserva.getValorReserva() + "</td>");
                out.println("<td>" + (reserva.getEstadoReserva() != null ? reserva.getEstadoReserva() : "") + "</td>");
                out.println("<td>" + numeroReservas + "</td>");
                out.println("</tr>");
            }
            
            out.println("</tbody>");
            out.println("</table>");
            
        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            if (out != null) {
                out.println("Error al realizar la consulta: " + e.getMessage()+" Comprueba que hayas iniciado sesion previamente");
            }
        } finally {
            try {
                if (conexion != null) {
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
