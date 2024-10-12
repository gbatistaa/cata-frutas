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

public class TelaDeConfig extends JFrame {
    private JTextField dimensaoField, pedrasField, probBichadasField, capacidadeMochilaField;
    private JTextField maracujaField, laranjaField, abacateField, cocoField, acerolaField, amoraField, goiabaField; // Campos para frutas
    private JTextField arvoresMaracujaField, arvoresLaranjaField, arvoresAbacateField, arvoresCocoField, arvoresAcerolaField, arvoresAmoraField, arvoresGoiabaField; // Campos para árvores por fruta
    private JButton startButton;

    public TelaDeConfig() {
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

        JLabel frutasNoChaoLabel = new JLabel("Frutas no chão (quantidade):");
        maracujaField = new JTextField();
        laranjaField = new JTextField();
        abacateField = new JTextField();
        cocoField = new JTextField();
        acerolaField = new JTextField();
        amoraField = new JTextField();
        goiabaField = new JTextField();

        JLabel arvoresPorFrutaLabel = new JLabel("Árvores por fruta (quantidade):");
        arvoresMaracujaField = new JTextField();
        arvoresLaranjaField = new JTextField();
        arvoresAbacateField = new JTextField();
        arvoresCocoField = new JTextField();
        arvoresAcerolaField = new JTextField();
        arvoresAmoraField = new JTextField();
        arvoresGoiabaField = new JTextField();

        JLabel probBichadasLabel = new JLabel("Probabilidade de uma fruta estar bichada:");
        probBichadasField = new JTextField();

        JLabel capacidadeMochilaLabel = new JLabel("Capacidade da mochila:");
        capacidadeMochilaField = new JTextField();

        // Array de JTextFields para garantir tamanho consistente
        JTextField[] textFields = { dimensaoField, pedrasField, maracujaField, laranjaField, abacateField, cocoField, acerolaField, amoraField, goiabaField, arvoresMaracujaField, arvoresLaranjaField, arvoresAbacateField, arvoresCocoField, arvoresAcerolaField, arvoresAmoraField, arvoresGoiabaField, probBichadasField, capacidadeMochilaField };

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

        // Frutas no chão
        add(frutasNoChaoLabel);
        add(new JLabel()); // Espaço vazio
        add(new JLabel("Maracujá:"));
        add(maracujaField);
        add(new JLabel("Laranja:"));
        add(laranjaField);
        add(new JLabel("Abacate:"));
        add(abacateField);
        add(new JLabel("Coco:"));
        add(cocoField);
        add(new JLabel("Acerola:"));
        add(acerolaField);
        add(new JLabel("Amora:"));
        add(amoraField);
        add(new JLabel("Goiaba:"));
        add(goiabaField);

        // Árvores por fruta
        add(arvoresPorFrutaLabel);
        add(new JLabel()); // Espaço vazio
        add(new JLabel("Maracujá:"));
        add(arvoresMaracujaField);
        add(new JLabel("Laranja:"));
        add(arvoresLaranjaField);
        add(new JLabel("Abacate:"));
        add(arvoresAbacateField);
        add(new JLabel("Coco:"));
        add(arvoresCocoField);
        add(new JLabel("Acerola:"));
        add(arvoresAcerolaField);
        add(new JLabel("Amora:"));
        add(arvoresAmoraField);
        add(new JLabel("Goiaba:"));
        add(arvoresGoiabaField);

        // Outros campos
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

                // Cria os mapas para frutas no chão e árvores por fruta
                Map<String, Integer> frutasNoChao = new HashMap<>();
                frutasNoChao.put("maracuja", Integer.parseInt(maracujaField.getText()));
                frutasNoChao.put("laranja", Integer.parseInt(laranjaField.getText()));
                frutasNoChao.put("abacate", Integer.parseInt(abacateField.getText()));
                frutasNoChao.put("coco", Integer.parseInt(cocoField.getText()));
                frutasNoChao.put("acerola", Integer.parseInt(acerolaField.getText()));
                frutasNoChao.put("amora", Integer.parseInt(amoraField.getText()));
                frutasNoChao.put("goiaba", Integer.parseInt(goiabaField.getText()));

                Map<String, Integer> arvoresPorFruta = new HashMap<>();
                arvoresPorFruta.put("maracuja", Integer.parseInt(arvoresMaracujaField.getText()));
                arvoresPorFruta.put("laranja", Integer.parseInt(arvoresLaranjaField.getText()));
                arvoresPorFruta.put("abacate", Integer.parseInt(arvoresAbacateField.getText()));
                arvoresPorFruta.put("coco", Integer.parseInt(arvoresCocoField.getText()));
                arvoresPorFruta.put("acerola", Integer.parseInt(arvoresAcerolaField.getText()));
                arvoresPorFruta.put("amora", Integer.parseInt(arvoresAmoraField.getText()));
                arvoresPorFruta.put("goiaba", Integer.parseInt(arvoresGoiabaField.getText()));

                // Cria a configuração do jogo
                Configuracao config = new Configuracao(dimensao, pedras, arvoresPorFruta, frutasNoChao, pBichadas, capacidadeMochila);
                System.out.println("Configuração criada: " + config);

                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Cata-frutas");
                    Jogo jogo = new Jogo();
                    Floresta f = new Floresta();
                    f.gerar(config);
                    System.out.println("Floresta criada: \n" + f);
                    frame.add(jogo);
                    frame.setSize(600, 600);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);
                    frame.setVisible(true);
                    jogo.render(config);
                });
            }
        });
    }
}
