package br.unifor.grafos.algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDeArquivo {
	
	private final File arquivo;
	
	private final FileReader reader;
	
	public LeitorDeArquivo(String nomeDoArquivo) throws FileNotFoundException {
		this.arquivo = new File(nomeDoArquivo);
		this.reader = new FileReader(this.arquivo);
	}
	
	public Grafo ler() throws NumberFormatException, IOException {
		BufferedReader leitor = new BufferedReader(this.reader);
		
		String line;
		Integer numeroDaLinha = 0;
		Integer numeroDaColuna = 0;
		
		Integer numeroDeVertices = Integer.parseInt(leitor.readLine().trim());
		
		Grafo grafo = new Grafo(numeroDeVertices);
		
		while ((line = leitor.readLine()) != null) {
			numeroDaLinha = 0;
			
			
			String[] splitted = line.split("\t");
			
			for (int i = 0; i < splitted.length; i++) {
				if ("inf".equalsIgnoreCase(splitted[i].trim())) {
					grafo.insereAresta(numeroDaLinha, numeroDaColuna, Integer.MAX_VALUE);
				} else {
					Integer valor = Integer.parseInt(splitted[i].trim());
					grafo.insereAresta(numeroDaLinha, numeroDaColuna, valor);
				}
				
				numeroDaLinha++;
			}
			
			numeroDaColuna++;
		}
		
		return grafo;
	}

}
