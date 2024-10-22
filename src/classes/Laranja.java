package classes;

public class Laranja extends Fruta {
    @Override
    public void aoConsumir(Jogador jogador, Jogo jogo) {
        super.aoConsumir(jogador, jogo);
        jogador.setDoente(false);
    }
}
