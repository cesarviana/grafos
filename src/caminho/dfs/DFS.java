package caminho.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import caminho.Caminho;
import model.Grafo;
import model.Vertice;

public class DFS implements Caminho {

    private List<Vertice> visitados;

    @Override
    public List<Vertice> encontraCaminho(Grafo grafo, String rotuloOrigem,
	    String rotuloDestino) {
	visitados = new ArrayList<>();
	Stack<Vertice> pilha = new Stack<Vertice>();
	Vertice atual = grafo.getVertice(rotuloOrigem);
	Vertice destino = grafo.getVertice(rotuloDestino);

	visitados.add(atual);
	pilha.push(atual);

	if (atual == destino)
	    return visitados;

	while (atual != destino && !pilha.isEmpty()) {
	    if (visitouTodosAdjacentes(atual)) {
		pilha.pop();
		atual = pilha.isEmpty() ? null : pilha.peek();
	    }
	    if (atual != null)
		for (Vertice adjacente : atual.getAdjacentes()) {
		    if (!visitados.contains(adjacente)) {
			visitados.add(adjacente);
			pilha.push(adjacente);
			atual = adjacente;
			break;
		    }
		}
	}
	return atual == destino ? visitados : Collections.emptyList();
    }

    private boolean visitouTodosAdjacentes(Vertice vertice) {
	for (Vertice adjacente : vertice.getAdjacentes()) {
	    if (!visitados.contains(adjacente))
		return false;
	}
	return true;
    }

}
