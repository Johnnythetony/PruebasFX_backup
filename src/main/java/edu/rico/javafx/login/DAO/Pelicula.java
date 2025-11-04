package edu.rico.javafx.login.DAO;

import jakarta.persistence.*;

@Entity
@Table(name="peliculas")
public class Pelicula
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="titulo")
    private String titulo;

    @Column(name="anho")
    private String anho;

    @Column(name="genero")
    private String genero;

    @Column(name="director")
    private String director;

    @Column(name="portada")
    private String portada;

    public Pelicula () {}

    public Pelicula(int id, String titulo, String anho, String genero, String director, String portada)
    {
        this.id = id;
        this.titulo = titulo;
        this.anho = anho;
        this.genero = genero;
        this.director = director;
        this.portada = portada;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}
