package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoCriacaoBD {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public DaoCriacaoBD() {
        String sql = "CREATE DATABASE IF NOT EXISTS " + NOME_BD;

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement operacao = conexao.prepareStatement(sql);

            operacao.execute();

            System.out.print("Banco de dados criado com sucesso!");

            conexao.close();
        }
        catch (SQLException e) {
            System.out.print("Erro: " + e.getMessage());
        }
    }
}