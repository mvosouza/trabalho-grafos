package br.unifor.grafos.algoritmos;

import java.util.Comparator;

/**
 * TAD de Aresta
 * 
 * @author Daniel Alves
 * 
 */

public class Aresta implements Comparator<Aresta> {

	private int origem, destino, valor;
	
	public Aresta() {}

	public Aresta(int v, int a, int valor) {
		this.origem = v;
		this.destino = a;
		this.valor = valor;
	}

	public int origem() {
		return origem;
	}

	public int destino() {
		return destino;
	}

	public int valor() {
		return valor;
	}

	/**
	 * Usado nas comparacoes durante as operacoes de adicionar remover.
	 */

	@Override
	public int compare(Aresta aresta1, Aresta aresta2) {
		if (aresta1.valor() < aresta2.valor()) {
			return -1;
		} else if (aresta1.valor() == aresta2.valor()
				&& aresta1.origem() == aresta2.origem()
				&& aresta1.destino() == aresta2.destino()) {
			return 0;
		} else if (aresta1.valor() == aresta2.valor()) {
			return -1;
		} else if (aresta1.valor() > aresta2.valor()) {
			return 1;
		}

		return 0;
	}

	/**
	 * Usado nas comparacoes durante as operacoes de adicionar remover
	 */

	public boolean equals(Object obj) {

		Aresta aresta = (Aresta) obj;

		return (valor == aresta.valor && origem == aresta.origem && destino == aresta.destino);
	}

}
