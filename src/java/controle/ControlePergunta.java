
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
import quizEnum.PerguntaEnum;
import web.DbListener;


public class ControlePergunta {
    
    public static void verificaSeExistePerguntasCadastradas() throws ClassNotFoundException, SQLException{
        if(buscaPerguntas().isEmpty()){
           inserePerguntas(); 
        }
    }
    
    public static ArrayList<Pergunta> buscaPerguntas() throws ClassNotFoundException, SQLException{
        
        ArrayList<Pergunta> listaPerguntas = new ArrayList<>();
        
        String query = "SELECT * FROM pergunta";
        ResultSet rs = executaQuery(iniciaETerminaConexaoComBanco(true), query); 
        
        while(rs.next()){
            Pergunta pergunta = new Pergunta();
            
            pergunta.setCodigoPergunta(String.valueOf(rs.getInt("cd_pergunta")));
            pergunta.setPergunta(rs.getString("ds_pergunta"));
            
            listaPerguntas.add(pergunta);
        }
        Collections.shuffle(listaPerguntas);
        
        rs.close();
        iniciaETerminaConexaoComBanco(false);
        
        return listaPerguntas;
        
    }
    
    private static void inserePerguntas() throws ClassNotFoundException, SQLException{
        
        List<PerguntaEnum> listaPerguntas = PerguntaEnum.getListaPerguntas();
        
        Connection con = DriverManager.getConnection(DbListener.JDBCURL);
        
        PreparedStatement stmt = null;
        int i = 1;
        for(PerguntaEnum pergunta: listaPerguntas){
            
            String query = "INSERT INTO pergunta values (?,?)";
            stmt = con.prepareStatement(query);
            
            stmt.setInt(1, i++);
            stmt.setString(2, pergunta.getPergunta());
            
            stmt.execute();
        }
        
        stmt.close();
        con.close();
        
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
