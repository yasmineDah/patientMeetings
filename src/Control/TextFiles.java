package Control;



import Model.*;
import View.*;

import java.io.File;
import java.io.FileWriter;

public class TextFiles {

    public static void imprimerRDV(RDV r, String filePath){
        String s1 = "Patient : "+r.getPatient()+"\n";
        String s2 = "Objet : "+r.getObjet()+"\n";
        String s3 = "Date : "+r.getDate()+"\n";
        String s4 = "Heure : "+r.getHeure()+"\n";

        try {
            String filePathName = filePath+"\\RDV_"+r.getId()+".txt";
            File f = new File(filePathName);
            if(f.createNewFile()){
                FileWriter fw = new FileWriter(filePathName);
                fw.write(s1+s2+s3+s4);
                fw.close();
            }else{}
        }catch(Exception e){ System.out.print("Exception");}
    }
}
