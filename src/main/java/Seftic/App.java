package Seftic;

import Seftic.UI.*;
import Seftic.model.Producto;
import Seftic.model.Registro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class App extends Application {

    private Parent root;
    private Parent root2;
    private Parent root3;
    private Parent root4;
    private Parent root5;
    private Parent root6;
    private Parent root7;
    private Parent root8; //Parent para hacer el login

    private Stage stage;
    private Stage stageAnadir;
    private Stage stageStock;
    private Stage stageAnadirStock;
    private Stage stageModificarProducto;
    private Stage stageTrabajadores;
    private Stage stageAnadirTrabajador;
    private Stage stageLogin;

    private Scene escena;
    private Scene escena2;
    private Scene escena3;
    private Scene escena4;
    private Scene escena5;
    private Scene escena6;
    private Scene escena7;
    private Scene escena8; //Escena del login

    private TableController tCont;
    private AnadirController anadirCont;
    private TableStockController stockCont;
    private AnadirStock anadirStockCont;
    private ModificarProducto modificarProductoCont;
    private TrabajadoresContr trabContr;
    private AnadirTrabajadorController anadirTrabContr;
    private Login loginContr;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image img = new Image(getClass().getResourceAsStream("/logoSeftic.png"));
        stage = primaryStage;
        stage.setTitle("Lista de recursos");
        stage.getIcons().add(img);
        stageAnadir = primaryStage;
        stageAnadir.setTitle("Añadir un nuevo registro");
        stageAnadir.getIcons().add(img);
        stageStock = primaryStage;
        stageStock.setTitle("Lista de los productos");
        stageStock.getIcons().add(img);
        stageAnadirStock = primaryStage;
        stageAnadirStock.setTitle("Añadir un nuevo producto");
        stageLogin = primaryStage;
        stageLogin.setTitle("Login");

        stageModificarProducto = primaryStage;
        stageModificarProducto.setTitle("Modificar un producto");

        stageTrabajadores = primaryStage;
        stageTrabajadores.setTitle("Lista de trabajadores");

        stageAnadirTrabajador = primaryStage;
        stageAnadirTrabajador.setTitle("Añadir un nuevo trabajador");

        pantailakKargatu();

        cargarLogin();
        /*
        escena8 = new Scene(root8,950,600);
        stageLogin.setScene(escena8);
        stageLogin.show();

         */
    }

    private void pantailakKargatu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tabla.fxml"));
        root = loader.load();
        tCont = loader.getController();
        tCont.setMainApp(this);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/Añadir.fxml"));
        root2 = loader2.load();
        escena2 = new Scene(root2);
        anadirCont = loader2.getController();
        anadirCont.setMainApp(this);

        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/tablaStock.fxml"));
        root3 = loader3.load();
        escena3 = new Scene(root3);
        stockCont = loader3.getController();
        stockCont.setMainApp(this);

        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/añadirProducto.fxml"));
        root4 = loader4.load();
        escena4 = new Scene(root4);
        anadirStockCont = loader4.getController();
        anadirStockCont.setMainApp(this);

        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("/modificarProducto.fxml"));
        root5 = loader5.load();
        escena5 = new Scene(root5);
        modificarProductoCont = loader5.getController();
        modificarProductoCont.setMainApp(this);

        FXMLLoader loader6 = new FXMLLoader(getClass().getResource("/tablaTrabajadores.fxml"));
        root6 = loader6.load();
        escena6 = new Scene(root6);
        trabContr = loader6.getController();
        trabContr.setMainApp(this);

        FXMLLoader loader7 = new FXMLLoader(getClass().getResource("/añadirTrabajador.fxml"));
        root7 = loader7.load();
        escena7 = new Scene(root7);
        anadirTrabContr = loader7.getController();
        anadirTrabContr.setMainApp(this);

        FXMLLoader loader8 = new FXMLLoader(getClass().getResource("/admin.fxml"));
        root8 = loader8.load();
        escena8 = new Scene(root8);
        loginContr = loader8.getController();
        loginContr.setMainApp(this);


    }

    public void ensenarAnadir() throws ParseException, SQLException {
        stageAnadir.setScene(escena2);
        stageAnadir.show();
        Registro r = new Registro("","","","","","","","","",0); //Hacer esto es un poco feo pero es que no se me ocurre otra cosa
        anadirCont.hasieratu(r);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void ensenarTabla() {
        stage.setScene(escena);
    }

    public void mostrarInventario() {
        stageStock.setScene(escena3);
        stageStock.show();
    }

    public void ensenarAnadirStock() {
        stageAnadirStock.setScene(escena4);
        stageAnadirStock.show();
    }

    public void modificarProducto(Producto p){
        stageModificarProducto.setScene(escena5);
        stageModificarProducto.show();
        modificarProductoCont.hasieratu(p);
    }

    public void modificarRegistro(Registro r) throws SQLException {
        stageAnadir.setScene(escena2);
        stageAnadir.show();
        anadirCont.hasieratu(r);
    }

    public void cargarTablaTrab() throws SQLException {
        stageTrabajadores.setScene(escena6);
        stageTrabajadores.show();
        trabContr.iniciar();
    }

    public void cargarNuevoTrab(String s){
        stageAnadirTrabajador.setScene(escena7);
        stageAnadirTrabajador.show();
        anadirTrabContr.iniciar(s);
    }


    //Actualizar la lista de registros para cada vez que se añade uno nuevo
    public void actualizarListaDeRegistros() throws SQLException, ParseException {
        tCont.actualizarTabla();
    }

    //Actualizar la lista de trabajadores cada vez que se añade uno nuevo
    public void actulizarListaTrabajadores() throws SQLException {
        trabContr.actualizarTabla();
    }

    //Actualizar la lista de productos cada vez que se añade uno nuevo
    public void actualizarListaStock() throws SQLException {
        stockCont.actualizarTabla();
    }


    public void setAdmin(){
        anadirCont.setAdmin();
    }

    public void cargarLogin(){
        stageLogin.setScene(escena8);
        stageLogin.show();
    }
}
