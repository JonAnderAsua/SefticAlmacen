package Seftic.UI;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Trabajador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrabajadoresContr {

    @FXML
    private TableView<Trabajador> tableId;

    @FXML
    private TableColumn<Trabajador, String> trabajadorId;

    @FXML
    private TableColumn<Trabajador, String> registroId;

    private RecursosKud rk = RecursosKud.getInstance();
    private ContextMenu cm = new ContextMenu();
    private MenuItem m1 = new MenuItem("Borrar");
    private MenuItem m2 = new MenuItem("Modificar");
    private App app;

    @FXML
    void anadirClick(ActionEvent event) {
        app.cargarNuevoTrab("");
    }

    @FXML
    void volverClick(ActionEvent event) {
        app.ensenarTabla();
    }

    public void iniciar() throws SQLException {
        List<String> listaString = rk.getTrabajadores();
        List<Trabajador> lista = deStringATrabajador(listaString);
        trabajadorId.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        registroId.setCellValueFactory(new PropertyValueFactory<>("ultimoRegistro"));
        cm.getItems().clear();

        m1.setOnAction(col ->{ //Borrar
            if(tableId.getSelectionModel().getSelectedItem() != null){
                String nombre = tableId.getSelectionModel().getSelectedItem().getNombre();
                rk.borrarTrabajador(nombre);
                try {
                    cargarTabla(deStringATrabajador(rk.getTrabajadores()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });

        m2.setOnAction(col -> { //Modificar
            if(tableId.getSelectionModel().getSelectedItem() != null){
                String nombre = tableId.getSelectionModel().getSelectedItem().getNombre();
                rk.borrarTrabajador(nombre);
                app.cargarNuevoTrab(nombre);
            }
        });
        cm.getItems().addAll(m1,m2);
        tableId.setContextMenu(cm);

        cargarTabla(lista);

    }

    private List<Trabajador> deStringATrabajador(List<String> listaString) {
        List<Trabajador> lista = new ArrayList<>();
        Iterator itr = listaString.iterator();
        while(itr.hasNext()){
            String s = (String) itr.next();
            lista.add(new Trabajador(s));
        }
        return lista;
    }

    private void cargarTabla(List<Trabajador> recursos) {
        tableId.getItems().clear();
        ObservableList<Trabajador> listaObs = FXCollections.observableArrayList();
        listaObs.addAll(recursos);
        tableId.setItems(listaObs);
    }

    public void setMainApp(App app){
        this.app = app;
    }

    public void actualizarTabla() throws SQLException {
        cargarTabla(deStringATrabajador(rk.getTrabajadores()));
    }
}
