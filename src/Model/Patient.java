package Model;

import Control.*;
import View.*;

import javafx.scene.control.Label;

public class  Patient implements java.io.Serializable  {

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String mail;
    private String informationMed;

    public Patient(int id, String nom, String prenom, String adresse, String telephone, String mail, String informationMed) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
        this.informationMed = informationMed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getInformationMed() {
        return informationMed;
    }

    public void setInformationMed(String informationMed) {
        this.informationMed = informationMed;
    }

}
