package Seftic.UI;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import Seftic.App;
import Seftic.DB.RecursosKud;
import Seftic.model.Producto;
import Seftic.model.Registro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AñadirController {

    @FXML
    private TextField serialId;

    @FXML
    private TextField clienteId;

    @FXML
    private TextField fEntradaId;
    @FXML
    private ComboBox<String> entradaSalidaBox;

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

    @FXML
    private Button limpiarId;

    @FXML
    private Button volverId;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();

    @FXML
    void añadirClick(ActionEvent event) throws ParseException, SQLException {
        Boolean fechasCorrectas;
        Boolean hayStock = true;
        if(serialId.getText() == null  || trabajadorId.getValue() == null || cantidadId.getText() == null ){
            avisoLabel.setText("Hay que meter los valores obligatorios");
        }
        else{
            Producto p = rk.getProductoUnico(serialId.getText()) ;
            if(comentarioId.getText() == null){
                comentarioId.setText("");
            }
            if(entradaSalidaBox.getValue().equals("Salida")){ //Sale algo del almacén
                hayStock = rk.comprobarStock(serialId.getText(),Integer.parseInt(cantidadId.getText()));
            }
            if(clienteId.getText() == null){
                clienteId.setText("");
            }

            Boolean entrada = false;
            if(entradaSalidaBox.getValue().equals("Entrada")){
                entrada = true;
            }
            fechasCorrectas = comprobarFechas(fEntradaId.getText());
            if(hayStock && fechasCorrectas){
                Registro r = new Registro(serialId.getText(),p.getDesc(),comentarioId.getText(), p.getTipo(), fEntradaId.getText(),entrada,clienteId.getText(),trabajadorId.getValue(),Integer.parseInt(cantidadId.getText()));
                rk.añadirRegistro(r);
                app.enseñarTabla();
            }
            if(!hayStock){
                avisoLabel.setText("No hay tanto Stock de este producto");
            }
            if(!fechasCorrectas){
                System.out.println("Introduce las fechas correctamente: YYYY-MM-DD");
            }
        }

    }

    private Boolean comprobarFechas(String fechaIn) {
        if(!fechaIn.equals("")){
            char[] fechaEntrada = fechaIn.toCharArray();
            if(fechaEntrada[4] == '/' && fechaEntrada[7] == '/' && fechaEntrada.length == 9){
                return true;
            }
        }
        else{
            return false;
        }
        return false;
    }


    public void hasieratu(Registro r) throws SQLException {
        entradaSalidaBox.getItems().addAll("Entrada","Salida");
        entradaSalidaBox.setValue("Entrada");
        List<String> lista = rk.getTrabajadores();
        for(int i = 0; i<lista.size() ; i++ ){
            trabajadorId.getItems().add(lista.get(i));
        }
        serialId.setText(r.getSerial());
        clienteId.setText(r.getCliente());
        if(fEntradaId.getText() != null){
            fEntradaId.setText(r.getFecha());
        }
        comentarioId.setText(r.getComent());
        trabajadorId.setValue(r.getTrab());
        cantidadId.setText(String.valueOf(r.getCantMod()));
    }

    public void setMainApp(App app) {
        this.app = app;
    }

    @FXML
    void limpiarClick(ActionEvent event) {
        serialId.setText("");
        comentarioId.setText("");
        clienteId.setText("");
        cantidadId.setText("");
    }

    @FXML
    void volverClick(ActionEvent event) {
        app.enseñarTabla();
    }

}
