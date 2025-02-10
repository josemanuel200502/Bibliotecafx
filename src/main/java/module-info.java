module org.example.bibliotecajfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;// Agregar esta línea para que el módulo tenga acceso a Jakarta Persistence.

    opens org.example.bibliotecajfx to javafx.fxml;
    exports org.example.bibliotecajfx;

}
