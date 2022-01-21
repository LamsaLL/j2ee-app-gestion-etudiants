
<%--<%@ page import="com.example.amsallel_etape3.model.Student" %>--%>
<%--<%@ page import="com.example.amsallel_etape3.model.GestionFactory" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Récupération des données --%>
<jsp:useBean id="student" class="Project.Model.Student" scope="request"/>
<%--<jsp:useBean id="nbAbsences" type="java.lang.Integer" scope="request"/>--%>
<html>
<head>
  <title>Détail de l'étudiant</title>
</head>
<body>
<h1>Détail de l'étudiant</h1>
<p>Nom: <%= student.getName() %>  </p>
<p>Prenom: <%= student.getFirstName() %>  </p>
<%--<p>Absences: <%= nbAbsences %> </p>--%>

</body>
</html>


