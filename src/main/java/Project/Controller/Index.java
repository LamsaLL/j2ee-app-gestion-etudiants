package Project.Controller;

import Project.Model.*;
import Project.Model.Module;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;

public class Index extends HttpServlet {
    // URL
    private String urlLayout;
    private String urlHome;
    private String urlSpecialities;
    private String urlSpeciality;
    private String urlStudent;
    private String urlStudents;

    // INIT
    public void init() throws ServletException {
        urlLayout = getServletConfig().getInitParameter("urlLayout");
        urlHome = getServletConfig().getInitParameter("urlHome");
        urlSpecialities = getServletConfig().getInitParameter("urlSpecialities");
        urlSpeciality = getServletConfig().getInitParameter("urlSpeciality");
        urlStudents = getServletConfig().getInitParameter("urlStudents");
        urlStudent = getServletConfig().getInitParameter("urlStudent");
        GestionFactory.open();

        if ((AbsenceDAO.getAll().size() == 0)
                && (SpecialityDAO.getAll().size() == 0)
                && (MarkDAO.getAll().size() == 0)
                && (ModuleDAO.getAll().size() == 0)
                && (StudentDAO.getAll().size() == 0)){
            // Create Modules
            Module devMobile = ModuleDAO.create("Développement mobile");
            Module devFront = ModuleDAO.create("Développement front-end");
            Module anglais = ModuleDAO.create("Anglais");
            Module reseaux = ModuleDAO.create("Système et réddeaux");
            Module maths = ModuleDAO.create("Mathématiques");
            Module bdd = ModuleDAO.create("Base de données");
            Module php = ModuleDAO.create("PHP");
            Module devops = ModuleDAO.create("DEVOPS");
            Module python = ModuleDAO.create("python");

            // Create specialities
            Speciality aw = SpecialityDAO.create("AW");
            Speciality assr = SpecialityDAO.create("ASSR");
            Speciality bigData = SpecialityDAO.create("BIG-DATA");
            Speciality simo = SpecialityDAO.create("SIMO");

            // AW => Développement Mobile Développement-front Anglais
            // ASSR => Système et réseaux  Mathématiques pour la sécurité Anglais
            // BIG DATA => BASE DE DONNEES PHP DEVOPS
            // SIMO => DEVOPS BASE DE DONNEES PYTHON
            // Link Module and groups
            aw.addModule(devMobile);
            SpecialityDAO.update(aw);
            aw.addModule(devFront);
            SpecialityDAO.update(aw);
            aw.addModule(anglais);
            SpecialityDAO.update(aw);
            bigData.addModule(bdd);
            SpecialityDAO.update(bigData);
            bigData.addModule(devops);
            SpecialityDAO.update(bigData);
            bigData.addModule(php);
            SpecialityDAO.update(bigData);
            assr.addModule(reseaux);
            SpecialityDAO.update(assr);
            assr.addModule(maths);
            SpecialityDAO.update(assr);
            assr.addModule(anglais);
            SpecialityDAO.update(assr);
            simo.addModule(devops);
            SpecialityDAO.update(simo);
            simo.addModule(bdd);
            SpecialityDAO.update(simo);
            simo.addModule(python);
            SpecialityDAO.update(simo);

            // Create students
            Student poutou = StudentDAO.create("Philippe", "Poutou", aw);
            Student lepen = StudentDAO.create("Marine", "Lepen", simo);
            Student macron = StudentDAO.create("Emmanuel", "Macron", aw);
            Student zemmour = StudentDAO.create("Eric", "Zemmour", bigData);
            Student lassale = StudentDAO.create("Jean", "Lassale", assr);

            // Create marks
            MarkDAO.create(15, poutou.getId(), devFront.getId());
            MarkDAO.create(14, poutou.getId(), anglais.getId());
            MarkDAO.create(16, lepen.getId(), devops.getId());
            MarkDAO.create(9, lepen.getId(), bdd.getId());
            MarkDAO.create(11, lepen.getId(), python.getId());
            MarkDAO.create(10, macron.getId(), devMobile.getId());
            MarkDAO.create(19, macron.getId(), devFront.getId());
            MarkDAO.create(8, zemmour.getId(), bdd.getId());
            MarkDAO.create(16, zemmour.getId(), devops.getId());
            MarkDAO.create(13, zemmour.getId(), php.getId());
            MarkDAO.create(18, lassale.getId(), reseaux.getId());
            MarkDAO.create(12, lassale.getId(), maths.getId());
            MarkDAO.create(5, lassale.getId(), anglais.getId());

            // Create Absences
            AbsenceDAO.create(LocalDateTime.of(2022, 1, 28, 8, 0),
                    LocalDateTime.of(2022, 1, 28, 12, 0), false, poutou.getId());
            AbsenceDAO.create(LocalDateTime.of(2021, 11, 15, 8, 0),
                    LocalDateTime.of(2021, 11, 15, 18, 0), true, zemmour.getId());

        }


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

        if (action.equals("/students")) {
            doStudents(request, response);
        }

        if (action.equals("/student")) {
            doStudent(request, response);
        }

        if (action.equals("/specialities")) {
            doSpecialities(request, response);
        }

        if (action.equals("/speciality")) {
            doSpeciality(request, response);
        }


//        loadJSP(urlHome, request, response);
    }

    private void doHome(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("content", urlHome);
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
    private void doStudents(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {

        try {
            Collection<Student> students = (Collection<Student>) StudentDAO.getAll();
            request.setAttribute("students", students);
            request.setAttribute("content", urlStudents);
            loadJSP(urlLayout, request, response);
        } catch (Exception e) {
            log("Erreur: La liste des étudiants n'a pas pu être chargé correctement");
            loadJSP(urlLayout, request, response);
        }
        // Inclusion du content dans le template

    }

    // Affichage de la page de la liste des groupes des étudiants
    private void doSpecialities(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        try {
            Collection<Speciality> specialities = (Collection<Speciality>) SpecialityDAO.getAll();
            request.setAttribute("specialities", specialities);
            request.setAttribute("content", urlSpecialities);
            loadJSP(urlLayout, request, response);
        } catch (Exception e) {
            log("Erreur: La liste des groupes des étudiants n'a pas pu être chargé correctement");
            loadJSP(urlLayout, request, response);
        }
        // Inclusion du content dans le template

    }

    // Affichage de la page de la liste des absences des étudiants
    private void doSpeciality(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
//            Collection<Module> modules = (Collection<Module>) ModuleDAO.getAll();
            Speciality speciality = (Speciality) SpecialityDAO.getById(Integer.parseInt(request.getParameter("id")));
            Collection<Student> students = (Collection<Student>) speciality.getStudents();

            request.setAttribute("speciality", speciality);
            request.setAttribute("students", students);
//            request.setAttribute("modules", modules);

            request.setAttribute("content", urlSpeciality);
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
