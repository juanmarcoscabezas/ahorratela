package com.example.juan.ahorratela.Modelos;

public class GraficaLineas {
    private String nombre;
    private int valor;
    private  double ahorro;

    public GraficaLineas(String nombre, int valor, double ahorro) {
        this.nombre = nombre;
        this.valor = valor;
        this.ahorro = ahorro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public double getAhorro() {
        return ahorro;
    }

    public void setAhorro(double ahorro) {
        this.ahorro = ahorro;
    }
}
