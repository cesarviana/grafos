package caixeiroviajante;

import java.util.List;

import model.Grafo;
import model.Vertice;

public interface CaixeiroViajanteListener {

	void executouPasso(Grafo g, List<Vertice> vertices);

	void finalizou(Grafo g, List<Vertice> verticesCaminho);

}
