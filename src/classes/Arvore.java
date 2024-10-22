package classes;

public class Arvore extends Entidade{
    private Fruta tipoFruta;
    private int turnosDesdeUltimoDrop = 999;

    public Arvore(Fruta tipoFruta) {
        this.tipoFruta = tipoFruta;
    }

    public Fruta getTipo() {
        return tipoFruta;
    }
    public void passarTurno() {turnosDesdeUltimoDrop++;}
    public Fruta TryDropFruta() {
        if (turnosDesdeUltimoDrop > Recursos.getInstancia().DelayTurnosDropArvore) {
            turnosDesdeUltimoDrop = 0;
            return Fruta.PorNome(tipoFruta.getClass().getSimpleName());
        }
        return null;
    }
}
