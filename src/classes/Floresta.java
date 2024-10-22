/**
 * A classe Floresta representa a floresta no jogo, contendo informações sobre as dimensões e tipos de frutas.
 */
package classes;

import java.util.Map;

public class Floresta {
    public int dimensao;
    public double posicao;
    public int nPedras;
    public int qtdFrutasOuro;
    public int qtdFrutasNormais;
    public Arvore tiposArvore[];
    public Fruta tiposFrutas[];
    private Entidade[][] mapa;

    /**
     * Construtor da floresta.
     */
    public Floresta() {
        super();
    }

    /**
     * Obtém a entidade na posição (x, y) da floresta.
     *
     * @param x Coordenada X.
     * @param y Coordenada Y.
     * @return A entidade na posição especificada, ou null se não houver.
     */
    public Entidade getEntidade(int x, int y) {
        return mapa[x][y];
    }

    /**
     * Define a entidade na posição (x, y) da floresta.
     *
     * @param x Coordenada X.
     * @param y Coordenada Y.
     * @param entidade A entidade a ser colocada na posição.
     */
    public void setEntidade(int x, int y, Entidade entidade) {
        mapa[x][y] = entidade;
        if(entidade != null) {
            entidade.setX(x);
            entidade.setY(y);
        }
    }

    /**
     * Remove a entidade da posição (x, y) da floresta.
     *
     * @param x Coordenada X.
     * @param y Coordenada Y.
     */
    public void removeEntidade(int x, int y) {
        mapa[x][y] = null; // Remove a entidade da posição
        System.out.println("A entidade foi removida (coletada)");
    }

    /**
     * Gera a floresta de acordo com as configurações.
     *
     * @param configuracao A configuração do jogo.
     */
    public void gerar(Configuracao configuracao) {
        // Define a dimensão da floresta com base na configuração
        this.dimensao = configuracao.getDimensao();
        this.mapa = new Entidade[dimensao][dimensao];

        // Define a quantidade de pedras
        this.nPedras = configuracao.getPedras();
        distribuirPedras();

        // Distribui as árvores e frutas
        Map<String, Integer> arvoresPorFruta = configuracao.getArvoresPorFruta();
        Map<String, Integer> frutasNoChao = configuracao.getFrutasNoChao();

        for (String tipoFruta : arvoresPorFruta.keySet()) {
            int numArvores = arvoresPorFruta.get(tipoFruta);
            distribuirArvores(tipoFruta, numArvores);

            int numFrutasNoChao = frutasNoChao.get(tipoFruta);
            distribuirFrutasNoChao(tipoFruta, numFrutasNoChao);
        }
    }

    private void distribuirPedras() {
        for (int i = 0; i < nPedras; i++) {
            // Colocar uma pedra em uma posição aleatória no mapa
            int x = (int) (Math.random() * dimensao);
            int y = (int) (Math.random() * dimensao);
            if (mapa[x][y] == null)
                setEntidade(x, y, new Pedra());
        }
    }

    private void distribuirArvores(String tipoFruta, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            int x = (int) (Math.random() * dimensao);
            int y = (int) (Math.random() * dimensao);
            if(mapa[x][y] == null) {
                setEntidade(x, y, new Arvore(Fruta.PorNome(tipoFruta)));

            }
        }
    }

    private void distribuirFrutasNoChao(String tipoFruta, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            int x = (int) (Math.random() * dimensao);
            int y = (int) (Math.random() * dimensao);
            if (mapa[x][y] == null)
                setEntidade(x, y, Fruta.PorNome(tipoFruta));
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Percorre cada linha da floresta (mapa)
        for (int i = 0; i < dimensao; i++) {
            // Percorre cada coluna da linha
            for (int j = 0; j < dimensao; j++) {
                if (mapa[i][j] instanceof Pedra) {
                    sb.append("P  "); // Representa uma pedra (ajustado para 2 caracteres)
                } else if (mapa[i][j] instanceof Arvore) {
                    Arvore arvore = (Arvore) mapa[i][j];
                    String tipoArvore = arvore.getTipo().getClass().getSimpleName();
                    sb.append(tipoArvore.substring(0, 2).toUpperCase()).append(" "); // Primeiras duas letras do tipo da árvore
                } else if (mapa[i][j] instanceof Fruta) {
                    Fruta fruta = (Fruta) mapa[i][j];
                    String tipoFruta = fruta.getClass().getSimpleName();
                    sb.append(tipoFruta.substring(0, 2).toLowerCase()).append(" "); // Primeiras duas letras do nome da fruta
                } else {
                    sb.append(".  "); // Espaço vazio na floresta (ajustado para 2 caracteres)
                }
            }
            sb.append("\n"); // Quebra de linha após cada linha da floresta
        }

        return sb.toString();
    }


}
