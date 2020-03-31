package algoritmos;

import java.util.ArrayList;

import util.Grafo;

public class Busca {

	private Grafo G;

	public Busca(Grafo G) {
		this.G = G;
	}

	public void buscaLargura(int s) {// Busca em Largura
		int[] visitado = new int[G.V.size()];
		for (int i = 0; i < G.V.size(); ++i) {
			visitado[i] = 0;
		}
		visitado[s] = 1;
		System.out.println(s);
		ArrayList<Integer> F = new ArrayList<>();
		F.add(s);
		while (!F.isEmpty()) {
			int u = F.remove(0);// posicao na fila que esta removendo
			for (int i = 0; i < G.listaAdj.get(u).size(); ++i) { // adjacentes vertices u
				int v = G.listaAdj.get(u).get(i).getDestino();
				if (visitado[v] == 0) {
					visitado[v] = 1;
					System.out.println(v);
					F.add(v);
				}
			}
		}
	}

}
