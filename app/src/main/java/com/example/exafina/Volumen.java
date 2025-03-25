package com.example.exafina;

public class Volumen {
    private String id;
    private String volumen;
    private String numero;
    private String anio;
    private String fechaPublicacion;
    private String titulo;
    private String doi;
    private String portada;

    public Volumen(String id, String volumen, String numero, String anio,
                   String fechaPublicacion, String titulo, String doi, String portada) {
        this.id = id;
        this.volumen = volumen;
        this.numero = numero;
        this.anio = anio;
        this.fechaPublicacion = fechaPublicacion;
        this.titulo = titulo;
        this.doi = doi;
        this.portada = portada;
    }

    public String getId() {
        return id;
    }

    public String getVolumen() {
        return volumen;
    }

    public String getNumero() {
        return numero;
    }

    public String getAnio() {
        return anio;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDoi() {
        return doi;
    }

    public String getPortada() {
        return portada;
    }

    @Override
    public String toString() {
        return "Volumen{" +
                "id='" + id + '\'' +
                ", volumen='" + volumen + '\'' +
                ", numero='" + numero + '\'' +
                ", anio='" + anio + '\'' +
                ", fechaPublicacion='" + fechaPublicacion + '\'' +
                ", titulo='" + titulo + '\'' +
                ", doi='" + doi + '\'' +
                ", portada='" + portada + '\'' +
                '}';
    }
}
