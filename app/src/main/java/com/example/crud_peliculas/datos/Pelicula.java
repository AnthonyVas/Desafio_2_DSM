package com.example.crud_peliculas.datos;

public class Pelicula {
    private String titulo;
    private String desc;
    private  String year;
    private String rate;
    String key;
    public Pelicula() {
    }

    public Pelicula(String titulo, String desc, String year, String rate) {
        this.titulo = titulo;
        this.desc = desc;
        this.year = year;
        this.rate = rate;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}