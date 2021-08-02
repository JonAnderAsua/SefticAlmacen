package Seftic.UI;

import Seftic.App;
import Seftic.DB.RecursosKud;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AñadirTrabajadorController {

    @FXML
    private TextField nombreId;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    @FXML
    void añadirClick(ActionEvent event) throws SQLException {
        if(!rk.existeElTrabajador(nombreId.getText())){
            rk.añadirTrabajador(nombreId.getText());
            app.actulizarListaTrabajadores();
            app.cargarTablaTrab();
        }
    }

    public void setMainApp(App app){this.app = app;}

    public void iniciar(String s){
        nombreId.setText(s);
    }

}
