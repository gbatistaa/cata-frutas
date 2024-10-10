/**
 * A classe Main é o ponto de entrada para a aplicação.
 */
package classes;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Inicializa a interface gráfica da aplicação.
        SwingUtilities.invokeLater(() -> {
            TelaDeConfig tela = new TelaDeConfig();
            tela.setVisible(true);
        });
    }
}
