package org.example.bibliotecajfx.Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Autores")
public class Autores implements Serializable {


    @Id
    private String nombreAutor;  // Clave primaria

    private String nacionalidad;

    public Autores() {}

    public Autores(String nombreAutor, String nacionalidad) {
        this.nombreAutor = nombreAutor;
        this.nacionalidad = nacionalidad;
    }

    // Getters y setters
    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Autor: " + nombreAutor + ", Nacionalidad: " + nacionalidad;
    }
}
