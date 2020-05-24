package View;

import Model.*;
import Control.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreerRDVexistController {

    @FXML
    private DatePicker champ_dateRDVexist;

    @FXML
    private TextField hr;

    @FXML
    private TextField min;
    @FXML
    private TextField champ_objet;

    @FXML
    private TableView<Patient> mytable;

    @FXML
    private TableColumn<Patient, String> col_nom;

    @FXML
    private TableColumn<Patient, String> col_prenom;

    @FXML
    private TableColumn<Patient, String> col_adresse;

    @FXML
    private TableColumn<Patient, String> col_telephone;

    @FXML
    private TableColumn<Patient, String> col_mail;

    @FXML
    private TableColumn<Patient, String> col_infoMed;

    @FXML
    private TableColumn<Patient, Label> col_RDV;

    public static String nomm,prenomm;


    @FXML
    void creerRDVexist(ActionEvent event) {
        Patient p = mytable.getSelectionModel().getSelectedItem();
        if(champ_dateRDVexist.getValue() != null && !hr.getText().equals("") && !min.getText().equals("") && !champ_objet.getText().equals("") && p != null){
        LocalDate dt = champ_dateRDVexist.getValue();
        String objet;
        int heure, minute;
        heure = Integer.parseInt(hr.getText());
        minute = Integer.parseInt(min.getText());
        objet = champ_objet.getText();
        RDV rdv = new RDV(0,dt, LocalTime.of(heure, minute),objet,p.getId(),p.getPrenom()+" "+p.getNom());
        Requetes.insererRDV(rdv);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Nouveau RDV crée avec succes!");
            alert.showAndWait();

        Stage stage2 = (Stage) hr.getScene().getWindow();
        stage2.close();


            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("sample2.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 640,480);
                Stage stage = new Stage();
                stage.setTitle("New Window");
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs\n\nVeuillez choisir un patient du tableau par la suite");
            alert.showAndWait();
        }
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        Scene scene2 = new Scene(view2,640,480);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void initialize() {
        numericOnly(hr);
        numericOnly(min);
        initTable();
    }

    @FXML
    private void initTable(){
        initCols();
        loadData();
    }

    @FXML
    private void initCols(){
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        col_infoMed.setCellValueFactory(new PropertyValueFactory<>("informationMed"));
        col_RDV.setCellValueFactory(new PropertyValueFactory<>("RDV"));
    }

    private void loadData(){
        ObservableList<Patient> table_data = FXCollections.observableArrayList();
        for (int i = 0; i< Listes.Patients.size(); i++){
            table_data.add(Listes.Patients.get(i));
        }
        mytable.setItems(table_data);
    }

    void numericOnly(TextField textField){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }


}
