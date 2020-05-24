package Control;

import Model.*;
import View.*;

import java.util.ArrayList;

public class Listes {
    public static ArrayList<Patient> Patients;
    public static ArrayList<RDV> RendezVous;
    public static int maxIndexPatients;
    public static int maxIndexRDV;

    public static void initializations(){
        Patients = new ArrayList<>();
        RendezVous = new ArrayList<>();
        maxIndexPatients = 0;
        maxIndexRDV = 0;
    }
}
