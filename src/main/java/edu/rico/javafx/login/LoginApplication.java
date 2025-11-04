package edu.rico.javafx.login;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LoginApplication extends Application {

    private static MediaPlayer prueba_musica;

    @Override
    public void start(Stage stage) {
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Poker Stars");
        stage.getIcons().add(new Image(String.valueOf(LoginApplication.class.getResource("/images/icon.png").toExternalForm())));
        playMusic();
        FXMLManager.setStage(stage);
        FXMLManager.loadScene("login-view.fxml");
    }

    public static void main(String[] args)
    {
        launch();
    }

    private static void playMusic()
    {
            Media musica_menu = new Media(FXMLManager.class.getResource("/audio/menu-music.wav").toExternalForm());
            prueba_musica = new MediaPlayer(musica_menu);
            prueba_musica.setCycleCount(MediaPlayer.INDEFINITE);
            prueba_musica.setVolume(0);
            prueba_musica.play();
    }

    public static MediaPlayer getMusicPlayer()
    {
        return prueba_musica;
    }

    public static void musicMute()
    {
        if(prueba_musica.isMute())
        {
            prueba_musica.setMute(false);
        }
        else
        {
            prueba_musica.setMute(true);
        }
    }
}