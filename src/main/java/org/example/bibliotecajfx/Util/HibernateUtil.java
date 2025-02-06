package org.example.bibliotecajfx.Util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Configuración de Hibernate a partir del archivo hibernate.cfg.xml
            sessionFactory = new Configuration().configure().addAnnotatedClass(org.example.bibliotecajfx.Entities.Autores.class)
                    .addAnnotatedClass(org.example.bibliotecajfx.Entities.Libros.class)
                    .addAnnotatedClass(org.example.bibliotecajfx.Entities.Prestamos.class)
                    .addAnnotatedClass(org.example.bibliotecajfx.Entities.Socios.class)
                    .buildSessionFactory();

        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Error al inicializar Hibernate: " + e.getMessage());
        }
    }

    /**
     * Obtiene una sesión de Hibernate.
     * @return Una nueva sesión de Hibernate.
     */
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    /**
     * Cierra la sesión de Hibernate.
     * @param session La sesión a cerrar.
     */
    public static void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

    /**
     * Cierra la sesión de Hibernate y la SessionFactory.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
