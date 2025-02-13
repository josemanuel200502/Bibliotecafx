package org.example.bibliotecajfx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import org.example.bibliotecajfx.DAO.SociosImpl;
import org.example.bibliotecajfx.Entities.Socios;
import org.example.bibliotecajfx.Util.HibernateUtil;


public class SociosController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nombreSocio;
    @FXML
    private TextField direccionSocio;
    @FXML
    private TextField telefonoSocio;

    // Campos para modificar un socio existente
    @FXML
    private TextField idSocioModificar;
    @FXML
    private TextField nuevoNombreSocio;
    @FXML
    private TextField nuevaDireccionSocio;
    @FXML
    private TextField nuevoTelefonoSocio;

    // Campo para eliminar un socio
    @FXML
    private TextField idSocioEliminar;

    // Campo para buscar un socio
    @FXML
    private TextField criterioBusqueda;

    // Campo para mostrar la lista de socios
    @FXML
    private TableView<Socios> tableViewSocios;
    @FXML
    private TableColumn<Socios, Integer> colId;
    @FXML
    private TableColumn<Socios, String> colNombre;
    @FXML
    private TableColumn<Socios, String> colDireccion;
    @FXML
    private TableColumn<Socios, String> colTelefono;

    private SociosImpl sociosImpl;



    // Método para listar todos los socios
    @FXML
    private void listarSocios(ActionEvent event) {
        // Obtener la lista de socios desde la base de datos
        List<Socios> listaSocios = sociosImpl.listarSocios();

        // Convertir la lista a un ObservableList para usar en el TableView
        ObservableList<Socios> observableSocios = FXCollections.observableArrayList(listaSocios);

        // Asignar la lista de socios al TableView
        tableViewSocios.setItems(observableSocios);

        // Configurar las columnas del TableView
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreSocio"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionSocio"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("numTelefono"));
    }



    // Añadir socio
    public void añadirSocio(ActionEvent event) {


    }


    @FXML
    private void modificarSocio(ActionEvent event) {
        try {
            // Convertir la ID a Integer
            Integer idSocio = Integer.parseInt(idSocioModificar.getText());
            String nuevoNombre = nuevoNombreSocio.getText();
            String nuevaDireccion = nuevaDireccionSocio.getText();
            String nuevoTelefono = nuevoTelefonoSocio.getText();

            // Validar que los campos no estén vacíos
            if (nuevoNombre.isEmpty() || nuevaDireccion.isEmpty() || nuevoTelefono.isEmpty()) {
                // Mostrar mensaje de error (por ejemplo, un alert o en un Label)
                return;
            }

            // Crear un nuevo objeto Socios con los datos modificados
            Socios socioModificado = new Socios(idSocio, nuevoNombre, nuevaDireccion, nuevoTelefono);
            // Llamar a la implementación para modificar el socio en la base de datos
            sociosImpl.modificarSocio(socioModificado);
        } catch (NumberFormatException e) {
            // Mostrar mensaje de error si el ID no es válido
            System.out.println("Error: El ID del socio no es válido.");
        }

        // Limpiar los campos después de realizar la modificación
        limpiarCampos();
    }
    @FXML
    private void eliminarSocio(ActionEvent event) {
        Long idSocio = Long.parseLong(idSocioEliminar.getText());
        sociosImpl.eliminarSocio(idSocio);

        // Limpiar el campo de ID
        idSocioEliminar.clear();
    }



    @FXML
    private void buscarSocio(ActionEvent event) {
        String criterio = criterioBusqueda.getText();
        List<Socios> sociosEncontrados = sociosImpl.buscarSocios(criterio);

        // Convertir la lista a ObservableList
        ObservableList<Socios> observableSocios = FXCollections.observableArrayList(sociosEncontrados);

        // Asignar los socios encontrados al TableView
        tableViewSocios.setItems(observableSocios);
    }


    private void limpiarCampos() {
        nombreSocio.clear();
        direccionSocio.clear();
        telefonoSocio.clear();
        idSocioModificar.clear();
        nuevoNombreSocio.clear();
        nuevaDireccionSocio.clear();
        nuevoTelefonoSocio.clear();
        idSocioEliminar.clear();
    }

    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecajfx/hello-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
