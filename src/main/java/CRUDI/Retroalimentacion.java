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
public class Retroalimentacion {
    private int idRetroalimentacion;
    private int idAutor;
    private String textoRetroalimentacion;
    private Timestamp fechaGeneracion;

    public Retroalimentacion() {
    }
    
    public Retroalimentacion(int idAutor, String textoRetroalimentacion) {
        this.idAutor = idAutor;
        this.textoRetroalimentacion = textoRetroalimentacion;
        this.fechaGeneracion = Timestamp.from(Instant.now());
    }

    public int getIdRetroalimentacion() {
        return idRetroalimentacion;
    }

    public void setIdRetroalimentacion(int idRetroalimentacion) {
        this.idRetroalimentacion = idRetroalimentacion;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getTextoRetroalimentacion() {
        return textoRetroalimentacion;
    }

    public void setTextoRetroalimentacion(String textoRetroalimentacion) {
        this.textoRetroalimentacion = textoRetroalimentacion;
    }

    public Timestamp getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Timestamp fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
    
    
    
}
