package view;

import model.Carta;
import dao.DaoCarregaCartaPorId;
import dao.DaoCarregaCartaAleatoria;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaJogo extends JFrame {
    private JPanel painelCartasJogador;
    private JPanel painelCartasMaquina;
    private JPanel painelComparacao;
    private JPanel painelAtributos;
    private JLabel lblPlacarJogador;
    private JLabel lblPlacarMaquina;
    private JLabel lblResultado;

    // Cartas atuais
    private Carta cartaJogadorAtual;
    private Carta cartaMaquinaAtual;
    private int vitoriasJogador = 0;
    private int vitoriasMaquina = 0;

    public TelaJogo() {
        setTitle("Super Trunfo!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(30, 30, 30));

        // Painel superior com placar
        JPanel painelPlacar = criarPainelPlacar();

        // Painel central com cartas e atributos
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BorderLayout(10, 10));
        painelCentral.setBackground(new Color(30, 30, 30));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de cartas
        JPanel painelCartas = criarPainelCartas();

        // Painel de atributos
        painelAtributos = criarPainelAtributos();

        painelCentral.add(painelCartas, BorderLayout.CENTER);
        painelCentral.add(painelAtributos, BorderLayout.SOUTH);

        // Painel inferior com botões
        JPanel painelInferior = criarPainelInferior();

        painelPrincipal.add(painelPlacar, BorderLayout.NORTH);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);

        // Carregar primeira carta
        carregarNovasCarta();

        setVisible(true);
    }

    private JPanel criarPainelPlacar() {
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 10));
        painel.setBackground(new Color(50, 50, 50));
        painel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        lblPlacarJogador = new JLabel("Você: 0");
        lblPlacarJogador.setFont(new Font("Arial", Font.BOLD, 20));
        lblPlacarJogador.setForeground(new Color(0, 200, 0));

        lblPlacarMaquina = new JLabel("Máquina: 0");
        lblPlacarMaquina.setFont(new Font("Arial", Font.BOLD, 20));
        lblPlacarMaquina.setForeground(new Color(200, 0, 0));

        painel.add(lblPlacarJogador);
        painel.add(lblPlacarMaquina);

        return painel;
    }

    private JPanel criarPainelCartas() {
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(1, 3, 20, 0));
        painel.setBackground(new Color(30, 30, 30));

        // Carta do Jogador
        painelCartasJogador = new JPanel();
        painelCartasJogador.setBackground(new Color(50, 50, 50));
        painelCartasJogador.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        painelCartasJogador.setLayout(new BoxLayout(painelCartasJogador, BoxLayout.Y_AXIS));

        painelCartasJogador.add(Box.createVerticalGlue());
        painelCartasJogador.add(Box.createVerticalGlue());

        // Versus
        JPanel painelVersus = new JPanel();
        painelVersus.setLayout(new BorderLayout());
        painelVersus.setBackground(new Color(30, 30, 30));
        JLabel lblVersus = new JLabel("VS");
        lblVersus.setFont(new Font("Arial", Font.BOLD, 30));
        lblVersus.setForeground(Color.WHITE);
        lblVersus.setHorizontalAlignment(JLabel.CENTER);
        painelVersus.add(lblVersus, BorderLayout.CENTER);

        // Carta da Máquina
        painelCartasMaquina = new JPanel();
        painelCartasMaquina.setBackground(new Color(50, 50, 50));
        painelCartasMaquina.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        painelCartasMaquina.setLayout(new BoxLayout(painelCartasMaquina, BoxLayout.Y_AXIS));

        painelCartasMaquina.add(Box.createVerticalGlue());
        painelCartasMaquina.add(Box.createVerticalGlue());

        painel.add(painelCartasJogador);
        painel.add(painelVersus);
        painel.add(painelCartasMaquina);

        return painel;
    }

    private JPanel criarPainelAtributos() {
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(1, 5, 5, 0));
        painel.setBackground(new Color(30, 30, 30));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        String[] atributos = {"Força", "Velocidade", "Habilidade", "Equipamento", "Inteligência"};

        for (String atributo : atributos) {
            JButton btn = new JButton(atributo);
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setBackground(new Color(0, 100, 200));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createRaisedBevelBorder());
            btn.setName(atributo.toLowerCase());
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    compararAtributo(atributo.toLowerCase());
                }
            });
            painel.add(btn);
        }

        return painel;
    }

    private JPanel criarPainelInferior() {
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        painel.setBackground(new Color(50, 50, 50));
        painel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        lblResultado = new JLabel("Escolha um atributo para comparar!");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 14));
        lblResultado.setForeground(new Color(255, 200, 0));

        JButton btnProxima = new JButton("PRÓXIMA RODADA");
        btnProxima.setFont(new Font("Arial", Font.BOLD, 14));
        btnProxima.setPreferredSize(new Dimension(180, 40));
        btnProxima.setBackground(new Color(0, 150, 0));
        btnProxima.setForeground(Color.WHITE);
        btnProxima.setFocusPainted(false);
        btnProxima.setBorder(BorderFactory.createRaisedBevelBorder());
        btnProxima.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarNovasCarta();
            }
        });

        JButton btnVoltar = new JButton("VOLTAR AO MENU");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setPreferredSize(new Dimension(180, 40));
        btnVoltar.setBackground(new Color(200, 0, 0));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBorder(BorderFactory.createRaisedBevelBorder());
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarAoMenu();
            }
        });

        painel.add(lblResultado);
        painel.add(btnProxima);
        painel.add(btnVoltar);

        return painel;
    }

    private void compararAtributo(String atributo) {
        if (cartaJogadorAtual == null || cartaMaquinaAtual == null) {
            lblResultado.setText("Cartas não carregadas!");
            return;
        }

        int valorJogador = obterValorAtributo(cartaJogadorAtual, atributo);
        int valorMaquina = obterValorAtributo(cartaMaquinaAtual, atributo);

        String resultado = "";
        if (valorJogador > valorMaquina) {
            vitoriasJogador++;
            resultado = "VOCÊ GANHOU! Seu atributo foi maior!";
            lblResultado.setForeground(new Color(0, 200, 0));
        } else if (valorMaquina > valorJogador) {
            vitoriasMaquina++;
            resultado = "VOCÊ PERDEU! A máquina venceu!";
            lblResultado.setForeground(new Color(200, 0, 0));
        } else {
            resultado = "EMPATE!";
            lblResultado.setForeground(new Color(255, 200, 0));
        }

        lblResultado.setText(resultado);
        atualizarPlacar();
    }

    private int obterValorAtributo(Carta carta, String atributo) {
        return switch (atributo) {
            case "forca" -> carta.getForca();
            case "velocidade" -> carta.getVelocidade();
            case "habilidade" -> carta.getHabilidade();
            case "equipamento" -> carta.getEquipamento();
            case "inteligencia" -> carta.getInteligencia();
            default -> 0;
        };
    }

    private void carregarNovasCarta() {
        lblResultado.setText("⏳ Carregando cartas do banco de dados...");
        lblResultado.setForeground(new Color(255, 200, 0));

        try {
            // Carregar carta aleatória para o jogador
            cartaJogadorAtual = DaoCarregaCartaAleatoria.carregar();

            if (cartaJogadorAtual == null) {
                lblResultado.setText("❌ Erro: Nenhuma carta disponível no banco!");
                lblResultado.setForeground(new Color(200, 0, 0));
                return;
            }

            // Carregar carta aleatória para a máquina (excluindo a carta do jogador)
            cartaMaquinaAtual = DaoCarregaCartaAleatoria.carregar(cartaJogadorAtual.getId());

            if (cartaMaquinaAtual == null) {
                lblResultado.setText("❌ Erro: Segunda carta não disponível!");
                lblResultado.setForeground(new Color(200, 0, 0));
                return;
            }

            lblResultado.setText("Escolha um atributo para comparar!");
            lblResultado.setForeground(new Color(255, 200, 0));

            atualizarExibicaoCartas();

        } catch (Exception e) {
            System.err.println("Erro ao carregar cartas: " + e.getMessage());
            lblResultado.setText("❌ Erro ao carregar cartas: " + e.getMessage());
            lblResultado.setForeground(new Color(200, 0, 0));
        }
    }

    private void atualizarExibicaoCartas() {
        // ===== CARTA DO JOGADOR =====
        painelCartasJogador.removeAll();
        painelCartasJogador.setLayout(new BoxLayout(painelCartasJogador, BoxLayout.Y_AXIS));

        JLabel lblJogador = new JLabel("🟢 SUA CARTA");
        lblJogador.setFont(new Font("Arial", Font.BOLD, 14));
        lblJogador.setForeground(Color.GREEN);
        lblJogador.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tenta carregar imagem baseado no ID da carta
        JLabel imgCartaJogador = new JLabel();
        imgCartaJogador.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Gera ID da carta no formato esperado (A1, B2, etc)
        String idCartaFormatado = gerarIdCarta(cartaJogadorAtual.getId());
        String caminhoCartaJogador = ImagemUtil.getCaminhoCartaPorId(idCartaFormatado);
        ImageIcon iconJogador = ImagemUtil.carregarImagem(caminhoCartaJogador, 120, 180);

        if (iconJogador != null) {
            imgCartaJogador.setIcon(iconJogador);
        } else {
            imgCartaJogador.setText("[Imagem não encontrada]");
            imgCartaJogador.setForeground(Color.YELLOW);
        }

        // Informações da carta
        String infoJogador = String.format(
            "<html><center><b>%s</b><br>ID: %d<br>" +
            "💪 Força: %d | 🏃 Veloidade: %d<br>" +
            "🎯 Habilidade: %d | ⚔️ Equipamento: %d<br>" +
            "🧠 Inteligência: %d</center></html>",
            cartaJogadorAtual.getNome(),
            cartaJogadorAtual.getId(),
            cartaJogadorAtual.getForca(),
            cartaJogadorAtual.getVelocidade(),
            cartaJogadorAtual.getHabilidade(),
            cartaJogadorAtual.getEquipamento(),
            cartaJogadorAtual.getInteligencia()
        );

        JLabel lblInfoJogador = new JLabel(infoJogador);
        lblInfoJogador.setFont(new Font("Arial", Font.PLAIN, 10));
        lblInfoJogador.setForeground(Color.WHITE);
        lblInfoJogador.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCartasJogador.add(Box.createVerticalGlue());
        painelCartasJogador.add(lblJogador);
        painelCartasJogador.add(Box.createVerticalStrut(5));
        painelCartasJogador.add(imgCartaJogador);
        painelCartasJogador.add(Box.createVerticalStrut(5));
        painelCartasJogador.add(lblInfoJogador);
        painelCartasJogador.add(Box.createVerticalGlue());

        // ===== CARTA DA MÁQUINA =====
        painelCartasMaquina.removeAll();
        painelCartasMaquina.setLayout(new BoxLayout(painelCartasMaquina, BoxLayout.Y_AXIS));

        JLabel lblMaquina = new JLabel("🔴 CARTA DA MÁQUINA");
        lblMaquina.setFont(new Font("Arial", Font.BOLD, 14));
        lblMaquina.setForeground(Color.RED);
        lblMaquina.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tenta carregar imagem da máquina (pode ser virada)
        JLabel imgCartaMaquina = new JLabel();
        imgCartaMaquina.setAlignmentX(Component.CENTER_ALIGNMENT);
        String idCartaMaquinaFormatado = gerarIdCarta(cartaMaquinaAtual.getId());
        String caminhoCartaMaquina = ImagemUtil.getCaminhoCartaPorId(idCartaMaquinaFormatado);
        ImageIcon iconMaquina = ImagemUtil.carregarImagem(caminhoCartaMaquina, 120, 180);

        if (iconMaquina != null) {
            imgCartaMaquina.setIcon(iconMaquina);
        } else {
            imgCartaMaquina.setText("[Carta oculta]");
            imgCartaMaquina.setForeground(Color.YELLOW);
        }

        // Informações da máquina (ocultas até a comparação)
        JLabel lblInfoMaquina = new JLabel("? ? ?");
        lblInfoMaquina.setFont(new Font("Arial", Font.PLAIN, 10));
        lblInfoMaquina.setForeground(Color.WHITE);
        lblInfoMaquina.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelCartasMaquina.add(Box.createVerticalGlue());
        painelCartasMaquina.add(lblMaquina);
        painelCartasMaquina.add(Box.createVerticalStrut(5));
        painelCartasMaquina.add(imgCartaMaquina);
        painelCartasMaquina.add(Box.createVerticalStrut(5));
        painelCartasMaquina.add(lblInfoMaquina);
        painelCartasMaquina.add(Box.createVerticalGlue());

        painelCartasJogador.revalidate();
        painelCartasJogador.repaint();
        painelCartasMaquina.revalidate();
        painelCartasMaquina.repaint();
    }

    /**
     * Converte ID numérico em formato de letra+número (A1, B2, etc)
     * Assumindo 6 cartas por série (A-E)
     */
    private String gerarIdCarta(int id) {
        int serie = (id - 1) / 6; // 0-4 para A-E
        int numero = (id - 1) % 6 + 1; // 1-6

        char letra = (char) ('A' + serie);
        return letra + String.valueOf(numero);
    }

    private void atualizarPlacar() {
        lblPlacarJogador.setText("Você: " + vitoriasJogador);
        lblPlacarMaquina.setText("Máquina: " + vitoriasMaquina);
    }

    private void voltarAoMenu() {
        this.dispose();
        new TelaMenu();
    }
}
