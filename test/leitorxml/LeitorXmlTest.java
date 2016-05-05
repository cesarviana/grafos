package leitorxml;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import leitorxml.impl.LeitorGraphMax;
import model.Aresta;
import model.Grafo;
import model.Vertice;

import org.junit.Before;
import org.junit.Test;

import testeutil.TesteUtil;

public class LeitorXmlTest {

	private Grafo grafo, grafoDijkstra;

	@Before
	public void setUp() {
		grafo = TesteUtil.importaGrafo();
		grafoDijkstra = new LeitorGraphMax().ler(new File(
				"grafoDijkstraMinimo.xml"));
	}

	@Test
	public void test_importacao() {
		assert_importacao_correta(grafo);
	}

	private void assert_importacao_correta(Grafo grafo) {
		assert_qtd_vertices(grafo);
		assert_qtd_adjacentes(grafo);
		assert_ordem_adjacentes(grafo);
		assert_arestas(grafo);
	}

	private void assert_qtd_vertices(Grafo grafo) {
		Collection<Vertice> vertices = grafo.getVertices();
		assertEquals(7, vertices.size());
	}

	private void assert_qtd_adjacentes(Grafo grafo) {
		Vertice vertice = grafo.getVertice("A");
		assertEquals(1, vertice.getAdjacentes().size());
	}

	private void assert_ordem_adjacentes(Grafo grafo) {
		Vertice verticeB = grafo.getVertice("B");
		Collection<Vertice> adjacentes = verticeB.getAdjacentes();
		Iterator<Vertice> i = adjacentes.iterator();
		assertEquals("D", i.next().getRotulo());
		assertEquals("E", i.next().getRotulo());
		assertEquals("F", i.next().getRotulo());
	}

	@Test
	public void test_arestas() {
		Vertice vA = grafoDijkstra.getVertice("A");
		assertEquals(5, vA.getArestaCom("B").getPeso(), 0);
		assertEquals(1, vA.getArestaCom("C").getPeso(), 0);
		Vertice vC = grafoDijkstra.getVertice("C");
		assertEquals(1, vC.getArestaCom("B").getPeso(), 0);
	}

	private void assert_arestas(Grafo grafo) {
		Collection<Aresta> arestas = grafo.getArestas();
		List<String> arestasS = Arrays.asList(
				"F - E",
				"D - E", 
				"B - D", 
				"A - B", 
				"B - E", 
				"C - A", 
				"F - C", 
				"E - G",
				"B - F"
		); 
		long count = arestas.stream().filter(a->arestasS.contains(a.toString())).count();
		assertEquals(arestasS.size(), count);
	}
	
}
