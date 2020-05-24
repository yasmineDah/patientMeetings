package Model;

import Control.*;
import View.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Stock implements Serializable {

    public ArrayList<Patient> Patients;
    public ArrayList<RDV> RendezVous;
    public int maxIndexPatients;
    public int maxIndexRDV;



}
