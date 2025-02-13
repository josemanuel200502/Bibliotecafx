package org.example.bibliotecajfx.Entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Socios")
public class Socios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // Identificador único del socio

    private String nombre;
    private String direccion;
    private String numTelefono;  // Mejor usar String para evitar problemas con formatos de números

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamos> prestamos;  // Lista de préstamos asociados a este socio

    public Socios() {}

    public Socios(Integer id,String nombre, String direccion, String numTelefono) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.numTelefono = numTelefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSocio() {
        return nombre;
    }

    public void setNombreSocio(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public List<Prestamos> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamos> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public String toString() {
        return "Socio [ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion +
                ", Teléfono: " + numTelefono + "]";
    }
}
