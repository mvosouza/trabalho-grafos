package br.unifor.grafos.algoritmos;

public class BellmanFord {

	private final Grafo grafo;

	private final Integer fonte;

	private Integer[] pesos;

	public BellmanFord(final Grafo grafo, Integer fonte) {
		this.grafo = grafo;
		this.fonte = fonte;
		this.pesos = new Integer[grafo.getNumeroDeVertices()];
		this.inicializarDistancias();
	}

	private void inicializarDistancias() {
		for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
			pesos[i] = Integer.MAX_VALUE;
		}

		pesos[fonte] = 0;
	}

	private boolean relaxamento(Aresta aresta) {
		if (pesos[aresta.destino()] > pesos[aresta.origem()] + aresta.valor()) {
			pesos[aresta.destino()] = pesos[aresta.origem()]
					+ aresta.valor();

			return true;
		}
		
		return false;
	}

	public void computar() {
		Integer[][] matriz = grafo.getMatrizDeAdjacencia();

		boolean relaxou = false;

		for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
			for (int j = 0; j < grafo.getNumeroDeVertices(); j++) {
				Aresta aresta = new Aresta(i, j, matriz[i][j]);
				relaxou = relaxamento(aresta);
			}

			if (!relaxou) {
				break;
			}
		}
	}

	public boolean temCiclosNegativos() {
		Integer[][] matriz = grafo.getMatrizDeAdjacencia();

		for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
			for (int j = 0; j < grafo.getNumeroDeVertices(); j++) {
				Aresta aresta = new Aresta(i, j, matriz[i][j]);

				if (pesos[aresta.destino()] > pesos[aresta.origem()] + aresta.valor()) {
					return true;
				}
			}
		}

		return false;
	}

	public void distanciaMaisCurta() {
		for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
			System.out.println("A distancia mais curta entre os vertices " + fonte + " e " + i + " eh " + pesos[i]);
		}

	}

}
