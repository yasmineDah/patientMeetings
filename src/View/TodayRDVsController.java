package View;

import Control.Requetes;
import Model.RDV;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TodayRDVsController {

    @FXML
    private TableView<RDV> mytable;

    @FXML
    private javafx.scene.control.TableColumn<RDV, Integer> col_id;

    @FXML
    private javafx.scene.control.TableColumn<RDV, String> col_nomPrenom;

    @FXML
    private javafx.scene.control.TableColumn<RDV, LocalTime> col_heure;

    @FXML
    private javafx.scene.control.TableColumn<RDV, String> col_objet;

    @FXML
    private Label dateLab;

    @FXML
    private TextField textField;

    private ObservableList<RDV> table_data;

    @FXML
    public void initialize() {
        dateLab.setText(LocalDate.now().toString());
        initTable();
    }


    @FXML
    private void delete(ActionEvent event){
        RDV rdv = mytable.getSelectionModel().getSelectedItem();
        table_data.remove(rdv);
        Requetes.supprimerRDV(rdv.getId());
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
        col_heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        col_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
    }

    private void loadData(){
        table_data = FXCollections.observableArrayList(Requetes.rdvJour(LocalDate.now()));
        dateLab.setText(LocalDate.now().toString());


        /** ---------------------------------------------------------------------*/
        FilteredList<RDV> filteredData = new FilteredList<>(table_data, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
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
}
