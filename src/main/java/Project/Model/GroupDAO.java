package Project.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GroupDAO {
    public static Group create(String nom, List<Student> students) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // create
        em.getTransaction().begin();

        // create new Group
        Group Group = new Group();
        Group.setName(nom);

        em.persist(Group);
//
//        for (Module module : modules) {
//            Group.addModule(module);
//        }
        for (Student student : students) {
            Group.addStudent(student);
        }

        em.merge(Group);


        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return Group;
    }

    public static Group delete(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        Group Group = em.find(Group.class, id);
        em.merge(Group);
        em.remove(Group);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return Group;
    }

//    public static void deleteModule(int idModule, int idGroup) {
//
//        EntityManager em = GestionFactory.factory.createEntityManager();
//        //
//        em.getTransaction().begin();
//
//        Module module = em.find(Module.class, idModule);
//        Group Group = em.find(Group.class, idGroup);
//        group.removeModule(module);
//
//        em.merge(group);
//
//        // Commit
//        em.getTransaction().commit();
//
//        // Close the entity manager
//        em.close();
//
//    }

    public static void deleteStudent(int idStudent, int idGroup) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();


        //tentative de suppression dun etudiant dans un groupe, l'étudiant existe toujours hors du groupe, il pourra être assigné à un autre groupe
        try {
            Student student = em.find(Student.class, idStudent);
            Group group = em.find(Group.class, idGroup);
            em.merge(group);
            em.merge(student);
            group.removeStudent(student);

        } catch (Exception e) {
            System.out.println("Suppression de l'étudiant du group n'a pas abouti");
        }
        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }

    public static void addStudent(int idStudent, int idGroup) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        //ajoute un étudiant au groupe
        Student student = em.find(Student.class, idStudent);
        Group group = em.find(Group.class, idGroup);
        group.addStudent(student);

        em.merge(group);
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
//        //ajoute un module au group
//        Module module = em.find(Module.class, idModule);
//        Group group = em.find(Group.class, idGroup);
//        group.addModule(module);
//
//        em.merge(group);
//        // Commit
//        em.getTransaction().commit();
//
//        // Close the entity manager
//        em.close();
//
//    }


    public static List<Group> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Récupération de tous les groupes à l'aide d'une requête sql
        Query q = em.createQuery("SELECT g FROM Group g ORDER BY g.id DESC");

        @SuppressWarnings("unchecked")
        List<Group> listGroup = q.getResultList();

        return listGroup;
    }

    public static Group getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT g FROM Group g WHERE g.id = :id", Group.class);

        Group group = (Group) q.setParameter("id", id).getSingleResult();

        return group;
    }
}
