module com.example.livecricketscorecard {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.livecricketscorecard to javafx.fxml;
    exports com.example.livecricketscorecard;
}