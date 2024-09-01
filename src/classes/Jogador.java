package classes;

public class Jogador extends Entidade {
	private Mochila mochila;
	private int pontosVitoria = 0;
	private int poderForca = 1;
	private int poderDefesa = 1;

	public Mochila getMochila() {
		return mochila;
	}

	public void setMochila(Mochila mochila) {
		this.mochila = mochila;
	}

	public int getPontosVitoria() {
		return pontosVitoria;
	}

	public void setPontosVitoria(int pontosVitoria) {
		this.pontosVitoria = pontosVitoria;
	}

	public int getPoderForca() {
		return poderForca;
	}

	public void setPoderForca(int poderForca) {
		this.poderForca = poderForca;
	}

	public int getPoderDefesa() {
		return poderDefesa;
	}

	public void setPoderDefesa(int poderDefesa) {
		this.poderDefesa = poderDefesa;
	}

	public void coletar(Fruta fruta) {
		if (mochila.adicionarFruta(fruta)) {
			fruta.aoColetar(this);
		}
		// Logica qualquer
	}

	public void empurrar(Jogador oponente) {
		// Logica de empurrar
	}

	public void consumirFruta(Fruta fruta) {
		// Logica de consumir fruta
	}
}
