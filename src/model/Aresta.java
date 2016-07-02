package model;

import model.nulo.ArestaNula;

public interface Aresta {

    public Aresta NULA = new ArestaNula();

    public Vertice getVerticeLigadoA(Vertice vertice);

    public void addVertice(Vertice verticePadrao);

    public double getPeso();

	Vertice getVerticeA();

	Vertice getVerticeB();
    

}
