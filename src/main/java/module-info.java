module com.example.livecricketscorecard {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.java;

    opens com.livecricketscorecard to javafx.fxml;
    exports com.livecricketscorecard;
}