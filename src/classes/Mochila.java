package classes;

import java.util.ArrayList;
import java.util.List;

public class Mochila {
  private final int capacidade;
  private final List<Fruta> frutas;

  public Mochila(int capacidade) {
    this.capacidade = capacidade;
    this.frutas = new ArrayList<>();
  }

  public boolean adicionarFruta(Fruta fruta) {
    if (frutas.size() < capacidade) {
      frutas.add(fruta);
      return true;
    }
    return false;
  }

  public void removerFruta(Fruta fruta) {
    frutas.remove(fruta);
  }

  public int getCapacidade() {
    return capacidade;
  }

  public List<Fruta> getFrutas() {
    return frutas;
  }
}
