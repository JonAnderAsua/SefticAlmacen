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
    private TextField serialLabel;

    @FXML
    private TextField descLabel;

    @FXML
    private TextField nombreLabel;

    @FXML
    private TextField cantLabel;

    @FXML
    private ComboBox<String> comboboxTipo;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    @FXML
    void limpiarClick(ActionEvent event) {
        nombreLabel.setText("");
        serialLabel.setText("");
        descLabel.setText("");
        cantLabel.setText("");
    }

    @FXML
    void modificarClick(ActionEvent event) {
        rk.borrarProducto(serialLabel.getText());
        rk.añadirProducto(nombreLabel.getText(),serialLabel.getText(),descLabel.getText(),Integer.parseInt(cantLabel.getText()) ,comboboxTipo.getValue());
    }

    @FXML
    void volverClick(ActionEvent event) {
        app.mostrarInventario();
    }


    public void hasieratu(Producto p){
        comboboxTipo.getItems().addAll("PC","Video","Red","Otros");
        serialLabel.setText(p.getSerial());
        descLabel.setText(p.getDesc());
        cantLabel.setText(String.valueOf(p.getCantidad()));
        comboboxTipo.setValue(p.getTipo());
        nombreLabel.setText(p.getNombre());
    }

    public void setMainApp(App app) {
        this.app = app;
    }

}
