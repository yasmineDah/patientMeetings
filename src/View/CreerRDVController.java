package View;

import Model.*;
import Control.*;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreerRDVController {

    @FXML
    private TextField champ_nom;

    @FXML
    private TextField champ_prenom;

    @FXML
    private DatePicker champ_dateRDV;

    @FXML
    private TextField champ_adresse;

    @FXML
    private TextField champ_telephone;

    @FXML
    private TextField champ_mail;

    @FXML
    private TextField hr;

    @FXML
    private TextField min;

    @FXML
    private TextArea champ_objet;

    @FXML
    private TextArea champ_infoMed;

    @FXML
    void creerRDVNonExist(ActionEvent event) {
        if(!champ_nom.getText().equals("") && !champ_prenom.getText().equals("") && !champ_adresse.getText().equals("") && !champ_telephone.getText().equals("") && !champ_mail.getText().equals("") && !champ_infoMed.getText().equals("") && champ_dateRDV.getValue() != null && !hr.getText().equals("") && !min.getText().equals("") &&  !champ_objet.getText().equals("")){
        String nom,prenom,adresse,telephone,mail,infoMed,objet;
        int heure, minute;
        LocalDate dt =  champ_dateRDV.getValue();
        nom = champ_nom.getText();
        prenom = champ_prenom.getText();
        adresse = champ_adresse.getText();
        telephone = champ_telephone.getText();
        mail = champ_mail.getText();
        infoMed = champ_infoMed.getText();
        heure = Integer.parseInt(hr.getText());
        minute = Integer.parseInt(min.getText());
        objet = champ_objet.getText();
        Patient pt = new Patient(0,nom,prenom,adresse,telephone,mail,infoMed);
        int id = Requetes.insererPatient(pt);
        RDV rdv = new RDV(0, dt, LocalTime.of(heure, minute),objet,id,prenom+" "+nom);
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
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
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
    private void initialize() {
        numericOnly(hr);
        numericOnly(min);
        numericOnly(champ_telephone);

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
