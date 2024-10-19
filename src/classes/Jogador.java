/**
 * A classe Jogador representa um jogador no jogo, que pode coletar frutas e interagir com outros jogadores.
 */
package classes;

import java.util.List;
import java.awt.*;
import java.util.Random;

public class Jogador extends Entidade {
    private Mochila mochila;
    private Color color;
    private int pontosVitoria = 0;
    private int multiplicadorPoder = 1;

    public Jogador(int x, int y, Color color) {
        setX(x);
        setY(y);
        this.color = color;
    }

    public int getPoder() { return mochila.getQuantidadeFrutas() * multiplicadorPoder; }

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

    public Color getColor() { return color; }

    /**
     * Coleta uma fruta e a adiciona à mochila do jogador.
     *
     * @param fruta A fruta a ser coletada.
     */
    public void coletar(Fruta fruta , Floresta flo) {
        if (mochila.adicionarFruta(fruta)) {
            fruta.aoColetar(this);
        }
       
		
    }

    /**
     * Empurra outro jogador.
     *
     * @param oponente O jogador a ser empurrado.
     */
    public void empurrar(Jogador oponente) {
        int fa = getPoder();
        int fd = oponente.getPoder();
        int log2fa = (int) Math.round(Math.log(fa + 1) / Math.log(2));
        int log2fd = (int) Math.round(Math.log(fd + 1) / Math.log(2));
        int empurrao = Math.max(0, log2fa - log2fd);

        Random random = new Random();
        Floresta floresta = Recursos.getInstancia().getFloresta();

        // DERRUBAR FRUTAS DA MOCHILA ALEATORIAMENTE
        for (int i = 0; i < empurrao && oponente.mochila.getQuantidadeFrutas() > 0; i++) {
            // Seleciona uma fruta aleatória da mochila do oponente
            Fruta frutaDerrubada = oponente.mochila.removerFrutaAleatoria();

            int posX = oponente.getX();
            int posY = oponente.getY();

            // Verifica as posições adjacentes
            int[][] direcoes = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Cima, Baixo, Esquerda, Direita
            boolean frutaColocada = false;

            for (int[] direcao : direcoes) {
                int novaX = posX + direcao[0];
                int novaY = posY + direcao[1];

                // Verifica se a nova posição está dentro dos limites da floresta
                if (novaX >= 0 && novaX < floresta.dimensao && novaY >= 0 && novaY < floresta.dimensao) {
                    if (floresta.getEntidade(novaX, novaY) == null) {
                        floresta.setEntidade(novaX, novaY, frutaDerrubada);
                        frutaColocada = true;
                        System.out.println("Fruta colocada em X:" + novaX + ",Y:" + novaY);
                        break;
                    }
                }
            }
        }
    }


    /**
     * Consome uma fruta.
     *
     * @param fruta A fruta a ser consumida.
     */
    public void consumirFruta(Fruta fruta) {
        // Lógica de consumir fruta
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Adiciona a posição do jogador=
        sb.append("Posição: (").append(getX()).append(", ").append(getY()).append(")\n");

        // Adiciona a lista de frutas na mochila
        List<Fruta> frutasNaMochila = mochila.getFrutas(); // Supondo que exista um método getFrutas() em Mochila
        sb.append("Frutas na Mochila:\n");

        if (frutasNaMochila.isEmpty()) {
            sb.append("Nenhuma fruta na mochila.\n");
        } else {
            for (Fruta fruta : frutasNaMochila) {
                sb.append("- ").append(fruta.getClass().getSimpleName()).append("\n"); // Supondo que Fruta tem um método getNome()
            }
        }

        return sb.toString(); // Retorna a representação em string do jogador
    }
}
