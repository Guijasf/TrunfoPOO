package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoInsercaoCartas {
    public DaoInsercaoCartas() {
        try {
            Connection conexao = ConfiguracaoBD.getConnection();

            String contarCartas = "SELECT COUNT(*) FROM cartas";

            PreparedStatement operacaoContarCartas = conexao.prepareStatement(contarCartas);
            ResultSet resultado = operacaoContarCartas.executeQuery();

            resultado.next();

            int quantidadeCartas = resultado.getInt(1);

            if (quantidadeCartas > 0) {
                System.out.println("✅ Cartas já cadastradas.");

                ConfiguracaoBD.fecharConexao(conexao);
                return;
            }

            String sqlInsert = """
                    INSERT INTO cartas
                    (nome, forca, velocidade, habilidade, equipamento, inteligencia, super_trunfo)
                    VALUES
                    ('Homem de Ferro (Super Trunfo)', 5, 6, 2, 5, 6, true),
                    ('Capitão América', 3, 2, 6, 2, 3, false),
                    ('Thor', 6, 5, 3, 3, 2, false),
                    ('Hulk', 6, 5, 4, 1, 1, false),
                    ('Nick Fury', 1, 2, 4, 6, 3, false),
                    ('Viúva Negra', 1, 2, 6, 4, 3, false),
                    ('Gavião Arqueiro', 1, 1, 6, 5, 2, false),
                    ('Agente Hill', 1, 1, 4, 5, 3, false),
                    ('Agente Coulson', 1, 1, 3, 5, 2, false),
                    ('Tony Stark', 1, 1, 1, 5, 6, false),
                    ('Bruce Banner', 1, 1, 1, 3, 6, false),
                    ('Loki', 5, 4, 4, 5, 5, false),
                    ('Chitauri', 2, 1, 2, 3, 1, false),
                    ('Leviathan', 5, 5, 1, 5, 1, false),
                    ('Caveira Vermelha', 3, 2, 4, 4, 3, false),
                    ('Soldados Hidra', 2, 1, 2, 4, 2, false),
                    ('Chicote Negro', 2, 2, 3, 4, 6, false),
                    ('Monge de Ferro', 5, 4, 2, 5, 4, false),
                    ('Gigante de Gelo', 4, 3, 3, 2, 2, false),
                    ('Abominável', 6, 3, 3, 1, 3, false),
                    ('Heimdall', 4, 3, 3, 3, 2, false),
                    ('Emil Blonsk', 2, 3, 5, 2, 3, false),
                    ('Howard Stark', 1, 1, 2, 4, 6, false),
                    ('War Machine', 5, 5, 2, 5, 3, false),
                    ('Homem de Ferro (Mark V)', 5, 4, 2, 4, 6, false),
                    ('Odin', 5, 2, 1, 6, 6, false),
                    ('Sif', 4, 2, 4, 3, 3, false),
                    ('Dum Dum Dugan', 1, 2, 3, 2, 2, false),
                    ('Bucky Barnes', 1, 2, 3, 3, 3, false),
                    ('Destruidor', 6, 4, 3, 3, 1, false)
                    """;

            PreparedStatement operacaoInsert = conexao.prepareStatement(sqlInsert);

            operacaoInsert.execute();

            System.out.println("✅ Cartas inseridas com sucesso!");

            ConfiguracaoBD.fecharConexao(conexao);
        } catch (SQLException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
}