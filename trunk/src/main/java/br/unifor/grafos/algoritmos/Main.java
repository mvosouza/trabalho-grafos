package br.unifor.grafos.algoritmos;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// Ler arquivo e retorna o grafo
		Grafo grafo = new LeitorDeArquivo("src/main/resources/exemploMatriz.txt").ler();
		
		// Questao 01
		FloydWarshall floydWarshall = new FloydWarshall(grafo);
		floydWarshall.computar();
		
		// Questao 03 - Aqui voces instanciam o algoritmos de voces e facam a logica.
		BellmanFord bellmanFord = new BellmanFord(grafo, 0);
		bellmanFord.computar();
		bellmanFord.distanciaMaisCurta();
		
		System.out.println("Tem ciclos negativos? " + bellmanFord.temCiclosNegativos());
	}

}
