package UI;

import java.net.URL;
import java.util.ResourceBundle;
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
    private ComboBox<?> tipoId;

    @FXML
    private DatePicker fEntradaId;

    @FXML
    private DatePicker fSalidaId;

    @FXML
    private TextField comentarioId;

    @FXML
    private ComboBox<?> trabajadorId;

    @FXML
    private TextField cantidadId;

    @FXML
    private Button añadirId;

    @FXML
    void añadirClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert serialId != null : "fx:id=\"serialId\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert descripcionId != null : "fx:id=\"descripcionId\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert clienteId != null : "fx:id=\"clienteId\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert tipoId != null : "fx:id=\"tipoId\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert fEntradaId != null : "fx:id=\"fEntradaId\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert fSalidaId != null : "fx:id=\"fSalidaId\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert comentarioId != null : "fx:id=\"comentarioId\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert trabajadorId != null : "fx:id=\"trabajadorId\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert cantidadId != null : "fx:id=\"cantidadId\" was not injected: check your FXML file 'Añadir.fxml'.";
        assert añadirId != null : "fx:id=\"añadirId\" was not injected: check your FXML file 'Añadir.fxml'.";

    }
}
