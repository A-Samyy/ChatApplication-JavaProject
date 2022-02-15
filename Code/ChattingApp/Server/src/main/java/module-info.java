module Server {
    requires java.naming;  
    requires mysql.connector.java;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires  org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires transitive java.sql.rowset;
    requires transitive javafx.graphics;
    requires java.rmi;
    opens gov.iti.jets.presentation.controllers to javafx.fxml;

    exports gov.iti.jets.presentation.controllers;
    exports gov.iti.jets.service;
    exports gov.iti.jets;
}
