package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoConsultaCartas {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost/" + NOME_BD;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public DaoConsultaCartas() {
        String sql = """
                SELECT *
                FROM cartas
                WHERE utilizada = FALSE
                """;

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);
            ResultSet resultado = operacao.executeQuery();

            while (resultado.next()) {
                System.out.println(
                        resultado.getInt("id") + " | " +
                                resultado.getString("nome") + " | " +
                                resultado.getInt("força") + " | " +
                                resultado.getInt("velocidade") + " | " +
                                resultado.getInt("habilidade") + " | " +
                                resultado.getInt("equipamento") + " | " +
                                resultado.getInt("inteligência")
                );
            }

            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}