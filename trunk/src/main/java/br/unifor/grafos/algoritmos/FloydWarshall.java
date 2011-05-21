package br.unifor.grafos.algoritmos;

public class FloydWarshall {
	
	private final Integer[][] matrizAdjacencia;
	
	private final Integer numeroDeVertices;
	
	public FloydWarshall(final Integer[][] matrizAdjacencia, Integer numeroDeVertices) {
		this.matrizAdjacencia = matrizAdjacencia;
		this.numeroDeVertices = numeroDeVertices;
	}
	
	public void computar() {
		for (int k = 0; k < numeroDeVertices; k++) {
			for (int i = 0; i < numeroDeVertices; i++) {
				for (int j = 0; j < numeroDeVertices; j++) {
					this.matrizAdjacencia[i][j] = Math.min(matrizAdjacencia[i][j], matrizAdjacencia[i][k] + matrizAdjacencia[k][j]);
					this.imprime();
				}
			}
		}
	}
	
	public void imprime() {
		for (int i = 0; i < numeroDeVertices; i++) {
			for (int j = 0; j < numeroDeVertices; j++) {
				System.out.println(matrizAdjacencia[i][j]);
			}
		}
	}

}
