package edu.rico.javafx.login.Controllers;

import edu.rico.javafx.login.DAO.Singleton;
import edu.rico.javafx.login.DAO.Usuario;
import edu.rico.javafx.login.FXMLManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable
{
    @FXML
    public Label usernameL;

    @FXML
    public Label nameL;

    @FXML
    public Label surnameL;

    @FXML
    public Label emailL;

    @FXML
    public Label birth_dateL;

    @FXML
    public Label feedbackL;

    @FXML
    public Label passwordL;

    @FXML
    public TextField usernameT;

    @FXML
    public TextField nameT;

    @FXML
    public TextField surnameT;

    @FXML
    public TextField emailT;

    @FXML
    public TextField birth_dateT;

    @FXML
    public PasswordField passwordT;

    @FXML
    public Button _backButton;

    @FXML
    public Button _submitB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void submitUser(ActionEvent event)
    {
        feedbackL.setText(Singleton.createUser(usernameT.getText(), nameT.getText(), surnameT.getText(), emailT.getText(), birth_dateT.getText(), passwordT.getText()));
    }

    public void back(ActionEvent event)
    {
        Singleton.setUsuario(new Usuario());
        FXMLManager.loadScene("login-view.fxml");
    }
}
