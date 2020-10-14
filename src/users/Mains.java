package users;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Mains {
    public static class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("../users/login.fxml"));
            primaryStage.setTitle("Book Manager");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.centerOnScreen();
        }


        public static void main(String[] args) {
            launch(args);
        }
    }
}
