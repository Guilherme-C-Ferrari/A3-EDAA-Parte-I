package EDAA;

class ListaSimples {
    private No cabeca;
    private int tamanho;

    public ListaSimples() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    // Adiciona uma creche ao final da lista
    public void adicionar(String creche) {
        No novo_no = new No(creche);
        if (cabeca == null) {
            cabeca = novo_no;
        } else {
            No atual = cabeca;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novo_no);
        }
        tamanho++;
    }

    // Retorna o índice da creche (posição na matriz)
    public int getIndice(String creche) {
        No atual = cabeca;
        int indice = 0;
        while (atual != null) {
            if (atual.getCreche().equals(creche)) {
                return indice;
            }
            atual = atual.getProximo();
            indice++;
        }
        return -1; // Não encontrado
    }

    // Retorna o nome da creche pelo índice
    public String getCreche(int indice) {
        if (indice < 0 || indice >= tamanho) {
            return null;
        }
        No atual = cabeca;
        for (int i = 0; i < indice; i++) {
            atual = atual.getProximo();
        }
        return atual.getCreche();
    }

    public No getCabeca() {
        return cabeca;
    }

    public void setCabeca(No cabeca) {
        this.cabeca = cabeca;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}