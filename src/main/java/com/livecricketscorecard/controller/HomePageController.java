package com.livecricketscorecard.controller;

import com.livecricketscorecard.LiveCricketScoreCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePageController {

    public TextField home_page_search;

    public static void switchToHomePageScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LiveCricketScoreCard.class.getResource("home_page_view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1048, 642);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void search(ActionEvent event) throws IOException {
        if (Objects.equals(home_page_search.getText(), "")) {
            System.out.println("please enter your search.");
        } else {
            SearchViewController.switchToSearchScene(event, home_page_search.getText());
        }
    }
}
