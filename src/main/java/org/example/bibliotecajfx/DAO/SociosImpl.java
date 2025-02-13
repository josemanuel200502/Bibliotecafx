package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Socios;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SociosImpl implements SociosInt {

    private Session session;




    /**
     * Busca un socio por su ID.
     *
     * @param id El ID del socio a buscar.
     * @return El socio que coincide con el ID o null si no se encuentra.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    public Socios findSocioById(Integer id) throws HibernateException {
        try {
            // Creamos la consulta HQL para buscar el socio por ID
            Socios socio = session.get(Socios.class, id);  // Utilizamos session.get para buscar por ID

            // Si el socio es encontrado, lo devolvemos
            return socio;
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar socio por ID.", e);
        }
    }

    /**
     * Añade un nuevo socio a la base de datos.
     *
     * @param socio Objeto Socios a registrar.
     * @return El socio registrado.
     * @throws HibernateException en caso de error con la base de datos.
     */
    @Override
    public Socios agregarSocio(Socios socio) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(socio);
            transaction.commit();
            return socio;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al agregar el socio.", e);
        }
    }

    /**
     * Modifica la información de un socio existente.
     *
     * @param socio Objeto Socios con la información actualizada.
     * @return El socio modificado.
     * @throws HibernateException en caso de error con la base de datos.
     */
    @Override
    public Socios modificarSocio(Socios socio) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(socio);
            transaction.commit();
            return socio;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al modificar el socio.", e);
        }
    }

    /**
     * Elimina un socio de la base de datos.
     *
     * @param idSocio ID del socio a eliminar.
     * @throws HibernateException en caso de error con la base de datos.
     */
    @Override
    public void eliminarSocio(Long idSocio) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Socios socio = session.get(Socios.class, idSocio);
            if (socio != null) {
                session.delete(socio);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al eliminar el socio.", e);
        }
    }

    /**
     * Busca socios por nombre o número de teléfono.
     *
     * @param criterio Nombre o número de teléfono del socio.
     * @return Lista de socios que coinciden con el criterio.
     * @throws HibernateException en caso de error con la base de datos.
     */
    @Override
    public List<Socios> buscarSocios(String criterio) throws HibernateException {
        try {
            return session.createQuery("FROM Socios WHERE nombre LIKE :criterio OR telefono LIKE :criterio", Socios.class)
                    .setParameter("criterio", "%" + criterio + "%")
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar socios.", e);
        }
    }

    /**
     * Lista todos los socios registrados en la biblioteca.
     *
     * @return Lista de todos los socios.
     * @throws HibernateException en caso de error con la base de datos.
     */
    @Override
    public List<Socios> listarSocios() throws HibernateException {
        try {
            return session.createQuery("FROM Socios", Socios.class).getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al listar los socios.", e);
        }
    }
}
