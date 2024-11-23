/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import CRUDI.OperacionesBaseDatos;
import CRUDI.OperacionesReservas;
import CRUDI.OperacionesUsuarios;
import CRUDI.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author ramon
 */
@WebServlet(name = "SvEliminarReserva", urlPatterns = {"/SvEliminarReserva"})
public class SvEliminarReserva extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Connection conexion = null;
        try {
            conexion = OperacionesBaseDatos.conectar();
            OperacionesReservas opReserva = new OperacionesReservas(conexion);
            HttpSession session = request.getSession(false);


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaReserva = sdf.parse(request.getParameter("fechaReserva"));

            Integer id = (Integer) session.getAttribute("idUsuario");
            int idUsuario = id.intValue();

            ArrayList<Reserva> reservasUsuario = opReserva.buscarReservaPorIdUsuario(idUsuario);
            ArrayList<Reserva> reservasFiltradas = new ArrayList<>();

            for (Reserva reserva : reservasUsuario) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String reservaFecha = inputFormat.format(reserva.getFechaReserva());
                String buscarFecha = inputFormat.format(fechaReserva);

                if (reservaFecha.equals(buscarFecha)) {
                    reservasFiltradas.add(reserva);
                }
            }

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (reservasFiltradas.isEmpty()) {
                out.println("<p>No tienes reservas en esta fecha.</p>");
            } else {
                out.println("<table>");
                out.println("<thead><tr><th>Hora</th><th>Accion</th></tr></thead>");
                out.println("<tbody>");

                for (Reserva reserva : reservasFiltradas) {
                    SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
                    String hora = hourFormat.format(reserva.getFechaReserva());

                    out.println("<tr id='fila-" + reserva.getIdReserva() + "'>");
                    out.println("<td>" + hora + "</td>");
                    out.println("<td style='text-align: center; vertical-align: middle;'><button class='cancelar-btn btn btn-primary btn-xl' data-id='" + reserva.getIdReserva() + "'>Cancelar</button></td>");
                    out.println("</tr>");
                }

                out.println("</tbody>");
                out.println("</table>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error al obtener las reservas.");
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idReserva = Integer.parseInt(request.getParameter("idReserva"));
        
        Connection conexion = null;
        try{
            HttpSession session = request.getSession(false);
            conexion = OperacionesBaseDatos.conectar();
            OperacionesReservas opRes = new OperacionesReservas(conexion);
            boolean cancelado = opRes.eliminarDatos(idReserva);
            
            if (cancelado) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("Reserva cancelada con éxito.");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println("Error al cancelar la reserva.");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvEliminarReserva.class.getName()).log(Level.SEVERE, null, ex);
        }






    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
