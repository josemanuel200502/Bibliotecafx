package org.example.bibliotecajfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.tool.schema.Action;
import javafx.scene.Node;


import java.io.IOException;


public class HelloController {


    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private void abrirLibros(ActionEvent event) throws IOException{
        cambiarVentana(event,"autores-view.fxml");
    }

    @FXML
    private void abrirAutores(ActionEvent event)throws IOException{
        cambiarVentana(event,"autores-view.fxml");
    }

    @FXML
    private void abrirSocios(ActionEvent event) throws IOException{
        cambiarVentana(event,"socios-view.fxml");
    }

    @FXML
    private void abrirPrestamos(ActionEvent event) throws IOException{
        cambiarVentana(event,"prestamos-view.fxml");
    }

    private void cambiarVentana(ActionEvent event, String fxmlFile) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource(fxmlFile));
        root=loader.load();
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}