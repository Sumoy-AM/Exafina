package com.example.exafina;

import java.util.List;

public class Articulo {
    private String titulo;
    private String resumen;
    private String doi;
    private String pdfUrl;
    private String htmlUrl;
    private List<String> autores;

    public Articulo(String titulo, String resumen, String doi, String pdfUrl, String htmlUrl, List<String> autores) {
        this.titulo = titulo;
        this.resumen = resumen;
        this.doi = doi;
        this.pdfUrl = pdfUrl;
        this.htmlUrl = htmlUrl;
        this.autores = autores;
    }

    public String getTitulo() { return titulo; }
    public String getResumen() { return resumen; }
    public String getDoi() { return doi; }
    public String getPdfUrl() { return pdfUrl; }
    public String getHtmlUrl() { return htmlUrl; }
    public List<String> getAutores() { return autores; }
}
