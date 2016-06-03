package model.nulo;

import java.util.Collection;
import java.util.Collections;

import model.Aresta;
import model.Grafo;
import model.Vertice;

public class GrafoNulo implements Grafo {

	@Override
	public Collection<Vertice> getVertices() {
		return Collections.emptyList();
	}

	@Override
	public Vertice getVertice(String rotulo) {
		return Vertice.NULO;
	}

	@Override
	public void adicionarVertice(int posX, int posY, String rotulo, int relId) {
	}

	@Override
	public void adicionarAresta(int idVertice1, int idVertice2, double peso) {
	}

	@Override
	public void setDirigido(boolean dirigido) {
	}

	@Override
	public Collection<Aresta> getArestas() {
		return Collections.emptyList();
	}

	@Override
	public void adicionarVertice(Vertice vertice) {
	}
}
