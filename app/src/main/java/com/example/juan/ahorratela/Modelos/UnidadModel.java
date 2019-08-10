package com.example.juan.ahorratela.Modelos;

/**
 * Created by juan on 30/09/2018.
 */

public class UnidadModel {
    int id;
    String nombre;

    public UnidadModel(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
