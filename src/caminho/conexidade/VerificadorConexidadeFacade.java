package caminho.conexidade;

import caminho.conexidade.impl.VerificadorConexidadeImpl;
import model.Grafo;

public class VerificadorConexidadeFacade {

	private static final VerificadorConexidade verificador = new VerificadorConexidadeImpl();

	public static boolean isConexo(Grafo grafo) {
		return verificador.isConexo(grafo);
	}

}
