package br.unifor.grafos.algoritmos;

public class Aresta {

	private int v, a, valor;

	public Aresta(int v, int a, int valor){
		this.v=v;
		this.a=a;
		this.valor=valor;
	}

	public int v() {
		return v;
	}

	public int a() {
		return a;
	}

	public int valor() {
		return valor;
	}
}

