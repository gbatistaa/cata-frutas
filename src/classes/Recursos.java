package classes;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.Toolkit;
/**
 * Singleton que gerencia o carregamento e o cache de imagens,
 * além de armazenar a instância da floresta do jogo.
 */
public class Recursos {
    // Instância única (Singleton)
    private static Recursos instanciaUnica;

    // Cache de imagens
    private Map<String, Image> cacheImagens;

    private Floresta floresta;

    /**
     * O numero de turnos que deve passar antes de uma arvore drope uma fruta nova
     */
    public static final int DelayTurnosDropArvore = 5;

    /**
     * Construtor privado para evitar instâncias fora da classe.
     * Inicializa o cache de imagens.
     */
    private Recursos() {
        cacheImagens = new HashMap<>();
    }

    /**
     * Obtém a instância única da classe Recursos.
     *
     * @return A instância única de Recursos.
     */
    public static synchronized Recursos getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Recursos();
        }
        return instanciaUnica;
    }

    /**
     * Carrega uma imagem a partir do caminho especificado e utiliza o cache para melhorar o desempenho.
     *
     * @param caminho O caminho da imagem a ser carregada.
     * @return A imagem carregada do cache ou recém-carregada.
     */
    public Image carregarImagem(String caminho) {
        // Verifica se a imagem já está no cache
        if (!cacheImagens.containsKey(caminho)) {
            // Carrega a imagem e coloca no cache
            String caminhoAbsoluto = new File(caminho).getAbsolutePath();
            Image imagem = Toolkit.getDefaultToolkit().getImage(caminhoAbsoluto);
            cacheImagens.put(caminho, imagem);
        }
        // Retorna a imagem do cache
        return cacheImagens.get(caminho);
    }
    /**
     * Define a instância da floresta.
     *
     * @param floresta A instância da floresta a ser definida.
     */
    public void setFloresta(Floresta floresta) { this.floresta = floresta; }
    /**
     * Obtém a instância da floresta.
     *
     * @return A instância da floresta.
     */
    public Floresta getFloresta() { return floresta; }
}