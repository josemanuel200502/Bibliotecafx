package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Libros;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibrosImpl implements LibrosInt {

    private Session session;



    /**
     * Busca un libro por su ISBN.
     *
     * @param isbn El ISBN del libro a buscar.
     * @return El libro que coincida con el ISBN o null si no se encuentra.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public  Libros findLibroByISBN(String isbn) throws HibernateException {
        try {
            // Creamos la consulta HQL para buscar el libro por ISBN
            List<Libros> libros = session.createQuery("FROM Libros WHERE isbn = :isbn", Libros.class)
                    .setParameter("isbn", isbn)
                    .getResultList();

            // Si encontramos un libro, devolvemos el primero, si no, devolvemos null
            if (!libros.isEmpty()) {
                return libros.get(0);  // Devuelve el primer libro encontrado
            }
            return null;  // Retorna null si no se encuentra ningún libro con ese ISBN
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar libro por ISBN.", e);
        }
    }

    /**
     * Obtiene todos los libros que no han sido prestados.
     *
     * @return Lista con todos los libros disponibles que no han sido prestados.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public List<Libros> findAllAvailable() throws HibernateException {
        try {
            return session.createQuery("FROM Libros WHERE prestado = false", Libros.class)
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar los libros disponibles.", e);
        }
    }

    /**
     * Busca libros por título.
     *
     * @param titulo El título del libro a buscar.
     * @return Lista de libros que coincidan con el título.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public List<Libros> findByTitulo(String titulo) throws HibernateException {
        try {
            return session.createQuery("FROM Libros WHERE titulo LIKE :titulo", Libros.class)
                    .setParameter("titulo", "%" + titulo + "%")
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar libros por título.", e);
        }
    }

    /**
     * Busca libros por autor.
     *
     * @param autor El autor del libro a buscar.
     * @return Lista de libros que coincidan con el autor.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public List<Libros> findByAutor(String autor) throws HibernateException {
        try {
            return session.createQuery("FROM Libros WHERE autor LIKE :autor", Libros.class)
                    .setParameter("autor", "%" + autor + "%")
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar libros por autor.", e);
        }
    }

    /**
     * Busca libros por ISBN.
     *
     * @param isbn El ISBN del libro a buscar.
     * @return Lista de libros que coincidan con el ISBN.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public List<Libros> findByIsbn(String isbn) throws HibernateException {
        try {
            return session.createQuery("FROM Libros WHERE isbn = :isbn", Libros.class)
                    .setParameter("isbn", isbn)
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar libros por ISBN.", e);
        }
    }

    /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param libro El objeto libro a insertar.
     * @return El objeto libro creado.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public Libros create(Libros libro) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(libro);
            transaction.commit();
            return libro;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al crear el libro.", e);
        }
    }

    /**
     * Actualiza un libro existente en la base de datos.
     *
     * @param libro El objeto libro con los datos actualizados.
     * @return El objeto libro actualizado.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public Libros update(Libros libro) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(libro);
            transaction.commit();
            return libro;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al actualizar el libro.", e);
        }
    }

    /**
     * Elimina un libro de la base de datos por su ID.
     *
     * @param id El ID del libro a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws HibernateException en caso de error de conexión con la base de datos.
     */
    @Override
    public boolean deleteById(Long id) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Libros libro = session.get(Libros.class, id);
            if (libro != null) {
                session.delete(libro);
                transaction.commit();
                return true;
            }
            return false;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al eliminar el libro.", e);
        }
    }
}
