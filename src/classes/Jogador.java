/**
 * A classe Jogador representa um jogador no jogo, que pode coletar frutas e interagir com outros jogadores.
 * Cada jogador possui uma mochila, que armazena as frutas coletadas, e pode empurrar outros jogadores.
 */
package classes;

import java.util.List;
import java.awt.*;
import java.util.Random;

public class Jogador extends Entidade {
    private Mochila mochila;
    private Color color;
    private int multiplicadorPoder = 1;
    private boolean doente = false;

    /**
     * Cria um novo jogador na posição especificada com a cor fornecida.
     *
     * @param x    A coordenada x do jogador.
     * @param y    A coordenada y do jogador.
     * @param color A cor do jogador.
     */
    public Jogador(int x, int y, Color color) {
        setX(x);
        setY(y);
        this.color = color;
    }

    /**
     * Obtém o poder do jogador, calculado com base na quantidade de frutas na mochila
     * multiplicada pelo multiplicador de poder.
     *
     * @return O poder do jogador.
     */
    public int getPoder() {
        return mochila.getQuantidadeFrutas();
    }

    /**
     * Dobra o poder de empurrão (força de ataque) do jogador.
     */
    public void dobrarPoderEmpurrao() {
        multiplicadorPoder *= 2;
    }

    /**
     * Obtém a mochila do jogador.
     *
     * @return A mochila do jogador.
     */
    public Mochila getMochila() {
        return mochila;
    }

    /**
     * Define a mochila do jogador.
     *
     * @param mochila A mochila a ser definida.
     */
    public void setMochila(Mochila mochila) {
        this.mochila = mochila;
    }

    /**
     * Obtém os pontos de vitória do jogador, calculando a quantidade de frutas do tipo
     * Maracuja na mochila.
     *
     * @return Os pontos de vitória do jogador.
     */
    public int getPontosVitoria() {
        List<Fruta> frutasNaMochila = mochila.getFrutas();
        int pontosVitoria = 0;
        for (Fruta fruta : frutasNaMochila) {
            if (fruta instanceof Maracuja) {
                pontosVitoria++;
            }
        }
        return pontosVitoria;
    }

    /**
     * Obtém a cor do jogador.
     *
     * @return A cor do jogador.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Coleta uma fruta e a adiciona à mochila do jogador.
     *
     * @param fruta A fruta a ser coletada.
     */
    public void coletar(Fruta fruta) {
        if (mochila.adicionarFruta(fruta)) {
            System.out.println("Coletou " + fruta.getClass().getSimpleName());
        }
    }

    /**
     * Empurra outro jogador, derrubando frutas da mochila do oponente com base na
     * força do jogador e do oponente.
     *
     * @param oponente O jogador a ser empurrado.
     */
    public void empurrar(Jogador oponente) {
        int fa = getPoder();
        int fd = oponente.getPoder();
        int log2fa = (int) Math.round(Math.log(fa + 1) / Math.log(2));
        int log2fd = (int) Math.round(Math.log(fd + 1) / Math.log(2));
        int empurrao = Math.max(0, log2fa - log2fd);
        multiplicadorPoder = 1;
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
     * Verifica se o jogador está doente.
     *
     * @return True se o jogador estiver doente; caso contrário, false.
     */
    public boolean isDoente() {
        return doente;
    }

    /**
     * Define o estado de doença do jogador.
     *
     * @param doente O estado de doença a ser definido.
     */
    public void setDoente(boolean doente) {
        this.doente = doente;
    }

    /**
     * Retorna uma representação em string do jogador, incluindo sua posição,
     * pontos de vitória e frutas na mochila.
     *
     * @return A representação em string do jogador.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Adiciona a posição do jogador
        sb.append("Posição: (").append(getX()).append(", ").append(getY()).append(")\n");
        sb.append("Pontos:").append(getPontosVitoria()).append("\n");

        // Adiciona a lista de frutas na mochila
        List<Fruta> frutasNaMochila = mochila.getFrutas();
        sb.append("Frutas na Mochila:\n");

        if (frutasNaMochila.isEmpty()) {
            sb.append("Nenhuma fruta na mochila.\n");
        } else {
            for (Fruta fruta : frutasNaMochila) {
                sb.append("- ").append(fruta.getClass().getSimpleName()).append("\n");
            }
        }

        return sb.toString();
    }
}
