package Seftic.UI;

import Seftic.App;
import Seftic.DB.RecursosKud;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class AnadirStock {


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
    void anadirClick(ActionEvent event) throws SQLException {
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
                rk.anadirProducto(nombreField.getText(),descField.getText(),0,comboBoxTipo.getValue());
                app.actualizarListaStock();
                labelAviso.setText("El producto " + nombreField.getText() + " se ha añadido");
            }
        }
    }

    @FXML
    void limpiarClick(ActionEvent event) {
        descField.setText("");
        labelAviso.setText("");
        nombreField.setText("");
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
        comboBoxTipo.getItems().clear();
        comboBoxTipo.getItems().addAll("PC", "Video", "Red", "Otro");
    }

}
