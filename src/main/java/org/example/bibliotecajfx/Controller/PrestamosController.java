package org.example.bibliotecajfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import org.example.bibliotecajfx.DAO.*;
import org.example.bibliotecajfx.Entities.Prestamos;
import org.example.bibliotecajfx.Entities.Libros;
import org.example.bibliotecajfx.Entities.Socios;
import org.example.bibliotecajfx.Util.HibernateUtil;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PrestamosController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Instancia de la clase PrestamosImpl para manejar las operaciones
    private PrestamosImpl prestamosImpl;

    // Campos en la interfaz gráfica
    @FXML
    private TextField libroIdField;  // Campo para el ID del libro
    @FXML
    private TextField socioIdField;  // Campo para el ID del socio
    @FXML
    private DatePicker fechaPrestamoPicker;  // Campo para la fecha de préstamo
    @FXML
    private DatePicker fechaDevolucionPicker;  // Campo para la fecha de devolución

    public PrestamosController() {
        // Inicializamos el DAO de préstamos con la sesión actual de Hibernate
        this.prestamosImpl = new PrestamosImpl(HibernateUtil.getSession());
    }

    // Método para volver al menú principal
    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecajfx/hello-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void registrarPrestamo(ActionEvent event) {
        try {
            // Obtenemos los datos del formulario
            String libroId = libroIdField.getText();  // ISBN es de tipo String
            Integer socioId = Integer.parseInt(socioIdField.getText());  // El ID del socio es un Integer
            LocalDate fechaPrestamo = fechaPrestamoPicker.getValue();
            LocalDate fechaDevolucion = fechaDevolucionPicker.getValue();

            // Crear una instancia de los DAO (SociosImpl y LibrosImpl)
            SociosInt sociosImpl = new SociosImpl();
            LibrosInt librosImpl = new LibrosImpl();

            // Buscar el libro usando su ISBN
            Libros libro = librosImpl.findByIsbn(libroId).stream().findFirst().orElse(null);  // Usamos el método findByIsbn
            // Buscar el socio usando su ID
            Socios socio = sociosImpl.findSocioById(socioId);  // Usamos el método findSocioById

            if (libro != null && socio != null) {
                // Crear el objeto préstamo
                Prestamos prestamo = new Prestamos();
                prestamo.setLibro(libro);  // Asociamos el libro
                prestamo.setSocio(socio);  // Asociamos el socio
                prestamo.setFechaPrestamo(fechaPrestamo);
                prestamo.setFechaDevolucion(fechaDevolucion);

                // Registrar el préstamo utilizando el DAO
                Prestamos nuevoPrestamo = prestamosImpl.registrarPrestamo(prestamo);

                // Mostrar mensaje de éxito
                if (nuevoPrestamo != null) {
                    mostrarMensaje("Préstamo registrado con éxito", "El préstamo fue registrado correctamente.");
                } else {
                    mostrarMensaje("Error al registrar préstamo", "Hubo un error al registrar el préstamo.");
                }
            } else {
                mostrarMensaje("Error", "No se encontró el libro o socio.");
            }

        } catch (NumberFormatException | HibernateException e) {
            mostrarMensaje("Error", "Por favor ingresa datos válidos.");
        }
    }




    // Método para listar los préstamos activos
    @FXML
    private void listarPrestamos(ActionEvent event) {
        try {
            List<Prestamos> prestamosActivos = prestamosImpl.listarPrestamosActivos();
            if (prestamosActivos.isEmpty()) {
                mostrarMensaje("Sin préstamos activos", "No hay préstamos activos en este momento.");
            } else {
                // Aquí deberías actualizar la interfaz con los resultados de prestamosActivos
                // Como ejemplo, mostramos los resultados en un mensaje
                prestamosActivos.forEach(p -> System.out.println(p.toString()));
            }
        } catch (HibernateException e) {
            mostrarMensaje("Error", "Hubo un problema al listar los préstamos activos.");
        }
    }

    // Método para obtener el historial de préstamos de un socio
    @FXML
    private void listarHistorialPorSocio(ActionEvent event) {
        try {
            Long socioId = Long.parseLong(socioIdField.getText());  // Obtener el ID del socio desde el campo de texto
            List<Prestamos> historial = prestamosImpl.listarHistorialPorSocio(socioId);
            if (historial.isEmpty()) {
                mostrarMensaje("Sin historial", "No hay préstamos registrados para este socio.");
            } else {
                // Aquí deberías mostrar el historial en la interfaz, como en una tabla o lista
                historial.forEach(p -> System.out.println(p.toString()));
            }
        } catch (NumberFormatException | HibernateException e) {
            mostrarMensaje("Error", "Hubo un problema al obtener el historial de préstamos.");
        }
    }

    // Método para mostrar mensajes emergentes
    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
