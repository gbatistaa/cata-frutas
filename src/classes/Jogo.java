/**
 * A classe Jogo representa a lógica do jogo, incluindo o gerenciamento dos jogadores e do tabuleiro.
 */
package classes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Jogo extends JPanel {
    private Jogador um, dois;
    private boolean ativo = false;
    private Configuracao config;
    private Floresta flo;
    private int poderUsado = 0;
    private Boolean turnoJogadorUm = true;

    /**
     * Construtor do jogo.
     */
    public Jogo(Floresta floresta, Configuracao config)
    {
        this.config = config;
        um = new Jogador(config.getDimensao() / 2, 0, Color.RED); // Inicializa jogador1 na posição (0, 0)
        dois = new Jogador(config.getDimensao() / 2, config.getDimensao() - 1, Color.BLUE); // Inicializa jogador2 na posição (5, 5)
        this.flo = floresta;
        // Configura o foco para o painel
        setFocusable(true);

        // Adiciona o KeyListener com uma classe interna anônima
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        um.mover(0, -1); // Move para cima
                        break;
                    case KeyEvent.VK_DOWN:
                        um.mover(0, 1); // Move para baixo
                        break;
                    case KeyEvent.VK_LEFT:
                        um.mover(-1, 0); // Move para a esquerda
                        break;
                    case KeyEvent.VK_RIGHT:
                        um.mover(1, 0); // Move para a direita
                        break;
                    case KeyEvent.VK_W:
                        dois.mover(0, -1); // Move para cima
                        break;
                    case KeyEvent.VK_S:
                        dois.mover(0, 1); // Move para baixo
                        break;
                    case KeyEvent.VK_A:
                        dois.mover(-1, 0); // Move para a esquerda
                        break;
                    case KeyEvent.VK_D:
                        dois.mover(1, 0); // Move para a direita
                        break;
                }
                // Atualiza o desenho na tela
                repaint();
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

    public boolean isAtivo() {
        return ativo;
    }

    public Configuracao getConfig() {
        return config;
    }

    public void iniciar() {
        ativo = true;
    }

    public void carregarConfig(String path) {
        // Carrega a configuração do jogo a partir do arquivo especificado
    }

    public void update() {
        // Lógica do jogo
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ativo = false;
    }

    public void render(Configuracao config) {
        // Define o layout do tabuleiro (grade)
        setLayout(new GridLayout(config.getDimensao(), config.getDimensao()));

        // Cria os quadrados do tabuleiro
        for (int y = 0; y < config.getDimensao(); y++) {
            for (int x = 0; x < config.getDimensao(); x++) {
                int finalY = y;
                int finalX = x;
                JPanel quadrado = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
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
                add(quadrado);
            }
        }

        setLocation(new Point(0, 0)); // Centraliza a janela
    }



    // Método que retorna a imagem da entidade
    public Image getImageParaEntidade(int x, int y) {
        Entidade entidade = flo.getEntidade(x, y);
        if (entidade instanceof Pedra) {
            return carregarImagem("assets/pedra.png");
        } else if (entidade instanceof Arvore) {
            Arvore arvore = (Arvore) entidade;
            return carregarImagem("assets/arvore_" + arvore.getTipo().getClass().getSimpleName().toLowerCase() + ".png");
        } else if (entidade instanceof Fruta) {
            Fruta fruta = (Fruta) entidade;
            return carregarImagem("assets/fruta_" + fruta.getClass().getSimpleName().toLowerCase() + ".png");
        }

        // Verifica se a posição contém um dos jogadores
        if (um.getX() == x && um.getY() == y) {
            return carregarImagem("assets/null.png"); // Imagem do jogador um
        }
        if (dois.getX() == x && dois.getY() == y) {
            return carregarImagem("assets/null.png"); // Imagem do jogador dois
        }

        return null;
    }


    // Método auxiliar para carregar a imagem
    private Image carregarImagem(String caminho) {
        String caminhoAbsoluto = new File(caminho).getAbsolutePath();
        return Toolkit.getDefaultToolkit().getImage(caminhoAbsoluto);
    }



}
