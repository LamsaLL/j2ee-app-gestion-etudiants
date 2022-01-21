<%--
  Created by IntelliJ IDEA.
  User: leo
  Date: 03/01/2022
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="card">
    <div class="card-header">Accueil <small>(home.jsp)</small></div>
    <div class="card-body">
        Bienvenue dans notre application de consultation des notes et absences
    </div>
    <a href="<%= application.getContextPath()%>/do/studentList" class="btn btn-primary">Naviguer</a>

</div>
