package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoConexaoBD {
    public DaoConexaoBD() {
        try {
            Connection conexao = ConfiguracaoBD.getConnection();
            System.out.println("✅ Conexão com o banco de dados Aiven estabelecida com sucesso!");
            ConfiguracaoBD.fecharConexao(conexao);
        }
        catch (SQLException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
}