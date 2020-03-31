package util;

import java.util.ArrayList;

public class Grafo {
	
	public ArrayList<Integer> V;
	public ArrayList<Aresta> E;
	public int [] [] matrizAdj;
	public ArrayList<ArrayList<Aresta>> listaAdj;
	
	public Grafo(ArrayList<Integer> v, ArrayList<Aresta> e, int[][] matrizAdj, ArrayList<ArrayList<Aresta>> listaAdj) {
		V = v;
		E = e;
		this.matrizAdj = matrizAdj;
		this.listaAdj = listaAdj;
	}
	
	
	

}
