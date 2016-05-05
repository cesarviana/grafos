package view.model;

import astar.Mapa;
import model.Grafo;

public class MainWindowModel {

    private Grafo grafo = Grafo.NULO;
    private Mapa mapa = Mapa.NULO;
    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public Mapa getMapa() {
		return mapa;
	}
    
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
    
}
