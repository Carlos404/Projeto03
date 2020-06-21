
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
    
    public static void verificaSeExisteRespostasCadastradas() throws ClassNotFoundException, SQLException{
        if(buscaRespostas(new ArrayList<>()).isEmpty()){
           insereRespostas(); 
        }
    }
    
    public static ArrayList<Resposta> buscaAlternativas(String codigoPergunta) throws SQLException, ClassNotFoundException{
       ArrayList<Resposta> listaAlternativas = new ArrayList<>();
       
       String query = "SELECT * FROM resposta where cd_pergunta = ? ";
       
       Connection con = DriverManager.getConnection(DbListener.JDBCURL);
       PreparedStatement stmt = con.prepareStatement(query);
       
       stmt.setInt(1, Integer.parseInt(codigoPergunta));
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Resposta alternativa = new Resposta();
            alternativa.setResposta(rs.getString("ds_resposta"));
            alternativa.setCodigoResposta(String.valueOf(rs.getInt("cd_resposta")));
            
            listaAlternativas.add(alternativa);
        }
        Collections.shuffle(listaAlternativas);
        rs.close();
        iniciaETerminaConexaoComBanco(false);
        return listaAlternativas;
    }

    public static ArrayList<Resposta> buscaRespostas(ArrayList<Pergunta> listaPerguntas) throws ClassNotFoundException, SQLException{
        ArrayList<Resposta> listaRespostas = new ArrayList<>();
        boolean perguntasPadroes = false;
        if(listaPerguntas.isEmpty()) {
            perguntasPadroes = true;
            listaPerguntas = ControlePergunta.buscaPerguntas();
        }

       StringBuilder query = new StringBuilder("SELECT * FROM resposta where cd_pergunta in (")
                                               .append(substituiValorPorInterrogacao(listaPerguntas))
                                               .append(")");
       
       if(!perguntasPadroes) query.append(" and ic_resposta_correta = ?");
       
       Connection con = DriverManager.getConnection(DbListener.JDBCURL);
       PreparedStatement stmt = con.prepareStatement(query.toString());
       
       int j = substituiInterrogacaoPorValor(listaPerguntas, stmt);
       if(!perguntasPadroes) stmt.setBoolean(j, true);
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Resposta resposta = new Resposta();
            
            resposta.setCodigoPergunta(String.valueOf(rs.getInt("cd_pergunta")));
            resposta.setResposta(rs.getString("ds_resposta"));
            resposta.setCodigoResposta(String.valueOf(rs.getInt("cd_resposta")));
            resposta.setRespostaVerdadeiraOuFalsa(rs.getBoolean("ic_resposta_correta"));
            
            listaRespostas.add(resposta);
        }
        
        rs.close();
        iniciaETerminaConexaoComBanco(false);
        
        return listaRespostas;
    }
    
    
    public static void insereRespostas() throws ClassNotFoundException, SQLException{
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
    
    private static int substituiInterrogacaoPorValor(ArrayList<Pergunta> lista, PreparedStatement stm) throws SQLException{
        int j=1;
        for(int i=0; i<lista.size(); i++){
            stm.setInt(j, Integer.parseInt(lista.get(i).getCodigoPergunta()));
            j++;
        }
        return j;
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
}
