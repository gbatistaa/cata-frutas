package classes;

public class Jogo {
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

  public void render() {
    // Renderizar
  }
}
