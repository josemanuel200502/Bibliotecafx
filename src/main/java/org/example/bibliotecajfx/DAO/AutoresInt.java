package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Autores;
import org.hibernate.HibernateException;

import java.util.List;

public interface AutoresInt {


        /**
         * Obtiene todos los registros de la tabla Autores.
         *
         * @return Lista con todos los autores en la base de datos.
         * @throws HibernateException en caso de error de conexión con la base de datos.
         */
        List<Autores> findAll() throws HibernateException;

        /**
         * Busca autores por nombre.
         *
         * @param nombre El nombre del autor a buscar.
         * @return Lista de autores que coincidan con el nombre.
         * @throws HibernateException en caso de error de conexión con la base de datos.
         */
        List<Autores> findAllByNombre(String nombre) throws HibernateException;

        /**
         * Busca autores por nacionalidad.
         *
         * @param nacionalidad La nacionalidad de los autores a buscar.
         * @return Lista de autores que coincidan con la nacionalidad.
         * @throws HibernateException en caso de error de conexión con la base de datos.
         */
        List<Autores> findAllByNacionalidad(String nacionalidad) throws HibernateException;

    List<Autores> findByNombre(String nombre) throws HibernateException;

    /**
         * Inserta un nuevo autor en la base de datos.
         *
         * @param autor Objeto del autor a insertar.
         * @return El autor insertado.
         * @throws HibernateException en caso de error de conexión con la base de datos.
         */
        Autores create(Autores autor) throws HibernateException;

        /**
         * Actualiza un autor existente en la base de datos.
         *
         * @param autor Objeto del autor con los datos actualizados.
         * @return El autor actualizado.
         * @throws HibernateException en caso de error de conexión con la base de datos.
         */
        Autores update(Autores autor) throws HibernateException;

        /**
         * Elimina un autor de la base de datos por su ID.
         *
         * @param id ID del autor a eliminar.
         * @return true si la eliminación fue exitosa, false en caso contrario.
         * @throws HibernateException en caso de error de conexión con la base de datos.
         */
        boolean deleteById(Long id) throws HibernateException;
    }
