package classes;
import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Floresta flo = new Floresta(6);
		
		 SwingUtilities.invokeLater(() -> {
			 Jogo jogo = new Jogo();	
			jogo.render(flo);

			 jogo.setVisible(true);
	        });
	
	}

}
