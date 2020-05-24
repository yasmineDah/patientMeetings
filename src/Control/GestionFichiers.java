package Control;


import Model.*;
import View.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestionFichiers {

    public static void stockage(){
        try {
            FileOutputStream fos = new FileOutputStream("data\\donnees.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Stock s = new Stock();
            s.Patients = Listes.Patients;
            s.RendezVous = Listes.RendezVous;
            s.maxIndexPatients = Listes.maxIndexPatients;
            s.maxIndexRDV = Listes.maxIndexRDV;
            oos.writeObject(s);
            oos.close();
            fos.close();
        } catch (Exception i) {
            i.printStackTrace();
        }
    }

    public static void recuperation(){
        try {
            FileInputStream fileIn = new FileInputStream("data\\donnees.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Stock s = (Stock) in.readObject();
            in.close();
            fileIn.close();
            Listes.Patients = s.Patients;
            Listes.RendezVous = s.RendezVous;
            Listes.maxIndexPatients = s.maxIndexPatients;
            Listes.maxIndexRDV = s.maxIndexRDV;

        } catch (Exception i) {
            i.printStackTrace();
        }
    }


    



}
