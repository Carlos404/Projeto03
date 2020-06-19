
package quizEnum;

import java.util.Arrays;
import java.util.List;

public enum PerguntaEnum {
    
    PERGUNTA_1(0, "Quem foi Oskar Schindler?"),
    PERGUNTA_2(1, "O que foi o dia D?"),
    PERGUNTA_3(2, "O que houve no dia 6 de junho de 1944?"),
    PERGUNTA_4(3, "Em que governo Brasília foi construída?"),
    PERGUNTA_5(4, "O que é jQuery?"),
    PERGUNTA_6(5, "Qual foi o maior conquistador da história?"),
    PERGUNTA_7(6, "O que é Spring Boot?");

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
