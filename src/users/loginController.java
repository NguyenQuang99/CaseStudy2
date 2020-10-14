package users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class
loginController implements Initializable {
    @FXML
    TextField txtUserName;

    @FXML
    PasswordField txtPassWord;
    @FXML
    Label checkPass;

    @FXML
    public void login(ActionEvent event) throws IOException {
        try {
            File userPass = new File("D:\\Books\\CaseStudyModule2\\userNamePass.txt");
            if (!userPass.exists()) {
                userPass.createNewFile();
            }
        } catch (Exception ex) {
            System.out.println("Not found file!!!");
        }
        String login = txtUserName.getText();
        String pass = txtPassWord.getText();
        if (!login.equals("abc") || !pass.equals("abc")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information !!!");
            alert.setContentText("User name or password wrong ! Please enter again !");
            alert.show();
        } else {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../bookmanage/bookmanager.fxml"));
            primaryStage.setTitle("Book Manager");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.centerOnScreen();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
