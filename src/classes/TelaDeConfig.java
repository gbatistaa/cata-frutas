package classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Classe que representa a tela de configuração do jogo "Cata-Frutas".
 * Permite ao usuário definir as configurações iniciais do jogo, como dimensões do tabuleiro,
 * quantidade de pedras, frutas e outras configurações.
 */
public class TelaDeConfig extends JFrame {
    private JTextField dimensaoField, pedrasField, probBichadasField, capacidadeMochilaField;
    private JTextField numeroFrutasOuroField, frutasNoChaoField;
    private JButton startButton;

    /**
     * Construtor da classe TelaDeConfig.
     * Inicializa a interface gráfica e os componentes necessários.
     */
    public TelaDeConfig() {
        // Configurações básicas da janela
        setTitle("Configuração do Jogo Cata-Frutas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 10, 10));
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Campos de entrada de dados
        JLabel dimensaoLabel = new JLabel("Dimensão do tabuleiro (m):");
        dimensaoField = new JTextField();

        JLabel pedrasLabel = new JLabel("Quantidade de pedras:");
        pedrasField = new JTextField();

        JLabel numeroFrutasOuroLabel = new JLabel("Quantidade de frutas ouro:");
        numeroFrutasOuroField = new JTextField();

        JLabel frutasNoChaoLabel = new JLabel("Frutas normais no chão:");
        frutasNoChaoField = new JTextField();

        JLabel probBichadasLabel = new JLabel("Probabilidade de uma fruta estar bichada:");
        probBichadasField = new JTextField();

        JLabel capacidadeMochilaLabel = new JLabel("Capacidade da mochila:");
        capacidadeMochilaField = new JTextField();

        // Array de JTextFields
        JTextField[] textFields = { dimensaoField, pedrasField, numeroFrutasOuroField, frutasNoChaoField,
                probBichadasField, capacidadeMochilaField };

        // Define o tamanho preferido para todos os JTextFields
        for (JTextField textField : textFields) {
            textField.setPreferredSize(new Dimension(100, 30));
        }

        // Botão para iniciar o jogo
        startButton = new JButton("Iniciar Jogo");

        // Adiciona os componentes à janela
        add(dimensaoLabel);
        add(dimensaoField);

        add(pedrasLabel);
        add(pedrasField);

        add(numeroFrutasOuroLabel);
        add(numeroFrutasOuroField);

        add(frutasNoChaoLabel);
        add(frutasNoChaoField);

        add(probBichadasLabel);
        add(probBichadasField);

        add(capacidadeMochilaLabel);
        add(capacidadeMochilaField);

        add(new JLabel()); // Espaço vazio
        add(startButton);

        // Ação do botão de iniciar jogo
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pega os dados do formulário
                int dimensao = Integer.parseInt(dimensaoField.getText());
                int pedras = Integer.parseInt(pedrasField.getText());
                float pBichadas = Float.parseFloat(probBichadasField.getText());
                int capacidadeMochila = Integer.parseInt(capacidadeMochilaField.getText());

                // Processa os campos de Map (árvores por fruta e frutas no chão)
                int nFrutasOuro = Integer.parseInt(numeroFrutasOuroField.getText());
                Map<String, Integer> frutasNormaisNoChao = parseInputToMap(frutasNoChaoField.getText());

                // Cria a configuração do jogo
                if (nFrutasOuro > 0) {
                    Configuracao config = new Configuracao(dimensao, pedras, nFrutasOuro, frutasNormaisNoChao,
                            pBichadas, capacidadeMochila);
                    // Usando o objeto config para carregar a próxima tela do jogo
                    System.out.println("Configuração criada: " + config);

                    SwingUtilities.invokeLater(() -> {
                        JFrame frame = new JFrame("Jogo da Floresta");
                        Jogo jogo = new Jogo(); // Instancia o jogo com o objeto floresta.

                        frame.add(jogo); // Adiciona o painel Jogo ao JFrame
                        frame.setSize(600, 600); // Define o tamanho da janela
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        frame.setResizable(false); // Opcional: impede o redimensionamento da janela
                        frame.setVisible(true); // Torna a janela visível
                        jogo.render(config);
                    });
                }

            }
        });
    }

    /**
     * Converte o input de string no formato fruta:quantidade para um Map.
     * 
     * @param input A string de entrada contendo pares no formato fruta:quantidade.
     * @return Um mapa contendo frutas e suas respectivas quantidades.
     */
    private Map<String, Integer> parseInputToMap(String input) {
        Map<String, Integer> map = new HashMap<>();
        String[] pairs = input.split(","); // Cada par fruta:quantidade separado por vírgula
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                map.put(keyValue[0].trim(), Integer.parseInt(keyValue[1].trim()));
            }
        }
        return map;
    }

}
