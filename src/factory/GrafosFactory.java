package factory;

import model.Aresta;
import model.Grafo;
import model.Vertice;

public interface GrafosFactory {

	public Vertice criaVertice(int posX, int posY, String rotulo, int relId);
	public Grafo criaGrafo();
	public Aresta criaAresta();
	public Aresta criaAresta(double peso);
	public Vertice criaVertice(String string, int i);
	
}
