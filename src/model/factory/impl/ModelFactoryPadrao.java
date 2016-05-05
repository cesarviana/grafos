package model.factory.impl;

import model.Aresta;
import model.Grafo;
import model.Vertice;
import model.factory.ModelFactory;
import model.impl.ArestaPadrao;
import model.impl.GrafoImpl;
import model.impl.VerticePadrao;

public class ModelFactoryPadrao implements ModelFactory {

    @Override
    public Grafo criaGrafo() {
	return new GrafoImpl();
    }

    @Override
    public Vertice criaVertice(String rotulo, int relId) {
	return new VerticePadrao(rotulo, relId);
    }

    @Override
    public Aresta criaAresta() {
	return new ArestaPadrao();
    }

}
