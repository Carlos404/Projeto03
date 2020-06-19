
package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import objeto.Pergunta;
import objeto.Resposta;
import quizEnum.RespostaEnum;
import web.DbListener;


public class ControleResposta {
    
    public static void verificaSeExisteRespostasCadastradasNoBanco() throws ClassNotFoundException, SQLException{
        if(buscaRespostasNoBanco().isEmpty()){
           insereRespostasNoBanco(); 
        }
    }
    
    public static ArrayList<Resposta> buscaRespostasNoBanco() throws ClassNotFoundException, SQLException{
        ArrayList<Resposta> listaRespostas = new ArrayList<>();
        ArrayList<Pergunta> listaPerguntas = ControlePergunta.buscaPerguntasNoBanco();

        String query = "SELECT * FROM resposta where cd_pergunta in ("+substituiValorPorInterrogacao(listaPerguntas)+")";
       
       Connection con = DriverManager.getConnection(DbListener.JDBCURL);
       PreparedStatement stmt = con.prepareStatement(query);
       
       substituiInterrogacaoPorValor(listaPerguntas, stmt);
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Resposta resposta = new Resposta();
            
            resposta.setCodigoPergunta(String.valueOf(rs.getInt("cd_pergunta")));
            resposta.setResposta(rs.getString("nm_resposta"));
            resposta.setCodigoResposta(String.valueOf(rs.getInt("cd_resposta")));
            resposta.setRespostaVerdadeiraOuFalsa(rs.getBoolean("ic_resposta_correta"));
            
            listaRespostas.add(resposta);
        }
        Collections.shuffle(listaRespostas);
        
        rs.close();
        iniciaETerminaConexaoComBanco(false);
        
        return listaRespostas;
    }
    
    
    public static void insereRespostasNoBanco() throws ClassNotFoundException, SQLException{
        List<RespostaEnum> listaRespostas = RespostaEnum.getListaRespostas();
        
        Connection con = DriverManager.getConnection(DbListener.JDBCURL);
        PreparedStatement stmt = null;
        
        int i = 0;
        int j=1;
        for(RespostaEnum resposta: listaRespostas){
            String query = "INSERT INTO resposta values (?,?,?,?)";
            stmt = con.prepareStatement(query);
            
            stmt.setInt(1 , i++);
            stmt.setString(2, resposta.getResposta());
            stmt.setInt(3, resposta.getCodigoPergunta());
            stmt.setBoolean(4, resposta.getRespostaVerdadeiraOuFalsa());
            
            stmt.execute();
        }
        
        stmt.close();
        con.close();
        
    }
    
    private static String substituiValorPorInterrogacao(ArrayList<?> lista){
        StringBuilder interrogacao = new StringBuilder();
        int j=1;
        for(int i=0; i<lista.size(); i++){
            interrogacao.append("?");
            
            if(lista.size()-j != 0){
                interrogacao.append(",");
                j++;
            }
        }
        
        return interrogacao.toString();
    }
    
    private static void substituiInterrogacaoPorValor(ArrayList<Pergunta> lista, PreparedStatement stm) throws SQLException{
        int j=1;
        for(int i=0; i<lista.size(); i++){
            stm.setInt(j, Integer.parseInt(lista.get(i).getCodigoPergunta()));
            j++;
        }
    }
    
    private static Statement iniciaETerminaConexaoComBanco(boolean inicia) throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.JDBCURL);
        Statement stmt =  con.createStatement();
        
        if(inicia){
            return stmt;
        }else{
            stmt.close();
            con.close();
        }
        
        return null;
    }
    
    private static ResultSet executaQuery(Statement stmt, String query) throws SQLException{
        return stmt.executeQuery(query);
    }
}
