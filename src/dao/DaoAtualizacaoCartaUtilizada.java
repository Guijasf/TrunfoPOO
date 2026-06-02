package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoAtualizacaoCartaUtilizada {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost/" + NOME_BD;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public DaoAtualizacaoCartaUtilizada(int idCarta) {
        String sql = """
                UPDATE cartas
                SET utilizada = TRUE
                WHERE id = ?
                """;

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);

            operacao.setInt(1, idCarta);

            operacao.executeUpdate();

            System.out.println("Carta marcada como utilizada.");

            conexao.close();

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}