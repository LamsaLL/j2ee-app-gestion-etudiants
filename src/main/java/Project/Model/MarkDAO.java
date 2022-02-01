package Project.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MarkDAO {
    public static Mark update(Mark mark) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (note) à l’EntityManager courant  pour réaliser la modification
        em.merge(mark);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return mark;
    }

    public static Mark create(float value, int idStudent, int idModule) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // create new note
        Mark mark = new Mark();
        mark.setValue(value);
        Student student = StudentDAO.getById(idStudent);
        mark.setStudent(student);
//        Module module = ModuleDAO.getById(idModule);
//        mark.setModule(module);
        em.persist(mark);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return mark;
    }

    public static void deleteById(int idStudent, int idModule) {
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();
        Mark mark = MarkDAO.getByStudentAndModule(idStudent, idModule);
        Mark mark2 = em.merge(mark);
        em.remove(mark2);
        em.getTransaction().commit();

        em.close();

    }

    //Récupération d'une note par l'id de l'étudiant et du module, étant donnée qu'une note n'a pas sa propre id
    public static Mark getByStudentAndModule(int idStudent, int idModule) {

        EntityManager em = GestionFactory.factory.createEntityManager();

        Student student = StudentDAO.getById(idStudent);
//        Module module = ModuleDAO.getById(idModule);

        // Recherche
        Query q = em.createQuery("SELECT n FROM Mark n WHERE n.student = :student ", Mark.class);

        Mark mark = (Mark) q.setParameter("student", student).getSingleResult();
        return mark;
    }
}
