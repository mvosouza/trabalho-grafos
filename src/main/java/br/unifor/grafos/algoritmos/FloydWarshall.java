package br.unifor.grafos.algoritmos;

public class FloydWarshall {
	
	private final Integer[][] matrizAdjacencia;
	
	private final Integer numeroDeVertices;
	
	private Integer[][] matrizSaida;
	
	public FloydWarshall(final Grafo grafo) {
		this.matrizAdjacencia = grafo.getMatrizDeAdjacencia();
		this.numeroDeVertices = grafo.getNumeroDeVertices();
	}
	
	public void computar() {
		this.matrizSaida = this.matrizAdjacencia;
	
		this.imprime();
		
		for (int k = 0; k < this.numeroDeVertices; k++) {
			for (int i = 0; i < this.numeroDeVertices; i++) {
				
				Integer[][] matrizAnterior = this.matrizSaida;
				
				for (int j = 0; j < this.numeroDeVertices; j++) {
					this.matrizSaida[i][j] = Math.min(matrizAnterior[i][j], matrizAnterior[i][k] + matrizAnterior[k][j]);
				}
	
			}
			
			this.imprime();
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
				Integer valor = this.matrizSaida[i][j];
				
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
