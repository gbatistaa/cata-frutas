package classes;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> {
			TelaDeConfig tela = new TelaDeConfig();
			tela.setVisible(true);
		});

	}

}
