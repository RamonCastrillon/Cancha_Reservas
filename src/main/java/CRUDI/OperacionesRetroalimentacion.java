/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUDI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ramon
 */
public class OperacionesRetroalimentacion {
    private Connection conexion;
    
    public OperacionesRetroalimentacion(Connection conexion) {
        this.conexion = conexion; 
    }
    
    public void insertarRetroalimentacion(Retroalimentacion retro) throws ClassNotFoundException {
    String query = "INSERT INTO retroalimentaciones (idAutor,textoRetroalimentacion,fechaGeneracion) VALUES (?, ?, ?)";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
             
        statement.setInt(1, retro.getIdAutor()); 
        statement.setString(2, retro.getTextoRetroalimentacion());  
        statement.setTimestamp(3, retro.getFechaGeneracion());  
        
        statement.executeUpdate();  
        
        System.out.println("Datos insertados correctamente.");
        
    } catch (SQLException e) {
        System.out.println("Error al insertar datos: " + e.getMessage()); 
        
    }
}
    
    public ArrayList<Retroalimentacion> buscarTodasRetroalimentaciones() throws SQLException {
        
    ArrayList<Retroalimentacion> retroalimentaciones = new ArrayList<>();

    String query = "SELECT* FROM retroalimentaciones";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Retroalimentacion retro = new Retroalimentacion();
            retro.setIdRetroalimentacion(rs.getInt("idRetroalimentacion"));
            retro.setIdAutor(rs.getInt("idAutor"));
            retro.setTextoRetroalimentacion(rs.getString("textoRetroalimentacion"));
            retro.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            
            retroalimentaciones.add(retro);
        }
    }
    return retroalimentaciones;
    }
    
    public Retroalimentacion buscarRetroalimentacionPorId(int id) throws SQLException {
        
        Retroalimentacion retro = new Retroalimentacion();
        String query = "SELECT* FROM retroalimentaciones WHERE idRetroalimentacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
            retro.setIdRetroalimentacion(rs.getInt("idRetroalimentacion"));
            retro.setIdAutor(rs.getInt("idAutor"));
            retro.setTextoRetroalimentacion(rs.getString("textoRetroalimentacion"));
            retro.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
           
        }
    }
    return retro;
    }
    
     public ArrayList<Retroalimentacion> buscarRetroalimentacionPorFecha(Timestamp fechaGeneracion) throws SQLException {
        
    ArrayList<Retroalimentacion> retroalimentaciones = new ArrayList<>();

    String query = "SELECT* FROM retroalimentaciones WHERE DATE(fechaGeneracion) = DATE(?)";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setTimestamp(1, fechaGeneracion);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Retroalimentacion retro = new Retroalimentacion();
            retro.setIdRetroalimentacion(rs.getInt("idRetroalimentacion"));
            retro.setIdAutor(rs.getInt("idAutor"));
            retro.setTextoRetroalimentacion(rs.getString("textoRetroalimentacion"));
            retro.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            
            retroalimentaciones.add(retro);
        }
    }
    return retroalimentaciones;
    }
     
     public ArrayList<Retroalimentacion> buscarRetroalimentacionPorMes(Timestamp fechaGeneracion) throws SQLException {
        
    ArrayList<Retroalimentacion> retroalimentaciones = new ArrayList<>();

    String query = "SELECT* FROM retroalimentaciones WHERE MONTH(fechaGeneracion) = MONTH(?)";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setTimestamp(1, fechaGeneracion);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Retroalimentacion retro = new Retroalimentacion();
            retro.setIdRetroalimentacion(rs.getInt("idRetroalimentacion"));
            retro.setIdAutor(rs.getInt("idAutor"));
            retro.setTextoRetroalimentacion(rs.getString("textoRetroalimentacion"));
            retro.setFechaGeneracion(rs.getTimestamp("fechaGeneracion"));
            
            retroalimentaciones.add(retro);
        }
    }
    return retroalimentaciones;
    }
     
     public void eliminarDatos(int id) throws ClassNotFoundException {
    String query = "DELETE FROM retroalimentaciones WHERE idRetroalimentacion = ?";
    
    try (Connection connection = conexion;
         PreparedStatement statement = connection.prepareStatement(query)) {
             
        statement.setInt(1, id);       
        statement.executeUpdate();     
        
        System.out.println("Datos eliminados correctamente.");
        
    } catch (SQLException e) {
        System.out.println("Error al eliminar datos: " + e.getMessage());
    }
}
}
