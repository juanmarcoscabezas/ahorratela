package com.example.juan.ahorratela.Modelos;

public class ComprasModel {

    private int id;
    private int id_producto;
    private int id_ubicacion;
    private String fecha;
    private int valor_compra;
    private float valor_ahorro;
    private String nombre_producto;



    public ComprasModel(int id_producto, int id_ubicacion, String fecha, int valor_compra, float valor_ahorro) {
        this.id_producto = id_producto;
        this.id_ubicacion = id_ubicacion;
        this.fecha=fecha;
        this.valor_compra = valor_compra;
        this.valor_ahorro=valor_ahorro;
    }

    public ComprasModel(int id_producto, int id_ubicacion, int valor_compra, String nombre_producto) {
        this.id_producto = id_producto;
        this.id_ubicacion = id_ubicacion;
        this.valor_compra = valor_compra;
        this.nombre_producto = nombre_producto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int producto) {
        this.id_producto = producto;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.id_ubicacion = ubicacion;
    }

    public int getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(int valor_compra) {
        this.valor_compra = valor_compra;
    }

    public float getValor_ahorro() {
        return valor_ahorro;
    }

    public void setValor_ahorro(float valor_ahorro) {
        this.valor_ahorro = valor_ahorro;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
}

