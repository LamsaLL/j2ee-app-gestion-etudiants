<%--
  Created by IntelliJ IDEA.
  User: leo
  Date: 20/01/2022
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Project.Model.Speciality" %>

<jsp:useBean id="specialities" type="java.util.Collection<Project.Model.Speciality>" scope="request" />

<div class="container">
    <div class="row justify-content-center">
        <h1>Liste des groupes</h1>
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Nom du groupe</th>
                <th scope="col">Nombre d'élèves</th>
                <th scope="col">Détail</th>
            </tr>
            </thead>
            <tbody>
            <% for(Speciality speciality : specialities){%>
                <tr>
                    <td><%= speciality.getName()%></td>
                    <td><%= speciality.getStudents().size()%></td>
                    <td><a href="<%= application.getContextPath()%>/do/speciality?id=<%=speciality.getId()%>"><button type="button" class="btn btn-primary">Détails</button></a></td>
                </tr>
                <a href="<%= application.getContextPath()%>/do/speciality"></a>
            <% } %>
            </tbody>
        </table>
    </div>
<div/>