package com.livecricketscorecard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LiveCricketScoreCard extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Jihad");
        FXMLLoader fxmlLoader = new FXMLLoader(LiveCricketScoreCard.class.getResource("home_page_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1048, 642);
        stage.setTitle("EWUCrickInfo!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}