package edu.rico.javafx.login.DAO;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="jugador")
public class Jugador
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="apodo")
    private String apodo;

    @Column(name="fechaNac")
    private Date fechaNac;

    @Column(name="estilo")
    private String estilo;

    public Jugador(){}

    public Jugador(int id, String nombre, String apellido, String apodo, Date fechaNac, String estilo)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.fechaNac = fechaNac;
        this.estilo = estilo;
    }

    public Jugador(String nombre, String apellido, String apodo, Date fechaNac, String estilo)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.fechaNac = fechaNac;
        this.estilo = estilo;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
}
