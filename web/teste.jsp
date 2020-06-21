<%@page import="controle.ControlePergunta"%>
<%@page import="objeto.Pergunta"%>
<%@page import="controle.ControleResposta"%>
<%@page import="objeto.Resposta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="objeto.Teste"%>
<%@page import="controle.ControleQuiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Exception requestException = null;
    int quantidadeAcertos = -1;
    Teste teste = new Teste();
    teste.setCodigoPergunta(request.getParameter("codigos-perguntas"));
    teste.setCodigoResposta(request.getParameter("codigos-respostas"));
    teste.setCodigoUsuario(request.getParameter("codigoUsuario"));
    if(request.getParameter("enviar")!=null){
        try{
            quantidadeAcertos = new ControleQuiz().getResultadoQuiz(teste);
            //response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestException = ex;
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Realize o teste!</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
        <h1>Realize o teste e veja quantas questões você acerta!</h1>
        <hr>
        <%if(session.getAttribute("usuarioLogin") != null){%>
            <form>
                <input type="hidden" id="quantidade-acertos" value="<%=quantidadeAcertos != -1 ? quantidadeAcertos : -1%>"/>
                <input type="hidden" id="codigo-usuario"  name="codigoUsuario" value="<%=session.getAttribute("usuarioLogin")%>"/>
                <%int j = 1;
                ArrayList<Pergunta> quiz = new ControlePergunta().buscaPerguntasNoBanco();
                for(int i=0; i<quiz.size(); i++){%>
                
                    <span class="font-weight-bold"><%=j%>.)</span>    
                    
                    <span class="font-weight-bold"><%=quiz.get(i).getPergunta()%></span><br/>
                    
                    <%ArrayList<Resposta> alternativas = new ControleResposta().buscaAlternativas(quiz.get(i).getCodigoPergunta());
                    
                        for(Resposta alternativa : alternativas){%>
                         <input type="radio" name="resposta-<%=j%>"  value="<%=alternativa.getCodigoResposta()%>-<%=quiz.get(i).getCodigoPergunta()%>">
                        
                         <%=alternativa.getResposta()%>
                         <br/>
                    <%}%>
                    <%j++;%>
                    <br/><br/>
                <%}%>
                <input type="hidden" name="codigos-perguntas" id="codigos-perguntas" />
                <input type="hidden" name="codigos-respostas" id="codigos-respostas" />
                <button type="button" id="enviar-respostas">Enviar</button>
                <input type="submit" name="enviar" value="Enviar"/>
            </form>
        <%}%>
        </div>
    </body>
</html>

<script>
    document.addEventListener("DOMContentLoaded", function(event) {
        aplicaClickBotaoEnviar();
        mostraQuantidadeAcertos();
    });

    function mostraQuantidadeAcertos(){
        if(document.getElementById("quantidade-acertos").value !== '-1'){
            alert(document.getElementById("quantidade-acertos").value);
        }
    }
    function aplicaClickBotaoEnviar(){
        let botaoEnviar = document.getElementById("enviar-respostas");
        
        botaoEnviar.addEventListener("click", function(){
            recuperaValoresDasAlternativas();
            }
        );
    }
    
    function recuperaValoresDasAlternativas(){
        var respostas = document.querySelectorAll('input[name^="resposta-"]'); 
        var teste = {
            codigoPergunta : [] ,
            codigoResposta : []
        };
        let j =0;
        for(i = 0; i < respostas.length; i++) { 
            if(respostas[i].checked) {
                teste.codigoResposta[j] = respostas[i].value.split("-")[0];
                teste.codigoPergunta[j] = respostas[i].value.split("-")[1];
                j++;
                
            }
        }
        document.getElementById("codigos-respostas").value = teste.codigoResposta; 
        document.getElementById("codigos-perguntas").value = teste.codigoPergunta; 
    }

</script>