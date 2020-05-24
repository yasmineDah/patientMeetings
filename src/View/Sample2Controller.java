package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class Sample2Controller {
    @FXML
    private javafx.scene.shape.Circle myCircle;
    @FXML
    private Circle myCircle1;

    @FXML
    private Circle myCircle2;

    @FXML
    private Circle myCircle3;


    @FXML
    private void addcreerRDV(MouseEvent event)throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("existNonexist.fxml"));
        Scene scene2 = new Scene(view2);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
    @FXML
    private void addlistePatients(MouseEvent event)throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("listePatients.fxml"));
        Scene scene2 = new Scene(view2);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
    @FXML
    private void addlisteRDVs(MouseEvent event)throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("listeRDVs.fxml"));
        Scene scene2 = new Scene(view2);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
    @FXML
    private void addtodayRDVs(MouseEvent event)throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("todayRDVs.fxml"));
        Scene scene2 = new Scene(view2);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void initialize() {
        myCircle.setStroke(Color.SEAGREEN);
        Image im = new Image("pics/creerRDV.JPG",false);
        myCircle.setFill(new ImagePattern(im));
        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        myCircle2.setStroke(Color.SEAGREEN);
        Image im2 = new Image("pics/listeRDV.JPG",false);
        myCircle2.setFill(new ImagePattern(im2));

        myCircle1.setStroke(Color.SEAGREEN);
        Image im1 = new Image("pics/listePatints.JPG",false);
        myCircle1.setFill(new ImagePattern(im1));
        myCircle1.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        myCircle3.setStroke(Color.SEAGREEN);
        Image im3 = new Image("pics/today.JPG",false);
        myCircle3.setFill(new ImagePattern(im3));
        myCircle3.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    }
}
