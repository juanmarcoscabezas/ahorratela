package com.example.juan.ahorratela.Modelos;

/**
 * Created by juan on 28/09/2018.
 */

public class ProductosModel {
    private Integer id;
    private String nombre;
    private String presentacion;
    private String unidad;
    private Integer medida;

    public ProductosModel(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public ProductosModel(Integer id, String nombre, String presentacion, String unidad, Integer medida) {
        this.id = id;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.unidad = unidad;
        this.medida = medida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Integer getMedida() {
        return medida;
    }

    public void setMedida(Integer medida) {
        this.medida = medida;
    }
}
