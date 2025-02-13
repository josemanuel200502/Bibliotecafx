package org.example.bibliotecajfx.Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Libros")
public class Libros implements Serializable {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String ISBN;  // Se usa el ISBN como clave primaria

    private String titulo;

    private String editorial;

    private int anyoPubli;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_nombre")
    private Autores autor;  // Cambié el tipo de String a Autores

    private boolean prestado;  // Para saber si está prestado o no

    public Libros() {}

    public Libros(Integer id,String ISBN, String titulo, Autores autor, String editorial, int anyoPubli) {

        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;  // Ahora pasamos un objeto de tipo Autor
        this.editorial = editorial;
        this.anyoPubli = anyoPubli;
        this.prestado = false;  // Por defecto, un libro nuevo no está prestado
    }

    // Getters y setters
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autores getAutor() {  // Cambié el tipo de retorno a Autores
        return autor;
    }

    public void setAutor(Autores autor) {  // Cambié el parámetro a Autores
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnyoPubli() {
        return anyoPubli;
    }

    public void setAnyoPubli(int anyoPubli) {
        this.anyoPubli = anyoPubli;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    @Override
    public String toString() {
        return "Libro: " + titulo + " (ISBN: " + ISBN + "), Autor: " + (autor != null ? autor.getNombreAutor() : "Desconocido") +
                ", Editorial: " + editorial + ", Año: " + anyoPubli +
                ", Prestado: " + (prestado ? "Sí" : "No");
    }
}
