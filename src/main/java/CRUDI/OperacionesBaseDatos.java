/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUDI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ramon
 */
public class OperacionesBaseDatos {
    private static final String RUTA = "jdbc:mysql://localhost:3306/Cancha?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root"; 

    public static Connection conectar() throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Para conectar con el driver que instale
            Connection conexion = DriverManager.getConnection(RUTA, USUARIO, CONTRASENA); //Crear la conexión con la base de datos
            System.out.println("Conexión exitosa a la base de datos.");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error en la conexión a la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
}
