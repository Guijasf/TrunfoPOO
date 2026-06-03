package dao;

import model.Carta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para carregar uma carta aleatória disponível
 * Retorna um objeto Carta pronto para usar
 */
public class DaoCarregaCartaAleatoria {

    /**
     * Carrega uma carta aleatória disponível (não utilizada)
     * @return Objeto Carta ou null se não encontrar
     */
    public static Carta carregar() {
        String sql = "SELECT * FROM cartas WHERE utilizada = FALSE ORDER BY RAND() LIMIT 1";

        try {
            Connection conexao = ConfiguracaoBD.getConnection();
            PreparedStatement operacao = conexao.prepareStatement(sql);
            ResultSet resultado = operacao.executeQuery();

            Carta carta = null;
            if (resultado.next()) {
                carta = new Carta(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getInt("forca"),
                    resultado.getInt("velocidade"),
                    resultado.getInt("habilidade"),
                    resultado.getInt("equipamento"),
                    resultado.getInt("inteligencia"),
                    resultado.getBoolean("super_trunfo"),
                    resultado.getBoolean("utilizada")
                );
                System.out.println("✅ Carta aleatória carregada: " + carta.getNome());
            } else {
                System.out.println("❌ Nenhuma carta disponível.");
            }

            ConfiguracaoBD.fecharConexao(conexao);
            return carta;

        } catch (SQLException e) {
            System.out.println("❌ Erro ao carregar carta aleatória: " + e.getMessage());
            return null;
        }
    }

    /**
     * Carrega uma carta aleatória disponível, excluindo uma carta específica
     * @param idCartaExcluir ID da carta a ser excluída
     * @return Objeto Carta ou null se não encontrar
     */
    public static Carta carregar(int idCartaExcluir) {
        String sql = "SELECT * FROM cartas WHERE utilizada = FALSE AND id <> ? ORDER BY RAND() LIMIT 1";

        try {
            Connection conexao = ConfiguracaoBD.getConnection();
            PreparedStatement operacao = conexao.prepareStatement(sql);
            operacao.setInt(1, idCartaExcluir);

            ResultSet resultado = operacao.executeQuery();

            Carta carta = null;
            if (resultado.next()) {
                carta = new Carta(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getInt("forca"),
                    resultado.getInt("velocidade"),
                    resultado.getInt("habilidade"),
                    resultado.getInt("equipamento"),
                    resultado.getInt("inteligencia"),
                    resultado.getBoolean("super_trunfo"),
                    resultado.getBoolean("utilizada")
                );
                System.out.println("✅ Carta aleatória carregada: " + carta.getNome());
            } else {
                System.out.println("❌ Nenhuma carta disponível (excluindo ID " + idCartaExcluir + ").");
            }

            ConfiguracaoBD.fecharConexao(conexao);
            return carta;

        } catch (SQLException e) {
            System.out.println("❌ Erro ao carregar carta aleatória: " + e.getMessage());
            return null;
        }
    }
}

