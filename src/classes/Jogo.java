package classes;
import javax.swing.*;

public class Jogo extends JFrame {
  private Jogador um, dois;
  private boolean ativo = false;
  private Configuracao config;

  public Jogo() {
    um = new Jogador();
    dois = new Jogador();
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
    Configuracao resultado = new Configuracao();
    resultado.foo = 10;
    config = resultado;
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

  public void render(Floresta flo) {
	// Define o layout do tabuleiro (grade)
      setLayout(new GridLayout(flo.dimensao, flo.dimensao));

      // Cria os quadrados do tabuleiro
      for (int i = 0; i < flo.dimensao * flo.dimensao; i++) {
          JPanel quadrado = new JPanel();
          quadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
          quadrado.setBackground(Color.GREEN); // Cor de fundo para representar a floresta
          add(quadrado);
      }

      // Configurações da janela
      setTitle("Floresta - Jogo de Tabuleiro");
      setSize(600, 600); // Define o tamanho da janela
      setLocationRelativeTo(null); // Centraliza a janela
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao clicar no "X"
  }
}
