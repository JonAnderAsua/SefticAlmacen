package Seftic.UI;

import java.net.URL;
import java.util.ResourceBundle;

import Seftic.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AñadirController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField serialId;

    @FXML
    private TextField descripcionId;

    @FXML
    private TextField clienteId;

    @FXML
    private ComboBox<String> tipoId;

    @FXML
    private DatePicker fEntradaId;

    @FXML
    private DatePicker fSalidaId;

    @FXML
    private TextField comentarioId;

    @FXML
    private ComboBox<String> trabajadorId;

    @FXML
    private TextField cantidadId;

    @FXML
    private Button añadirId;

    private App app;

    @FXML
    void añadirClick(ActionEvent event) {
        //Añadir a la base de datos
        app.enseñarTabla();
    }

    @FXML
    void initialize() {
        tipoId.getItems().addAll("PC","Video","Red","Otros");
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}
