package br.unifor.grafos.algoritmos;

public class Grafo {

	private Integer matrizAdjacencia[][];

	private Integer numVertices;

	private final int HIGHEST_VALUE = Integer.MAX_VALUE;

	public Grafo(int numVertices) {
		this.matrizAdjacencia = new Integer[numVertices][numVertices];

		this.numVertices = numVertices;

		for (int i = 0; i < this.numVertices; i++) {
			for (int j = 0; j < this.numVertices; j++) {
				this.matrizAdjacencia[i][j] = HIGHEST_VALUE;
			}
		}
	}

	public Grafo(int numVertices, int numArestas) {
		this.matrizAdjacencia = new Integer[numVertices][numArestas];
		this.numVertices = numVertices;
		for (int i = 0; i < this.numVertices; i++) {
			for (int j = 0; j < numArestas; j++) {
				this.matrizAdjacencia[i][j] = HIGHEST_VALUE;
			}
		}
	}

	public void insereVertice() {
		Integer x = numVertices + 1;

		Integer[][] matriz = new Integer[x][x];

		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < this.numVertices; j++) {
				matriz[i][j] = this.matrizAdjacencia[i][j];
			}
		}

		this.matrizAdjacencia = matriz;
		this.numVertices = numVertices + 1;
	}

	public void retiraVertice(int vertice) {
		if (verificaLinha(vertice)) {

			int newNumVertices = numVertices - 1;

			Integer newMat[][] = new Integer[newNumVertices][numVertices];

			for (int i = 0; i < numVertices; i++) {
				for (int j = 0; j < numVertices; j++) {
					if (j < vertice)
						newMat[j][i] = matrizAdjacencia[j][i];
					else if (j > vertice)
						newMat[j - 1][i] = matrizAdjacencia[j][i];
				}
			}

			this.matrizAdjacencia = newMat;
			this.numVertices = newNumVertices;
		} else {
			System.out.println("Vertice inexistente");
		}
	}

	private boolean verificaLinha(int l) {
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				if (i == l) {
					return true;
				}
			}
		}
		return false;
	}

	public void insereAresta(int v1, int v2, int peso) {
		this.matrizAdjacencia[v1][v2] = peso;
	}

	public boolean existeAresta(int v1, int v2) {
		return (this.matrizAdjacencia[v1][v2] > 0);
	}

	public boolean listaAdjVazia(int v) {
		for (int i = 0; i < this.numVertices; i++) {
			if (this.matrizAdjacencia[v][i] > 0) {
				return false;
			}
		}

		return true;
	}

	public Aresta retiraAresta(int v1, int v2) {
		if (this.matrizAdjacencia[v1][v2] == 0) {
			return null;
		}

		Aresta aresta = new Aresta(v1, v2, this.matrizAdjacencia[v1][v2]);
		this.matrizAdjacencia[v1][v2] = 0;
		return aresta;
	}

	public void imprime() {
		System.out.print("   ");

		for (int i = 0; i < this.numVertices; i++) {
			System.out.print(i + "   ");
		}

		System.out.println();

		for (int i = 0; i < this.numVertices; i++) {
			System.out.print(i + "  ");

			for (int j = 0; j < this.numVertices; j++) {
				System.out.print(this.matrizAdjacencia[i][j] + "   ");
			}

			System.out.println();
		}
	}

	public Integer[][] getMatrizDeAdjacencia() {
		return matrizAdjacencia;
	}

	public int getNumeroDeVertices() {
		return numVertices;
	}

}
