package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConexaoBD {
    private static final String NOME_BD = "supertrunfo";
    private static final String URL = "jdbc:mysql://localhost/" + NOME_BD;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public DaoConexaoBD() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            System.out.print("Conexão com o banco de dados estabelecida com sucesso!");

            conexao.close();
        }
        catch (SQLException e) {
            System.out.print("Erro: " + e.getMessage());
        }
    }
}