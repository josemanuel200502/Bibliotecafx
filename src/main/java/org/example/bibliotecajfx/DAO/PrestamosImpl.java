package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Prestamos;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestamosImpl implements PrestamosInt {

    private Session session;

    /**
     * Constructor de la clase PrestamosImpl.
     *
     * @param session La sesión de Hibernate que se utilizará para realizar las operaciones.
     */
    public PrestamosImpl(Session session) {
        this.session = session;
    }

    /**
     * Registra un nuevo préstamo en la base de datos.
     *
     * @param prestamo Objeto préstamo a registrar.
     * @return El préstamo registrado.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public Prestamos registrarPrestamo(Prestamos prestamo) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
            return prestamo;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al registrar el préstamo.", e);
        }
    }

    /**
     * Lista todos los libros que están actualmente prestados.
     *
     * @return Lista de préstamos activos.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public List<Prestamos> listarPrestamosActivos() throws HibernateException {
        try {
            return session.createQuery("FROM Prestamos WHERE fechaDevolucion IS NULL", Prestamos.class)
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al listar los préstamos activos.", e);
        }
    }

    /**
     * Lista el historial de préstamos de un socio en particular.
     *
     * @param idSocio ID del socio cuyo historial se desea consultar.
     * @return Lista de todos los préstamos (activos y finalizados) del socio.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public List<Prestamos> listarHistorialPorSocio(Long idSocio) throws HibernateException {
        try {
            return session.createQuery("FROM Prestamos WHERE socio.id = :idSocio", Prestamos.class)
                    .setParameter("idSocio", idSocio)
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al obtener el historial de préstamos del socio.", e);
        }
    }
}
