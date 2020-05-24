package View;

import Model.*;
import Control.*;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class modifierRDVController implements Initializable {
    public static RDV rdv;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtIdPatient;

    @FXML
    private TextField txtNom;

    @FXML
    private DatePicker pDate;

    @FXML
    private TextField hr;

    @FXML
    private TextField min;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFields();
        numericOnly(hr);
        numericOnly(min);

    }

    void initFields(){
        txtId.setText(String.valueOf(rdv.getId()));
        txtIdPatient.setText(String.valueOf(rdv.getPatient()));
        txtNom.setText(rdv.getNomPrenom());

        pDate.setValue(rdv.getDate());
        hr.setText(String.valueOf(rdv.getHeure().getHour()));
        min.setText(String.valueOf(rdv.getHeure().getMinute()));
    }


    public void modif(ActionEvent actionEvent) {
        if(pDate.getValue() != null && !hr.getText().equals("") && !min.getText().equals("")){
            System.out.println("Modif");

            //sauvegarder dans liste
            rdv.setDate(pDate.getValue());
            rdv.setHeure(LocalTime.of(Integer.parseInt(hr.getText()), Integer.parseInt(min.getText())));
            Requetes.modifierRDV(rdv);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("RDV modifié avec succes!");
            alert.showAndWait();

            Stage stage = (Stage) txtNom.getScene().getWindow();
            stage.close();

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }











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
