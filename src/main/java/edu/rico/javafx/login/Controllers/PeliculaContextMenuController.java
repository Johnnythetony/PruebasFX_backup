package edu.rico.javafx.login.Controllers;

import edu.rico.javafx.login.DAO.Pelicula;
import edu.rico.javafx.login.EntityModels.ModelHandler;
import edu.rico.javafx.login.EntityModels.PeliculaModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PeliculaContextMenuController implements Initializable
{
    @FXML
    private ImageView portadaIV;

    @FXML
    private Label tituloL;

    @FXML
    private Label descripcionL;

    @FXML
    private Label generoL;

    @FXML
    private Label duracionL;

    @FXML
    private ListView<Hyperlink> streamsitesLV;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> pelicula_data = retrieveFromAPI();
    }

    private ArrayList<String> retrieveFromAPI()
    {
        ArrayList<String> datos_pelicula = new ArrayList();

        try
        {
        String f_title = "The_Matrix";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb.iamidiotareyoutoo.com/search?q="+f_title))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Object o = JSONValue.parse(response.body());
        JSONObject jsonObject = (JSONObject) o;
        JSONArray jsonArray = (JSONArray) jsonObject.get("description");
        PeliculaModel pelicula = ModelHandler.getSelectedFilm();
        //TODO Almacenar informacion recogida en un arraylist y colocar los datos en sus nodos correspondientes (utilizando JSON-simple)
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return datos_pelicula;
    }
}
