module org.example.bibliotecajfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;// Agregar esta línea para que el módulo tenga acceso a Jakarta Persistence.

    opens org.example.bibliotecajfx to javafx.fxml;
    opens org.example.bibliotecajfx.Entities to org.hibernate.orm.core; // <-- Agrega esta línea
    exports org.example.bibliotecajfx;
    exports org.example.bibliotecajfx.Controller;
    opens org.example.bibliotecajfx.Controller to javafx.fxml;

}
