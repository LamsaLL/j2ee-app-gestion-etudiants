<%--
  Created by IntelliJ IDEA.
  User: leo
  Date: 27/02/2022
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Project.Model.Speciality" %>
<%@ page import="Project.Model.Module" %>
<%@ page import="Project.Model.Student" %>

<jsp:useBean id="students" type="java.util.Collection<Project.Model.Student>" scope="request" />
<jsp:useBean id="speciality" class="Project.Model.Speciality" scope="request" />


<div class="container">
    <div class="row justify-content-center">
        <h1>GROUPE : <%= speciality.getName()%></h1>
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Nom de l'étudiant</th>
                <% for(Module module : speciality.getModules()){%>
                <th scope="col"><%=module.getName()%></th>
                <%}%>
            </tr>
            </thead>
            <tbody>
            <%  for(Student student : students){%>
            <tr>
                <td><%= student.getName()%></td>
                <% for(Module module : speciality.getModules()){ %>
                    <%if (student.getMarkByModule(module) != null) {%>
                        <td class="note" id="<%=student.getId()%><%=module.getId()%>" onClick="editNote(<%=student.getId()%>,<%=module.getId()%>)"><%=student.getMarkByModule(module).getValue()%></td>
                    <%} else {%>
                      <td class="note" id="<%=student.getId()%><%=module.getId()%>" onClick="editNote(<%=student.getId()%>,<%=module.getId()%>)"></td>
                    <%}%>
                <%}%>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>