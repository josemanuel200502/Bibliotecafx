package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Prestamos;
import org.hibernate.HibernateException;

import java.util.List;

public interface PrestamosInt {

    /**
     * Registra un nuevo préstamo en la base de datos.
     *
     * @param prestamo Objeto préstamo a registrar.
     * @return El préstamo registrado.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    Prestamos registrarPrestamo(Prestamos prestamo) throws HibernateException;

    /**
     * Lista todos los libros que están actualmente prestados.
     *
     * @return Lista de préstamos activos.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Prestamos> listarPrestamosActivos() throws HibernateException;

    /**
     * Lista el historial de préstamos de un socio en particular.
     *
     * @param idSocio ID del socio cuyo historial se desea consultar.
     * @return Lista de todos los préstamos (activos y finalizados) del socio.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    List<Prestamos> listarHistorialPorSocio(Long idSocio) throws HibernateException;
}
