package com.livecricketscorecard.controller;

import com.livecricketscorecard.LiveCricketScoreCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePageController {
    @FXML
    private ListView<?> homepage_list_view;

    public static void switchToHomePageScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LiveCricketScoreCard.class.getResource("home_page_view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1048, 642);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void search(ActionEvent event) throws Exception {
        SearchViewController.switchToSearchScene(event);
    }

    @FXML
    void initialize() {
        assert homepage_list_view != null : "fx:id=\"homepage_list_view\" was not injected: check your FXML file 'home_page_view.fxml'.";
    }
}
