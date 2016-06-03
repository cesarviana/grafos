package model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import model.Aresta;
import model.Vertice;

public class VerticePadrao implements Vertice {

	private int poxX;
	private int poxY;
	private String rotulo;
	private int relId;
	private Set<Aresta> arestas;

	public VerticePadrao(String rotulo, int relId) {
		this(0, 0, rotulo, relId);
	}

	public VerticePadrao(int poxX, int poxY, String rotulo, int relId) {
		this.poxX = poxX;
		this.poxY = poxY;
		this.rotulo = rotulo;
		this.relId = relId;
		this.arestas = new HashSet<Aresta>();
	}

	@Override
	public Collection<Vertice> getAdjacentes() {
		Collection<Vertice> adjacentes = new ArrayList<>();
		for (Aresta aresta : arestas) {
			adjacentes.add(aresta.getVerticeLigadoA(this));
		}
		return adjacentes.stream()
				.sorted((va, vb) -> va.getRotulo().compareTo(vb.getRotulo()))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<Vertice> getAdjacentesExceto(Vertice verticeNaoIncluir) {
		return getAdjacentes().stream().filter(adj -> !adj.equals(verticeNaoIncluir))
				.collect(Collectors.toList());
	}

	@Override
	public int getRelId() {
		return relId;
	}

	@Override
	public String getRotulo() {
		return rotulo;
	}

	/**
	 * Por padrão ao adicionar a aresta no vertice, o vertice é adiciona do à
	 * aresta. Se for grafo dirigido, só deve adicionar a aresta no vertice
	 * origem.
	 */
	@Override
	public void addAresta(Aresta aresta) {
		this.arestas.add(aresta);
		aresta.addVertice(this);
	}

	@Override
	public int getPosX() {
		return poxX;
	}

	@Override
	public void setPoxX(int poxX) {
		this.poxX = poxX;
	}

	@Override
	public int getPosY() {
		return poxY;
	}

	@Override
	public void setPoxY(int poxY) {
		this.poxY = poxY;
	}

	@Override
	public String toString() {
		return rotulo;
	}

	@Override
	public Aresta getArestaCom(String rotulo) {
		for (Aresta aresta : arestas) {
			if (aresta.getVerticeLigadoA(this).getRotulo().equals(rotulo))
				return aresta;
		}
		throw new NoSuchElementException("Não exista a ligação (" + this
				+ ")---->(" + rotulo + ")");
	}

	@Override
	public Collection<Aresta> getArestas() {
		return arestas;
	}

	@Override
	public int grau() {
		return getAdjacentes().size();
	}
	
}
