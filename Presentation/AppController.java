package gestiondesprofesseurs.Presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppController {

    @FXML
    private AnchorPane idAccueilView;

    @FXML
    private Button idGestionProf;

    @FXML
    private Button idGestionDepartement;

    @FXML
    void actionGestionProf()
    {
        Stage windowProf = new Stage();
        try
        {
            BorderPane rootProf = FXMLLoader.load(getClass().getResource("page2.fxml"));
            Scene sceneProf = new Scene(rootProf);
            windowProf.setScene(sceneProf);
            windowProf.initModality(Modality.APPLICATION_MODAL);
            windowProf.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void actionGestionDepartement()
    {
        Stage windowDepart= new Stage();
        try
        {
            AnchorPane rootProf = FXMLLoader.load(getClass().getResource("page3.fxml"));
            Scene sceneDepart = new Scene(rootProf);
            windowDepart.setScene(sceneDepart);
            windowDepart.initModality(Modality.APPLICATION_MODAL);
            windowDepart.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }





}
