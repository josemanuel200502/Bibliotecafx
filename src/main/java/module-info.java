module org.example.bibliotecajfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires java.sql;

    opens org.example.bibliotecajfx to javafx.fxml;
    exports org.example.bibliotecajfx;

    opens org.example.bibliotecajfx.Entities to org.hibernate.orm.core;
}