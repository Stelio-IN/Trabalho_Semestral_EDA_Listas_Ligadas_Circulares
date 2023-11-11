package Main;
import java.io.Serializable;

class Venda implements Serializable {
    private String nomeItem;
    private int quantidade;

    public Venda(String nomeItem, int quantidade) {
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
