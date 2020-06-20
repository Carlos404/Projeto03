
package controle;

import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import objeto.Teste;
import objeto.Usuario;

public class ControleUsuario {

    public static Usuario login(Usuario usuario){
        
        return usuario;
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
