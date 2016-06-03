package coloracao.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import model.Grafo;
import model.Vertice;
import coloracao.Coloracao;
import coloracao.ColoracaoListener;
import coloracao.VerticeColorido;

public class ColoracaoImpl implements Coloracao {

	private Grafo grafo = Grafo.NULO;
	private Collection<ColoracaoListener> listeners;
	private Collection<Color> cores;
	public ColoracaoImpl() {
		listeners = new ArrayList<ColoracaoListener>();	
	}

	@Override
	public Grafo colorir(Grafo grafo) {
		inicializa(grafo);
		while (!coloriuTodos()) {
			coloreProximo();
		}
		return grafo;
	}

	private void inicializa(Grafo grafo) {
		this.grafo = grafo;
		cores = new ArrayList<>();
		coloreVerticeMaiorGrau();
	}

	private void coloreVerticeMaiorGrau() {
		Vertice maiorGrau = encontraVerticeMaiorGrau();
		colorirVertice(maiorGrau);
	}

	private Vertice encontraVerticeMaiorGrau() {
		return grafo.getVertices().stream().reduce((a, b) -> {
			return a.grau() >= b.grau() ? a : b;
		}).get();
	}

	private void colorirVertice(Vertice vertice) {
		if (vertice instanceof VerticeColorido) {
			coloreEhNotifica((VerticeColorido) vertice);
		}
	}

	private void coloreEhNotifica(VerticeColorido verticeColorido) {
		Color cor = calculaCor(verticeColorido);
		verticeColorido.setCor(cor);
		adicionaCorNaListaSeNaoEstiver(cor);
		listeners.forEach(listener -> listener.coloriu(grafo, verticeColorido));
	}

	private void adicionaCorNaListaSeNaoEstiver(Color cor) {
		if (!cores.contains(cor))
			cores.add(cor);
	}

	private Color calculaCor(VerticeColorido verticeColorido) {
		if (verticeColorido.grauSaturacao() == cores.size())
			return criaNovaCor();
		else {
			return retornaCorNaoAdjacente(verticeColorido);
		}
	}

	private Color criaNovaCor() {
		Color cor;
		do
			cor = new Color(rgbInt(), rgbInt(), rgbInt());
		while (cor == VerticeColorido.COR_NULA || cores.contains(cor));

		return cor;
	}

	private Color retornaCorNaoAdjacente(VerticeColorido verticeColorido) {
		for (Color color : cores) {
			if (!verticeColorido.temCorAdjacente(color)) {
				return color;
			}
		}
		return VerticeColorido.COR_NULA;
	}

	private int rgbInt() {
		return (int) (Math.random() * 255);
	}

	private boolean coloriuTodos() {
		return vertices().noneMatch(v -> v.semCor());
	}

	private void coloreProximo() {
		colorirVertice(calculaVerticeMaiorSaturacao());
	}

	private Vertice calculaVerticeMaiorSaturacao() {
		BinaryOperator<VerticeColorido> accumulator = (a, b) -> {
			return a.grauSaturacao() >= b.grauSaturacao() ? a : b;
		};
		return vertices().filter(v -> v.semCor()).reduce(accumulator).get();
	}

	private Stream<VerticeColorido> vertices() {
		return grafo.getVertices().stream()
				.filter(v -> v instanceof VerticeColorido)
				.map(v -> (VerticeColorido) v);
	}

	public void addListener(ColoracaoListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public Grafo executaPasso(Grafo grafo) {
		if (isNovo(grafo)) {
			inicializa(grafo);
		} else {
			executaPasso();
		}
		return grafo;
	}

	private boolean isNovo(Grafo grafo) {
		return !this.grafo.equals(grafo);
	}

	private void executaPasso() {
		if (!coloriuTodos()) {
			coloreProximo();
		} else {
			throw new IllegalStateException(
					"O grafo já foi colorido completamente.");
		}
	}

}
