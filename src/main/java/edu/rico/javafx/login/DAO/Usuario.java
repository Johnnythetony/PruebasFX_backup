package edu.rico.javafx.login.DAO;

import jakarta.persistence.*;
import javafx.css.converter.BooleanConverter;
import org.hibernate.annotations.Type;
import org.hibernate.boot.model.source.internal.hbm.HibernateTypeSourceImpl;
import org.hibernate.boot.model.source.spi.HibernateTypeSource;
import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.type.descriptor.java.BooleanJavaType;

import java.sql.Date;

@Entity
@Table(name="usuario")
public class Usuario
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private Date birth_date;

    @Column(name = "password")
    private String password;

    //TODO Preguntar a Rico que cojones pasa aqui y por que ya no me reconoce la contraseña
    @Column(name = "is_admin", columnDefinition = "TINYINT(1)")
    @Convert(converter = NumericBooleanConverter.class)
    private boolean is_admin;

    public Usuario(){}

    public Usuario(String username, String name, String surname, String email, Date birth_date, String password)
    {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birth_date = birth_date;
        this.password = password;
        this.is_admin = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_admin() {
        return is_admin;
    }
}
