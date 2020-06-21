
package controle;

import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import objeto.Teste;

import objeto.Usuario;
import web.DbListener;

public class ControleUsuario {

	public static ArrayList<Usuario> login(Usuario usuario) throws Exception {

		ArrayList<Usuario> list = new ArrayList<>();
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection(DbListener.JDBCURL);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
		while (rs.next()) {
			list.add(new Usuario(rs.getString("nome"), rs.getString("login"), rs.getString("senha")));
		}
		rs.close();
		stmt.close();
		con.close();
		return list;
	}

	public static Usuario getUsuario(String login, String senha) throws Exception {
		Usuario usuario = null;
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection(DbListener.JDBCURL);
		String SQL = "SELECT * FROM usuario WHERE nm_login_usuario=? AND cd_senha_hash=?";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setString(1, login);
		stmt.setLong(2, senha.hashCode());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			usuario.setLogin(rs.getString("nm_login_usuario"));
			usuario.setNome(rs.getString("nm_usuario"));
			usuario.setSenha(rs.getString("cd_senha_hash"));
		} else {

		}
		rs.close();
		stmt.close();
		con.close();
		return usuario;
	}
	
	public static void setUsuario(String nome, String login, String senha) throws Exception{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.JDBCURL);
        String SQL = "INSERT INTO usuario(nm_usuario, nm_login_usuario, cd_senha_hash) VALUES(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, nome);
        stmt.setString(2, login);
        stmt.setLong(3, senha.hashCode());
        stmt.execute();
        stmt.close();
        con.close();
    }
    public static String getMediaUsuario(String codigoUsuario) throws ClassNotFoundException, SQLException{
        ArrayList<Teste> testesRealizados = ControleQuiz.getUltimosTestesRealizadosUsuario(codigoUsuario);
        double somaTotalAcertos = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        
        for(Teste teste: testesRealizados){
            somaTotalAcertos = somaTotalAcertos + parseInt(teste.getResultado());
        }
        return df.format(somaTotalAcertos/testesRealizados.size());
    }
}
