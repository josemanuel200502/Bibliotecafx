package org.example.bibliotecajfx;

import org.example.bibliotecajfx.Entities.Socios;
import org.example.bibliotecajfx.Entities.Libros;
import org.example.bibliotecajfx.Entities.Autores;  // Importar la clase Autor
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Configuración de Hibernate
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // Lee la configuración de hibernate.cfg.xml
                .addAnnotatedClass(Socios.class)
                .addAnnotatedClass(Libros.class)
                .addAnnotatedClass(Autores.class)  // Añadir la clase Autores
                .buildSessionFactory();

        // Crear una sesión
        Session session = factory.getCurrentSession();


        //A la hora de ejecutar el programa hay que cambiar el nomrbe del autor y el isbn
        try {
            // Crear nuevo socio (asegurate de que Socios tiene un constructor adecuado)
            Socios nuevoSocio = new Socios();
            nuevoSocio.setNombreSocio("Juan Pérez");
            nuevoSocio.setDireccion("Calle Falsa 123");
            nuevoSocio.setNumTelefono(String.valueOf(123456789));

            // Crear nuevo autor
            Autores autor = new Autores("Pepep mel", "Española");

            // Crear nuevo libro y asignar el autor
            Libros nuevoLibro = new Libros();
            nuevoLibro.setISBN("1234567891056");
            nuevoLibro.setTitulo("El Quijote");
            nuevoLibro.setAutor(autor);  // Asignar el objeto Autor
            nuevoLibro.setEditorial("Editorial A");
            nuevoLibro.setAnyoPubli(1605);

            // Iniciar una transacción
            session.beginTransaction();

            // Guardar el socio y el libro
            session.save(nuevoSocio);
            session.save(autor);  // Guardar el autor también
            session.save(nuevoLibro);

            // Commit de la transacción
            session.getTransaction().commit();

            // Mostrar los datos guardados
            System.out.println("Socio guardado: " + nuevoSocio);
            System.out.println("Autor guardado: " + autor);
            System.out.println("Libro guardado: " + nuevoLibro);

            // Ahora probamos consultar los socios desde la base de datos
            session = factory.getCurrentSession();
            session.beginTransaction();

            // Consulta para obtener todos los socios
            List<Socios> sociosList = session.createQuery("from Socios").getResultList();
            System.out.println("Lista de Socios:");
            for (Socios socio : sociosList) {
                System.out.println(socio);
            }

            // Consulta para obtener todos los libros
            List<Libros> librosList = session.createQuery("from Libros").getResultList();
            System.out.println("Lista de Libros:");
            for (Libros libro : librosList) {
                System.out.println(libro);
            }

            // Finalizar la sesión
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
