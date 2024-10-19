/**
 * A classe Entidade é uma classe abstrata que representa uma entidade no jogo, como jogadores ou frutas.
 */
package classes;

public abstract class Entidade {
    private int x;
    private int y;

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

    /**
     * Move a entidade para uma nova posição.
     *
     * @param deltaX A mudança na coordenada x.
     * @param deltaY A mudança na coordenada y.
     */
    public void mover(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    /**
     * Move o jogador em uma direção específica por um número determinado de casas.
     *
     * @param direcao A direção para a qual o jogador deve se mover. Pode ser uma das direções: CIMA, BAIXO, DIREITA ou ESQUERDA.
     * @param casas O número de casas a serem movidas na direção especificada.
     */
    public void mover(Direcao direcao, int casas) {
        switch(direcao) {
            case Direcao.CIMA -> y -= casas;
            case Direcao.BAIXO -> y += casas;
            case Direcao.DIREITA -> x += casas;
            case Direcao.ESQUERDA -> x -= casas;
        }
    }
}
