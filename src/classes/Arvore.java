/**
 * A classe Arvore representa uma árvore que pode produzir frutas.
 */
package classes;

public class Arvore extends Entidade {
    private Fruta tipoFruta;
    private int turnosDesdeUltimoDrop = 999;

    /**
     * Construtor da árvore.
     *
     * @param tipoFruta O tipo de fruta que a árvore pode produzir.
     */
    public Arvore(Fruta tipoFruta) {
        this.tipoFruta = tipoFruta;
    }

    /**
     * Retorna o tipo de fruta que a árvore pode produzir.
     *
     * @return O tipo de fruta.
     */
    public Fruta getTipo() {
        return tipoFruta;
    }

    /**
     * Incrementa o contador de turnos desde o último drop de fruta.
     */
    public void passarTurno() {
        turnosDesdeUltimoDrop++;
    }

    /**
     * Tenta fazer a árvore produzir uma fruta.
     *
     * @return A fruta produzida, ou null se não for possível.
     */
    public Fruta TryDropFruta() {
        if (turnosDesdeUltimoDrop > Recursos.DelayTurnosDropArvore) {
            turnosDesdeUltimoDrop = 0;
            return Fruta.PorNome(tipoFruta.getClass().getSimpleName());
        }
        return null;
    }
}
