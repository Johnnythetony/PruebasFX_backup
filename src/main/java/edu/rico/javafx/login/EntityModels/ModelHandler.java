package edu.rico.javafx.login.EntityModels;

import edu.rico.javafx.login.DAO.Singleton;
import edu.rico.javafx.login.Mappers.PeliculaMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ModelHandler
{
    private static ObservableList<JugadorModel> jugadores;
    private static ObservableList<PeliculaModel> peliculas_usuario;
    private static ObservableList<PeliculaModel> peliculas;

    private static List<JugadorModel> importJugadores()
    {
        ArrayList<JugadorModel> jugador_model_list = new ArrayList<>();

        for(int i = 0; i < Singleton.getJugadores().size(); i++)
        {
            jugador_model_list.add(new JugadorModel(
                    Singleton.getJugadores().get(i).getId(),
                    Singleton.getJugadores().get(i).getNombre(),
                    Singleton.getJugadores().get(i).getApellido(),
                    Singleton.getJugadores().get(i).getApodo(),
                    String.valueOf(Singleton.getJugadores().get(i).getFechaNac()),
                    Singleton.getJugadores().get(i).getEstilo()));
        }

        return jugador_model_list;
    }

    private static List<PeliculaModel> importPeliculasUsuario()
    {
        ArrayList<PeliculaModel> pelicula_model_list = new ArrayList<>();

        for(int i = 0; i < Singleton.getPeliculasUsuario().size(); i++)
        {
            pelicula_model_list.add(PeliculaMapper.INSTANCE.PeliculaDAOToPeliculaModel(Singleton.getPeliculasUsuario().get(i)));
        }

        return pelicula_model_list;
    }

    private static List<PeliculaModel> importPeliculas()
    {
        ArrayList<PeliculaModel> pelicula_model_list = new ArrayList<>();

        for(int i = 0; i < Singleton.getPeliculas().size(); i++)
        {
            pelicula_model_list.add(PeliculaMapper.INSTANCE.PeliculaDAOToPeliculaModel(Singleton.getPeliculas().get(i)));
        }

        return pelicula_model_list;
    }

    public static ObservableList<JugadorModel> getJugadores()
    {
        if(jugadores == null)
        {
            jugadores = FXCollections.observableArrayList(importJugadores());
        }
        return jugadores;
    }

    public static ObservableList<PeliculaModel> getPeliculasUsuario()
    {
        peliculas_usuario = FXCollections.observableArrayList(importPeliculasUsuario());
        return peliculas_usuario;
    }

    public static ObservableList<PeliculaModel> getPeliculas()
    {
        peliculas = FXCollections.observableArrayList(importPeliculas());
        return peliculas;
    }

    public static void updateJugadoresDAO()
    {
        Singleton.updateJugadoresDAO(jugadores);
    }

    public static void updatePeliculasDAO()
    {
        Singleton.updatePeliculaList(peliculas_usuario);
    }

    public static void onLogout()
    {
        jugadores = null;
        peliculas_usuario = null;
    }

    public static void removePeliculaDAO(PeliculaModel peliculaModel)
    {
        Singleton.removePelicula(peliculaModel);
        ModelHandler.getPeliculas().remove(peliculaModel);
    }
}
