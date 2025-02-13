package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Prestamos;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestamosImpl implements PrestamosInt {

    private Session session;

    public PrestamosImpl(Session session) {
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
            session.save(prestamo);  // Guarda el préstamo en la base de datos
            transaction.commit();
            return prestamo;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al registrar el préstamo.", e);
        }
    }

    @Override
    public List<Prestamos> listarPrestamosActivos() throws HibernateException {
        try {
            return session.createQuery("FROM Prestamos WHERE fechaDevolucion IS NULL", Prestamos.class)
                    .getResultList();  // Devuelve préstamos cuyo campo de fechaDevolucion es NULL (activos)
        } catch (HibernateException e) {
            throw new HibernateException("Error al listar los préstamos activos.", e);
        }
    }

    @Override
    public List<Prestamos> listarHistorialPorSocio(Long idSocio) throws HibernateException {
        try {
            return session.createQuery("FROM Prestamos WHERE socio.id = :idSocio", Prestamos.class)
                    .setParameter("idSocio", idSocio)
                    .getResultList();  // Devuelve el historial completo del socio (activos y finalizados)
        } catch (HibernateException e) {
            throw new HibernateException("Error al obtener el historial de préstamos del socio.", e);
        }
    }
}
