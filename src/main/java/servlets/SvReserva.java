/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import CRUDI.OperacionesBaseDatos;
import CRUDI.OperacionesPersona;
import CRUDI.OperacionesReservas;
import CRUDI.OperacionesUsuarios;
import CRUDI.Persona;
import CRUDI.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "SvReserva", urlPatterns = {"/SvReserva"})
public class SvReserva extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conexion = null;
        try{
            conexion = OperacionesBaseDatos.conectar();
            OperacionesUsuarios opUser = new OperacionesUsuarios(conexion);
            OperacionesReservas opReserva = new OperacionesReservas(conexion);
            
            String fechaReserva = request.getParameter("fechaReserva");
            Timestamp fecha = Timestamp.valueOf(fechaReserva + " 00:00:00");
            
            ArrayList<String> horariosDisponibles = opReserva.obtenerHorariosDisponibles(fecha);
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            if (horariosDisponibles.isEmpty()) {
            out.println("<p>No hay horarios disponibles para esta fecha.</p>");
        } else {
            out.println("<table>");
            out.println("<thead><tr><th>Hora</th><th>Accion</th></tr></thead>");
            out.println("<tbody>");
            for (String hora : horariosDisponibles) {
                out.println("<tr id='fila-" + hora + "'>");
                out.println("<td>" + hora + "</td>");
                out.println("<td><button class='reservar-btn' data-hora='" + hora + "'>Reservar</button></td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
        }
        } catch (Exception e) {
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().println("Error al obtener los horarios disponibles.");
    }
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conexion = null;
        
        try {
        HttpSession session = request.getSession(false);
        String horaSeleccionada = request.getParameter("horaSeleccionada");
        String fechaSeleccionada = request.getParameter("fechaSeleccionada");
        Timestamp fechaReserva = Timestamp.valueOf(fechaSeleccionada + " " + horaSeleccionada);
        
        conexion = OperacionesBaseDatos.conectar();
        OperacionesPersona opPerson = new OperacionesPersona(conexion);
        OperacionesReservas opRes = new OperacionesReservas(conexion);
        
        Integer id= (Integer) session.getAttribute("idUsuario");
        int idUsuario = id.intValue();
        
        Persona person = opPerson.buscarPersonaPorId(idUsuario);
        
        Reserva nuevaReserva = new Reserva(idUsuario, person.getNombre(),person.getPrimerApellido(),person.getSegundoApellido(), fechaReserva);

        opRes.insertarReserva(nuevaReserva);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Reserva creada con éxito.");
    } catch (Exception e) {
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().println("Error al realizar la reserva.");
    }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
