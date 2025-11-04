package edu.rico.javafx.login.Controllers;

import edu.rico.javafx.login.DAO.Singleton;
import edu.rico.javafx.login.FXMLManager;
import edu.rico.javafx.login.LoginApplication;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable
{
    @FXML
    private SplitPane containerPane;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Slider volumeS;

    @FXML
    private Menu adminMenu;

    private String current_fxml;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setMusicVolume();

        if(Singleton.getUsuario().isIs_admin())
        {
            adminMenu.setDisable(false);
        }
    }

    private void setMusicVolume()
    {
        volumeS.adjustValue(LoginApplication.getMusicPlayer().getVolume()*100);
        LoginApplication.getMusicPlayer().volumeProperty().bind(volumeS.valueProperty().divide(100));
    }

    public void loadSettingsView(ActionEvent actionEvent)
    {
        loadView("account-view.fxml");
    }

    public void loadJugadoresView(ActionEvent actionEvent)
    {
        loadView("jugadores-view.fxml");
    }

    public void loadPeliculasView(ActionEvent actionEvent){ loadView("peliculas-view.fxml"); }

    public void gotoLogin(ActionEvent actionEvent)
    {
        FXMLManager.gotoLogin();
    }

    private void loadView(String view)
    {
        setProgress(() ->{
            try
            {
                Parent contenido = FXMLLoader.load(LoginApplication.class.getResource(view));
                FXMLManager.getStage().resizableProperty().setValue(true);
                containerPane.getItems().clear();
                containerPane.getItems().add(contenido);
                FXMLManager.getStage().setMinHeight(660);
                FXMLManager.getStage().setMinWidth(590);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        current_fxml = view;
    }

    private void setProgress(Runnable operaciones)
    {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    operaciones.run();
                    progressBar.progressProperty().unbind();
                    progressBar.setProgress(0);
                });
                return null;
            }
        };

        progressBar.progressProperty().bind(tarea.progressProperty());
        new Thread(tarea).start();
    }

    public void reloadView(ActionEvent actionEvent)
    {
        if(current_fxml != null)
        {
            loadView(current_fxml);
        }
    }
}
