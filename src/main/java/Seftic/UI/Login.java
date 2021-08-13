package Seftic.UI;

import Seftic.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Login {

    @FXML
    private ImageView imageId;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label labelId;

    private App app;

    @FXML
    void accederClick(ActionEvent event) {
        String s = "pasahitza";

        if(passwordField.getText().hashCode() == s.hashCode()){
            app.setAdmin();
            app.enseñarTabla();
        }
        else{
            labelId.setText("Contraseña incorrecta...");
        }
    }

    @FXML
    void noAdminClick(ActionEvent event) {
        app.enseñarTabla();
    }

    public void setMainApp(App app) {
        this.app = app;
    }

    public void borrarPass(){
        passwordField.setText("");
    }
    @FXML
    void initialize() {
        Image img = new Image(getClass().getResourceAsStream("/logoSeftic.png"));
        imageId.setImage(img);
    }
}
