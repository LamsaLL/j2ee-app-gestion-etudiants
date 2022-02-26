package Project.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ModuleDAO {

    public static Module create(String nom) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // create
        em.getTransaction().begin();

        // create new module
        Module module = new Module();
        module.setName(nom);

        em.persist(module);
        em.getTransaction().commit();
        em.close();

        return module;
    }

    public static Module update(Module module) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (livre) à l’EntityManager courant pour réaliser la modification
        em.merge(module);
        em.getTransaction().commit();
        em.close();

        return module;
    }

    // Retourne l'ensemble des modules
    public static List<Module> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT m FROM Module m ORDER BY m.id DESC");

        @SuppressWarnings("unchecked")
        List<Module> listModule = q.getResultList();

        return listModule;
    }

    public static Module getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        Module module = em.find(Module.class, id);
        // Récupération d'un module par son id
        // ou NULL si le module n'existe pas


        // Close the entity manager
        em.close();

        return module;
    }

    //Suppression d'un group dans un module
    public static void deleteSpeciality(int idModule, int idSpeciality) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        Module module = em.find(Module.class, idModule);
        Speciality speciality = em.find(Speciality.class, idSpeciality);
        module.removeSpeciality(speciality);

        em.merge(speciality);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }

    //ajouter un speciality dans un module
    public static void addSpeciality(int idSpeciality, int idModule) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        Module module = em.find(Module.class, idModule);
        Speciality speciality = em.find(Speciality.class, idSpeciality);
        module.addSpeciality(speciality);
        em.merge(speciality);
        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }

    public static Module delete(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        Module module = em.find(Module.class, id);
        try {


            em.merge(module);
            em.remove(module);

            // Commit
            em.getTransaction().commit();

            em.close();

            return module;

        } catch (Exception e) {
            System.out.println("erreur lors de la suppression du module " + e);
        }
        // Close the entity manager

        em.close();

        return module;
    }

    public static List<Module> getBySpeciality(Speciality speciality) {

        EntityManager em = GestionFactory.factory.createEntityManager();

        Query q = em.createQuery("SELECT m FROM Module m WHERE m.specialities = :speciality", Module.class);

        return (List<Module>) q.setParameter("speciality", speciality).getResultList();
    }
}