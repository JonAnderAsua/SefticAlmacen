package Seftic.UI;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.text.ParseException;

public class ModificarProducto {

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
        descLabel.setText("");
        cantLabel.setText("");
    }

    @FXML
    void modificarClick(ActionEvent event) throws SQLException, ParseException {
        rk.borrarProducto(nombreLabel.getText());
        rk.anadirProducto(nombreLabel.getText(),descLabel.getText(),Integer.parseInt(cantLabel.getText()) ,comboboxTipo.getValue());
        app.actualizarListaStock();
        app.actualizarListaDeRegistros();
    }

    @FXML
    void volverClick(ActionEvent event) {
        app.mostrarInventario();
    }


    public void hasieratu(Producto p){
        comboboxTipo.getItems().clear();
        comboboxTipo.getItems().addAll("PC","Video","Red","Otros");
        nombreLabel.setText(p.getNombre());
        cantLabel.setText(String.valueOf(p.getCantidad()));
        comboboxTipo.setValue(p.getTipo());
        nombreLabel.setText(p.getNombre());
    }

    public void setMainApp(App app) {
        this.app = app;
    }

}
