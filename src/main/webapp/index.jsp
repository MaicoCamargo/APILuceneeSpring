<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <title>trab</title>
</head>
<body>
<br>
<br>
<hr>
<br>
<h2 class="text-center">Registre sua Tarefa/Lembrete!</h2>
<c:if test="${Msg == 'ok'}">
    <p>cadastrado</p>
</c:if>
<c:if test="${Msg == 'erro'}">
    <p>cadastrado</p>
</c:if>
<div class="col-md-offset-3">
    <form action="CadastraTarefa" method="post" class="form-select-button">
        <label class="form-group">título:</label>
        <input class="form-control" type="text" name="titulo">
        <br>
        <label class="form-group">descrição:</label>
        <textarea class="form-control" placeholder="digite seu lembrete aqui" name="descricao"></textarea>
        <button type="submit" class="btn btn-success text-center">criar</button>
    </form>
</div>
<a href="pesquisando" class="text-center">Pesquisar Registros</a>
</body>
</html>