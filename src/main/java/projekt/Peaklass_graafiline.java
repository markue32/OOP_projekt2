package projekt;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Peaklass_graafiline extends Application {
    public void start(Stage peaLava) throws Exception {

        //// Kood ////

        ArrayList<Buss> bussid = new ArrayList<>();
        Buss b1 = new Buss(10, 7, "Tallinn - Tartu");
        //b1.bussiplaan();
        Buss b2 = new Buss(9, 7, "Tartu - Tallinn");
        Buss b3 = new Buss(14, 5, "Tallinn - Pärnu");
        bussid.add(b1);
        bussid.add(b2);
        bussid.add(b3);

        //// Kõikide lehtede stseenid ////

        Group juur1 = new Group();
        Scene login = new Scene(juur1, 260, 100, Color.SNOW);

        Group juur2 = new Group();
        Scene admin = new Scene(juur2, 1000, 500, Color.SNOW);

        Group juur3 = new Group();
        Scene ostja = new Scene(juur3, 1000, 500, Color.SNOW);

        Group juur4 = new Group();
        Scene ost = new Scene(juur4, 270, 275, Color.SNOW);


        //// Sisenemine //// 1

        Label silt11 = new Label("Sisenen süsteemi kui:");
        silt11.setLayoutX(70);
        silt11.setLayoutY(20);
        juur1.getChildren().add(silt11);

        Button nupp11 = new Button("Administraator");
        nupp11.setLayoutX(20);
        nupp11.setLayoutY(60);
        nupp11.setMinWidth(100);
        juur1.getChildren().add(nupp11);
        nupp11.setOnMouseClicked(event -> peaLava.setScene(admin));

        Button nupp12 = new Button("Ostja");
        nupp12.setLayoutX(140);
        nupp12.setLayoutY(60);
        nupp12.setMinWidth(100);
        juur1.getChildren().add(nupp12);
        nupp12.setOnMouseClicked(event -> peaLava.setScene(ostja));



        //// Ostja //// 3

        BorderPane bp3 = new BorderPane();
        HBox hb3 = new HBox();
        hb3.setPadding(new Insets(10));

        Button nupp31 = new Button("Tagasi");
        nupp31.setOnMouseClicked(event -> peaLava.setScene(login));
        hb3.getChildren().add(nupp31);
        bp3.setTop(hb3);

        VBox vb31 = new VBox();
        vb31.setPadding(new Insets(10));
        vb31.setSpacing(10);
        vb31.setMinWidth(200);
        Label silt31 = new Label("Valige buss");
        vb31.getChildren().add(silt31);

        ToggleGroup nupud3 = new ToggleGroup();
        for (Buss buss : bussid) {
            RadioButton rb = new RadioButton(buss.getLiin());
            rb.setUserData(buss.getLiin());
            rb.setToggleGroup(nupud3);
            vb31.getChildren().add(rb);
        }
        bp3.setLeft(vb31);


        VBox vb32 = new VBox();
        vb32.setPadding(new Insets(10));
        Label silt32 = new Label("Buss:");
        vb32.getChildren().add(silt32);

        nupud3.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle,
                 Toggle new_toggle) -> {
                    if (nupud3.getSelectedToggle() != null) {
                        Buss buss = bussid.get(0);
                        for (Buss b : bussid) {
                            if (b.getLiin().equals(nupud3.getSelectedToggle().getUserData().toString())) {
                                buss = b;
                                break;
                            }
                        }
                        vb32.getChildren().clear();
                        Label silt33 = new Label("Buss: " + buss.getLiin());
                        Label silt34 = new Label("Pileti hind: " + buss.getPiletiHind());
                        Label silt35 = new Label("Vabu kohti: " + buss.vabad_kohad());
                        vb32.getChildren().addAll(silt33, silt34, silt35);
                    }
                });

        bp3.setCenter(vb32);

        //// Ostja valik ////

        BorderPane piir2 = new BorderPane();
        piir2.setPadding(new Insets(10));
        bp3.setRight(piir2);

        VBox vb33 = new VBox();
        vb33.setMaxHeight(350);
        vb33.setPadding(new Insets(10));
        vb33.setSpacing(20);
        Label silt38 = new Label("Kohad");
        vb33.getChildren().add(silt38);
        piir2.setCenter(vb33);
        Button nuppKinnita = new Button("Kinnita");

        nupud3.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle,
                 Toggle new_toggle) -> {
                    if (nupud3.getSelectedToggle() != null) {
                        Buss buss = bussid.get(0);
                        for (Buss b : bussid) {
                            if (b.getLiin().equals(nupud3.getSelectedToggle().getUserData().toString())) {
                                buss = b;
                                break;
                            }
                        }
                        vb33.getChildren().clear();
                        vb33.getChildren().add(silt38);
                        List<CheckBox> kohaKogu = new ArrayList<>();

                        for (Integer integer : buss.getKohtadeNr()) {
                            CheckBox kohaNumber = new CheckBox(integer.toString());
                            kohaKogu.add(kohaNumber);
                        }

                        ListView<CheckBox> list = new ListView<>();
                        ObservableList<CheckBox> items = FXCollections.observableArrayList(kohaKogu);
                        list.setItems(items);
                        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                        vb33.getChildren().add(list);

                        piir2.setBottom(nuppKinnita);
                    }
                });


        juur3.getChildren().add(bp3);
        peaLava.widthProperty().addListener((obs, oldVal, newVal) -> vb32.setMinWidth((double)newVal - 500));

        nuppKinnita.setOnMouseClicked(event -> peaLava.setScene(ost));


        //// Administraator //// 2

        BorderPane bp2 = new BorderPane();
        HBox hb2 = new HBox();
        hb2.setPadding(new Insets(10));

        Button nupp21 = new Button("Tagasi");
        nupp21.setOnMouseClicked(event -> peaLava.setScene(login));
        hb2.getChildren().add(nupp21);
        bp2.setTop(hb2);

        VBox vb21 = new VBox();
        vb21.setPadding(new Insets(10));
        vb21.setSpacing(10);
        vb21.setMinWidth(200);
        Label silt21 = new Label("Valige buss");
        vb21.getChildren().add(silt21);

        ToggleGroup nupud2 = new ToggleGroup();
        for (Buss buss : bussid) {
            RadioButton rb = new RadioButton(buss.getLiin());
            rb.setUserData(buss.getLiin());
            rb.setToggleGroup(nupud2);
            vb21.getChildren().add(rb);
        }
        bp2.setLeft(vb21);


        VBox vb22 = new VBox();
        vb22.setPadding(new Insets(10));
        Label silt22 = new Label("Buss:");
        vb22.getChildren().add(silt22);

        nupud2.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle,
                 Toggle new_toggle) -> {
                    if (nupud2.getSelectedToggle() != null) {
                        Buss buss = bussid.get(0);
                        for (Buss b : bussid) {
                            if (b.getLiin().equals(nupud2.getSelectedToggle().getUserData().toString())) {
                                buss = b;
                                break;
                            }
                        }
                        vb22.getChildren().clear();
                        Label silt23 = new Label("Buss: " + buss.getLiin());
                        Label silt24 = new Label("Pileti hind: " + buss.getPiletiHind());
                        Label silt25 = new Label("Tulu: " + buss.tulu());
                        Label silt26 = new Label("Vabu kohti: " + buss.vabad_kohad());
                        Label silt27 = new Label("\nReisijate andmed:");
                        vb22.getChildren().addAll(silt23, silt24, silt25, silt26, silt27);

                        List<Piletiostja> ostjad = buss.getReisijad();
                        for (Piletiostja o : ostjad){
                            Label l = new Label(o.toString());
                            vb22.getChildren().add(l);
                        }
                    }
                });

        bp2.setCenter(vb22);

        VBox vb23 = new VBox();
            vb23.setPadding(new Insets(10));
            vb23.setSpacing(20);
            Label silt28 = new Label("Lisage buss");

            HBox hb21 = new HBox();
                hb21.setSpacing(5);
                VBox vb24 = new VBox();
                    vb24.setSpacing(18);
                    vb24.setPadding(new Insets(5));
                    Label silt29 = new Label("Bussi ridade arv");
                    Label silt210 = new Label("Pileti hind     ");
                    Label silt211 = new Label("Liin            ");
                    vb24.getChildren().addAll(silt29, silt210, silt211);
                VBox vb25 = new VBox();
                    vb25.setSpacing(10);
                    TextField tf21 = new TextField();
                    tf21.setPromptText("5-15");
                    TextField tf22 = new TextField();
                    tf22.setPromptText("hind €");
                    TextField tf23 = new TextField();
                    tf23.setPromptText("sihtkoht - lähtekoht");
                    vb25.getChildren().addAll(tf21, tf22, tf23);
                hb21.getChildren().addAll(vb24, vb25);

            Label silt212 = new Label();
            Button nupp22 = new Button("Lisa");
                nupp22.setOnMouseClicked(event -> {
                    if (!tf21.getText().isEmpty() && !tf22.getText().isEmpty() && !tf23.getText().isEmpty()){
                        silt212.setText("");
                        Buss buss = new Buss(Integer.parseInt(tf21.getText()), Double.parseDouble(tf22.getText()), tf23.getText());
                        bussid.add(buss);

                        RadioButton rb = new RadioButton(buss.getLiin());
                        rb.setUserData(buss.getLiin());
                        rb.setToggleGroup(nupud2);
                        vb21.getChildren().add(rb);

                        RadioButton rb2 = new RadioButton(buss.getLiin());
                        rb2.setUserData(buss.getLiin());
                        rb2.setToggleGroup(nupud3);
                        vb31.getChildren().add(rb2);

                        tf21.clear();
                        tf22.clear();
                        tf23.clear();
                    } else {
                        silt212.setText("Vigane sisend!");
                    }
                });

            vb23.getChildren().addAll(silt28, hb21, nupp22, silt212);
        bp2.setRight(vb23);

        juur2.getChildren().add(bp2);
        peaLava.widthProperty().addListener((obs, oldVal, newVal) -> vb22.setMinWidth((double)newVal - 500));


        //// Ost ////

        BorderPane bp4 = new BorderPane();
        HBox hb4 = new HBox();
        hb4.setPadding(new Insets(10));

        Button nupp41 = new Button("Tagasi");
        nupp41.setOnMouseClicked(event -> peaLava.setScene(ostja));
        hb4.getChildren().add(nupp41);
        bp4.setTop(hb4);


        VBox vb4 = new VBox();
        vb4.setPadding(new Insets(10));
        vb4.setSpacing(20);
        Label silt4 = new Label("Sisestage enda andmed:");


        HBox hb42 = new HBox();
        hb42.setSpacing(5);

        VBox vb41 = new VBox();
        vb41.setSpacing(18);
        vb41.setPadding(new Insets(5));
        Label silt41 = new Label("Eesnimi");
        Label silt42 = new Label("Perekonna nimi");
        Label silt43 = new Label("E-mail");
        vb41.getChildren().addAll(silt41, silt42, silt43);

        VBox vb42 = new VBox();
        vb42.setSpacing(10);
        TextField tf41 = new TextField();
        tf41.setPromptText("eesnimi");
        TextField tf42 = new TextField();
        tf42.setPromptText("perekonnanimi");
        TextField tf43 = new TextField();
        tf43.setPromptText("e-mail");
        vb42.getChildren().addAll(tf41, tf42, tf43);
        hb42.getChildren().addAll(vb41, vb42);

        Label silt441 = new Label();
        Button nupp441 = new Button("Kinnita ost");
        nupp441.setOnMouseClicked(event -> {
            if (!tf41.getText().isEmpty() && !tf42.getText().isEmpty() && !tf43.getText().isEmpty()){
                silt441.setText("");
                Piletiostja piletiostja = new Piletiostja(tf41.getText() + " " + tf42.getText() ,tf43.getText());

                
                tf41.clear();
                tf42.clear();
                tf43.clear();
            } else {
                silt441.setText("Vigane sisend!");
            }
        });

        vb4.getChildren().addAll(silt4, hb42, nupp441, silt441);
        bp4.setCenter(vb4);



        juur4.getChildren().add(bp4);
        peaLava.widthProperty().addListener((obs, oldVal, newVal) -> vb22.setMinWidth((double)newVal - 500));


        //// Aken ////

        peaLava.setTitle("Bussireisid");
        peaLava.setScene(login);
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
