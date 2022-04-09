package gestiondesprofesseurs.Presentation;

import gestiondesprofesseurs.Metier.Departement;
import gestiondesprofesseurs.Metier.IMetierImplement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DepartementController implements Initializable {

        private IMetierImplement implementDepart = new IMetierImplement();
        ObservableList<Departement> departementObservableList = FXCollections.observableArrayList();

        @FXML
        private TableView<Departement> idTableViewDepart;

        @FXML private TableColumn<Departement, Integer> idDepartement;
        @FXML private TableColumn<Departement, String> idNomDepart;
        @FXML private TableColumn<Departement, Button> idActiondelete;
        @FXML private TableColumn<Departement, Button> idActionEdit;

        @FXML private TextField idTextFieldDepart;

        @FXML private Button idAjouterDepart;

        @Override
        public void initialize(URL location, ResourceBundle resources)
        {
            idDepartement.setCellValueFactory(new PropertyValueFactory<>("idDepart"));
            idNomDepart.setCellValueFactory(new PropertyValueFactory<>("nomDepart"));
            idActiondelete.setCellValueFactory(new PropertyValueFactory<>("supprimerBtn"));//a ajouter dans chaque ligne
            idActionEdit.setCellValueFactory(new PropertyValueFactory<>("modifierBtn"));//a ajouter dans chaque ligne
            departementObservableList.addAll(implementDepart.getAllDepartement());
            idTableViewDepart.getItems().addAll(departementObservableList);

        }
        @FXML private void actionDeleteBtn()
        {
            for(int i=0; i<departementObservableList.size();i++)
            {
                int finalI = i;
                idActiondelete.getCellData(i).setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message de confirmation");
                    alert.setContentText("Voulez-vous vraiment supprimer ce département!");
                    // pour recuperer la la dÃ©cision de la suppression
                    Optional<ButtonType> option = alert.showAndWait();

                    if(option.get() == ButtonType.OK)
                    {
                        implementDepart.deleteDepartement(departementObservableList.get(finalI));
                        departementObservableList.clear();
                        departementObservableList.addAll(implementDepart.getAllDepartement());
                        idTableViewDepart.setItems(departementObservableList);
                    }
                });
            }
        }


        @FXML private void actionAjouterDepart()
        {

            String nomDepart = idTextFieldDepart.getText();
            String resultat = null;
            for(Departement d:idTableViewDepart.getItems())
            {
                if(nomDepart.equals(d.getNomDepart()))
                    resultat = nomDepart;
            }
            if(resultat == null)
            {


                if(nomDepart.equals(" ") || nomDepart.length()<4 || nomDepart.isEmpty())
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setContentText("Veuillez saisir le nouveau nom du département");
                    alert.show();
                }
                else
                {
                    Departement departement = new Departement(nomDepart);
                    implementDepart.addDepartement(departement);
                    departementObservableList.clear();
                    departementObservableList.addAll(implementDepart.getAllDepartement());
                    idTableViewDepart.setItems(departementObservableList);
                }

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setContentText("Ce département existe déjà ");
                alert.show();
            }
        }

        @FXML private void actionEditBtn()
        {
            int indice = idTableViewDepart.getSelectionModel().getSelectedIndex();//index sur une ligne

            if(indice>=0)
            {
                Departement oldDepartement = idTableViewDepart.getItems().get(indice);
                idTableViewDepart.setEditable(true);//permettre a éditer

                idNomDepart.setCellFactory(TextFieldTableCell.forTableColumn());
                idNomDepart.onEditCommitProperty().setValue(event -> {
                    for(int i=0; i<departementObservableList.size();i++)
                    {
                        int finalI = i;
                        idActionEdit.getCellData(finalI).setOnAction(event1 ->
                        {
                            Departement newDepartement = new Departement(event.getNewValue());//avoir la nouvelle valeur
                            implementDepart.modifyDepartement(oldDepartement, newDepartement);//appler la fonction afin de modifier la nouvelle valeur da sla base de dnées

                            departementObservableList.clear();
                            departementObservableList.addAll(implementDepart.getAllDepartement());
                            idTableViewDepart.setItems(departementObservableList);
                        });
                    }
                });
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message d'erreur !");
                alert.setContentText("Vous devez choisir un département ");
                alert.show();

            }
        }
}