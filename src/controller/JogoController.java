package controller;

import dao.*;

public class JogoController {
    public static void main(String[] args) {
        // Gerenciamento do banco de dados

        new DaoConexaoMySQL();
        new DaoCriacaoBD();
        new DaoConexaoBD();
        new DaoCriacaoTabelaCartas();
        new DaoInsercaoCartas();

        /* Idealmente a inserção de cartas só deve ser executada uma vez (na primeira vez que o programa é executado)
        mas eu coloquei uma verificação pra garantir que não seriam inseridas cartas duplicadas
        então se eu fiz tudo certo não tem problema não deixar comentado */

        // Gerenciamento de partida

        //new DaoIniciarPartida();
        //new DaoSortearCartaMaquina(/* INSERIR ID DA CARTA ESCOLHIDA PELO JOGADOR */);
        new DaoConsultaCartas();
        //new DaoBuscarCartasDisponiveis();
        //new DaoConsultaCartaPorId(/* INSERIR ID DA CARTA ESCOLHIDA PELO JOGADOR */);
        //new DaoAtualizacaoCartaUtilizada(/* INSERIR ID DA CARTA JOGADA */);
        //DaoReiniciarPartida daoReiniciarPartida = new DaoReiniciarPartida();
    }
}