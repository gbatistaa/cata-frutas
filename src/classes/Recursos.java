package classes;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.Toolkit;

public class Recursos {
    // Instância única (Singleton)
    private static Recursos instanciaUnica;

    // Cache de imagens
    private Map<String, Image> cacheImagens;

    private Floresta floresta;

    // Construtor privado para evitar instâncias fora da classe
    private Recursos() {
        cacheImagens = new HashMap<>();
    }

    // Método para obter a instância única
    public static synchronized Recursos getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Recursos();
        }
        return instanciaUnica;
    }

    // Método para carregar a imagem e usar o cache
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

    public void setFloresta(Floresta floresta) { this.floresta = floresta; }
    public Floresta getFloresta() { return floresta; }
}