package br.unifor.grafos.algoritmos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

public class Kruskal {
  private final int MAXIMO_NOS = 999;
  private HashSet nos[];               // Array of connected components
  private TreeSet todasArestas;              // Priority queue of Edge objects
  private Vector novasArestas;            // Edges in Minimal-Spanning Tree
  
  Kruskal() {
    // Constructor
    nos = new HashSet[MAXIMO_NOS];      // Create array for components
    todasArestas = new TreeSet<Aresta>();  // Create empty priority queue
    novasArestas = new Vector(MAXIMO_NOS); // Create vector for MST edges
  }

  public int iniciar(int num_nodes, int vertices[], Integer matrix[][]) {
          
	     // Cria os nos
	      int  origem, destino, valor;
          for (int i=0; i<num_nodes; i++) {
                          for (int j=0; j<num_nodes; j++) {
                                          origem = i;
                                          destino   = j;
                                          valor = matrix[i][j];
                                          todasArestas.add(new Aresta(origem, destino, valor));  // Update priority queue
                                          if (nos[origem] == null) {
                                                  // Create set of connect components [singleton] for this node
                                                  nos[origem] = new HashSet(2*MAXIMO_NOS);
                                                  nos[origem].add(new Integer(origem));
                                          }
                  
                                          if (nos[destino] == null) {
                                                  // Create set of connect components [singleton] for this node
                                                  nos[destino] = new HashSet(2*MAXIMO_NOS);
                                                  nos[destino].add(new Integer(destino));
                                          }
                          }
          } 
        
          // Iterecao Principal de Kruskal
          int size = todasArestas.size();
          for (int i=0; i<size; i++) {
            Aresta arestaAtual = (Aresta) todasArestas.first();
            if (todasArestas.remove(arestaAtual)) {
              // successful removal from priority queue: allEdges

              if (nosEstaoEmSetsDiferentes(arestaAtual.origem(), arestaAtual.destino())) {
                // System.out.println("Nodes are in different sets ...");
                HashSet hsetOrigem, hSetDestino;
                int destinoHashSetIndex;

                if (nos[arestaAtual.origem()].size() > nos[arestaAtual.destino()].size()) {
                  // have to transfer all nodes including curEdge.to
                  hsetOrigem = nos[arestaAtual.destino()];
                  hSetDestino = nos[destinoHashSetIndex = arestaAtual.origem()];
                } else {
                  // have to transfer all nodes including curEdge.from
                  hsetOrigem = nos[arestaAtual.origem()];
                  hSetDestino = nos[destinoHashSetIndex = arestaAtual.destino()];
                }

                Object arrayOrigem[] = hsetOrigem.toArray();
                int transferSize = arrayOrigem.length;
                for (int j=0; j<transferSize; j++) {
                  // move each node from set: src into set: dst
                  // and update appropriate index in array: nodes
                  if (hsetOrigem.remove(arrayOrigem[j])) {
                    hSetDestino.add(arrayOrigem[j]);
                    nos[((Integer) arrayOrigem[j]).intValue()] = nos[destinoHashSetIndex];
                  } else {
                    // This is a serious problem
                    System.out.println("Something wrong: set union");
                    System.exit(1);
                  }
                }

                novasArestas.add(arestaAtual);
                // add new edge to MST edge vector
              } else {
                // System.out.println("Nodes are in the same set ... nothing to do here");
              }

            } else {
              // This is a serious problem
              System.out.println("TreeSet should have contained this element!!");
              System.exit(1);
            }
            imprimeSolucaoParcial();
            imprimeFilaDePrioridade();
          }
          
          return retornaCusto();
  }

  
  public void imprimeSolucaoParcial(){
	  // imprime all new Edges
	  System.out.println("**** Imprimindo Solu��o Parcial **** Conjuntos disjuntos da floresta \n");
	  for (int i=0; i<novasArestas.size(); i++){
		  System.out.println("Aresta : "+i+" com peso - "+((Aresta) novasArestas.get(i)).valor());
	  }
	  System.out.println("**** Imprimindo Solu��o Parcial **** Conjuntos disjuntos da floresta \n");
  }
  
  public void imprimeFilaDePrioridade(){
	  // imprime allEdges
	  System.out.println("**** Imprimindo Fila de Prioridade **** \n");
	  Iterator i = todasArestas.iterator();
//	  } 
	  int contador = 0;
		while (i.hasNext()) {
			System.out.println("Aresta : "+contador+" com peso - "+((Aresta) i.next()).valor());
			contador++;
		}
		System.out.println("**** Imprimindo Fila de Prioridade **** \n");
	  
  }

  private boolean nosEstaoEmSetsDiferentes(int a, int b) {
    // returns true if graph nodes (a,b) are in different
    // connected components, ie the set for 'a' is different
    // from that for 'b'
    return(!nos[a].equals(nos[b]));
  }

  private int retornaCusto() {
          int i=0;
          while (!novasArestas.isEmpty()) {
                  // for each edge in Vector of MST edges
                  Aresta e = (Aresta) novasArestas.firstElement();
                  i = i + e.valor();
                  novasArestas.remove(e);
          }
          return i;
        }

}
