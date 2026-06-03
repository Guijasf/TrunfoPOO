package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe central para configuração de conexão com banco de dados Aiven
 * Todos os DAOs usam ESTA classe, não fazem conexão direta
 */
public class ConfiguracaoBD {
    
    // ===== CREDENCIAIS AIVEN =====
    private static final String URL = "jdbc:mysql://mysql-2c435ba5-soulasalle.a.aivencloud.com:21933/defaultdb?useSSL=true&serverTimezone=UTC";
    private static final String USUARIO = "avnadmin";
    private static final String SENHA = "AVN8_K1x0G9gX7B6M89vY1-w";
    
    /**
     * Retorna uma conexão aberta com o banco Aiven
     * @return Connection pronta para usar
     * @throws SQLException se não conseguir conectar
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
    /**
     * Fecha uma conexão com segurança
     * @param conexao Connection a fechar
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
