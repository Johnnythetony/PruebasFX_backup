package edu.rico.javafx.login.Controllers;

import edu.rico.javafx.login.EntityModels.ModelHandler;
import edu.rico.javafx.login.EntityModels.PeliculaModel;
import edu.rico.javafx.login.FXMLManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PeliculasController implements Initializable
{
    private static String texto_busqueda;
    private Timeline timeline;
    private final static int DEBOUNCE_DELAY_MS = 400;

    @FXML
    private TableView<PeliculaModel> tablaPeliculas;

    @FXML
    private TableColumn<PeliculaModel,String> colPortada;

    @FXML
    private TableColumn<PeliculaModel, String> colTitulo;

    @FXML
    private TableColumn<PeliculaModel, String> colGenero;

    @FXML
    private TableColumn<PeliculaModel, String> colDirector;

    @FXML
    private TableColumn<PeliculaModel, String> colAnho;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextField tfGenero;

    @FXML
    private TextField tfDirector;

    @FXML
    private ComboBox<Integer> cbAnhoEditar;

    @FXML
    private Label feedbackL;

    @FXML
    private TextField tfSearchBar;

    @FXML
    private TextField tfSearchBarFilter;

    @FXML
    private Accordion accordionPeliculas;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tablaPeliculas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tfTitulo.setText(newValue.getTitulo());
            tfGenero.setText(newValue.getGenero());
            tfDirector.setText(newValue.getDirector());
            cbAnhoEditar.getSelectionModel().select(Integer.parseInt(newValue.getAnho()));
        });

        //Rellenar ComboBoxes con años 1800-actualidad
        ObservableList<Integer> anhos = FXCollections.observableArrayList();
        for(int i = LocalDate.now().getYear();i >= 1800;i--)
        {
            anhos.add(i);
        }
        cbAnhoEditar.setItems(anhos);

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colAnho.setCellValueFactory(new PropertyValueFactory<>("anho"));
        colPortada.setCellValueFactory(new PropertyValueFactory<>("portada"));

        tablaPeliculas.setItems(ModelHandler.getPeliculasUsuario());

        //Adaptar tamaño de la tabla al tamaño de la lista
        tablaPeliculas.setFixedCellSize(100);
        tablaPeliculas.prefHeightProperty().bind(Bindings.size(ModelHandler.getPeliculasUsuario()).multiply(tablaPeliculas.getFixedCellSize()).add(30));
        tablaPeliculas.setMinHeight(0);
        tablaPeliculas.setMaxHeight(Control.USE_PREF_SIZE);
        tablaPeliculas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Cargar las portadas a partir de la URL proporcionada
        //TODO centrar dinamicamente las imagenes en sus celdas
        colPortada.setCellFactory(column -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String url, boolean empty) {
                super.updateItem(url, empty);

                if (empty || url == null || url.isEmpty()) {
                    try {
                        Image image = new Image(String.valueOf(FXMLManager.class.getResource("/images/imgnotfound.png")), 120, 120, true, true);
                        imageView.setImage(image);
                        setGraphic(imageView);
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                } else {
                    try {
                        Image image = new Image(url, 120, 120, true, true);
                        imageView.setImage(image);
                        setGraphic(imageView);
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });

        //Inicializar lista de peliculas a la izquierda
        filterPeliculas("");

        //Configuramos la timeline
        timeline = new Timeline(new KeyFrame(Duration.millis(DEBOUNCE_DELAY_MS), event -> filterPeliculas(texto_busqueda)));
        timeline.setCycleCount(1);

        //Añadimos listeners a los campos de texto que usaremos para filtrar datos de la tabla y el panel izquierdo
        tfSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(DEBOUNCE_DELAY_MS), event -> filterPeliculas(texto_busqueda)));
            timeline.stop();
            setTexto_busqueda(newValue);
            timeline.playFromStart();
        });
        tfSearchBarFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(DEBOUNCE_DELAY_MS), event -> {
                        ObservableList<PeliculaModel> peliculas_usuario = filterPeliculasUsuario(texto_busqueda);

                        tablaPeliculas.setItems(peliculas_usuario);
                        if (!peliculas_usuario.isEmpty()) {
                            tablaPeliculas.prefHeightProperty().bind(Bindings.size(peliculas_usuario).multiply(tablaPeliculas.getFixedCellSize()).add(30));
                        }
                    }));
            timeline.stop();
            setTexto_busqueda(newValue);
            timeline.playFromStart();
        });
    }

    private void setTexto_busqueda(String busqueda)
    {
        texto_busqueda = busqueda;
    }

    public void filterPeliculas(String filter)
    {
        List<PeliculaModel> peliculasFiltradas = ModelHandler.getPeliculas().stream()
                .filter(p -> p.getTitulo().toLowerCase().contains(filter.toLowerCase()))
                .toList();

        Map<String, List<PeliculaModel>> peliculasPorGenero = peliculasFiltradas.stream()
                .collect(Collectors.groupingBy(PeliculaModel::getGenero));

        accordionPeliculas.getPanes().clear();

        peliculasPorGenero.keySet().stream().sorted().forEach(genero -> {

            List<PeliculaModel> listaDelGenero = peliculasPorGenero.get(genero);

            if (!listaDelGenero.isEmpty()) {

                ListView<PeliculaModel> listaPeliculas = crearListView(listaDelGenero);

                TitledPane tPane = new TitledPane();
                tPane.setText(genero + " (" + listaDelGenero.size() + ")");
                tPane.setContent(new VBox(listaPeliculas));

                accordionPeliculas.getPanes().add(tPane);
            }
        });

        if (!accordionPeliculas.getPanes().isEmpty()) {
            accordionPeliculas.setExpandedPane(accordionPeliculas.getPanes().getFirst());
        }
    }

    private ListView<PeliculaModel> crearListView(List<PeliculaModel> peliculas) {
    ListView<PeliculaModel> listView = new ListView<>();
    ObservableList<PeliculaModel> items = FXCollections.observableArrayList(peliculas);
    listView.setItems(items);
    listView.setMaxWidth(Double.MAX_VALUE);
    listView.setMaxHeight(Double.MAX_VALUE);
    listView.setPrefHeight(Region.USE_COMPUTED_SIZE);

    listView.setCellFactory(lv -> new ListCell<PeliculaModel>() {
        @Override
        protected void updateItem(PeliculaModel pelicula, boolean empty) {
            super.updateItem(pelicula, empty);
            if (empty || pelicula == null) {
                setText(null);
            } else {
                setText(pelicula.getTitulo());
            }
        }
    });
    return listView;
}

    public ObservableList<PeliculaModel> filterPeliculasUsuario(String filter)
    {
        ObservableList<PeliculaModel> peliculas = FXCollections.observableArrayList();

        for (PeliculaModel pelicula : ModelHandler.getPeliculasUsuario())
        {
            if(pelicula.getTitulo().toLowerCase().contains(filter.toLowerCase()) ||
                    pelicula.getGenero().toLowerCase().contains(filter.toLowerCase()) ||
                    pelicula.getDirector().toLowerCase().contains(filter.toLowerCase()) ||
                    pelicula.getAnho().toLowerCase().contains(filter.toLowerCase()))
            {
                peliculas.add(pelicula);
            }
        }

        return peliculas;
    }

    public void handleAnadir(ActionEvent actionEvent)
    {
        if(tfTitulo.getText().isEmpty() || tfGenero.getText().isEmpty() || tfDirector.getText().isEmpty() || cbAnhoEditar.getSelectionModel().getSelectedItem() == null)
        {
            feedbackL.setText("Faltan campos por rellenar");
        }
        else
        {
            //TODO preguntar si se quiere buscar y rellenar con los datos de la API
            ModelHandler.getPeliculasUsuario().add(new PeliculaModel(0, tfTitulo.getText(),tfGenero.getText(),tfDirector.getText(), String.valueOf(cbAnhoEditar.getSelectionModel().getSelectedItem()), null));
        }
    }

    public void handleModificar(ActionEvent actionEvent)
    {
        if(tfTitulo.getText().isEmpty() || tfGenero.getText().isEmpty() || tfDirector.getText().isEmpty() || cbAnhoEditar.getSelectionModel().getSelectedItem() == null)
        {
            feedbackL.setText("Faltan campos por rellenar");
        }
        else
        {
            tablaPeliculas.getSelectionModel().getSelectedItem().setTitulo(tfTitulo.getText());
            tablaPeliculas.getSelectionModel().getSelectedItem().setGenero(tfGenero.getText());
            tablaPeliculas.getSelectionModel().getSelectedItem().setDirector(tfDirector.getText());
            tablaPeliculas.getSelectionModel().getSelectedItem().setAnho(String.valueOf(cbAnhoEditar.getSelectionModel().getSelectedItem()));
        }
    }

    public void handleEliminar(ActionEvent actionEvent)
    {
        try
        {
            ModelHandler.removePeliculaDAO(tablaPeliculas.getSelectionModel().getSelectedItem());
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void handleLimpiar(ActionEvent actionEvent)
    {
        tfTitulo.setText("");
        tfGenero.setText("");
        tfDirector.setText("");
        cbAnhoEditar.getSelectionModel().clearSelection();
    }

    public void handleSubmit(ActionEvent actionEvent)
    {
        ModelHandler.updatePeliculasDAO();
    }
}
