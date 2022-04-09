package gestiondesprofesseurs.Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppexamFX extends Application {

        public static void main(String[] args)
        {
            launch(args);
        }
        @Override
        public void start(Stage primaryStage) throws Exception
        {
            AnchorPane root = FXMLLoader.load(getClass().getResource("page1.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        }

    }
