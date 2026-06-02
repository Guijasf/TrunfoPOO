package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoSortearCartaMaquina {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost/" + NOME_BD;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public DaoSortearCartaMaquina(int idCartaJogador) {
        String sql = """
                SELECT *
                FROM cartas
                WHERE utilizada = FALSE
                AND id <> ?
                ORDER BY RAND()
                LIMIT 1
                """;

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);

            operacao.setInt(1, idCartaJogador);

            ResultSet resultado = operacao.executeQuery();

            if (resultado.next()) {
                System.out.println("Carta sorteada para a máquina:");

                System.out.println(
                        resultado.getInt("id") + " | " +
                        resultado.getString("nome") + " | " +
                        resultado.getInt("força") + " | " +
                        resultado.getInt("velocidade") + " | " +
                        resultado.getInt("habilidade") + " | " +
                        resultado.getInt("equipamento") + " | " +
                        resultado.getInt("inteligência")
                );
            } else {
                System.out.println("Nenhuma carta disponível para sorteio.");
            }

            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}