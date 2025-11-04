package edu.rico.javafx.login.Mappers;

import edu.rico.javafx.login.DAO.Jugador;
import edu.rico.javafx.login.DAO.Pelicula;
import edu.rico.javafx.login.EntityModels.JugadorModel;
import edu.rico.javafx.login.EntityModels.PeliculaModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeliculaMapper
{
    PeliculaMapper INSTANCE = Mappers.getMapper(PeliculaMapper.class);

    PeliculaModel PeliculaDAOToPeliculaModel(Pelicula pelicula);
    Pelicula PeliculaModelToPeliculaDAO(PeliculaModel pelicula);

    void updateFromModel(PeliculaModel peliculaModel, @MappingTarget Pelicula pelicula );
}
