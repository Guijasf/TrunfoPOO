package dao;

import model.Carta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para carregar uma carta específica pelo ID
 * Retorna um objeto Carta pronto para usar
 */
public class DaoCarregaCartaPorId {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost/" + NOME_BD;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    /**
     * Carrega uma carta do banco de dados pelo ID
     * @param idCarta ID da carta
     * @return Objeto Carta ou null se não encontrar
     */
    public static Carta carregar(int idCarta) {
        String sql = "SELECT * FROM cartas WHERE id = ?";

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);
            operacao.setInt(1, idCarta);

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
                System.out.println("✅ Carta carregada: " + carta.getNome());
            } else {
                System.out.println("❌ Carta com ID " + idCarta + " não encontrada.");
            }

            conexao.close();
            return carta;

        } catch (SQLException e) {
            System.out.println("❌ Erro ao carregar carta: " + e.getMessage());
            return null;
        }
    }
}

