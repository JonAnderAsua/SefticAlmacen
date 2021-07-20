package Seftic.UI;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Registro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
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
    private TextField buscarTextId;

    @FXML
    private ComboBox<String> comboBuscarId;


    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    @FXML
    void añadirClick(ActionEvent event) {
        app.enseñarAñadir();
    }

    @FXML
    void buscarClick(ActionEvent event) {

    }

    @FXML
    void inventarioClick(ActionEvent event) {
        app.mostrarInventario();
    }

    @FXML
    void initialize() throws SQLException, ParseException {
        comboBuscarId.getItems().addAll("Serial", "Trabajador", "Cliente");
        List<Registro> listaTotal = rk.getRecursos();
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}
