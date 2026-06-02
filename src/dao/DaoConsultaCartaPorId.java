package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoConsultaCartaPorId {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost/" + NOME_BD;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public DaoConsultaCartaPorId(int idCarta) {
        String sql = """
                SELECT *
                FROM cartas
                WHERE id = ?
                """;

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);

            operacao.setInt(1, idCarta);

            ResultSet resultado = operacao.executeQuery();

            if (resultado.next()) {
                System.out.println(resultado.getString("nome"));
            } else {
                System.out.println("Carta não encontrada.");
            }

            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}