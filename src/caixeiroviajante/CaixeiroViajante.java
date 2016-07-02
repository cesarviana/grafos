package caixeiroviajante;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import model.Aresta;
import model.Grafo;
import model.Vertice;

public class CaixeiroViajante {

	private List<Vertice> verticesCaminho;
	private List<Vertice> verticesFaltaAdicionar;
	private List<AdicaoPossivel> adicoes;
	private Grafo grafo;
	private Collection<CaixeiroViajanteListener> listeners;

	public CaixeiroViajante() {
		verticesCaminho = new ArrayList<Vertice>();
		listeners = new ArrayList<>();
	}

	public List<Vertice> calcula(Grafo grafo) {
		this.grafo = grafo;
		Aresta maisCurta = grafo.getArestas().stream().reduce((a, b) -> {
			return a.getPeso() < b.getPeso() ? a : b;
		}).get();

		verticesCaminho.add(maisCurta.getVerticeA());
		verticesCaminho.add(maisCurta.getVerticeB());
		verticesCaminho.add(maisCurta.getVerticeA());

		listeners.forEach(listener -> listener.executouPasso(grafo,
				verticesCaminho));
		
		verticesFaltaAdicionar = getVerticesFaltaAdicionar();
		while (!verticesFaltaAdicionar.isEmpty()) {

			AdicaoPossivel adicao = calculaMelhorAdicao();

			int ia = verticesCaminho.indexOf(adicao.verticeA);
			int ib = verticesCaminho.indexOf(adicao.verticeB);
			int indiceAdicionar = ia > ib ? ia : ib;
			verticesCaminho.add(indiceAdicionar, adicao.verticeAdicionar);
			
			listeners.forEach(listener -> listener.executouPasso(grafo,
					verticesCaminho));
			
			verticesFaltaAdicionar = getVerticesFaltaAdicionar();
		}

		listeners.forEach(listener -> listener
				.finalizou(grafo, verticesCaminho));

		return verticesCaminho;
	}

	public double calculaDistancia(List<Vertice> vertices) {
		double distancia = 0;
		for (int i = 0; i < vertices.size() - 1; i++) {
			Vertice vertice = vertices.get(i);
			String proximo = vertices.get(i + 1).getRotulo();
			distancia += vertice.getArestaCom(proximo).getPeso();
		}
		return distancia;
	}

	private AdicaoPossivel calculaMelhorAdicao() {

		adicoes = new ArrayList<>();
		for (int i = 0; i < verticesCaminho.size()-1; i++) {
			Vertice d = verticesCaminho.get(i);
			Vertice e = verticesCaminho.get(i + 1);
			calculaAdicoesEntreOsVertices(d, e);
		}

		return adicoes.stream().reduce((a, b) -> {
			return a.soma < b.soma ? a : b;
		}).get();
	}

	private void calculaAdicoesEntreOsVertices(Vertice d, Vertice e) {
		for (Vertice verticeAdicionar : verticesFaltaAdicionar) {
			double candidadoTraz = verticeAdicionar.getArestaCom(d.getRotulo())
					.getPeso();
			double canditatoFrente = verticeAdicionar.getArestaCom(e.getRotulo())
					.getPeso();
			double vizinhoVizinho = d.getArestaCom(e.getRotulo()).getPeso();
			double r = (candidadoTraz + canditatoFrente) - vizinhoVizinho;
			adicoes.add(new AdicaoPossivel(d, e, r, verticeAdicionar));
		}
	}

	private List<Vertice> getVerticesFaltaAdicionar() {
		return grafo.getVertices().stream()
				.filter(v -> !verticesCaminho.contains(v))
				.collect(Collectors.toList());
	}

	private class AdicaoPossivel {
		private Vertice verticeA;
		private Vertice verticeB;
		private Vertice verticeAdicionar;
		private double soma;

		private AdicaoPossivel(Vertice a, Vertice b, double soma,
				Vertice adicionar) {
			this.verticeA = a;
			this.verticeB = b;
			this.soma = soma;
			this.verticeAdicionar = adicionar;
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("Entre ").append(verticeA).append(" e ").append(verticeB)
					.append(" inserir ").append(verticeAdicionar)
					.append(" soma = ").append(soma);
			return s.toString();
		}

	}

	public void addListener(CaixeiroViajanteListener caixeiroViajanteListener) {
		this.listeners.add(caixeiroViajanteListener);
	}

}
