package Seftic.UI;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TableStockController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Producto, String> nombreId;

    @FXML
    private TableView<Producto> tableStockId;


    @FXML
    private TableColumn<Producto, String> descId;

    @FXML
    private TableColumn<Producto, String> comentarioId;

    @FXML
    private TableColumn<Producto, Integer> cantidadId;

    @FXML
    private TableColumn<Producto, String> tipoId;

    @FXML
    private ComboBox<String> comboId;

    @FXML
    private TextField buscarField;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    //Context Menu
    private ContextMenu cm = new ContextMenu();
    private MenuItem m1 = new MenuItem("Borrar");
    private MenuItem m2 = new MenuItem("Modificar");

    @FXML
    void anadirClick(ActionEvent event) {
        app.ensenarAnadirStock();
    }

    @FXML
    void buscarClick(ActionEvent event) throws SQLException {
        if(buscarField.getText().equals("")){
            if(comboId.getValue().equals("Ambas")){
                List<Producto> lista = rk.getAllProductos();
                cargarTabla(lista);
            }
            else{
                List<Producto> lista = rk.getProductoPorStock(comboId.getValue());
                cargarTabla(lista);
            }

        }
        else {
            List<Producto> listaPorSerial = rk.getProductoPorNombre(buscarField.getText());
            cargarTabla(listaPorSerial);
        }
    }

    @FXML
    void volverClick(ActionEvent event) {
        app.ensenarTabla();
    }

    @FXML
    void initialize() throws SQLException {
        comboId.getItems().addAll("Sí", "No", "Ambas");
        comboId.setValue("");
        List<Producto> lista =  rk.getAllProductos();
        nombreId.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descId.setCellValueFactory(new PropertyValueFactory<>("desc"));
        cantidadId.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tipoId.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        m1.setOnAction(col ->{ //Borrar
            if(tableStockId.getSelectionModel().getSelectedItem() != null){
                String nombre = tableStockId.getSelectionModel().getSelectedItem().getNombre();
                rk.borrarProducto(nombre);
                try {
                    cargarTabla(rk.getAllProductos());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        m2.setOnAction(col -> {
            if(tableStockId.getSelectionModel().getSelectedItem() != null){
                String nombre = tableStockId.getSelectionModel().getSelectedItem().getNombre();
                int cant = tableStockId.getSelectionModel().getSelectedItem().getCantidad();
                String tipo = tableStockId.getSelectionModel().getSelectedItem().getTipo();
                String desc = tableStockId.getSelectionModel().getSelectedItem().getDesc();
                app.modificarProducto(new Producto(nombre, cant, tipo,desc));

                try {
                    cargarTabla(rk.getAllProductos());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        cm.getItems().addAll(m1,m2);
        tableStockId.setContextMenu(cm);

        cargarTabla(lista);


    }

    private void cargarTabla(List<Producto> lista) {
        tableStockId.getItems().clear();
        ObservableList<Producto> listaObs = FXCollections.observableArrayList();
        listaObs.addAll(lista);
        tableStockId.setItems(listaObs);
    }

    public void setMainApp(App app) {
        this.app = app;
    }

    public void actualizarTabla() throws SQLException {
        cargarTabla(rk.getAllProductos());
    }
}
