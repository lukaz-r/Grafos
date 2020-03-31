package main;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import util.Grafo;
import util.Leitor;

public class Main {

	public static void main(String[] args) {

		try {
			// Le arquivo de entrada

			System.out.println("Arquivo de Grafo: ");

			Scanner in = new Scanner(System.in);
			String nomeArquivo = in.nextLine();
			Leitor leitor = new Leitor();
			Grafo G;
			G = leitor.lerArquivo(nomeArquivo);

			// imprime o grafo lido

			// System.out.println(G.listaAdj);
			// Info info = new Info(G);

			// System.out.println("Densidade: " + info.densidade());
			//
			// if(info.regular()) {
			// System.out.println("regular");
			// }else {
			// System.out.println("NAO regular");
			// }
			// if(info.completo()) {
			// System.out.println("Completo");
			// }else {
			// System.out.println("NAO completo");
			// }
			//

			leitor.selectAlgoritmo();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(Main.class.getName());
		}

	}

}
