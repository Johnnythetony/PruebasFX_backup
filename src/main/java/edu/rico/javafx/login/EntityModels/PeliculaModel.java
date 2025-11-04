package edu.rico.javafx.login.EntityModels;

import javafx.beans.property.SimpleStringProperty;

public class PeliculaModel
{
    private int id = -1;
    private SimpleStringProperty titulo = new SimpleStringProperty();
    private SimpleStringProperty genero = new SimpleStringProperty();
    private SimpleStringProperty director = new SimpleStringProperty();
    private SimpleStringProperty anho = new SimpleStringProperty();
    private SimpleStringProperty portada =  new SimpleStringProperty();

    public PeliculaModel(int id, String titulo, String genero, String director, String anho, String portada)
    {
        this.id = id;
        this.titulo.setValue(titulo);
        this.genero.setValue(genero);
        this.director.setValue(director);
        this.anho.set(anho);
        this.portada.set(portada);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo.get();
    }

    public SimpleStringProperty tituloProperty() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public String getGenero() {
        return genero.get();
    }

    public SimpleStringProperty generoProperty() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public String getDirector() {
        return director.get();
    }

    public SimpleStringProperty directorProperty() {
        return director;
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public String getAnho() {
        return anho.get();
    }

    public SimpleStringProperty anhoProperty() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho.set(anho);
    }

    public String getPortada() {
        return portada.get();
    }

    public SimpleStringProperty portadaProperty() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada.set(portada);
    }
}
