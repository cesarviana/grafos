package model;

import java.util.Collection;

import model.nulo.GrafoNulo;

public interface Grafo {

    public static final Grafo NULO = new GrafoNulo();
    
    public Collection<Vertice> getVertices();

    public Vertice getVertice(String rotulo);

    public void adicionarVertice(int posX, int posY, String rotulo, int relId);

    public void adicionarAresta(int idVertice1, int idVertice2, double peso);

    public void setDirigido(boolean dirigido);

	public Collection<Aresta> getArestas();

	void adicionarVertice(Vertice vertice);

}
