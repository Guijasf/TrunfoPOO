package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoCriacaoTabelaCartas {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost/" + NOME_BD;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public DaoCriacaoTabelaCartas() {
        String sql = """
                CREATE TABLE IF NOT EXISTS cartas(
                    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(50),
                    forca INT,
                    velocidade INT,
                    habilidade INT,
                    equipamento INT,
                    inteligencia INT,
                    super_trunfo BOOLEAN DEFAULT FALSE,
                    utilizada BOOLEAN DEFAULT FALSE
                )
            """;

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);

            operacao.execute();

            System.out.print("Tabela carta criada com sucesso!");

            conexao.close();
        }
        catch (SQLException e) {
            System.out.print("Erro: " + e.getMessage());
        }
    }
}