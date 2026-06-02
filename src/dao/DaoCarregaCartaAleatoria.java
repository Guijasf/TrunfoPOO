package dao;

import model.Carta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para carregar uma carta aleatória disponível
 * Retorna um objeto Carta pronto para usar
 */
public class DaoCarregaCartaAleatoria {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost/" + NOME_BD;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    /**
     * Carrega uma carta aleatória disponível (não utilizada)
     * @return Objeto Carta ou null se não encontrar
     */
    public static Carta carregar() {
        String sql = "SELECT * FROM cartas WHERE utilizada = FALSE ORDER BY RAND() LIMIT 1";

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);
            ResultSet resultado = operacao.executeQuery();

            Carta carta = null;
            if (resultado.next()) {
                carta = new Carta(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getInt("força"),
                    resultado.getInt("velocidade"),
                    resultado.getInt("habilidade"),
                    resultado.getInt("equipamento"),
                    resultado.getInt("inteligência"),
                    resultado.getBoolean("super_trunfo"),
                    resultado.getBoolean("utilizada")
                );
                System.out.println("✅ Carta aleatória carregada: " + carta.getNome());
            } else {
                System.out.println("❌ Nenhuma carta disponível.");
            }

            conexao.close();
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
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);
            operacao.setInt(1, idCartaExcluir);

            ResultSet resultado = operacao.executeQuery();

            Carta carta = null;
            if (resultado.next()) {
                carta = new Carta(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getInt("força"),
                    resultado.getInt("velocidade"),
                    resultado.getInt("habilidade"),
                    resultado.getInt("equipamento"),
                    resultado.getInt("inteligência"),
                    resultado.getBoolean("super_trunfo"),
                    resultado.getBoolean("utilizada")
                );
                System.out.println("✅ Carta aleatória carregada: " + carta.getNome());
            } else {
                System.out.println("❌ Nenhuma carta disponível (excluindo ID " + idCartaExcluir + ").");
            }

            conexao.close();
            return carta;

        } catch (SQLException e) {
            System.out.println("❌ Erro ao carregar carta aleatória: " + e.getMessage());
            return null;
        }
    }
}

