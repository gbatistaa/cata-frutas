/**
 * A classe Fruta é uma classe abstrata que representa frutas que podem ser coletadas no jogo.
 */
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

    /**
     * Define a lógica que deve ocorrer ao coletar a fruta.
     *
     * @param coletor O jogador que coletou a fruta.
     */
    public void aoColetar(Jogador coletor) {
        // Lógica ao coletar fruta
    }

    public static Fruta PorNome(String nome) {
        switch(nome.toLowerCase()) {
            case "abacate": return new Abacate();
            case "acerola": return new Acerola();
            case "amora": return new Amora();
            case "coco": return new Coco();
            case "goiaba": return new Goiaba();
            case "laranja": return new Laranja();
            case "maracuja": return new Maracuja();
            default:
                System.err.println("Fruta invalida: " + nome);
                return null;
        }
    }
}
