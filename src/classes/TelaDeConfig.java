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
    private JTextField numeroFrutasOuroField, frutasNoChaoField , arvoresPorFrutaField;
    private JButton startButton;

    /**
     * Construtor da classe TelaDeConfig.
     * Inicializa a interface gráfica e os componentes necessários.
     */
    public TelaDeConfig() {
        // Configurações básicas da janela
        setTitle("Configuração do Jogo Cata-Frutas");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2, 10, 10));
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Campos de entrada de dados
        JLabel dimensaoLabel = new JLabel("Dimensão do tabuleiro (m):");
        dimensaoField = new JTextField();

        JLabel pedrasLabel = new JLabel("Quantidade de pedras:");
        pedrasField = new JTextField();        

        JLabel frutasNoChaoLabel = new JLabel("Escolha o número das frutas no chão:");
        frutasNoChaoField = new JTextField();
        JLabel instrucoesArLabel = new JLabel("Exemplo: maracuja:2,abacate:2,goiaba:1,acerola:2,...");

        JLabel arvoresPorFrutaLabel = new JLabel("Escolha o número de cada árvore");
        arvoresPorFrutaField = new JTextField();
        JLabel instrucoesAr2Label = new JLabel("Exemplo: coco:1,abacate:1,goiaba:1,acerola:2,... ");

        JLabel probBichadasLabel = new JLabel("Probabilidade de uma fruta estar bichada:");
        probBichadasField = new JTextField();

        JLabel capacidadeMochilaLabel = new JLabel("Capacidade da mochila:");
        capacidadeMochilaField = new JTextField();

        // Array de JTextFields
        JTextField[] textFields = { dimensaoField, pedrasField,arvoresPorFrutaField, frutasNoChaoField,
                probBichadasField, capacidadeMochilaField };

        // Define o tamanho preferido para todos os JTextFields
        for (JTextField textField : textFields) {
            textField.setPreferredSize(new Dimension(80, 30));
        }

        // Botão para iniciar o jogo
        startButton = new JButton("Iniciar Jogo");

        // Adiciona os componentes à janela
        add(dimensaoLabel);
        add(dimensaoField);

        add(pedrasLabel);
        add(pedrasField);       

        add(frutasNoChaoLabel);
        add(frutasNoChaoField);
        add(instrucoesArLabel);
        add(new JLabel()); // Espaço vazio

        add(arvoresPorFrutaLabel);
        add(arvoresPorFrutaField);
        add(instrucoesAr2Label);
        add(new JLabel()); // Espaço vazio

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
                Map<String, Integer> frutasNoChao = parseInputToMap(frutasNoChaoField.getText());
                Map<String, Integer> arvoresPorFruta = parseInputToMap(frutasNoChaoField.getText());

                // Cria a configuração do jogo
                 
                    Configuracao config = new Configuracao( dimensao,  pedras,  arvoresPorFruta,
                			 frutasNoChao,  pBichadas,  capacidadeMochila);
                    // Usando o objeto config para carregar a próxima tela do jogo
                    System.out.println("Configuração criada: " + config);

                    SwingUtilities.invokeLater(() -> {
                        JFrame frame = new JFrame("Cata-frutas");
                        Jogo jogo = new Jogo(); // Instancia o jogo com o objeto floresta.

                        frame.add(jogo); // Adiciona o painel Jogo ao JFrame
                        frame.setSize(600, 600); // Define o tamanho da janela
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        frame.setResizable(false); // Opcional: impede o redimensionamento da janela
                        frame.setVisible(true); // Torna a janela visível
                        jogo.render(config);
                    });
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
