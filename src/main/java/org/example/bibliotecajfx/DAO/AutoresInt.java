package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Autores;
import org.hibernate.HibernateException;

import java.util.List;

public interface AutoresInt {


    // Añadir un nuevo autor
    Autores addAutor(Autores autor) throws HibernateException;

    // Modificar la información de un autor
    Autores updateAutor(Autores autor) throws HibernateException;

    // Eliminar un autor por su nombre
    boolean deleteAutor(String nombreAutor) throws HibernateException;

    // Buscar autores por nombre (con coincidencias parciales)
    List<Autores> findByNombre(String nombre) throws HibernateException;

    // Listar todos los autores
    List<Autores> findAll() throws HibernateException;
}