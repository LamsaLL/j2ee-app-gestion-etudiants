package Project.Model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.HashMap;

public class GestionFactory {

    private static final String PERSISTENCE_UNIT_NAME = "Projet_JPA_MYSQL_DIST";

    // Factory pour la création d'EntityManager (gestion des transactions)
    public static EntityManagerFactory factory;

    // Creation de la factory
    public static void open() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    // Fermeture de la factory
    public static void close() {
        factory.close();
    }
}
