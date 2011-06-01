package br.unifor.grafos.algoritmos;

/**
 * Algoritmo de Bellman Ford
 * 
 * @author Felipe Benevides
 *
 */

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
		this.inicializar();
	}
	
	/**
	 * Inicializa toda a lista de pesos com valor infinito e 
	 * todos os pais com valor nulo.
	 */

	private void inicializar() {
		for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
			pesos[i] = Integer.MAX_VALUE;
			pais[i] = null;
		}

		pesos[fonte] = 0;
	}
	
	/**
	 * Efetua o relaxamento.
	 * 
	 * Verifica se o peso do vertice origem somado com o peso da aresta
	 * que ligam (u,v) e' menor do que menor do que o peso do vertice destino.
	 * 
	 * @param aresta
	 * @return
	 */

	private boolean relaxamento(Aresta aresta) {
		if (pesos[aresta.origem()] + aresta.valor() < pesos[aresta.destino()]) {
			pesos[aresta.destino()] = pesos[aresta.origem()] + aresta.valor();
			pais[aresta.destino()] = aresta.origem();
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Executa o algoritmo.
	 * 
	 * Executa o relaxamento V-1 vezes, ou seja, a cada V-1 iteracoes
	 * atualiza os pesos e os pais dos vertices. 
	 */
	
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
	
	/**
	 * Verifica se tem ciclos negativos.
	 * 
	 * Percorre a matriz de adj., verifica se o peso do vertice de origem (u)
	 * somado com o peso da aresta (u,v) e' menor que o peso do vertice de destino
	 * 
	 * @return
	 */

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
