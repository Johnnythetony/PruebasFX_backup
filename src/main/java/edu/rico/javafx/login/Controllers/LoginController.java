package edu.rico.javafx.login.Controllers;

import edu.rico.javafx.login.DAO.Singleton;
import edu.rico.javafx.login.DAO.Usuario;
import edu.rico.javafx.login.FXMLManager;
import edu.rico.javafx.login.LoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    private TextField nombreT;

    @FXML
    private Label nombreL;

    @FXML
    private TextField passwordT;

    @FXML
    private Label passwordL;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button _loginButton;

    @FXML
    private Button _registerButton;

    @FXML
    private Label loginerror;

    @FXML
    private ImageView menuI;

    @FXML
    private StackPane parentN;

    @FXML
    private Slider volumeS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuI.setImage(new Image(String.valueOf(getClass().getResource("/images/Menuimage.png").toExternalForm())));
        menuI.fitHeightProperty().bind(parentN.heightProperty());
        menuI.fitWidthProperty().bind(parentN.widthProperty());

        setMusicVolume();
    }

    private void setMusicVolume()
    {
        volumeS.adjustValue(LoginApplication.getMusicPlayer().getVolume()*100);
        LoginApplication.getMusicPlayer().volumeProperty().bind(volumeS.valueProperty().divide(100));
    }

    public void manageLogin(ActionEvent actionEvent) {

        int verificado = Singleton.verifyUser(nombreT.getText(), passwordT.getText());

        if(verificado == 0)
        {
            Singleton.setUsuario(nombreT.getText(), passwordT.getText());
            FXMLManager.loadScene("usermenu-view.fxml");
        }
        else
        {
            loginerror.setText("Usuario/contraseña incorrectos");
            passwordT.setText("");
            nombreT.setText("");
        }
    }

    public void gotoRegister(ActionEvent actionEvent)
    {
        FXMLManager.loadScene("register-view.fxml");
    }

    public void muteMusic(ActionEvent actionEvent)
    {
        LoginApplication.musicMute();
    }
}
