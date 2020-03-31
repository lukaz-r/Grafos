package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import algoritmos.CaminhoMinimo;

public class Leitor {

	public File arquivo;
	public Grafo G;

	public Leitor() {

	}

	public Grafo lerArquivo(String name) throws IOException {

		this.arquivo = new File(name);

		FileReader leitor = new FileReader(arquivo);
		BufferedReader bufferLeitura = new BufferedReader(leitor);

		// l� o arquivo;
		String[] linha;
		linha = bufferLeitura.readLine().split(" ");
		int numVertices = Integer.parseInt(linha[0]);
		int numArestas = Integer.parseInt(linha[1]);

		ArrayList<Integer> V = new ArrayList<>();
		ArrayList<Aresta> E = new ArrayList<>();

		int[][] matrizAdj = new int[numVertices][numVertices];
		ArrayList<ArrayList<Aresta>> listaAdj = new ArrayList<ArrayList<Aresta>>();

		// Cria lista e matriz de adjacencia
		for (int i = 0; i < numVertices; ++i) {

			ArrayList<Aresta> listaAdjDeI = new ArrayList<Aresta>();
			V.add(i);
			listaAdj.add(listaAdjDeI);
			for (int j = 0; j < numVertices; ++j) {
				matrizAdj[i][j] = 0;

			}
		}
		// Preenche arestas, lista de adjacencias e matriz

		for (int i = 0; i < numArestas; ++i) {
			String[] infoAresta = bufferLeitura.readLine().split(" ");
			int origem = Integer.parseInt(infoAresta[0]);
			int destino = Integer.parseInt(infoAresta[1]);
			int peso = Integer.parseInt(infoAresta[2]);
			Aresta e = new Aresta(origem, destino, peso);
			E.add(e);
			listaAdj.get(origem).add(e);
			matrizAdj[origem][destino] = peso;
		}
		G = new Grafo(V, E, matrizAdj, listaAdj);
		bufferLeitura.close();
		leitor.close();

		return G;

	}

	public void selectAlgoritmo() {

		Scanner ler = new Scanner(System.in);
		CaminhoMinimo m = new CaminhoMinimo();

		System.out.println("Algoritmo: ");
		System.out.println("1 Dijkstra ");
		System.out.println("2 Bellman-Ford ");
		System.out.println("3 Floyd-Warshall ");

		int op = ler.nextInt();

		System.out.println("Origem: ");
		int orig = ler.nextInt();
		System.out.println("Destino: ");
		int dest = ler.nextInt();

		switch (op) {

		case 1:
			long inicio = System.currentTimeMillis();
			m.Dijkstra(G, orig, dest);
			long fim = System.currentTimeMillis();
			System.out.println("Tempo: " + (fim - inicio) / 1000f + "s");

			break;

		case 2:
			inicio = System.currentTimeMillis();
			m.BellmanFord(G, orig, dest);
			fim = System.currentTimeMillis();
			System.out.println("Tempo: " + (fim - inicio) / 1000f + "s");

			break;

		case 3:
			inicio = System.currentTimeMillis();
			m.FloydWarshall(G, orig, dest);
			fim = System.currentTimeMillis();
			System.out.println("Tempo: " + (fim - inicio) / 1000f + "s");

			break;

		default:
			System.out.println("Opcao invalida !");

		}

	}

}
