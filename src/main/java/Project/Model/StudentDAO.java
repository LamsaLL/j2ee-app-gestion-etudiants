package Project.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class StudentDAO {

    public static Student getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        em.getTransaction().begin();
        //
        Student etu = em.find(Student.class, id);
        // etu est maintenant un objet de la classe Etudiant
        // ou NULL si l'étudiant n'existe pas


        // Close the entity manager
        em.close();

        return etu;
    }


    public static Student create(String prenom, String nom, Group group) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // create new etudiant
        Student etudiant = new Student();
        etudiant.setName(prenom);
        etudiant.setFirstName(nom);
        if (group != null) {
            etudiant.setGroup(group);
        }
        em.persist(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return etudiant;
    }

    public static void update(Student etudiant) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (etudiant) à l’EntityManager courant pour réaliser la modification
        em.merge(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }


    public static void remove(Student etudiant) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Retrouver l'entité persistante et ses liens avec d'autres entités en vue de la suppression
        etudiant = em.find(Student.class, etudiant.getId());
        em.remove(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        // if EclipseLink cache enable -->
        // GestionFactory.factory.getCache().evictAll();
    }

    public static void delete(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        //
        em.createQuery("DELETE FROM Student AS e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        // if EclipseLink cache enable -->
        // GestionFactory.factory.getCache().evictAll();
    }

    public static int removeAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // RemoveAll
        int deletedCount = em.createQuery("DELETE FROM Student").executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return deletedCount;
    }

    // Retourne l'ensemble des etudiants
    public static List<Student> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        em.getTransaction().begin();

        // Recherche
        Query q = em.createQuery("SELECT e FROM Student e ORDER BY e.id DESC");

        @SuppressWarnings("unchecked")
        List<Student> listStudent = q.getResultList();

        em.close();

        return listStudent;
    }


    public static List<Student> retrieveByGroup(Group group) {
        EntityManager em = GestionFactory.factory.createEntityManager();

        Query q = em.createQuery("SELECT e FROM Student e WHERE e.group = :group", Student.class);
        return (List<Student>) q.setParameter("group", group).getResultList();

    }

    public static List<Student> getStudentsWithoutGroup() {
        EntityManager em = GestionFactory.factory.createEntityManager();


        Query q = em.createQuery("SELECT e FROM Student e WHERE e.group IS NULL", Student.class);
        return (List<Student>) q.getResultList();

    }

}
