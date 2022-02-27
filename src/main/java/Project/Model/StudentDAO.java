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
        Student student = em.find(Student.class, id);

        // Close the entity manager
        em.close();

        return student;
    }


    public static Student create(String firstName, String name, Speciality speciality) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // create new etudiant
        Student student = new Student();
        student.setName(name);
        student.setFirstName(firstName);
        if (speciality != null) {
            student.setSpeciality(speciality);
        }
        em.persist(student);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return student;
    }

    // Retourne l'ensemble des etudiants
    public static List<Student> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT a FROM Student a");

        @SuppressWarnings("unchecked")
        List<Student> list = q.getResultList();

        return list;
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
    
    public static List<Student> retrieveBySpeciality(Speciality speciality) {
        EntityManager em = GestionFactory.factory.createEntityManager();

        Query q = em.createQuery("SELECT e FROM Student e WHERE e.speciality = :speciality", Student.class);
        return (List<Student>) q.setParameter("speciality", speciality).getResultList();

    }

    public static List<Student> getStudentsWithoutSpeciality() {
        EntityManager em = GestionFactory.factory.createEntityManager();


        Query q = em.createQuery("SELECT e FROM Student e WHERE e.speciality IS NULL", Student.class);
        return (List<Student>) q.getResultList();

    }

}
