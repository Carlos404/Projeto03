package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class DbListener implements ServletContextListener{
    public static final String JDBCURL = "jdbc:sqlite:C:\\Users\\somat\\banco\\quiz.db";
    
    public static String exceptionMessage = null;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String step = "Database creation";
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection(JDBCURL);
            Statement stmt = con.createStatement();
            
            step = "Table 'usuario' creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS usuario("
                    + "nm_usuario VARCHAR(200) NOT NULL,"
                    + "nm_login_usuario VARCHAR(20) PRIMARY KEY,"
                    + "cd_senha_hash LONG NOT NULL"
                    + ")");
            
            step = "Table 'pergunta' creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pergunta("
                    + "cd_pergunta INTEGER PRIMARY KEY,"
                    + "ds_pergunta VARCHAR(1000) NOT NULL"
                    + ")");
            
            step = "Table 'resposta' creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS resposta("
                    + "cd_resposta INTEGER PRIMARY KEY,"
                    + "ds_resposta VARCHAR(1000) NOT NULL,"
                    + "cd_pergunta INTEGER NOT NULL,"
                    + "ic_resposta_correta BOOLEAN NOT NULL,"
                    + "FOREIGN KEY (cd_pergunta) REFERENCES pergunta(cd_pergunta)"
                    + ")");
            
            step = "Table 'teste' creation";
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS teste("
                    + "cd_teste INTEGER PRIMARY KEY,"
                    + "cd_resultado INTEGER NOT NULL,"
                    + "cd_usuario INTEGER NOT NULL,"
                    + "FOREIGN KEY (cd_usuario) REFERENCES usuario(cd_usuario)"
                    + ")");
            stmt.close();
            con.close();
        }catch(Exception ex){
            exceptionMessage = step + ": " + ex.getMessage();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
