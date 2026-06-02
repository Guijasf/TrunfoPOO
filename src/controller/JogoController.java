package controller;

import view.TelaPrincipal;
import javax.swing.*;

public class JogoController {
    public static void main(String[] args) {
        // Iniciar interface gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPrincipal();
            }
        });

        /* COMENTADO: Inicialização anterior do banco de dados
        new DaoConexaoMySQL();
        new DaoCriacaoBD();
        new DaoConexaoBD();
        new DaoCriacaoTabelaCartas();
        new DaoInsercaoCartas();

        Idealmente a inserção de cartas só deve ser executada uma vez (na primeira vez que o programa é executado)
        mas eu coloquei uma verificação pra garantir que não seriam inseridas cartas duplicadas
        então se eu fiz tudo certo não tem problema não deixar comentado

        new DaoConsultaCartas();
        */
    }
}