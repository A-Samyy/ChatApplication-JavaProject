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
    requires CommonModule;
    requires org.jetbrains.annotations;
    requires com.zaxxer.hikari;
    opens gov.iti.jets.presentation.controllers to javafx.fxml;
    requires org.slf4j;
    requires org.slf4j.jul;
    requires chatter.bot.api;
    requires jakarta.validation;
    exports gov.iti.jets.presentation.controllers;
    exports gov.iti.jets;
    opens gov.iti.jets.presistance.dtos to org.hibernate.validator;
}
