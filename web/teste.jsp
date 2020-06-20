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
        }catch(Exception ex){
            requestException = ex;
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Realize o teste!</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <%if(session.getAttribute("usuarioLogin") != null){%>
            <h1>Realize o teste e veja quantas questões você acerta!</h1>
            <form id="form-quiz">
                <input type="hidden" id="quantidade-acertos" value="<%=quantidadeAcertos != -1 ? quantidadeAcertos : -1%>"/>
                <input type="hidden" id="codigo-usuario"  name="codigoUsuario" value="<%=session.getAttribute("usuarioLogin")%>"/>
                <%int j = 1;
                ArrayList<Pergunta> quiz = new ControlePergunta().buscaPerguntas();
                for(int i=0; i<quiz.size(); i++){%>
                
                    <span class="font-weight-bold"><%=j%>  - </span>
                    <span class="font-weight-bold"><%=quiz.get(i).getPergunta()%></span><br/><br/>
                    
                    <%ArrayList<Resposta> alternativas = new ControleResposta().buscaAlternativas(quiz.get(i).getCodigoPergunta());
                    
                        for(Resposta alternativa : alternativas){%>
                         <input type="radio" name="resposta-<%=j%>"  value="<%=alternativa.getCodigoResposta()%>-<%=quiz.get(i).getCodigoPergunta()%>">
                        
                         <%=alternativa.getResposta()%>
                         <br/>
                    <%}%>
                    <hr/>
                    <%j++;%>
                    
                <%}%>
                
                <button type="button" class="btn btn-primary d-none" data-toggle="modal" id="botao-alert" data-target="#modal-alerta-quantidade-acertos"></button>

                <div class="modal fade" id="modal-alerta-quantidade-acertos" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                  <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Resultado</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                          <span>Você acertou <%=quantidadeAcertos%> questões do quiz</span>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="confirma-quantidade-acertos">Ok</button>
                      </div>
                    </div>
                  </div>
                </div>
                      
                <input type="hidden" name="codigos-perguntas" id="codigos-perguntas" />
                <input type="hidden" name="codigos-respostas" id="codigos-respostas" />
                <button type="button" class="btn btn-primary" id="enviar-respostas" value="enviar">Enviar</button>
                <input type="submit" name="enviar"  style="display:none" id="submitar-form" value="enviar" />
            </form>
        <%}%>
    </body>
</html>

<script>
    document.addEventListener("DOMContentLoaded", function(event) {
        aplicaClickBotaoEnviar();
        mostraQuantidadeAcertos();
    });

    function mostraQuantidadeAcertos(){
        if(document.getElementById("quantidade-acertos").value !== '-1'){
            adicionaClickConfirmaQuantidadeAcertos();
            document.getElementById("botao-alert").click();
        }
    }
    
    function adicionaClickConfirmaQuantidadeAcertos(){
        document.getElementById("confirma-quantidade-acertos").addEventListener("click", function(){
             window.location.href="index.jsp";
        });
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
        let contador=0;
        let j =0;
        for(i = 0; i < respostas.length; i++) { 
            if(respostas[i].checked) {
                teste.codigoResposta[j] = respostas[i].value.split("-")[0];
                teste.codigoPergunta[j] = respostas[i].value.split("-")[1];
                contador++;
                j++;
                
            }
        }
        if(teste.codigoResposta.length === 10){
            document.getElementById("codigos-respostas").value = teste.codigoResposta; 
            document.getElementById("codigos-perguntas").value = teste.codigoPergunta;
            document.getElementById("submitar-form").click();
        }else{
            alert("Responda todas as questões para continuar");
        }
    }

</script>