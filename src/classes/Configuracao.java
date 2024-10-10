/**
 * A classe Configuracao é responsável por gerenciar as configurações do jogo, incluindo dimensões, frutas e bichadas.
 */
package classes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe que representa a configuração do jogo.
 */
public class Configuracao {
    private int dimensao;
    private int pedras;
    private int nFrutasOuro;
    private Map<String, Integer> arvoresPorFruta;
    private Map<String, Integer> frutasNoChao;
    private float probBichadas;
    private int capacidadeMochila;
    private int bichadas;

    /**
     * Construtor da configuração do jogo.
     *
     * @param dimensao A dimensão do campo de jogo.
     * @param pedras O número de pedras no jogo.
     * @param arvoresPorFruta Mapa contendo o número de árvores por tipo de fruta.
     * @param frutasNoChao Mapa contendo o número de frutas no chão por tipo.
     * @param bichadas O número de bichadas no jogo.
     * @param capacidadeMochila A capacidade da mochila do jogador.
     */
    public Configuracao(int dimensao, int pedras, Map<String, Integer> arvoresPorFruta, Map<String, Integer> frutasNoChao, int bichadas, int capacidadeMochila) {
        this.dimensao = dimensao;
        this.pedras = pedras;
        this.arvoresPorFruta = arvoresPorFruta;
        this.frutasNoChao = frutasNoChao;
        this.bichadas = bichadas;
        this.capacidadeMochila = capacidadeMochila;
    }

    /**
     * Retorna a dimensão do campo de jogo.
     *
     * @return A dimensão do campo de jogo.
     */
    public int getDimensao() {
        return dimensao;
    }

    /**
     * Retorna o número de pedras no jogo.
     *
     * @return O número de pedras.
     */
    public int getPedras() {
        return pedras;
    }

    /**
     * Retorna o total de frutas por nome.
     *
     * @param nomeFruta O nome da fruta.
     * @return O total de frutas.
     */
    public int getTotalDeFrutaPorNome(Object nomeFruta) {
        if (arvoresPorFruta.containsKey(nomeFruta)) {
            return arvoresPorFruta.get(nomeFruta);
        } else {
            throw new IllegalArgumentException("Fruta do tipo '" + nomeFruta + "' não encontrada.");
        }
    }

    /**
     * Retorna o número de frutas no chão por nome.
     *
     * @param nomeFruta O nome da fruta.
     * @return O número de frutas no chão.
     */
    public int getNumFrutasNoChaoPorNome(String nomeFruta) {
        if (frutasNoChao.containsKey(nomeFruta)) {
            return frutasNoChao.get(nomeFruta);
        } else {
            throw new IllegalArgumentException("Fruta do tipo '" + nomeFruta + "' não encontrada.");
        }
    }

    /**
     * Retorna o total de frutas por instância.
     *
     * @param instanciaFruta A instância da fruta.
     * @return O total de frutas.
     */
    public int getTotalDeFrutaPorInstancia(Object instanciaFruta) {
        String fruta = instanciaFruta.getClass().getSimpleName().toLowerCase();
        if (arvoresPorFruta.containsKey(fruta)) {
            return arvoresPorFruta.get(fruta);
        } else {
            throw new IllegalArgumentException("Fruta do tipo '" + fruta + "' não encontrada.");
        }
    }

    /**
     * Retorna o número de frutas no chão por instância.
     *
     * @param instanciaFruta A instância da fruta.
     * @return O número de frutas no chão.
     */
    public int getNumFrutasNoChaoPorInstancia(Object instanciaFruta) {
        String fruta = instanciaFruta.getClass().getSimpleName().toLowerCase();
        if (frutasNoChao.containsKey(fruta)) {
            return frutasNoChao.get(fruta);
        } else {
            throw new IllegalArgumentException("Fruta do tipo '" + fruta + "' não encontrada.");
        }
    }

    /**
     * Retorna o mapa de árvores por fruta.
     *
     * @return O mapa de árvores por fruta.
     */
    public Map<String, Integer> getArvoresPorFruta() {
        return arvoresPorFruta;
    }

    /**
     * Retorna o mapa de frutas no chão.
     *
     * @return O mapa de frutas no chão.
     */
    public Map<String, Integer> getFrutasNoChao() {
        return frutasNoChao;
    }

    /**
     * Retorna o número de bichadas no jogo.
     *
     * @return O número de bichadas.
     */
    public int getBichadas() {
        return bichadas;
    }

    /**
     * Retorna a capacidade da mochila do jogador.
     *
     * @return A capacidade da mochila.
     */
    public int getCapacidadeMochila() {
        return capacidadeMochila;
    }

    /**
     * Lê a configuração do jogo a partir de uma string.
     *
     * @param configString A string contendo a configuração.
     * @return Um objeto Configuracao com os dados lidos.
     * @throws IllegalArgumentException Se a configuração estiver em um formato inválido.
     */
    public static Configuracao lerConfiguracaoPorString(String configString) throws IllegalArgumentException {
        Scanner scanner = new Scanner(configString);
        int dimensao = 0;
        int pedras = 0;
        Map<String, Integer> arvoresPorFruta = new HashMap<>();
        Map<String, Integer> frutasNoChao = new HashMap<>();
        int bichadas = 0;
        int capacidadeMochila = 0;

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] partes = linha.split(" ");
            switch (partes[0]) {
                case "dimensao":
                    dimensao = Integer.parseInt(partes[1]);
                    break;
                case "pedras":
                    pedras = Integer.parseInt(partes[1]);
                    break;
                case "bichadas":
                    bichadas = Integer.parseInt(partes[1]);
                    break;
                case "mochila":
                    capacidadeMochila = Integer.parseInt(partes[1]);
                    break;
                case "maracuja":
                case "laranja":
                case "abacate":
                case "acerola":
                case "coco":
                case "amora":
                case "goiaba":
                    if (partes.length == 3) {
                        String tipoFruta = partes[0];
                        int arvores = Integer.parseInt(partes[1]);
                        int noChao = Integer.parseInt(partes[2]);
                        arvoresPorFruta.put(tipoFruta, arvores);
                        frutasNoChao.put(tipoFruta, noChao);
                    } else {
                        throw new IllegalArgumentException("Formato inválido para a fruta " + partes[0]);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Chave desconhecida: " + partes[0]);
            }
        }
        scanner.close();
        return new Configuracao(dimensao, pedras, arvoresPorFruta, frutasNoChao, bichadas, capacidadeMochila);
    }

    /**
     * Lê a configuração do jogo a partir de um arquivo.
     *
     * @param caminhoArquivo O caminho para o arquivo de configuração.
     * @return Um objeto Configuracao com os dados lidos.
     * @throws IOException Se ocorrer um erro ao ler o arquivo.
     * @throws IllegalArgumentException Se a configuração estiver em um formato inválido.
     */
    public static Configuracao lerConfiguracao(String caminhoArquivo) throws IOException, IllegalArgumentException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        int dimensao = 0;
        int pedras = 0;
        Map<String, Integer> arvoresPorFruta = new HashMap<>();
        Map<String, Integer> frutasNoChao = new HashMap<>();
        int bichadas = 0;
        int capacidadeMochila = 0;

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(" ");
            switch (partes[0]) {
                case "dimensao":
                    dimensao = Integer.parseInt(partes[1]);
                    break;
                case "pedras":
                    pedras = Integer.parseInt(partes[1]);
                    break;
                case "bichadas":
                    bichadas = Integer.parseInt(partes[1]);
                    break;
                case "mochila":
                    capacidadeMochila = Integer.parseInt(partes[1]);
                    break;
                case "maracuja":
                case "laranja":
                case "abacate":
                case "acerola":
                case "coco":
                case "amora":
                case "goiaba":
                    if (partes.length == 3) {
                        String tipoFruta = partes[0];
                        int arvores = Integer.parseInt(partes[1]);
                        int noChao = Integer.parseInt(partes[2]);
                        arvoresPorFruta.put(tipoFruta, arvores);
                        frutasNoChao.put(tipoFruta, noChao);
                    } else {
                        throw new IllegalArgumentException("Formato inválido para a fruta " + partes[0]);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Chave desconhecida: " + partes[0]);
            }
        }
        reader.close();
        return new Configuracao(dimensao, pedras, arvoresPorFruta, frutasNoChao, bichadas, capacidadeMochila);
    }

    /**
     * Retorna uma representação em string da configuração do jogo.
     *
     * @return Uma string representando a configuração do jogo.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("dimensao ").append(dimensao).append("\n");
        sb.append("pedras ").append(pedras).append("\n");
        for (String fruta : arvoresPorFruta.keySet()) {
            sb.append(fruta).append(" ").append(arvoresPorFruta.get(fruta)).append(" ").append(frutasNoChao.get(fruta)).append("\n");
        }
        sb.append("bichadas ").append(bichadas).append("\n");
        sb.append("mochila ").append(capacidadeMochila).append("\n");
        return sb.toString();
    }
}
