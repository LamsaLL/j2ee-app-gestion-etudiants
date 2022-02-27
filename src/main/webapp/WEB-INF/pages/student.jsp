<%@ page import="Project.Model.Student" %>
<%@ page import="Project.Model.Speciality" %>
<%@ page import="Project.Model.Module" %>
<%@ page import="Project.Model.Absence" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Student student = (Student) request.getAttribute("student");%>
<div class="container">

        <h2 class="card-title"><%= student.getName()%> <%= student.getFirstName() %></h2>
        <p >
          <% if (student.getSpeciality() != null) { %>
          Spécialité <strong><%=student.getSpeciality().getName()%></strong>
          <% } %>
        </p>

    <!-- table des absences -->
    <% List<Absence> absences = student.getAbsences();%>
    <table class="table table-bordered">
      <thead class="thead-dark">
      <tr>
        <th scope="col">Absence</th>
        <th scope="col">Début</th>
        <th scope="col">Fin</th>
        <th scope="col">Justifié</th>
        <th scope="col">Actions</th>
      </tr>
      </thead>
      <tbody>
      <%  int ind= 0;
        for(Absence absence : absences) {%>
      <tr>
        <th scope="row">#</th>
        <td class="cellDebut"><%=absence.getStart()%></td>
        <td class="cellFin"><%=absence.getEnd()%></td>
        <td class="cellJustifie"><% if(absence.isJustified()) { %>oui<% } else { %>non<% } %></td>
        <td><button class="btn btn-danger" onClick="deleteAbsence(<%=absence.getId()%>, this, <%=ind%>)">supprimer</button><button onClick="updateAbsence(<%=absence.getId()%>, this)" class="btn btn-primary">Modifier</button></td>
      </tr>
      <%ind++; } %>
      <tr>
        <th scope="row">Nouveau :</th>
        <form method="post" action="<%= application.getContextPath()%>/absence/create">
          <td><input type="datetime-local" name="dateDebut" placeholder="Date de début" required/></td>
          <td><input type="datetime-local" name="dateFin" placeholder="Date de fin" required/></td>
          <td><input type="checkbox" name="justifie" value="true"/></td>
          <input type="hidden" name="idStudent" value="<%= student.getId()%>"/>
          <td><input type="submit" class="btn btn-success" value="Ajouter"/></td>
        </form>
      </tr>
      </tbody>
    </table>

    <!-- Table des notes -->
    <% if (student.getSpeciality() != null) { %>
      <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
          <th scope="col">Module</th>
          <th scope="col">Note</th>
        </tr>
        </thead>
        <tbody>

        <% Speciality speciality = student.getSpeciality();
          for(Module module : student.getSpeciality().getModules()) {%>
            <tr>
              <td><%=module.getName()%></td>
              <%if (student.getMarkByModule(module) != null) {%>
                <td id="<%=student.getId()%><%=module.getId()%>" onClick="editNote(<%=student.getId()%>,<%=module.getId()%>)"><%=student.getMarkByModule(module).getValue()%></td>
              <%} else {%>
                <td id="<%=student.getId()%><%=module.getId()%>" onClick="editNote(<%=student.getId()%>,<%=module.getId()%>)"></td>
              <%}%>
            </tr>
          <%}
          %>
        </tbody>
      </table>
    <%} else {%>
        <p>L'étudiant n'a pas de specialitées</p>
    <% } %>
</div>


