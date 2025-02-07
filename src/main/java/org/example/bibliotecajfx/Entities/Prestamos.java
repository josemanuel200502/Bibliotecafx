package org.example.bibliotecajfx.Entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Prestamos")
public class Prestamos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del préstamo

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn_libro", referencedColumnName = "ISBN")
    private Libros libro;  // Relación con la entidad Libros

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id")
    private Socios socio;  // Relación con la entidad Socios

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamos() {}

    public Prestamos(Libros libro, Socios socio, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    public Socios getSocio() {
        return socio;
    }

    public void setSocio(Socios socio) {
        this.socio = socio;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Préstamo [Libro: " + libro.getTitulo() + ", Socio: " + getSocio().getNombreSocio() +
                ", Fecha Préstamo: " + fechaPrestamo + ", Fecha Devolución: " + fechaDevolucion + "]";
    }
}
