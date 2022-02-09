module Client {
    requires transitive javafx.controls;
    requires  javafx.fxml;
    requires  org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
     exports gov.iti.jets;
     opens gov.iti.jets.presentation.controllers to javafx.fxml;
   
}