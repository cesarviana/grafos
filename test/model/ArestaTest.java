package model;

import static org.junit.Assert.*;
import model.impl.ArestaPadrao;
import model.impl.GrafoImpl;
import model.impl.VerticePadrao;

import org.junit.Test;

public class ArestaTest {

	@Test
	public void test_equals() {
		Vertice va = new VerticePadrao("a", 1);
		Vertice vb = new VerticePadrao("b", 1);
		
		ArestaPadrao aresta = new ArestaPadrao();
		aresta.addVertice(va);
		aresta.addVertice(vb);
		
		ArestaPadrao aresta2 = new ArestaPadrao();
		aresta2.addVertice(vb);
		aresta2.addVertice(va);
		
		assertEquals(aresta,aresta2);
	}
	
	@Test
	public void test_grafo(){
		Grafo g = new GrafoImpl();
		g.adicionarVertice(0, 0, "a", 1);
		g.adicionarVertice(0, 0, "b", 2);
		g.adicionarAresta(1, 2, 5);
		assertEquals(1,g.getArestas().size());
	}

}
