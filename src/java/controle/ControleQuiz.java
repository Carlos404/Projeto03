
package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import objeto.Pergunta;
import objeto.Resposta;
import web.DbListener;


public class ControleQuiz {
    
//    public static int getReultadoTeste(ArrayList<Pergunta> listaPerguntas) throws ClassNotFoundException, SQLException{
//        
//        return verificaQuantidadeAcertos(listaPerguntas, getRespostas(listaPerguntas));
//    }
    
    private static int verificaQuantidadeAcertos(ArrayList<Pergunta> listaPerguntas, ArrayList<Resposta> listaRespostas){
        int quantidadeAcertos = 0;
        
        return quantidadeAcertos;
    }
    
    
    
//    public static ArrayList<Resposta> getRespostas(ArrayList<Pergunta> listaPerguntas) throws ClassNotFoundException, SQLException{
//       ArrayList<Resposta> listaRespostas = new ArrayList<>();
//       
//       
//       String query = "SELECT * FROM resposta where cd_pergunta in ("+substituiValorPorInterrogacao(listaPerguntas)+")";
//       
//       Connection con = DriverManager.getConnection(DbListener.JDBCURL);
//       PreparedStatement stmt = con.prepareStatement(query);
//       
//       substituiInterrogacaoPorValor(listaPerguntas, stmt);
//       
//       ResultSet rs = stmt.executeQuery();
//       
//        while(rs.next()){
//            Resposta resposta = new Resposta();
//            
//            resposta.setCodigoResposta(String.valueOf(rs.getInt("cd_resposta")));
//            resposta.setResposta(rs.getString("nm_resposta"));
//            
//            listaRespostas.add(resposta);
//        }
//        
//        rs.close();
//        iniciaETerminaConexaoComBanco(false);
//        
//        return listaRespostas;
//    }
    
    private static ResultSet executaQuery(Statement stmt, String query) throws SQLException{
        return stmt.executeQuery(query);
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
