package caminho;

import java.util.List;

import caminho.bfs.BFS;
import caminho.dfs.DFS;
import model.Grafo;
import model.Vertice;

public interface Caminho {

    public static final Caminho BFS = new BFS();
    public static final Caminho DFS = new DFS();
    public List<Vertice> encontraCaminho(Grafo grafo, String origem,
	    String destino);

}
