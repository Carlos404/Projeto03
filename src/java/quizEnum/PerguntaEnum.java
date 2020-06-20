
package quizEnum;

import java.util.Arrays;
import java.util.List;

public enum PerguntaEnum {
    
    PERGUNTA_1("Quem foi Oskar Schindler?"),
    PERGUNTA_2("O que foi o dia D?"),
    PERGUNTA_3("O que houve no dia 6 de junho de 1944?"),
    PERGUNTA_4("Em que governo Brasília foi construída?"),
    PERGUNTA_5("O que é jQuery?"),
    PERGUNTA_6("Qual foi o maior conquistador da história?"),
    PERGUNTA_7("O que é Spring Boot?"),
    PERGUNTA_8("Qual é o terceiro planeta do sistema solar em ordem de proximidade ao sol?"),
    PERGUNTA_9("Quais são os 5 estados do Brasil que começam com a letra P?"),
    PERGUNTA_10("O que está escrito na bandeira do Brasil?");

    private final String pergunta;

    PerguntaEnum(String pergunta){
        this.pergunta = pergunta;
    }

    public static List<PerguntaEnum> getListaPerguntas(){
        List<PerguntaEnum> listaPerguntas = Arrays.asList(PerguntaEnum.values());
        return listaPerguntas;
    }
    
    public String getPergunta() {
        return pergunta;
    }
}
