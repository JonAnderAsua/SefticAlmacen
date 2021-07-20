package Seftic.UI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableStockController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Producto> tableStockId;

    @FXML
    private TableColumn<Producto, String> serialId;

    @FXML
    private TableColumn<Producto, String> descId;

    @FXML
    private TableColumn<?, ?> comentarioId;

    @FXML
    private TableColumn<Producto, Integer> cantidadId;

    @FXML
    private TableColumn<Producto, String> tipoId;

    @FXML
    private Button añadirId;

    @FXML
    private Button buscarId;

    @FXML
    private ComboBox<String> comboId;

    @FXML
    private Button volverId;
    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    @FXML
    void añadirClick(ActionEvent event) {

    }

    @FXML
    void buscarClick(ActionEvent event) {

    }

    @FXML
    void volverClick(ActionEvent event) {
        app.enseñarTabla();
    }

    @FXML
    void initialize() throws SQLException {
        comboId.getItems().addAll("Sí", "No");
        rk.getAllProductos();
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}
