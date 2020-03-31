package algoritmos;

import util.Grafo;
import java.util.ArrayList;

public class CaminhoMinimo {

	public void Dijkstra(Grafo G, int v1, int v2) {

		ArrayList<Integer> dist = new ArrayList<Integer>();
		ArrayList<Integer> pred = new ArrayList<Integer>();
		ArrayList<Integer> vNaoVisitado = new ArrayList<Integer>();
		int vert = 0;

		for (int i = 0; i < G.V.size(); i++) {

			pred.add(i, null); // Set distancia mínima de cada vértice
			dist.add(i, 9999999); // Distancia infinita de cada predecessor de vértice
			vNaoVisitado.add(i); // Array armazena vértices (Q)
		}
		dist.add(v1, 0); // distância do primeiro vertice igual a zero

		while (!vNaoVisitado.isEmpty()) { // Enquanto o Array de vértices n for vazio

			int min = 9999999;

			//Pega o indice do menor vertice da lista (Q)
			for (int i = 0; i < vNaoVisitado.size(); i++) {
				if (min > dist.get(vNaoVisitado.get(i))) { // Dist minima > dist do vertice?
					min = dist.get(vNaoVisitado.get(i)); // Atualiza dist minima
					vert = vNaoVisitado.get(i); // variavel recebe vertice de menor distancia
				}
			}
			vNaoVisitado.remove((Integer) vert); // remove vertice de menor distancia

			for (int i = 0; i < G.listaAdj.get(vert).size(); i++) { // laco entre os adjacentes ao  menor vertice 
				int v = G.listaAdj.get(vert).get(i).getDestino(); // recebe o vertice adj analisado
				if (dist.get(v) > (dist.get(vert) + G.listaAdj.get(vert).get(i).getPeso())) {// dist do vertice adj for maior que a dist do menor vert + peso entre eles
					dist.set(v, dist.get(vert) + G.listaAdj.get(vert).get(i).getPeso()); //dist atualiza
					pred.set(v, vert); //pred recebe num do vertice adjacente
				}
			}
		}
		System.out.println("Dijkstra:");
		retornaCaminho(v1, v2, pred); 
		System.out.println("Custo: " + dist.get(v2));

	}

	public boolean BellmanFord(Grafo G, int v1, int v2) {

		ArrayList<Integer> dist = new ArrayList<Integer>();
		ArrayList<Integer> pred = new ArrayList<Integer>();

		for (int i = 0; i < G.V.size(); i++) {
			dist.add(i, 9999999); // Distancia infinita de cada predecessor de vértice
			pred.add(0); // Set distancia mínima de cada vértice
		}
		dist.set(v1, 0); // distância do primeiro vertice igual a zero

		for (int i = 0; i < G.V.size(); i++) { //Numero de vezes que ira repetir as arestas, verificando aresta negativa
			for (int j = 0; j < G.E.size(); j++) {
				if (dist.get(G.E.get(j).getDestino()) > dist.get(G.E.get(j).getOrigem()) + G.E.get(j).getPeso()) { // Dist minima > dist do vertice + peso?
					dist.set(G.E.get(j).getDestino(), (dist.get(G.E.get(j).getOrigem()) + G.E.get(j).getPeso())); // Atualiza dist minima
					pred.set(G.E.get(j).getDestino(), G.E.get(j).getOrigem()); //recebe vertice de menor distancia
				}
			}
		}
		for (int j = 0; j < G.E.size(); j++) {
			if (dist.get(G.E.get(j).getDestino()) > dist.get(G.E.get(j).getOrigem()) + G.E.get(j).getPeso()) {// verifica aresta negativa
				retornaCaminho(v1, v2, pred); 
				return false; //possui ciclo de custo negativo (aceita aresta de peso negativo)
			}
		}

		System.out.println("Bellman-Ford:");
		retornaCaminho(v1, v2, pred);
		System.out.println("Custo: " + dist.get(v2));
		return true;

	}

	public void FloydWarshall(Grafo G, int v1, int v2) {

		int[][] dist = new int[G.matrizAdj.length][G.matrizAdj.length];
		int[][] pred = new int[G.matrizAdj.length][G.matrizAdj.length];
		ArrayList<Integer> t = new ArrayList<Integer>(); 

		for (int i = 0; i < G.V.size(); i++) {
			for (int j = 0; j < G.V.size(); j++) {

				if (i == j) {
					dist[i][j] = 0; //Diagonal principal
				} else if (G.matrizAdj[i][j] != 0) { // se existe aresta entre i e j
					dist[i][j] = G.matrizAdj[i][j]; //matriz dist armazena distancia de cada vertice (linha) x (coluna)
					pred[i][j] = i; // matriz pred recebe indice i (Linha) ; vertices que precedem os analisados 
				} else {
					dist[i][j] = 99999999; // matriz dist recebe distancia infinita
				}

			}
		}
		
		// Verifica se a distancia da origem e destino > que a distancia da origem + a distancia do destino
		for (int k = 0; k < G.V.size(); k++) {
			for (int i = 0; i < G.V.size(); i++) {
				for (int j = 0; j < G.V.size(); j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j]; // atualiza distancias
						pred[i][j] = pred[k][j]; // atualiza predecessores
					}
				}
			}
		}

		for (int i = 0; i < G.V.size(); i++) {
			t.add(pred[v1][i]); //Array recebe dados atualizados

		}
		System.out.println("Floyd-Warshall:");
		retornaCaminho(v1, v2, t);
		System.out.println("Custo: " + dist[v1][v2]);
	}

	public void retornaCaminho(int v1, int v2, ArrayList<Integer> pred) {
		ArrayList<Integer> caminho = new ArrayList<Integer>();
		int aux = v2;
		while (aux != v1) {
			aux = pred.get(aux);
			caminho.add(0, aux);
		}
		caminho.add(v2);
		System.out.println("Caminho minimo entre " + v1 + " e " + v2 + ": " + caminho.toString());
	}
}
