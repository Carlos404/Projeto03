
package objeto;


public class Resposta {

    private String codigoResposta;
    private String codigoPergunta;
    private String resposta;
    private boolean respostaVerdadeiraOuFalsa;
    
    public String getCodigoResposta() {
        return codigoResposta;
    }

    public void setCodigoResposta(String codigoResposta) {
        this.codigoResposta = codigoResposta;
    }
    
    public String getCodigoPergunta() {
        return codigoPergunta;
    }

    public void setCodigoPergunta(String codigoPergunta) {
        this.codigoPergunta = codigoPergunta;
    }
    
    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
    public boolean isRespostaVerdadeiraOuFalsa() {
        return respostaVerdadeiraOuFalsa;
    }

    public void setRespostaVerdadeiraOuFalsa(boolean respostaVerdadeiraOuFalsa) {
        this.respostaVerdadeiraOuFalsa = respostaVerdadeiraOuFalsa;
    }
    
}
