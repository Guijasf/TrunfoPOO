package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoConsultaCartaPorId {
    public DaoConsultaCartaPorId(int idCarta) {
        String sql = """
                SELECT *
                FROM cartas
                WHERE id = ?
                """;

        try {
            Connection conexao = ConfiguracaoBD.getConnection();
            PreparedStatement operacao = conexao.prepareStatement(sql);

            operacao.setInt(1, idCarta);

            ResultSet resultado = operacao.executeQuery();

            if (resultado.next()) {
                System.out.println("✅ Carta: " + resultado.getString("nome"));
            } else {
                System.out.println("❌ Carta não encontrada.");
            }

            ConfiguracaoBD.fecharConexao(conexao);
        } catch (SQLException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
}