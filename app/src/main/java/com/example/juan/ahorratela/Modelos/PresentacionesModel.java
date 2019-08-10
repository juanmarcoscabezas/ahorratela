package com.example.juan.ahorratela.Modelos;

/**
 * Created by juan on 30/09/2018.
 */

public class PresentacionesModel {
    int id;
    String nombre;

    public PresentacionesModel(int id, String nombre) {
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
