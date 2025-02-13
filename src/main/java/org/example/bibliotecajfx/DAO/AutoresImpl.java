package org.example.bibliotecajfx.DAO;

import org.example.bibliotecajfx.Entities.Autores;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutoresImpl implements AutoresInt {

    private static Session session;




    public void AutorImpl(Session session) {
        this.session = session;
    }

    public Autores addAutor(Autores autor) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(autor); // Guarda el nuevo autor
            transaction.commit();
            return autor;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al añadir el autor.", e);
        }
    }



    @Override
    public Autores updateAutor(Autores autor) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(autor); // Actualiza el autor existente
            transaction.commit();
            return autor;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al actualizar el autor.", e);
        }
    }

    @Override
    public boolean deleteAutor(String nombreAutor) throws HibernateException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Autores autor = session.get(Autores.class, nombreAutor); // Busca el autor por su nombre
            if (autor != null) {
                session.delete(autor); // Elimina el autor
                transaction.commit();
                return true;
            }
            return false;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error al eliminar el autor.", e);
        }
    }

    @Override
    public List<Autores> findByNombre(String nombre) throws HibernateException {
        try {
            return session.createQuery("FROM Autores WHERE nombreAutor LIKE :nombre", Autores.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar autores por nombre.", e);
        }
    }

    @Override
    public List<Autores> findAll() throws HibernateException {
        try {
            return session.createQuery("FROM Autores", Autores.class).getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al listar todos los autores.", e);
        }
    }
}
