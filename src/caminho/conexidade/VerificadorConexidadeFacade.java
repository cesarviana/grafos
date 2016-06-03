package caminho.conexidade;

import model.Grafo;
import caminho.conexidade.impl.VerificadorConexidadeImpl;

public class VerificadorConexidadeFacade {

	private static final VerificadorConexidade verificador = new VerificadorConexidadeImpl();

	public static boolean isConexo(Grafo grafo) {
		return verificador.isConexo(grafo);
	}

}
