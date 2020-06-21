<%@page import="controle.ControleQuiz"%>
<%@page import="objeto.Teste"%>
<%@page import="java.util.ArrayList"%>
<%@page import="web.DbListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <%if(DbListener.exceptionMessage != null){%>
         <h3 class="text-center text-danger"><%= DbListener.exceptionMessage %></h3>
        <%}%>
        <%if(session.getAttribute("usuarioLogin") != null){%>
        <p>Sua média é: <%=new ControleUsuario().getMediaUsuario(session.getAttribute("usuarioCodigo").toString())%> </p>
        <p class="text-center" >Últimos testes realizados</p>
        <div class="container">
        
            <table border="1" class=" table table-stripped table-bordered">
                <tr>
                    <th>Código do teste</th>
                    <th>Quantidade de acertos</th>
                </tr>
            <% ArrayList<Teste> listaTestes = new ControleQuiz().getUltimosTestesRealizadosUsuario(session.getAttribute("usuarioCodigo").toString());
               for(Teste teste : listaTestes){%>
                    <tr>
                       <td><%=teste.getCodigoTeste()%></td>  
                       <td><%=teste.getResultado()%></td>  
                    </tr>
                <%}%>
            </table>
            <%}%>
        </div>
        <hr/>
        <div class="container">
        <div class="row">
        <div class="col-6 justify-content-center">
        <p class="text-center">Ranking das melhores notas</p>
        <table class="table table-stripped table-bordered">
            <tr>
                <th>Usuário</th>
                <th>Quantidade de acertos</th>
            </tr>
        <% ArrayList<Teste> ranking = new ControleQuiz().getRanking();
           for(Teste teste : ranking){%>
                <tr>
                   <td><%=teste.getNomeUsuario()%></td>  
                   <td><%=teste.getResultado()%></td>  
                </tr>
            <%}%>
        </table>
        </div>
        <div class="col-6 justify-content-center">
            <p class="text-center">Ultimos testes realizados - Geral</p>
        <table class="table table-stripped table-bordered">
            <tr>
                <th>Usuário</th>
                <th>Quantidade de acertos</th>
            </tr>
        <% ArrayList<Teste> ultimosTestes = new ControleQuiz().getUltimosTestesRealizados();
           for(Teste teste : ultimosTestes){%>
                <tr>
                   <td><%=teste.getNomeUsuario()%></td>  
                   <td><%=teste.getResultado()%></td>  
                </tr>
            <%}%>
        </table>
        </div>
        </div>
        </div>
    </body>
</html>
