package br.unifor.grafos.algoritmos;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// Ler arquivo e retorna o grafo
		LeitorDeArquivo leitor = new LeitorDeArquivo("src/main/resources/exemploMatriz.txt");
		Grafo grafo = leitor.ler();
		
		// Questao 01
		FloydWarshall floydWarshall = new FloydWarshall(grafo);
		floydWarshall.computar();
		
		// Questao 03 - Aqui voces instanciam o algoritmos de voces e facam a logica.
		
	}

}
