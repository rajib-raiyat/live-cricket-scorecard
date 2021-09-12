package com.livecricketscorecard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LiveCricketScoreCardController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}