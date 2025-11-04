package edu.rico.javafx.login.Mappers;

import edu.rico.javafx.login.DAO.Jugador;
import edu.rico.javafx.login.EntityModels.JugadorModel;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-04T22:41:48+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
*/
public class JugadorMapperImpl implements JugadorMapper {

    @Override
    public JugadorModel JugadorDAOToJugadorModel(Jugador jugador) {
        if ( jugador == null ) {
            return null;
        }

        int id = 0;
        String nombre = null;
        String apellido = null;
        String apodo = null;
        String estilo = null;

        id = jugador.getId();
        nombre = jugador.getNombre();
        apellido = jugador.getApellido();
        apodo = jugador.getApodo();
        estilo = jugador.getEstilo();

        String fecha_nacimiento = null;

        JugadorModel jugadorModel = new JugadorModel( id, nombre, apellido, apodo, fecha_nacimiento, estilo );

        return jugadorModel;
    }

    @Override
    public Jugador JugadorModelToJugadorDAO(JugadorModel jugador_model) {
        if ( jugador_model == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setEstilo( jugador_model.getEstilo() );
        jugador.setApodo( jugador_model.getApodo() );
        jugador.setApellido( jugador_model.getApellido() );
        jugador.setNombre( jugador_model.getNombre() );

        return jugador;
    }

    @Override
    public void updateFromModel(JugadorModel jugadorModel, Jugador jugador) {
        if ( jugadorModel == null ) {
            return;
        }

        jugador.setEstilo( jugadorModel.getEstilo() );
        jugador.setApodo( jugadorModel.getApodo() );
        jugador.setApellido( jugadorModel.getApellido() );
        jugador.setNombre( jugadorModel.getNombre() );
    }
}
