/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUDI;

/**
 *
 * @author ramon
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; //Da fecha con la hora incluida
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperacionesReservas {

    private Connection conexion;
    
    public OperacionesReservas(Connection conexion) {
        this.conexion = conexion; 
    }
    
    
    
    public void insertarReserva(Reserva reservacion) throws ClassNotFoundException, SQLException {
    String query = "INSERT INTO reservaciones (ID_Usuario,Nombre_Usuario,primerApellido,segundoApellido,"
            + "fechaReserva,fechaGeneracion,valorReserva) VALUES (?, ?, ?, ?, ?, ?,?)";
    
    Connection conexion2 = OperacionesBaseDatos.conectar();
    Connection conexion3 = OperacionesBaseDatos.conectar();
    
    
    OperacionesInfoPagina opInfo = new OperacionesInfoPagina(conexion3);
    
    Pagina pagina = opInfo.buscarDatosPagina();

    OperacionesPersona opPerson = new OperacionesPersona(conexion2);
    opPerson.sumarNumeroReservas(reservacion.getIdUsuario());
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
             
        statement.setInt(1, reservacion.getIdUsuario());  // Los numeros son para el orden de insercion, no son el indice de filas
        statement.setString(2, reservacion.getNombreUsuario());  
        statement.setString(3, reservacion.getPrimerApellido());  
        statement.setString(4, reservacion.getSegundoApellido());
        statement.setTimestamp(5, reservacion.getFechaReserva());
        statement.setTimestamp(6, reservacion.getFechaGeneracion());
        statement.setInt(7, pagina.getValorReserva());
        statement.executeUpdate();       // Ejecuta la consulta de inserción
        
        
        
        System.out.println("Datos insertados correctamente.");
        
    } catch (SQLException e) {
        System.out.println("Error al insertar datos: " + e.getMessage()); 
        
    }
}
    
    
    
    public void actualizarDatos(String estado, int id) throws ClassNotFoundException {
    String query = "UPDATE reservaciones SET estadoReserva = ? WHERE ID_Reserva = ?"; // where para saber que fila modifica
    
    try (Connection connection = conexion;
        PreparedStatement statement = connection.prepareStatement(query)) {
        
        statement.setString(1, estado);
        statement.setInt(2,id); //El dato del where se pone al final es segun el cual va a buscar la fila a modificar
        
        int filasActualizadas = statement.executeUpdate();
        
        if (filasActualizadas > 0) {
            System.out.println("Datos actualizados correctamente.");
        } else {
            System.out.println("No se encontró el registro con ID: " + id);
        }
        
    } catch (SQLException e) {
        System.out.println("Error al actualizar datos: " + e.getMessage());
    }
}
    
    public boolean actualizarValorReservaciones(int valorReserva){
        String query = "ALTER TABLE reservas MODIFY COLUMN valorReserva INT DEFAULT ?";
        try (Connection connection = conexion;
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, valorReserva);
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<String> obtenerHorariosDisponibles(Timestamp fecha) throws SQLException {

        ArrayList<String> horariosDisponibles = new ArrayList<>();
        
        LocalDateTime fechaHora = fecha.toLocalDateTime();
        DayOfWeek diaDeLaSemana = fechaHora.getDayOfWeek();
        
       Integer[] horasPosibles;
        
        switch (diaDeLaSemana){
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                horasPosibles = new Integer[]{8,9,10,11,12,13,14,15,16,17,18,19,21,22,23};
                break;
            case SATURDAY:
            case SUNDAY:
                horasPosibles = new Integer[]{11,12,13,14,15,16,17,18,19,20,21,22,23};
                break;
            default:
                horasPosibles = new Integer[]{};
        }
        
        
        String query = "SELECT HOUR(fechaReserva) AS hora FROM reservaciones WHERE DATE(fechaReserva) = DATE(?)"; 
        //El date se aplica a ambas para que la comparacion se haga de manera correcta
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setTimestamp(1, fecha);
            ResultSet rs = statement.executeQuery(); //Almacena conjunto de resultados de la consulta (query)
            
            ArrayList<Integer> horasReservadas = new ArrayList<>();
            while (rs.next()) {  //Va recorriendo las filas de los resultados obtenidos con el query
                horasReservadas.add(rs.getInt("hora")); //Las horas quedan almacenadas como entero, solo la primera parte
            }
            
            // Comparar horas posibles con horas reservadas
            for (int hora : horasPosibles) {
                if (horasReservadas.contains(hora) == false) {
                    if(hora < 10){
                        horariosDisponibles.add("0"+hora+":00:00");
                    }
                    else{
                        horariosDisponibles.add(hora+":00:00");
                    }
                }
            }
        }
        return horariosDisponibles;
    }
    
    public ArrayList<Reserva> buscarTodasReservas() throws SQLException {
        
    ArrayList<Reserva> reservas = new ArrayList<>();

    String query = "SELECT* FROM reservaciones";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Reserva rv = new Reserva();
            rv.setIdReserva(rs.getInt("ID_Reserva"));
            rv.setIdUsuario(rs.getInt("ID_Usuario"));
            rv.setNombreUsuario(rs.getString("Nombre_Usuario"));
            rv.setPrimerApellido(rs.getString("primerApellido"));
            rv.setSegundoApellido(rs.getString("segundoApellido"));
            rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
            rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            rv.setValorReserva(rs.getInt("valorReserva"));
            rv.setEstadoReserva(rs.getString("estadoReserva"));
            
            reservas.add(rv);
        }
    }
    return reservas;
    }
    
    public ArrayList<Reserva> buscarReservaPorIdUsuario(int idUsuario) throws SQLException {
        
    ArrayList<Reserva> reservas = new ArrayList<>();

    String query = "SELECT* FROM reservaciones WHERE ID_Usuario = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, idUsuario);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Reserva rv = new Reserva();

            rv.setIdReserva(rs.getInt("ID_Reserva"));
            rv.setIdUsuario(idUsuario);
            rv.setNombreUsuario(rs.getString("Nombre_Usuario"));
            rv.setPrimerApellido(rs.getString("primerApellido"));
            rv.setSegundoApellido(rs.getString("segundoApellido"));
            rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
            rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            rv.setValorReserva(rs.getInt("valorReserva"));
            rv.setEstadoReserva(rs.getString("estadoReserva"));
            
            reservas.add(rv);
        }
    }
    return reservas;
    }
    
    public Reserva buscarReservaPorIdReserva(int idReserva) throws SQLException {
        
        Reserva rv = new Reserva();
        String query = "SELECT* FROM reservaciones WHERE ID_Reserva = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idReserva);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
            rv.setIdReserva(idReserva);
            rv.setIdUsuario(rs.getInt("ID_Usuario"));
            rv.setNombreUsuario(rs.getString("Nombre_Usuario"));
            rv.setPrimerApellido(rs.getString("primerApellido"));
            rv.setSegundoApellido(rs.getString("segundoApellido"));
            rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
            rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            rv.setValorReserva(rs.getInt("valorReserva"));
            rv.setEstadoReserva(rs.getString("estadoReserva"));

            }

            }

        return rv;
        }
    
    public ArrayList<Reserva> buscarReservaPorNombre(String nombreUsuario) throws SQLException {
        
    ArrayList<Reserva> reservas = new ArrayList<>();

    String query = "SELECT* FROM reservaciones WHERE Nombre_Usuario = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, nombreUsuario);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Reserva rv = new Reserva();

            rv.setIdReserva(rs.getInt("ID_Reserva"));
            rv.setIdUsuario(rs.getInt("ID_Usuario"));
            rv.setNombreUsuario(nombreUsuario);
            rv.setPrimerApellido(rs.getString("primerApellido"));
            rv.setSegundoApellido(rs.getString("segundoApellido"));
            rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
            rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            rv.setValorReserva(rs.getInt("valorReserva"));
            rv.setEstadoReserva(rs.getString("estadoReserva"));
            
            reservas.add(rv);
        }
    }
    return reservas;
    }
    
    public ArrayList<Reserva> buscarReservaPorApellido(String primerApellido) throws SQLException {
        
    ArrayList<Reserva> reservas = new ArrayList<>();

    String query = "SELECT* FROM reservaciones WHERE primerApellido = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, primerApellido);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Reserva rv = new Reserva();

            rv.setIdReserva(rs.getInt("ID_Reserva"));
            rv.setIdUsuario(rs.getInt("ID_Usuario"));
            rv.setNombreUsuario(rs.getString("Nombre_Usuario"));
            rv.setPrimerApellido(primerApellido);
            rv.setSegundoApellido(rs.getString("segundoApellido"));
            rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
            rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            rv.setValorReserva(rs.getInt("valorReserva"));
            rv.setEstadoReserva(rs.getString("estadoReserva"));
            
            reservas.add(rv);
        }
    }
    return reservas;
    }
    
   
    
    public ArrayList<Reserva> buscarReservaPorFechaReserva(Timestamp fechaReserva) throws SQLException {
        
    ArrayList<Reserva> reservas = new ArrayList<>();

    String query = "SELECT* FROM reservaciones WHERE DATE(fechaReserva) = DATE(?)"; //DAte pa que solo coja la fecha y no la hora
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setTimestamp(1, fechaReserva);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Reserva rv = new Reserva();

            rv.setIdReserva(rs.getInt("ID_Reserva"));
            rv.setIdUsuario(rs.getInt("ID_Usuario"));
            rv.setNombreUsuario(rs.getString("Nombre_Usuario"));
            rv.setPrimerApellido(rs.getString("primerApellido"));
            rv.setSegundoApellido(rs.getString("segundoApellido"));
            rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
            rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            rv.setValorReserva(rs.getInt("valorReserva"));
            rv.setEstadoReserva(rs.getString("estadoReserva"));
            
            reservas.add(rv);
        }
    }
    return reservas;
    }
    
    public ArrayList<Reserva> buscarReservaPorFechaGeneracion(Timestamp fechaGeneracion) throws SQLException {
        
    ArrayList<Reserva> reservas = new ArrayList<>();

    String query = "SELECT* FROM reservaciones WHERE DATE(fechaGeneracion) = DATE(?)"; //DAte pa que solo coja la fecha y no la hora
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setTimestamp(1, fechaGeneracion);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Reserva rv = new Reserva();

            rv.setIdReserva(rs.getInt("ID_Reserva"));
            rv.setIdUsuario(rs.getInt("ID_Usuario"));
            rv.setNombreUsuario(rs.getString("Nombre_Usuario"));
            rv.setPrimerApellido(rs.getString("primerApellido"));
            rv.setSegundoApellido(rs.getString("segundoApellido"));
            rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
            rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            rv.setValorReserva(rs.getInt("valorReserva"));
            rv.setEstadoReserva(rs.getString("estadoReserva"));
            
            reservas.add(rv);
        }
    }
    return reservas;
    }
    
    public ArrayList<Reserva> buscarReservaPorEstado(String estadoReserva) throws SQLException {
        
    ArrayList<Reserva> reservas = new ArrayList<>();

    String query = "SELECT* FROM reservaciones WHERE estadoReserva = ?"; //DAte pa que solo coja la fecha y no la hora
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, estadoReserva);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Reserva rv = new Reserva();

            rv.setIdReserva(rs.getInt("ID_Reserva"));
            rv.setIdUsuario(rs.getInt("ID_Usuario"));
            rv.setNombreUsuario(rs.getString("Nombre_Usuario"));
            rv.setPrimerApellido(rs.getString("primerApellido"));
            rv.setSegundoApellido(rs.getString("segundoApellido"));
            rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
            rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            rv.setValorReserva(rs.getInt("valorReserva"));
            rv.setEstadoReserva(estadoReserva);
            
            reservas.add(rv);
        }
    }
    return reservas;
    }
    
    public ArrayList<Reserva> buscarReservaPorSemana(Timestamp fechaReserva) throws SQLException {
        ArrayList<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM reservaciones WHERE YEARWEEK(fechaReserva) = YEARWEEK(?)";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setTimestamp(1, fechaReserva);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Reserva rv = new Reserva();
                rv.setIdReserva(rs.getInt("ID_Reserva"));
                rv.setIdUsuario(rs.getInt("ID_Usuario"));
                rv.setNombreUsuario(rs.getString("Nombre_Usuario"));
                rv.setPrimerApellido(rs.getString("primerApellido"));
                rv.setSegundoApellido(rs.getString("segundoApellido"));
                rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
                rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
                rv.setValorReserva(rs.getInt("valorReserva"));
                rv.setEstadoReserva(rs.getString("estadoReserva"));
                reservas.add(rv);
            }
        }
        return reservas;
    }

    public ArrayList<Reserva> buscarReservaPorMes(Timestamp fechaReserva) throws SQLException {
        ArrayList<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM reservaciones WHERE YEAR(fechaReserva) = YEAR(?) AND MONTH(fechaReserva) = MONTH(?)";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setTimestamp(1, fechaReserva);
            statement.setTimestamp(2, fechaReserva);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Reserva rv = new Reserva();
                rv.setIdReserva(rs.getInt("ID_Reserva"));
                rv.setIdUsuario(rs.getInt("ID_Usuario"));
                rv.setNombreUsuario(rs.getString("Nombre_Usuario"));
                rv.setPrimerApellido(rs.getString("primerApellido"));
                rv.setSegundoApellido(rs.getString("segundoApellido"));
                rv.setFechaReserva(rs.getTimestamp("fechaReserva"));
                rv.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
                rv.setValorReserva(rs.getInt("valorReserva"));
                rv.setEstadoReserva(rs.getString("estadoReserva"));
                reservas.add(rv);
            }
        }
        return reservas;
    }
    
    public boolean eliminarDatos(int id) throws ClassNotFoundException {
    String query = "DELETE FROM reservaciones WHERE ID_Reserva = ?";
    Connection conexion2 = OperacionesBaseDatos.conectar();
    OperacionesPersona opPerson = new OperacionesPersona(conexion2);
    Reserva res;
    
        try {
            res = buscarReservaPorIdReserva(id);
            opPerson.restarNumeroReservas(res.getIdUsuario());
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    try (Connection connection = conexion;
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, id);       
        statement.executeUpdate();
        
        
        System.out.println("Reserva eliminada correctamente.");
        return true;
        
    } catch (SQLException e) {
        System.out.println("Error al eliminar datos: " + e.getMessage());
        return false;
    }
}

}
