package coloracao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import model.Grafo;

import org.junit.Test;

import testeutil.TesteUtil;

public class ColoracaoTest {

	private final Grafo grafo = TesteUtil
			.importaGrafo(new File("grafoTesteColoracao.xml"));

	@Test
	public void test() {
		Coloracao coloracao = ColoracaoFactory.cria();
		Grafo grafoColorido = coloracao.colorir(grafo);
		VerticeColorido b = (VerticeColorido) grafoColorido.getVertice("B");
		VerticeColorido c = (VerticeColorido) grafoColorido.getVertice("C");
		VerticeColorido g = (VerticeColorido) grafoColorido.getVertice("G");
		assertTrue(b.getCor().equals(c.getCor()));
		assertTrue(b.getCor().equals(g.getCor()));

		VerticeColorido a = (VerticeColorido) grafoColorido.getVertice("A");
		VerticeColorido d = (VerticeColorido) grafoColorido.getVertice("D");
		VerticeColorido f = (VerticeColorido) grafoColorido.getVertice("F");
		assertTrue(a.getCor().equals(d.getCor()));
		assertTrue(a.getCor().equals(f.getCor()));

		VerticeColorido e = (VerticeColorido) grafoColorido.getVertice("E");
		assertFalse(e.getCor().equals(b.getCor()));
		assertFalse(e.getCor().equals(a.getCor()));
	}
	
	

}
