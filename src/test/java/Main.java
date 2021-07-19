import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Parent root;
    private Stage stage;
    private MainKud mainKud;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("Almacén Seftic");
        stage.setScene(new Scene(root,741,561));
        stage.show();
    }

    private void pantailakKargatu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        root = loader.load();
        mainKud = loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
