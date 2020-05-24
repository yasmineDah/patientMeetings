package Control;



import Model.*;
import View.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Requetes {

    public static int insererPatient(Patient p){
        Listes.maxIndexPatients++;
        p.setId(Listes.maxIndexPatients);
        Listes.Patients.add(p);
        return Listes.maxIndexPatients;
    }

    public static void insererRDV(RDV r){
        Listes.maxIndexRDV++;
        r.setId(Listes.maxIndexRDV);
        Listes.RendezVous.add(r);
    }

    public static ArrayList<RDV> recupRDVPatient(int p){
        ArrayList<RDV> rdvs = new ArrayList<>();
        for (RDV r :
                Listes.RendezVous) {
            if (r.getPatient() == p){
                rdvs.add(r);
            }
        }
        return rdvs;
    }

    public static void modifierRDV(RDV rn){
        for (RDV r :
                Listes.RendezVous) {
            if(rn.getId() == r.getId()){
                int i = Listes.RendezVous.indexOf(r);
                Listes.RendezVous.set(i,rn);
                break;
            }
        }
    }

    public static void supprimerRDV(int id){
        for (RDV r :
                Listes.RendezVous) {
            if(r.getId() == id){
                Listes.RendezVous.remove(r);
                break;
            }
        }
    }

    public static ArrayList<RDV> rdvJour(LocalDate d){
        ArrayList<RDV> rdvs = new ArrayList<>();
        for (RDV r :
                Listes.RendezVous) {
            if (r.getDate().compareTo(d) == 0) {
                rdvs.add(r);
            }
        }
        return rdvs;
    }
}