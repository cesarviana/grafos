package model.nulo;

import java.util.Collection;
import java.util.Collections;

import model.Aresta;
import model.Vertice;

public class VerticeNulo implements Vertice {

	@Override
	public Collection<Vertice> getAdjacentes() {
		return Collections.emptyList();
	}

	@Override
	public Collection<Vertice> getAdjacentesExceto(Vertice vertice) {
		return getAdjacentes();
	}

	@Override
	public String getRotulo() {
		return "Vertice Nulo";
	}

	@Override
	public void addAresta(Aresta aresta) {
	}

	@Override
	public String toString() {
		return getRotulo();
	}

	@Override
	public int getRelId() {
		return 0;
	}

	@Override
	public Aresta getArestaCom(String rotulo) {
		return Aresta.NULA;
	}

	public void setPoxY(int poxY) {
	}

	public int getPosY() {
		return 0;
	}

	public void setPoxX(int poxX) {
	}

	public int getPosX() {
		return 0;
	}

	@Override
	public Collection<Aresta> getArestas() {
		return Collections.emptyList();
	}

	@Override
	public int grau() {
		return 0;
	}
	
}
