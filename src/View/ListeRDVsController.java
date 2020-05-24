package View;

import Model.*;
import Control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ListeRDVsController {

    public static RDV rd;

    @FXML
    private TableView<RDV> mytable;

    @FXML
    private javafx.scene.control.TableColumn<RDV, Integer> col_id;

    @FXML
    private javafx.scene.control.TableColumn<RDV, String> col_nomPrenom;

    @FXML
    private javafx.scene.control.TableColumn<RDV, LocalDate> col_date;

    @FXML
    private javafx.scene.control.TableColumn<RDV, LocalTime> col_heure;

    @FXML
    private javafx.scene.control.TableColumn<RDV, String> col_objet;

    @FXML
    private DatePicker datePick;

    @FXML
    private TextField textField;

    ObservableList<RDV> table_data;


    @FXML
    private void initialize() throws IOException {
        initTable();
        clickableRow();
    }

    @FXML
    private void initTable(){
        initCols();
        loadData();
    }

    @FXML
    private void initCols(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nomPrenom.setCellValueFactory(new PropertyValueFactory<>("nomPrenom"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        col_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
    }

    private void loadData(){
        table_data = FXCollections.observableArrayList(Listes.RendezVous);

        /** ---------------------------------------------------------------------*/
        FilteredList<RDV> filteredData = new FilteredList<>(table_data, p -> true);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rdv -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (rdv.getNomPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (rdv.getObjet().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 2. Set the filter Predicate whenever the filter changes.
        datePick.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rdv -> {
                // If filter text is empty, display all .
                if (newValue == null) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.

                if (rdv.getDate().compareTo(newValue) == 0) {
                    return true; // Filter matches
                }else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<RDV> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(mytable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        mytable.setItems(sortedData);

        /********************************************************************/

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
    private void delete(){
        RDV rdv = mytable.getSelectionModel().getSelectedItem();
        table_data.remove(rdv);
        Requetes.supprimerRDV(rdv.getId());
    }

    void clickableRow(){
        mytable.setRowFactory( tv -> {
            TableRow<RDV> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    modifierRDVController.rdv = row.getItem();
                    Parent view2 = null;
                    try {
                        view2 = FXMLLoader.load(getClass().getResource("modifierRDV.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene2 = new Scene(view2);
                    Stage window = new Stage();
                    window.setScene(scene2);
                    window.showAndWait();

                    table_data.clear();
                    initTable();
                }
            });
            return row ;
        });
    }

    @FXML
    void imprimer(){
        //Trouver Le RDV selectionne
        RDV r = new RDV();
        //
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if(selectedDirectory == null){
            //No Directory selected
        }else{
            String path = selectedDirectory.getAbsolutePath();
            System.out.println(path);
            TextFiles.imprimerRDV(r, path);
        }
    }

}
