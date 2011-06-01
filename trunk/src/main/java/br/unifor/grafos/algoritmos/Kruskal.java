package br.unifor.grafos.algoritmos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

public class Kruskal {

	private final Integer MAX = 999;

	private final Grafo grafo;

	private HashSet<Integer>[] florestaDeComponentes;

	private TreeSet<Aresta> todasAsArestas;
	
	private Vector<Aresta> arvoreMST;

	public Kruskal(final Grafo grafo) {
		this.grafo = grafo;
		this.florestaDeComponentes = new HashSet[MAX];
		this.todasAsArestas = new TreeSet<Aresta>(new Aresta());
		this.arvoreMST = new Vector<Aresta>(MAX);

		this.criaConjuntoDeArestasEFlorestaDeComponentes();
	}

	private void criaConjuntoDeArestasEFlorestaDeComponentes() {
		Integer[][] matrizDeAdjacencia = grafo.getMatrizDeAdjacencia();

		for (int i = 0; i < grafo.getNumeroDeVertices(); i++) {
			for (int j = 0; j < grafo.getNumeroDeVertices(); j++) {
				Aresta aresta = new Aresta(i, j, matrizDeAdjacencia[i][j]);
				this.todasAsArestas.add(aresta);

				if (florestaDeComponentes[aresta.origem()] == null) {
					florestaDeComponentes[aresta.origem()] = new HashSet<Integer>(
							2 * MAX);
					florestaDeComponentes[aresta.origem()].add(aresta.origem());
				}

				if (florestaDeComponentes[aresta.destino()] == null) {
					florestaDeComponentes[aresta.destino()] = new HashSet<Integer>(
							2 * MAX);
					florestaDeComponentes[aresta.destino()].add(aresta
							.destino());
				}
			}
		}
	}

	private boolean arestaConectaDuasArvoresDiferentes(Aresta aresta) {
		return !florestaDeComponentes[aresta.origem()]
				.equals(florestaDeComponentes[aresta.destino()]);
	}

	private void combinarDuasArvoresEmUma(Aresta aresta) {
		HashSet<Integer> arvoresOrigem;
		HashSet<Integer> arvoresDestino;

		int indiceDaArvore;

		if (florestaDeComponentes[aresta.origem()].size() > florestaDeComponentes[aresta.destino()].size()) {
			indiceDaArvore = aresta.origem();

			arvoresOrigem = florestaDeComponentes[aresta.destino()];
			arvoresDestino = florestaDeComponentes[indiceDaArvore];

		} else {
			indiceDaArvore = aresta.destino();
			
			arvoresOrigem = florestaDeComponentes[aresta.origem()];
			arvoresDestino = florestaDeComponentes[indiceDaArvore];
		}
		
		Object[] origens = arvoresOrigem.toArray();
		
		for (int i = 0; i < origens.length; i++) {
			
			int j = (Integer)origens[i];
			
			if (arvoresOrigem.remove(origens[i])) {
				arvoresDestino.add(j);
				florestaDeComponentes[j] = florestaDeComponentes[indiceDaArvore];
			}
		}
	}
	
	private void imprimeArvoreMST() {
		Aresta aresta;
		
		System.out.println("\nConjuntos disjuntos da floresta...");
		System.out.println("----------");
		
		for (int i = 0; i < arvoreMST.size(); i++) {
			aresta = arvoreMST.get(i);
			
			if (aresta.valor() != 0 && aresta.valor() != Integer.MAX_VALUE) {
				String temp = "(" + aresta.origem() + ", " + aresta.destino() + ")";
				
				System.out.println("Aresta " + i + " " + temp + " com peso: " + aresta.valor());
			}
		}
	}
	
	private void imprimeTodasArestas() {
		Iterator<Aresta> it = todasAsArestas.iterator();
		
		Aresta aresta;
		
		System.out.println("\nImprimindo fila de prioridade...");
		System.out.println("----------");
		
		while (it.hasNext()) {
			aresta = it.next();
			
			if (aresta.valor() != 0 && aresta.valor() != Integer.MAX_VALUE) {
				String temp = "(" + aresta.origem() + ", " + aresta.destino() + ")";
				
				System.out.println("Aresta " + temp + " com peso: " + aresta.valor());
			}
		}
	}
	
	public Integer custoTotal() {
		Integer custo = 0;
		
		Aresta arestaAtual;
		
		while (!arvoreMST.isEmpty()) {
			arestaAtual = arvoreMST.firstElement();
			
			if (arvoreMST.remove(arestaAtual)) {
				custo += arestaAtual.valor();
			}
		}
		
		return custo;
	}

	public void executar() {
		while (!todasAsArestas.isEmpty()) {

			Aresta arestaAtual = todasAsArestas.first();

			if (todasAsArestas.remove(arestaAtual)) {

				if (arestaConectaDuasArvoresDiferentes(arestaAtual)) {
					combinarDuasArvoresEmUma(arestaAtual);
					this.arvoreMST.add(arestaAtual);
				}
				
				imprimeArvoreMST();
				imprimeTodasArestas();
			}
		}
		
		System.out.println("\nCusto total: " + custoTotal());
	}

}
