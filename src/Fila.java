import java.util.NoSuchElementException;

public class Fila<E> {

    private Celula<E> frente;
    private Celula<E> tras;

    public Fila() {
        Celula<E> sentinela = new Celula<E>();
        frente = tras = sentinela;
    }

    public boolean vazia() {
        return frente == tras;
    }

    public void enfileirar(E item) {
        Celula<E> novaCelula = new Celula<E>(item);
        tras.setProximo(novaCelula);
        tras = tras.getProximo();
    }

    public E desenfileirar() {
        E item = consultarPrimeiro();

        Celula<E> primeiro = frente.getProximo();
        frente.setProximo(primeiro.getProximo());
        primeiro.setProximo(null);

		// Caso o item desenfileirado seja também o último da fila.
		if (primeiro == tras)
            tras = frente;
        }

        return item;
    }

    public E consultarPrimeiro() {

        if (vazia()) {
            throw new NoSuchElementException("Nao há nenhum item na fila!");
        }

        return frente.getProximo().getItem();
    }

    public int contarOcorrencias(E itemProcurado) {
        int ocorrencias = 0;
        Celula<E> aux = frente.getProximo();

        while (aux != null) {
            E itemAtual = aux.getItem();

            if (itemProcurado == null) {
                if (itemAtual == null) {
                    ocorrencias++;
                }
            } else if (itemProcurado.equals(itemAtual)) {
                ocorrencias++;
            }

            aux = aux.getProximo();
        }

        return ocorrencias;
    }

    public Fila<E> extrairLote(int numItens) {
        Fila<E> lote = new Fila<E>();

        if (numItens <= 0) {
            return lote;
        }

        int itensExtraidos = 0;

        while (!vazia() && itensExtraidos < numItens) {
            lote.enfileirar(desenfileirar());
            itensExtraidos++;
        }

        return lote;
    }

    public int tamanho() {
        int total = 0;
        Celula<E> aux = frente.getProximo();

        while (aux != null) {
            total++;
            aux = aux.getProximo();
        }

        return total;
    }

    public void imprimir() {
		
        Celula<E> aux;

		if (vazia())
            System.out.println("A fila está vazia!");
		else {
			aux = this.frente.getProximo();
            while (aux != null) {
                System.out.println(aux.getItem());
                aux = aux.getProximo();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder dadosFila = new StringBuilder();
        Celula<E> aux = frente.getProximo();

        while (aux != null) {
            dadosFila.append(aux.getItem()).append(System.lineSeparator());
            aux = aux.getProximo();
        }

        return dadosFila.toString();
    }
}