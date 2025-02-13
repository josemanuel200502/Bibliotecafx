package org.example.bibliotecajfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.bibliotecajfx.DAO.AutoresImpl;
import org.example.bibliotecajfx.Entities.Autores;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.util.List;

public class AutorController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Campos de texto para el nombre y la nacionalidad del autor
    @FXML
    private TextField nombreAutorField;
    @FXML
    private TextField nacionalidadField;

    // Instancia de la clase de implementación
    private AutoresImpl autoresImpl = new AutoresImpl(); // Crear una instancia de AutoresImpl

    // Método para volver al menú
    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecajfx/hello-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Añadir autor

    @FXML
    private void añadirAutor(ActionEvent event) {
        String nombreAutor = nombreAutorField.getText();
        String nacionalidad = nacionalidadField.getText();

        if (nombreAutor.isEmpty() || nacionalidad.isEmpty()) {
            showAlert(AlertType.WARNING, "Campos vacíos", "Por favor, ingrese todos los campos.");
        } else {
            // Crear un objeto Autor con los datos ingresados
            Autores nuevoAutor = new Autores(nombreAutor, nacionalidad);

            // Crear una instancia de AutoresImpl para llamar al método no estático
            AutoresImpl autoresImpl = new AutoresImpl();

            // Llamar al método addAutor pasando el objeto autor
            try {
                Autores autorAgregado = autoresImpl.addAutor(nuevoAutor);
                if (autorAgregado != null) {
                    showAlert(AlertType.INFORMATION, "Éxito", "Autor añadido exitosamente.");
                } else {
                    showAlert(AlertType.ERROR, "Error", "No se pudo añadir el autor.");
                }
            } catch (HibernateException e) {
                showAlert(AlertType.ERROR, "Error", "Hubo un problema al añadir el autor.");
            }
        }
    }



    @FXML
    private void modificarAutor(ActionEvent event) {
        String nombreAutor = nombreAutorField.getText();
        String nuevaNacionalidad = nacionalidadField.getText();

        if (nombreAutor.isEmpty() || nuevaNacionalidad.isEmpty()) {
            showAlert(AlertType.WARNING, "Campos vacíos", "Por favor, ingrese todos los campos.");
        } else {
            // Crear un objeto Autor con los datos ingresados
            Autores autorModificado = new Autores(nombreAutor, nuevaNacionalidad);

            // Crear una instancia de AutoresImpl para llamar al método no estático
            AutoresImpl autoresImpl = new AutoresImpl();

            // Llamar al método updateAutor pasando el objeto autor modificado
            try {
                Autores autorActualizado = autoresImpl.updateAutor(autorModificado);
                if (autorActualizado != null) {
                    showAlert(AlertType.INFORMATION, "Éxito", "Autor modificado exitosamente.");
                } else {
                    showAlert(AlertType.ERROR, "Error", "No se pudo modificar el autor.");
                }
            } catch (HibernateException e) {
                showAlert(AlertType.ERROR, "Error", "Hubo un problema al modificar el autor.");
            }
        }
    }

    // Eliminar autor
    @FXML
    private void eliminarAutor(ActionEvent event) {
        String nombreAutor = nombreAutorField.getText();

        if (nombreAutor.isEmpty()) {
            showAlert(AlertType.WARNING, "Campo vacío", "Por favor, ingrese el nombre del autor.");
        } else {
            // Llamada al método de instancia
            boolean eliminado = autoresImpl.deleteAutor(nombreAutor);
            if (eliminado) {
                showAlert(AlertType.INFORMATION, "Éxito", "Autor eliminado exitosamente.");
            } else {
                showAlert(AlertType.ERROR, "Error", "No se pudo eliminar el autor.");
            }
        }
    }

    // Buscar autor por nombre
    @FXML
    private void buscarAutor(ActionEvent event) {
        String nombreAutor = nombreAutorField.getText();

        if (nombreAutor.isEmpty()) {
            showAlert(AlertType.WARNING, "Campo vacío", "Por favor, ingrese el nombre del autor.");
        } else {
            // Llamada al método de instancia
            List<Autores> autores = autoresImpl.findByNombre(nombreAutor);
            if (autores != null && !autores.isEmpty()) {
                // Muestra los resultados de búsqueda
                StringBuilder result = new StringBuilder("Autores encontrados:\n");
                for (Autores autor : autores) {
                    result.append(autor.toString()).append("\n");
                }
                showAlert(AlertType.INFORMATION, "Resultados de búsqueda", result.toString());
            } else {
                showAlert(AlertType.ERROR, "No encontrado", "No se encontraron autores con ese nombre.");
            }
        }
    }

    // Listar todos los autores
    @FXML
    private void listarAutores(ActionEvent event) {
        // Llamada al método de instancia
        List<Autores> autores = autoresImpl.findAll();
        if (autores != null && !autores.isEmpty()) {
            // Muestra todos los autores
            StringBuilder result = new StringBuilder("Todos los autores:\n");
            for (Autores autor : autores) {
                result.append(autor.toString()).append("\n");
            }
            showAlert(AlertType.INFORMATION, "Lista de autores", result.toString());
        } else {
            showAlert(AlertType.ERROR, "No hay autores", "No se encontraron autores en la base de datos.");
        }
    }

    // Método para mostrar alertas
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
