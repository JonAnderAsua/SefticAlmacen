package Seftic;

import Seftic.UI.AñadirController;
import Seftic.UI.AñadirStockController;
import Seftic.UI.TableController;
import Seftic.UI.TableStockController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Parent root;
    private Parent root2;
    private Parent root3;
    private Parent root4;

    private Stage stage;
    private Stage stageAñadir;
    private Stage stageStock;
    private Stage stageAñadirStock;

    private Scene escena;
    private Scene escena2;
    private Scene escena3;
    private Scene escena4;

    private TableController tCont;
    private AñadirController añadirCont;
    private TableStockController stockCont;
    private AñadirStockController añadirStockCont;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stageAñadir = primaryStage;
        stageStock = primaryStage;
        stageAñadirStock = primaryStage;
        pantailakKargatu();

        stage.setTitle("Almacén Seftic");
        escena = new Scene(root,950,600);
        stage.setScene(escena);
        stage.show();
    }

    private void pantailakKargatu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tabla.fxml"));
        root = loader.load();
        tCont = loader.getController();
        tCont.setMainApp(this);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/Añadir.fxml"));
        root2 = loader2.load();
        escena2 = new Scene(root2);
        añadirCont = loader2.getController();
        añadirCont.setMainApp(this);

        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/tablaStock.fxml"));
        root3 = loader3.load();
        escena3 = new Scene(root3);
        stockCont = loader3.getController();
        stockCont.setMainApp(this);

        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/añadirProducto.fxml"));
        root4 = loader4.load();
        escena4 = new Scene(root4);
        añadirStockCont = loader4.getController();
        añadirStockCont.setMainApp(this);


    }

    public void enseñarAñadir(){
        stageAñadir.setScene(escena2);
        stageAñadir.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void enseñarTabla() {
        stage.setScene(escena);
    }

    public void mostrarInventario() {
        stageStock.setScene(escena3);
        stageStock.show();
    }

    public void enseñarAñadirStock() {
        stageAñadirStock.setScene(escena4);
        stageAñadirStock.show();
    }
}
