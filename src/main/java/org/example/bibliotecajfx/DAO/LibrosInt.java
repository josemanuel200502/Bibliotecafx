package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Libros;
import org.hibernate.HibernateException;

import java.util.List;

public interface LibrosInt {

    /**
     * Obtiene todos los libros que no han sido prestados.
     *
     * @return Lista con todos los libros disponibles que no han sido prestados.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Libros> findAllAvailable() throws HibernateException;

    /**
     * Busca libros por título.
     *
     * @param titulo El título del libro a buscar.
     * @return Lista de libros que coincidan con el título.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Libros> findByTitulo(String titulo) throws HibernateException;

    /**
     * Busca libros por autor.
     *
     * @param autor El autor del libro a buscar.
     * @return Lista de libros que coincidan con el autor.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Libros> findByAutor(String autor) throws HibernateException;

    /**
     * Busca libros por ISBN.
     *
     * @param isbn El ISBN del libro a buscar.
     * @return Lista de libros que coincidan con el ISBN.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Libros> findByIsbn(String isbn) throws HibernateException;

    /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param libro El objeto libro a insertar.
     * @return El objeto libro creado.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    Libros create(Libros libro) throws HibernateException;

    /**
     * Actualiza un libro existente en la base de datos.
     *
     * @param libro El objeto libro con los datos actualizados.
     * @return El objeto libro actualizado.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    Libros update(Libros libro) throws HibernateException;

    /**
     * Elimina un libro de la base de datos por su ID.
     *
     * @param id El ID del libro a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    boolean deleteById(Long id) throws HibernateException;
}
