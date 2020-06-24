<%@page import="controle.ControleUsuario"%>
<%@page import="objeto.Usuario"%>
<%@page import="web.DbListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

	String errorMessage = null;

if (request.getParameter("cadastrar") != null) {
    String nm_usuario = request.getParameter("nome");
    String nm_login_usuario = request.getParameter("login");
    String cd_senha_hash = request.getParameter("senha");
    try {
        ControleUsuario.setUsuario(nm_usuario, nm_login_usuario, cd_senha_hash);
        response.sendRedirect("profile.jsp");
        session.setAttribute("user.name", nm_usuario);
        session.setAttribute("user.login", nm_login_usuario);
    } catch (Exception e) {
        DbListener.exceptionMessage = e.getMessage();
    }
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar usuario</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <center>
            <h1>Criar conta</h1>
            <form method="post" class="col-4 form-group">
            <p class="font-weight-bold form-group">Nome:</p>
                <input type="text" name="nome" class="form-control">
                <p class="font-weight-bold form-group">Usuário:</p>
                <input type="text" name="login" class="form-control">
                <p class="font-weight-bold form-group">Senha:</p>
                <input type="password" name="senha" class="form-control">
                <input type="submit" name="cadastrar" class="form-control mt-2 mb-2 btn btn-primary">
                <a href="index.jsp" class="form-control btn btn-primary">Voltar</a>
            </form>
            </center>
        </div>
    </body>
</html>
