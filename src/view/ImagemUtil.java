package view;

import javax.swing.*;
import java.awt.Image;
import java.io.File;

/**
 * Classe utilitária para carregar e gerenciar imagens no projeto.
 * Funciona independentemente do diretório de execução.
 */
public class ImagemUtil {

    /**
     * Retorna o caminho base do projeto
     */
    private static String getCaminhoBase() {
        // Tenta encontrar o arquivo .iml para localizar a raiz do projeto
        String caminhoAtual = System.getProperty("user.dir");

        // Se estamos em POO, volta um nível
        if (caminhoAtual.endsWith("POO")) {
            caminhoAtual = caminhoAtual + File.separator + "TrunfoPOO";
        }
        // Se estamos em TrunfoPOO, perfeito
        else if (caminhoAtual.endsWith("TrunfoPOO")) {
            // já está correto
        }
        // Se estamos em src/view ou outro subdiretório, volta até TrunfoPOO
        else if (caminhoAtual.contains("src")) {
            File file = new File(caminhoAtual);
            while (file != null && !file.getName().equals("TrunfoPOO")) {
                file = file.getParentFile();
            }
            if (file != null) {
                caminhoAtual = file.getAbsolutePath();
            }
        }

        return caminhoAtual;
    }

    /**
     * Carrega uma imagem e a redimensiona
     * @param caminhoRelativo - Caminho relativo (ex: "img/logo/super-trunfo.jpg")
     * @param largura - Largura desejada
     * @param altura - Altura desejada
     * @return ImageIcon redimensionado, ou null se não encontrar
     */
    public static ImageIcon carregarImagem(String caminhoRelativo, int largura, int altura) {
        try {
            String caminhoBase = getCaminhoBase();
            String caminhoCompleto = caminhoBase + File.separator + caminhoRelativo;

            File arquivo = new File(caminhoCompleto);
            if (!arquivo.exists()) {
                System.err.println("❌ Imagem não encontrada: " + caminhoCompleto);
                return null;
            }

            ImageIcon icon = new ImageIcon(caminhoCompleto);
            Image img = icon.getImage();

            if (img == null) {
                System.err.println("❌ Erro ao carregar imagem: " + caminhoCompleto);
                return null;
            }

            // Redimensionar
            Image imgRedimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

            System.out.println("✅ Imagem carregada: " + caminhoRelativo);
            return new ImageIcon(imgRedimensionada);

        } catch (Exception e) {
            System.err.println("❌ Erro ao carregar imagem " + caminhoRelativo + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Carrega uma imagem sem redimensionar
     */
    public static ImageIcon carregarImagem(String caminhoRelativo) {
        try {
            String caminhoBase = getCaminhoBase();
            String caminhoCompleto = caminhoBase + File.separator + caminhoRelativo;

            File arquivo = new File(caminhoCompleto);
            if (!arquivo.exists()) {
                System.err.println("❌ Imagem não encontrada: " + caminhoCompleto);
                return null;
            }

            System.out.println("✅ Imagem carregada: " + caminhoRelativo);
            return new ImageIcon(caminhoCompleto);

        } catch (Exception e) {
            System.err.println("❌ Erro ao carregar imagem " + caminhoRelativo + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Retorna o caminho da imagem de uma carta
     * Exemplo: "A1" retorna "img/cartas/A/A1.png"
     */
    public static String getCaminhoCartaPorId(String idCarta) {
        if (idCarta == null || idCarta.length() < 2) {
            return null;
        }

        String letra = idCarta.substring(0, 1).toUpperCase();
        String numero = idCarta.substring(1);

        return "img/cartas/" + letra + "/" + idCarta + ".png";
    }
}


