package View;

import Model.*;
import Control.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ListePatientsController {

    @FXML
    private TableView<Patient> mytable;

    @FXML
    private TableColumn<Patient, Integer> col_id;

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
    private TextField textField;


    ObservableList<Patient> table_data;


    @FXML
    private void initialize() throws IOException {
        initTable();
        clickableRow();
    }

    @FXML
    private void delete(ActionEvent event){
        Patient p = mytable.getSelectionModel().getSelectedItem();
        table_data.remove(p);
        Listes.Patients.remove(p);
    }
    @FXML
    private void retour(MouseEvent event)throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        Scene scene2 = new Scene(view2,640,480);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void initTable(){
        initCols();
        loadData();
    }

    @FXML
    private void initCols(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        col_infoMed.setCellValueFactory(new PropertyValueFactory<>("informationMed"));
    }

    private void loadData(){
        table_data = FXCollections.observableArrayList(Listes.Patients);
        /** ---------------------------------------------------------------------*/
        FilteredList<Patient> filteredData = new FilteredList<>(table_data, p -> true);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pat -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (pat.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (pat.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (pat.getAdresse().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (pat.getInformationMed().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if (pat.getMail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Patient> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(mytable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        mytable.setItems(sortedData);

        /********************************************************************/
    }

    void clickableRow(){
        mytable.setRowFactory( tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ListeRDVsDeTelController.patientID = row.getItem().getId();
                    System.out.println(ListeRDVsDeTelController.patientID);
                    System.out.println(Requetes.recupRDVPatient(ListeRDVsDeTelController.patientID));
                    Parent view2 = null;
                    try {
                        view2 = FXMLLoader.load(getClass().getResource("listeRDVsDeTel.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene2 = new Scene(view2);
                    Stage window = new Stage();
                    window.setScene(scene2);
                    window.show();
                }
            });
            return row ;
        });
    }
}

