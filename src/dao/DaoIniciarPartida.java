package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoIniciarPartida {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost/" + NOME_BD;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public DaoIniciarPartida() {
        String sql = """
                UPDATE cartas
                SET utilizada = FALSE
                """;

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);

            int registros = operacao.executeUpdate();

            System.out.println("Partida iniciada! " + registros + " cartas liberadas.");

            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}