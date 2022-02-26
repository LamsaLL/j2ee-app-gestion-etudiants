package Project.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class SpecialityDAO {

    public static Speciality create(String name) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // create
        em.getTransaction().begin();

        // create new Group
        Speciality speciality = new Speciality();
        speciality.setName(name);

        em.persist(speciality);
        em.getTransaction().commit();
        em.close();

        return speciality;
    }

    // Retourne l'ensemble des absences
    public static List<Speciality> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT g FROM Speciality g");

        @SuppressWarnings("unchecked")
        List<Speciality> list = q.getResultList();

        return list;
    }

    public static Speciality update(Speciality speciality) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (livre) à l’EntityManager courant pour réaliser la modification
        em.merge(speciality);
        em.getTransaction().commit();
        em.close();

        return speciality;
    }

    public static Speciality delete(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        Speciality speciality = em.find(Speciality.class, id);
        em.merge(speciality);
        em.remove(speciality);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return speciality;
    }

//    public static void deleteModule(int idModule, int idGroup) {
//
//        EntityManager em = GestionFactory.factory.createEntityManager();
//        //
//        em.getTransaction().begin();
//
//        Module module = em.find(Module.class, idModule);
//        Group Group = em.find(Group.class, idGroup);
//        speciality.removeModule(module);
//
//        em.merge(speciality);
//
//        // Commit
//        em.getTransaction().commit();
//
//        // Close the entity manager
//        em.close();
//
//    }

    public static void deleteStudent(int idStudent, int idSpeciality) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();


        //tentative de suppression dun etudiant dans un specialitye, l'étudiant existe toujours hors du specialitye, il pourra être assigné à un autre specialitye
        try {
            Student student = em.find(Student.class, idStudent);
            Speciality speciality = em.find(Speciality.class, idSpeciality);
            em.merge(speciality);
            em.merge(student);
            speciality.removeStudent(student);

        } catch (Exception e) {
            System.out.println("Suppression de l'étudiant du speciality n'a pas abouti");
        }
        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }

    public static void addStudent(int idStudent, int idSpeciality) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        //ajoute un étudiant au specialitye
        Student student = em.find(Student.class, idStudent);
        Speciality speciality = em.find(Speciality.class, idSpeciality);
        speciality.addStudent(student);

        em.merge(speciality);
        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }

//    public static void addModule(int idModule, int idGroup) {
//
//        EntityManager em = GestionFactory.factory.createEntityManager();
//        //
//        em.getTransaction().begin();
//
//        //ajoute un module au speciality
//        Module module = em.find(Module.class, idModule);
//        Group speciality = em.find(Group.class, idGroup);
//        speciality.addModule(module);
//
//        em.merge(speciality);
//        // Commit
//        em.getTransaction().commit();
//
//        // Close the entity manager
//        em.close();
//
//    }

    public static Speciality getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT g FROM Speciality g WHERE g.id = :id", Speciality.class);

        Speciality speciality = (Speciality) q.setParameter("id", id).getSingleResult();

        return speciality;
    }
}
