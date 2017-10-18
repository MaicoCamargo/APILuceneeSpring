<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: camargo
  Date: 18/10/17
  Time: 00:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <title>Pesquisa Lembretes Tarefas</title>
</head>
<body>
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-9">
        <form action="pesquisar" method="post">
            <div class="form-group">
                <input class="form-control" type="text" name="pesquisa" placeholder="digite o que deseja pesquisar">
                <button type="submit" class="btn btn-success">Pesquisar Ocorrência do termo</button>
            </div>
        </form>
    </div>
</div>

<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-9">
            <ul>
                <p>Registros Recuperados - ordenados por score</p>
                <c:forEach var="doc" items="${documentos}">
                <li>
                    <form action="abrir" method="post">
                    ${doc.get("filename")}
                        <input name="documento" type="hidden"value="${doc.get("fullpath")}"/>
                        <button type="submit" class="btn btn-warnning text-success">ver registro</button>
                    </form>
                </li>
                </c:forEach>
            </ul>
    </div>
</div>
</body>
</html>
