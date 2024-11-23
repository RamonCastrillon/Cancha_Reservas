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
public class OperacionesUsuarios {
    private Connection conexion;
    
    public OperacionesUsuarios(Connection conexion) {
        this.conexion = conexion; 
    }
    
    public boolean insertarUsuario(Usuario user) throws ClassNotFoundException {
    String query = "INSERT INTO usuarios (Identificacion,Contrasena,Rol) VALUES (?, ?, ?)";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        String contrasenaHasheada = BCrypt.hashpw(user.getContrasena(), BCrypt.gensalt());
        
        statement.setInt(1, user.getIdUsuario()); 
        statement.setString(2, contrasenaHasheada);  
        statement.setString(3, user.getRol());  
        
        statement.executeUpdate();  
        
        System.out.println("Datos insertados correctamente.");
        return true;
        
    } catch (SQLException e) {
        System.out.println("Error al insertar datos: " + e.getMessage()); 
        return false;
    }
}
    public boolean actualizarDatos(String estado, String contrasena, int id) throws ClassNotFoundException, SQLException {
    String query = "UPDATE usuarios SET Contrasena = ?, estadoUsuario = ? WHERE Identificacion = ?"; // where para saber que fila modifica
    Usuario user = buscarUsuarioPorId(id);
    
    try (Connection connection = conexion;
        PreparedStatement statement = connection.prepareStatement(query)) {
            if(contrasena != null && !contrasena.equals("")){
                String contrasenaHasheada = BCrypt.hashpw(contrasena, BCrypt.gensalt());
                statement.setString(1, contrasenaHasheada);
            }
            else{
                statement.setString(1, user.getContrasena());
            }
            if(estado != null){
                statement.setString(2,estado);
            }
            else{
                statement.setString(2,user.getEstado());
            }
            statement.setInt(3,id);
            
            int filasActualizadas = statement.executeUpdate();
        
            if (filasActualizadas > 0) {
                System.out.println("Datos actualizados correctamente.");
            } else {
                System.out.println("No se encontró el registro con ID: " + id);
            }

            return true;
        }catch (SQLException e) {
            System.out.println("Error al actualizar datos: " + e.getMessage());
            return false;
        }

}
    public ArrayList<Usuario> buscarTodasUsuarios() throws SQLException {
        
    ArrayList<Usuario> usuarios = new ArrayList<>();

    String query = "SELECT* FROM usuarios";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Usuario user = new Usuario();
            user.setIdUsuario(rs.getInt("Identificacion"));
            user.setRol(rs.getString("Rol"));
            user.setRol(rs.getString("estadoUsuario"));
            
            usuarios.add(user);
        }
    }
    return usuarios;
    }
    
    public Usuario buscarUsuarioPorId(int identificacion) throws SQLException {
        
        Usuario user = new Usuario();
        String query = "SELECT* FROM usuarios WHERE Identificacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, identificacion);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
            user.setIdUsuario(identificacion);
            user.setContrasena(rs.getString("Contrasena"));
            user.setRol(rs.getString("Rol"));
            user.setEstado(rs.getString("estadoUsuario"));
            }

            }

        return user;
        }
    
    public ArrayList<Usuario> buscarUsuarioPorEstado(String estadoUsuario) throws SQLException {
        
    ArrayList<Usuario> usuarios = new ArrayList<>();

    String query = "SELECT* FROM usuarios WHERE estadoUsuario = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, estadoUsuario);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Usuario user = new Usuario();

            user.setIdUsuario(rs.getInt("Identificacion"));
            user.setRol(rs.getString("Rol"));
            user.setEstado(estadoUsuario);
            
            usuarios.add(user);
        }
    }
    return usuarios;
    }
    
    public ArrayList<Usuario> buscarUsuarioPorRol(String rol) throws SQLException {
        
    ArrayList<Usuario> usuarios = new ArrayList<>();

    String query = "SELECT* FROM usuarios WHERE Rol = ?";
    
    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, rol);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Usuario user = new Usuario();

            user.setIdUsuario(rs.getInt("Identificacion"));
            user.setRol(rol);
            user.setEstado(rs.getString("estadoUsuario"));
            
            usuarios.add(user);
        }
    }
    return usuarios;
    }
    
    public void eliminarDatos(int id) throws ClassNotFoundException {
    String query = "DELETE FROM usuarios WHERE Identificacion = ?";
    
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
