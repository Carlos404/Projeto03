<%-- 
    Document   : cadastrar-usuario
    Created on : 19/06/2020, 21:17:15
    Author     : Igor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar usuario</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <center>
            <h1>Criar conta</h1>
            <form action="action" class="col-4">
                <p class="font-weight-bold">Usuário:</p>
                <input type="text" name="login" class="form-control">
                <p class="font-weight-bold">Senha:</p>
                <input type="password" name="senha" class="form-control">
                <input type="submit" name="cadastrar" value="Cadastrar" class="form-control mt-2 mb-2 btn btn-primary">
                <a href="index.jsp" class="form-control btn btn-primary">Voltar</a>
            </form>
            </center>
        </div>
    </body>
</html>
