package edu.rico.javafx.login.Controllers;

import edu.rico.javafx.login.EntityModels.JugadorModel;
import edu.rico.javafx.login.EntityModels.ModelHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class JugadoresController implements Initializable
{
    @FXML
    TableView<JugadorModel> tablaJugadores;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tablaJugadores.setItems(ModelHandler.getJugadores());
    }

    public void handleAnadir(ActionEvent actionEvent)
    {
    }

    public void handleModificar(ActionEvent actionEvent)
    {
    }

    public void handleEliminar(ActionEvent actionEvent)
    {
    }

    public void handleLimpiar(ActionEvent actionEvent)
    {
    }

    public void handleSubmit(ActionEvent actionEvent)
    {
        ModelHandler.updateJugadoresDAO();
    }
}
