package Seftic;

import Seftic.UI.AñadirController;
import Seftic.UI.TableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Parent root;
    private Parent root2;

    private Stage stage;
    private Stage stageAñadir;

    private Scene escena;

    private TableController tCont;
    private AñadirController añadirCont;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stageAñadir = primaryStage;
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
        añadirCont = loader2.getController();
        añadirCont.setMainApp(this);
    }

    public void enseñarAñadir(){
        stageAñadir.setScene(new Scene(root2));
        stageAñadir.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void enseñarTabla() {
        stage.setScene(escena);
    }
}
