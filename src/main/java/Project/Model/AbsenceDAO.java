package Project.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

public class AbsenceDAO {
    public static Absence create(LocalDateTime start, LocalDateTime end, Boolean justified, int studentId) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();


        em.getTransaction().begin();

        // create new absence
        Absence absence = new Absence();
        absence.setStart(start);
        absence.setEnd(end);
        absence.setJustified(justified);
        Student student = StudentDAO.getById(studentId);
        absence.setStudent(student);
        em.persist(absence);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return absence;
    }

    public static Absence delete(int idAbsence) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        //Suppression d'une absence
        Absence absence = em.find(Absence.class, idAbsence);
        Absence absence2 = em.merge(absence);
        em.remove(absence2);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return absence;
    }


    // Retourne l'ensemble des absences
    public static List<Absence> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT a FROM Absence a");

        @SuppressWarnings("unchecked")
        List<Absence> list = q.getResultList();

        return list;
    }

    public static Absence update(Absence absence) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Ouverture de la transaction
        em.getTransaction().begin();

        // Attacher une entité persistante (absence) à l’EntityManager courant  pour réaliser la modification
        em.merge(absence);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return absence;
    }

    public static Absence getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        Absence absence = em.find(Absence.class, id);
        // etu est maintenant un objet de la classe Etudiant
        // ou NULL si l'étudiant n'existe pas


        // Close the entity manager
        em.close();

        return absence;
    }
}
