package dijkstra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Grafo;
import model.Vertice;

public class Dijkstra {

	private static Dijkstra dijkstra;
	private Grafo grafo;
	private Map<String, Coluna> colunas;
	private Collection<Coluna> abertas;
	private Coluna inicio;
	private Coluna destino;

	public static Dijkstra instance(Grafo grafo) {
		if (dijkstra == null)
			dijkstra = new Dijkstra(grafo);

		dijkstra.grafo = grafo;
		dijkstra.inicializaColunas();

		return dijkstra;
	}

	private Dijkstra(Grafo grafo) {
	}

	private void inicializaColunas() {
		colunas = new HashMap<>();
		abertas = new LinkedList<Coluna>();
		for (Vertice vertice : grafo.getVertices()) {
			Coluna coluna = new Coluna(vertice);
			abertas.add(coluna);
			colunas.put(vertice.getRotulo(), coluna);
		}
	}

	public Dijkstra de(String inicio) {
		this.inicio = colunas.get(inicio);
		if (this.inicio == null)
			throw new NullPointerException("Origem não pertencente ao grafo.");
		this.inicio.estimativa = 0;
		return this;
	}

	public Dijkstra ate(String destino) {
		this.destino = colunas.get(destino);
		return this;
	}

	public List<Vertice> retornaCaminho() {
		validaOrigemDestino();
		constroiCaminhoEnquantoVerticeAberto();
		return constroiCaminhoDeVertices();
	}

	private void validaOrigemDestino() {
		validaOrigem();
		validaDestino();
	}

	private void validaDestino() {
		if (destino == null)
			throw new IllegalStateException("Falta informar o fim.");
	}

	private void validaOrigem() {
		if (inicio == null)
			throw new IllegalStateException("Falta informar o início.");
	}

	private void constroiCaminhoEnquantoVerticeAberto() {
		while (temVerticeAberto()) {
			constroiCaminho();
		}
	}

	private boolean temVerticeAberto() {
		return !abertas.isEmpty();
	}

	private void constroiCaminho() {
		Coluna atual = comMenorEstimativa();
		fechar(atual);
		for (Coluna sucessor : atual.getSucessoresAbertos()) {
			trocaPrecedenteParaAtualSeMenorEstimativa(atual, sucessor);
		}
	}

	private void trocaPrecedenteParaAtualSeMenorEstimativa(Coluna atual,
			Coluna sucessor) {
		double estimativaAnterior = sucessor.getEstimativa();
		double novaEstimativa = atual.somaEstimativa(sucessor);
		if (novaEstimativa < estimativaAnterior) {
			sucessor.setEstimativa(novaEstimativa);
			sucessor.setPrecedente(atual);
		}
	}

	private void fechar(Coluna k) {
		k.fechar();
		abertas.remove(k);
	}

	private Coluna comMenorEstimativa() {
		Coluna c = new Coluna(Vertice.NULO);
		for(Coluna atual : abertas){
			if(atual.estimativa <= c.estimativa)
				c = atual;
		}
		return c;
	}

	private List<Vertice> constroiCaminhoDeVertices() {
		List<Vertice> caminho = new ArrayList<>();
		Coluna coluna = destino;
		while (coluna.temPrecedente()) {
			caminho.add(coluna.getVertice());
			coluna = coluna.getPrecedente();
		}
		caminho.add(coluna.getVertice());
		Collections.reverse(caminho);
		return caminho;
	}

	public Collection<Coluna> retornaDistanciasParaDemaisVertices() {
		validaOrigem();
		constroiCaminhoEnquantoVerticeAberto();
		return colunas.values();
	}

	public class Coluna {
		private Vertice vertice;
		private double estimativa = Double.POSITIVE_INFINITY;
		private Coluna precedente;
		private boolean aberto = true;

		private Coluna(Vertice vertice) {
			this.setVertice(vertice);
		}

		private double somaEstimativa(Coluna k) {
			return estimativa
					+ getVertice().getArestaCom(k.getVertice().getRotulo())
							.getPeso();
		}

		private Collection<Coluna> getSucessoresAbertos() {
			Collection<Coluna> sucessoresAbertos = new ArrayList<>();
			getVertice().getAdjacentes().forEach(
					v -> sucessoresAbertos.add(colunas.get(v.getRotulo())));
			return sucessoresAbertos;
		}

		public Vertice getVertice() {
			return vertice;
		}

		private void setVertice(Vertice vertice) {
			this.vertice = vertice;
		}

		public boolean temPrecedente() {
			return getPrecedente().getVertice() != Vertice.NULO;
		}

		public Coluna getPrecedente() {
			if (precedente == null)
				precedente = new Coluna(Vertice.NULO);
			return precedente;
		}

		public void setPrecedente(Coluna precedente) {
			this.precedente = precedente;
		}

		private void fechar() {
			this.aberto = false;
		}

		public double getEstimativa() {
			return estimativa;
		}

		public void setEstimativa(double estimativa) {
			this.estimativa = estimativa;
		}

		@Override
		public String toString() {
			return "(" + getVertice().getRotulo() + ", " + estimativa + ", "
					+ getPrecedente().getVertice() + ", "
					+ (aberto ? "aberto" : "fechado") + ")";
		}
	}

}
