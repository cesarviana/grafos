package model;

import java.util.Collection;

import model.nulo.VerticeNulo;

public interface Vertice {

    public static final Vertice NULO = new VerticeNulo();
    
    public Collection<Vertice> getAdjacentes();
    
    public Collection<Vertice> getAdjacentesExceto(Vertice vertice);

    public String getRotulo();

    public void addAresta(Aresta aresta);

    public int getRelId();

    public Aresta getArestaCom(String rotulo);

    public abstract void setPoxY(int poxY);

    public abstract int getPosY();

    public abstract void setPoxX(int poxX);

    public abstract int getPosX();

	public Collection<Aresta> getArestas();

	public int grau();

}
