package edu.rico.javafx.login;

import edu.rico.javafx.login.DAO.Singleton;
import edu.rico.javafx.login.EntityModels.ModelHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class FXMLManager
{
    private static Stage stage;

    public static void loadScene(String filename)
    {
        loadScene(filename, stage);
    }

    public static void loadScene(String filename, Stage stage)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMLManager.class.getResource(filename));
        try
        {
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(FXMLManager.class.getResource("/stylesheets/poker-film-theme.css").toExternalForm());
            //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FXMLManager.stage = stage;
    }

    public static void gotoLogin()
    {
        Singleton.onLogout();
        ModelHandler.onLogout();
        loadScene("login-view.fxml", stage);
    }
}
