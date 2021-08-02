package Seftic.UI;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class AñadirStockController {

    @FXML
    private TextField serialField;

    @FXML
    private TextField descField;

    @FXML
    private ComboBox<String> comboBoxTipo;

    @FXML
    private TextField nombreField;

    @FXML
    private Label labelAviso;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();


    @FXML
    void añadirClick(ActionEvent event) throws SQLException {
        if(descField.getText() == null){
            descField.setText("");
        }


        if(nombreField.getText() == null || comboBoxTipo.getValue() == null){
            labelAviso.setText("Por favor rellena los campos obligatorios...");
        }
        else{
            boolean existe = rk.comprobarNombre(nombreField.getText());
            if(existe){
                labelAviso.setText("El producto que quieres añadir ya está en la DB");
            }
            else{
                rk.añadirProducto(nombreField.getText(),serialField.getText(),descField.getText(),0,comboBoxTipo.getValue());
                labelAviso.setText("El producto " + serialField.getText() + " se ha añadido");
            }
        }
    }

    @FXML
    void limpiarClick(ActionEvent event) {
        serialField.setText("");
        descField.setText("");
    }

    @FXML
    void volverClick(ActionEvent event) {
        app.mostrarInventario();
    }

    public void setMainApp(App app) {
        this.app = app;
    }

    @FXML
    void initialize(){
        comboBoxTipo.getItems().addAll("PC", "Video", "Red", "Otro");
    }

    public void llenarCampos(Producto p) {
        serialField.setText(p.getSerial());
        descField.setText(p.getDesc());
    }
}
