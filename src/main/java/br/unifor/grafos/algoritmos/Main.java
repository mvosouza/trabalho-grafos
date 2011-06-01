package br.unifor.grafos.algoritmos;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// Questao 01
		System.out.println("Questao 01");
		System.out.println("--------------------------------");
		Grafo grafo = new LeitorDeArquivo("src/main/resources/exemploQuestao01.txt").ler();
		FloydWarshall floydWarshall = new FloydWarshall(grafo);
		floydWarshall.computar();
		
		System.out.println("--------------------------------");
		
		// Questao 02
		System.out.println("Questao 02");
		grafo = new LeitorDeArquivo("src/main/resources/matrizQuestao02.txt").ler();
		Kruskal kruskal = new Kruskal(grafo);
		kruskal.executar();
		
		System.out.println("--------------------------------");
		
		// Questao 03
		System.out.println("Questao 03");
		grafo = new LeitorDeArquivo("src/main/resources/exemploMatriz.txt").ler();
		BellmanFord bellmanFord = new BellmanFord(grafo, 0);
		bellmanFord.computar();
		
		System.out.println("Tem ciclos negativos? " + bellmanFord.temCiclosNegativos());
	}

}
