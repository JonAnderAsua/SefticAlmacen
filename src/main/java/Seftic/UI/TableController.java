package Seftic.UI;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Producto;
import Seftic.model.Registro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TableController {

    @FXML
    private TableColumn<Registro,String> nombreId;

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
    private TableColumn<Registro, String> fechaId;

    @FXML
    private TableColumn<Registro, String> inOutId;

    @FXML
    private TableColumn<Registro, String> clienteId;

    @FXML
    private TableColumn<Registro, String> trabajadorId;

    @FXML
    private TableColumn<Registro, Integer> cantidadId;

    @FXML
    private ComboBox<String> comboBuscarId;

    @FXML
    private TextField buscarTextId;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    private ContextMenu cm = new ContextMenu();
    private MenuItem m1 = new MenuItem("Borrar");
    private MenuItem m2 = new MenuItem("Modificar");

    @FXML
    void añadirClick(ActionEvent event) throws SQLException, ParseException {
        app.enseñarAñadir();
    }

    @FXML
    private Button actualizarId;

    @FXML
    void ActualizarClick(ActionEvent event) throws SQLException, ParseException {
        cargarTabla(rk.getRecursos());
    }

    private void cargarTabla(List<Registro> recursos) {
        tableId.getItems().clear();
        ObservableList<Registro> listaObs = FXCollections.observableArrayList();
        listaObs.addAll(recursos);
        tableId.setItems(listaObs);
    }


    @FXML
    void buscarClick(ActionEvent event) throws SQLException, ParseException {
        List<Registro> lista = new ArrayList<>();
        switch (comboBuscarId.getValue()){
            case "Serial":
                lista = rk.buscarPorNombre(buscarTextId.getText());
                break;
            case "Trabajador":
                lista= rk.buscarPorTrabajador(buscarTextId.getText());
                break;
            case "Cliente":
                lista = rk.buscarPorCliente(buscarTextId.getText());
                break;
            default:
                lista = rk.getRecursos();
        }
        cargarTabla(lista);
    }

    @FXML
    void inventarioClick(ActionEvent event) {
        app.mostrarInventario();
    }

    @FXML
    void initialize() throws SQLException, ParseException {
        comboBuscarId.getItems().addAll("Serial", "Trabajador", "Cliente");
        List<Registro> listaTotal = rk.getRecursos();

        
        //Rellenar las columnas con sus respectivos valores
        nombreId.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        serialId.setCellValueFactory(new PropertyValueFactory<>("serial"));
        DescipcionId.setCellValueFactory(new PropertyValueFactory<>("desc"));
        ComentarioId.setCellValueFactory(new PropertyValueFactory<>("coment"));
        tipoId.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        fechaId.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        inOutId.setCellValueFactory(new PropertyValueFactory<>("entrada"));
        clienteId.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        trabajadorId.setCellValueFactory(new PropertyValueFactory<>("trab"));
        cantidadId.setCellValueFactory(new PropertyValueFactory<>("cantMod"));

        m1.setOnAction(col ->{ //Borrar
            String nombre = tableId.getSelectionModel().getSelectedItem().getNombreProducto();
            String serial = tableId.getSelectionModel().getSelectedItem().getSerial();
            String trabajador = tableId.getSelectionModel().getSelectedItem().getTrab();
            String fEntrada = tableId.getSelectionModel().getSelectedItem().getFecha();
            String entrada = tableId.getSelectionModel().getSelectedItem().getEntrada();
            int cantidad = tableId.getSelectionModel().getSelectedItem().getCantMod();
            try {
                rk.borrarRegistro(nombre,serial,trabajador,entrada,fEntrada,cantidad);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        m2.setOnAction(col -> { //Modificar
            String nombreProducto = tableId.getSelectionModel().getSelectedItem().getNombreProducto();
            String serial = tableId.getSelectionModel().getSelectedItem().getSerial();
            String desc = tableId.getSelectionModel().getSelectedItem().getDesc();
            String coment = tableId.getSelectionModel().getSelectedItem().getComent();
            String tipo = tableId.getSelectionModel().getSelectedItem().getTipo();
            String fEntrada = tableId.getSelectionModel().getSelectedItem().getFecha();
            String entrada = tableId.getSelectionModel().getSelectedItem().getEntrada();
            String cliente = tableId.getSelectionModel().getSelectedItem().getCliente();
            String trab = tableId.getSelectionModel().getSelectedItem().getTrab();
            int cantMod = tableId.getSelectionModel().getSelectedItem().getCantMod();
            try {
                rk.borrarProducto(serial); //la trampita
                app.modificarRegistro(new Registro(nombreProducto,serial,desc,coment,tipo,fEntrada,entrada,cliente,trab,cantMod));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
        cm.getItems().addAll(m1,m2);
        tableId.setContextMenu(cm);

        cargarTabla(listaTotal);

    }

    @FXML
    void trabajadorClick(ActionEvent event) throws SQLException {
        app.cargarTablaTrab();
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}
