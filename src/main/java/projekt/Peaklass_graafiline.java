package projekt;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Peaklass_graafiline extends Application {
    public void start(Stage peaLava) throws Exception {

        //// Kõikide lehtede stseenid ////

        Group juur1 = new Group();
        Scene login = new Scene(juur1, 260, 100, Color.SNOW);

        Group juur2 = new Group();
        Scene admin = new Scene(juur2, 500, 300, Color.SNOW);

        Group juur3 = new Group();
        Scene ostja = new Scene(juur3, 500, 300, Color.SNOW);

        Group juur4 = new Group();
        Scene ost = new Scene(juur4, 260, 100, Color.SNOW);


        //// Sisenemine ////

        Label silt1 = new Label("Sisenen süsteemi kui:");
        silt1.setLayoutX(70);
        silt1.setLayoutY(20);
        juur1.getChildren().add(silt1);

        Button nupp1 = new Button("Administraator");
        nupp1.setLayoutX(20);
        nupp1.setLayoutY(60);
        nupp1.setMinWidth(100);
        juur1.getChildren().add(nupp1);
        nupp1.setOnMouseClicked(event -> peaLava.setScene(admin));

        Button nupp2 = new Button("Ostja");
        nupp2.setLayoutX(140);
        nupp2.setLayoutY(60);
        nupp2.setMinWidth(100);
        juur1.getChildren().add(nupp2);
        nupp2.setOnMouseClicked(event -> peaLava.setScene(ostja));

        //// Administraator ////

        

        //// Ostja ////



        peaLava.setScene(login);
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
