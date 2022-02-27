<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Project.Model.Student" %>

<%--
  Created by IntelliJ IDEA.
  User: leo
  Date: 03/01/2022
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="students" type="java.util.Collection<Project.Model.Student>" scope="request" />

<div class="container">
    <div class="row justify-content-center">
        <h1>Liste des étudiants</h1>

        <!-- table qui affiche tous les etudiants présents dans la base de données -->
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Nom de l'élève</th>
                <th scope="col">Prénom de l'élève</th>
                <th scope="col">Groupe</th>
                <th scope="col"> Moyenne générale </th>
                <th scope="col"> Nombre d'absences </th>
                <th scope="col">Fiche</th>
            </tr>
            </thead>
            <tbody>
                <%for(Student student : students){%>
                    <tr>
                        <td><%= student.getName()%></td>
                        <td><%= student.getFirstName()%></td>
                        <td>
                            <% if (student.getId() != null) { %>
                                <a href="<%= application.getContextPath()%>/do/goup?id=<%=student.getSpeciality().getId()%>"><%=student.getSpeciality().getName()%></a>
                            <%  } %>
                        </td>
                        <td> <%= student.getAverageMark()%>  </td>
                        <td><%= student.getAbsences().size()%></td>
                        <td><a href="<%= application.getContextPath()%>/do/student?id=<%=student.getId()%>"><button type="button" class="btn btn-primary">Détails</button></a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>
