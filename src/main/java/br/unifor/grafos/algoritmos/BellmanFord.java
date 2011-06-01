package br.unifor.grafos.algoritmos;

public class BellmanFord {

	private final Grafo grafo;

	private final Integer fonte;

	private Integer[] pesos;
	
	private Integer[] pais;

	public BellmanFord(final Grafo grafo, Integer fonte) {
		this.grafo = grafo;
		this.fonte = fonte;
		this.pesos = new Integer[grafo.getNumeroDeVertices()];
		this.pais = new Integer[grafo.getNumeroDeVertices()];
		this.inicializarDistancias();
	}

	private void inicializarDistancias() {
		for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
			pesos[i] = Integer.MAX_VALUE;
			pais[i] = null;
		}

		pesos[fonte] = 0;
	}

	private boolean relaxamento(Aresta aresta) {
		if (pesos[aresta.origem()] + aresta.valor() < pesos[aresta.destino()]) {
			pesos[aresta.destino()] = pesos[aresta.origem()] + aresta.valor();
			pais[aresta.destino()] = aresta.origem();
			
			return true;
		}
		
		return false;
	}
	
	public void computar() {
		Integer[][] matriz = grafo.getMatrizDeAdjacencia();

		boolean relaxou = false;
		
		for (int k = 1; k <= grafo.getNumeroDeVertices()-1; k++) {
			
			for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
				for (int j = 0; j < grafo.getNumeroDeVertices(); j++) {
					Aresta aresta = new Aresta(i, j, matriz[i][j]);
					relaxou = relaxamento(aresta);
				}
			}
			
			System.out.println("Iteracao numero: " + k);
			System.out.println("-----");
			this.imprimePesosDosVertices();
			this.imprimePaiDosVertices();
			System.out.println("-----");
		}
	}

	public boolean temCiclosNegativos() {
		Integer[][] matriz = grafo.getMatrizDeAdjacencia();

		for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
			for (int j = 0; j < grafo.getNumeroDeVertices(); j++) {
				Aresta aresta = new Aresta(i, j, matriz[i][j]);

				if (pesos[aresta.origem()] + aresta.valor() < pesos[aresta.destino()]) {
					return true;
				}
			}
		}

		return false;
	}

	public void imprimePesosDosVertices() {
		for (int i = 0; i < pesos.length; i++) {
			System.out.println("Peso do vertice:" + i + ":" + pesos[i]);
		}
	}
	
	public void imprimePaiDosVertices() {
		for (int i = 0; i < pesos.length; i++) {
			System.out.println("Pai de " + i + ": " + pais[i]);
		}
	}

}
