package Seftic.UI;

import java.net.URL;
import java.util.ResourceBundle;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ModificarProducto {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField serialLabel;

    @FXML
    private TextField descLabel;

    @FXML
    private TextField comentarioLabel;

    @FXML
    private TextField cantLabel;

    @FXML
    private Button modificarId;

    @FXML
    private Button limpiarId;

    @FXML
    private Button volverId;

    @FXML
    private ComboBox<String> comboboxTipo;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    @FXML
    void limpiarClick(ActionEvent event) {
        serialLabel.setText("");
        descLabel.setText("");
        comentarioLabel.setText("");
        cantLabel.setText("");
    }

    @FXML
    void modificarClick(ActionEvent event) {
        rk.borrarProducto(serialLabel.getText());
        rk.añadirProducto(serialLabel.getText(),descLabel.getText(),comentarioLabel.getText(),Integer.parseInt(cantLabel.getText()) ,comboboxTipo.getValue());
    }

    @FXML
    void volverClick(ActionEvent event) {
        app.mostrarInventario();
    }
/*
    @FXML
    void initialize() {
       comboboxTipo.getItems().addAll("PC","Video","Red","Otros");
       serialLabel.setText(p.getSerial());
       descLabel.setText(p.getDesc());
       comentarioLabel.setText(p.getComentario());
       cantLabel.setText(String.valueOf(p.getCantidad()));
       comboboxTipo.setValue(p.getTipo());
    }

 */

    public void hasieratu(Producto p){
        comboboxTipo.getItems().addAll("PC","Video","Red","Otros");
        serialLabel.setText(p.getSerial());
        descLabel.setText(p.getDesc());
        comentarioLabel.setText(p.getComentario());
        cantLabel.setText(String.valueOf(p.getCantidad()));
        comboboxTipo.setValue(p.getTipo());
    }

    public void setMainApp(App app) {
        this.app = app;
    }

}
