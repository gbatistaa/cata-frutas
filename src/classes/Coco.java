package classes;

public class Coco extends Fruta {

    @Override
    public void aoConsumir(Jogador jogador, Jogo jogo) {
        super.aoConsumir(jogador, jogo);
        jogo.dobrarMovimentos();
    }
}
