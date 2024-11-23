/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUDI;

/**
 *
 * @author ramon
 */
public class Pagina {

    public Pagina() {
    }
    
    private int id;
    private String correo;
    private long numeroCelular;
    private int valorReserva;

    public int getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(int valorReserva) {
        this.valorReserva = valorReserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(long numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    
    
}
