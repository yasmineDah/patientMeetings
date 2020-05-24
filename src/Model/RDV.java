package Model;


import Control.*;
import View.*;

import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.LocalTime;

public class RDV implements java.io.Serializable{



    private int id;
    private LocalDate date;
    private LocalTime heure;
    private String objet;
    private int patient;

    private String nomPrenom;

    public RDV(){}


    public RDV(int id, LocalDate date, LocalTime heure, String objet, int patient, String nomPrenom) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.objet = objet;
        this.patient = patient;
        this.nomPrenom = nomPrenom;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }
}
