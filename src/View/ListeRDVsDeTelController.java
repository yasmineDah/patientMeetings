package View;

import Model.*;
import Control.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ListeRDVsDeTelController {

    @FXML
    private TableView<RDV> mytable;
    @FXML
    private javafx.scene.control.TableColumn<RDV, Integer> col_id;
    @FXML
    private javafx.scene.control.TableColumn<RDV, LocalDate> col_date;
    @FXML
    private javafx.scene.control.TableColumn<RDV, LocalTime> col_heure;
    @FXML
    private javafx.scene.control.TableColumn<RDV, String> col_objet;


    private ObservableList<RDV> table_data;

    public static int patientID;


    @FXML
    private void initialize() {
        initTable();
    }


    @FXML
    private void retour(MouseEvent event)throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        Scene scene2 = new Scene(view2,640,480);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }


    private void initTable(){
        initCols();
        loadData();
    }

    private void initCols(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        col_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
    }

    private void loadData(){
        ArrayList<RDV> rdvs = Requetes.recupRDVPatient(patientID);
        table_data = FXCollections.observableArrayList(rdvs);
        mytable.setItems(table_data);
    }

}
