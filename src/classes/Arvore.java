package classes;

public class Arvore extends Entidade{
    private Fruta tipoFruta;

    public Arvore(Fruta tipoFruta) {
        this.tipoFruta = tipoFruta;
    }

    public Fruta getTipo() {
        return tipoFruta;
    }

}
