package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConexaoMySQL {
    private static final String URL = "jdbc:mysql://localhost";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public DaoConexaoMySQL() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            System.out.print("Conexão com o MySQL estabelecida com sucesso!");

            conexao.close();
        }
        catch (SQLException e) {
            System.out.print("Erro: " + e.getMessage());
        }
    }
}