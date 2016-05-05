package model.factory;

import model.Aresta;
import model.Grafo;
import model.Vertice;

public interface ModelFactory {

    public Grafo criaGrafo();

    public Vertice criaVertice(String rotulo, int relId);

    public Aresta criaAresta();
    
}
