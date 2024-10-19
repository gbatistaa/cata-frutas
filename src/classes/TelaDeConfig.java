package classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class TelaDeConfig extends JFrame {
    private JTextField dimensaoField, pedrasField, probBichadasField, capacidadeMochilaField;
    private JTextField maracujaField, laranjaField, abacateField, cocoField, acerolaField, amoraField, goiabaField; // Campos para frutas
    private JTextField  arvoresLaranjaField, arvoresAbacateField, arvoresCocoField, arvoresAcerolaField, arvoresAmoraField, arvoresGoiabaField; // Campos para árvores por fruta
    private JButton startButton, importButton, exportButton;

    public TelaDeConfig() {
        setTitle("Configuração do Jogo Cata-Frutas");
        setSize(650, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2, 6, 6));
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
        JTextField[] textFields = { dimensaoField, pedrasField, maracujaField, laranjaField, abacateField, cocoField, acerolaField, amoraField, goiabaField,  arvoresLaranjaField, arvoresAbacateField, arvoresCocoField, arvoresAcerolaField, arvoresAmoraField, arvoresGoiabaField, probBichadasField, capacidadeMochilaField };

        for (JTextField textField : textFields) {
            textField.setPreferredSize(new Dimension(80, 30));
        }

        // Botão para iniciar o jogo
        startButton = new JButton("Iniciar Jogo");

        importButton = new JButton("Importar Configuração");

        exportButton = new JButton("Exportar configuração");



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

        add(exportButton);
        add(importButton);

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
                    inicializarJogo(config);
                });
            }
        });

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(TelaDeConfig.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Aqui você pode realizar o tratamento para carregar a configuração do arquivo
                    try {
                        Configuracao config = Configuracao.lerConfiguracao(selectedFile.getAbsolutePath());
                        inicializarJogo(config);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria um JFileChooser para selecionar o local do arquivo
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salvar Configuração");

                // Exibe o diálogo e verifica se o usuário selecionou um arquivo
                int userSelection = fileChooser.showSaveDialog(TelaDeConfig.this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    // Adiciona a extensão .txt se o usuário não a especificou
                    if (!fileToSave.getName().endsWith(".txt")) {
                        fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                    }

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
                    arvoresPorFruta.put("laranja", Integer.parseInt(arvoresLaranjaField.getText()));
                    arvoresPorFruta.put("abacate", Integer.parseInt(arvoresAbacateField.getText()));
                    arvoresPorFruta.put("coco", Integer.parseInt(arvoresCocoField.getText()));
                    arvoresPorFruta.put("acerola", Integer.parseInt(arvoresAcerolaField.getText()));
                    arvoresPorFruta.put("amora", Integer.parseInt(amoraField.getText()));
                    arvoresPorFruta.put("goiaba", Integer.parseInt(arvoresGoiabaField.getText()));

                    // Cria a configuração do jogo
                    Configuracao config = new Configuracao(dimensao, pedras, arvoresPorFruta, frutasNoChao, pBichadas, capacidadeMochila);
                    String configString = config.toString(); // Converte a configuração para string

                    // Tenta salvar a configuração no arquivo
                    try (java.io.PrintWriter writer = new java.io.PrintWriter(fileToSave)) {
                        writer.println(configString);
                        JOptionPane.showMessageDialog(TelaDeConfig.this, "Configuração exportada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(TelaDeConfig.this, "Erro ao salvar a configuração: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


    }

    private void inicializarJogo(Configuracao config) {
        JFrame frame = new JFrame("Cata-frutas");
        Floresta f = new Floresta();
        Jogo jogo = new Jogo(f, config);
        f.gerar(config);
        System.out.println("Floresta criada: \n" + f);
        frame.add(jogo);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        jogo.render(config);
    }
}
