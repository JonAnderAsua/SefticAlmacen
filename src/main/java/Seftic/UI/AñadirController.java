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
import javafx.scene.control.*;

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

    @FXML
    private Label avisoLabel;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    @FXML
    void añadirClick(ActionEvent event) throws ParseException, SQLException {
        String fechaOut = "";
        String fechaIn = "";
        Boolean hayStock = true;
        if(serialId.getText() == null || tipoId.getValue() == null || trabajadorId.getValue() == null || cantidadId.getText() == null ){
            avisoLabel.setText("Hay que meter los valores obligatorios");
        }
        else{
            if(descripcionId.getText() == null){
                descripcionId.setText("");
            }
            if(comentarioId.getText() == null){
                comentarioId.setText("");
            }
            if(fEntradaId.getValue().toString() != null){
                fechaIn = fEntradaId.getValue().toString();
                hayStock = rk.comprobarStock(serialId.getText(),Integer.parseInt(cantidadId.getText()));
            }
            if(fSalidaId.getValue().toString() != null){
                fechaOut = fSalidaId.getValue().toString();

            }
            if(clienteId.getText() == null){
                clienteId.setText("");
            }
            if(hayStock){
                Registro r = new Registro(serialId.getText(),descripcionId.getText(),comentarioId.getText(), tipoId.getValue(), fechaIn,fechaOut,clienteId.getText(),trabajadorId.getValue(),Integer.parseInt(cantidadId.getText()));
                rk.añadirRegistro(r);
                app.enseñarTabla();
            }

            else{
                avisoLabel.setText("No hay tanto Stock de este producto");
            }
        }

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
