package classes;

public abstract class Fruta extends Entidade {
  private int peso = 1;
  private boolean bichada = false;

  public int getPeso() {
    return peso;
  }

  public void setPeso(int peso) {
    this.peso = peso;
  }

  public boolean isBichada() {
    return bichada;
  }

  public void setBichada(boolean bichada) {
    this.bichada = bichada;
  }

  public void aoColetar(Jogador coletor) {
    // Logica ao coletar fruta
  }
}
