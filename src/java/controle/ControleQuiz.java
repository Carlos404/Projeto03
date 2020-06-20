
package controle;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import objeto.Pergunta;
import objeto.Resposta;
import objeto.Teste;
import web.DbListener;
        
public class ControleQuiz {
    
    public static int getResultadoQuiz(Teste quiz) throws ClassNotFoundException, SQLException, SQLException, SQLException{
        return validaRespostas(quiz);
    }
    
    public static ArrayList<Teste> getRanking() throws SQLException{
        ArrayList<Teste> listaTestes = new ArrayList<>();
       
       String query = "SELECT t.cd_teste, t.cd_resultado, u.nm_usuario "
                    + "FROM teste t  "
                    + "INNER JOIN usuario u where t.cd_usuario = u.rowid  "
                    + " order by t.cd_resultado desc LIMIT 10;";
      
       Connection con = DriverManager.getConnection(DbListener.JDBCURL);
       PreparedStatement stmt = con.prepareStatement(query);
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Teste teste = new Teste();
            teste.setCodigoTeste(valueOf(rs.getInt("cd_teste")));
            teste.setResultado(valueOf(rs.getInt("cd_resultado")));
            teste.setNomeUsuario(rs.getString("nm_usuario"));
            
            listaTestes.add(teste);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        return listaTestes;
    }
    
    public static ArrayList<Teste> getUltimosTestesRealizadosUsuario(String codigoUsuario) throws ClassNotFoundException, SQLException{
       ArrayList<Teste> listaTestes = new ArrayList<>();
       
       String query = "SELECT * FROM teste where cd_usuario = ? order by rowid desc LIMIT 10";
      
       Connection con = DriverManager.getConnection(DbListener.JDBCURL);
       PreparedStatement stmt = con.prepareStatement(query);
       
       stmt.setInt(1, Integer.parseInt(codigoUsuario));
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Teste teste = new Teste();
            teste.setCodigoTeste(valueOf(rs.getInt("cd_teste")));
            teste.setResultado(valueOf(rs.getInt("cd_resultado")));
            
            listaTestes.add(teste);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        return listaTestes;
    }
    
    public static ArrayList<Teste> getUltimosTestesRealizados() throws ClassNotFoundException, SQLException{
       ArrayList<Teste> listaTestes = new ArrayList<>();
       
       String query = "SELECT t.cd_teste, t.cd_resultado, u.nm_usuario "
                    + "FROM teste t  "
                    + "INNER JOIN usuario u where t.cd_usuario = u.rowid  "
                    + " order by t.rowid desc LIMIT 10;";
      
      
       Connection con = DriverManager.getConnection(DbListener.JDBCURL);
       PreparedStatement stmt = con.prepareStatement(query);
       
       ResultSet rs = stmt.executeQuery();
       
        while(rs.next()){
            Teste teste = new Teste();
            
            teste.setCodigoTeste(valueOf(rs.getInt("cd_teste")));
            teste.setResultado(valueOf(rs.getInt("cd_resultado")));
            teste.setNomeUsuario(rs.getString("nm_usuario"));
            
            listaTestes.add(teste);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        return listaTestes;
    }
    
    private static int validaRespostas(Teste quiz) throws ClassNotFoundException, SQLException{
        int quantidadeAcertos = 0;
        ArrayList<Resposta> respostasCorretas = ControleResposta.buscaRespostasNoBanco(preparaListaPerguntas(quiz.getCodigoPergunta()));
        List<String> listaRespostasEnviadas = Arrays.asList(quiz.getCodigoResposta().split(","));
        
        for(Resposta resposta : respostasCorretas){
            if(listaRespostasEnviadas.contains(resposta.getCodigoResposta())) quantidadeAcertos++;
        }
        
        insereResultado(quantidadeAcertos,quiz.getCodigoUsuario());
        
        return quantidadeAcertos;
    }
    
    private static void insereResultado(int quantidadeAcertos, String codigoUsuario) throws SQLException, ClassNotFoundException{
        Connection con = DriverManager.getConnection(DbListener.JDBCURL);
        PreparedStatement stmt = null;
       
        codigoUsuario = "1";
        
        String query = "INSERT INTO teste values (?,?, ?)";
        stmt = con.prepareStatement(query);

        stmt.setInt(1, buscaUltimoCodigoTesteInserido()+1);
        stmt.setInt(2, quantidadeAcertos);
        stmt.setInt(3, parseInt(codigoUsuario));

        stmt.execute();
       
        
        stmt.close();
        con.close();
    }
    
    private static int buscaUltimoCodigoTesteInserido() throws SQLException, ClassNotFoundException{
       int ultimoCodigo = 0;
       
       String query = "SELECT cd_teste FROM teste order by rowid desc LIMIT 1 ";
       Class.forName("org.sqlite.JDBC");
       Connection con = DriverManager.getConnection(DbListener.JDBCURL);
       Statement stmt =  con.createStatement();
              
       ResultSet rs = stmt.executeQuery(query);
       
        while(rs.next()){
            ultimoCodigo =  rs.getInt("cd_teste");
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        return ultimoCodigo;
    }
    
    private static ArrayList<Pergunta> preparaListaPerguntas(String codigoPerguntas){
        ArrayList<Pergunta> listaPergunta = new ArrayList<>();
        
        for(String codigoPergunta: codigoPerguntas.split(",")){
            Pergunta pergunta = new Pergunta();
            pergunta.setCodigoPergunta(codigoPergunta);
                      
            listaPergunta.add(pergunta);
        }
        
        return listaPergunta;
    }
}
