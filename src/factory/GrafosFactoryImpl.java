package factory;

import model.Aresta;
import model.Grafo;
import model.Vertice;
import model.impl.ArestaPadrao;
import model.impl.GrafoImpl;
import coloracao.VerticeColorido;

public class GrafosFactoryImpl implements GrafosFactory {

	@Override
	public Vertice criaVertice(int poxX, int poxY, String rotulo, int relId) {
		return new VerticeColorido(poxX, poxY, rotulo, relId);
	}

	@Override
	public Vertice criaVertice(String rotulo, int relId) {
		return new VerticeColorido(rotulo, relId);
	}

	@Override
	public Grafo criaGrafo() {
		return new GrafoImpl();
	}

	@Override
	public Aresta criaAresta() {
		return new ArestaPadrao();
	}


}
