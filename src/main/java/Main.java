import UI.TableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Parent root;
    private Stage stage;
    private TableController tCont;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("Almacén Seftic");
        stage.setScene(new Scene(root,950,600));
        stage.show();
    }

    private void pantailakKargatu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tabla.fxml"));
        root = loader.load();
        tCont = loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
