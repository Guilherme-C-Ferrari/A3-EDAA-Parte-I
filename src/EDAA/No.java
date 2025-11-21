package EDAA;

public class No {
    private String creche;
    private int distancia;
    private No proximo;

    public No(String creche, int distancia) {
        this.creche = creche;
        this.distancia = distancia;
        this.proximo = null;
    }

    public No(String creche) {
        this(creche, 0);
    }

    public String getCreche() {
        return creche;
    }

    public void setCreche(String creche) {
        this.creche = creche;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
