/**
 * A classe Jogo representa a lógica do jogo, incluindo o gerenciamento dos jogadores e do tabuleiro.
 */
package classes;

import java.awt.*;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
/**
 * Representa a lógica do jogo, incluindo o gerenciamento dos jogadores e do tabuleiro.
 */
public class Jogo extends JPanel {
    private JFrame parent;
    private JLabel labelTurno;
    private JLabel labelMovimentos;
    private JPanel painelInventario;
    private Jogador um, dois;
    private boolean ativo = false;
    private Configuracao config;
    private Floresta flo;
    private int movimentos = 0;
    private Boolean turnoJogadorUm = true;
    private Direcao ultimoMov;
    int turno = 1;
    int numMaracujasInvocados;
    int vencedorId = 0;

    

	/**
     * Construtor do jogo.
     */
    public Jogo(Floresta floresta, Configuracao config, JFrame parent)
    {
        Inicializar(floresta, config);
        this.parent = parent;
    }

    /**
     * Inicializa os componentes do jogo.
     *
     * @param floresta A floresta que contém as entidades do jogo.
     * @param config   A configuração do jogo, incluindo dimensões e capacidade da mochila.
     */
    private void Inicializar(Floresta floresta, Configuracao config) {
        movimentos = (int) (Math.random() * 12);
        System.out.println("MOVIMENTOS: " + movimentos);
        System.out.println("TOTAL DE MARACUJAS: " + config.getTotalDeFrutaPorNome("maracuja"));

        this.config = config;
        um = new Jogador(config.getDimensao() / 2, 0, Color.RED); // Inicializa jogador1 na posição (0, 0)
        um.setMochila(new Mochila(config.getCapacidadeMochila()));
        dois = new Jogador(config.getDimensao() / 2, config.getDimensao() - 1, Color.BLUE); // Inicializa jogador2 na posição (5, 5)
        dois.setMochila(new Mochila(config.getCapacidadeMochila()));

        this.flo = floresta;
        numMaracujasInvocados = config.getNumFrutasNoChaoPorNome("maracuja");
        Recursos.getInstancia().setFloresta(flo);
        // Configura o foco para o painel
        setFocusable(true);

        // Adiciona o KeyListener com uma classe interna anônima
        addKeyListener(criarKeyListener());
    }

    /**
     * Renderiza o tabuleiro do jogo de acordo com a configuração fornecida.
     *
     * @param config A configuração do jogo, incluindo dimensões do tabuleiro.
     */
    void render(Floresta floresta, Configuracao config) {
        // Define o layout principal como BorderLayout
        setLayout(new BorderLayout());

        // Painel do tabuleiro (à esquerda)
        JPanel tabuleiro = new JPanel(new GridLayout(config.getDimensao(), config.getDimensao()));

        // Cria os quadrados do tabuleiro
        for (int y = 0; y < config.getDimensao(); y++) {
            for (int x = 0; x < config.getDimensao(); x++) {
                int finalY = y;
                int finalX = x;

                JPanel quadrado = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        // Desenha a grama
                        Image backgroundImage = Recursos.getInstancia().carregarImagem("assets/grama2.png");
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Desenha a imagem de fundo

                        // Desenha a imagem da entidade, se existir
                        Image imagem = getImageParaEntidade(finalX, finalY); // Obtém a imagem da entidade
                        if (imagem != null) {
                            g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this); // Desenha a imagem no painel
                        }

                        // Verifica se a posição contém um dos jogadores
                        if (um.getX() == finalX && um.getY() == finalY) {
                            Image jog = Recursos.getInstancia().carregarImagem("assets/jogador1.png");
                            int width = obterLarguraPorAltura(jog, getHeight());
                            g.drawImage(jog, (getWidth() - width) / 2, 0, width, getHeight(), this); // Desenha a imagem no painel
                        }
                        if (dois.getX() == finalX && dois.getY() == finalY) {
                            Image jog = Recursos.getInstancia().carregarImagem("assets/jogador2.png");
                            int width = obterLarguraPorAltura(jog, getHeight());
                            g.drawImage(jog, (getWidth() - width) / 2, 0, width, getHeight(), this); // Desenha a imagem no painel
                        }
                    }
                };

                quadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tabuleiro.add(quadrado); // Adiciona o quadrado ao tabuleiro
            }
        }

        // Adiciona o tabuleiro ao centro
        add(tabuleiro, BorderLayout.CENTER);

        // Painel de mensagens e escolha de frutas (à direita)
        JPanel painelLateral = new JPanel(new BorderLayout());
        painelLateral.setPreferredSize(new Dimension(200, 300)); // Definir uma largura preferida para o painel lateral

        // Painel para exibir o turno e número de movimentos restantes
        JPanel painelInfoTurno = new JPanel();
        painelInfoTurno.setLayout(new BoxLayout(painelInfoTurno, BoxLayout.Y_AXIS)); // Layout vertical
        labelTurno = new JLabel("Turno: Jogador 1"); // Mensagem inicial
        labelMovimentos = new JLabel("Movimentos restantes: 5"); // Mensagem inicial

        painelInventario = new JPanel();
        painelInventario.setLayout(new GridLayout(0, 1)); // Layout para listar as frutas

        atualizarInventario();

        painelInfoTurno.add(labelTurno); // Adiciona o label do turno
        painelInfoTurno.add(labelMovimentos); // Adiciona o label de movimentos restantes

        // Área de mensagens
        JTextArea areaMensagens = new JTextArea();
        areaMensagens.setEditable(false); // Não permitir que o jogador edite as mensagens
        areaMensagens.setFocusable(false); // Impedir que a área de texto receba foco e eventos de clique

        JScrollPane scrollMensagens = new JScrollPane(areaMensagens);
        scrollMensagens.setPreferredSize(new Dimension(200, 100)); // Define uma altura preferida para a área de mensagens

        painelLateral.add(painelInfoTurno, BorderLayout.NORTH); // Adiciona o painel de informação no topo
        painelLateral.add(scrollMensagens, BorderLayout.SOUTH); // Adiciona a área de mensagens
        painelLateral.add(painelInventario, BorderLayout.CENTER); // Adiciona o painel de inventário

        // Adiciona o painel lateral ao lado direito
        add(painelLateral, BorderLayout.EAST);

        // Redireciona a saída do console para o JTextArea
        PrintStream printStream = new PrintStream(new CustomOutputStream(areaMensagens));
        System.setOut(printStream); // Redireciona o System.out para o JTextArea
        System.setErr(printStream); // Redireciona o System.err para o JTextArea (caso necessário)

    }



    //
    /**
     * Retorna a imagem da entidade na posição especificada.
     *
     * @param x A coordenada x da entidade.
     * @param y A coordenada y da entidade.
     * @return A imagem correspondente à entidade, ou null se não houver entidade.
     */
    public Image getImageParaEntidade(int x, int y) {
        Entidade entidade = flo.getEntidade(x, y);
        Recursos recursos = Recursos.getInstancia(); // Obtenha a instância única de Recursos

        if (entidade instanceof Pedra) {
            return recursos.carregarImagem("assets/pedra.png");
        } else if (entidade instanceof Arvore) {
            Arvore arvore = (Arvore) entidade;
            return recursos.carregarImagem("assets/arvore_" + arvore.getTipo().getClass().getSimpleName().toLowerCase() + ".png");
        } else if (entidade instanceof Fruta) {
            Fruta fruta = (Fruta) entidade;
            return recursos.carregarImagem("assets/fruta_" + fruta.getClass().getSimpleName().toLowerCase() + ".png");
        }

        return null;
    }
    /**
     * Retorna a imagem da entidade especifica
     *
     * @param entidade A entidade
     * @return A imagem correspondente à entidade, ou null se não houver entidade.
     */
    public Image getImagemParaEntidade(Entidade entidade) {
        Recursos recursos = Recursos.getInstancia(); // Obtenha a instância única de Recursos
        if (entidade instanceof Pedra) {
            return recursos.carregarImagem("assets/pedra.png");
        } else if (entidade instanceof Arvore) {
            Arvore arvore = (Arvore) entidade;
            return recursos.carregarImagem("assets/arvore_" + arvore.getTipo().getClass().getSimpleName().toLowerCase() + ".png");
        } else if (entidade instanceof Fruta) {
            Fruta fruta = (Fruta) entidade;
            return recursos.carregarImagem("assets/fruta_" + fruta.getClass().getSimpleName().toLowerCase() + ".png");
        }

        return null;
    }
    
    

    /**
     * Move o jogador na direção especificada, considerando as regras do jogo.
     *
     * @param jogador O jogador a ser movido.
     * @param dir     A direção para a qual o jogador deve se mover.
     */
    private void AoMover(Jogador jogador, Direcao dir) {
        int prevX = jogador.getX();
        int prevY = jogador.getY();

        jogador.mover(dir, 1);

        if(um.getX() == dois.getX() && um.getY() == dois.getY()) {
            jogador.setX(prevX);
            jogador.setY(prevY);
            Jogador empurrador = turnoJogadorUm ? um : dois;
            Jogador empurrado = turnoJogadorUm ? dois : um;
            empurrador.empurrar(empurrado);
            movimentos--;
            return;
        }

        // Verifica se o movimento resultou em uma posição válida
        if (jogador.getX() >= 0 && jogador.getX() < config.getDimensao() &&
                jogador.getY() >= 0 && jogador.getY() < config.getDimensao()) {

            // Se a nova posição do jogador contém uma Pedra
            if (flo.getEntidade(jogador.getX(), jogador.getY()) instanceof Pedra) {
                // Mover novamente na mesma direção para pular a pedra
                jogador.mover(dir, 1); // Mover mais 1 unidade

                // Verifica se o novo movimento também é válido
                if (jogador.getX() > 0 && jogador.getX() < config.getDimensao() - 1 &&
                        jogador.getY() > 0 && jogador.getY() < config.getDimensao() - 1) {
                    movimentos -= 3;
                } else {
                    // Se não for um movimento válido, retornar à posição anterior
                    jogador.setX(prevX);
                    jogador.setY(prevY);
                }
            } else {
                movimentos--;
            }
        } else {
            // Se o movimento não for válido, retornar à posição anterior
            jogador.setX(prevX);
            jogador.setY(prevY);
        }
    }

    /**
     * Invoca um Maracujá em uma posição aleatória ao redor das árvores disponíveis no mapa.
     *
     * Verifica se o número de Maracujás invocados não excede o total permitido.
     * Coleta todas as árvores e tenta invocar um Maracujá em posições adjacentes
     * (cima, baixo, esquerda ou direita). Se uma posição estiver vazia,
     * o Maracujá é instanciado e sua localização é impressa.
     * Se não for possível invocar um Maracujá, uma mensagem é exibida.
     */
    public void invocarMaracuja() {
        if(numMaracujasInvocados > config.getTotalDeFrutaPorNome("maracuja")) {
            System.out.println("Ja foram invocados todos os maracujas");
            return;
        }
        Random random = new Random();
        List<Arvore> arvores = new ArrayList<>();

        // Coletar todas as árvores
        for (int y = 0; y < config.getDimensao(); y++) {
            for (int x = 0; x < config.getDimensao(); x++) {
                if (flo.getEntidade(x, y) instanceof Arvore) {
                    arvores.add((Arvore) flo.getEntidade(x, y));
                }
            }
        }

        // Enquanto houver árvores disponíveis
        while ( !arvores.isEmpty()) {
            int indexArvore = random.nextInt(arvores.size());
            Arvore arvore = arvores.get(indexArvore);
            arvores.remove(indexArvore);

            // Definir direções possíveis
            ArrayList<int[]> direcoes = new ArrayList<>();
            direcoes.add(new int[]{1, 0});  // Direção para a direita
            direcoes.add(new int[]{-1, 0}); // Direção para a esquerda
            direcoes.add(new int[]{0, 1});  // Direção para baixo
            direcoes.add(new int[]{0, -1}); // Direção para cima

            // Enquanto houver direções disponíveis
            while (!direcoes.isEmpty()) {
                int index = random.nextInt(direcoes.size());
                int[] direcaoAleatoria = direcoes.get(index);
                direcoes.remove(index);

                // Verificar se a posição está vazia
                int novaPosX = arvore.getX() + direcaoAleatoria[0];
                int novaPosY = arvore.getY() + direcaoAleatoria[1];

                if(novaPosX < 0 || novaPosX >= config.getDimensao() || novaPosY < 0 || novaPosY >= config.getDimensao()) {
                    continue;
                }

                if (flo.getEntidade(novaPosX, novaPosY) == null) {
                    Maracuja maracuja = new Maracuja();
                    flo.setEntidade(novaPosX, novaPosY, maracuja);
                    System.out.println("MARACUJA INVOCADO EM X:" + novaPosX + ",Y:" + novaPosY);
                    numMaracujasInvocados++;
                    return; // Saia após instanciar o Maracujá
                }
            }
        }
        System.out.println("Não foi possível instanciar maracuja");
    }
    /**
     * Atualiza o painel de inventário, exibindo as frutas disponíveis
     * na mochila do jogador atual. Limpa o painel antes de adicionar
     * as frutas. Cada fruta é representada por um JLabel que, ao ser
     * clicado, consome a fruta correspondente e atualiza a interface.
     */
    private void atualizarInventario() {
        painelInventario.removeAll(); // Limpa o painel de inventário antes de atualizar
        List<Fruta> frutas = (turnoJogadorUm ? um : dois).getMochila().getFrutas();

        for (Fruta fruta : frutas) {
            // Obtenha a imagem da fruta, assumindo que há um método getImagem() em Fruta
            ImageIcon imagemFruta = new ImageIcon(getImagemParaEntidade(fruta)); // Altere aqui para a forma correta de obter a imagem
            JLabel labelFruta = new JLabel(imagemFruta); // Use a imagem como o ícone do JLabel

            if (fruta.isBichada()) {
                labelFruta.setOpaque(true); // Permite que o fundo do JLabel seja visível
                labelFruta.setBackground(new Color(0x5a228b)); // Define a cor de fundo roxa
            } else {
                labelFruta.setOpaque(false); // Mantém o fundo padrão
            }

            // Configurações do JLabel
            labelFruta.setText(fruta.getClass().getSimpleName()); // Define o nome da fruta como texto do JLabel
            labelFruta.setHorizontalTextPosition(JLabel.RIGHT); // Posiciona o texto à direita do ícone
            labelFruta.setVerticalTextPosition(JLabel.CENTER); // Centraliza o texto verticalmente em relação ao ícone
            labelFruta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cursor de mão ao passar o mouse

            // Aumenta o tamanho da imagem para melhor visibilidade
            Image imagemRedimensionada = imagemFruta.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            labelFruta.setIcon(new ImageIcon(imagemRedimensionada)); // Atualiza o ícone com a imagem redimensionada

            // Adiciona um MouseListener para consumir a fruta ao clicar
            labelFruta.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ((turnoJogadorUm ? um : dois).getMochila()).consumirFruta(fruta);
                    atualizarInventario(); // Atualiza o inventário após consumir a fruta
                    System.out.println("Consumiu a fruta: " + fruta.getClass().getSimpleName());
                }
            });

            painelInventario.add(labelFruta); // Adiciona a label da fruta ao painel de inventário
        }

        painelInventario.revalidate(); // Atualiza a interface
        painelInventario.repaint(); // Repaint para mostrar as alterações
    }



    /**
     * Cria um {@link KeyListener} para gerenciar a entrada de teclado do jogador.
     *
     * @return O {@link KeyListener} configurado.
     */
    public KeyListener criarKeyListener() {
        return new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                Jogador movedor = turnoJogadorUm ? um : dois;

                labelMovimentos.setText("Movimentos restantes: " + movimentos);

                if (movedor.isDoente()) {
                    movimentos = 0;
                    movedor.setDoente(false);
                }
                switch (keyCode) {
                    case KeyEvent.VK_M:
                        vencedorId = 1;
                        break;
                    case KeyEvent.VK_UP:
                        AoMover(movedor, Direcao.CIMA);
                        break;
                    case KeyEvent.VK_DOWN:
                        AoMover(movedor, Direcao.BAIXO);
                        break;
                    case KeyEvent.VK_LEFT:
                        AoMover(movedor, Direcao.ESQUERDA);
                        break;
                    case KeyEvent.VK_RIGHT:
                        AoMover(movedor, Direcao.DIREITA);
                        break;
                    case KeyEvent.VK_ENTER:
                        Entidade noLocal = flo.getEntidade(movedor.getX(), movedor.getY());
                        if(noLocal instanceof Fruta) {
                            movedor.coletar((Fruta) noLocal);
                            flo.setEntidade(movedor.getX(), movedor.getY(), null);
                            atualizarInventario();
                        }
                        break;
                    case KeyEvent.VK_Q:
                        movimentos = 0;
                        break;

                    default:
                        System.out.println("Você digitou um número que não corresponde a uma fruta");
                        break;
                }
                // Atualiza o desenho na tela
                repaint();
                System.out.println("MOVIMENTOS: " + movimentos);

                if(movimentos <= 0) {

                    labelTurno.setText(turnoJogadorUm ? "Turno: Jogador 1" : "Turno: Jogador 2");

                    if(flo.getEntidade(movedor.getX(), movedor.getY()) instanceof Arvore) {
                        Fruta f = ((Arvore) flo.getEntidade(movedor.getX(), movedor.getY())).TryDropFruta();
                        if(f != null)
                            movedor.coletar(f);
                    }
                    turnoJogadorUm = !turnoJogadorUm;

                    for(int y = 0; y < config.getDimensao(); y++) {
                        for (int x = 0; x < config.getDimensao(); x++) {
                            if (flo.getEntidade(x, y) instanceof Arvore) {
                                ((Arvore) flo.getEntidade(x, y)).passarTurno();
                            }
                        }
                    }

                    movimentos = (int) (Math.random() * 12);
                    labelMovimentos.setText("Movimentos restantes: " + movimentos);
                    System.out.println("\n\nTROCA DE TURNO");
                    System.out.println("MOVIMENTOS: " + movimentos);
                    if (turnoJogadorUm) {
                        System.out.println("Jogador vermelho joga.\n" + um);
                        turno++;
                    } else {
                        System.out.println("Jogador azul joga.\n" + dois);
                    }
                    if(turno % 2 == 0)
                        invocarMaracuja();

                    int maxPontos = config.getTotalDeFrutaPorNome("maracuja") / 2;
                    if(um.getPontosVitoria() > maxPontos) {
                        vencedorId = 1;
                    }
                    if(dois.getPontosVitoria() > maxPontos) {
                        vencedorId = 2;
                    }
                    atualizarInventario();
                    if (vencedorId > 0) {
                        System.out.println("VENCEDOR DECLARADO");
                        // Exibe um popup informando quem ganhou
                        String mensagemVencedor = "O jogador " + vencedorId + " venceu!";
                        int resposta = JOptionPane.showConfirmDialog(parent, mensagemVencedor, "Fim de Jogo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        // Se o botão "OK" for pressionado, retorna à tela de configuração
                        if (resposta == JOptionPane.OK_OPTION) {
                            // Aqui você pode chamar o método que retorna à tela de configuração
                            TelaDeConfig.exibir();
                            parent.dispose();
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Não implementado
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Não implementado
            }
        };
    }
    /**
     * Calcula a largura correta de uma imagem com base na altura fornecida,
     * mantendo a proporção original da imagem.
     *
     * @param imagem A imagem da qual se deseja obter a largura.
     * @param altura A altura desejada para a imagem.
     * @return A largura calculada com base na altura fornecida.
     */
    public int obterLarguraPorAltura(Image imagem, int altura) {
        // Obtém as dimensões originais da imagem
        int larguraOriginal = imagem.getWidth(null);
        int alturaOriginal = imagem.getHeight(null);

        // Calcula a proporção original
        double proporcao = (double) larguraOriginal / alturaOriginal;

        // Calcula a nova largura com base na altura fornecida
        return (int) (altura * proporcao);
    }

    /**
     * Dobra o numero de movimentos restantes na jogada.
     */
    public void dobrarMovimentos() {
        movimentos *= 2;
    }



}
