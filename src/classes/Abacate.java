package classes;

public class Abacate extends Fruta {
    @Override
    public void aoConsumir(Jogador jogador, Jogo jogo) {
        super.aoConsumir(jogador, jogo);
        jogador.dobrarPoderEmpurrao();
    }
}
