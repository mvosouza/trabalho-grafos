package br.unifor.grafos.algoritmos;

public class FloydWarshall {
	
	private final Integer[][] matrizAdjacencia;
	
	private final Integer numeroDeVertices;
	
	public FloydWarshall(final Grafo grafo) {
		this.matrizAdjacencia = grafo.getMatrizDeAdjacencia();
		this.numeroDeVertices = grafo.getNumeroDeVertices();
	}
	
	public void computar() {
		Integer numeroDeIteracoes = 0;
		
		for (int k = 0; k < numeroDeVertices; k++) {
			for (int i = 0; i < numeroDeVertices; i++) {
				for (int j = 0; j < numeroDeVertices; j++) {
					System.out.println("Execucao numero: " + ++numeroDeIteracoes);
					System.out.println("------------");
					
					this.matrizAdjacencia[i][j] = Math.min(matrizAdjacencia[i][j], matrizAdjacencia[i][k] + matrizAdjacencia[k][j]);
					this.imprime();
					
					System.out.println();
				}
			}
		}
	}
	
	public void imprime() {
		System.out.print("   ");

		for (int i = 0; i < this.numeroDeVertices; i++) {
			System.out.print(i+1 + "   ");
		}

		System.out.println();

		for (int i = 0; i < this.numeroDeVertices; i++) {
			System.out.print(i+1 + "  ");

			for (int j = 0; j < this.numeroDeVertices; j++) {
				Integer valor = this.matrizAdjacencia[i][j];
				
				if (valor == Integer.MAX_VALUE) {
					System.out.print("inf ");
				} else {
					System.out.print(valor + "   ");
				}
			}

			System.out.println();
		}
	}

}
