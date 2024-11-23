/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUDI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ramon
 */
public class OperacionesPersona {
    private Connection conexion;
    
    public OperacionesPersona(Connection conexion) {
        this.conexion = conexion; 
    }
    
    public boolean insertarPersona(Persona pers) throws ClassNotFoundException {
    String query = "INSERT INTO informacion_usuarios (Identificacion,nombre,primerApellido,segundoApellido,fechaNacimiento,"
            + "numeroCelular,correoElectronico) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        java.sql.Date fechaSQL = new java.sql.Date(pers.getFechaNacimiento().getTime());
        
        statement.setInt(1, pers.getIdentificacion()); 
        statement.setString(2, pers.getNombre());  
        statement.setString(3, pers.getPrimerApellido());  
        statement.setString(4, pers.getSegundoApellido());  
        statement.setDate(5, fechaSQL);
        statement.setLong(6, pers.getNumeroCelular());
        statement.setString(7, pers.getCorreoElectronico());
        
        
        statement.executeUpdate();  
        
        System.out.println("Datos insertados correctamente.");
        
        return true;
        
    } catch (SQLException e) {
        System.out.println("Error al insertar datos: " + e.getMessage()); 
        return false;
    }
}
    
    public boolean actualizarDatos(long numeroCelular, String correoElectronico, int id) throws ClassNotFoundException, SQLException {
        String query = "UPDATE informacion_usuarios SET numeroCelular = ?, correoElectronico = ? WHERE Identificacion = ?";
        Persona person = buscarPersonaPorId(id);

        try (Connection connection = conexion;
            PreparedStatement statement = connection.prepareStatement(query)) {

            if(numeroCelular != 0){
                statement.setLong(1, numeroCelular);
            }
            else{
                statement.setLong(1, person.getNumeroCelular());
            }
            if(correoElectronico != null && !correoElectronico.equals("")){
                statement.setString(2,correoElectronico);
            }
            else{
                statement.setString(2,person.getCorreoElectronico());
            }
            statement.setInt(3,id);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Datos personales actualizados correctamente.");
            } else {
                System.out.println("No se encontró el registro con ID: " + id);
            }
             return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos: " + e.getMessage());
            return false;
        }
}
    public void sumarNumeroReservas(int id) throws ClassNotFoundException {
    String query = "UPDATE informacion_usuarios SET numeroReservas = ? WHERE Identificacion = ?"; // where para saber que fila modifica
    
    try (Connection connection = conexion;
        PreparedStatement statement = connection.prepareStatement(query)) {
        Persona person = buscarPersonaPorId(id);
        int nuevasReservas = person.getNumeroReservas()+1;
        
        statement.setInt(1,nuevasReservas);
        statement.setInt(2,id); 
        
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
    
    public void restarNumeroReservas(int id) throws ClassNotFoundException {
    String query = "UPDATE informacion_usuarios SET numeroReservas = ? WHERE Identificacion = ?"; // where para saber que fila modifica
    
    try (Connection connection = conexion;
        PreparedStatement statement = connection.prepareStatement(query)) {
        Persona person = buscarPersonaPorId(id);
        int nuevasReservas = person.getNumeroReservas()-1;
        
        statement.setInt(1, nuevasReservas);
        statement.setInt(2,id); 
        
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
    
    public void actualizarReservasGratis(int id) throws ClassNotFoundException {
    String query = "UPDATE informacion_usuarios SET reservasGratis = ? WHERE Identificacion = ?"; // where para saber que fila modifica
    
    try (Connection connection = conexion;
        PreparedStatement statement = connection.prepareStatement(query)) {
        Persona person = buscarPersonaPorId(id);
        
        statement.setInt(1, person.getReservasGratis()+1);
        statement.setInt(2,id); 
        
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
    
    public ArrayList<Persona> buscarTodasPersonas() throws SQLException {
        
    ArrayList<Persona> personas = new ArrayList<>();

    String query = "SELECT* FROM informacion_usuarios";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Persona person = new Persona();
            person.setIdentificacion(rs.getInt("Identificacion"));
            person.setNombre(rs.getString("nombre"));
            person.setPrimerApellido(rs.getString("primerApellido"));
            person.setSegundoApellido(rs.getString("segundoApellido"));
            person.setFechaNacimiento(rs.getDate("fechaNacimiento"));
            person.setNumeroCelular(rs.getLong("numeroCelular"));
            person.setCorreoElectronico(rs.getString("correoElectronico"));
            person.setNumeroReservas(rs.getInt("numeroReservas"));
            person.setReservasGratis(rs.getInt("reservasGratis"));
            
            personas.add(person);
        }
    }
    return personas;
    }
    
    public Persona buscarPersonaPorId(int id) throws SQLException {
        
        Persona person = new Persona();
        String query = "SELECT* FROM informacion_usuarios WHERE Identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
            person.setIdentificacion(id);
            person.setNombre(rs.getString("nombre"));
            person.setPrimerApellido(rs.getString("primerApellido"));
            person.setSegundoApellido(rs.getString("segundoApellido"));
            person.setFechaNacimiento(rs.getDate("fechaNacimiento"));
            person.setNumeroCelular(rs.getLong("numeroCelular"));
            person.setCorreoElectronico(rs.getString("correoElectronico"));
            person.setNumeroReservas(rs.getInt("numeroReservas"));
            person.setReservasGratis(rs.getInt("reservasGratis"));
            }

            }

        return person;
        }
    
    public Persona buscarPersonaPorCelular(long numeroCelular) throws SQLException {
        
        Persona person = new Persona();
        String query = "SELECT* FROM informacion_usuarios WHERE numeroCelular = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setLong(1, numeroCelular);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
            person.setIdentificacion(rs.getInt("Identificacion"));
            person.setNombre(rs.getString("nombre"));
            person.setPrimerApellido(rs.getString("primerApellido"));
            person.setSegundoApellido(rs.getString("segundoApellido"));
            person.setFechaNacimiento(rs.getDate("fechaNacimiento"));
            person.setNumeroCelular(rs.getLong("numeroCelular"));
            person.setCorreoElectronico(rs.getString("correoElectronico"));
            person.setNumeroReservas(rs.getInt("numeroReservas"));
            person.setReservasGratis(rs.getInt("reservasGratis"));
            }

            }

        return person;
        }
    
    public Persona buscarPersonaPorCorreo(String correoElectronico) throws SQLException {
        
        Persona person = new Persona();
        String query = "SELECT* FROM informacion_usuarios WHERE correoElectronico = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, correoElectronico);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
            person.setIdentificacion(rs.getInt("Identificacion"));
            person.setNombre(rs.getString("nombre"));
            person.setPrimerApellido(rs.getString("primerApellido"));
            person.setSegundoApellido(rs.getString("segundoApellido"));
            person.setFechaNacimiento(rs.getDate("fechaNacimiento"));
            person.setNumeroCelular(rs.getLong("numeroCelular"));
            person.setCorreoElectronico(rs.getString("correoElectronico"));
            person.setNumeroReservas(rs.getInt("numeroReservas"));
            person.setReservasGratis(rs.getInt("reservasGratis"));
            }

            }

        return person;
        }
    
    public ArrayList<Persona> buscarPersonaPorNombre(String nombre) throws SQLException {
        
    ArrayList<Persona> personas = new ArrayList<>();

    String query = "SELECT* FROM informacion_usuarios WHERE nombre = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, nombre);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Persona person = new Persona();

            person.setIdentificacion(rs.getInt("Identificacion"));
            person.setNombre(rs.getString("nombre"));
            person.setPrimerApellido(rs.getString("primerApellido"));
            person.setSegundoApellido(rs.getString("segundoApellido"));
            person.setFechaNacimiento(rs.getDate("fechaNacimiento"));
            person.setNumeroCelular(rs.getLong("numeroCelular"));
            person.setCorreoElectronico(rs.getString("correoElectronico"));
            person.setNumeroReservas(rs.getInt("numeroReservas"));
            person.setReservasGratis(rs.getInt("reservasGratis"));
            
            personas.add(person);
        }
    }
    return personas;
    }
    
    public ArrayList<Persona> buscarPersonaPorApellido(String primerApellido) throws SQLException {
        
    ArrayList<Persona> personas = new ArrayList<>();

    String query = "SELECT* FROM informacion_usuarios WHERE primerApellido = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, primerApellido);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Persona person = new Persona();

            person.setIdentificacion(rs.getInt("Identificacion"));
            person.setNombre(rs.getString("nombre"));
            person.setPrimerApellido(rs.getString("primerApellido"));
            person.setSegundoApellido(rs.getString("segundoApellido"));
            person.setFechaNacimiento(rs.getDate("fechaNacimiento"));
            person.setNumeroCelular(rs.getLong("numeroCelular"));
            person.setCorreoElectronico(rs.getString("correoElectronico"));
            person.setNumeroReservas(rs.getInt("numeroReservas"));
            person.setReservasGratis(rs.getInt("reservasGratis"));
            
            personas.add(person);
        }
    }
    return personas;
    }
    
    public void eliminarDatos(int id) throws ClassNotFoundException {
    String query = "DELETE FROM informacion_usuarios WHERE Identificacion = ?";
    
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
