package br.unifor.grafos.algoritmos;

public class Aresta {

	private int origem, destino, valor;

	public Aresta(int v, int a, int valor) {
		this.origem = v;
		this.destino = a;
		this.valor = valor;
	}

	public int v() {
		return origem;
	}

	public int a() {
		return destino;
	}

	public int valor() {
		return valor;
	}

}
