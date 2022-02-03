package Project.Controller;

import Project.*;
import Project.Model.GestionFactory;
import Project.Model.Group;
import Project.Model.Student;
import Project.Model.StudentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class Index extends HttpServlet {
    // URL
    private String urlLayout;
    private String urlIndex;
    private String urlMarks;
    private String urlAbsences;
    private String urlStudent;
    private String urlStudentList;
    private String urlHome;

    // INIT
    public void init() throws ServletException {
        urlLayout = getServletConfig().getInitParameter("urlLayout");
        urlIndex = getServletConfig().getInitParameter("urlIndex");
        urlMarks = getServletConfig().getInitParameter("urlMarks");
        urlAbsences = getServletConfig().getInitParameter("urlAbsences");
        urlStudentList = getServletConfig().getInitParameter("urlStudentList");
        urlStudent = getServletConfig().getInitParameter("urlStudent");
        urlHome = getServletConfig().getInitParameter("urlHome");

        StudentDAO.create("leo", "amsallem", new Group());
        GestionFactory.open();
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // on passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "/home";
            System.out.println("action == null");

        }

        if (action.equals("/home")) {
            doHome(request, response);
        }

        if (action.equals("/studentList")) {
            doStudentList(request, response);
        }

        if (action.equals("/student")) {
            doStudent(request, response);
        }

        if (action.equals("/marks")) {
            doMarks(request, response);
        }

        if (action.equals("/absences")) {
            doAbsences(request, response);
        }


//        loadJSP(urlIndex, request, response);
    }

    private void doHome(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("content", urlIndex);
        loadJSP(urlLayout, request, response);
    }

    private void doStudent(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        try {
            if (StudentDAO.getById(Integer.parseInt(request.getParameter("id"))) != null) {
                Student student = StudentDAO.getById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("student", student);
                request.setAttribute("content", urlStudent);
                loadJSP(urlLayout, request, response);
            } else {
                log("Erreur: l'étudiant n'existe pas");
            }
        } catch (Exception e) {
            log("Erreur: l'étudiant n'a pas pu être chargé corectement");
            loadJSP(urlLayout, request, response);
        }
    }


    // Affichage de la page de la liste des étudiants
    private void doStudentList(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {

        try {
            Collection<Student> studentList = (Collection<Student>) StudentDAO.getAll();
            request.setAttribute("studentList", studentList);
            request.setAttribute("content", urlStudentList);
            loadJSP(urlLayout, request, response);
        } catch (Exception e) {
            log("Erreur: La liste des étudiants n'a pas pu être chargé correctement");
            loadJSP(urlLayout, request, response);
        }
        // Inclusion du content dans le template

    }

    // Affichage de la page de la liste des notes des étudiants
    private void doMarks(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        try {
            Collection<Student> studentList = (Collection<Student>) StudentDAO.getAll();
            request.setAttribute("studentList", studentList);
            request.setAttribute("content", urlMarks);
            loadJSP(urlLayout, request, response);
        } catch (Exception e) {
            log("Erreur: La liste des notes des étudiants n'a pas pu être chargé correctement");
            loadJSP(urlLayout, request, response);
        }
        // Inclusion du content dans le template

    }

    // Affichage de la page de la liste des absences des étudiants
    private void doAbsences(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            Collection<Student> studentList = (Collection<Student>) StudentDAO.getAll();
            request.setAttribute("studentList", studentList);
            request.setAttribute("content", urlAbsences);
            loadJSP(urlLayout, request, response);
        } catch (Exception e) {
            log("Erreur: La liste des absences des étudiants n'a pas pu être chargé correctement");
            loadJSP(urlLayout, request, response);
        }
        // Inclusion du content dans le template

    }



    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loadJSP(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

//		L'interface RequestDispatcher permet de transférer le contrôle à une autre servlet
//		Deux méthodes possibles :
//		- forward() : donne le contrôle à une autre servlet. Annule le flux de sortie de la servlet courante
//		- include() : inclus dynamiquement une autre servlet
//			+ le contrôle est donné à une autre servlet puis revient à la servlet courante (sorte d'appel de fonction).
//			+ Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }
    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }
}
