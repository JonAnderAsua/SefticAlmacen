package Seftic.UI;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.security.auth.callback.Callback;

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
        app.enseñarAñadirStock();
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
        List<Producto> lista =  rk.getAllProductos();
        serialId.setCellValueFactory(new PropertyValueFactory<>("serial"));
        descId.setCellValueFactory(new PropertyValueFactory<>("desc"));
        comentarioId.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        cantidadId.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tipoId.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        //Callback<TableColumn<Producto,String>, TableCell<Producto,String>> defaultTextFieldCellFactory = TextFieldTableCell.<Producto>forTableColumn();
        tableStockId.getItems().clear();
        ObservableList<Producto> listaObs = FXCollections.observableArrayList();
        listaObs.addAll(lista);
        tableStockId.setItems(listaObs);
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}
