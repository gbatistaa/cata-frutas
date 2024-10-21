/**
 * A classe Jogo representa a lógica do jogo, incluindo o gerenciamento dos jogadores e do tabuleiro.
 */
package classes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;
/**
 * Representa a lógica do jogo, incluindo o gerenciamento dos jogadores e do tabuleiro.
 */
public class Jogo extends JPanel {
    private Jogador um, dois;
    private boolean ativo = false;
    private Configuracao config;
    private Floresta flo;
    private int movimentos = 0;
    private Boolean turnoJogadorUm = true;
    private Direcao ultimoMov;

    

	/**
     * Construtor do jogo.
     */
    public Jogo(Floresta floresta, Configuracao config)
    {
        Inicializar(floresta, config);
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

        this.config = config;
        um = new Jogador(config.getDimensao() / 2, 0, Color.RED); // Inicializa jogador1 na posição (0, 0)
        um.setMochila(new Mochila(config.getCapacidadeMochila()));
        dois = new Jogador(config.getDimensao() / 2, config.getDimensao() - 1, Color.BLUE); // Inicializa jogador2 na posição (5, 5)
        dois.setMochila(new Mochila(config.getCapacidadeMochila()));

        this.flo = floresta;
        Recursos.getInstancia().setFloresta(flo);
        // Configura o foco para o painel
        setFocusable(true);

        // Adiciona o KeyListener com uma classe interna anônima
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                Jogador movedor = turnoJogadorUm ? um : dois;
                if (movedor.isDoente()) {
					movimentos = 0;
					movedor.setDoente(false);
				}
                switch (keyCode) {
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
                            movedor.coletar((Fruta) noLocal , floresta);
                            flo.setEntidade(movedor.getX(), movedor.getY(), null);
                        }
                        break;
                    case KeyEvent.VK_1:
                    	int res = movedor.consumirCoco(movedor.getMochila(), config);
                        if (res == 0 ) {
                            movimentos *= 2;
                            System.out.println("MOVIMENTOS: " + movimentos);
                        }
                        break;
                    case KeyEvent.VK_2:
                        movedor.consumirAbacate(movedor.getMochila(), config);
                        break;
                    case KeyEvent.VK_3:
                        movedor.consumirLaranja(movedor.getMochila(), config);
                        break;
                    
                    default:
                        System.out.println("Você digitou um número que não corresponde a uma fruta");
                        break;
                }
                // Atualiza o desenho na tela
                repaint();
                System.out.println("MOVIMENTOS: " + movimentos);

                if(movimentos <= 0) {
                    turnoJogadorUm = !turnoJogadorUm;
                    movimentos = (int) (Math.random() * 12);
                    System.out.println("\n\nTROCA DE TURNO");
                    System.out.println("MOVIMENTOS: " + movimentos);
                    if (turnoJogadorUm) {
						System.out.println("Jogador vermelho joga.\n" + um);
					} else {
						System.out.println("Jogador azul joga.\n" + dois);

					}
                   // System.out.println(turnoJogadorUm ? um : dois);
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
        });
        
    }

    /**
     * Renderiza o tabuleiro do jogo de acordo com a configuração fornecida.
     *
     * @param config A configuração do jogo, incluindo dimensões do tabuleiro.
     */
    void render( Floresta floresta , Configuracao config  ) {
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
                            g.setColor(um.getColor()); // Usa a cor do jogador um
                            g.fillOval(0, 0, getWidth(), getHeight()); // Desenha o jogador um
                        }
                        if (dois.getX() == finalX && dois.getY() == finalY) {
                            g.setColor(dois.getColor()); // Usa a cor do jogador dois
                            g.fillOval(0, 0, getWidth(), getHeight()); // Desenha o jogador dois
                        }
                    }
                };

                quadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tabuleiro.add(quadrado); // Adiciona o quadrado ao tabuleiro
            }
        }

        // Adiciona o tabuleiro ao lado esquerdo
        add(tabuleiro, BorderLayout.CENTER);

        // Painel de mensagens e escolha de frutas (à direita)
        JPanel painelLateral = new JPanel(new BorderLayout());
        painelLateral.setPreferredSize(new Dimension(200, getHeight())); // Definir uma largura preferida para o painel lateral

        // Área de texto para exibir mensagens do jogo
        JTextArea areaMensagens = new JTextArea();
        areaMensagens.setEditable(false); // Não permitir que o jogador edite as mensagens
        JScrollPane scrollMensagens = new JScrollPane(areaMensagens);
        painelLateral.add(scrollMensagens, BorderLayout.CENTER);    
    

    
        


        // Adiciona o painel lateral ao lado direito
        add(painelLateral, BorderLayout.EAST);     

        

     // Redireciona a saída do console para o JTextArea
        PrintStream printStream = new PrintStream(new CustomOutputStream(areaMensagens));
        System.setOut(printStream); // Redireciona o System.out para o JTextArea
        System.setErr(printStream); // Redireciona o System.err para o JTextArea (caso necessário)
        setLocation(new Point(0, 0)); // Centraliza a janela
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



}
