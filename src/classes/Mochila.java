/**
 * A classe Mochila representa uma mochila que pode armazenar frutas.
 */
package classes;

import java.util.ArrayList;
import java.util.List;

public class Mochila {
    private final int capacidade;
    private final List<Fruta> frutas;

    /**
     * Construtor da mochila.
     *
     * @param capacidade A capacidade máxima da mochila.
     */
    public Mochila(int capacidade) {
        this.capacidade = capacidade;
        this.frutas = new ArrayList<>();
    }

    /**
     * Adiciona uma fruta à mochila.
     *
     * @param fruta A fruta a ser adicionada.
     * @return true se a fruta foi adicionada com sucesso, false se a mochila estiver cheia.
     */
    public boolean adicionarFruta(Fruta fruta) {
        if (frutas.size() < capacidade) {
            frutas.add(fruta);
            return true;
        }
        return false;
    }

    /**
     * Remove uma fruta da mochila.
     *
     * @param fruta A fruta a ser removida.
     */
    public void removerFruta(Fruta fruta) {
        frutas.remove(fruta);
    }

    /**
     * Retorna a capacidade da mochila.
     *
     * @return A capacidade da mochila.
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Retorna a lista de frutas na mochila.
     *
     * @return Uma lista de frutas.
     */
    public List<Fruta> getFrutas() {
        return frutas;
    }
}
