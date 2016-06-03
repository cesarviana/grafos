package model;

import static org.junit.Assert.assertEquals;
import model.impl.ArestaPadrao;

import org.junit.Test;

import factory.GrafoStaticFactory;

public class ArestaTest {

	@Test
	public void test_equals() {
		Vertice va = GrafoStaticFactory.criaFactory().criaVertice("a", 1);
		Vertice vb = GrafoStaticFactory.criaFactory().criaVertice("b", 1);
		
		Aresta aresta = GrafoStaticFactory.criaFactory().criaAresta();
		aresta.addVertice(va);
		aresta.addVertice(vb);
		
		Aresta aresta2 = GrafoStaticFactory.criaFactory().criaAresta();
		aresta2.addVertice(vb);
		aresta2.addVertice(va);
		
		assertEquals(aresta,aresta2);
	}
	
	@Test
	public void test_grafo(){
		Grafo g = GrafoStaticFactory.criaFactory().criaGrafo();
		g.adicionarVertice(0, 0, "a", 1);
		g.adicionarVertice(0, 0, "b", 2);
		g.adicionarAresta(1, 2, 5);
		assertEquals(1,g.getArestas().size());
	}

}
