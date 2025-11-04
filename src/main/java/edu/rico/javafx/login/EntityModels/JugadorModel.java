package edu.rico.javafx.login.EntityModels;

import javafx.beans.property.SimpleStringProperty;

public class JugadorModel
{
    private int id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty apodo;
    private SimpleStringProperty fecha_nacimiento;
    private SimpleStringProperty estilo;

    public JugadorModel(int id, String nombre, String apellido, String apodo, String fecha_nacimiento, String estilo)
    {
        this.id = id;
        this.nombre.set(nombre);
        this.apellido.set(apellido);
        this.apodo.set(apodo);
        this.fecha_nacimiento.set(fecha_nacimiento);
        this.estilo.set(estilo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public SimpleStringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getApodo() {
        return apodo.get();
    }

    public SimpleStringProperty apodoProperty() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo.set(apodo);
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento.get();
    }

    public SimpleStringProperty fecha_nacimientoProperty() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento.set(fecha_nacimiento);
    }

    public String getEstilo() {
        return estilo.get();
    }

    public SimpleStringProperty estiloProperty() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo.set(estilo);
    }
}