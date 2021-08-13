package Seftic.UI;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
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
    private Label avisoLabel;


    @FXML
    private ComboBox<String> comboNombre;

    private App app;
    private RecursosKud rk = RecursosKud.getInstance();
    private Boolean admin = false;

    @FXML
    void añadirClick(ActionEvent event) throws ParseException, SQLException {
        Boolean hayStock = true;

        //Comprobar si mete todos los valores obligatorios
        if(comboNombre.getValue() == null  || trabajadorId.getValue() == null || cantidadId.getText() == null || fEntradaId.getText() == null){
            avisoLabel.setText("Hay que meter los valores obligatorios");
        }

        else {
            Producto p = rk.getProductoUnico(comboNombre.getValue()) ;
            if(comentarioId.getText() == null){
                comentarioId.setText("");
            }
            if(entradaSalidaBox.getValue().equals("Salida")){ //Sale algo del almacén
                hayStock = rk.comprobarStock(comboNombre.getValue(),Integer.parseInt(cantidadId.getText()));
            }
            if(clienteId.getText() == null){
                clienteId.setText("");
            }

            if(hayStock && comprobarFechas(fEntradaId.getText())){
                rk.añadirRegistro(new Registro(comboNombre.getValue(),serialId.getText(),p.getDesc(),comentarioId.getText(), p.getTipo(), fEntradaId.getText(),entradaSalidaBox.getValue(),clienteId.getText(),trabajadorId.getValue(),Integer.parseInt(cantidadId.getText())));
                app.actualizarListaDeRegistros();
                app.actualizarListaStock();
                app.enseñarTabla();
            }
            if(!hayStock){
                avisoLabel.setText("La cantidad máxima que se puede sacar es: " + rk.getProductoUnico(comboNombre.getValue()).getCantidad());
            }
            if(!comprobarFechas(fEntradaId.getText())){
                avisoLabel.setText("Introduce bien las fechas YYYY/MM/DD");
            }
        }

    }

    private Boolean comprobarFechas(String fechaIn) {
        Boolean correcto = false;
        if(!fechaIn.equals("")){
            char[] fechaEntrada = fechaIn.toCharArray();
            System.out.println(fechaEntrada.length);
            if(fechaEntrada[4] == '/' && fechaEntrada[7] == '/' && fechaEntrada.length == 10){
                System.out.println("Las barras están");
                correcto = sonNumeros(fechaEntrada);
            }
        }
        return correcto;
    }

    private Boolean sonNumeros(char[] fechaEntrada) {
        int i = 0;
        boolean esNumero = true;

        while(i < fechaEntrada.length && esNumero){
            if(fechaEntrada[i] != '/'){
                try{
                    Integer.parseInt(String.valueOf(fechaEntrada[i]));
                }
                catch (NumberFormatException exception){
                    esNumero = false;
                }
            }
            i++;
        }

        if(esNumero){
           int mes = Integer.parseInt(String.valueOf(fechaEntrada[5]) + String.valueOf(fechaEntrada[6]));
           int dia = Integer.parseInt(String.valueOf(fechaEntrada[8]) + String.valueOf(fechaEntrada[9]));            System.out.println("Mes: " + mes + " Dia: " + dia);
           if(mes == 01 || mes == 03 || mes == 05 || mes == 07 || mes == 10 || mes == 12 || mes == 8){
               if(dia > 31){
                   esNumero = false;
               }
           }
           else if(mes == 04 || mes == 06 || mes == 9 || mes == 11){
               if(dia > 30){
                   esNumero = false;
               }
           }
           else if(mes == 02){
               if(dia > 28){
                   esNumero = false;
               }
           }
           else{
               esNumero = false;
           }
        }

        return esNumero;
    }


    public void hasieratu(Registro r) throws SQLException {
        entradaSalidaBox.getItems().clear();
        if(admin){
            entradaSalidaBox.getItems().add("Entrada");
        }
        entradaSalidaBox.getItems().add("Salida");
        System.out.println("Holi: " + r.getEntrada());
        entradaSalidaBox.setValue(r.getEntrada());

        entradaSalidaBox.setValue(r.getEntrada());

        //Reiniciar los comboBox, si no da error
        trabajadorId.getItems().clear();
        comboNombre.getItems().clear();

        List<String> lista = rk.getTrabajadores();
        for(int i = 0; i<lista.size() ; i++ ){
            trabajadorId.getItems().add(lista.get(i));
        }

        lista = rk.getAllProductosString();
        for(int i = 0; i<lista.size() ; i++ ){
            comboNombre.getItems().add(lista.get(i));
        }

        comboNombre.setValue(r.getNombreProducto());
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
        fEntradaId.setText("");
        clienteId.setText("");
        cantidadId.setText("");
    }

    @FXML
    void volverClick(ActionEvent event) {
        app.enseñarTabla();
    }

    public void setAdmin(){this.admin=true;}

    public void adminFalse(){this.admin = false;}

}
