package com.example.exafina;

public class Revista {
    private String id;
    private String nombre;
    private String descripcion;
    private String portada;
    private String abbreviation;

    public Revista(String id, String nombre, String descripcion, String portada, String abbreviation) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portada = portada;
        this.abbreviation = abbreviation;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPortada() {
        return portada;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
