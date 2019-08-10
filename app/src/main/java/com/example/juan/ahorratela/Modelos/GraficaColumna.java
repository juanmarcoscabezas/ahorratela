package com.example.juan.ahorratela.Modelos;

public class GraficaColumna {
    String nombre;
    double ahorro;

    public GraficaColumna(String nombre, double ahorro) {
        this.nombre = nombre;
        this.ahorro = ahorro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getAhorro() {
        return ahorro;
    }

    public void setAhorro(double ahorro) {
        this.ahorro = ahorro;
    }
}
