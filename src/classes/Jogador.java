/**
 * A classe Jogador representa um jogador no jogo, que pode coletar frutas e interagir com outros jogadores.
 */
package classes;

import java.awt.*;

public class Jogador extends Entidade {
    private Mochila mochila;
    private Color color;
    private int pontosVitoria = 0;
    private int poderForca = 1;
    private int poderDefesa = 1;

    public Jogador(int x, int y, Color color) {
        setX(x);
        setY(y);
        this.color = color;
    }

    public Mochila getMochila() {
        return mochila;
    }

    public void setMochila(Mochila mochila) {
        this.mochila = mochila;
    }

    public int getPontosVitoria() {
        return pontosVitoria;
    }

    public void setPontosVitoria(int pontosVitoria) {
        this.pontosVitoria = pontosVitoria;
    }

    public int getPoderForca() {
        return poderForca;
    }

    public void setPoderForca(int poderForca) {
        this.poderForca = poderForca;
    }

    public int getPoderDefesa() {
        return poderDefesa;
    }

    public void setPoderDefesa(int poderDefesa) {
        this.poderDefesa = poderDefesa;
    }

    /**
     * Coleta uma fruta e a adiciona à mochila do jogador.
     *
     * @param fruta A fruta a ser coletada.
     */
    public void coletar(Fruta fruta) {
        if (mochila.adicionarFruta(fruta)) {
            fruta.aoColetar(this);
        }
        // Lógica qualquer
    }

    /**
     * Empurra outro jogador.
     *
     * @param oponente O jogador a ser empurrado.
     */
    public void empurrar(Jogador oponente) {
        // Lógica de empurrar
    }

    /**
     * Consome uma fruta.
     *
     * @param fruta A fruta a ser consumida.
     */
    public void consumirFruta(Fruta fruta) {
        // Lógica de consumir fruta
    }
}
