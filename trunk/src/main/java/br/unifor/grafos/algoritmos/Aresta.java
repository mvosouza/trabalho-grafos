package br.unifor.grafos.algoritmos;

public class Aresta {

	private int origem, destino, valor;

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

}
