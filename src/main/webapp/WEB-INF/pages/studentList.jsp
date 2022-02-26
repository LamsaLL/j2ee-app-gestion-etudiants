<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%@ page import="Project.Model.Student" %>
<%@ page import="Project.Model.GestionFactory" %>

<%--
  Created by IntelliJ IDEA.
  User: leo
  Date: 03/01/2022
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean id="studentList" type="java.util.Collection<Project.Model.Student>" scope="request" />

<div class="container">
    <div class="row justify-content-center">
        <h1>Liste des étudiants</h1>
    </div>
</div>
</br>

<!-- table qui affiche tous les etudiants présents dans la base de données -->
<div class="container">
    <div class="row justify-content-center">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Nom de l'élève</th>
                <th scope="col">Groupe</th>
                <th scope="col">Fiche</th>
            </tr>
            </thead>
            <tbody>
                <%
//                    final Collection<Student> studentList = GestionFactory.getEtudiants();
                    for(Student student : studentList){%>
                <tr>
                    <td><%= student.getName()%></td>
                    <td>
                        <% if (student.getId() != null) { %>
                        <a href="<%= application.getContextPath()%>/do/goup?id=<%=student.getSpeciality().getId()%>"><%=student.getSpeciality().getName()%></a>
                        <%  } %>
                    </td>
                    <td><a href="<%= application.getContextPath()%>/do/student?id=<%=student.getId()%>"><button type="button" class="btn btn-primary">Détails</button></a></td>
                </tr>
    <%--            <a href="<%= application.getContextPath()%>/do/groupe"></a>--%>
                <% } %>
            </tbody>
        </table>
    </div>
</div>