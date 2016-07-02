package model.nulo;

import model.Aresta;
import model.Vertice;

public class ArestaNula implements Aresta {

	@Override
	public Vertice getVerticeLigadoA(Vertice vertice) {
		return Vertice.NULO;
	}

	@Override
	public void addVertice(Vertice verticePadrao) {
	}

	@Override
	public double getPeso() {
		return 0;
	}

	@Override
	public Vertice getVerticeA() {
		return Vertice.NULO;
	}

	@Override
	public Vertice getVerticeB() {
		return Vertice.NULO;
	}
}
