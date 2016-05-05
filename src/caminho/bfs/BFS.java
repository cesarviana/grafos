package caminho.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import caminho.Caminho;
import model.Grafo;
import model.Vertice;

public class BFS implements Caminho {

    private List<Vertice> visitados;

    @Override
    public List<Vertice> encontraCaminho(Grafo grafo, String rotuloOrigem,
	    String rotuloDestino) {

	visitados = new ArrayList<>();
	Queue<Vertice> fila = new LinkedList<>();
	Vertice atual = grafo.getVertice(rotuloOrigem);
	Vertice destino = grafo.getVertice(rotuloDestino);

	visitados.add(atual);
	fila.add(atual);

	if (atual == destino)
	    return visitados;

	while (atual != destino && !fila.isEmpty()) {
	    if (visitouTodosAdjacentes(atual)) {
		fila.remove();
		atual = fila.peek();
	    }
	    if(atual != null)
		for (Vertice adjacente : atual.getAdjacentes()) {
		    if (!visitados.contains(adjacente)) {
			visitados.add(adjacente);
			fila.add(adjacente);
		    }
		    if (adjacente == destino)
			return visitados;
		}
	}

	return Collections.emptyList();
    }

    private boolean visitouTodosAdjacentes(Vertice vertice) {
	for (Vertice adjacente : vertice.getAdjacentes()) {
	    if (!visitados.contains(adjacente))
		return false;
	}
	return true;
    }

}
