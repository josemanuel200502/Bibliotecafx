package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Autores;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AutoresImpl implements AutoresInt {

    private Session session;

    /**
     * Constructor de la Clase AutoresImpl
     *
     * @param session La session de Hibernate que se utilizará para realizar las operaciones.
     */


    public AutoresImpl(Session session) {
        this.session = session;
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Autores.class)
                .buildSessionFactory();
    }

    /**
     * Obtiene todos los registros de la tabla Animales.
     *
     * @return Una lista con todos los animales en la base de datos.
     * @throws HibernateException Si ocurre un error al realizar la consulta.
     */
    @Override
    public List<Autores> findAll() throws HibernateException {
        try {
            return session.createQuery("FROM Autores", Autores.class).getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar autores.", e);
        }
    }

    /**
     * Busca autores por nombre.
     *
     * @param nombre El nombre del autor a buscar.
     * @return Lista de autores que coincidan con el nombre.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public List<Autores> findAllByNombre(String nombre) throws HibernateException {

        return List.of();
    }

    /**
     * Busca autores por nacionalidad.
     *
     * @param nacionalidad La nacionalidad de los autores a buscar.
     * @return Lista de autores que coincidan con la nacionalidad.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public List<Autores> findAllByNacionalidad(String nacionalidad) throws HibernateException {
        return List.of();
    }


    /**
     * Busca un autor por su nombre.
     *
     * @param nombre El nombre del autor a buscar.
     * @return Una lista con los autores que coincidan con el nombre.
     * @throws HibernateException Si ocurre un error al realizar la consulta.
     */
    @Override
    public List<Autores> findByNombre(String nombre) throws HibernateException {
        try {
            return session.createQuery("FROM Autores WHERE nombre LIKE :nombre", Autores.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar autores por nombre.", e);
        }
    }

    /**
     * Crea un nuevo autor en la base de datos.
     *
     * @param autor El objeto Autores a insertar.
     * @return El objeto Autores creado.
     * @throws HibernateException Si ocurre un error al realizar la operación.
     */
    @Override
    public Autores create(Autores autor) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
            return autor;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al crear el autor.", e);
        }
    }

    /**
     * Actualiza la información de un autor existente en la base de datos.
     *
     * @param autor El objeto Autores con los datos actualizados.
     * @return El objeto Autores actualizado.
     * @throws HibernateException Si ocurre un error al realizar la operación.
     */
    @Override
    public Autores update(Autores autor) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(autor);
            transaction.commit();
            return autor;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error actualizando el autor.", e);
        }
    }

    /**
     * Elimina un autor de la base de datos por su ID.
     *
     * @param id El ID del autor a eliminar.
     * @return {@code true} si el autor fue eliminado, {@code false} si no se encontró.
     * @throws HibernateException Si ocurre un error al realizar la operación.
     */
    @Override
    public boolean deleteById(Long id) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Autores autor = session.get(Autores.class, id);
            if (autor != null) {
                session.delete(autor);
                transaction.commit();
                return true;
            }
            return false;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al borrar el autor.", e);
        }
    }
}
