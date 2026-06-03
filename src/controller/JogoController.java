package controller;

import view.TelaPrincipal;
import dao.*;
import javax.swing.*;

public class JogoController {
    public static void main(String[] args) {
        // Inicializar banco de dados ANTES de abrir interface
        System.out.println("🔧 Inicializando banco de dados Aiven...");
        new DaoConexaoBD();
        new DaoCriacaoTabelaCartas();
        new DaoInsercaoCartas();
        System.out.println("✅ Banco pronto!\n");

        // Iniciar interface gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPrincipal();
            }
        });
    }
}