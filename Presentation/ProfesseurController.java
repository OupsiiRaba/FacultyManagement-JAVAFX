package gestiondesprofesseurs.Presentation;

import gestiondesprofesseurs.Metier.Departement;
import gestiondesprofesseurs.Metier.IMetierImplement;
import gestiondesprofesseurs.Metier.Professeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProfesseurController implements Initializable {


        private IMetierImplement implementProf = new IMetierImplement();
        private ObservableList<Professeur> professeurObservableList = FXCollections.observableArrayList();

        @FXML
        private BorderPane idBorderBaneProf = new BorderPane();
        @FXML
        private TableView<Professeur> tableViewDesProfesseurs = new TableView<>();

        @FXML
        private TableColumn<Professeur, Integer> idProfesseur = new TableColumn<>();
        @FXML
        private TableColumn<Professeur, String> nomProfesseur = new TableColumn<>();
        @FXML
        private TableColumn<Professeur, String> prenomProf = new TableColumn<>();
        @FXML
        private TableColumn<Professeur, String> cinProf = new TableColumn<>();
        @FXML
        private TableColumn<Professeur, String> adresseProf = new TableColumn<>();
        @FXML private TableColumn<Professeur, String> telephoneProf = new TableColumn<>();
        @FXML private TableColumn<Professeur, String> emailProf = new TableColumn<>();
        @FXML private TableColumn<Professeur, String> dateRucProf = new TableColumn<>();

        @FXML private ChoiceBox<Departement> idDepartChoice = new ChoiceBox();

        @Override
        public void initialize(URL location, ResourceBundle resources)
        {
            idProfesseur.setCellValueFactory(new PropertyValueFactory<>("idProf"));
            nomProfesseur.setCellValueFactory(new PropertyValueFactory<>("nomProf"));
            prenomProf.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cinProf.setCellValueFactory(new PropertyValueFactory<>("cin"));
            adresseProf.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            telephoneProf.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            emailProf.setCellValueFactory(new PropertyValueFactory<>("email"));
            dateRucProf.setCellValueFactory(new PropertyValueFactory<>("dateRecrutement"));

            professeurObservableList.addAll(implementProf.getAllProfesseurs());
            tableViewDesProfesseurs.setItems(professeurObservableList);
            idDepartChoice.getItems().add(new Departement("Tous"));

            for (Departement d: implementProf.getAllDepartement())
            {
                idDepartChoice.getItems().add(d);
            }//pour avoir tout les départements
        }

        @FXML private void actionAfficherProfByDeapt()
        {
            Departement depart = idDepartChoice.getValue();
            professeurObservableList.clear();
            professeurObservableList.addAll(implementProf.getProfesseursByDepartement(depart));//afficher le prof par son département
            if(depart.getNomDepart().equals("Tous"))
                professeurObservableList.addAll(implementProf.getAllProfesseurs());//si c est tout afficher tout les profs

        }

        @FXML private TextField idSearchMC;
        @FXML private void actionRecherButton()//l'affecter a toutes les pages
        {
            String MotCle = idSearchMC.getText();
            professeurObservableList.clear();
            professeurObservableList.addAll(implementProf.getProfesseursByMotCle(MotCle));
        }

        @FXML private VBox idContenuProf;
        @FXML private void actionAfficherButton()
        {
            try
            {
                idContenuProf = FXMLLoader.load(getClass().getResource("professeurs/Liste.fxml"));
                idBorderBaneProf.setCenter(idContenuProf);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @FXML private TextField idNom;
        @FXML private TextField idPrenom;
        @FXML private TextField idCin;
        @FXML private TextField idTelephone;
        @FXML private TextField idEmail;
        @FXML private DatePicker idDateRec;
        @FXML private TextField idAdresse;

        @FXML private VBox idVboxAjout;

        @FXML private void actionNouveauButton()
        {
            try
            {
                idVboxAjout = FXMLLoader.load(getClass().getResource("professeurs/Ajout.fxml"));
                idBorderBaneProf.setCenter(idVboxAjout);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        @FXML private void actionEnregistrerButtonNew()//ajouter un nouveau prof
        {
            String nom = idNom.getText();
            String prenom = idPrenom.getText();
            String cin = idCin.getText();
            String telephone = idTelephone.getText();
            String email = idEmail.getText();
            String adresse = idAdresse.getText();
            LocalDate dateRecuretemnt = idDateRec.getValue();
            implementProf.addProfesseur(new Professeur(nom,prenom,cin,adresse,telephone,email,dateRecuretemnt));
            actionAnnulerButton();
        }

        @FXML private void actionAnnulerButton()//supprimer tout ce qui a été saisie
        {
            idNom.clear();
            idPrenom.clear();
            idCin.clear();
            idTelephone.clear();
            idEmail.clear();
            idAdresse.clear();
            idDateRec.getEditor().clear();

        }


        @FXML private ChoiceBox<Departement> idDepartChoiceAffecte = new ChoiceBox<>();
        @FXML private void actionAffecterButton()//le grand boutton
        {
            try
            {
                idContenuProf = FXMLLoader.load(getClass().getResource("professeurs/Affectation.fxml"));
                idBorderBaneProf.setCenter(idContenuProf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML private Button idAffecterProfToDep;
        @FXML private void actionAffecterItem()
        {
            idDepartChoiceAffecte.setItems(idDepartChoice.getItems());
            int index = tableViewDesProfesseurs.getSelectionModel().getSelectedIndex();
            Professeur professeur = tableViewDesProfesseurs.getItems().get(index);
            idAffecterProfToDep.setOnAction(event -> {
                implementProf.affectProfToDepartement(professeur, idDepartChoiceAffecte.getValue());//le professeur indexé plus le département
                actionAfficherProfByDeapt();//appeler la fonction afficher prof par département en haut
            });



        }


        @FXML private void actionModifierButton()
        {
            try
            {
                idContenuProf = FXMLLoader.load(getClass().getResource("professeurs/Modification.fxml"));
                idBorderBaneProf.setCenter(idContenuProf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML private Button idEnregistrerEdit;
        @FXML private void actionSelectItem()
        {
            int index = tableViewDesProfesseurs.getSelectionModel().getSelectedIndex();
            Professeur professeur = tableViewDesProfesseurs.getItems().get(index);
            idNom.setText(professeur.getPrenom());
            idPrenom.setText(professeur.getPrenom());
            idCin.setText(professeur.getCin());
            idTelephone.setText(professeur.getTelephone());
            idEmail.setText(professeur.getEmail());
            idAdresse.setText(professeur.getAdresse());
            idDateRec.setValue(professeur.getDateRecrutement());
            idEnregistrerEdit.setOnAction(event -> {
                String nom = idNom.getText();
                String prenom = idPrenom.getText();
                String cin = idCin.getText();
                String telephone = idTelephone.getText();
                String email = idEmail.getText();
                String adresse = idAdresse.getText();
                LocalDate dateRecuretemnt = idDateRec.getValue();
                implementProf.editProfesseur(professeur, new Professeur(nom, prenom, cin, adresse, telephone, email, dateRecuretemnt));
                // tableViewDesProfesseurs.getItems().addAll(professeurObservableList);
                actionAnnulerButton();
                professeurObservableList.clear();
                professeurObservableList.addAll(implementProf.getAllProfesseurs());
            });


        }


        @FXML private void actionSupprimerButton()//le grand boutton
        {
            try
            {
                idContenuProf = FXMLLoader.load(getClass().getResource("professeurs/Suppression.fxml"));
                idBorderBaneProf.setCenter(idContenuProf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @FXML private Button idSupprimerProf;
        @FXML private void actionSelectItemDelete()
        {
            int index = tableViewDesProfesseurs.getSelectionModel().getSelectedIndex();
            idSupprimerProf.setOnAction(event -> {
                if(index>=0)
                {
                    try
                    {
                        Professeur professeur = tableViewDesProfesseurs.getItems().get(index);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Ce professeur sera supprimer définitivement. Etes-vous sur?");
                        // pour recuperer la la décision de la suppression
                        Optional<ButtonType> option = alert.showAndWait();

                        if(option.get() == ButtonType.OK)
                        {
                            implementProf.deleteProfesseur(professeur);
                            professeurObservableList.clear();
                            professeurObservableList.addAll(implementProf.getAllProfesseurs());
                        }

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Veuillez sélectionnez un professeur!");
                    alert.show();
                }
            });

        }


    }
