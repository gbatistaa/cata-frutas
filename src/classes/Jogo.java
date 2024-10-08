package classes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Jogo extends  JPanel{
  private Jogador um, dois;
  private boolean ativo = false;
  private Configuracao config;
  private Floresta flo;

  public Jogo() {
	  um = new Jogador(0, 0, Color.RED);  // Inicializa jogador1 na posição (0, 0)
      dois = new Jogador(5, 5, Color.BLUE); // Inicializa jogador2 na posição (5, 5)

      // Configura o foco para o painel
      setFocusable(true);

      // Adiciona o KeyListener com uma classe interna anônima
      addKeyListener(new KeyListener() {
          @Override
          public void keyPressed(KeyEvent e) {
              int keyCode = e.getKeyCode();

              switch (keyCode) {
              // Movimento para o jogador1 (usando as setas do teclado)
              case KeyEvent.VK_UP:
                  um.mover(0, -1); // Move para cima
                  break;
              case KeyEvent.VK_DOWN:
                  um.mover(0, 1);  // Move para baixo
                  break;
              case KeyEvent.VK_LEFT:
                  um.mover(-1, 0); // Move para a esquerda
                  break;
              case KeyEvent.VK_RIGHT:
                  um.mover(1, 0);  // Move para a direita
                  break;

              // Movimento para o jogador2 (usando as teclas W, A, S, D)
              case KeyEvent.VK_W:
                  dois.mover(0, -1); // Move para cima
                  break;
              case KeyEvent.VK_S:
                  dois.mover(0, 1);  // Move para baixo
                  break;
              case KeyEvent.VK_A:
                  dois.mover(-1, 0); // Move para a esquerda
                  break;
              case KeyEvent.VK_D:
                  dois.mover(1, 0);  // Move para a direita
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
    //Configuracao resultado = new Configuracao();
    //resultado.foo = 10;
   // config = resultado;
  }

  public void update() {
    // Logica do jogo
    // Ler input
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
      for (int i = 0; i < config.getDimensao() * config.getDimensao(); i++) {
          JPanel quadrado = new JPanel();
          quadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
          quadrado.setBackground(Color.GREEN); // Cor de fundo para representar a floresta
          add(quadrado);
      }
      
     
     
      setLocation(null); // Centraliza a janela
      
  }
  
  }

