package planaridade.impl;

import model.Grafo;
import model.Vertice;
import planaridade.VerificadorPlanaridade;

public class VerificadorPlanaridadeImpl implements VerificadorPlanaridade {

	private Grafo grafo;

	@Override
	public boolean isPlanar(Grafo grafo) {
		this.grafo = grafo;
		int qtdArestas = grafo.getArestas().size();
		int qtdVertices = grafo.getVertices().size();
		if (qtdArestas <= 3 * qtdVertices - 6) {
			if (possuiClicloDe3()) {
				return true;
			} else {
				return qtdArestas <= 2 * qtdVertices - 4;
			}
		}
		return false;
	}

	private boolean possuiClicloDe3() {
		for (Vertice a : grafo.getVertices()) {
			for (Vertice b : a.getAdjacentes()) {
				for (Vertice c : b.getAdjacentesExceto(a)) {
					if (c.getAdjacentes().contains(a))
						return true;
				}
			}
		}
		return false;
	}

}
