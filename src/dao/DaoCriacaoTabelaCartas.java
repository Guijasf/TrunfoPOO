package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoCriacaoTabelaCartas {
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
            Connection conexao = ConfiguracaoBD.getConnection();
            PreparedStatement operacao = conexao.prepareStatement(sql);

            operacao.execute();

            System.out.println("✅ Tabela cartas criada com sucesso!");

            ConfiguracaoBD.fecharConexao(conexao);
        }
        catch (SQLException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
}