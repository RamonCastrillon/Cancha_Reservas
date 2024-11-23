/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUDI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ramon
 */
public class OperacionesInfoPagina {
    private Connection conexion;
    
    public OperacionesInfoPagina(Connection conexion) {
        this.conexion = conexion; 
    }
    
    public boolean actualizarDatos(String correoElectronico, long numeroCelular, int valorReserva) throws ClassNotFoundException, SQLException {
        String query = "UPDATE informacion_pagina SET numeroCelular = ?, correoElectronico = ?,valorReserva = ? WHERE idInfo = 1";
        Pagina page = buscarDatosPagina();

        try (Connection connection = conexion;
            PreparedStatement statement = connection.prepareStatement(query)) {

            if(numeroCelular != 0){
                statement.setLong(1, numeroCelular);
            }
            else{
                statement.setLong(1, page.getNumeroCelular());
            }
            if(correoElectronico != null && !correoElectronico.equals("")){
                statement.setString(2,correoElectronico);
            }
            else{
                statement.setString(2,page.getCorreo());
            }
            if(valorReserva != 0){
                statement.setInt(3, valorReserva);
            }
            else{
                statement.setInt(3, page.getValorReserva());
            }
            
            
            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Datos de la pagina actualizados correctamente.");
            } else {
                System.out.println("Error al actualizar datos.");
            }
             return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos: " + e.getMessage());
            return false;
        }
}
    
    public Pagina buscarDatosPagina() throws SQLException {
        
        Pagina page = new Pagina();
        String query = "SELECT* FROM informacion_pagina WHERE idInfo = 1";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
            page.setId(rs.getInt("idInfo"));
            page.setCorreo(rs.getString("correoElectronico"));
            page.setNumeroCelular(rs.getLong("numeroCelular"));
            page.setValorReserva(rs.getInt("valorReserva"));
            }
            return page;

        }
    }
}
