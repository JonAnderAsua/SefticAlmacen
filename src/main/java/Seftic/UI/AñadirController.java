package Seftic.UI;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Registro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AñadirController {

    @FXML
    private TextField serialId;

    @FXML
    private TextField descripcionId;

    @FXML
    private TextField clienteId;

    @FXML
    private ComboBox<String> tipoId;

    @FXML
    private DatePicker fEntradaId;

    @FXML
    private DatePicker fSalidaId;

    @FXML
    private TextField comentarioId;

    @FXML
    private ComboBox<String> trabajadorId;

    @FXML
    private TextField cantidadId;

    @FXML
    private Button añadirId;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    @FXML
    void añadirClick(ActionEvent event) throws ParseException {
        Registro r = new Registro(serialId.getText(),descripcionId.getText(),comentarioId.getText(), tipoId.getValue(), fEntradaId.getValue().toString(),fSalidaId.getValue().toString(),clienteId.getText(),trabajadorId.getValue(),Integer.parseInt(cantidadId.getText()));
        rk.añadirRegistro(r);
        app.enseñarTabla();
    }

    @FXML
    void initialize() throws SQLException {

        tipoId.getItems().addAll("PC","Video","Red","Otros");
        List<String> lista = rk.getTrabajadores();
        for(int i = 0; i<lista.size() ; i++ ){
            trabajadorId.getItems().add(lista.get(i));
        }
    }

    public void setMainApp(App app) {
        this.app = app;
    }
}
