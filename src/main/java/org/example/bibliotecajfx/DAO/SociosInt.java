package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Socios;
import org.hibernate.HibernateException;

import java.util.List;

public interface SociosInt {

    Socios findSocioById(Integer id) throws HibernateException;

    /**
     * Añade un nuevo socio a la base de datos.
     *
     * @param socio Objeto Socios a registrar.
     * @return El socio registrado.
     * @throws HibernateException en caso de error con la base de datos.
     */
    Socios agregarSocio(Socios socio) throws HibernateException;

    /**
     * Modifica la información de un socio existente.
     *
     * @param socio Objeto Socios con la información actualizada.
     * @return El socio modificado.
     * @throws HibernateException en caso de error con la base de datos.
     */
    Socios modificarSocio(Socios socio) throws HibernateException;

    /**
     * Elimina un socio de la base de datos.
     *
     * @param idSocio ID del socio a eliminar.
     * @throws HibernateException en caso de error con la base de datos.
     */
    void eliminarSocio(Long idSocio) throws HibernateException;

    /**
     * Busca socios por nombre o número de teléfono.
     *
     * @param criterio Nombre o número de teléfono del socio.
     * @return Lista de socios que coinciden con el criterio.
     * @throws HibernateException en caso de error con la base de datos.
     */
    List<Socios> buscarSocios(String criterio) throws HibernateException;

    /**
     * Lista todos los socios registrados en la biblioteca.
     *
     * @return Lista de todos los socios.
     * @throws HibernateException en caso de error con la base de datos.
     */
    List<Socios> listarSocios() throws HibernateException;
}
