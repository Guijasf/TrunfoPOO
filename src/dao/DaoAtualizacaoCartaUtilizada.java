package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoAtualizacaoCartaUtilizada {
    public DaoAtualizacaoCartaUtilizada(int idCarta) {
        String sql = """
                UPDATE cartas
                SET utilizada = TRUE
                WHERE id = ?
                """;

        try {
            Connection conexao = ConfiguracaoBD.getConnection();
            PreparedStatement operacao = conexao.prepareStatement(sql);

            operacao.setInt(1, idCarta);

            operacao.executeUpdate();

            System.out.println("✅ Carta marcada como utilizada.");

            ConfiguracaoBD.fecharConexao(conexao);

        } catch (SQLException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
}