package caminho.conexidade.impl;

import caminho.Caminho;
import caminho.conexidade.VerificadorConexidade;
import model.Grafo;
import model.Vertice;

public class VerificadorConexidadeImpl implements VerificadorConexidade {

    @Override
    public boolean isConexo(Grafo grafo) {
	for (Vertice origem : grafo.getVertices()) {
	    for (Vertice destino : grafo.getVertices()) {
		if(naoTemCaminho(grafo, origem, destino)){
		    return false;
		}
	    }
	}
	return true;
    }

    protected boolean naoTemCaminho(Grafo grafo, Vertice origem, Vertice destino) {
	return Caminho.BFS.encontraCaminho(grafo, origem.getRotulo(),
		destino.getRotulo()).isEmpty();
    }
}
