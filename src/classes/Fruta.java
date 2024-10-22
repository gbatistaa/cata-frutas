/**
 * A classe Fruta é uma classe abstrata que representa frutas que podem ser coletadas no jogo.
 * Ela contém propriedades comuns a todas as frutas e métodos que definem seu comportamento.
 *
 * <p>As frutas têm um peso, e podem estar "bichadas", o que é determinado por uma configuração
 * que define a probabilidade de uma fruta estar bichada ao ser coletada.</p>
 *
 * <p>A classe também fornece um método estático para instanciar frutas específicas com base no nome.</p>
 *
 * @see Entidade
 */
package classes;

import java.util.Random;

public abstract class Fruta extends Entidade {
    private int peso = 1;
    private boolean bichada;

    /**
     * Retorna o peso da fruta.
     *
     * @return O peso da fruta.
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Define o peso da fruta.
     *
     * @param peso O novo peso da fruta.
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Verifica se a fruta está bichada com base em uma configuração de probabilidade.
     *
     * @param config A configuração que contém a probabilidade de a fruta estar bichada.
     * @return true se a fruta está bichada, false caso contrário.
     */
    public boolean isBichada(Configuracao config) {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(101);

        if (config.getProbBichadas() > numeroAleatorio) {
            this.bichada = true;
        } else {
            this.bichada = false;
        }
        return bichada;
    }

    /**
     * Define se a fruta está bichada.
     *
     * @param bichada O novo estado de bichada da fruta.
     */
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

    /**
     * Retorna uma instância de uma fruta específica com base no nome fornecido.
     *
     * @param nome O nome da fruta a ser instanciada.
     * @return Uma instância da fruta correspondente ou null se o nome for inválido.
     */
    public static Fruta PorNome(String nome) {
        switch (nome.toLowerCase()) {
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
