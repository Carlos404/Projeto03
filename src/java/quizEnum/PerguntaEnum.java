
package quizEnum;

import java.util.Arrays;
import java.util.List;

public enum PerguntaEnum {
    
    PERGUNTA_1(1, "Quem foi Oskar Schindler?"),
    PERGUNTA_2(2, "O que foi o dia D?"),
    PERGUNTA_3(3, "O que houve no dia 6 de junho de 1944?"),
    PERGUNTA_4(4, "Em que governo Brasília foi construída?"),
    PERGUNTA_5(5, "O que é jQuery?"),
    PERGUNTA_6(6, "Qual foi o maior conquistador da história?"),
    PERGUNTA_7(7, "O que é Spring Boot?");

    private final int codigoPergunta;
    private final String pergunta;

    PerguntaEnum(int codigoPergunta, String pergunta){
        this.codigoPergunta = codigoPergunta;
        this.pergunta = pergunta;
    }

    public static List<PerguntaEnum> getListaPerguntas(){
        List<PerguntaEnum> listaPerguntas = Arrays.asList(PerguntaEnum.values());
        return listaPerguntas;
    }
    
     public int getCodigoPergunta() {
        return codigoPergunta;
    }

    public String getPergunta() {
        return pergunta;
    }
}
