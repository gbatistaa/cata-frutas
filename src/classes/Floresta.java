/**
 * A classe Floresta representa a floresta no jogo, contendo informações sobre as dimensões e tipos de frutas.
 */
package classes;

public class Floresta {
    public int dimensao;
    public double posicao;
    public int nPedras;
    public int qtdFrutasOuro;
    public int qtdFrutasNormais;
    public Arvore tiposArvore[];
    public Fruta tiposFrutas[];

    /**
     * Construtor da floresta.
     *
     * @param dimensao A dimensão da floresta.
     */
    public Floresta(int dimensao) {
        super();
        this.dimensao = dimensao;
    }
}
