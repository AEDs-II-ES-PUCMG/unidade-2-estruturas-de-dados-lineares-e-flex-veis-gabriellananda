import java.util.NoSuchElementException;

public class Pilha<E> {

	private Celula<E> topo;
	private Celula<E> fundo;

	public Pilha() {
		Celula<E> sentinela = new Celula<E>();
		fundo = sentinela;
		topo = sentinela;
	}

	public boolean vazia() {
		return fundo == topo;
	}

	public void empilhar(E item) {
		topo = new Celula<E>(item, topo);
	}

	public E desempilhar() {
		E desempilhado = consultarTopo();
		topo = topo.getProximo();
		return desempilhado;
	}

	public E consultarTopo() {
		if (vazia()) {
			throw new NoSuchElementException("Nao há nenhum item na pilha!");
		}

		return topo.getItem();
	}

	/**
	 * Cria e devolve uma nova pilha contendo os primeiros numItens elementos
	 * do topo da pilha atual.
	 * 
	 * Os elementos são mantidos na mesma ordem em que estavam na pilha original.
	 * Caso a pilha atual possua menos elementos do que o valor especificado,
	 * uma exceção será lançada.
	 *
	 * @param numItens o número de itens a serem copiados da pilha original.
	 * @return uma nova instância de Pilha<E> contendo os numItens primeiros elementos.
	 * @throws IllegalArgumentException se a pilha não contém numItens elementos.
	 */
	public Pilha<E> subPilha(int numItens) {

		if (numItens < 0) {
			throw new IllegalArgumentException("A quantidade de itens não pode ser negativa.");
		}

		Pilha<E> auxiliar = new Pilha<>();
		Pilha<E> resultado = new Pilha<>();

		Celula<E> atual = topo;
		int contador = 0;

		while (atual != fundo && contador < numItens) {
			auxiliar.empilhar(atual.getItem());
			atual = atual.getProximo();
			contador++;
		}

		if (contador < numItens) {
			throw new IllegalArgumentException("A pilha não possui " + numItens + " itens.");
		}

		while (!auxiliar.vazia()) {
			resultado.empilhar(auxiliar.desempilhar());
		}

		return resultado;
	}

	public void imprimir() {
		Celula<E> atual = topo;
		while(atual!=fundo) {
			System.out.println(atual.getItem()); //Para pegar o valor armazenado na pilha.
			atual = atual.getProximo(); //Atualiza o atual para referenciar a próxima célula.
		}
	}

	void imprime_certo() {
		Celula<E> atual = topo;
		certo(atual);
	}

	void certo(Celula<E> atual) {
		if(atual!=fundo) {
			certo(atual.getProximo());
			System.out.println(atual.getItem());
		}
	}
}