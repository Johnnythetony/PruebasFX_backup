package edu.rico.javafx.login.Mappers;

import edu.rico.javafx.login.DAO.Jugador;
import edu.rico.javafx.login.EntityModels.JugadorModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JugadorMapper
{
    JugadorMapper INSTANCE = Mappers.getMapper( JugadorMapper.class );

    JugadorModel JugadorDAOToJugadorModel(Jugador jugador );
    Jugador JugadorModelToJugadorDAO( JugadorModel jugador_model );

    void updateFromModel( JugadorModel jugadorModel, @MappingTarget Jugador jugador );
}
