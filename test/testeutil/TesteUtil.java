package testeutil;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Collection;

import leitorxml.impl.LeitorGraphMax;
import model.Grafo;
import model.Vertice;

public class TesteUtil {

    public static Grafo importaGrafo() {
	return importaGrafo(new File("grafo.xml"));
    }

	public static Grafo importaGrafo(File file) {
	return new LeitorGraphMax().ler(file);
    }

    public static void assertCaminho(Collection<Vertice> caminho,
	    String caminhoEsperado) {
	StringBuilder camihoStr = new StringBuilder();
	caminho.stream().forEach(
		(vertice) -> camihoStr.append(vertice.getRotulo()));
	assertEquals(caminhoEsperado, camihoStr.toString());
    }

}
