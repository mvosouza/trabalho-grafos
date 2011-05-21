package br.unifor.grafos.algoritmos;

class Grafo {

	private int mat[][];

	private int numVertices;

	private final int highestValue = Integer.MAX_VALUE;

	public Grafo(int numVertices) {
		this.mat = new int[numVertices][numVertices];

		this.numVertices = numVertices;
		
		for (int i = 0; i < this.numVertices; i++) {
			for (int j = 0; j < this.numVertices; j++) {
				this.mat[i][j] = highestValue;
			}
		}
	}

	public Grafo(String matrixFileName) {

	}

	public Grafo(int numVertices, int numArestas) {
		this.mat = new int[numVertices][numVertices];
		this.numVertices = numVertices;
		for (int i = 0; i < this.numVertices; i++) {
			for (int j = 0; j < this.numVertices; j++) {
				this.mat[i][j] = highestValue;
			}
		}
	}

	public void insereVertice() {
		int x = numVertices + 1;
		
		int[][] matriz = new int[x][x];
		
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < this.numVertices; j++) {
				matriz[i][j] = this.mat[i][j];
			}
		}
		
		this.mat = matriz;
		this.numVertices = numVertices + 1;
	}

	public void retiraVertice(int vertice) {
		if (verificaLinha(vertice)) {
			
			int newNumVertices = numVertices - 1;
			
			int newMat[][] = new int[newNumVertices][numVertices];
			
			for (int i = 0; i < numVertices; i++) {
				for (int j = 0; j < numVertices; j++) {
					if (j < vertice)
						newMat[j][i] = mat[j][i];
					else if (j > vertice)
						newMat[j - 1][i] = mat[j][i];
				}
			}
			
			this.mat = newMat;
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
		this.mat[v1][v2] = peso;
	}

	public boolean existeAresta(int v1, int v2) {
		return (this.mat[v1][v2] > 0);
	}

	public boolean listaAdjVazia(int v) {
		for (int i = 0; i < this.numVertices; i++) {
			if (this.mat[v][i] > 0) {
				return false;
			}
		}
			
		return true;
	}

	public Aresta retiraAresta(int v1, int v2) {
		if (this.mat[v1][v2] == 0) {
			return null;
		}
		
		Aresta aresta = new Aresta(v1, v2, this.mat[v1][v2]);
		this.mat[v1][v2] = 0;
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
				System.out.print(this.mat[i][j] + "   ");
			}

			System.out.println();
		}
	}

	public int numVertices() {
		return this.numVertices;
	}

	public Grafo grafoSimplesBasico(Grafo g) {
		Grafo grafoSB = g;
		
		for (int i = 0; i < g.numVertices; i++) {
			for (int j = 0; j < this.numVertices; j++) {
				if (i != j && g.mat[i][j] != 0) {
					grafoSB.mat[i][j] = 1;
				}
			}
		}

		return grafoSB;
	}

}
