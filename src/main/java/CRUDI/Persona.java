/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUDI;

import java.sql.Date;

/**
 *
 * @author ramon
 */
public class Persona {
    private int identificacion;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private long numeroCelular;
    private String correoElectronico;
    private int numeroReservas;
    private int reservasGratis;
    

    public Persona() {
    }

    public Persona(int identificacion, String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, long numeroCelular, String correoElectronico) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroCelular = numeroCelular;
        this.correoElectronico = correoElectronico;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(long numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getNumeroReservas() {
        return numeroReservas;
    }

    public void setNumeroReservas(int numeroReservas) {
        this.numeroReservas = numeroReservas;
    }

    public int getReservasGratis() {
        return reservasGratis;
    }

    public void setReservasGratis(int reservasGratis) {
        this.reservasGratis = reservasGratis;
    }
    
    
    
}
