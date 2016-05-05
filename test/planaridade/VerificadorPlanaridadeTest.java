package planaridade;

import static org.junit.Assert.*;

import java.io.File;

import model.Grafo;

import org.junit.Test;

import testeutil.TesteUtil;

public class VerificadorPlanaridadeTest {

	private Grafo grafoNaoPlanar = TesteUtil.importaGrafo(new File("grafoNaoPlanar.xml"));
	private Grafo grafoPlanar = TesteUtil.importaGrafo(new File("grafoPlanar.xml"));
	
	@Test
	public void test() {
		VerificadorPlanaridade verificador = VerificadorPlanaridadeFactory.create();
		assertFalse("Grafo deve ser não planar",verificador.isPlanar(grafoNaoPlanar));
		assertTrue("Grafo deve ser planar",verificador.isPlanar(grafoPlanar));
	}

}
