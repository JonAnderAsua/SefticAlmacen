package Seftic.UI;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Registro;
import Seftic.model.Trabajador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TransferQueue;

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
    void actualizarClick(ActionEvent event) throws SQLException {
        cargarTabla(deStringATrabajador(rk.getTrabajadores()));
    }

    @FXML
    void añadirClick(ActionEvent event) {

    }

    @FXML
    void volverClick(ActionEvent event) {
        app.enseñarTabla();
    }

    public void iniciar() throws SQLException {
        List<String> listaString = rk.getTrabajadores();
        List<Trabajador> lista = deStringATrabajador(listaString);
        trabajadorId.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        registroId.setCellValueFactory(new PropertyValueFactory<>("ultimoRegistro"));

        m1.setOnAction(col ->{ //Borrar
            String nombre = tableId.getSelectionModel().getSelectedItem().getNombre();
            rk.borrarTrabajador(nombre);
        });

        m2.setOnAction(col -> { //Modificar
            String nombre = tableId.getSelectionModel().getSelectedItem().getNombre();

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

}
