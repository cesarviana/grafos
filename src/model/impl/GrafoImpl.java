package model.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import factory.GrafoStaticFactory;
import model.Aresta;
import model.Grafo;
import model.Vertice;

public class GrafoImpl implements Grafo {

	private final Map<String, Vertice> verticesRotulos;
	private final Map<Integer, Vertice> verticesRelId;
	private boolean dirigido;

	public GrafoImpl() {
		this.verticesRotulos = new HashMap<>();
		this.verticesRelId = new HashMap<>();
	}

	@Override
	public Collection<Vertice> getVertices() {
		return verticesRotulos.values();
	}

	@Override
	public Vertice getVertice(String rotulo) {
		Vertice vertice = verticesRotulos.get(rotulo.toUpperCase());
		return vertice == null ? Vertice.NULO : vertice;
	}

	@Override
	public void adicionarVertice(int posX, int posY, String rotulo, int relId) {
		Vertice vertice = GrafoStaticFactory.criaFactory().criaVertice(posX, posY, rotulo, relId);
		verticesRotulos.put(rotulo, vertice);
		verticesRelId.put(relId, vertice);
	}
	
	@Override
	public void adicionarVertice(Vertice vertice){
		verticesRotulos.put(vertice.getRotulo(), vertice);
		verticesRelId.put(vertice.getRelId(), vertice);
	}

	@Override
	public void adicionarAresta(int idVertice1, int idVertice2, double peso) {
		Aresta aresta = GrafoStaticFactory.criaFactory().criaAresta(peso);
		verticesRelId.get(idVertice1).addAresta(aresta);
		ligaOutroVertice(idVertice2, aresta);
	}

	protected void ligaOutroVertice(int idVertice2, Aresta aresta) {
		if (dirigido) {
			aresta.addVertice(verticesRelId.get(idVertice2));
		} else {
			verticesRelId.get(idVertice2).addAresta(aresta);
		}
	}
	
	@Override
	public Collection<Aresta> getArestas() {
		// FIXME 
		Set<Aresta> arestas = new HashSet<Aresta>();
		verticesRelId.values().forEach(vertice->{
			arestas.addAll(vertice.getArestas());
		});
		return arestas;
	}

	@Override
	public void setDirigido(boolean dirigido) {
		this.dirigido = dirigido;
	}

}
