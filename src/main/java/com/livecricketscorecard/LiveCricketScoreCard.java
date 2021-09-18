package com.livecricketscorecard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LiveCricketScoreCard extends Application {
    @Override
    public void start(Stage PrimaryStage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(LiveCricketScoreCard.class.getResource("home_page_view.fxml")));
        Scene scene = new Scene(root, 1048, 642);
        PrimaryStage.setScene(scene);
        PrimaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}