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

public class ExistNonexistController {

    @FXML
    private Circle mycirclexist;

    @FXML
    private Circle cyrclNomexist;

    @FXML
    private void initialize() {
        mycirclexist.setStroke(Color.SEAGREEN);
        Image im = new Image("pics/check.JPG",false);
        mycirclexist.setFill(new ImagePattern(im));
        mycirclexist.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        cyrclNomexist.setStroke(Color.SEAGREEN);
        Image imn = new Image("pics/uncheck.PNG",false);
        cyrclNomexist.setFill(new ImagePattern(imn));
        cyrclNomexist.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));}


        @FXML
        private void addcreerRDV(MouseEvent event) throws IOException {
            Parent view2 = FXMLLoader.load(getClass().getResource("creerRDV.fxml"));
            Scene scene2 = new Scene(view2);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();
        }

    @FXML
    private void addcreerRDVexist(MouseEvent event) throws IOException {
        //Parent view2 = FXMLLoader.load(getClass().getResource("creerRDVexist.fxml"));
        Parent view2 = FXMLLoader.load(getClass().getResource("creerRDVexist.fxml"));
        Scene scene2 = new Scene(view2);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }



}

