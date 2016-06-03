package model;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import factory.GrafoStaticFactory;
import factory.GrafosFactory;

public class VerticeTest {

	private Vertice a;
	private Vertice b;
	private Aresta AB, AC;
	private Vertice c;

	@Before
	public void setup() {
		GrafosFactory factory = GrafoStaticFactory.criaFactory();
		a = factory.criaVertice("A", 0);
		c = factory.criaVertice("C", 2);
		b = factory.criaVertice("B", 1);
		AC = factory.criaAresta();
		a.addAresta(AC);
		c.addAresta(AC);
		AB = factory.criaAresta();
		a.addAresta(AB);
		b.addAresta(AB);
	}

	@Test
	public void test_adjacente() {
		Iterator<Vertice> iterator = a.getAdjacentes().iterator();
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
	}

	@Test
	public void test_aresta_vertice_ligado_a() {
		Vertice verticeLigado = AB.getVerticeLigadoA(b);
		assertEquals(a, verticeLigado);
	}

	@Test(expected = IllegalStateException.class)
	public void test_add_varios_vertices() {
		GrafosFactory factory = GrafoStaticFactory.criaFactory();
		Vertice verticeA = factory.criaVertice("A", 0);
		Vertice verticeB = factory.criaVertice("B", 1);
		Vertice verticeC = factory.criaVertice("C", 2);
		Aresta aresta = factory.criaAresta();
		aresta.addVertice(verticeA);
		aresta.addVertice(verticeB);
		aresta.addVertice(verticeC);
	}

}
