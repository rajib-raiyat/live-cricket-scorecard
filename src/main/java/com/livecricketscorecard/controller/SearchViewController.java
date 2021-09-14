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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class SearchViewController implements Initializable {

    public Button back_to_home_page;
    ArrayList<String> words = new ArrayList<>(
            Arrays.asList("test", "dog", "Human", "Days of our life", "The best day",
                    "Friends", "Animal", "Human", "Humans", "Bear", "Life",
                    "This is some text", "Words", "222", "Bird", "Dog", "A few words",
                    "Subscribe!", "SoftwareEngineeringStudent", "You got this!!",
                    "Super Human", "Super", "Like")
    );

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;

    public static void switchToSearchScene(ActionEvent event, String search_query) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LiveCricketScoreCard.class.getResource("search_view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1048, 642);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void set_search_test(String search_query) {
        searchBar.setText(search_query);
    }

    @FXML
    void search(ActionEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(), words));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(words);
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    @FXML
    void back_to_home_page(ActionEvent event) throws IOException {
        HomePageController.switchToHomePageScene(event);
    }
}