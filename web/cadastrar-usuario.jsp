<%@page import="controle.ControleResposta"%>
<%@page import="controle.ControlePergunta"%>
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
        new ControleUsuario().setUsuario(nm_usuario, nm_login_usuario, cd_senha_hash);
        response.sendRedirect("index.jsp");
        session.setAttribute("user.name", nm_usuario);
        session.setAttribute("user.login", nm_login_usuario);
    } catch (Exception e) {
        DbListener.exceptionMessage = e.getMessage();
    }
}
%>

<%      
        new ControlePergunta().verificaSeExistePerguntasCadastradas();
	new ControleResposta().verificaSeExisteRespostasCadastradas();

	if (request.getParameter("entrar") != null) {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
                String redirectURL = "index.jsp";
		response.sendRedirect(redirectURL);
		try {
			Usuario usuario = ControleUsuario.getUsuario(login, senha);
			if (usuario == null) {
				errorMessage = "Login ou senha incorretas";
				
			} else {
				session.setAttribute("usuarioLogin", usuario.getLogin());
				session.setAttribute("usuarioCodigo", usuario.getCodigoUsuario());
				session.setAttribute("usuarioNome", usuario.getNome());
				response.sendRedirect(request.getRequestURI());
			}
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
		}
	} else if (request.getParameter("sair") != null) {
		session.removeAttribute("usuarioLogin");
		session.removeAttribute("usuarioNome");
		String redirectURL = "index.jsp";
		response.sendRedirect(redirectURL);
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
        <%@include file="WEB-INF/jspf/navbar.jspf" %>
        <div class="container">
            <center>
            <h1>Criar conta</h1>
            <form class="col-4 form-group">
            <p class="font-weight-bold form-group">Nome:</p>
                <input type="text" name="nome" class="form-control">
                <p class="font-weight-bold form-group">Usuário:</p>
                <input type="text" name="login" class="form-control">
                <p class="font-weight-bold form-group">Senha:</p>
                <input type="password" name="senha" class="form-control">
                <input type="submit" name="cadastrar" value="Cadastrar" class="form-control mt-2 mb-2 btn btn-primary"/>
                <a href="index.jsp" class="form-control btn btn-primary">Voltar</a>
            </form>
            </center>
        </div>
    </body>
</html>
