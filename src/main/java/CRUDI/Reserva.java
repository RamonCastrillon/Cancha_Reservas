/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUDI;

import java.sql.Timestamp;
import java.time.Instant;

/**
 *
 * @author ramon
 */
public class Reserva {
    private int idReserva;
    private int idUsuario;
    private String nombreUsuario;
    private String primerApellido;
    private String segundoApellido;
    private Timestamp fechaReserva;
    private Timestamp fechaGeneracion;
    private int valorReserva;
    private String estadoReserva;

    public Reserva() {
    }
    
    public Reserva(int idUsuario,String nombreUsuario, String primerApellido, String segundoApellido, Timestamp fechaReserva) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaReserva = fechaReserva;
        this.fechaGeneracion = Timestamp.from(Instant.now());
    }


    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }



    public Timestamp getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Timestamp fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Timestamp getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Timestamp fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(int valorReserva) {
        this.valorReserva = valorReserva;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
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
    
    
    
}
