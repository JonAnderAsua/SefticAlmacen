package UI;

import model.Registro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Registro> tableId;

    @FXML
    private TableColumn<Registro, String> serialId;

    @FXML
    private TableColumn<Registro, String> DescipcionId;

    @FXML
    private TableColumn<Registro, String> ComentarioId;

    @FXML
    private TableColumn<Registro, String> tipoId;

    @FXML
    private TableColumn<Registro, Date> fechaEntradaId;

    @FXML
    private TableColumn<Registro, Date> fechaSalidaId;

    @FXML
    private TableColumn<Registro, String> clienteId;

    @FXML
    private TableColumn<Registro, String> trabajadorId;

    @FXML
    private Button añadirId;

    @FXML
    private Button buscarId;

    @FXML
    private Button inventarioId;

    @FXML
    void añadirClick(ActionEvent event) {

    }

    @FXML
    void buscarClick(ActionEvent event) {

    }

    @FXML
    void inventarioClick(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
