package classes;

public abstract class Entidade {
  private int x, y;
  private boolean estatico = false;

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public boolean isEstatico() {
    return estatico;
  }

  public void setEstatico(boolean estatico) {
    this.estatico = estatico;
  }

  public void movimentar(int novoX, int novoY) {
    this.x = novoX;
    this.y = novoY;
  }

  public void movimentar(Direcao dir) {
    switch (dir) {
      case CIMA:
        break;
      case BAIXO:
        break;
      case ESQUERDA:
        break;
      case DIREITA:
        break;
      default:
        break;
    }
  }
}
