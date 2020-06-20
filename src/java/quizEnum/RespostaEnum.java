package quizEnum;

import java.util.Arrays;
import java.util.List;

public enum RespostaEnum{
    
        RESPOSTA_1_PERGUNTA_0("Empresário alemão de sucesso que ficou conhecido por encarar Adolf Hitler de frente durante a Segunda Guerra", 1, false),
        RESPOSTA_2_PERGUNTA_0("Empresário alemão que foi perseguido e levado para um campo de concentração durante a Segunda Guerra após esconder judeus em suas empresas", 1, false),
        RESPOSTA_3_PERGUNTA_0("Empresário que fazia parte do partido Nazista e que ajudou a salvar mais de mil judeus durante a Segunda Guerra", 1, true),
        RESPOSTA_4_PERGUNTA_0("Empresário alemão que financiou a pesquisa e avanço militar do regime nazista durante a segunda guerra mundial", 1, false),
        
        RESPOSTA_1_PERGUNTA_1("Foi o dia onde a primeira guerra mundial acabou", 2,false),
        RESPOSTA_2_PERGUNTA_1("Dia durante a Segunda Guerra Mundial onde o Japão atacou Pearl Harbor", 2,false),
        RESPOSTA_3_PERGUNTA_1("Dia onde o bug do milênio foi finalmente solucionado e evitou a falência de milhares de empresas na virada do milênio", 2,false),
        RESPOSTA_4_PERGUNTA_1("Dia onde os aliados invadiram a praia de Omaha na operação Overlord durante a Segunda Guerra Mundial", 2,true),
        
        
        RESPOSTA_1_PERGUNTA_2("Invasão a praia de Omaha para a operação Overlod", 3,true),
        RESPOSTA_2_PERGUNTA_2("Ataque do Japão em Pearl Harbor", 3,false),
        RESPOSTA_3_PERGUNTA_2("Invasão da Alemanha Nazista na Polônia",3,false),
        RESPOSTA_4_PERGUNTA_2("Derrota do exercito aliado e consagrada a vitória do eixo durante a Segunda Guerra", 3,false),
        
        
        RESPOSTA_1_PERGUNTA_3("Durante os governos da ditadura militar", 4,false),
        RESPOSTA_2_PERGUNTA_3("Getúlio Vargas", 4,false),
        RESPOSTA_3_PERGUNTA_3("Juscelino kubitschek", 4,true),
        RESPOSTA_4_PERGUNTA_3("João Goulart", 4,false),
        
        RESPOSTA_1_PERGUNTA_4("Uma linguagem web de programação rival do JavaScript", 5,false),
        RESPOSTA_2_PERGUNTA_4("Uma linguagem de programação onde é possível usar o Java para realizar queries em um banco de dados", 5,false),
        RESPOSTA_3_PERGUNTA_4("Biblioteca JavaScript que ajuda a simplificar a programação Javascript ", 5,true),
        RESPOSTA_4_PERGUNTA_4("Framework php para manipular eventos em sites", 5,false),
        
        RESPOSTA_1_PERGUNTA_5("Átila, o Huno",6,false),
        RESPOSTA_2_PERGUNTA_5("Ciro, o Grande", 6,false),
        RESPOSTA_3_PERGUNTA_5("Alexandre, o Grande", 6,false),
        RESPOSTA_4_PERGUNTA_5("Gêngis Khan", 6,true),
        
        RESPOSTA_4_PERGUNTA_6("Framework Java que facilita a consrução de aplicações Spring stand-alone ", 7,true),
        RESPOSTA_1_PERGUNTA_6("Uma biblioteca C# que facilita a programação e interação com os ambientes .net", 7,false),
        RESPOSTA_2_PERGUNTA_6("Um framework JavaScript que utiliza Node.js para construir uma aplicação monolítica ", 7,false),
        RESPOSTA_3_PERGUNTA_6("Biblioteca que facilita a análise de dados em aplicações Java", 7,false);

        private final String resposta;
        private final int codigoPergunta;
        private final boolean respostaVerdadeiraOuFalsa;
        
        public int getCodigoPergunta() {
            return codigoPergunta;
        }

        public String getResposta() {
            return resposta;
        }
        
        public boolean getRespostaVerdadeiraOuFalsa(){
            return respostaVerdadeiraOuFalsa;
        }
        
        
        RespostaEnum(String resposta, int codigoPergunta, boolean respostaVerdadeiraOuFalsa){
            this.resposta = resposta;
            this.codigoPergunta = codigoPergunta;
            this.respostaVerdadeiraOuFalsa = respostaVerdadeiraOuFalsa;
        }
        
        public static List<RespostaEnum> getListaRespostas(){
            List<RespostaEnum> listaRespostas = Arrays.asList(RespostaEnum.values());
            return listaRespostas;
        }
        
    }