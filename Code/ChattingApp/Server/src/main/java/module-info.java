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
    requires com.zaxxer.hikari;
//    requires org.slf4j;
 //   requires org.log4j;
    opens gov.iti.jets.presentation.controllers to javafx.fxml;
  //  exports org.apache.log4j to com.google.protobuf;
    exports gov.iti.jets.presentation.controllers;
    exports gov.iti.jets.service;
    exports gov.iti.jets;
}
