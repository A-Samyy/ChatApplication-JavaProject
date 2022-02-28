module CommonModule{
    requires java.rmi;
    requires javafx.controls;
    exports gov.iti.jets.common.dtos;
    exports gov.iti.jets.common.interfaces;
    requires jakarta.validation;
    requires org.hibernate.validator;
    requires org.hibernate.validator.cdi;
    opens gov.iti.jets.common.dtos to org.hibernate.validator;
    exports gov.iti.jets.common.hibernate;

}
