package com.livecricketscorecard.controller;

import com.livecricketscorecard.LiveCricketScoreCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import search.Search;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;


public class SearchViewController implements Initializable {

    @FXML
    private TextFlow search_page_result;

    @FXML
    private Button back_to_home_page;
    @FXML
    private TextField search_page_search_bar;

    public static void switchToSearchScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LiveCricketScoreCard.class.getResource("search_view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1048, 642);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void search(ActionEvent event) throws Exception {
        ArrayList<HashMap<String, Object>> search = Search.main(search_page_search_bar.getText());
    }

    @FXML
    void back_to_home_page(ActionEvent event) throws IOException {
        HomePageController.switchToHomePageScene(event);
    }

    @FXML
    void initialize() {
        assert search_page_search_bar != null : "fx:id=\"search_page_search_bar\" was not injected: check your FXML file 'search_view.fxml'.";
        assert search_page_result != null : "fx:id=\"search_page_result\" was not injected: check your FXML file 'search_view.fxml'.";
        assert back_to_home_page != null : "fx:id=\"back_to_home_page\" was not injected: check your FXML file 'search_view.fxml'.";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        search_page_result.clear();
    }
}