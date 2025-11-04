package edu.rico.javafx.login.Mappers;

import edu.rico.javafx.login.DAO.Pelicula;
import edu.rico.javafx.login.EntityModels.PeliculaModel;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-04T22:41:48+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
*/
public class PeliculaMapperImpl implements PeliculaMapper {

    @Override
    public PeliculaModel PeliculaDAOToPeliculaModel(Pelicula pelicula) {
        if ( pelicula == null ) {
            return null;
        }

        int id = 0;
        String titulo = null;
        String genero = null;
        String director = null;
        String anho = null;
        String portada = null;

        id = pelicula.getId();
        titulo = pelicula.getTitulo();
        genero = pelicula.getGenero();
        director = pelicula.getDirector();
        anho = pelicula.getAnho();
        portada = pelicula.getPortada();

        PeliculaModel peliculaModel = new PeliculaModel( id, titulo, genero, director, anho, portada );

        return peliculaModel;
    }

    @Override
    public Pelicula PeliculaModelToPeliculaDAO(PeliculaModel pelicula) {
        if ( pelicula == null ) {
            return null;
        }

        Pelicula pelicula1 = new Pelicula();

        pelicula1.setTitulo( pelicula.getTitulo() );
        pelicula1.setAnho( pelicula.getAnho() );
        pelicula1.setGenero( pelicula.getGenero() );
        pelicula1.setDirector( pelicula.getDirector() );
        pelicula1.setPortada( pelicula.getPortada() );

        return pelicula1;
    }

    @Override
    public void updateFromModel(PeliculaModel peliculaModel, Pelicula pelicula) {
        if ( peliculaModel == null ) {
            return;
        }

        pelicula.setTitulo( peliculaModel.getTitulo() );
        pelicula.setAnho( peliculaModel.getAnho() );
        pelicula.setGenero( peliculaModel.getGenero() );
        pelicula.setDirector( peliculaModel.getDirector() );
        pelicula.setPortada( peliculaModel.getPortada() );
    }
}
