/**
 * A classe Mochila representa uma mochila que pode armazenar frutas.
 */
package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mochila {
    private  int capacidade;
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
    public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
    
    public int getQuantidadeFrutas() { return frutas.size(); }

    /**
     * Retorna a lista de frutas na mochila.
     *
     * @return Uma lista de frutas.
     */
    public List<Fruta> getFrutas() {
        return frutas;
    }

    /**
     * Remove uma fruta aleatória da mochila.
     *
     * @return A fruta removida, ou null se não houver frutas disponíveis.
     */
    public Fruta removerFrutaAleatoria() {
        if (frutas.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int indiceAleatorio = random.nextInt(frutas.size());

        Fruta fruta = frutas.remove(indiceAleatorio);

        return fruta;
    }

	
}
