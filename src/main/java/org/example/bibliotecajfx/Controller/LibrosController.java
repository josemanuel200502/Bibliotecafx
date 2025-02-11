package org.example.bibliotecajfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;

public class LibrosController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Campos de texto para introducir los datos del libro
    @FXML
    private TextField tituloField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField autorField;
    @FXML
    private TextField editorialField;
    @FXML
    private TextField anioField;

    // Tabla para mostrar los libros
    @FXML
    private TableView<Libro> tablaLibros;

    // Lista observable para almacenar los libros
    private ObservableList<Libro> listaLibros = FXCollections.observableArrayList();

    // Clase Libro para almacenar los datos de cada libro
    public static class Libro {
        private String titulo;
        private String isbn;
        private String autor;
        private String editorial;
        private String anio;

        public Libro(String titulo, String isbn, String autor, String editorial, String anio) {
            this.titulo = titulo;
            this.isbn = isbn;
            this.autor = autor;
            this.editorial = editorial;
            this.anio = anio;
        }

        public String getTitulo() { return titulo; }
        public String getIsbn() { return isbn; }
        public String getAutor() { return autor; }
        public String getEditorial() { return editorial; }
        public String getAnio() { return anio; }
    }

    // Método para volver al menú principal
    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Método para añadir un libro
    @FXML
    private void añadirLibro() {
        String titulo = tituloField.getText();
        String isbn = isbnField.getText();
        String autor = autorField.getText();
        String editorial = editorialField.getText();
        String anio = anioField.getText();

        // Validación de que todos los campos estén completos
        if (titulo.isEmpty() || isbn.isEmpty() || autor.isEmpty() || editorial.isEmpty() || anio.isEmpty()) {
            showAlert("Error", "Todos los campos deben ser completados.");
        } else {
            Libro libro = new Libro(titulo, isbn, autor, editorial, anio);
            listaLibros.add(libro);
            tablaLibros.setItems(listaLibros);
            clearFields();
        }
    }

    // Método para modificar un libro (basado en ISBN)
    @FXML
    private void modificarLibro() {
        String isbn = isbnField.getText();
        boolean encontrado = false;

        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equals(isbn)) {
                // Modificamos los detalles del libro
                libro = new Libro(
                        tituloField.getText(),
                        isbn,
                        autorField.getText(),
                        editorialField.getText(),
                        anioField.getText()
                );
                tablaLibros.refresh();  // Refrescamos la tabla para mostrar el libro actualizado
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            showAlert("Error", "Libro no encontrado.");
        }
        clearFields();
    }

    // Método para eliminar un libro (por ISBN)
    @FXML
    private void eliminarLibro() {
        String isbn = isbnField.getText();
        boolean encontrado = false;

        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equals(isbn)) {
                listaLibros.remove(libro);  // Eliminar el libro de la lista
                tablaLibros.setItems(listaLibros);  // Actualizamos la tabla
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            showAlert("Error", "Libro no encontrado.");
        }
        clearFields();
    }

    // Método para buscar libros (por título, ISBN o autor)
    @FXML
    private void buscarLibro() {
        String searchTerm = tituloField.getText();  // O usar otros campos como ISBN o autor
        ObservableList<Libro> filteredList = FXCollections.observableArrayList();

        for (Libro libro : listaLibros) {
            if (libro.getTitulo().contains(searchTerm) || libro.getIsbn().contains(searchTerm) || libro.getAutor().contains(searchTerm)) {
                filteredList.add(libro);
            }
        }
        tablaLibros.setItems(filteredList);
    }

    // Limpiar los campos de texto después de cada acción
    private void clearFields() {
        tituloField.clear();
        isbnField.clear();
        autorField.clear();
        editorialField.clear();
        anioField.clear();
    }

    // Mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
