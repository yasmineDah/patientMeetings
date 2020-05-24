import Control.GestionFichiers;
import Control.Listes;
import Control.Requetes;
import Model.Patient;
import Model.RDV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main extends Application {

    @Override
    public void stop(){
        System.out.println("Stage is closing");

        GestionFichiers.stockage();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.setResizable(false);
        primaryStage.show();

    }



    public static void main(String[] args) {

       /*Listes.initializations();
        Requetes.insererPatient(new Patient(0,"Suzanne","Collins", "Connecticut", "555 555 555", "Suzanne@Collins.com", "Maladie 1"));
        Requetes.insererPatient(new Patient(0 , "Dashner","James", "Georgia", "444 444 444", "James@Dashner.com", "Maladie 2"));
        Requetes.insererPatient(new Patient(0, "Yancey","Rick", "Miami", "777 777 777", "Rick@Yancey.com", "Maladie 3"));
        Requetes.insererPatient(new Patient(0, "Meyer","Stephenie", "Connecticut", "888 888 888", "Stephenie@Meyer.com", "Maladie 4"));
        Requetes.insererPatient(new Patient(0, "Claire","Cassandra", "Tahran", "222 222 222", "Cassandra@Claire.com", "Maladie 5"));
        Requetes.insererPatient(new Patient(0, "Green","John", "Indiana", "666 666 666", "John@Green.com", "Maladie 6"));
        //System.out.println("MaxP = "+Listes.maxIndexPatients);
        //System.out.println("Count = "+Listes.Patients.size());

        //RDV(int id, LocalDate date, LocalTime heure, String objet, int patient)

        Requetes.insererRDV(new RDV(0, LocalDate.of(2020, 4, 16), LocalTime.now(), "Objet 1", 1, "Suzanne Collins"));
        Requetes.insererRDV(new RDV(0, LocalDate.of( 2020, 4, 15), LocalTime.now(), "Objet 2", 1, "Suzanne Collins"));
        Requetes.insererRDV(new RDV(0, LocalDate.of(2020, 4, 14), LocalTime.now(), "Objet 3", 1, "Suzanne Collins"));
        Requetes.insererRDV(new RDV(0, LocalDate.of(2020, 4, 13), LocalTime.now(), "Objet 4", 2, "James Dashner"));
        Requetes.insererRDV(new RDV(0, LocalDate.of(2020, 4, 17), LocalTime.now(), "Objet 5", 2, "James Dashner"));
        Requetes.insererRDV(new RDV(0, LocalDate.of(2020, 4, 17), LocalTime.now(), "Objet 6", 2,"James Dashner"));
        Requetes.insererRDV(new RDV(0, LocalDate.of(2020, 4, 11), LocalTime.now(), "Objet 7", 3, "Rick Yancey"));
        Requetes.insererRDV(new RDV(0, LocalDate.of(2020, 4, 22), LocalTime.now(), "Objet 8", 3, "Rick Yancey"));
        Requetes.insererRDV(new RDV(0, LocalDate.of(2020, 4, 30), LocalTime.now(), "Objet 9", 3, "Rick Yancey"));
        Requetes.insererRDV(new RDV(0, LocalDate.of(2020, 5, 4), LocalTime.now(), "Objet 10", 4,"Stephanie Meyer"));
        //System.out.println("MaxR = "+Listes.maxIndexRDV);
        //System.out.println("Count = "+Listes.RendezVous.size());

        */

       GestionFichiers.recuperation();
        launch(args);
    }
}
